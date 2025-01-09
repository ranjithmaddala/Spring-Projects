import { Component } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { TransactionService } from './transaction.service';
import { HttpClient } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { TransactionCodeDialogComponent } from '../transaction-code-dialog/transaction-code-dialog.component';


@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent {

  accountNumber:any;

  transForm !: FormGroup;

  successMsg = "";
  failedMsg = "";

  constructor(private fb: FormBuilder, private http:HttpClient, private dialog:MatDialog){}

  ngOnInit(){
    console.log(sessionStorage.getItem('accNum'))
    console.log(sessionStorage.getItem('pin'))
    
    
    
    this.accountNumber = sessionStorage.getItem('accNum');


     this.transForm = this.fb.group({
      recAccountNumber: [
        '',
        [Validators.required, Validators.pattern(/^[0-9]{12}$/)]
      ],
      recEmail: ['', [Validators.required, Validators.email]],
      recAccountName: [''],
      amount: ['', [Validators.required, Validators.min(1)]]
    });
  }

  sendMoney() {
    if (this.transForm.valid) {
      // Open transaction code dialog
      const dialogRef = this.dialog.open(TransactionCodeDialogComponent, {
        width: '400px',
      });

      dialogRef.afterClosed().subscribe((result) => {
        if (result) {
          // Proceed with form submission
          console.log('Form Submitted', this.transForm.value);
          this.http
            .post(
              `http://localhost:1235/abcbanking/get?accNumber=${this.accountNumber}`,
              this.transForm.value,
              { responseType: 'text' }
            )
            .subscribe((data) => {
              console.log(data);
              if (data.includes('Failed')) {
                this.failedMsg = data +", please check the details you've entered";
              } else {
                this.successMsg = data;
              }
              setTimeout(() => {
                this.successMsg = '';
                this.failedMsg = '';
              }, 3000);
            });
        } else {
          console.log('Transaction canceled or invalid code.');
        }
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
