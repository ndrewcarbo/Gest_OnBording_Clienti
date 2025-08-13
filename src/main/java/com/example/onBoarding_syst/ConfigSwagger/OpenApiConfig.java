package com.example.onBoarding_syst.ConfigSwagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@Profile("develop")
public class OpenApiConfig {
	
	@Bean
	OpenAPI SecurityOpenAPI() {
		String Schema = "bearerAuth";
		
		return new OpenAPI()
	            .info(new Info()
	                    .title("OnBoarding System API Documentazione")
	                    .version("1.0")
	                    .description("Documentazione delle API per il sistema di onboarding"))
	            .addSecurityItem(new SecurityRequirement().addList(Schema))
	            .components(new Components().addSecuritySchemes(Schema,
	                    new SecurityScheme()
	                            .name(Schema)
	                            .type(SecurityScheme.Type.HTTP)
	                            .scheme("bearer")
	                            .bearerFormat("JWT")));
	}
	
	/*
	@Bean
	OpenAPI SwaggerOpenAPI() {
		return new OpenAPI().info(new Info().title("OnBoarding System API Documentazione")
				.version("1.0")
				.description("Documentazione delle API"));
	}
	*/
}
