import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {NgIf} from "@angular/common";
import {RegisterDto} from "../../../dtos/registerDto";
import {MatIcon} from "@angular/material/icon";
import {AuthService} from "../../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
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
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm: FormGroup;
  hidePassword: boolean = true;

  registerRequest: RegisterDto = {
    email: '',
    inGameName: '',
    firstName: '',
    lastName: '',
    password: ''
  };

  responseData: any;

  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router

  ) {
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: [null, [Validators.required, Validators.maxLength(64)]],
      lastName: [null, [Validators.required, Validators.maxLength(64)]],
      email: [null, [Validators.required, Validators.pattern(this.emailRegx)]],
      password: [null, [Validators.required, Validators.minLength(8), Validators.maxLength(64)]],
      inGameName: [null, [Validators.required, Validators.maxLength(64)]],
    });
  }

  submit() {
    if (this.registerForm.valid) {
      const formValues = this.registerForm.value;
      this.registerRequest.email = formValues.email;
      this.registerRequest.inGameName = formValues.inGameName;
      this.registerRequest.firstName = formValues.firstName;
      this.registerRequest.lastName = formValues.lastName;
      this.registerRequest.password = formValues.password;
      this.authService.registerUser(this.registerRequest).subscribe({
        next: value => {
          this.responseData = value;
          this.router.navigate(['/login']);
        }
      })
    }
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
}
