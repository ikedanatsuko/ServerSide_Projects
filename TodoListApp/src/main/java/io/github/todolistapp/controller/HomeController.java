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

import io.github.todolistapp.entity.TodoList;
import io.github.todolistapp.service.TodoListService;

@Controller
public class HomeController {

	@Autowired
	private TodoListService todoListService;

	@RequestMapping(value = { "/" })
	public String index(ModelMap model) {
		String key = BindingResult.MODEL_KEY_PREFIX + "todoList";
		if (model.containsKey("result")) {
            model.addAttribute(key, model.get("result"));
        }
		
		List<TodoList> allTodolist = todoListService.getAllTodolist();
		model.addAttribute("allTodolist", allTodolist);

		return "index";
	}

	@RequestMapping(value = "/todolist/create", method = RequestMethod.POST)
	public String createTodolist(Model model, @Valid @ModelAttribute TodoList todoList,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("result", result);
		} else {
			todoListService.addTodolist(todoList);
		}

		return "redirect:/";
	}

}
