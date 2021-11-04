package com.smoothstack.utopia.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia.user.dao.UserRoleDAO;
import com.smoothstack.utopia.user.entity.UserRole;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleDAO dao;
	
	public UserRoleService(UserRoleDAO dao) {
		this.dao = dao;
	}
	
	public List<UserRole> readAll() {
		
		List<UserRole> userRoles = new ArrayList<UserRole>();
		dao.findAll().forEach(userRole -> userRoles.add(userRole));
		return userRoles;
	}
	
	public Optional<UserRole> readById(int id) {
		
		return dao.findById(id);
	}
	
	public void save(UserRole userRole) {
		
		dao.save(userRole);
	}
	
	public void delete(int id) {
		
		dao.findById(id).ifPresent(dao::delete);
	}
}
