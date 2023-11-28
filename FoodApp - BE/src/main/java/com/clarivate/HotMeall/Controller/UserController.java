package com.clarivate.HotMeall.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clarivate.HotMeall.DTO.User;
import com.clarivate.HotMeall.Response.ResponseStructure;
import com.clarivate.HotMeall.Service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
		return userService.getUsers();
	}

	@GetMapping("/getallstaffs")
	public ResponseEntity<ResponseStructure<List<User>>> getAllStaffs() {
		return userService.getAllStaffs();
	}

	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping("/saveStaff/{managerId}")
	public ResponseEntity<ResponseStructure<User>> saveUserAsStaff(@RequestBody User user,@PathVariable int managerId) {
		return userService.saveStaff(user,managerId);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable int userId) {
		return userService.getUserById(userId);
	}
  
	@PutMapping("/user/{managerId}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user,@PathVariable int managerId) {
		return userService.updateUser(user,managerId);
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int userId) {
		return userService.deleteUser(userId);
	}

	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<User>> login(@RequestBody User user) {
		return userService.login(user.getEmail(),user.getPassword());
	}

}
