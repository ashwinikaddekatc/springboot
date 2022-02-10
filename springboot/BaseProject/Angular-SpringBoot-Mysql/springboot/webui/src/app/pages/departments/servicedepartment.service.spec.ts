import { TestBed } from '@angular/core/testing';

import { ServicedepartmentService } from './servicedepartment.service';

describe('ServicedepartmentService', () => {
  let service: ServicedepartmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicedepartmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
