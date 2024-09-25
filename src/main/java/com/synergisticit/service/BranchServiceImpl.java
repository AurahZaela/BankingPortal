package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Branch;
import com.synergisticit.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService{

	
	@Autowired
	BranchRepository branchRepository;
	
	@Override
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public Branch updateBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public Branch findById(long branchId) {
		Optional<Branch> optBranch = branchRepository.findById(branchId);
		
		if(optBranch.isPresent()) {
			return optBranch.get();
		}
		return null;
	}

	@Override
	public List<Branch> findAll() {
		return branchRepository.findAll();
	}

	@Override
	public void deleteById(long branchId) {
		branchRepository.deleteById(branchId);
		
	}

	@Override
	public Long getNextBranchId() {
		return branchRepository.getNextSeriesId();
	}

	@Override
	public boolean existById(Long branchId) {
		return branchRepository.existsById(branchId);
	}

}
