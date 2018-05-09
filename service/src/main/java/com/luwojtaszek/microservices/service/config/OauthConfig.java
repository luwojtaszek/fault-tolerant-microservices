package com.luwojtaszek.microservices.service.config;

import com.luwojtaszek.microservices.service.config.oauth.OauthTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OauthConfig {

  @Bean
  public OauthTokenProvider tokenProvider() {
    return () -> "token-content";
  }

}
