package com.luwojtaszek.microservices.client.client.service1;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class Service1ClientFallbackFactory implements FallbackFactory<Service1Client> {

  @Override
  public Service1Client create(Throwable cause) {
    return new Service1ClientFallback(cause);
  }
}
