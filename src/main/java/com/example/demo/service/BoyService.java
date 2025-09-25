package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Boy;

public interface BoyService {
	
	Boy save(Boy boy);
	List<Boy> getallBoy();
	Boy getbyid(int id);
	Boy UpdateBoy(int id, Boy boy);
	void delete(int id);

}
