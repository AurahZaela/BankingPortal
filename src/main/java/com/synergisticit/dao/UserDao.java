package com.synergisticit.dao;

import com.synergisticit.domain.User;

public interface UserDao {
	User findById(Long userId);
}
