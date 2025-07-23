package com.berteek.bankingapp.service;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager("wallets");
        manager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .maximumSize(10_000)
                .recordStats());
        return manager;
    }
}
