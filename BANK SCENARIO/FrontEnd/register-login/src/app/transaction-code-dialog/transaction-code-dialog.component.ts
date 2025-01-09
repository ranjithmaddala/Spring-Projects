import { HttpClient } from '@angular/common/http';
import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-transaction-code-dialog',
  template: `
  <h2 mat-dialog-title>Transaction Code Verification</h2>
    <mat-dialog-content>
      <mat-form-field appearance="outline" class="w-100">
        <mat-label>Enter Transaction Code</mat-label>
        <input matInput [(ngModel)]="transactionCode" type="password" />
      </mat-form-field>
      <div *ngIf="errorMsg" class="text-danger">{{ errorMsg }}</div>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button (click)="onCancel()">Cancel</button>
      <button mat-raised-button color="primary" (click)="onSubmit()">Submit</button>
    </mat-dialog-actions>
  `,
  styles: [`.w-100 {
        width: 100%;
      }`]
})
export class TransactionCodeDialogComponent {
  transactionCode: string = '';
  errorMsg: string | null = null;

  constructor(
    public dialogRef: MatDialogRef<TransactionCodeDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http:HttpClient
  ) {}

  onSubmit() {
     this.http.get('http://localhost:1234/abcbanking/matches?num='+sessionStorage.getItem('accNum')+'&pin='+this.transactionCode).subscribe(
      (result) =>{
        console.warn(result);
        if (result) {
          this.dialogRef.close(true); // Return success
        } else {
          this.errorMsg = 'Invalid transaction code.';
        }
      }
    )
    
  }

  onCancel() {
    this.dialogRef.close(false); // Return failure
  }
}
