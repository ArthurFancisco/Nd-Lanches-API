package com.ndlanches.nd_lanches_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * PASSO 1 — Centraliza o CORS aqui.
 * Remove todos os @CrossOrigin("*") dos controllers depois de adicionar isso.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    // Bean para facilitar injeção da chave em qualquer lugar no futuro
    @Bean
    public String adminKeyBean(
            @org.springframework.beans.factory.annotation.Value("${admin.key}") String adminKey) {
        return adminKey;
    }
}