package com.luwojtaszek.microservices.client.api.mapper;

import com.luwojtaszek.microservices.client.api.base.BaseContentsMapper;
import com.luwojtaszek.microservices.client.api.v1.model.ContentsResponse;
import com.luwojtaszek.microservices.client.domain.GetContentsResult;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ContentsV1Mapper extends BaseContentsMapper {

  @Mappings({
    @Mapping(target = "duration", expression = "java(duration(start, end))"),
  })
  ContentsResponse map(GetContentsResult result, String message, LocalDateTime start,
    LocalDateTime end);
}
