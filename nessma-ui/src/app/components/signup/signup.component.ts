import { Component, OnInit } from '@angular/core';
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  userForm : FormGroup;
  private router: Router

  constructor(private formBuilder: FormBuilder ,private userService:UserService) {}

  ngOnInit(): void {

    this.userForm = this.formBuilder.group({

        name: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        address: ['', [Validators.required]],
        phone: ['', [Validators.required, Validators.maxLength(10),Validators.pattern(new RegExp("^[0-9]*$"))]],
        password: ['', [Validators.required, Validators.minLength(6)]]

        });
  }
  // convenience getter for easy access to form fields
  get f() { return this.userForm.controls; }
  onReset() {
        this.userForm.reset();
    }

  onSubmit() {
        // stop here if form is invalid
        if (this.userForm.invalid) {
            return ;
        }
      this.userService.signUp(this.userForm.value).subscribe(
      u => {
        this.userForm.reset();
        this.router.navigate(['/login']);
        return true;
    },
        error => {
          return Observable.throw(error);
        });
  }
}
