package com.luwojtaszek.microservices.client.client.service3;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rx.Single;

@FeignClient(name = "service3", url = "${client.service3.url}", fallbackFactory = Service3ClientFallbackFactory.class)
public interface Service3Client {

  @GetMapping(value = "/contents/{contentsCode}")
  Single<Optional<ServiceResponse>> getContents(@PathVariable("contentsCode") String contentsCode);
}
