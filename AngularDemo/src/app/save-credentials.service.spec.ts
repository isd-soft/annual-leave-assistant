import { TestBed, inject } from '@angular/core/testing';

import { SaveCredentialsService } from './save-credentials.service';

describe('SaveCredentialsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SaveCredentialsService]
    });
  });

  it('should be created', inject([SaveCredentialsService], (service: SaveCredentialsService) => {
    expect(service).toBeTruthy();
  }));
});
