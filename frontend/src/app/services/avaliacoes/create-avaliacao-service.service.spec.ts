import { TestBed } from '@angular/core/testing';

import { CreateAvaliacaoServiceService } from './create-avaliacao-service.service';

describe('CreateAvaliacaoServiceService', () => {
  let service: CreateAvaliacaoServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateAvaliacaoServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
