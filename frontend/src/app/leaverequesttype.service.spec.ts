import { TestBed, inject } from '@angular/core/testing';

import { LeaverequesttypeService } from './leaverequesttype.service';

describe('LeaverequesttypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LeaverequesttypeService]
    });
  });

  it('should be created', inject([LeaverequesttypeService], (service: LeaverequesttypeService) => {
    expect(service).toBeTruthy();
  }));
});
