package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Boy;

import com.example.demo.service.BoyService;
@RestController
@RequestMapping("/boy")
public class BoyController {
	@Autowired
	private BoyService boyService;
	
	@GetMapping
	
	public ResponseEntity<?> getll(){
		List<Boy> getallBoy = boyService.getallBoy();
		return ResponseEntity.ok(getallBoy);
	}
	
	   @PostMapping("/save")
	    public ResponseEntity<Boy> createSchool(@RequestBody Boy boy) {
	        return ResponseEntity.ok(boyService.save(boy));
	    }
	   @PutMapping("/update/{id}")
	   public ResponseEntity<?>update(@PathVariable int id, @RequestBody Boy boy){
		   Boy updateSchool = boyService.UpdateBoy(id, boy);
		  return ResponseEntity.ok(updateSchool);  
	   }
	   @DeleteMapping("/{id}")
	   public ResponseEntity<Void> delete(@PathVariable int id) {
	       boyService.delete(id);
	       return ResponseEntity.noContent().build(); 
	   }

}
