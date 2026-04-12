package com.example.assignmnet.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class UtilityBean {

    public UtilityBean() {
        System.out.println("1. Bean Created");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. Bean Initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("3. Bean Destroyed");
    }
}

@Configuration
class AppConfig {

    @Bean
    public UserService userService() {
        return new UserService();
    }
}

@Component
class UserService {
}
