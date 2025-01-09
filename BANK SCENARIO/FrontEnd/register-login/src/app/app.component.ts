import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'register-login';
  loggedIn: boolean = false;

  constructor(private authService: AuthService, private route:Router) {}

  ngOnInit(): void {
    // Sync logged-in status on component load
    this.loggedIn = this.authService.isLoggedIn;

    // Subscribe to updates for real-time changes
    this.authService.loggedIn$.subscribe((status) => {
      this.loggedIn = status;
    });
  }

  logout(){
    sessionStorage.clear();
    this.route.navigate(['home']);
    this.authService.setLoggedIn(false);
    //window.location.reload();
    
  }
}
