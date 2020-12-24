package com.nessma.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nessma.java.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);

}
