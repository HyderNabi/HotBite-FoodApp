import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { ManagerServiceService } from '../Services/manager-service.service';

@Component({
  selector: 'app-add-fp',
  templateUrl: './add-fp.component.html',
  styleUrls: ['./add-fp.component.css']
})
export class AddFpComponent implements OnInit {

  res:any
  manager = JSON.parse(localStorage.getItem("user")!);
  menu_id = JSON.parse(localStorage.getItem("my_menu")!);

  constructor(private service:ManagerServiceService, private route:Router) { }

  ngOnInit(): void {
  }
  OnSubmit(form:NgForm){
    console.log(form.value);
    console.log("Manager id: "+this.manager.id);
    console.log("Menu id: "+this.menu_id);

    this.service.addfpData(form.value, this.menu_id).subscribe(r=>{
      this.res=r;
      console.log(this.res.message);
      if(!this.res.error){
        alert("Food product added!");
        this.route.navigate(["/manager"])
      }
    })
    
  }

}
