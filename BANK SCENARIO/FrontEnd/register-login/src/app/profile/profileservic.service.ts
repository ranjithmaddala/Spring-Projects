import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class ProfileservicService {

  constructor(private http: HttpClient) {}

  getUserData(): Observable<User> {
    return this.http.get<User>('http://localhost:1234/abcbanking/getuser?email='+sessionStorage.getItem("email"));
  }

  updateUser(updateform :any){
    console.log("performing updation");           
    return this.http.patch('http://localhost:1234/abcbanking/updateuser?email='+sessionStorage.getItem("email"), updateform);
  }
}
