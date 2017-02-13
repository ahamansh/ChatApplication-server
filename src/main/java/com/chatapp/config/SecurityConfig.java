package com.chatapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import com.chatapp.auth.JwtAuthFilter;
import com.chatapp.auth.JwtAuthenticationEntryPoint;
import com.chatapp.auth.JwtAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	//	@Autowired
	//	private CORSFilter corsfilter;

	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthEndPoint;

	@Override
	public void configure(AuthenticationManagerBuilder auth)  throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().ignoringAntMatchers("/login");

		http.csrf().disable();

		http.authorizeRequests()
//		.requestMatchers(CorsUtils::isCorsRequest)
//		.permitAll()
		.antMatchers("/login")
		.permitAll()
		.antMatchers("/register")
		.permitAll()
//		.antMatchers("/logout")
//		.permitAll()
		.antMatchers("/**/*")
		.hasAuthority("ROLE_USER")
		.and()
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling()
		.authenticationEntryPoint(jwtAuthEndPoint);
	}
	
	@Bean
    public FilterRegistrationBean registration(JwtAuthFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }


}
