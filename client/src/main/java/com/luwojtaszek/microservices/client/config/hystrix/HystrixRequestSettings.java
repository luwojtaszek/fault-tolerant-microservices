package com.luwojtaszek.microservices.client.config.hystrix;

import java.util.Set;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "hystrix.request")
public class HystrixRequestSettings {

  private Propagate propagate = new Propagate();

  @Data
  public static class Propagate {

    private boolean enabled;
    private Set<String> headers;
  }

}
