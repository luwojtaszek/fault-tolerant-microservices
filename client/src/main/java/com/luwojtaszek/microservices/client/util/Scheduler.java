package com.luwojtaszek.microservices.client.util;

import java.util.concurrent.Executors;
import lombok.experimental.UtilityClass;
import rx.schedulers.Schedulers;

@UtilityClass
public class Scheduler {

  public static final rx.Scheduler DEFAULT = Schedulers.from(Executors.newFixedThreadPool(50));
}
