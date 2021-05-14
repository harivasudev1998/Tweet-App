import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/core/services/api.service';
import { User } from '../model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  isValidRegistration: boolean = false;
  isValidRegistrationServer: boolean = false;
  loggedInUser: User = <User>{};
  firstNameFlag: boolean = false;
  lastNameFlag: boolean = false;
  emailFlag: boolean = false;
  loginIDFlag: boolean = false;
  passwordFlag: boolean = false;
  confirmPasswordFlag: boolean = false;
  contactNumberFlag: boolean = false;
  genderFlag: boolean = false;

  constructor(
    private _apiService: ApiService,
    public formBuilder: FormBuilder,
    private _router: Router
  ) {}
  signup_form = this.formBuilder.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    email: [
      '',
      [
        Validators.required,
        Validators.pattern('[a-zA-Z0-9]+.[a-zA-Z0-9]+@+[a-zA-Z]+.com'),
      ],
    ],
    loginID: ['', Validators.required],
    password: [
      '',
      [Validators.required, Validators.pattern('[a-zA-Z0-9!#$%&@[]{8,}$')],
    ],
    confirmPassword: [
      '',
      [Validators.required, this.matchConfirmPassword.bind(this)],
    ],
    contactNumber: [
      '',
      [Validators.required, Validators.pattern('^[1-9]{1}[0-9]{9}$')],
    ],
    gender: ['', Validators.required],
  });

  ngOnInit(): void {}

  register(): void {
    let registrationUser: User = this.signup_form.value;
    this._apiService.register(registrationUser).subscribe(
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
      (err: HttpErrorResponse) => {
        if (err.status === 500) {
          this.isValidRegistrationServer = true;
        } else {
          this.isValidRegistration = true;
        }
      }
    );
  }

  matchConfirmPassword(formControl: FormControl): { [s: string]: boolean } {
    if (this.signup_form) {
      if (
        formControl.value &&
        formControl.value.length > 0 &&
        formControl.value !== this.signup_form.get('password').value
      ) {
        return { nomatch: true };
      }
    }
    return null;
  }
}
