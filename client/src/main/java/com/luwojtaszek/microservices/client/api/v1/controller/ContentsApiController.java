package com.luwojtaszek.microservices.client.api.v1.controller;

import static org.springframework.http.ResponseEntity.ok;
import com.luwojtaszek.microservices.client.api.base.V1Controller;
import com.luwojtaszek.microservices.client.api.mapper.ContentsV1Mapper;
import com.luwojtaszek.microservices.client.domain.GetContentsResult;
import com.luwojtaszek.microservices.client.service.ContentsService;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContentsApiController implements V1Controller, ContentsApi {

  private final ContentsService contentsService;
  private final ContentsV1Mapper mapper;

  @Override
  public ResponseEntity<?> getCollectedContents(
    @ApiParam(value = "Custom Header 1", required = false) @RequestHeader(value = "X-Custom-Header-1", required = false) String customHeader1,
    @ApiParam(value = "Custom Header 2", required = false) @RequestHeader(value = "X-Custom-Header-2", required = false) String customHeader2,
    @ApiParam(value = "Contents code", required = true) @PathVariable("contentsCode") String contentsCode) {

    final LocalDateTime start = LocalDateTime.now();
    final GetContentsResult result = contentsService.getContents(contentsCode);
    final LocalDateTime end = LocalDateTime.now();
    return ok(mapper.map(result, "Client Response", start, end));
  }
}
