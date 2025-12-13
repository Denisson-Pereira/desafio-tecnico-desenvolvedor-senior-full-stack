import { TestBed } from '@angular/core/testing';

import { GetAllDisciplinasServiceService } from './get-all-disciplinas-service.service';

describe('GetAllDisciplinasServiceService', () => {
  let service: GetAllDisciplinasServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllDisciplinasServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
