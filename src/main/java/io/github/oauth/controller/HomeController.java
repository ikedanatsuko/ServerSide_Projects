package io.github.oauth.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private Twitter twitter;
	
	private ConnectionRepository connectionRepository;
	
	@Inject
	public HomeController(Twitter twitter, ConnectionRepository connectionRepository) {
		this.twitter = twitter;
		this.connectionRepository = connectionRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String login(Model model){
		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
			return "redirect:/connect/twitter";
		}	
		return "redirect:/home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model){
		model.addAttribute(twitter.userOperations().getUserProfile());
		List<Tweet> favorites = twitter.timelineOperations().getFavorites();
		model.addAttribute("favorites", favorites);
		return "home";
	}
}