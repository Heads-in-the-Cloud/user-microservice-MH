package com.smoothstack.utopia.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia.user.dao.UserDAO;
import com.smoothstack.utopia.user.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDAO dao;
	
	public UserService(UserDAO dao) {
		this.dao = dao;
	}
	
	public List<User> readAll() {
		
		List<User> users = new ArrayList<User>();
		dao.findAll().forEach(user -> users.add(user));
		return users;
	}
	
	public Optional<User> readById(int id) {
		
		return dao.findById(id);
	}
	
	public void save(User user) {
		
		dao.save(user);
	}
	
	public void delete(int id) {
		
		dao.findById(id).ifPresent(dao::delete);
	}
}
