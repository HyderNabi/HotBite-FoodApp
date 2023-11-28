package com.clarivate.HotMeall.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clarivate.HotMeall.DTO.Branch;
import com.clarivate.HotMeall.DTO.Menu;
import com.clarivate.HotMeall.DTO.User;
import com.clarivate.HotMeall.Dao.MenuDao;
import com.clarivate.HotMeall.Dao.UserDao;
import com.clarivate.HotMeall.Response.ResponseStructure;


@Service
public class UserService {
	
	@Autowired
	UserDao userDao;

	@Autowired
	MenuDao menuDao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		
		if (user.getRole().equals("BranchManager")) {
			Menu menu = new Menu();
			menu.setUser(user);
			menu = menuDao.addMenu(menu);
			user.setMenu(menu);
		}
		
		structure.setError(false);
		structure.setMessage(user.getName() + " Added Successfully!");
		structure.setData(userDao.addUser(user));
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<User>> getUserById(int id){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.getUserById(id);
		if(optional.isEmpty()) {
			System.out.println("No such id is found");
		}
		else {
			structure.setError(false);
			structure.setMessage("User Found");
			structure.setData(optional.get());
		}
		
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> getAllStaffs(){
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
			structure.setError(false);
			structure.setMessage("User Found");
			structure.setData(userDao.getAllStaff());
		
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> getUsers(){
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
			structure.setError(false);
			structure.setMessage("User Found");
			structure.setData(userDao.getAllUsers());
		
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<User>> login(String email, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		User user = userDao.loginUser(email, password);
		if(user!=null) {
			structure.setError(false);
			structure.setMessage("Login Successful!");
			structure.setData(user);
		}else {
			structure.setError(true);
			structure.setMessage("Invalid Credentials!");
			structure.setData(user);
		}
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User user,int manager_id){	
		ResponseStructure<User> structure = new ResponseStructure<>();
		int existingUserId = user.getId();
		User manager = userDao.getUserById(manager_id).get();

		user.setId(existingUserId);
		user.setManager(manager);
		structure.setError(false);
		structure.setMessage("Staff Member Updated!");
		structure.setData(userDao.updateUser(user));
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.getUserById(id);
		if(optional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("No user with that id");
		}else {
			structure.setError(false);
			structure.setMessage("User Deleted");
			userDao.deleteUser(optional.get());
		}
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> saveStaff(User user, int managerId) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> manager = userDao.getUserById(managerId);
		if(manager == null) {
			structure.setError(true);
			structure.setMessage("Manager not found!");
		} else {
			user.setManager(manager.get());
			structure.setError(false);
			structure.setMessage("Staff Member Created Successfully!");
			structure.setData(userDao.addUser(user));
		}
				
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
	}
	
}
