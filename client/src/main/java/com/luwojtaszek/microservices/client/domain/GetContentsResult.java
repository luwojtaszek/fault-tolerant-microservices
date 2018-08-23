package com.luwojtaszek.microservices.client.domain;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import lombok.*;

@Value
@Builder
public class GetContentsResult {

  private ServiceResponse serviceA;
  private ServiceResponse serviceB;
  private ServiceResponse serviceC;
}
