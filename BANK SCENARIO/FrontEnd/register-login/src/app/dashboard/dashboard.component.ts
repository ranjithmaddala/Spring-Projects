import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  data : any;

  constructor(private http:HttpClient){}
  ngOnInit(){
    this.getAllTrans();
  }

  getAllTrans(){
    console.log("Account Number : "+sessionStorage.getItem('accNum'));
    this.http.get("http://localhost:1235/abcbanking/getTrans?accNumber="+sessionStorage.getItem('accNum')).subscribe(
      (data) =>{
        console.log(data);

        this.data = data;
      }
    )
  }
}
