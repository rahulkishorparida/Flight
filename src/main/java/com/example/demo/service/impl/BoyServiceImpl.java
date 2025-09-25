package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Boy;
import com.example.demo.repo.BoyRepo;
import com.example.demo.service.BoyService;
@Service
public class BoyServiceImpl implements BoyService {
	@Autowired
	private BoyRepo boyRepo;

	@Override

    // Save new boy + clear "allBoys" cache
    @CacheEvict(value = "boysCache", key = "'allBoys'")
	public Boy save(Boy boy) {
	return boyRepo.save(boy);
	}

	@Override
    @Cacheable(value = "boysCache", key = "'allBoys'")
	public List<Boy> getallBoy() {
		List<Boy> all = boyRepo.findAll();
		return all;
	}

	@Override
	 // Get one boy by ID (cached individually)
    @Cacheable(value = "boysCache", key = "#id")
	public Boy getbyid(int id) {
	return boyRepo.findById(id)
			.orElseThrow(()-> new RuntimeException("not found "+id));
	}

	@Override
    // Update specific boy and refresh cache for this boy
    @CachePut(value = "boysCache", key = "#id")
    @CacheEvict(value = "boysCache", key = "'allBoys'") // clear list
	public Boy UpdateBoy(int id, Boy boy) {
		 Boy bo = boyRepo.findById(id)
			.orElseThrow(()-> new RuntimeException("not found "+id));
		 bo.setName(boy.getName());
		 bo.setPhone(boy.getPhone());
		 bo.setEmail(boy.getEmail());
		return boyRepo.save(bo);
		 
	}
	//Using only @CachePut is fine if you don’t cache lists of all boys.
	//But if you cache both single objects and lists, you must evict the list after updating a single item to avoid stale data.

	@Override

    // Delete boy by id + clear cache
    @CacheEvict(value = "boysCache", allEntries = true)
	public void delete(int id) {
		if (!boyRepo.existsById(id)) {
			throw new RuntimeException("not found"+ id);
		}
	 boyRepo.deleteById(id);
		
	}
	
	

}
