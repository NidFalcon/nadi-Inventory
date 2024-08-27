package com.cpi.nadi.is.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.cpi.nadi.is.controller")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map requests for /js/** to the /js/ directory
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/");
        
        // Similarly, you can map other static resources like CSS, images, etc.
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");
        
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/");
    }
}