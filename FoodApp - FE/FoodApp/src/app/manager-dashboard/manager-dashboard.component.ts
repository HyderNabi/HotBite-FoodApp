import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ManagerServiceService } from '../Services/manager-service.service';
import { BranchService } from '../Services/branch.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-manager-dashboard',
  templateUrl: './manager-dashboard.component.html',
  styleUrls: ['./manager-dashboard.component.css'],
})
export class ManagerDashboardComponent implements OnInit {
  flag: boolean = false;
  result: any;
  menuFlag = true;

  manager = JSON.parse(localStorage.getItem('user')!);
  my_menu: any;
  constructor(
    private menu: ManagerServiceService,
    private router: Router,
    private branchService:BranchService,
    private authService:AuthService
    ) {}
  allBranches:any;

  ngOnInit(): void {
    this.menu.getMenu(this.manager.id).subscribe((data) => {
     // console.log(data);
      this.result = data;
      if (this.result.data.foodProducts.length != 0) {
        this.menuFlag = false;
      } else {
        this.menuFlag = true;
      }
      console.log(this.result.data.id);
      console.log(this.manager);
      localStorage.setItem('my_menu', this.result.data.id);
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
  reply: any;
  deletefp(id: number) {
    console.log('delete btn clicked. Id:' + id);
    this.reply = window.confirm('Are you sure you want to delete the product?');
    console.log(this.reply);
    if (this.reply == true) {
      this.menu.deleteFpData(id).subscribe((response) => {
        console.log(response);
        this.router.navigate(['manager']);
        this.menu.getMenu(this.manager).subscribe((data) => {
          this.result = data;
          console.log(this.result);
        });
      });
      location.reload();
    }
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
