package com.hack.casesOrIfs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hack.casesOrIfs.entities.Content;

public interface ContentRepositorie extends JpaRepository<Content, Long> {}
