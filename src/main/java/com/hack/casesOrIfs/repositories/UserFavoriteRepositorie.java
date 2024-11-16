package com.hack.casesOrIfs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.entities.UserFavorite;

public interface UserFavoriteRepositorie extends JpaRepository<UserFavorite, Long> {
	List<UserFavorite> findByUserId(Long userId);
	
	@Query(value = "SELECT c.* " +
            "FROM user_favorite uf " +
            "JOIN content c ON c.id = uf.content_id " +
            "WHERE uf.user_id = ?1 " +
            "AND c.name ILIKE '%' || ?2 || '%'",
    nativeQuery = true)
	List<Content> findByUserIdWithFilter(Long userId, String textFilter);
	
	Optional<UserFavorite> findByUserIdAndContentId (Long userId, Long contentId);
}
