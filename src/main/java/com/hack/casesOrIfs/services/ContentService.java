package com.hack.casesOrIfs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.repositories.ContentRepositorie;

@Service
public class ContentService {
	
	@Autowired
	public ContentRepositorie contentRepositorie;
	
	public List<Content> getAllContents() {
		return contentRepositorie.findAll();
	}
}
