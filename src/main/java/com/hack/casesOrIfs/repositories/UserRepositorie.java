package com.hack.casesOrIfs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hack.casesOrIfs.entities.User;

public interface UserRepositorie extends JpaRepository<User, Long> {

}
