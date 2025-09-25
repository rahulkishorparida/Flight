package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Girl;



public interface UsersService {
	
    Girl saveSchool( Girl school);
    List< Girl> getAllSchools();
    Girl getSchoolById(int id);
    Girl updateSchool(int id,  Girl girl);
    void deleteSchool(int id);
    
    void refreshAllGirls();
    Girl getbyname(String name);

}
