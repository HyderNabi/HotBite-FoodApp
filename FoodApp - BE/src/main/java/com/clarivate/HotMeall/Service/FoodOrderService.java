package com.clarivate.HotMeall.Service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clarivate.HotMeall.DTO.FoodOrder;
import com.clarivate.HotMeall.DTO.User;
import com.clarivate.HotMeall.Dao.FoodOrderDao;
import com.clarivate.HotMeall.Dao.UserDao;
import com.clarivate.HotMeall.Response.ResponseStructure;

@Service
public class FoodOrderService {

	@Autowired
	FoodOrderDao foodOrderDao;

	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<FoodOrder>> saveFoodOrder(FoodOrder foodOrder, int id) {
		System.out.println(foodOrder.getTotalPrice());
		Optional<User> optional = userDao.getUserById(id);
		ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
		
		if(optional.get().getRole().equals("BranchManager")) {
			structure.setError(true);
			structure.setMessage("Access Denied!");
			return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
		}
		if (optional.isEmpty()) {
			System.out.println("No Id found");
		} else {
			foodOrder.setUser(optional.get());
		}
		structure.setError(false);
		structure.setMessage("Order Placed!");
		structure.setData(foodOrderDao.addFoodOrder(foodOrder));

		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<FoodOrder>>> getFoodOrder(int userId) {
		ResponseStructure<List<FoodOrder>> structure = new ResponseStructure<>();
		Optional<User> optional = userDao.getUserById(userId);
		if (optional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("No Staff Found!");
		} else {
			structure.setError(false);
			structure.setMessage("Food Order Fetched!");
			structure.setData(optional.get().getFoodOrders());
		}
		return new ResponseEntity<ResponseStructure<List<FoodOrder>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> getFoodOrderByItsId(int foodOrderId) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
		Optional<FoodOrder> foodOrderOptional = foodOrderDao.getFoodOrderById(foodOrderId);
		if (foodOrderOptional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("No foodOrder Found!");
		} else {
			structure.setError(false);
			structure.setMessage("Food Order Fetched!");
			structure.setData(foodOrderOptional.get());
		}
		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateFoodOrder(FoodOrder foodOrder) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
		FoodOrder foodOrderTobeUpdated = foodOrderDao.getFoodOrderById(foodOrder.getId()).get();
		User user = foodOrderTobeUpdated.getUser();
		foodOrder.setUser(user);
		structure.setError(false);
		structure.setMessage("Food Order Updated!");
		structure.setData(foodOrderDao.updateFoodOrder(foodOrder));

		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<FoodOrder>> updateStatus(String status, int foodOrderId) {
		ResponseStructure<FoodOrder> structure = new ResponseStructure<>();
		FoodOrder foodOrderTobeUpdated = foodOrderDao.getFoodOrderById(foodOrderId).get();
		if(status.equals("delivered")) {
			foodOrderTobeUpdated.setOrderDeliveryTime(new Date());
		}
		foodOrderTobeUpdated.setStatus(status);
		structure.setError(false);
		structure.setMessage("Food Order Status Updated!");
		structure.setData(foodOrderDao.updateFoodOrder(foodOrderTobeUpdated));

		return new ResponseEntity<ResponseStructure<FoodOrder>>(structure, HttpStatus.OK);
	}
	

	public ResponseEntity<ResponseStructure<String>> deleteFoodOrderById(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<FoodOrder> optional = foodOrderDao.getFoodOrderById(id);
		if (optional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("Food Order Not Found!");
		} else {
			structure.setError(false);
			structure.setMessage("Order Deleted!");
			foodOrderDao.deleteFoodOrder(id);
		}

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

}