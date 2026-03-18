package com.ndlanches.nd_lanches_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Libera o acesso para o seu frontend no Firebase e testes locais
        registry.addMapping("/**")
                .allowedOrigins(
                    "https://ndlanches-e7ccf.web.app", 
                    "https://ndlanches-e7ccf.firebaseapp.com",
                    "http://localhost:5500",
                    "http://127.0.0.1:5500"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}