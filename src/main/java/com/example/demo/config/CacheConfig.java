package com.example.demo.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.CacheManager; // Spring's interface
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        var entityConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(1000))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(60)))
                .build();

        CachingProvider provider = Caching.getCachingProvider("org.ehcache.jsr107.EhcacheCachingProvider");
        javax.cache.CacheManager jCacheManager = provider.getCacheManager();
        createCacheIfAbsent(jCacheManager, "com.example.demo.model.entity.User", entityConfig);
        createCacheIfAbsent(jCacheManager, "com.example.demo.model.entity.Order", entityConfig);
        createCacheIfAbsent(jCacheManager, "com.example.demo.model.entity.Product", entityConfig);

        return new JCacheCacheManager(jCacheManager);
    }


    private void createCacheIfAbsent(javax.cache.CacheManager manager, String name, org.ehcache.config.CacheConfiguration<Object, Object> config) {
        if (manager.getCache(name) == null) {
            manager.createCache(name, Eh107Configuration.fromEhcacheCacheConfiguration(config));
        }
    }
}

