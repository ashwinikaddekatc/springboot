import { TestBed } from '@angular/core/testing';

import { ServicebuilderService } from './servicebuilder.service';

describe('ServicebuilderService', () => {
  let service: ServicebuilderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicebuilderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
