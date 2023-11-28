import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StaffServiceService {
  constructor(private http: HttpClient) {}

  getAllFoodOrder(value: number) {
    return this.http.get(`http://localhost:8090/foodorder/${value}`);
  }

  deleteFoodOrder(value: number) {
    return this.http.delete(`http://localhost:8090/foodorder/${value}`);
  }

  getOrderById(value: number) {
    return this.http.get(`http://localhost:8090/foodorderbyid/${value}`);
  }

  updateStatus(foodOrder:any){
    return this.http.put("http://localhost:8090/foodorder",foodOrder)
  }

  saveFoodOrder(staffId:number, foodOrder:any){
    return this.http.post(`http://localhost:8090/foodorder/${staffId}`, foodOrder)
  }


  updateOrderStatus(status: String, id: number){
    return this.http.put(`http://localhost:8090/foodOrder/${id}`,status)
  }
}
