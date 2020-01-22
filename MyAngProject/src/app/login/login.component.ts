import { Component, OnInit } from '@angular/core';
import {AuthentificationService} from '../authentification.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private authService: AuthentificationService, private  router: Router) { }

  ngOnInit() {}
  onLogin(data) {
   this.authService.login(data)
     .subscribe(resp => {
       console.log(resp.headers.get('Authorization'));
       const jwt = resp.headers.get('Authorization');
       this.authService.saveToke(jwt);
       this.router.navigateByUrl('/');
     }, error1 => {
       console.log(error1);
     });
  }
  isAdmin() {
    return this.authService.isAdmin();
  }
  isUsrer() {
    return this.authService.isUser();
  }

}
