import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistserLoginComponent } from './registser-login.component';

describe('RegistserLoginComponent', () => {
  let component: RegistserLoginComponent;
  let fixture: ComponentFixture<RegistserLoginComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RegistserLoginComponent]
    });
    fixture = TestBed.createComponent(RegistserLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
