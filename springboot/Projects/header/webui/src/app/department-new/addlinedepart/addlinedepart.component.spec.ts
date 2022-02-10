import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddlinedepartComponent } from './addlinedepart.component';

describe('AddlinedepartComponent', () => {
  let component: AddlinedepartComponent;
  let fixture: ComponentFixture<AddlinedepartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddlinedepartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddlinedepartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
