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
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		
		service.save(user);
		return ResponseEntity.ok(user);
	}
	
	//Read
	@GetMapping("/all")
	public ResponseEntity<List<User>> readAllUsers() {
		
		List<User> users = service.readAll();
		if(users.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> readUsersById(@PathVariable int id) {
		
		User user = service.readById(id).orElseThrow(IdNotFoundException::new);
		return ResponseEntity.ok().body(user);
	}
	
	//Update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody User user) {
		
		//Check if path id = user id
		if(id != user.getId())
			throw new IdMismatchException();
		//Check if the record to update exists
		User temp = service.readById(id).orElseThrow(IdNotFoundException::new);
		service.save(user);
		return ResponseEntity.ok("User saved successfully");
	}
	
	//Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
