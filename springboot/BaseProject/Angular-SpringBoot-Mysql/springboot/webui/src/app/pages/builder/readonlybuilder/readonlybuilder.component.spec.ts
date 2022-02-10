import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadonlybuilderComponent } from './readonlybuilder.component';

describe('ReadonlybuilderComponent', () => {
  let component: ReadonlybuilderComponent;
  let fixture: ComponentFixture<ReadonlybuilderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReadonlybuilderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadonlybuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
