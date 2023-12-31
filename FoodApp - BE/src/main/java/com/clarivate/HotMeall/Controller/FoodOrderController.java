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

import com.clarivate.HotMeall.DTO.FoodOrder;
import com.clarivate.HotMeall.Response.ResponseStructure;
import com.clarivate.HotMeall.Service.FoodOrderService;

@RestController
@CrossOrigin
public class FoodOrderController {
	
	@Autowired
	FoodOrderService foodOrderService;
	
	@PostMapping("/foodorder/{staffId}")
	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(@RequestBody FoodOrder foodOrder, @PathVariable int staffId){
		
		return foodOrderService.saveFoodOrder(foodOrder, staffId );
	}
	//FoodOrder By Staff ID - gives orders placed by staff
	@GetMapping("/foodorder/{userId}")
	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getFoodOrder(@PathVariable int userId) {
		return foodOrderService.getFoodOrder(userId); 
	}
	//FoodOrder by FoodOrder ID
	@GetMapping("/foodorderbyid/{foodOrderId}")
	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderById(@PathVariable int foodOrderId) {
		return foodOrderService.getFoodOrderByItsId(foodOrderId); 
	}
	
	@PutMapping("/foodorder")
	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(@RequestBody FoodOrder foodOrder) {
		return foodOrderService.updateFoodOrder(foodOrder);
	}
	
	@PutMapping("/foodOrder/{foodOrderId}")
	public ResponseEntity<ResponseStructure<FoodOrder>> updateStatus(@RequestBody String status, @PathVariable int foodOrderId) {
		return foodOrderService.updateStatus(status,foodOrderId);
	}
	
	
	@DeleteMapping("/foodorder/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFoodOrderById(@PathVariable int id) {
		return foodOrderService.deleteFoodOrderById(id);
	}
}
