package org.example.java_web_hn_k24_cntt5_dohongky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("org.example.java_web_hn_k24_cntt5_dohongky")
public class AppConfig implements WebMvcConfigurer {

        @Bean
        public SpringResourceTemplateResolver templateResolver() {
                SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
                resolver.setPrefix("/WEB-INF/views/");
                resolver.setSuffix(".html");
                resolver.setCharacterEncoding("UTF-8");
                return resolver;
        }

        @Bean
        public SpringTemplateEngine templateEngine() {
                SpringTemplateEngine engine = new SpringTemplateEngine();
                engine.setTemplateResolver(templateResolver());
                return engine;
        }

        @Bean
        public ThymeleafViewResolver viewResolver() {
                ThymeleafViewResolver resolver = new ThymeleafViewResolver();
                resolver.setTemplateEngine(templateEngine());
                resolver.setCharacterEncoding("UTF-8");
                return resolver;
        }

        @Bean
        public StandardServletMultipartResolver multipartResolver() {
                return new StandardServletMultipartResolver();
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/uploads/**").addResourceLocations("/WEB-INF/uploads/");
        }
}