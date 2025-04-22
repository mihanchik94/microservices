package ru.job4j.sservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "s3")
@Configuration
public class S3Properties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
