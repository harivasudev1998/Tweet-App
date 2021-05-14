import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/core/services/api.service';
import { LoginUser } from '../model/login-user';
import { User } from '../model/user';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
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
    password: [
      '',
      [Validators.required, Validators.pattern('[a-zA-Z0-9!#$%&@[]{8,}$')],
    ],
    confirmPassword: [
      '',
      [Validators.required, this.matchConfirmPassword.bind(this)],
    ],
  });
  ngOnInit(): void {}

  reset() {
    this.user.loginID = this.login_form.value['userName'];
    this.user.password = this.login_form.value['password'];

    this._apiService.reset(this.user).subscribe(
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
        this.login_form.controls['confirmPassword'].setValue('');
      }
    );
  }
  matchConfirmPassword(formControl: FormControl): { [s: string]: boolean } {
    if (this.login_form) {
      if (
        formControl.value &&
        formControl.value.length > 0 &&
        formControl.value !== this.login_form.get('password').value
      ) {
        return { nomatch: true };
      }
    }
    return null;
  }
}
