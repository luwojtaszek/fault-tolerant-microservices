package com.luwojtaszek.microservices.client.client.service2;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class Service2ClientFallbackFactory implements FallbackFactory<Service2Client> {

  @Override
  public Service2Client create(Throwable cause) {
    return new Service2ClientFallback(cause);
  }
}
