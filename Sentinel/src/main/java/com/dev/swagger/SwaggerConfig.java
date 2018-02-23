package com.dev.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Sahan on 25/6/2017.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.dev"))
                .paths(regex("/api.*"))
                .build()
                .apiInfo(metaData())
                .globalOperationParameters(aParameters);
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Sentinal API",
                "Powered by Spring Boot",
                "1.0.1",
                "Terms of service",
                new Contact("Sahan Devaka Lokuge", "http://www.maybank.com/about/", "suhan.d@maybank.com.com"),
                "License Version 2.0",
                "http://www.maybank.com/licenses/LICENSE-2.0");
        return apiInfo;
    }
}