import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegistserLoginComponent } from './registser-login/registser-login.component';
import { AboutComponent } from './about/about.component';
import { ProfileComponent } from './profile/profile.component';
import { NewAccountComponent } from './new-account/new-account.component';
import { TransactionComponent } from './transaction/transaction.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  {path:"home",component:HomeComponent},
  {path:"login-register",component:RegistserLoginComponent},
  {path:"about",component:AboutComponent},
  {path:"profile", component:ProfileComponent},
  {path:"new-acc", component: NewAccountComponent},
  {path:"transaction", component:TransactionComponent},
  {path:"dash", component : DashboardComponent},
  {path:"**", pathMatch:"full", redirectTo:"home"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
 