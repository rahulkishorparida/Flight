package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NameNotFound;
import com.example.demo.model.Girl;
import com.example.demo.repo.UsersRepo;
import com.example.demo.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
	private UsersRepo repo;
    @Autowired
    private CacheManager cacheManager;
	
    @Override
    public Girl saveSchool(Girl school) {
        return repo.save(school);
    }

	@Override
	   @Cacheable(value = "girlsCache", key = "'allGirls'")
	public List<Girl> getAllSchools() {
		return repo.findAll();
	}

	@Override
	@Cacheable(value = "girlsCache", key = "#id")
	public Girl getSchoolById(int id) {
	    return repo.findById(id)
	               .orElseThrow(() -> new RuntimeException("Girl not found with id " + id));
	}

	@Override
	  @CachePut(value = "girlsCache", key = "#id")
	public Girl updateSchool(int id, Girl girlDetails) {
	   
	    Girl existingGirl = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Girl not found with id " + id));

	    existingGirl.setName(girlDetails.getName());
	    existingGirl.setPhone(girlDetails.getPhone());
	    existingGirl.setEmail(girlDetails.getEmail());
	 
	    return repo.save(existingGirl);
	}

	@Override
    @CacheEvict(value = "girlsCache", key = "#id")
	public void deleteSchool(int id) {
	    if (!repo.existsById(id)) {
	        throw new RuntimeException("Girl not found with id " + id);
	    }
	    repo.deleteById(id);
	}

    // Optional: clear whole cache after bulk update
    @CacheEvict(value = "girlsCache", allEntries = true)
    public void refreshAllGirls() {
        List<Girl> girls = repo.findAll();
        // Warm up the "allGirls" cache key
        cacheManager.getCache("girlsCache").put("allGirls", girls);
        //Bulk refreshes where you need to push a known value into cache proactively.
    }

	@Override
//	@Cacheable(value = "girlsCache", key = "#name")
	@Cacheable(value = "girlsCache", key = "#name", unless="#result == null")
	public Girl getbyname(String name) {
	 return repo.findByName(name)
	.orElseThrow(()-> new NameNotFound("not found"+ name));
	
	}
//Without unless="#result == null", null would also be cached
}
