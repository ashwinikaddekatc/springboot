import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatedeptComponent } from './updatedept.component';

describe('UpdatedeptComponent', () => {
  let component: UpdatedeptComponent;
  let fixture: ComponentFixture<UpdatedeptComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdatedeptComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatedeptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
