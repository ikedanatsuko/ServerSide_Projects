package io.github.oauth.controller;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import groovy.lang.MetaClassImpl.Index;
import io.github.oauth.entity.DateTotal;
import io.github.oauth.entity.ItemOutput;
import io.github.oauth.form.TermForm;
import io.github.oauth.service.ItemOutputService;
import io.github.oauth.token.TokenManager;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private ItemOutputService itemOutputService;
	
	@Autowired
	private TokenManager tokenManager;
	
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
		
		return "redirect:/index";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(Model model, RedirectAttributes redirectAttributes) {
		
		List<DateTotal> allDateTotal = itemOutputService.getAllDateTotal();
		List<List<ItemOutput>> allCreatedItem = itemOutputService.getAllCreatedItem();
		List<List<ItemOutput>> allDeletedItem = itemOutputService.getAllDeletedItem();
		
		redirectAttributes.addFlashAttribute("dateTotals", allDateTotal);
		redirectAttributes.addFlashAttribute("createdItems", allCreatedItem);
		redirectAttributes.addFlashAttribute("deletedItems", allDeletedItem);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(Model model, RedirectAttributes redirectAttributes,
			@Validated TermForm termForm, BindingResult result) {
		
		if (result.hasErrors()) {
			return "redirect:/index";
		}
		
		List<DateTotal> dateTotals = itemOutputService.getDateTotalsByTerm(
				Date.valueOf(termForm.getBegin()), Date.valueOf(termForm.getEnd()));
		List<List<ItemOutput>> createdItems = itemOutputService.getCreatedItems(dateTotals);
		List<List<ItemOutput>> deletedItems = itemOutputService.getDeletedItems(dateTotals);
		
		redirectAttributes.addFlashAttribute("dateTotals", dateTotals);
		redirectAttributes.addFlashAttribute("createdItems", createdItems);
		redirectAttributes.addFlashAttribute("deletedItems", deletedItems);
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model, ModelMap modelMap){
		model.addAttribute("token", 
				tokenManager.getToken(twitter.userOperations().getScreenName()));
		model.addAttribute(twitter.userOperations().getUserProfile());
		model.addAttribute("termForm", new TermForm());
		
		if (modelMap.get("dateTotals") == null) {
			return "redirect:/index";
		}
		
		model.addAttribute(modelMap.get("dateTotals"));
		model.addAttribute(modelMap.get("createdItems"));
		model.addAttribute(modelMap.get("deletedItems"));
		
		return "home";
	}
}