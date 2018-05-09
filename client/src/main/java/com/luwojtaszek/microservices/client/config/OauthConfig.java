package com.luwojtaszek.microservices.client.config;

import com.luwojtaszek.microservices.client.config.oauth.OauthTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OauthConfig {

  @Bean
  public OauthTokenProvider tokenProvider() {
    // FIXME - it's fake token provider to make it works implement proper one
    return () -> "fake-token-content";
  }

}
