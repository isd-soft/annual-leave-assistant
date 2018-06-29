import { TestBed, inject } from '@angular/core/testing';

import { CompleteRegistrationService } from './complete-registration.service';

describe('CompleteRegistrationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CompleteRegistrationService]
    });
  });

  it('should be created', inject([CompleteRegistrationService], (service: CompleteRegistrationService) => {
    expect(service).toBeTruthy();
  }));
});
