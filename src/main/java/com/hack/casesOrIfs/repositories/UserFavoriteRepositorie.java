package com.hack.casesOrIfs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hack.casesOrIfs.entities.Content;
import com.hack.casesOrIfs.entities.UserFavorite;

public interface UserFavoriteRepositorie extends JpaRepository<UserFavorite, Long> {
	List<UserFavorite> findByUserId(Long userId);
	
	@Query("""
		    SELECT c
		    FROM Content c
		    JOIN UserFavorite uf ON c.id = uf.content.id
		    WHERE uf.user.id = :userId
		      AND LOWER(c.name) LIKE LOWER(CONCAT('%', :textFilter, '%'))
		""")
	List<Content> findByUserIdWithFilter(@Param("userId") Long userId, @Param("textFilter") String textFilter);
	
	Optional<UserFavorite> findByUserIdAndContentId (Long userId, Long contentId);
}
