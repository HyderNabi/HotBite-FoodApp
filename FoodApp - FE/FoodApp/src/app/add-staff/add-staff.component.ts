import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from './../Services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-staff',
  templateUrl: './add-staff.component.html',
  styleUrls: ['./add-staff.component.css'],
})
export class AddStaffComponent implements OnInit {
  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute
  ) {}
  myId:any;

  ngOnInit(): void {
    this.myId = JSON.parse(localStorage.getItem("user")!);
    console.log(this.myId.id);
    this.myId = this.myId.id;
  }

  addStaff(form: NgForm) {
    console.log(form.value);
    // form.value.password = 'newStaff';
    form.value.role = 'staff';
    console.log(form.value);

    this.userService.registerUserAsStaff(form.value,this.myId).subscribe((res) => {
      console.log(res);
      this.router.navigate(['/showStaff']);
      alert('Staff Member Added Successfully!');
    });
  }
}
