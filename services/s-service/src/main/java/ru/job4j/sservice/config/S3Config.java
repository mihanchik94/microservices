package ru.job4j.sservice.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.job4j.sservice.properties.S3Properties;

@Configuration
public class S3Config {

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(S3Properties properties) {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(properties.getAccessKey(),
                        properties.getSecretKey()));
    }

    @Bean
    public AmazonS3 amazonS3(S3Properties properties, AWSCredentialsProvider credentialsProvider) {
        return AmazonS3ClientBuilder.standard()
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(properties.getEndpoint(), Regions.US_EAST_1.getName()))
                .withCredentials(credentialsProvider)
                .build();
    }
}