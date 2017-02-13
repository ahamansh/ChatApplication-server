package com.chatapp.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


public class CopyOfJwtAuthFilter extends OncePerRequestFilter {
   

/*    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	
    	if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if(CorsUtils.isPreFlightRequest(httpRequest)){
			String origin = httpRequest.getHeader("Origin");
			httpResponse.addHeader("Access-Control-Allow-Origin", origin);
			httpResponse.addHeader("Access-Control-Allow-Credential", "true");
			httpResponse.addHeader("Access-Control-Allow-Method", "GET,PUT,POST,DELETE");
			httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			chain.doFilter(request, response);
		} 	
    	
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String authorization = servletRequest.getHeader("Authorization");
        
        Cookie[] cookies = servletRequest.getCookies();
        
        if (authorization != null) {
            JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        chain.doFilter(request, response);
    }*/

	@Override
	protected void doFilterInternal(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		if(CorsUtils.isPreFlightRequest(httpRequest)){
			String origin = httpRequest.getHeader("Origin");
			httpResponse.addHeader("Access-Control-Allow-Origin", origin);
			httpResponse.addHeader("Access-Control-Allow-Credential", "true");
			httpResponse.addHeader("Access-Control-Allow-Method", "GET,PUT,POST,DELETE");
			httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			filterChain.doFilter(httpRequest, httpResponse);
		} 	
		
		
        String authorization = httpRequest.getHeader("Authorization");
        
        Cookie[] cookies = httpRequest.getCookies();
        
        if (authorization != null) {
            JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(httpRequest, httpResponse);
		
		
	}

   
}
