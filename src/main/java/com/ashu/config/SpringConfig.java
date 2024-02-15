package com.ashu.config;

import com.ashu.service.VideoProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public VideoProcessor videoProcessor() {
        return new VideoProcessor();
    }
}
