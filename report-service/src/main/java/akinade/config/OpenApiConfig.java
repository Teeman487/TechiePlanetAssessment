package akinade.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${swagger.api-gateway-url}") // swagger.api-gateway-url=http://localhost:8989/report
    String apiGatewayUrl;

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI()
                .info(new Info().
                        title("Report Service APIs").
                        description("Students Report Service APIs").
                        version("1.0.0")
                        .contact(new Contact().name("Akinade").email("adebusoyeteeman@gmail.com")))
                .servers(List.of(new Server().url(apiGatewayUrl)));
    }
}

// Swagger UI: http://localhost:8989/report/swagger-ui.html