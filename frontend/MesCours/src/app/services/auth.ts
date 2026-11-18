import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiURL: string = 'http://localhost:8082/users';
  token!: string;

  private helper = new JwtHelperService();

  public loggedUser!: string;
  public isloggedIn: Boolean = false;
  public roles!: string[];
  public registeredUser : User = new User();


  constructor(private router: Router, private http: HttpClient) {}




  login(user: User) {
    return this.http.post<User>(this.apiURL + '/login', user, { observe: 'response' });
  }

  
  saveToken(jwt: string) {
    // le microservice renvoie "Bearer eyJ..." : on enlève le préfixe
    if (jwt.startsWith('Bearer '))
      jwt = jwt.substring(7);

    localStorage.setItem('jwt', jwt);
    this.token = jwt;
    this.isloggedIn = true;
    this.decodeJWT();
  }

  
  decodeJWT() {
    if (this.token == undefined)
      return;
    const decodedToken = this.helper.decodeToken(this.token);
    this.roles = decodedToken.roles;
    this.loggedUser = decodedToken.sub;
  }

  
  loadToken() {
    this.token = localStorage.getItem('jwt')!;
    this.decodeJWT();
  }

  getToken(): string {
    return this.token;
  }

  isAdmin(): Boolean {
    if (!this.roles)
      return false;
    return this.roles.indexOf('ADMIN') >= 0;
  }


  logout() {
    this.loggedUser = undefined!;
    this.roles = undefined!;
    this.token = undefined!;
    this.isloggedIn = false;
    localStorage.removeItem('jwt');
    this.router.navigate(['/login']);
  }


  isTokenExpired(): Boolean {
    return this.helper.isTokenExpired(this.token);
  }

  registerUser(user: User) {
    return this.http.post<User>(this.apiURL + '/register', user, { observe: 'response' });
  }


  setRegisteredUser(user: User) {
  this.registeredUser = user;
}

getRegisteredUser() {
  return this.registeredUser;
}


validateEmail(code: string) {
  return this.http.get<User>(this.apiURL + '/verifyEmail/' + code);
}
}