import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionCodeDialogComponent } from './transaction-code-dialog.component';

describe('TransactionCodeDialogComponent', () => {
  let component: TransactionCodeDialogComponent;
  let fixture: ComponentFixture<TransactionCodeDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TransactionCodeDialogComponent]
    });
    fixture = TestBed.createComponent(TransactionCodeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
