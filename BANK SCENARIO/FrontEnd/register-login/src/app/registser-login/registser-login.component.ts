import { HttpClient } from '@angular/common/http';
import { Component, ViewContainerRef } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { AlertDialogComponent } from '../alert-dialog/alert-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { User } from '../profile/user';
import { ProfileservicService } from '../profile/profileservic.service';
import { matchPasswords } from './PasswordValidator';

@Component({
  selector: 'app-registser-login',
  templateUrl: './registser-login.component.html',
  styleUrls: ['./registser-login.component.css']
})
export class RegistserLoginComponent {

  user!:User;


  logForm!:FormGroup;

  registerForm !: FormGroup;

  lf:boolean = true;
  rf:boolean = false;

  registered : string = "";
  registerError:string = "";
  loginError : string = "";

  toggle(req:string){
    if(req == 'reg'){
      this.rf = true;
      this.lf = false;
    }
    else{
      this.rf = false;
      this.lf = true;
    }
    
  }


  ngOnInit(){
    this.logForm = this.fb.group({
      userId : ['', [Validators.required, Validators.email]],
      password : ['',[Validators.required]]
    });

    this.registerForm = this.fb.group({
      userName : ['',[Validators.required]],
      emailId : ['',[Validators.required, Validators.email]],
      accountNumber : ['',[Validators.required, Validators.pattern(/^[6-9][0-9]{11}$/)]],
      transactionPass : ['',[Validators.required]],
      cTransactioPass : ['',[Validators.required]],
      loginPass : ['',[Validators.required]],
      cLoginPass : ['',[Validators.required]]
    },
    {
      Validators :[
        matchPasswords('loginPass','cLoginPass'),
        matchPasswords('transactionPass','cTransactioPass')
      ]
    }
  
    );
  }

  constructor(private http:HttpClient, private fb:FormBuilder, 
    private route:Router, private auth:AuthService, private dialog:MatDialog, private serv : ProfileservicService){}

  register(){

    if(this.registerForm.controls['transactionPass'].value === this.registerForm.controls['cTransactioPass'].value
      && this.registerForm.controls['loginPass'].value === this.registerForm.controls['cLoginPass'].value
    ){
      if(this.registerForm.valid){
      this.http.post('http://localhost:1234/abcbanking/register', this.registerForm.value, {responseType:"text"}).subscribe(
          (data)=>{
            if(data.includes("verification"))
              this.registered = data;
            }
        );
      } 
      
    }  else{
        this.dialogBoxpass();
      }
  }
  
  loginForm() {

    const { userId, password } = this.logForm.value;

    if(this.logForm.valid){
        this.http.post('http://localhost:1234/abcbanking/login',this.logForm.value).subscribe(
        (data)=>{
          console.warn(data);
          if(data){
            sessionStorage.setItem("email",userId);
            this.serv.getUserData().subscribe(
              (data1 : User) =>{
                this.user = data1;
                sessionStorage.setItem("accNum",this.user.accountNumber);
                sessionStorage.setItem("pin", this.user.transactionPwd);
                  console.log(sessionStorage.getItem('accNum'))

              }
            )
            
            this.route.navigate(['home']);
            this.auth.setLoggedIn(true);
          }
          else{
            this.loginError = "Uh, Sorry! Please check your credentials";
            this.dialogBox();
          }
        }
      )
    }
    
  }

  dialogBox(){
    console.warn("Pop Up Called");
    this.dialog.open(AlertDialogComponent, {
      width : '350px',
      position :{top:'15%', left:'40%'},
      data: { message: 'Please check your credentials' }
    });
  }

  dialogBoxpass(){
    console.warn("Pop Up Called");
    this.dialog.open(AlertDialogComponent, {
      width : '450px',
      position :{top:'15%', left:'40%'},
      data: { message: 'Please make sure that \n Confrim Transaction Password, Transaction Password must be same. \n Confrim Login Password, Login Password must be same.'}
    });
  }
}
