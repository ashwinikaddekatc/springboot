import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateupdatedepartmentsComponent } from './updateupdatedepartments.component';

describe('UpdateupdatedepartmentsComponent', () => {
  let component: UpdateupdatedepartmentsComponent;
  let fixture: ComponentFixture<UpdateupdatedepartmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateupdatedepartmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateupdatedepartmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
