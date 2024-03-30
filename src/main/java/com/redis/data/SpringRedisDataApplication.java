package com.redis.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringRedisDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisDataApplication.class, args);
    }

}
