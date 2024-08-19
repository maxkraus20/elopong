import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {NgIf} from "@angular/common";
import {RegisterDto} from "../../../dtos/registerDto";
import {MatIcon} from "@angular/material/icon";

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

  registerDto: RegisterDto = {
    email: '',
    inGameName: '',
    firstName: '',
    lastName: '',
    password: ''
  };

  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  constructor(
    private formBuilder: FormBuilder
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

      this.registerDto.email = formValues.email;
      this.registerDto.inGameName = formValues.inGameName;
      this.registerDto.firstName = formValues.firstName;
      this.registerDto.lastName = formValues.lastName;
      this.registerDto.password = formValues.password;

      console.log(this.registerDto);
    } else {
      console.log(this.registerForm.errors);
      console.log('Form is not valid')
    }
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
}
