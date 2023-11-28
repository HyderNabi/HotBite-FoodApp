package com.clarivate.HotMeall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clarivate.HotMeall.DTO.Menu;
import com.clarivate.HotMeall.Response.ResponseStructure;
import com.clarivate.HotMeall.Service.MenuService;

@RestController
@CrossOrigin
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	@PostMapping("/menu/{userId}")
	public ResponseEntity<ResponseStructure<Menu>> saveMenu(@RequestBody Menu menu, @PathVariable int userId){
		return menuService.saveMenu(menu, userId);
	}
	
	@GetMapping("/menu/manager/{managerId}")
	public ResponseEntity<ResponseStructure<Menu>> getMenuByManagerId(@PathVariable int managerId){
		return menuService.getMenuByManagerId(managerId);
	}
	
	@DeleteMapping("/menu/{menuId}")
	public ResponseEntity<ResponseStructure<String>> deleteMenu(@PathVariable int menuId){
		return menuService.deleteMenu(menuId);
	}

}
