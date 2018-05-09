package com.luwojtaszek.microservices.client.service.impl;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import com.luwojtaszek.microservices.client.client.service1.Service1Client;
import com.luwojtaszek.microservices.client.client.service2.Service2Client;
import com.luwojtaszek.microservices.client.client.service3.Service3Client;
import com.luwojtaszek.microservices.client.domain.GetContentsResult;
import com.luwojtaszek.microservices.client.service.ContentsService;
import com.luwojtaszek.microservices.client.util.Scheduler;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rx.Single;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {

  private final Service1Client clientA;
  private final Service2Client clientB;
  private final Service3Client clientC;

  private static void sleep(int milliseconds) {
    try {
      TimeUnit.MILLISECONDS.sleep(milliseconds);
    } catch (InterruptedException e) {
      log.error("Interrupted", e);
    }
  }

  @Override
  public GetContentsResult getContents(@NotNull String contentsCode) {
    final Single<Optional<ServiceResponse>> responseA = clientA.getContents(contentsCode)
      .doOnEach(notification -> log.info("Received contents from clientC"));
    final Single<Optional<ServiceResponse>> responseB = clientB.getContents(contentsCode)
      .doOnEach(notification -> log.info("Received contents from clientB"));
    final Single<Optional<ServiceResponse>> responseC = clientC.getContents(contentsCode)
      .doOnEach(notification -> log.info("Received contents from clientC"))
      .observeOn(Scheduler.DEFAULT) // jumping to other thread pool to not block client pool
      .map(result -> {
        log.info("Simulating long result processing like fetching from DB");
        sleep(500);
        return result;
      });

    return Single
      .zip(responseA, responseB, responseC,
        (a, b, c) -> GetContentsResult.builder()
          .serviceA(a.orElse(null))
          .serviceB(b.orElse(null))
          .serviceC(c.orElse(null))
          .build())
      .doOnEach(notification -> log.info("Received all contents"))
      .toBlocking()
      .value();
  }
}
