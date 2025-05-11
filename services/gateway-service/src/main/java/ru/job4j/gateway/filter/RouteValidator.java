package ru.job4j.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> OPEN_API_ENDPOINTS = List.of(
            "/auth/**",
            "/eureka",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> OPEN_API_ENDPOINTS
                    .stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
