import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {
host2 = 'http://localhost:8084';
  public host = 'http://localhost:8086';
jwt: string;
username: string;
roles: Array<string>;
  constructor(private http: HttpClient) { }
  login(data) {
      return this.http.post(this.host2 + '/login', data, { observe: 'response'});
  }

  saveToke(jwt: string) {
    localStorage.setItem('token', jwt);
    this.jwt = jwt;
    this.parseJWT();
  }
  parseJWT() {
  const jwtHelper = new JwtHelperService();
  const objJWT = jwtHelper.decodeToken(this.jwt);
  this.username = objJWT.obj;
  this.roles = objJWT.roles;
  }
  isAdmin() {
    return this.roles.indexOf('ADMIN') >= 0;
}
  isUser() {
    return this.roles.indexOf('USER') >= 0;
  }
  isAuthenticated() {
    return this.roles && (this.isAdmin() || this.isUser());
  }

  loadToken() {
    this.jwt = localStorage.getItem('token');
    this.parseJWT();}

  logOut() {
    localStorage.removeItem('token');
    this.initParams();}
  initParams() {
    this.jwt = undefined;
    this.username = undefined;
    this.roles = undefined;}
  public getCategories() {
    if (this.jwt == null) { this.loadToken(); }
    return this.http.get(this.host + '/categories', { headers: new
      HttpHeaders({ 'authorization' :'Bearer ' + this.jwt})});}

getRessource(url) {
  if (this.jwt == null) { this.loadToken(); }
  return this.http.get(url, { headers: new
    HttpHeaders({ "authorization" :"Bearer " + this.jwt})});
  }
  deleteRessource(url: any) {
    if (this.jwt == null) { this.loadToken(); }
    return this.http.delete(url, { headers: new
      HttpHeaders({ "authorization" :"Bearer " + this.jwt})});
  }
}

