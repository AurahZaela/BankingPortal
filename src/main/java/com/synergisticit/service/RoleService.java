package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService{
	Role save(Role role);
    Role findByID(Long roleID);
    List<Role> findAll();
    void deleteByID(Long roleID);
    Long getNextRoleId();
    
    boolean existById(Long roleId);
}
