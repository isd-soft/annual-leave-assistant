import { TestBed, inject } from '@angular/core/testing';

import { GetRoleService } from './get-role.service';

describe('GetRoleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetRoleService]
    });
  });

  it('should be created', inject([GetRoleService], (service: GetRoleService) => {
    expect(service).toBeTruthy();
  }));
});
