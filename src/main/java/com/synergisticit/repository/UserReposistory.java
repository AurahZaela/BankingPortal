package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.User;

public interface UserReposistory extends JpaRepository<User,Long>{
	User findByUsername(String username);
	
	@Query(value = "SELECT userId FROM user ORDER BY userId DESC LIMIT 1", nativeQuery = true)
    Long getNextSeriesId();
}
