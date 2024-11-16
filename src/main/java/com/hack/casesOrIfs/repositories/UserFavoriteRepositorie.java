package com.hack.casesOrIfs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.entities.UserFavorite;

public interface UserFavoriteRepositorie extends JpaRepository<UserFavorite, Long> {
	List<UserFavorite> findByUserId(Long userId);
	
	@Query(value="SELECT c.* FROM user_favorite uf JOIN content c on (c.id = uf.content_id) where uf.user_id = ?1 and c.name ILIKE CONCAT('%', ?2, '%')",
			nativeQuery = true)
	List<Content> findByUserIdWithFilter(long userId, String textFilter);
}
