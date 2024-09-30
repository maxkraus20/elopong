import {Component, OnInit} from '@angular/core';
import {FormGroup, Validators, FormBuilder} from "@angular/forms";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from "@angular/material/button";
import { ReactiveFormsModule } from '@angular/forms';
import {MatCheckboxModule} from "@angular/material/checkbox";
import {NgIf} from "@angular/common";
import {AuthService} from "../../../services/auth.service";
import {AuthRequestDto} from "../../../dtos/authRequestDto";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    NgIf,
    MatIcon,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;

  authRequest: AuthRequestDto = {
    email: '',
    password: ''
  }
  responseData: any;

  hidePassword: boolean = true;
  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
    localStorage.removeItem('token');
    this.loginForm = this.formBuilder.group({
      email: [null, [Validators.required, Validators.pattern(this.emailRegx)]],
      password: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(64)]]
    });
  }

  submit() {
    if (this.loginForm.valid) {
      const formValues = this.loginForm.value;
      this.authRequest.email = formValues.email;
      this.authRequest.password = formValues.password;
      this.authService.authenticateUser(this.authRequest).subscribe({
        next: value => {
          this.responseData = value;
          localStorage.setItem('token', this.responseData.token);
        }, error: err => {
          console.log(err);
        }
      });
    } else {
      console.log(this.loginForm.errors);
      console.log('Form is not valid');
    }
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

}
