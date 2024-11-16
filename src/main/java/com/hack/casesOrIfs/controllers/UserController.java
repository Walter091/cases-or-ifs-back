package com.hack.casesOrIfs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hack.casesOrIfs.dto.FavoriteRequestDTO;
import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.services.UserService;

@RestController 
@RequestMapping("/periodicos-capes/v1/user")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/{userId}/favorites")
	public ResponseEntity<List<Content>> getFavoritesByUserId(@PathVariable Long userId, @RequestParam(required = false) String filter) {    
	    List<Content> contents;
	    
	    if (filter != null && !filter.isBlank()) {
	        contents = userService.getFavoritesByUserId(userId, filter);
	    } else {
	        contents = userService.getFavoritesByUserId(userId);
	    }
	    
	    return contents.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(contents);
	}
	
	@PutMapping("/favorites")
	public ResponseEntity<Void> putFavorited(@RequestBody FavoriteRequestDTO favoriteRequest) {
		userService.putFavorited(favoriteRequest);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/favorites/{userId}/{contentId}")
	public ResponseEntity<Void> removeFavorite(@PathVariable Long userId, @PathVariable Long contentId) {
	    try {      
	        boolean removed = userService.removeFavorite(userId, contentId);
	        
	        if (!removed) {            
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }  

	        return ResponseEntity.ok().build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}
}
