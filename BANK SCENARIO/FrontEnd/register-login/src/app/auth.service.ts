import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(this.getSessionStatus());

  // Observable for logged-in state
  loggedIn$ = this.loggedIn.asObservable();

  constructor() {
    // Ensure BehaviorSubject is synchronized with sessionStorage on service load
    const initialStatus = this.getSessionStatus();
    if (this.loggedIn.value !== initialStatus) {
      this.loggedIn.next(initialStatus);
    }
  }

  // Retrieve login status from sessionStorage
  private getSessionStatus(): boolean {
    return sessionStorage.getItem('login') === 'true';
  }

  // Getter for current login status
  get isLoggedIn(): boolean {
    return this.loggedIn.value;
  }

  // Setter to update login state
  setLoggedIn(value: boolean): void {
    this.loggedIn.next(value);
    sessionStorage.setItem('login', value.toString());
  }
}
