package com.hack.casesOrIfs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hack.casesOrIfs.dto.FavoriteRequestDTO;
import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.services.UserService;

@RestController 
@RequestMapping("/periodicos-capes/v1/user")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Content>> getFavoritesByUserId(@PathVariable Long userId) {
		List<Content> contents = userService.getFavoritesByUserId(userId);
		
		if (contents.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(contents);
	}
	
	@PutMapping("/favorited")
	public ResponseEntity<Void> putFavorited(@RequestBody FavoriteRequestDTO favoriteRequest) {
		userService.putFavorited(favoriteRequest);
		return ResponseEntity.ok().build();
	}
}
