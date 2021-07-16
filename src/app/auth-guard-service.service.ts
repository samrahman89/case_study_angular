import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardServiceService implements CanActivate {

  constructor(private router: Router) { }

  canActivate(): boolean{
    const user = localStorage.getItem('username');
    if(user!='admin'){
      this.router.navigate(['/home']);
      return false;
    }
    else{
      return true;
    }
  }

}
