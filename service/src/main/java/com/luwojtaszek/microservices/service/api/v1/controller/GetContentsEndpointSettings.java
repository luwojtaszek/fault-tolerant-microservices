package com.luwojtaszek.microservices.service.api.v1.controller;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "api.endpoints.contents.get")
public class GetContentsEndpointSettings {

  @NotNull
  private Integer minDuration;

  @NotNull
  private Integer maxDuration;

  @NotNull
  private String notFoundCodes;

  public Set<String> getNotFoundCodesAsSet() {
    return Sets.newHashSet(notFoundCodes.split(","));
  }
}
