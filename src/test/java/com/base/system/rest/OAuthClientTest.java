package com.base.system.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {OAuthClientConfig.class})
public class OAuthClientTest {

	Logger logger = LoggerFactory.getLogger(OAuthClientTest.class);

    @Autowired
    private OAuth2RestOperations restTemplate;

	@Test
	public void getSample() {

	        final String result = restTemplate.getForObject("http://localhost:8080/base/sample2", String.class);
	        
	        logger.debug("getAccessToken : {} ",restTemplate.getAccessToken());
	        
	        logger.debug("result : {}",result);

//	        final String result2 = restTemplate.getForObject("http://localhost:8080/base/sample", String.class);
//	        
//	        logger.debug("getAccessToken2 : {} ",restTemplate.getAccessToken());
//	        
//	        logger.debug("result2 : {}",result2);

	        
		
	}
	
}
