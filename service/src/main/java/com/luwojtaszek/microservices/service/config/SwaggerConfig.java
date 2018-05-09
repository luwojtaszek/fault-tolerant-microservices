package com.luwojtaszek.microservices.service.config;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

  private static final String BASE_PACKAGE = "com.luwojtaszek.microservices.service.api";

  private static ApiInfo apiInfo(String appName) {
    return new ApiInfoBuilder()
      .title(appName + " API")
      .description(appName + " Api Description.")
      .license(EMPTY)
      .licenseUrl("http://unlicense.org")
      .termsOfServiceUrl(EMPTY)
      .version("1.0.0")
      .contact(new Contact(EMPTY, EMPTY, EMPTY))
      .build();
  }

  @Bean
  public UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder()
      .docExpansion(DocExpansion.LIST)
      .operationsSorter(OperationsSorter.ALPHA)
      .defaultModelRendering(ModelRendering.EXAMPLE)
      .tagsSorter(TagsSorter.ALPHA)
      .build();
  }

  @Bean
  public Docket api(@Value("${info.name}") String appName) {
    return new Docket(DocumentationType.SWAGGER_2).select()
      .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
      .build()
      .directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
      .directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class)
      .apiInfo(apiInfo(appName));
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/swagger-ui.html");
  }
}
