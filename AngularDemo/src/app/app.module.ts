import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule} from '@angular/common/http';

import { AppComponent } from './app.component';
import { UserRegistrationComponent } from './user-registration/user-registration.component';
import { UserLoginComponent } from './user-login/user-login.component';
import {RouterModule} from '@angular/router';
import {AppRoutingModule} from './app-routing.module';
import { CompleteRegistrationComponent } from './complete-registration/complete-registration.component';
import {UserRegistrationService} from './user-registration/user-registration.service';
import {UserLoginServiceService} from './user-login/user-login-service.service';
import {DatePipe} from '@angular/common';
import { LeaveRequestComponent } from './leave-request/leave-request.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UserPageComponent } from './user-page/user-page.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import {MatSidenavModule, MatSidenavContent, MatSidenav} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    UserRegistrationComponent,
    UserLoginComponent,
    CompleteRegistrationComponent,
    LeaveRequestComponent,
    HomePageComponent,
    UserPageComponent,
    NavigationBarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
    MatSidenavModule,
    BrowserAnimationsModule
  ],
  providers: [UserRegistrationService,
  UserLoginServiceService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
