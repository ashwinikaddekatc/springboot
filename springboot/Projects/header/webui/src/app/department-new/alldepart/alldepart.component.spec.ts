import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlldepartComponent } from './alldepart.component';

describe('AlldepartComponent', () => {
  let component: AlldepartComponent;
  let fixture: ComponentFixture<AlldepartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlldepartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlldepartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
