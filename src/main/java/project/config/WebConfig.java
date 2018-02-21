package project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Configuration class for Spring MVC
 */
@Configuration
@ComponentScan(basePackages = "project.controller")
public class WebConfig implements WebMvcConfigurer {
}
