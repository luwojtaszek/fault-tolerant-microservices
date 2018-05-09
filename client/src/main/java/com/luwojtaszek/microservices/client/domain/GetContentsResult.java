package com.luwojtaszek.microservices.client.domain;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetContentsResult {

  private ServiceResponse serviceA;
  private ServiceResponse serviceB;
  private ServiceResponse serviceC;
}
