import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee, EmployeeControllerService } from '../openapi';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent implements OnInit {

  employees!: Employee[];

  constructor(
    private employeeControllerService: EmployeeControllerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.employeeControllerService.getEmployees().subscribe(
      data => {
        this.employees=data;
      },
      error => {
        alert('Invalid Credentials');
        localStorage.removeItem('empCredentials');
      }
    );
  }

  getRow(id: string | undefined): void {
    this.router.navigate(['employee', id]);
  }

}
