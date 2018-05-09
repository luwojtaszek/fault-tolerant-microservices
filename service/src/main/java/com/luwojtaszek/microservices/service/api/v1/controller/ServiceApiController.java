package com.luwojtaszek.microservices.service.api.v1.controller;

import static java.util.Objects.nonNull;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import com.luwojtaszek.microservices.service.api.base.V1Controller;
import com.luwojtaszek.microservices.service.api.v1.model.ServiceResponse;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ServiceApiController implements V1Controller, ServiceApi {

  private final GetContentsEndpointSettings settings;

  @Value("${info.name}")
  private String appName;

  private static org.joda.time.DateTime map(java.time.LocalDateTime date) {
    if (nonNull(date)) {
      return new org.joda.time.DateTime(date.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
    return null;
  }

  private static int toMilis(LocalDateTime dateTime) {
    return (int) dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
  }

  private static String authHeader() {
    final ServletRequestAttributes requestAttributes =
      (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    if (nonNull(requestAttributes)) {
      return requestAttributes.getRequest().getHeader("Authorization");
    } else {
      return null;
    }
  }

  @Override
  public ResponseEntity<?> getCollectedContents(
    @ApiParam(value = "Custom Header 1", required = false) @RequestHeader(value = "X-Custom-Header-1", required = false) String customHeader1,
    @ApiParam(value = "Custom Header 2", required = false) @RequestHeader(value = "X-Custom-Header-2", required = false) String customHeader2,
    @ApiParam(value = "Contents code", required = true) @PathVariable("contentsCode") String contentsCode)
    throws InterruptedException {
    log.info("Received request with code: {}", contentsCode);
    final LocalDateTime start = LocalDateTime.now();
    sleep();

    if (settings.getNotFoundCodesAsSet().contains(contentsCode)) {
      return notFound().build();
    } else {
      final LocalDateTime end = LocalDateTime.now();
      return ok(ServiceResponse.builder()
        .message(appName + " - response")
        .start(map(start))
        .end(map(end))
        .duration(toMilis(end) - toMilis(start))
        .receivedHeader("X-Custom-Header-1", customHeader1)
        .receivedHeader("X-Custom-Header-2", customHeader2)
        .receivedHeader("Authorization", authHeader())
        .build());
    }
  }

  private void sleep() throws InterruptedException {
    final int min = settings.getMinDuration();
    final int max = settings.getMaxDuration();
    int duration = ThreadLocalRandom.current().nextInt(min, max) * 100;
    TimeUnit.MILLISECONDS.sleep(duration);
  }
}
