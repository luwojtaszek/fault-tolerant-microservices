package com.luwojtaszek.microservices.client;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Slf4j
public class RxJavaExamples {

  private static Integer delayedValue(int num) throws InterruptedException {
    TimeUnit.SECONDS.sleep(1);
    return num;
  }

  @Test
  public void observablesArelazyAndSynchronousByDefault() {
    final Observable<Object> observable = Observable.unsafeCreate(subscriber -> {
      subscriber.onNext(101);
      subscriber.onNext(102);
      subscriber.onCompleted();
    }).doOnNext(elem -> log.info("Received elem: {}", elem));

    // observable.subscribe(); // make it hot

    log.info("END of the method");
  }

  @Test
  public void howToReturnValueTakenFromObservable() {
    final Integer result = Observable.just(500)
      .toBlocking().single();

    assertEquals(result, Integer.valueOf(500));
  }

  @Test
  public void doingObservableAsyncWay() {
    ArrayList<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Observable<Integer>> observables = numbers.stream()
      .map(num ->
        Observable
          .fromCallable(() -> delayedValue(num))
          .subscribeOn(Schedulers.io())) // Not recommended, Creates as many threads as needed - you're loosing control over thread pool
      .collect(toList());

    Observable.merge(observables)
      .subscribe(value -> log
        .info("Received value: {} on thread: {}", value, Thread.currentThread().getId()));

    // TimeUnit.SECONDS.sleep(2); // without it method will execute before observables job is done
    log.info("END of the method");
  }

  @Test
  public void doingItAsyncWayWithCustomScheduler() throws InterruptedException {
    Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(2));
    ArrayList<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Observable<Integer>> observables = numbers.stream()
      .map(num ->
        Observable
          .fromCallable(() -> delayedValue(num))
          .subscribeOn(scheduler))
      .collect(toList());

    long start = System.currentTimeMillis();
    Observable.merge(observables)
      .doOnEach(value -> log
        .info("subscribe | value: {}, thread: {}", value, Thread.currentThread().getId()))
      .toBlocking()
      .subscribe();
    long end = System.currentTimeMillis();

    log.info("END of the method, finished in: {} seconds", (end - start) / 1000);
  }

  @Test
  public void doingItAsyncWaySwitchingThreadPools() throws InterruptedException {
    Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(2));
    Scheduler schedule2 = Schedulers.from(Executors.newFixedThreadPool(5));
    ArrayList<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Observable<Integer>> observables = numbers.stream()
      .map(num ->
        Observable
          .fromCallable(() -> delayedValue(num))
          .subscribeOn(scheduler))
      .collect(toList());

    long start = System.currentTimeMillis();
    Observable.merge(observables)
      //.observeOn(schedule2) // jumping to the other thread pool to do some heavy operation
      .map(val -> {
        log.info("Mapping val: {} into: {} on thread: {}", val, val + 100,
          Thread.currentThread().getId());
        // doing some time consuming computation here
        try {
          TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return val + 100;
      })
      .doOnEach(value -> log
        .info("subscribe | value: {}, thread: {}", value, Thread.currentThread().getId()))
      .toBlocking()
      .subscribe();
    long end = System.currentTimeMillis();

    log.info("END of the method, finished in: {} seconds", (end - start) / 1000);
  }

}
