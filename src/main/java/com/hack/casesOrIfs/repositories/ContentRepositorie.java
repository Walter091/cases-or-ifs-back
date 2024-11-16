package com.hack.casesOrIfs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hack.casesOrIfs.entities.Content;

public interface ContentRepositorie extends JpaRepository<Content, Long> {
	
	@Query(value= "SELECT * FROM CONTENT where futureJob",nativeQuery = true)
	List<Content> findAllFutureJobs();
}
