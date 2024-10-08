package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    // Beans get injected into spring container
    // will cover this in detail in dependency injection video
    // gives use access to rest template throughout the app
    public RestTemplate restTemplate() {
        // configure your rest template options
        return new RestTemplate();
    }
}
