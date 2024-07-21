import { TestBed } from '@angular/core/testing';

import { BankcardService } from './bankcard.service';

describe('BankcardService', () => {
  let service: BankcardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BankcardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
