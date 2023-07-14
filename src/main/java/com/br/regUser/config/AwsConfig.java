package com.br.regUser.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AwsConfig {
    @Value("${amazon.user.accesskey}")
    private String accessKey;
    @Value("${amazon.user.secretkey}")
    private String secretKey;
    @PostConstruct
    public void configureAwsCredentials() {

        // Definir as propriedades do sistema
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretAccessKey", secretKey);
    }
}

