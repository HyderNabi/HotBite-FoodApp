import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from './Services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'FoodApp';
  constructor(
    private router: Router,
    private authService:AuthService
    ) {}
  user: any;
  ngOnInit(): void {
  }
   checkIfLogin() {
      if(this.authService.isloggedIn()) {
        this.user = JSON.parse(localStorage.getItem("user")!);
        console.log(this.user);
        let role = this.user.role;
        if (role === 'staff') {
          console.log('Staff Dashboard');
          this.router.navigate(['staff']);
        } else if (role === 'BranchManager') {
          console.log('Manager Dashboard');
          this.router.navigate(['manager']);
        } else if(role === 'Admin'){
          this.router.navigate(['/admin']);
        }
      }else {
        this.router.navigate(['/login']);
      }
  }
}
