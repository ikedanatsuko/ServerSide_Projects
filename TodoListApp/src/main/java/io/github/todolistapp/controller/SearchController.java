package io.github.todolistapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import io.github.todolistapp.entity.Todo;
import io.github.todolistapp.service.TodoService;

@Controller
public class SearchController {
	
	@Autowired
	private TodoService todoService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {

		return "search";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String getResult(Model model, @RequestParam("searchWord") String searchWord) {
		List<Todo> todosByWord = todoService.getTodosByWord(searchWord);
		model.addAttribute("todosByWord", todosByWord);
		
		return "search";
	}
}
