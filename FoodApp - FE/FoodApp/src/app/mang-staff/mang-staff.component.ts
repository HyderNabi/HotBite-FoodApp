import { UserService } from './../Services/user.service';
import { Router } from '@angular/router';
import { ManagerServiceService } from './../Services/manager-service.service';
import { Component, OnInit } from '@angular/core';
import { BranchService } from '../Services/branch.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-mang-staff',
  templateUrl: './mang-staff.component.html',
  styleUrls: ['./mang-staff.component.css'],
})
export class MangStaffComponent implements OnInit {
  manager = JSON.parse(localStorage.getItem('user')!);
  allStaff: any;
  staffFlag = false;
  constructor(
    private router: Router, 
    private userService: UserService,
    private branchService:BranchService,
    private authService:AuthService
    ) {}
  myId:any;
  allBranches:any;
  ngOnInit(): void {
    this.myId = JSON.parse(localStorage.getItem("user")!).id;
    this.userService.getAllStaffs().subscribe((data) => {
      console.log(data);
      this.allStaff = data;
      this.allStaff = this.allStaff.data.filter((p:any)=>p.manager.id === this.myId);
      console.log(this.allStaff);
      if (this.allStaff.length == 0) {
        this.staffFlag = true;
      }
    });
    this.branchService.getAllBranches().subscribe((response)=>{
      this.allBranches = response;
      this.allBranches = this.allBranches.data;
      // this.allBranches = this.allBranches.filter((p:any)=>p.user.id===this.manager.id)
      // console.log(this.allBranches);
      for(let p of this.allBranches){
        if(p.user.id === this.manager.id){
          this.allBranches = p;
          break;
        }
      }
    })
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }
  reply: any;
  deleteStaff(id: any) {
    console.log('delete btn clicked. Id:' + id);
    this.reply = window.confirm(
      'Are you sure you want to Fire the Staff Member?'
    );
    console.log(this.reply);
    if (this.reply == true) {
      this.userService.deleteUser(id).subscribe((response) => {
        console.log(response);
        this.router.navigate(['showStaff']);
        this.userService.getAllStaffs().subscribe((data) => {
          console.log(data);
          this.allStaff = data;
          this.allStaff = this.allStaff.data;
          console.log(this.allStaff);
          if (this.allStaff.length == 0) {
            this.staffFlag = true;
          }
        });
      });
      location.reload();
    }
  }
}
