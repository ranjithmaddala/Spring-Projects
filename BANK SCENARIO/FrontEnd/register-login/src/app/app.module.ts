import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistserLoginComponent } from './registser-login/registser-login.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ProfileComponent } from './profile/profile.component';
import { NewAccountComponent } from './new-account/new-account.component';
import { AlertDialogComponent } from './alert-dialog/alert-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import { TransactionComponent } from './transaction/transaction.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { TransactionCodeDialogComponent } from './transaction-code-dialog/transaction-code-dialog.component'

@NgModule({
  declarations: [
    AppComponent,
    RegistserLoginComponent,
    HomeComponent,
    AboutComponent,
    ProfileComponent,
    NewAccountComponent,
    AlertDialogComponent,
    TransactionComponent,
    DashboardComponent,
    TransactionCodeDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
