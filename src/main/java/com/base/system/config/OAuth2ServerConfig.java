package com.base.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
public class OAuth2ServerConfig {
	 @Configuration
	 @EnableResourceServer
	 protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		 /* token을 db에 저장할 경우 */
		 // http://kimseunghyun76.tistory.com/404?category=583716
//		 @Autowired
//		 private TokenStore tokenStore;
		 
		 @Value("${resource.id:spring-boot-application}")
		 private String resourceId;
	
		 @Override
		 public void configure(ResourceServerSecurityConfigurer resources) {
		    resources.resourceId(resourceId);
//		    resources.tokenStore(tokenStore);
		 }
	
		 @Override
		 public void configure(HttpSecurity http) throws Exception {
		      http.authorizeRequests()
		        .antMatchers("/sample/**").hasRole("USER");
		 }	
	 }
}
