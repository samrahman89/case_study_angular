import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { Form, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeControllerService } from '../openapi';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username!: string;
  password!: string;

  constructor(
    private employeeControllerService: EmployeeControllerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    localStorage.removeItem('empCredentials');
    localStorage.removeItem('username');
  }

  submit(form : NgForm) {
    if(!form.valid){
      return;
    }
    else
    {
      console.log(this.username+':'+this.password);
      localStorage.setItem('empCredentials', window.btoa(this.username+':'+this.password));
      localStorage.setItem('username', this.username);
      this.employeeControllerService.authenticate().subscribe(
        data => {
          this.router.navigate(['/home']);
        },
        error => {
          alert('Invalid Credentials');
          localStorage.removeItem('empCredentials');
          localStorage.removeItem('username');
        }
      );
    }
  }
}
