package com.luwojtaszek.microservices.client.client.base;


import static com.luwojtaszek.microservices.client.util.FeignUtil.isAuthorizationException;
import com.luwojtaszek.microservices.client.exception.ClientAuthorizationException;

public interface BaseClientFallback {

  default void handleCommonExceptions(Throwable cause) {
    handleAuthorizationException(cause);
  }

  default void handleAuthorizationException(Throwable cause) {
    if (isAuthorizationException(cause)) {
      throw new ClientAuthorizationException();
    }
  }
}
