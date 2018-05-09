package com.luwojtaszek.microservices.client.config.feign.interceptor;

import static java.util.Objects.nonNull;
import com.luwojtaszek.microservices.client.config.oauth.OauthTokenProvider;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String BEARER_TOKEN_TYPE = "Bearer";

  private final OauthTokenProvider tokenProvider;

  private static String tokenHeaderValue(String token) {
    return String.format("%s %s", BEARER_TOKEN_TYPE, token);
  }

  @Override
  public void apply(RequestTemplate template) {
    final String token = tokenProvider.getToken();

    if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
      log.warn("The Authorization token has been already set");
    } else if (nonNull(token)) {
      template.header(AUTHORIZATION_HEADER, tokenHeaderValue(token));
    } else {
      log.error("No valid token found!");
    }
  }
}
