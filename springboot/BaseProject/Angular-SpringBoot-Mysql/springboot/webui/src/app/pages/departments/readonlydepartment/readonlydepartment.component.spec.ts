import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadonlydepartmentComponent } from './readonlydepartment.component';

describe('ReadonlydepartmentComponent', () => {
  let component: ReadonlydepartmentComponent;
  let fixture: ComponentFixture<ReadonlydepartmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReadonlydepartmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadonlydepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
