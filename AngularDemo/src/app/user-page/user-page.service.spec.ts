import { TestBed, inject } from '@angular/core/testing';

import { UserPageService } from './user-page.service';

describe('UserPageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserPageService]
    });
  });

  it('should be created', inject([UserPageService], (service: UserPageService) => {
    expect(service).toBeTruthy();
  }));
});
