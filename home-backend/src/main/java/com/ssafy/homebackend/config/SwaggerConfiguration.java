package com.ssafy.homebackend.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
		        title = "SSAFY-11-HOME API 명세서",
		        description = "description is here",
		        version = "v1",
		        contact = @Contact(
//		            name = "admin",
//		            email = "admin@admin.com",
		            url = "https://github.com/SSAFY-11-HOME"
		        )
		    ),
		servers = {
				@Server(url = "/", description = "Default Server URL")
		})

@Configuration
public class SwaggerConfiguration {
	@Bean
	public GroupedOpenApi allApi() {
		return GroupedOpenApi.builder().group("all").pathsToMatch("/**").build();
	}

	@Bean
	public GroupedOpenApi houseApi() {
		return GroupedOpenApi.builder().group("house").pathsToMatch("/house/**").build();
	}

	@Bean
	public GroupedOpenApi searchApi() {
		return GroupedOpenApi.builder().group("search").pathsToMatch("/search/**").build();
	}

	@Bean
	public GroupedOpenApi userApi() {
		return GroupedOpenApi.builder().group("user").pathsToMatch("/user/**").build();
	}
	
	@Bean
	public GroupedOpenApi qboardApi() {
		return GroupedOpenApi.builder().group("qboard").pathsToMatch("/qboard/**").build();
	}
}
