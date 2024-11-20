package com.hack.casesOrIfs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.services.ContentService;
import com.hack.casesOrIfs.services.OpenAIService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController 
@RequestMapping("/periodicos-capes/v1/content")
public class ContentController {
	
	@Autowired
	public ContentService contentService;
	
	@Autowired
	public OpenAIService aiService;
	
	@GetMapping
	public ResponseEntity<List<Content>> getAllContents() {
		List<Content> contents = contentService.getAllContents();
		
		if (contents.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(contents);
	}
	
	@GetMapping("/futureJobs")
	public ResponseEntity<List<Content>> getAllFutureJobs() {
		List<Content> contents = contentService.getAllFutureJobs();
		
		if (contents.isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(contents);
	}
	
	@GetMapping("/{userId}/contentsOpenApi")
    public ResponseEntity<List<Content>> getContentsOpenApi(@PathVariable Long userId){
        List<Content> contents = aiService.getContentsOpenApi(userId);
        
        if (contents.isEmpty()) return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(contents);
    }
	
}
