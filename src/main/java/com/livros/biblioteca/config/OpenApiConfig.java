package com.livros.biblioteca.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Específico pro SWAGGER pra colocar o Authorizer la

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define o nome do esquema de segurança
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                // Adiciona a configuração de segurança global a todos os endpoints
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // Define os componentes da API, incluindo o esquema de segurança
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP) // O tipo é HTTP
                                                .scheme("bearer") // O esquema é "bearer"
                                                .bearerFormat("JWT") // O formato é JWT
                                )
                )
                // Adiciona informações gerais à sua documentação
                .info(new Info()
                        .title("API da Biblioteca")
                        .description("API RESTful para gerenciamento de uma biblioteca.")
                        .version("v1.0.0")
                );
    }
}
