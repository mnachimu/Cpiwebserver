package com.orisinterview.cpiwebserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Executor executor() {
        return new Executor();
    }
}
