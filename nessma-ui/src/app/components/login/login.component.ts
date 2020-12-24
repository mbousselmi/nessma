import { Component, OnInit } from '@angular/core';
import {AuthentificationService} from "../../services/authentification.service";
import { Router, ActivatedRoute } from '@angular/router';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private authentificationService: AuthentificationService) {}

  ngOnInit(): void {

    this.loginForm = this.formBuilder.group({

        email: ['', Validators.required],
        password: ['', Validators.required]

        });
  }

 get f() { return this.loginForm.controls; }

 onSubmit() {
    this.submitted = true;
     if (this.loginForm.invalid) {
         return;
     }
     this.loading = true;
     this.authentificationService.login(this.f.email.value, this.f.password.value)
         .pipe(first())
         .subscribe(
             data => {
                 this.router.navigate(['/']);
             },
             error => {
              return Observable.throw(error);
             });
 }
}
