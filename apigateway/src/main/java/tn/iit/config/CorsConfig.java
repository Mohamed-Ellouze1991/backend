package tn.iit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(CorsConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Configuring CORS for all paths...");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
        logger.info("CORS configuration applied successfully for all paths.");
    }

}
