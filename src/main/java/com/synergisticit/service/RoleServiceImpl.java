package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findByID(Long roleID) {
		  Optional<Role> optRole = roleRepository.findById(roleID);

	        return optRole.orElse(null);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public void deleteByID(Long roleID) {
		roleRepository.deleteById(roleID);
	}

	@Override
	public Long getNextRoleId() {
		return roleRepository.getNextSeriesId();
	}

	@Override
	public boolean existById(Long roleId) {
		return roleRepository.existsById(roleId);
	}

}
