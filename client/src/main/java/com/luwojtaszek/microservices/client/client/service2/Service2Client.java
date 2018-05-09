package com.luwojtaszek.microservices.client.client.service2;

import com.luwojtaszek.microservices.client.api.v1.model.ServiceResponse;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rx.Single;

@FeignClient(name = "service2", url = "${client.service2.url}", fallbackFactory = Service2ClientFallbackFactory.class)
public interface Service2Client {

  @GetMapping(value = "/contents/{contentsCode}")
  Single<Optional<ServiceResponse>> getContents(@PathVariable("contentsCode") String contentsCode);
}
