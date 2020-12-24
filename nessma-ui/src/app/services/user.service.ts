import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from "../models/User";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getAllUsers(){
    return this.http.get('/users');
  }

  signUp(user: User) {
      const url = '/register';
      return this.http.post(url, user);
  }
}
