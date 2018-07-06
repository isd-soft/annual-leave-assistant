import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';

import { DataSource , CdkTableModule} from '@angular/cdk/table';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {LoginGuard} from './guards/login.guard';
import {AuthGuard} from './guards/auth.guard';
import {AdminGuard} from './guards/admin.guard';
import {RegisterComponent} from './register/register.component';
import {UserPageComponent} from './user-page/user-page.component';
import {Interceptors} from './models/interceptors';
import {LeaveRequestTypesComponent} from './leave-request-types/leave-request-types.component';
import {SidebarComponent} from './sidebar/sidebar.component';
import {UsersComponent} from './users/users.component';
import {AddUserComponent} from './users/add-user/add-user.component';
import {ListUserComponent} from './users/list-user/list-user.component';
import {EditUserComponent} from './users/edit-user/edit-user.component';
import {CreateLeaveRequestComponent} from './leave-request/create-leave-request/create-leave-request.component';
import {ListLeaveRequestComponent} from './leave-request/list-leave-request/list-leave-request.component';
import { CreateComponent } from './leave-request-types/create/create.component';
import { EditComponent } from './leave-request-types/edit/edit.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SidebarComponent,
    UsersComponent,
    HomeComponent,
    RegisterComponent,
    CreateLeaveRequestComponent,
    ListLeaveRequestComponent,
    UserPageComponent,
    LeaveRequestTypesComponent,
    //  AddUserComponent,
    ListUserComponent,
    EditUserComponent,
    CreateComponent,
    EditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path: 'leaveRequestTypes',
        canActivate: [AuthGuard],
        component: LeaveRequestTypesComponent
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
        path: 'users',
        canActivate: [AdminGuard],
        component: ListUserComponent
      },
      {
        path: 'edit-user',
        canActivate: [AdminGuard],
        component: EditUserComponent
      },
      {
        path: 'leaveRequests',
        canActivate: [AuthGuard],
        component: ListLeaveRequestComponent
      },
      {
        path: 'create-leave-request',
        canActivate: [AuthGuard],
        component: CreateLeaveRequestComponent
      }
    ])
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: Interceptors, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
