import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../Services/user.service';
// import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  createUser : any;
  manager = "BranchManager";
  staff = "staff";
  user:any;

  constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
  }

  register(form: NgForm) {
    if(this.user.role === "Admin"){
      form.value.role = this.manager;
    } else {
      form.value.role = this.staff;
    }

    console.log(form);
    console.log(form.value);
    //add user to the database
    this.userService.registerUser(form.value).subscribe(res=>{
      console.log(res);
      this.router.navigate(["/admin"]);
      alert("Registration successful");
    })
  }

}
