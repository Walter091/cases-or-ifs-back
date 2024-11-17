package com.hack.casesOrIfs.services;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.hack.casesOrIfs.entities.Content;


@Service
public class OpenAIService {
	
	@Value("${openai.api.key}")
    private String apiKey;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public ContentService contentService;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public List<Content> getContentsOpenApi(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        
        JSONArray messages = new JSONArray();
        
        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "user");
        systemMessage.put("content", "Você é um sistema de recomendação de conteúdos. Baseie suas respostas nos dados fornecidos.");
        messages.put(systemMessage);
        
        List<Content> contentsFavorites = userService.getFavoritesByUserId(userId);
        List<Content> contents = contentService.getAllContents();
        
        for (Content content : contents) {
            JSONObject contentMessage = new JSONObject();
            contentMessage.put("role", "user");
            contentMessage.put("content", "id: " + content.getId() + ", resumo: " + content.getResumen());
            messages.put(contentMessage);
        }
        
        JSONObject profileMessage = new JSONObject();
        profileMessage.put("role", "user");
        profileMessage.put("content", String.format(
            "Perfil do usuário:\n- Idade: %d\n- Interesses: %s\n",
            20,
            contentsFavorites.stream().map(Content::getName).collect(Collectors.joining(", "))  
        ));
        messages.put(profileMessage);
        
        JSONObject recommendationRequest = new JSONObject();
        recommendationRequest.put("role", "user");
        recommendationRequest.put("content", "Sugira os 3 conteúdos mais relevantes para este usuário, me informe apenas o id de cada contedudo sepado por vigurla, exemplo '1, 2, 3'.");
        messages.put(recommendationRequest);

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-4o-mini");
        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 300);
        requestBody.put("temperature", 0.2);
        requestBody.put("top_p", 1);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);

        System.out.println(response.getBody());
        response.getBody();
        
        return null;
    }
}
