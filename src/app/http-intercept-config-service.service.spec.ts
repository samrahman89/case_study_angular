import { TestBed } from '@angular/core/testing';

import { HttpInterceptConfigServiceService } from './http-intercept-config-service.service';

describe('HttpInterceptConfigServiceService', () => {
  let service: HttpInterceptConfigServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpInterceptConfigServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
