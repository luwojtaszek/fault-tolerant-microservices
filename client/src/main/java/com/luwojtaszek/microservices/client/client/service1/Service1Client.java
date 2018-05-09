package com.luwojtaszek.microservices.client.client.service1;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rx.Single;

@FeignClient(name = "service1", url = "${client.service1.url}", fallbackFactory = Service1ClientFallbackFactory.class)
public interface Service1Client {

  @GetMapping(value = "/contents/{contentsCode}")
  Single<Optional<ServiceResponse>> getContents(@PathVariable("contentsCode") String contentsCode);
}
