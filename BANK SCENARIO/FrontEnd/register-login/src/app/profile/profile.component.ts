import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ProfileservicService } from './profileservic.service';
import { User } from './user';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  user !: User;
 
  

  constructor(private http:HttpClient, private serv:ProfileservicService){}

  ngOnInit(){
    this.serv.getUserData().subscribe(
      (data : User) => {
        console.warn(data);
        this.user = data;
        sessionStorage.setItem('accNum', this.user.accountNumber);
      }
    );                    
  }

  isEditing = false;
  successMessage = '';

  editProfile() {
    this.isEditing = true;
  }
 
  userE = {
      userName : "",
      emailId : ""
    }

  saveProfile(formValues: any) {
  
    // Update user details
    this.user = { ...this.user, ...formValues };
    this.isEditing = false;
    this.userE.userName = this.user.userName;
    this.userE.emailId = this.user.emailId;
    if(this.serv.updateUser(this.userE).subscribe() !=null)
      this.successMessage = 'Profile updated successfully!';
    else
      this.successMessage = 'Something went Wrong!!';
    // Hide success message after 3 seconds
    setTimeout(() => {
      this.successMessage = '';
    }, 3000);
  }

  cancelEdit() {
    this.isEditing = false;
  }

}
