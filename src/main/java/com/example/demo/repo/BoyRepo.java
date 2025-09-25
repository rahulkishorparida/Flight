package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Boy;

public interface BoyRepo extends JpaRepository<Boy, Integer> {

}
