package com.luwojtaszek.microservices.client.config;

import com.google.common.collect.ImmutableSet;
import com.luwojtaszek.microservices.client.config.feign.interceptor.OAuth2FeignRequestInterceptor;
import com.luwojtaszek.microservices.client.config.feign.interceptor.PropagateHeadersFeignRequestInterceptor;
import com.luwojtaszek.microservices.client.config.hystrix.HystrixRequestSettings;
import com.luwojtaszek.microservices.client.config.oauth.OauthTokenProvider;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  @ConditionalOnProperty(value = "feign.client.oauth.enabled")
  public RequestInterceptor oauth2FeignRequestInterceptor(OauthTokenProvider tokenProvider) {
    return new OAuth2FeignRequestInterceptor(tokenProvider);
  }

  @Bean
  @ConditionalOnProperty(value = "hystrix.request.propagate.enabled", matchIfMissing = true)
  public RequestInterceptor passIncomingDodaxHeadersFeignRequestInterceptor(
    HystrixRequestSettings requestSettings) {
    return new PropagateHeadersFeignRequestInterceptor(
      ImmutableSet.copyOf(requestSettings.getPropagate().getHeaders()));
  }

}
