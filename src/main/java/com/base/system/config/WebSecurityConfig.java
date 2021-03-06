package com.base.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.base.system.security.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("user").password("1111").roles("USER").and()
//				.withUser("admin").password("2222").roles("USER", "ADMIN");
//	}
	
	/* AuthenticationManager가 자동으로 Autowired 않되어서 넣음 */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	   return super.authenticationManagerBean();
	}

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("static/**");
	}
	/**
	 * 직접 userDetailService를 호출하지 않고 AuthenticationProvider를 연결 가능(DaoAuthenticationProvider)
	 *  authProvider.setUserDetailsService,authProvider.setPasswordEncoder 
	 *  ex) auth.authenticationProvider(authenticationProvider)
	 */
	@Autowired
	UserDetailsService userService;
	
	@Override
	protected UserDetailsService userDetailsService() {
//		return new UserService();
		return userService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(shaPasswordEncoder());
//		auth.authenticationProvider(authenticationProvider());
	}
	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//	    DaoAuthenticationProvider authProvider
//	      = new DaoAuthenticationProvider();
//	    authProvider.setUserDetailsService(userDetailsService());
//	    authProvider.setPasswordEncoder(shaPasswordEncoder());
//	    return authProvider;
//	}
	
	public ShaPasswordEncoder shaPasswordEncoder() {
		return new ShaPasswordEncoder(256);		
	}	
	
	@Bean
	public PasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder(11);		
	}	
	
//	@Override
//	public UserDetailsService userDetailsServiceBean() throws Exception {
//		return new UserService();
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(new UserService());
//	}

	@Bean
	public AuthenticationSuccessHandler getHandler() {
		AuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
		return handler;
	}

//	@Bean
//	public AuthenticationSuccessHandler getHandler() {
//		AuthenticationSuccessHandler handler = new LoginSucessHandler();
//		return handler;
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers().frameOptions().disable()
			.and()
		    .authorizeRequests()
		    .antMatchers("/").permitAll()
		    .antMatchers("/console/**").permitAll() //h2 database
		    .antMatchers("/**").hasRole("USER")
		    .anyRequest().authenticated()
			.and()
		    .formLogin()
		        .loginPage("/login.do")
		        .permitAll()
		        .loginProcessingUrl("/loginProcess.do")
		        .usernameParameter("username")
		        .successHandler(getHandler())
		        .passwordParameter("password")
//		        .defaultSuccessUrl("/main.do")
		        .failureUrl("/login.do")
		    .and()
		    .logout()
		    .and()
		    .csrf().disable();
//		    .httpBasic().disable();
		
	}
}
