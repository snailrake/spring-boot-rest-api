package com.webapp.testapi.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Music API",
                description = "Music API processing tracks, artist data", version = "1.0.0",
                contact = @Contact(
                        name = "Dyukov Viktor",
                        email = "snailrake@mail.ru"
                )
        )
)
public class OpenApiConfig {

}
