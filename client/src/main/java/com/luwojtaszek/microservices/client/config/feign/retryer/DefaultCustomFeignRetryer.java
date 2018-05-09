package com.luwojtaszek.microservices.client.config.feign.retryer;

import feign.Retryer;

public class DefaultCustomFeignRetryer extends Retryer.Default {

  public DefaultCustomFeignRetryer() {
    super(100, 300, 1);
  }
}
