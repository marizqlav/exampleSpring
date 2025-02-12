package com.app.prueba.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI myOpenAPI() {
        License license = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        Info info = new Info().title("Demo Service API")
                .description("This API exposes endpoints to manage demp.")
                .version("1.0")
                .license(license);

        Server server = new Server().url("http://localhost:8080").description("Server URL in Development environment");

        return new OpenAPI()
                .info(info).servers(List.of(server));
    }
}
