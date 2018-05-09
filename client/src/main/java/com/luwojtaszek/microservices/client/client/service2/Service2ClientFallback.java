package com.luwojtaszek.microservices.client.client.service2;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import com.luwojtaszek.microservices.client.client.base.BaseClientFallback;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import rx.Single;

@RequiredArgsConstructor
public class Service2ClientFallback implements BaseClientFallback, Service2Client {

  private final Throwable cause;

  @Override
  public Single<Optional<ServiceResponse>> getContents(String contentsCode) {
    handleCommonExceptions(cause);
    return Single.just(Optional.empty());
  }
}
