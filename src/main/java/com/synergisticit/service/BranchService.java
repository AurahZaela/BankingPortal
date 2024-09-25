package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Branch;

public interface BranchService {
	Branch save(Branch branch);
	Branch updateBranch(Branch branch);
	Branch findById(long branchId);
	List<Branch> findAll();
	void deleteById(long branchId);
	Long getNextBranchId();
	
	boolean existById(Long branchId);
}
