package com.example.demo.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {
//
//    private final UsersRepo usersRepo;
//
//    CacheConfig(UsersRepo usersRepo) {
//        this.usersRepo = usersRepo;
//    }
//	
//	public CacheManager cacheManager() {
//		CaffeineCacheManager cacheManager = new CaffeineCacheManager("girlsCache");
//		cacheManager.setCaffeine(
//				Caffeine.newBuilder()
//				.expireAfterAccess(10, TimeUnit.MINUTES)
//				.maximumSize(100)
//				);
//		return cacheManager();
//	}
	
//	@Bean
//	public CacheManager cacheManager() {
//	    CaffeineCacheManager cacheManager = new CaffeineCacheManager() {
//	        @Override
//	        protected com.github.benmanes.caffeine.cache.Cache<Object, Object> createNativeCaffeineCache(String name) {
//	            if ("girlsCache".equals(name)) {
//	                return Caffeine.newBuilder()
//	                        .expireAfterWrite(10, TimeUnit.MINUTES)
//	                        .maximumSize(100)
//	                        .build();
//	            } else if ("boysCache".equals(name)) {
//	                return Caffeine.newBuilder()
//	                        .expireAfterWrite(5, TimeUnit.MINUTES)
//	                        .maximumSize(50)
//	                        .build();
//	            }
//	            // default TTL
//	            return Caffeine.newBuilder()
//	                    .expireAfterWrite(2, TimeUnit.MINUTES)
//	                    .maximumSize(50)
//	                    .build();
//	        }
//	    };
//	    cacheManager.setCacheNames(Arrays.asList("girlsCache", "boysCache"));
//	    return cacheManager;
//	}
	
	
    @Bean
    public CacheManager cacheManager() {
        // Define cache configs in one place (Map)
        Map<String, CacheSpec> cacheSpecs = new HashMap<>();
        cacheSpecs.put("boysCache", new CacheSpec(5, 100));   // 5 min TTL, max 100
        cacheSpecs.put("girlsCache", new CacheSpec(10, 200)); // 10 min TTL, max 200
        cacheSpecs.put("default", new CacheSpec(2, 50));      // default fallback

 CaffeineCacheManager cacheManager = new CaffeineCacheManager() {
	 
 @Override
 protected com.github.benmanes.caffeine.cache.Cache<Object, Object> createNativeCaffeineCache(String name) {
          CacheSpec spec = cacheSpecs.getOrDefault(name, cacheSpecs.get("default"));
          
          return Caffeine.newBuilder()
           .expireAfterWrite(spec.ttlMinutes, TimeUnit.MINUTES)
           .maximumSize(spec.maxSize)
           .build();
            }
        };

        cacheManager.setCacheNames(cacheSpecs.keySet());
        return cacheManager;
    }

    // Simple helper class
    static class CacheSpec {
        final int ttlMinutes;
        final int maxSize;

        CacheSpec(int ttlMinutes, int maxSize) {
            this.ttlMinutes = ttlMinutes;
            this.maxSize = maxSize;
        }
    }


}
