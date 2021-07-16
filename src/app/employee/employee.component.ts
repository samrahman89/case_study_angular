import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'protractor';
import { EmployeeControllerService } from '../openapi';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

  employeeForm!: FormGroup;
  id!: string;
  title!: string;
  btnText!: string;
  bloodGrp = [
    {
      id: 'A+'
    },
    {
      id: 'A-'
    },
    {
      id: 'B+'
    },
    {
      id: 'B-'
    },
    {
      id: 'O+'
    },
    {
      id: 'O-'
    },
    {
      id: 'AB+'
    },
    {
      id: 'AB-'
    }
  ];

  constructor(
    private employeeControllerService: EmployeeControllerService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = params.id;
    });
    this.employeeForm = new FormGroup({
      id: new FormControl(''),
      employeeID: new FormControl('', [Validators.required]),
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      dob: new FormControl('', [Validators.required]),
      bloodGroup: new FormControl('', [Validators.required]),
      mobileNumber: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required])
    });
    if (this.id) {
      this.title = 'Update Employee';
      this.btnText = 'Update';
      this.employeeControllerService.getEmployee(this.id).subscribe(
        (data: { [key: string]: any; }) => {
          this.employeeForm.setValue(data);
        }
      );
    }
    else {
      this.title = 'Add Employee';
      this.btnText = 'Save';
    }
  }

  save(): void {
    this.employeeControllerService.postEmployee(this.employeeForm.value).subscribe(
      (data: any) => {
        if (!this.id) {
          alert('Employee Added Successfully.')
          this.router.navigate(['/employeeList']);
        }
        else{
          alert('Employee Updated Successfully.')
        }
      },
      (error: any) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 403) {
            alert('User Forbidden to perform this action !!!');
          }
        }
        else{
          alert('Unable to add/update employee. Error: ' + error.message);
        }
      }
    );
  }

  back(): void {
    this.router.navigate(['/employeeList']);
  }

  delete(): void {
    this.employeeControllerService.deleteEmployee(this.employeeForm.value).subscribe(
      (data: any) => {
          alert('Employee Deleted Successfully.')
          this.router.navigate(['/employeeList']);
      },
      (error: any) => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 403) {
            alert('User Forbidden to perform this action !!!');
          }
        }
        else{
          alert('Unable to delete employee. Error: ' + error.message);
        }
      }
    );
  }

}
