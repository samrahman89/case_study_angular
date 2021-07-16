import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptConfigServiceService implements HttpInterceptor {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log(req.url);
    const auth = localStorage.getItem('empCredentials');
    console.log(auth);
    req = req.clone({
      setHeaders: {
        'Authorization': `Basic ${auth}`
      },
    });
    return next.handle(req);

    }
}
