import { Component, OnInit } from '@angular/core';
import { UserService } from '../Services/user.service';
import { Router } from '@angular/router';
import { BranchService } from '../Services/branch.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-all-managers',
  templateUrl: './all-managers.component.html',
  styleUrls: ['./all-managers.component.css']
})
export class AllManagersComponent implements OnInit {

  allBranches:any;
  allManagers:any;
  role="BranchManager";
  constructor(private userService:UserService,
    private router:Router,
    private branchService:BranchService,
    private authService:AuthService
    ) { }


  ngOnInit(): void {
    this.userService.getAllUsers().subscribe((data)=>{
      this.allManagers = data;
      if(!this.allManagers.error){
        this.allManagers = this.allManagers.data;
        this.allManagers = this.allManagers.filter((p:any)=>p.role===this.role);
        console.log(this.allManagers);
      }
    })
    this.branchService.getAllBranches().subscribe((response)=>{
        this.allBranches = response;
        this.allBranches = this.allBranches.data;
        console.log(this.allBranches)
    })
  }
  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
