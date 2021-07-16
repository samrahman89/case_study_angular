import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeComponent } from './employee/employee.component';
import { HomeComponent } from './home/home.component';
import {LoginComponent} from './login/login.component';
import { AuthGuardServiceService } from './auth-guard-service.service';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'employeeList', component: EmployeeListComponent},
  {path: 'employee', component: EmployeeComponent,
  canActivate: [AuthGuardServiceService]},
  {path: 'employee/:id', component: EmployeeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
