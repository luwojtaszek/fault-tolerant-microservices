package com.luwojtaszek.microservices.client.api.base;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public interface BaseContentsMapper {

  default org.joda.time.DateTime map(java.time.LocalDateTime date) {
    if (Objects.nonNull(date)) {
      return new org.joda.time.DateTime(date.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
    return null;
  }

  default int toMilis(LocalDateTime dateTime) {
    return (int) dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
  }

  default int duration(LocalDateTime start, LocalDateTime end) {
    return toMilis(end) - toMilis(start);
  }

}
