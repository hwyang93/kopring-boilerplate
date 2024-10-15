package com.kopring.boilerplate.common.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .info(
            Info().title("Havit API")
                .description("Havit 애플리케이션을 위한 API 문서")
                .version("v1.0.0")
        )
        .addSecurityItem(SecurityRequirement().addList("Bearer Authentication"))
        .components(
            Components()
                .addSecuritySchemes(
                    "Bearer Authentication",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .`in`(SecurityScheme.In.HEADER)
                        .name("Authorization")
                )
        )
}