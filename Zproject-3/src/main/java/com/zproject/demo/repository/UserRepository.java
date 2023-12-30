package com.zproject.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zproject.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	public Optional<User> findByEmail(String email);

}
