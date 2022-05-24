package com.topekox.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfigurasi Rest Controller Non Spring Data Rest
 */

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Value("${allowed.origin}")
    private String allowedOrigin;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry cors) {
        // set up core mapping
        cors.addMapping(basePath + "/**").allowedOrigins(allowedOrigin);
    }
}
