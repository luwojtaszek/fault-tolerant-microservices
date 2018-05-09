package com.luwojtaszek.microservices.client.client.service3;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class Service3ClientFallbackFactory implements FallbackFactory<Service3Client> {

  @Override
  public Service3Client create(Throwable cause) {
    return new Service3ClientFallback(cause);
  }
}
