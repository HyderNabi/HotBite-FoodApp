import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
// import { ToastrService } from 'ngx-toastr';
import { UserService } from '../Services/user.service';
import { AuthService } from '../Services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private userService: UserService,
    private route: Router,
    private authService:AuthService     
    ) { }

  res : any;

  ngOnInit(): void {
  }

  login(form: NgForm) {
    console.log(form.value);
    this.userService.loginUser(form.value).subscribe(data=>{
    this.res = data;
    console.log(this.res)
    if(this.res.error){
      console.log("Credentials are Invalid!");
      alert(this.res.message);
    }else if(this.res.data.role === "BranchManager"){
      this.authService.login(this.res.data);
      this.route.navigate(["/manager"])
    }else if(this.res.data.role === "staff"){
      this.authService.login(this.res.data);
      this.route.navigate(["/staff"])
    } else if(this.res.data.role === "Admin"){
      this.authService.login(this.res.data);
      this.route.navigate(["/admin"]);
    }
   })
  }

}
