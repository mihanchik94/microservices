package ru.job4j.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SwaggerAggregatorConfig {
    @Bean
    public RouterFunction<ServerResponse> swaggerRoutes() {
        return RouterFunctions.route(
                RequestPredicates.GET("/swagger-ui/swagger-initializer.js"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("""
                    window.onload = function() {
                        window.ui = SwaggerUIBundle({
                            urls: [
                                {url: "/v3/api-docs/auth-service", name: "Auth Service"},
                                {url: "/v3/api-docs/r-service", name: "R Service"}
                            ],
                            dom_id: '#swagger-ui',
                            presets: [
                                SwaggerUIBundle.presets.apis,
                                SwaggerUIStandalonePreset
                            ],
                            layout: "StandaloneLayout"
                        });
                    };
                """)
        );
    }

}
