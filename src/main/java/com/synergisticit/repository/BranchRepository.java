package com.synergisticit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Branch;

public interface BranchRepository extends JpaRepository<Branch,Long>{
	 @Query(value = "SELECT branchId FROM branch ORDER BY branchId DESC LIMIT 1", nativeQuery = true)
	    Long getNextSeriesId();
	 
	 
	 Page<Branch> findAll(Pageable pageable);
}
