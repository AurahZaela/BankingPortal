package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{

	@Query(value = "SELECT roleId FROM role ORDER BY roleId DESC LIMIT 1", nativeQuery = true)
    Long getNextSeriesId();
}
