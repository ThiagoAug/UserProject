package com.br.regUser.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AwsConfig {
    @PostConstruct
    public void configureAwsCredentials() {
        // Configurar as chaves de acesso da AWS
        String accessKey = "AKIASB4FIJ7NQJJ62D7X";
        String secretKey = "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";

        // Definir as propriedades do sistema
        System.setProperty("aws.accessKeyId", accessKey);
        System.setProperty("aws.secretAccessKey", secretKey);
    }
}

