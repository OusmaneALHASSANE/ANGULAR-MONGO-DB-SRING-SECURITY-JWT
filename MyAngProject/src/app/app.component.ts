import {Component, OnInit} from '@angular/core';
import {AuthentificationService} from './authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'MyAngProject';


  constructor(private  authService: AuthentificationService) {


  }

  isAdmin() {
    return this.authService.isAdmin();
  }
  isUsrer() {
    return this.authService.isUser();
  }
  isAuthenticated() {
    return this.authService.isAuthenticated();
  }
onlogOut() {
    return this.authService.logOut();
}
  ngOnInit(): void {
    this.authService.loadToken();
  }
}
