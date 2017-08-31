package io.github.api.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.api.error.ResponseMessage;

@Component
public class AuthorizationFilter implements Filter {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		String token = httpRequest.getHeader("token");
		if (token == null) {
			errorResponse(httpResponse, messageSource.getMessage("token.notFound", null, null));
			return;
		}
		Set<String> keys = redisTemplate.keys("*:" + token);
		if (keys.isEmpty()) {
			errorResponse(httpResponse, messageSource.getMessage("token.unauthorized", null, null));
			return;
		}
		chain.doFilter(httpRequest, httpResponse);
	}
	
	public void errorResponse(HttpServletResponse response, String message) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		ResponseMessage responseMessage = new ResponseMessage(message, HttpStatus.BAD_REQUEST.toString());
		response.setStatus(HttpStatus.BAD_REQUEST.hashCode());
		String json = new ObjectMapper().writeValueAsString(responseMessage);
		response.getWriter().write(json);
		response.flushBuffer();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
}
