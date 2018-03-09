package com.dev.sv.server.conf;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
@Configuration
@EnableScheduling
@Component
public class CacheConfig {
    @CacheEvict(allEntries = true, value = {"user"})
    @Scheduled(fixedDelay = 120000 ,  initialDelay = 1000)
    public void userCacheEvict() {
        System.out.println("Flush user Cache " + new Date());
    }
}
