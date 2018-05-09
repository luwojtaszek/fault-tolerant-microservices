package com.luwojtaszek.microservices.client.util;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import com.google.common.collect.ImmutableSet;
import feign.FeignException;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class FeignUtil {

  private static final ImmutableSet<Integer> authErrorStatuses =
    ImmutableSet.of(UNAUTHORIZED.value(), FORBIDDEN.value());

  /**
   * Checks whether given exception is Feign exception.
   *
   * @param cause exception cause
   * @return true if the exception is FeignException, false otherwise
   */
  public static boolean isFeignException(Throwable cause) {
    return cause instanceof FeignException;
  }

  /**
   * Checks if given exception is Feign exception and if it's related to authorization issues.
   *
   * @param cause exception cause
   * @return true if the exception is FeignException and it's related to authorization issues, false otherwise
   */
  public static boolean isAuthorizationException(Throwable cause) {
    return isFeignException(cause) && authErrorStatuses.contains(((FeignException) cause).status());
  }

}
