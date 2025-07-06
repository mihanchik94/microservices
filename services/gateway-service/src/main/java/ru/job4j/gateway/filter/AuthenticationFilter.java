package ru.job4j.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import ru.job4j.gateway.util.JwtUtil;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                checkAuthorizationHeaderExisting(exchange);
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null &&  authHeader.startsWith("Bearer")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    log.warn("Invalid access token.");
                    throw new RuntimeException("Unauthorized access in app!");
                }
            }
            return chain.filter(exchange);
        };
    }

    private void checkAuthorizationHeaderExisting(ServerWebExchange exchange) {
        if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            log.warn("Missing authorization header!");
            throw new RuntimeException("Missing authorization header!");
        }
    }

    public static class Config {

    }
}
