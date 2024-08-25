package com.orisinterview.cpiwebserver;

import com.orisinterview.cpiwebserver.blsapi.BLSApiClient;
import com.orisinterview.cpiwebserver.blsapi.BLSApiClientV1;
import com.orisinterview.cpiwebserver.blsapi.BLSApiClientV2;
import com.orisinterview.cpiwebserver.cache.CacheManager;
import com.orisinterview.cpiwebserver.cache.ICacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Executor executor() {
        return new Executor();
    }

    @Bean
    public BLSApiClient blsApiClient() {
        return new BLSApiClientV1(); // change to v2 if using v2 and add the reg key in applciation properties
    }

    @Bean
    public ICacheManager cacheManager() {
        return CacheManager.getInstance();
    }
}
