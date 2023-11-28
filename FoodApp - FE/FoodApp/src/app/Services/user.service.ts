import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getAllUsers() {
    return this.http.get('http://localhost:8090/all');
  }

  registerUser(user: any) {
    return this.http.post('http://localhost:8090/user', user);
  }
  registerUserAsStaff(user: any,managerId:any) {
    return this.http.post(`http://localhost:8090/saveStaff/${managerId}`, user);
  }

  loginUser(user: any) {
    return this.http.post('http://localhost:8090/login', user);
  }

  getAllStaffs() {
    return this.http.get('http://localhost:8090/getallstaffs');
  }

  getUserById(id: number) {
    return this.http.get(`http://localhost:8090/user/${id}`);
  }

  updateUser(user: any,manager_id:any) {
    return this.http.put(`http://localhost:8090/user/${manager_id}`, user);
  }

  deleteUser(userId: number) {
    return this.http.delete(`http://localhost:8090/user/${userId}`);
  }
}
