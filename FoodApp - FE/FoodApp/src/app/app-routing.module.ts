import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { EditStaffComponent } from './edit-staff/edit-staff.component';
import { AddStaffComponent } from './add-staff/add-staff.component';
import { MangStaffComponent } from './mang-staff/mang-staff.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { StaffComponent } from './staff/staff.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ManagerDashboardComponent } from './manager-dashboard/manager-dashboard.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AddFpComponent } from './add-fp/add-fp.component';
import { EditFpComponent } from './edit-fp/edit-fp.component';
import { EditOrderComponent } from './edit-order/edit-order.component';
import { BillComponent } from './bill/bill.component';
import { AdminComponent } from './admin/admin.component';
import { BranchComponent } from './branch/branch.component';
import { AllManagersComponent } from './all-managers/all-managers.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: '', component: HomepageComponent },
  {path: 'admin',component:AdminComponent,canActivate:[AuthGuard]},
  {path:'branch',component:BranchComponent,canActivate:[AuthGuard]},
  {path:'allManagers',component:AllManagersComponent,canActivate:[AuthGuard]},
  { path: 'register', component: RegistrationComponent,canActivate:[AuthGuard]},
  { path: 'login', component: LoginComponent},
  { path: 'manager', component: ManagerDashboardComponent,canActivate:[AuthGuard]},
  { path: 'staff', component: StaffComponent,canActivate:[AuthGuard]},
  { path: 'add-fp', component: AddFpComponent,canActivate:[AuthGuard]},
  { path: 'edit-fp/:id', component: EditFpComponent,canActivate:[AuthGuard]},
  { path: 'create-order', component: CreateOrderComponent,canActivate:[AuthGuard]},
  { path: 'edit-order/:id', component: EditOrderComponent,canActivate:[AuthGuard]},
  { path: 'bill/:id', component: BillComponent,canActivate:[AuthGuard]},
  { path: 'showStaff', component: MangStaffComponent,canActivate:[AuthGuard]},
  { path: 'addStaff', component: AddStaffComponent,canActivate:[AuthGuard]},
  { path: 'editStaff/:id', component: EditStaffComponent,canActivate:[AuthGuard]},
  { path: 'editProfile/:id', component: EditProfileComponent,canActivate:[AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
