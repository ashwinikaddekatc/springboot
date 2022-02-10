import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllbuilderComponent } from './allbuilder.component';

describe('AllbuilderComponent', () => {
  let component: AllbuilderComponent;
  let fixture: ComponentFixture<AllbuilderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllbuilderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllbuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
