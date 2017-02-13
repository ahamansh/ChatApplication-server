package com.chatapp.auth;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;

@Component
public class JwtAuthFilter implements Filter{


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("OncePerRequestFilter just supports HTTP requests");
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String origin = httpRequest.getHeader("Origin");
		//if(origin.contains("localhost:3000"))
			httpResponse.addHeader("Access-Control-Allow-Origin", origin);
		httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
		httpResponse.addHeader("Access-Control-Allow-Method", "GET,PUT,POST,DELETE");
		Enumeration<String> heafers = httpRequest.getHeaderNames();
		httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

		if(!CorsUtils.isPreFlightRequest(httpRequest)){

			Cookie[] cookies = httpRequest.getCookies();

			if(cookies != null && cookies.length > 0){

				for(Cookie cookie : cookies){  		
					if(cookie.getName().startsWith("SM_IDENTITY")){
						JwtAuthToken token = new JwtAuthToken(cookie.getValue());
						SecurityContextHolder.getContext().setAuthentication(token);
					}        		
				}       	

			}
			chain.doFilter(httpRequest, httpResponse);
			//chain.doFilter(request, response);
		}


	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	//	@Override
	//	protected void doFilterInternal(HttpServletRequest httpRequest,
	//			HttpServletResponse httpResponse, FilterChain filterChain)
	//			throws ServletException, IOException {
	//		// TODO Auto-generated method stub
	//		
	//		
	//		
	//		if(CorsUtils.isPreFlightRequest(httpRequest)){
	//			String origin = httpRequest.getHeader("Origin");
	//			httpResponse.addHeader("Access-Control-Allow-Origin", origin);
	//			httpResponse.addHeader("Access-Control-Allow-Credential", "true");
	//			httpResponse.addHeader("Access-Control-Allow-Method", "GET,PUT,POST,DELETE");
	//			httpResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	//			filterChain.doFilter(httpRequest, httpResponse);
	//		} 	
	//		
	//		
	//        String authorization = httpRequest.getHeader("Authorization");
	//        
	//        Cookie[] cookies = httpRequest.getCookies();
	//        
	//        if (authorization != null) {
	//            JwtAuthToken token = new JwtAuthToken(authorization.replaceAll("Bearer ", ""));
	//            SecurityContextHolder.getContext().setAuthentication(token);
	//        }
	//        filterChain.doFilter(httpRequest, httpResponse);
	//		
	//		
	//	}


}
