import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaveRequestTypesComponent } from './leave-request-types.component';

describe('LeaveRequestTypesComponent', () => {
  let component: LeaveRequestTypesComponent;
  let fixture: ComponentFixture<LeaveRequestTypesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaveRequestTypesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaveRequestTypesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
