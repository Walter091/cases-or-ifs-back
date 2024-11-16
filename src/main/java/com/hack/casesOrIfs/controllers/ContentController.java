package com.hack.casesOrIfs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.services.ContentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController 
@RequestMapping("/periodicos-capes/v1/content")
public class ContentController {
	
	@Autowired
	public ContentService contentService;
	
	@GetMapping
	public ResponseEntity<List<Content>> getAllContents() {
		List<Content> contents = contentService.getAllContents();
		
		if (contents.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(contents);
	}
}
