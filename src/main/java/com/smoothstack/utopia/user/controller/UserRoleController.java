package com.smoothstack.utopia.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.utopia.user.entity.User;
import com.smoothstack.utopia.user.entity.UserRole;
import com.smoothstack.utopia.user.exception.*;
import com.smoothstack.utopia.user.service.UserRoleService;

@RestController
@RequestMapping(path = "/role")
public class UserRoleController {

	@Autowired
	private UserRoleService service;
	
	public UserRoleController(UserRoleService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping("/add")
	public ResponseEntity<UserRole> addUserRole(@RequestBody UserRole userRole) {
		
		service.save(userRole);
		return ResponseEntity.ok(userRole);
	}
	
	//Read
	@GetMapping
	public ResponseEntity<List<UserRole>> readAllUserRoles() {
		
		List<UserRole> userRoles = service.readAll();
		if(userRoles.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(userRoles);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserRole> readUserRoleById(@PathVariable int id) {
		
		UserRole userRole = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(userRole);
	}
	
	//Update
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUserRole(@PathVariable int id, @RequestBody UserRole userRole) {
		
		//Check if path id = user id
		if(id != userRole.getId())
			throw new IdMismatchException();
		//Check if record to update exists
		UserRole temp = service.readById(id).orElseThrow(IdNotFoundException::new);
		service.save(userRole);
		return ResponseEntity.ok("Role saved successfully");
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUserRole(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
