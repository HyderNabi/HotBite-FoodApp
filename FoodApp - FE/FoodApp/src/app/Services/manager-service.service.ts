import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ManagerServiceService {

  constructor(private http:HttpClient) { }

  getMenu(value:number){
    return this.http.get(`http://localhost:8090/menu/manager/${value}`);
  }
  getFoodProductsInMenu(menu_id:any){
    return this.http.get(`http://localhost:8090/foodproduct/${menu_id}`); 
  }

  editFpData(foodProduct:any){
    return this.http.put("http://localhost:8090/foodproduct",foodProduct);
  }

  deleteFpData(value:number){
    return this.http.delete(`http://localhost:8090/foodproduct/${value}`);
  }

  addfpData(foodProduct:any, menuId:number){
    return this.http.post(`http://localhost:8090/foodproduct/${menuId}`,foodProduct);
  }

  getAllFoodProducts(){
    return this.http.get("http://localhost:8090/foodproduct")
  }
}
