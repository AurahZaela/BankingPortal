package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.synergisticit.dao.UserDaoImpl;
import com.synergisticit.domain.User;
import com.synergisticit.repository.UserReposistory;

@Service
public class UserServiceImpl implements UserService{
	
	UserDaoImpl userDao;
	
	@Autowired
	UserReposistory userRepository;
	
	@Autowired 
	BCryptPasswordEncoder bCrypt;
	
	public UserServiceImpl(UserDaoImpl userDao){
		this.userDao = userDao;
	}
	
	@Override
	public User save(User user) {
		String encryptedPassword = bCrypt.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		return userRepository.save(user);
	}

	@Override
	public User findById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
	}


	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public void deleteByID(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public Boolean existsByID(Long userId) {
		return userRepository.existsById(userId);
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Long getNextUserId() {
		return userRepository.getNextSeriesId();
	}


	public User findByIdWithJdbc(Long userId) {
		return userDao.findById(userId);
	}
}
