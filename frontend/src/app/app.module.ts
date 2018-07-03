import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router'
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { LoginGuard } from './guards/login.guard';
import { AuthGuard } from './guards/auth.guard';
import { TopbarComponent } from './topbar/topbar.component';
import { RegisterComponent } from './register/register.component';
import { UserPageComponent } from './user-page/user-page.component';
import { Interceptors } from "./models/interceptors";
import { LeaveRequestTypesComponent } from './leave-request-types/leave-request-types.component';
import { CreateLeaveRequestComponent } from './create-leave-request/create-leave-request.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    TopbarComponent,
    RegisterComponent,
    UserPageComponent,
    LeaveRequestTypesComponent,
    CreateLeaveRequestComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: '',
        canActivate: [AuthGuard],
        component: HomeComponent
      },
      {
        path: 'login',
        canActivate: [LoginGuard],
        component: LoginComponent
      },
      {
        path: 'register',
        canActivate: [LoginGuard],
        component: RegisterComponent
      },
      {
        path: 'user-page',
        canActivate: [AuthGuard],
        component: UserPageComponent
      },
      {
        path: 'create-leave-request',
        canActivate: [AuthGuard],
        component: CreateLeaveRequestComponent
      }
    ])
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: Interceptors, multi:true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
