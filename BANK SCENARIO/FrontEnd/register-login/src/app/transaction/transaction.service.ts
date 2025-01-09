import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Transaction } from './transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  accountNumber = sessionStorage.getItem('accNum');

  constructor(private http : HttpClient) { }

  getStatusOfTransaction(form : FormGroup):any{
    console.warn("posting data to http://localhost:1235/abcbanking/get?accNumber=" + this.accountNumber+" --> "+form.value);
    return this.http.post("http://localhost:1235/abcbanking/get?accNumber="+this.accountNumber,form);
  }
}
