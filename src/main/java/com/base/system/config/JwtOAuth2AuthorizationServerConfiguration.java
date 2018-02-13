package com.base.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/*DispatcherServlet Mapping *.do로 해서 oauth/token을 못찾아서 캐 삽질함*/
@Configuration
//@RequiredArgsConstructor
@EnableAuthorizationServer
public class JwtOAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
		/* Spring 4.3 이상에서는 자동으로 autwired 된다는데 안됨 나중에 확인 */ 
		@Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
		UserDetailsService userDetailsService;

	    @Value("${resource.id:spring-boot-application}")
	    private String resourceId;

	    @Value("${access_token.validity_period:3600}")
	    private int accessTokenValiditySeconds = 3600;

	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
	      throws Exception {

	      endpoints.accessTokenConverter(jwtAccessTokenConverter())
	        .userDetailsService(userDetailsService)
	        .authenticationManager(this.authenticationManager);
	    }
	    
//	    @Override
//	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//	    	security.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')").checkTokenAccess(
//	                "hasAuthority('ROLE_TRUSTED_CLIENT')");
//	    }
	    
	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory()
	        .withClient("bar")
	        .authorizedGrantTypes("password")
	        .authorities("ROLE_USER")
	        .scopes("read", "write")
	        .resourceIds(resourceId)
	        .accessTokenValiditySeconds(accessTokenValiditySeconds)
	        .secret("foo")
	        .and()
	        .withClient("melong")
            .authorizedGrantTypes("client_credentials", "password")
            .authorities("ROLE_USER")
            .scopes("read")
            .resourceIds(resourceId)
            .accessTokenValiditySeconds(accessTokenValiditySeconds)
            .secret("secret");
	    }

	    /* http://wonwoo.ml/index.php/post/980  참고*/
	    /* 기본적인 서명을 통한인증 */
	    @Bean
	    public JwtAccessTokenConverter jwtAccessTokenConverter() {
	    	return new JwtAccessTokenConverter();	
	    }
}
