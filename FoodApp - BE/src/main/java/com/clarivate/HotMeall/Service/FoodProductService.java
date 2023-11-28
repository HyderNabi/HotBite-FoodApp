package com.clarivate.HotMeall.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.clarivate.HotMeall.DTO.FoodProduct;
import com.clarivate.HotMeall.DTO.Menu;
import com.clarivate.HotMeall.Dao.FoodProductDao;
import com.clarivate.HotMeall.Dao.MenuDao;
import com.clarivate.HotMeall.Dao.UserDao;
import com.clarivate.HotMeall.Response.ResponseStructure;

@Service
public class FoodProductService {
	
	@Autowired
	FoodProductDao foodProductDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	MenuDao menuDao;
	
	public  ResponseEntity<ResponseStructure<FoodProduct>> addFoodProduct(FoodProduct foodProduct, int menuId) {
		ResponseStructure<FoodProduct> structure = new ResponseStructure<>();
		Optional<Menu> optional = menuDao.getMenuById(menuId); 
		if(optional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("No Menu Found");
		}else {
			foodProduct.setMenu(optional.get());
			structure.setError(false);
			structure.setMessage("Food Product Created!");
			structure.setData(foodProductDao.addFoodProduct(foodProduct));
		}		
		return new ResponseEntity<ResponseStructure<FoodProduct>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getFoodProductsInMenu(int menuId){
		ResponseStructure<List<FoodProduct>> structure = new ResponseStructure<>();
		structure.setError(false);
		structure.setMessage("All Food Products In Menu!");
		structure.setData(foodProductDao.getMenuFoodProducts(menuId));
		return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> getAllFoodProduct() {
        ResponseStructure<List<FoodProduct>> structure = new ResponseStructure<>();
            structure.setError(false);
            structure.setMessage("All Food Products!");
            structure.setData(foodProductDao.getAllFoodProducts());
        return new ResponseEntity<ResponseStructure<List<FoodProduct>>>(structure, HttpStatus.OK);
    }
	
	public ResponseEntity<ResponseStructure<FoodProduct>> updateFoodProduct(FoodProduct foodProduct) {
		ResponseStructure<FoodProduct> structure = new ResponseStructure<>();
		FoodProduct foodProductTobeUpdated = foodProductDao.getFoodProductById(foodProduct.getId()).get();
		Menu menu= foodProductTobeUpdated.getMenu();
		foodProduct.setMenu(menu);		
		structure.setError(false);
		structure.setMessage("Food Product Updated");
		structure.setData(foodProductDao.updateFoodProduct(foodProduct));

		return new ResponseEntity<ResponseStructure<FoodProduct>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteFoodProduct(int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<FoodProduct> optional = foodProductDao.getFoodProductById(id);
		if(optional.isEmpty()) {
			structure.setError(true);
			structure.setMessage("No Food Product Found!");
		} else {
			structure.setError(false);
			structure.setMessage("Food Product deleted!");
			foodProductDao.deleteFoodProduct(id);
		}
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);

	}


}
