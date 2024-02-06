import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeUserManagerComponent } from './change-user-manager.component';

describe('ChangeUserManagerComponent', () => {
  let component: ChangeUserManagerComponent;
  let fixture: ComponentFixture<ChangeUserManagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChangeUserManagerComponent]
    });
    fixture = TestBed.createComponent(ChangeUserManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
