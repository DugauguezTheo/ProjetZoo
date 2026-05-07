// auth.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequest } from '../dto/auth-request';
import { AuthResponse } from '../dto/auth-response';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private _token: string = sessionStorage.getItem("token") ?? "";
  private _role: string = sessionStorage.getItem("role") ?? "";
  private _login : string = sessionStorage.getItem("login") ?? "";

  constructor(private http: HttpClient) { }

  // TOKEN
  public get token(): string {
    return this._token;
  }

  public set token(value: string) {
    this._token = value;
    sessionStorage.setItem("token", value);
  }

  // ROLE
  public get role(): string {
    return this._role;
  }

  public set role(value: string) {
    this._role = value;
    sessionStorage.setItem("role", value);
  }

  // LOGIN
  public get login(): string {
    return this._login;
  }

  public set login(value: string) {
    this._login = value;
    sessionStorage.setItem("login", value);
  }

  // AUTH
  public auth(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>("/auth", authRequest);
  }

  public isLogged(): boolean {
    return !!this._token;
  }

  public isAdmin(): boolean {
    return this._role === "ROLE_ADMIN";
  }

  public logout(): void {
    this._token = "";
    this._role = "";
    this._login = "";

    sessionStorage.removeItem("token");
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("login");
  }
}
