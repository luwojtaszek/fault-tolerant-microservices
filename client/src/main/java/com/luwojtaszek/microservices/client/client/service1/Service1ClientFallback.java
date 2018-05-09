package com.luwojtaszek.microservices.client.client.service1;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import com.luwojtaszek.microservices.client.client.base.BaseClientFallback;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import rx.Single;

@RequiredArgsConstructor
public class Service1ClientFallback implements BaseClientFallback, Service1Client {

  private final Throwable cause;

  @Override
  public Single<Optional<ServiceResponse>> getContents(String contentsCode) {
    handleCommonExceptions(cause);
    // Do whatever you want like: fetch from cache, return dummy object, etc.
    return Single.just(Optional.of(ServiceResponse.builder()
      .message("ServiceA - mocked response")
      .build()));
  }
}
