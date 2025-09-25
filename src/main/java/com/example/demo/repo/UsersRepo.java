package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Girl;

public interface UsersRepo extends JpaRepository<Girl, Integer> {
	
//	Girl findByname(Girl girl);
	Optional<Girl> findByName(String name);
	

}
