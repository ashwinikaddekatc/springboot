import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatebuilderComponent } from './updatebuilder.component';

describe('UpdatebuilderComponent', () => {
  let component: UpdatebuilderComponent;
  let fixture: ComponentFixture<UpdatebuilderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdatebuilderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdatebuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
