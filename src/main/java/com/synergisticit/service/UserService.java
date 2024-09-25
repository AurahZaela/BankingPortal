package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {
		User save(User user);
	    User findById(Long userId);
	    User findByUserName(String username);
	    List<User> findAll();
	    void deleteByID(Long userId);
	    Boolean existsByID(Long userId);
	    
	    Long getNextUserId();
	    User findByIdWithJdbc(Long userId);
}
