package com.luwojtaszek.microservices.client.api.v1.controller;

import com.luwojtaszek.microservices.client.api.v1.model.ContentsResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface ContentsApi {
  @ApiOperation(value = "Gets contents collected from other services", notes = "Returns contents collected from other services", response = ContentsResponse.class, tags={"client"})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Contents", response = ContentsResponse.class)})
  @RequestMapping(value = "/contents/{contentsCode}",
    produces = { "application/json" },
    method = RequestMethod.GET)
  ResponseEntity<?> getCollectedContents(@ApiParam(value = "Custom Header 1" ,required=false) @RequestHeader(value="X-Custom-Header-1", required=false) String customHeader1,
    @ApiParam(value = "Custom Header 2" ,required=false) @RequestHeader(value="X-Custom-Header-2", required=false) String customHeader2,
    @ApiParam(value = "Contents code",required=true ) @PathVariable("contentsCode") String contentsCode);
}
