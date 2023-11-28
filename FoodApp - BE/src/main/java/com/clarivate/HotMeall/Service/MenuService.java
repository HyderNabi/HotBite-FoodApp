package com.clarivate.HotMeall.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clarivate.HotMeall.DTO.Menu;
import com.clarivate.HotMeall.DTO.User;
import com.clarivate.HotMeall.Dao.MenuDao;
import com.clarivate.HotMeall.Dao.UserDao;
import com.clarivate.HotMeall.Response.ResponseStructure;

@Service
public class MenuService {

	@Autowired
	MenuDao menuDao;

	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<Menu>> saveMenu(Menu menu, int id) {
		Optional<User> optional = userDao.getUserById(id);
		ResponseStructure<Menu> structure = new ResponseStructure<>();

		if (!optional.get().getRole().equals("BranchManager")) {
			structure.setError(true);
			structure.setMessage("You are not Manager!");

			return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
		}
		if (optional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("No user found");
		} else if ((optional.get().getMenu()) != null) {
			structure.setError(true);
			structure.setMessage("One Menu Already Exists!");
		} else {
			menu.setUser(optional.get());
			structure.setError(false);
			structure.setMessage("Menu Created!");
			structure.setData(menuDao.addMenu(menu));
		}
		return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Menu>> getMenuByManagerId(int managerId) {
		Optional<User> optional = userDao.getUserById(managerId);
		ResponseStructure<Menu> structure = new ResponseStructure<>();

		if (optional.get().getRole().equals("BranchManager")) {
			Menu menuByManagerId = menuDao.getMenuByManagerId(managerId);
			structure.setError(false);
			structure.setMessage("Menu Fetched");
			structure.setData(menuByManagerId);
		} else {
			structure.setError(true);
			structure.setMessage("Access Denied!");
		}
		return new ResponseEntity<ResponseStructure<Menu>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteMenu(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Menu> optional = menuDao.getMenuById(id);
		if (optional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("No Menu!");
		} else {
			structure.setError(false);
			structure.setMessage("Menu Deleted!");
			menuDao.deleteMenu(id);
		}
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

}
