import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserTimesComponent } from './user-times.component';

describe('UserTimesComponent', () => {
  let component: UserTimesComponent;
  let fixture: ComponentFixture<UserTimesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserTimesComponent]
    });
    fixture = TestBed.createComponent(UserTimesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
