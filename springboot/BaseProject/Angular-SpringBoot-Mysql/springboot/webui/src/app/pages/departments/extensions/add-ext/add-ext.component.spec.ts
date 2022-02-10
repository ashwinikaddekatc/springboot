import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExtComponent } from './add-ext.component';

describe('AddExtComponent', () => {
  let component: AddExtComponent;
  let fixture: ComponentFixture<AddExtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddExtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
