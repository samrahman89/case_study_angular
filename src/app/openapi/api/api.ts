export * from './bookController.service';
import { BookControllerService } from './bookController.service';
export * from './employeeController.service';
import { EmployeeControllerService } from './employeeController.service';
export const APIS = [BookControllerService, EmployeeControllerService];
