import { TestBed } from '@angular/core/testing';

import { ProfileservicService } from './profileservic.service';

describe('ProfileservicService', () => {
  let service: ProfileservicService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileservicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
