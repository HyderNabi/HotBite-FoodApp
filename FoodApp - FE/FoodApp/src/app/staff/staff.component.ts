import { Router } from '@angular/router';
import { StaffServiceService } from './../Services/staff-service.service';
import { Component, OnInit } from '@angular/core';
import { BranchService } from '../Services/branch.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css'],
})
export class StaffComponent implements OnInit {
  searchOrder: any;
  confirmed = 'confirmed';
  delivered = 'delivered';
  progress = 'progress';
  deliveredTime: any;
  response: any;
  foodOrderId: Number = 0;
  constructor(
    private orders: StaffServiceService,
    private router: Router,
    private branchService:BranchService,
    private authService:AuthService
    ) {}

  allOrders: any;
  staff = JSON.parse(localStorage.getItem('user')!);

  allBranches:any;
  ngOnInit(): void {
    // Load all the food Orders
    this.orders.getAllFoodOrder(this.staff.id).subscribe((data) => {
      this.allOrders = data;
      console.log('List of all the Orders :', this.allOrders);
    });

    this.branchService.getAllBranches().subscribe((response)=>{
      this.allBranches = response;
      this.allBranches = this.allBranches.data;
      for(let p of this.allBranches){
        if(p.user.id === this.staff.manager.id){
          this.allBranches = p;
          break;
        }
      }
      //console.log(this.allBranches);
    })
  }

  deleteFoodOrder(orderID: number) {
    let permit = window.confirm('Are you sure you want to delete the order?');
    console.log(permit);
    if(permit == true){
      console.log('delete btn clicked.Id:' + orderID);
      this.orders.deleteFoodOrder(orderID).subscribe((response) => {
        console.log(response);
        this.router.navigate(['staff']);
        this.orders.getAllFoodOrder(this.staff.id).subscribe((data) => {
          this.allOrders = data;
          // this.deliveredTime=this.allOrders.data.orderDeliveryTime;
          console.log(this.allOrders);
        });
      });
    }
   
  }
  changeStatus(status: string, id: number) {
    console.log(status, id);
    this.orders.updateOrderStatus(status, id).subscribe((r) => {
      console.log(r);
      this.response = r;
      if (!this.response.error) {
        alert('Order status updated to: ' + status);
        if (this.response.data.status == 'delivered') {
          this.deliveredTime = this.response.data.orderDeliveryTime;
          console.log(this.deliveredTime);

          window.location.reload();
        }
      } else {
        alert("Couldn't update status. Try again! ");
      }
    });
  }

  logout(){
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
