package ru.job4j.authservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class JwtProperties {
    @Value("${jwt.secret-key}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationMills;
}
