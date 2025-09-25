package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Girl;
import com.example.demo.repo.UsersRepo;
import com.example.demo.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepo usersRepo;
	@Autowired
	private UsersService service;


    UsersController(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }
	
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@GetMapping("/rock")
	public String rock() {
		return "rock";
	}
	
	   @PostMapping("/save")
	    public ResponseEntity<Girl> createSchool(@RequestBody Girl user) {
	        return ResponseEntity.ok(service.saveSchool(user));
	    }
	   @GetMapping
	   public ResponseEntity<?> all(){
		   List<Girl> allSchools = service.getAllSchools();
		   return ResponseEntity.ok(allSchools);
	   }
	   
	   @PutMapping("/update/{id}")
	   public ResponseEntity<?>update(@PathVariable int id, @RequestBody Girl girl){
		   Girl updateSchool = service.updateSchool(id, girl);
		  return ResponseEntity.ok(updateSchool);
		   
	   }
	   
	   @DeleteMapping("/{id}")
	   public ResponseEntity<Void> delete(@PathVariable int id) {
	       service.deleteSchool(id);
	       return ResponseEntity.noContent().build(); // HTTP 204
	   }
	   
	   @GetMapping("/refresh")
	   public ResponseEntity<String> refreshCache() {
	       service.refreshAllGirls();
	       return ResponseEntity.ok("Girls cache refreshed successfully!");
	   }
//	   
//	   @GetMapping("/{name}")
//	   public ResponseEntity<Girl> getByName(@PathVariable String name) {
//	       Girl girl = service.getbyname(name);
//	       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(girl);
//	   }

	   @GetMapping("/{name}")
	   public ResponseEntity<Girl> getByName(@PathVariable String name) {
	       Girl girl = service.getbyname(name);  
	       return ResponseEntity.ok(girl);        
	   }

	   
	   

	   
	   



}


//public ResponseEntity<?> delete(@PathVariable int id){
//service.deleteSchool(id);
//return ResponseEntity.ok("delete"+ id);
//}
