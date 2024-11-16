package com.hack.casesOrIfs.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	public List<Content> getFavoritesByUserId(Long userId, String textFilter) {
		if (textFilter != null && !textFilter.isBlank()) {
			return favoriteRepositorie.findByUserIdWithFilter(userId, textFilter);
		}
		
		return getFavoritesByUserId(userId);
	}

	public List<Content> getFavoritesByUserId(Long userId) {
	    return favoriteRepositorie.findByUserId(userId).stream()
	    		.map(UserFavorite::getContent)
	    		.collect(Collectors.toUnmodifiableList());
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
	
	public boolean removeFavorite(Long userId, Long contentId) {
        try {
            Optional<UserFavorite> favorite = favoriteRepositorie.findByUserIdAndContentId(userId, contentId);
            
            if (!favorite.isPresent()) {
                return false;
            }

            favoriteRepositorie.delete(favorite.get());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
