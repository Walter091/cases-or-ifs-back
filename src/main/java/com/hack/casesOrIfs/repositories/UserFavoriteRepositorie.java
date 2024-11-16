package com.hack.casesOrIfs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hack.casesOrIfs.entities.UserFavorite;

public interface UserFavoriteRepositorie extends JpaRepository<UserFavorite, Long> {
	List<UserFavorite> findByUserId(Long userId);
}
