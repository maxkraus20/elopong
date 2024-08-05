import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators, FormsModule} from "@angular/forms";
import {MatCard, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatFormField, MatInput} from "@angular/material/input";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {FlexModule} from "@angular/flex-layout";
import {MatToolbar} from "@angular/material/toolbar";
import {MatButton, MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    MatCard,
    MatInput,
    MatFormField,
    FlexModule,
    MatToolbar,
    MatButton,
    MatCardContent,
    MatCardTitle,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconButton,
    MatIcon
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide: boolean = true;
  constructor() {
  }

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(265)])
      }
    );
  }

  onSubmit() {
    console.log("login")
  }

}
