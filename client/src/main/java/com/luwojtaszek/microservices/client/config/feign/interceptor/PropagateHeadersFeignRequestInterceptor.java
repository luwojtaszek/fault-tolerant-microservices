package com.luwojtaszek.microservices.client.config.feign.interceptor;

import static java.util.Objects.nonNull;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
public class PropagateHeadersFeignRequestInterceptor implements RequestInterceptor {

  private final ImmutableSet<String> headerNames;

  private static void passRequestHeaders(@NotNull RequestTemplate template,
    @NotNull HttpServletRequest request, @NotNull Set<String> headerNames) {
    Preconditions.checkNotNull(template, "Request Template cannot be null");
    Preconditions.checkNotNull(request, "Request cannot be null");
    Preconditions.checkNotNull(headerNames, "Header names cannot be null");

    headerNames.forEach(headerName -> {
      final String headerValue = request.getHeader(headerName);
      if (nonNull(headerValue)) {
        template.header(headerName, headerValue);
      }
    });
  }

  private static Optional<HttpServletRequest> extractRequest() {
    final ServletRequestAttributes requestAttributes =
      (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    return nonNull(requestAttributes) ? Optional.of(requestAttributes.getRequest())
      : Optional.empty();
  }

  @Override
  public void apply(RequestTemplate template) {
    extractRequest().ifPresent(request -> passRequestHeaders(template, request, headerNames));
  }
}
