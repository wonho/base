package com.base.system.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan(
		basePackages="com.base.business",
		useDefaultFilters=false,
		includeFilters={
				        @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class)
		}
 )
@EnableWebMvc
@EnableScheduling
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
	    TilesViewResolver viewResolver = new TilesViewResolver();
	    registry.viewResolver(viewResolver);		
	}
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		internalResourceViewResolver.setOrder(0);
		
		return internalResourceViewResolver;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer(){
	    TilesConfigurer tilesConfigurer = new TilesConfigurer();
	    tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/views/**/tiles.xml"});
	    tilesConfigurer.setCheckRefresh(true);
	    return tilesConfigurer;
    }
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	   registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//	}
	
	@Bean
	public MappingJackson2HttpMessageConverter jsonConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		jsonConverter.setSupportedMediaTypes(supportedMediaTypes);
		ObjectMapper objectMapper = new ObjectMapper();
		jsonConverter.setObjectMapper(objectMapper);		
		return jsonConverter;
	}
	
	/**
	 * JAXB XML Conveter
	 */
//	@Bean
//	public Jaxb2RootElementHttpMessageConverter xmlConverter() {
//		Jaxb2RootElementHttpMessageConverter  xmlConverter = new Jaxb2RootElementHttpMessageConverter();
//		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//		supportedMediaTypes.add(MediaType.APPLICATION_XML);		
//		xmlConverter.setSupportedMediaTypes(supportedMediaTypes);
//		return xmlConverter;
//	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		super.configureContentNegotiation(configurer);
	}

	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		converters.add(xmlConverter());
		converters.add(jsonConverter());
		super.configureMessageConverters(converters);
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		LoggerInceptor loggerInceptor = new LoggerInceptor();
//		registry.addInterceptor(loggerInceptor);
//	}
}
