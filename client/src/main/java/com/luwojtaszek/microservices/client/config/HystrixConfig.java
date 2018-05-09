package com.luwojtaszek.microservices.client.config;

import com.luwojtaszek.microservices.client.config.hystrix.RequestAttributeHystrixConcurrencyStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {

  @Bean
  @ConditionalOnProperty(value = "hystrix.request.propagate.enabled", matchIfMissing = true)
  public RequestAttributeHystrixConcurrencyStrategy hystrixRequestAutoConfiguration() {
    return new RequestAttributeHystrixConcurrencyStrategy();
  }

}
