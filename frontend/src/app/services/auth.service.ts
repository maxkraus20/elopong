import { Injectable } from '@angular/core';
import {Globals} from "../globals/globals";
import {HttpClient} from "@angular/common/http";
import {AuthRequestDto} from "../dtos/authRequestDto";
import {Observable} from "rxjs";
import {RegisterDto} from "../dtos/registerDto";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  readonly authBaseUri: string;

  constructor(
    private httpClient: HttpClient,
    private globals: Globals
  ) {
    this.authBaseUri = this.globals.backendUri + '/auth';
  }

  authenticateUser(authRequest:AuthRequestDto): Observable<AuthRequestDto> {
    console.log('Auth Request: ' + authRequest.email);
    return this.httpClient.post<AuthRequestDto>(
      this.authBaseUri + '/login',
      authRequest
    );
  }

  registerUser(registerRequest:RegisterDto): Observable<RegisterDto> {
    console.log('Register Request: ' + registerRequest.email);
    return this.httpClient.post<RegisterDto>(
      this.authBaseUri + '/register',
      registerRequest
    );
  }

}
