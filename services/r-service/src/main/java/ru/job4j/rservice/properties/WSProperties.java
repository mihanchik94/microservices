package ru.job4j.rservice.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class WSProperties {
    @Value("${ws.host}")
    private String wsHost;

    @Value("${ws.port:8085}")
    private int wsPort;

    @Value("${ws.prefix}")
    private String wsPrefix;

    @Value("${ws.student-endpoint}")
    private String studentEndpoint;
}