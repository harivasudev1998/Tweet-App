import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/core/services/api.service';
import { LoginUser } from '../model/login-user';
import { User } from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: LoginUser = <LoginUser>{};
  inValidLogin: boolean = false;
  isValidServer:boolean = false;

  loggedInUser: User = <User>{};
  constructor(
    private _apiService: ApiService,
    public formBuilder: FormBuilder,
    private _router: Router
  ) {}
  login_form = this.formBuilder.group({
    userName: ['', Validators.required],
    password: ['', Validators.required],
  });
  ngOnInit(): void {}

  login() {
    this.user.loginID = this.login_form.value['userName'];
    this.user.password = this.login_form.value['password'];

    this._apiService.login(this.user).subscribe(
      (response) => {
        if (response != null) {
          this.loggedInUser = response;
          sessionStorage.setItem(
            'loggedInUser',
            JSON.stringify(this.loggedInUser)
          );
          sessionStorage.setItem('userName', this.loggedInUser.loginID);
          this._router.navigateByUrl('home');
        }
      },
      (err) => {
        if (err.status === 500) {
          this.isValidServer = true;
        } else {
          this.inValidLogin = true;
        }
       
        this.login_form.controls['password'].setValue('');
      }
    );
  }
}
