package com.hack.casesOrIfs.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hack.casesOrIfs.dto.FavoriteRequestDTO;
import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.entities.User;
import com.hack.casesOrIfs.entities.UserFavorite;
import com.hack.casesOrIfs.repositories.ContentRepositorie;
import com.hack.casesOrIfs.repositories.UserFavoriteRepositorie;
import com.hack.casesOrIfs.repositories.UserRepositorie;

@Service
public class UserService {
	
	@Autowired
	public UserFavoriteRepositorie favoriteRepositorie;
	
	@Autowired
	public UserRepositorie userRepositorie;
	
	@Autowired
	public ContentRepositorie contentRepositorie;
	
	public List<Content> getFavoritesByUserId(Long userId) {
		List<Content>  contents = new ArrayList<>();
		
		List<UserFavorite> favorites = favoriteRepositorie.findByUserId(userId);
		favorites.forEach(favorite -> contents.add(favorite.getContent()));
	
		return contents;
	}
	
	public void putFavorited(FavoriteRequestDTO favorite) {
		User user = userRepositorie.findById(favorite.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

		Content content = contentRepositorie.findById(favorite.getContentId())
                         .orElseThrow(() -> new RuntimeException("Content not found"));

		UserFavorite fav = new UserFavorite();
		fav.setUser(user);
		fav.setContent(content);
		fav.setFavoritedAt(new Date());

		favoriteRepositorie.save(fav);	
	}
}
