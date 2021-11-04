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
import com.smoothstack.utopia.user.exception.*;
import com.smoothstack.utopia.user.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService service;
	
	public UserController(UserService service) {
		
		this.service = service;
	}
	
	//Create
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		
		service.save(user);
		return ResponseEntity.ok(user);
	}
	
	//Read
	@GetMapping
	public ResponseEntity<List<User>> readAllUsers() {
		
		List<User> users = service.readAll();
		if(users.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> readFlightById(@PathVariable int id) {
		
		User user = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok(user);
	}
	
	//Update
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
		
		if(id != user.getId())
			throw new IdMismatchException();
		service.save(user);
		return ResponseEntity.ok("User saved successfully");
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
