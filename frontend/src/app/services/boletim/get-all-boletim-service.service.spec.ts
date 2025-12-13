import { TestBed } from '@angular/core/testing';

import { GetAllBoletimServiceService } from './get-all-boletim-service.service';

describe('GetAllBoletimServiceService', () => {
  let service: GetAllBoletimServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllBoletimServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
