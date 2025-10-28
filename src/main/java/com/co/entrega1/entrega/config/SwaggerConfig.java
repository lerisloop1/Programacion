package com.co.entrega1.entrega.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Ventas de Motos")
                        .version("1.0.0")
                        .description("Sistema de gestión de compras de motos Desarrollado por: Matheo Ruiz, Mariana Uribe y Darwin Rubio"));
    }
}

