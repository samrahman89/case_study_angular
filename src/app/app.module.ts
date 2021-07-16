import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeComponent } from './employee/employee.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import{ApiModule} from './openapi';
import{HttpInterceptConfigServiceService} from './http-intercept-config-service.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    EmployeeListComponent,
    EmployeeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    ApiModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptConfigServiceService,
      multi: true
      }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
