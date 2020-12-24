import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './services/user.service';
import { AdminComponent } from './components/admin/admin.component';
import { SignupComponent } from './components/signup/signup.component';
import { FormsModule ,ReactiveFormsModule} from '@angular/forms';
import { NavigationComponent } from './components/navigation/navigation.component';
import { LoginComponent } from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    SignupComponent,
    NavigationComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],

  providers: [UserService],
  bootstrap: [

  ]
})
export class AppModule { }
