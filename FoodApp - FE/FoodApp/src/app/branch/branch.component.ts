import { Component, OnInit } from '@angular/core';
import { UserService } from '../Services/user.service';
import { BranchService } from '../Services/branch.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.css']
})
export class BranchComponent implements OnInit {

  managers:any;
  response:any;
  constructor(private service : UserService,private branchService:BranchService,private route:Router) { }


  ngOnInit(): void {
    this.service.getAllUsers().subscribe((response)=>{
      this.response  = response;
      if(!this.response.error) {
        console.log(this.response.data);
        this.managers = this.response.data.filter((p: any) => p.role === "BranchManager");
        console.log(this.managers);
      }
    });
  }
  result:any;
  OnSubmit(form:any){
    console.log(form.value);
    let branch={
      name:form.value.name,
      address:form.value.address,
      phoneNumber:form.value.phoneNumber,
      email:form.value.email
    }
    
    this.branchService.saveBranch(branch,form.value.branchManager).subscribe((response)=>{
      this.result = response;
      if(!this.result.error){
        alert(this.result.message);
        this.route.navigate(["/admin"]);
      } else {
        alert(this.result.message);
      }
    })
    
  }

}
