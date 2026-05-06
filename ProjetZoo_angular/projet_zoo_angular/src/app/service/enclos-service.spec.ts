import { TestBed } from '@angular/core/testing';

import { EnclosService } from './enclos-service';

describe('EnclosService', () => {
  let service: EnclosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnclosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
