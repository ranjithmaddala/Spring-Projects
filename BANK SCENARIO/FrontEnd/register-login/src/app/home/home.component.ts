import { Component } from '@angular/core';
import { User } from '../profile/user';
import { ProfileservicService } from '../profile/profileservic.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  constructor(private userServ:ProfileservicService){}

  user !: User;

  ngOnInit(){
    this.userServ.getUserData().subscribe(
      (data) => {
        this.user = data;
      }
    )
  }
}
