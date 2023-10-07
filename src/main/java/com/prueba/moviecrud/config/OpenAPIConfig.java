package com.prueba.moviecrud.config;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl("localhost:8080");
    devServer.setDescription("Server URL in Development environment");

    Contact contact = new Contact();
    contact.setEmail("mauricio.bec1996@gmail.com");
    contact.setName("Mauricio Espinosa");
    contact.setUrl("https://github.com/maurobec96");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Moviecrud API")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage Movies.")
        .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer));
  }
}