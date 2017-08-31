package io.github.oauth.token;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private static int TOKEN_LENGTH = 16;
	
	public String getToken(String userId) {
		Set<String> keys = redisTemplate.keys(userId + ":*");
		if (keys != null) {
			String[] strings = keys.toString().split(":");
			strings = strings[strings.length-1].split("]");
			
			return strings[strings.length-1];
		}	
		return null;
	}
	
	public String setToken(String userId) {
		String token = createToken();
		destroyToken(userId);
		redisTemplate.opsForValue().set(userId + ":" + token, "");
		
		return token;
	}
	
	public void destroyToken(String userId) {
		Set<String> keys = redisTemplate.keys(userId + ":*");
		if (keys != null) {
			redisTemplate.delete(keys);
		}
	}
	
	private String createToken() {
		byte token[] = new byte[TOKEN_LENGTH];
		StringBuffer buf = new StringBuffer();
		SecureRandom random = null;
		String tokenString = null;
		
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(token);
			
			for (int i = 0; i < token.length; i++) {
				buf.append(String.format("%02x", token[i]));
			}
			tokenString = buf.toString();
			
			return tokenString;
		} catch (NoSuchAlgorithmException e) {	
			return null;
		}
	}
}
