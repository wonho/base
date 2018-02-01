package com.base.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import com.base.system.util.MessageUtil.MessageUtil;

@Configuration
@ComponentScan(basePackages={"com.base.business"}, 
               excludeFilters=@ComponentScan.Filter(type=FilterType.ANNOTATION,
               value=Controller.class))
@PropertySource("classpath:properties/${spring.profiles.active:dev}.app.properties")
@EnableAspectJAutoProxy
public class CommonConfig {
	
	@Autowired
    Environment env;
		
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	 
	@Bean
	public MessageSource messageSource() {		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("classpath:messages/messages");
		
		messageSource.setBasename("WEB-INF/messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		
		messageSource.setUseCodeAsDefaultMessage(true);
		// default : true -> message_ko_KR.properties 와 message.prorperties 
//		messageSource.setFallbackToSystemLocale(false);
//		messageSource.setCacheSeconds(30);
		
		return messageSource;
	}
	
	@Bean
	public MessageUtil messageUtil() {
		MessageUtil messageUtil = new MessageUtil();
		messageUtil.setMessageSource(messageSource());		
		return messageUtil;
	}
}
