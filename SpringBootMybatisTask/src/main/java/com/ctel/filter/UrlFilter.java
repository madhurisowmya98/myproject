package com.ctel.filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UrlFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return !path.startsWith("/login");
    }
	
	/*
	 * @SuppressWarnings("rawtypes")
	 * 
	 * @Bean public FilterRegistrationBean someFilterRegistration() {
	 * 
	 * FilterRegistrationBean registration = new FilterRegistrationBean();
	 * //registration.setFilter(someFilter());
	 * registration.addUrlPatterns("/insert/batchinsert/findAll");
	 * //registration.addInitParameter("paramName", "paramValue");
	 * //registration.setName("someFilter"); return registration; }
	 */

	/*
	 * @Bean(name = "UrlFilter") public Filter someFilter() { return new
	 * UrlFilter(); }
	 */
}
