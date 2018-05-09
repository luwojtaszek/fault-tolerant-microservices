package com.luwojtaszek.microservices.client.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.joda.time.DateTime;

@Data
@ApiModel(description = "Represents contents api response")
public class ContentsResponse {

  @ApiModelProperty(example = "Client - response", value = "Message")
  private String message;

  @ApiModelProperty(example = "2018-06-01T11:15:45Z", value = "Date when method started doing its job")
  private DateTime start;

  @ApiModelProperty(example = "2018-06-01T11:15:45Z", value = "Date when method finished doing its job")
  private DateTime end;

  @ApiModelProperty(example = "123", value = "Duration of method execution in miliseconds")
  private Integer duration;

  @ApiModelProperty(value = "Service A - response")
  private ServiceResponse serviceA;

  @ApiModelProperty(value = "Service B - response")
  private ServiceResponse serviceB;

  @ApiModelProperty(value = "Service C - response")
  private ServiceResponse serviceC;

}
