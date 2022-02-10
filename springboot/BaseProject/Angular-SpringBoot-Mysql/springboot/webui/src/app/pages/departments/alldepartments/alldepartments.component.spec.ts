import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlldepartmentsComponent } from './alldepartments.component';

describe('AlldepartmentsComponent', () => {
  let component: AlldepartmentsComponent;
  let fixture: ComponentFixture<AlldepartmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlldepartmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlldepartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
