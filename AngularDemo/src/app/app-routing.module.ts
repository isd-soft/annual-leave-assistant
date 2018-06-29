import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent} from './user-login/user-login.component';
import {UserRegistrationComponent} from './user-registration/user-registration.component';
import {CompleteRegistrationComponent} from './complete-registration/complete-registration.component';
import {UserPageComponent} from './user-page/user-page.component';
import {HomePageComponent} from './home-page/home-page.component';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'registration', component: UserRegistrationComponent},
  {path: 'login', component: UserLoginComponent},
  {path: 'completeRegistration', component: CompleteRegistrationComponent},
  {path: 'profile-page', component: UserPageComponent},
  {path: 'home', component: HomePageComponent}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  declarations: [

  ],

  exports: [ RouterModule ]
})
export class AppRoutingModule { }
