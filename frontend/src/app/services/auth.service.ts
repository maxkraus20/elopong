import { Injectable } from '@angular/core';
import {Globals} from "../globals/globals";
import {HttpClient} from "@angular/common/http";
import {AuthRequestDto} from "../dtos/authRequestDto";
import {Observable} from "rxjs";
import {RegisterDto} from "../dtos/registerDto";
import jwt_decode, {jwtDecode} from 'jwt-decode';

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

  isLoggedIn() {
    return localStorage.getItem('token')!=null && !this.isExpired();
  }

  isExpired() {
    return this.getExpirationDate().valueOf() < new Date().valueOf();
  }

  logoutUser() {
    localStorage.removeItem('token');
  }

  getToken() {
    return localStorage.getItem('token') || '';
  }

  getEmail() {
    const decoded: any = jwtDecode(this.getToken());
    return decoded.sub;
  }

  getRole() {
    if (this.getToken()!= null) {
      const decoded: any = jwtDecode(this.getToken());
      const authInfo: string[] = decoded.rol;
      if (authInfo.includes('ADMIN')) {
        return 'ADMIN'
      } else if (authInfo.includes('USER')) {
        return 'USER';
      }
    }
    return 'UNDEFINED';
  }

  getExpirationDate(): Date {
    const decoded: any = jwtDecode(this.getToken());
    if (decoded.exp === undefined) {
      return new Date();
    }
    const date = new Date(0);
    date.setUTCSeconds(decoded.exp);
    return date;
  }

}
