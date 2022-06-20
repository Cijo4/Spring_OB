package com.cijo.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo("SpringBoot Api Rest", 
				"Computers Api rest docs.",
				"1.0", 
				"http://wwww.google.com", 
				new Contact("Poppy", "http://wwww.google.com", "poppy@gmail.com"), 
				"MIT", 
				"http://wwww.google.com",
				Collections.EMPTY_LIST);
	}
	
}
