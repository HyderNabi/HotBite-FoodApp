import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  constructor(private http: HttpClient) {}

  saveBranch(branch:any,managerId:any) {
    return this.http.post(`http://localhost:8090/createBranch/${managerId}`,branch);
  }

  getAllBranches(){
    return this.http.get("http://localhost:8090/getAllBranches");
  }
}
