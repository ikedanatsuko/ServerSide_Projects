package io.github.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.api.entity.Item;
import io.github.api.service.ItemService;

@RestController
@RequestMapping("/api/items/{id}")
public class SingleItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method = RequestMethod.GET) 
	public Item getItem(@PathVariable("id") int id) {
		Item item = itemService.getItemById(id);
		return item;
	}
	
	@RequestMapping(value = "/title", method = RequestMethod.GET)
	public String getTitle(@PathVariable("id") int id) {
		return itemService.getTitleById(id);
	}
	
	@RequestMapping(value = "/note", method = RequestMethod.GET)
	public String getNote(@PathVariable("id") int id) {
		return itemService.getNoteById(id);
	}
	
	@RequestMapping(value = "/price", method = RequestMethod.GET)
	public int getPrice(@PathVariable("id") int id) {
		return itemService.getPriceById(id);
	}
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public byte[] getImage(@PathVariable("id") int id) {
		return itemService.getImageById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Item> updateItem(@PathVariable("id") int id, @RequestBody Item item) {
		item.setId(id);
		Item currentItem = itemService.updateItem(item);
		return new ResponseEntity<>(currentItem, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Item> deleteItem(@PathVariable("id") int id) {
		itemService.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
