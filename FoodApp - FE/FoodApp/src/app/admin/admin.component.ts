import { Component, OnInit } from '@angular/core';
import { BranchService } from '../Services/branch.service';
import { Router } from '@angular/router';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(
    private branchService:BranchService,
    private router:Router,
    private authService:AuthService
    ) {}

  allBranches:any;
  ngOnInit(): void {
    this.branchService.getAllBranches().subscribe((data)=>{
      this.allBranches = data;
      if(!this.allBranches.error){
      //  alert(this.allBranches.message);
        console.log(this.allBranches);
      }
    })
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
