import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }
  login(user:any){
    let mymanager = null;
    if(user.manager){
      mymanager={
        id:user.manager.id,
        name:user.manager.name,
        email:user.manager.email,
        role:user.manager.role,

      }
    }
    let myUser ={
      id :user.id,
      name : user.name,
      email :user.email,
      role : user.role,
      manager : mymanager,
    }
    console.log(user);
    console.log(myUser);
    localStorage.setItem("user", JSON.stringify(myUser));
  }

  logout() {
    localStorage.clear();
  }

  isloggedIn(){
    if(JSON.parse(localStorage.getItem('user')!) != null){
      return true;
    } else {
      return false;
    }
  }
}
