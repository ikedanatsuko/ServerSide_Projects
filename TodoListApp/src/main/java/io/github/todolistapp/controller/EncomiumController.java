package io.github.todolistapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.todolistapp.entity.Encomium;
import io.github.todolistapp.service.EncomiumService;

@Controller
public class EncomiumController {
	
	@Autowired
	private EncomiumService encomiumService;
	
	@RequestMapping(value = "/encomium")
	public String encomium(ModelMap model) {
		String key = BindingResult.MODEL_KEY_PREFIX + "encomium";
		if (model.containsKey("result")) {
			model.addAttribute(key, model.get("result"));
		}
		
		List<Encomium> allEncomium = encomiumService.getAllEncomium();
		model.addAttribute("allEncomium", allEncomium);
		
		return "encomium";
	}
	
	@RequestMapping(value = "/encomium/create", method = RequestMethod.POST)
	public String createEncomium(Model model, @Valid @ModelAttribute Encomium encomium, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("result", result);
		} else {
			encomiumService.addEncomium(encomium);
		}
		
		return "redirect:/encomium";
	}
}
