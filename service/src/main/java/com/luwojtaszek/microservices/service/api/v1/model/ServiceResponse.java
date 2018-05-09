package com.luwojtaszek.microservices.service.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.joda.time.DateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Represents service response")
public class ServiceResponse {

  @ApiModelProperty(example = "Service A - response", value = "Message")
  private String message;

  @ApiModelProperty(example = "2018-06-01T11:15:45Z", value = "Date when method started doing its job")
  private DateTime start;

  @ApiModelProperty(example = "2018-06-01T11:15:45Z", value = "Date when method finished doing its job")
  private DateTime end;

  @ApiModelProperty(example = "123", value = "Duration of method execution in miliseconds")
  private Integer duration;

  @ApiModelProperty(value = "Map of received headers")
  @Singular
  private Map<String, String> receivedHeaders;
}
