package com.rafael.config;

import java.util.ArrayList;
import java.util.List;

import com.rafael.AppApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class Swagger implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/swagger", "/swagger-ui.html");
        registry.addRedirectViewController("", "/swagger-ui.html");
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

    @Bean
    public Docket all () {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(AppApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("Spring API")
                        .description("My base project for studing Spring Boot Framework for API construct.")
                        .version("v1.0")
                        .license("MIT License").build())
                .globalOperationParameters(globalOperationParameters());
    }

    private List<Parameter> globalOperationParameters () {
        ParameterBuilder parameterToken = new ParameterBuilder();
        parameterToken.name("Token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(parameterToken.build());

        return parameters;
    }
}
