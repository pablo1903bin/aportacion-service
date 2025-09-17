package com.tesoramobil.aportacion.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;@Configuration
public class OpenApiServerConfig {
	
  @Value("${api-doc.server-base-path:/aportacion-service}")
  private String serverBasePath;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Users Service API").version("v1"))

        // 1) "Servers": le dice a Swagger que la base de las llamadas es el GATEWAY
        .addServersItem(new Server().url(serverBasePath))

        // 2) "SecurityRequirement": marca que las operaciones requieren "bearerAuth"
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))

        // 3) "SecurityScheme": define qué es "bearerAuth" (Authorization: Bearer <JWT>)
        .components(new Components().addSecuritySchemes(
            "bearerAuth",
            new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")));
  }
}
