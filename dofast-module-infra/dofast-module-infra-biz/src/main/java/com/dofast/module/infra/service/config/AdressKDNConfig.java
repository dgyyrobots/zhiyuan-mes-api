package com.dofast.module.infra.service.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdressKDNConfig {
    @Bean
    public
    OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
