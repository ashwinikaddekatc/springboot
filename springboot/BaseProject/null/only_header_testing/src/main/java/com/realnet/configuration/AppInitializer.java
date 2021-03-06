package com.realnet.configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;




public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	  @Override
	    protected Class<?>[] getRootConfigClasses() {
	        return new Class[] { AppConfig.class };
	    }
	  
	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return null;
	    }
	  
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/" };
	    }
	    
	    @Override
	    protected Filter[] getServletFilters() {
	    	Filter [] singleton = { new CORSFilter() };
	    	return singleton;
		}

}
