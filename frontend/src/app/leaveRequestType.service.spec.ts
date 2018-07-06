import { TestBed, inject } from '@angular/core/testing';

import { LeaveRequestTypeService } from './leaveRequestType.service';

describe('LeaveRequestTypeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LeaveRequestTypeService]
    });
  });

  it('should be created', inject([LeaveRequestTypeService], (service: LeaveRequestTypeService) => {
    expect(service).toBeTruthy();
  }));
});
