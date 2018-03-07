package io.github.oauth.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import io.github.oauth.config.PropsConfig;
import io.github.oauth.token.TokenManager;

@Controller
@RequestMapping("/connect")
public class TwitterConnectController extends ConnectController {
	
	@Autowired
	private PropsConfig propsConfig;
	
	@Autowired
	private TokenManager tokenManager;
	
	private Twitter twitter;
	
	@PostConstruct
	public void Init() {
		setApplicationUrl(propsConfig.getUrl());
	}
	
	@Inject
	public TwitterConnectController(Twitter twitter, ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
		this.twitter = twitter;
	}
	
	@Override
	protected String connectedView(String provideId) {
		if (twitter.isAuthorized()) {
			 String token = tokenManager.setToken(
					 twitter.userOperations().getUserProfile().getScreenName());
		}
		return "redirect:/";
	}
	
	@Override
	@RequestMapping(value="/{providerId}", method=RequestMethod.DELETE)
	public RedirectView removeConnections(@PathVariable String providerId, NativeWebRequest request) {
		tokenManager.destroyToken(twitter.userOperations().getUserProfile().getScreenName());
		
		return super.removeConnections(providerId, request);
	}
}
