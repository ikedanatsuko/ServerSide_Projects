package io.github.api.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.api.entity.Item;
import io.github.api.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Item> getAllItem() {
		List<Item> allItem = itemService.getAllItem();
		return allItem;
	}
	
	@RequestMapping(params = "title", method = RequestMethod.GET)
	public List<Item> getItemByTitle(@RequestParam("title") String title) {
		List<Item> result = itemService.getItemsByTitle(title);
		return result;
	}
	
	@RequestMapping(params = "price", method = RequestMethod.GET)
	public List<Item> getItemByPrice(@RequestParam("price") int price) {
		List<Item> result = itemService.getItemsByPrice(price);
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createItem(@Valid @RequestBody Item item, BindingResult result,
			UriComponentsBuilder uriBuilder) {
		if (result.hasErrors()) {
			// Error list
			Map<String, Object> errors = new HashMap<>();
			int i = 1;
			for (ObjectError error : result.getAllErrors()) {
				errors.put("error" + (i++), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
		} else {
			itemService.createItem(item);
			URI location = uriBuilder.path("api/items/{id}").buildAndExpand(item.getId()).toUri();
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(location);
			return new ResponseEntity<Item>(item, headers, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Item> deleteAllItem() {
		itemService.deleteAllItem();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
