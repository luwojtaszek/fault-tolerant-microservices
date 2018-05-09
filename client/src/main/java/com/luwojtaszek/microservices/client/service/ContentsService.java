package com.luwojtaszek.microservices.client.service;

import com.luwojtaszek.microservices.client.domain.GetContentsResult;
import javax.validation.constraints.NotNull;

public interface ContentsService {

  GetContentsResult getContents(@NotNull String contentsCode);
}
