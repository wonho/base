package com.base.system.rest;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
public class OAuthClientConfig {

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {

	    ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
	    resource.setUsername("melong");
	    resource.setPassword("1234");
	    resource.setAccessTokenUri("http://localhost:8080/base/oauth/token");
	    resource.setClientId("bar");
	    resource.setClientSecret("foo");
	    resource.setGrantType("password");
	    resource.setScope(Arrays.asList("read", "write"));
//	    resource.setClientAuthenticationScheme(AuthenticationScheme.form);

	    return resource;
	}

//	@Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//    	return new JwtAccessTokenConverter();	
//    }

	@Bean
	 public OAuth2RestOperations restTemplate() {
//        restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
//	    return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
	    return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext());
	 }
}
