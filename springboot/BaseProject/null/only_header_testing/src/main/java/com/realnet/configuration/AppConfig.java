package com.realnet.configuration;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class AppConfig implements WebMvcConfigurer{

	/*
	 * Configure View Resolver
	 */
	@Bean
	TilesViewResolver viewResolver() {
		TilesViewResolver viewResolver = new TilesViewResolver();
		return viewResolver;
	}

	@Bean
	TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("WEB-INF/tiles.xml");
		tilesConfigurer
				.setPreparerFactoryClass(org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory.class);
		return tilesConfigurer;
	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 *
	 */
	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler("/resources/**").addResourceLocations(
	 * "/resources/"); }
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }

	/*
	 * Configure View Resolver
	 */
	/*
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver
	 * viewResolver = new InternalResourceViewResolver();
	 * viewResolver.setViewClass(JstlView.class);
	 * viewResolver.setPrefix("/WEB-INF/views/");
	 * viewResolver.setSuffix(".jsp");
	 * 
	 * return viewResolver; }
	 */

	/*
	 * Configure MessageSource to provide internationalized messages
	 *
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB
		return multipartResolver;
	}
	
	// @Bean
/*	 public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	 {
	        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();

	        Properties mappings = new Properties();
	        mappings.put("org.springframework.web.servlet.PageNotFound", "error_404");
	        mappings.put("org.springframework.dao.DataAccessException", "error_500");
	        mappings.put("org.springframework.transaction.TransactionException", "error_500");
	        mappings.put("org.springframework.web.servlet.NoHandlerFoundException", "error_404");
	        mappings.put("org.springframework.http.HttpStatus.NOT_FOUND", "error_404");
	        mappings.put("java.io.IOException", "error_404");
	        mappings.put("java.lang.Exception", "error_500");
	        mappings.put("com.springmvc.controller.ResourceNotFoundException", "error_404");
	        b.setExceptionMappings(mappings);
	        
	        Properties defaultex = new Properties();
	        defaultex.setProperty("defaultErrorView", "error_404");
	        
	        b.setDefaultErrorView("error_404");
	        
	        return b;
	    }
	 
*/	 

}
