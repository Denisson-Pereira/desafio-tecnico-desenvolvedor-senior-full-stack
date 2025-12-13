import { TestBed } from '@angular/core/testing';

import { GetAllTurmasServiceService } from './get-all-turmas-service.service';

describe('GetAllTurmasServiceService', () => {
  let service: GetAllTurmasServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllTurmasServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
