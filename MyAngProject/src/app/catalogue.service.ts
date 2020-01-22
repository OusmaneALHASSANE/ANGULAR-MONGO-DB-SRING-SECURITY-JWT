import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthentificationService} from './authentification.service';

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {

  public host = 'http://localhost:8086';

  constructor(private http: HttpClient, private authService: AuthentificationService) {}
  getAllCategories() {return this.authService.getCategories();
  }
  getRessource(url) {return this.authService.getRessource(url);
  }
  deleteRessource(url) {return this.authService.deleteRessource(url);
  }
  postRessource(url, data) {
    const headers=new  HttpHeaders({"authorization": "Bearer "+this.authService.jwt});
    return this.http.post(url, data,{ headers : headers});
  }
  putRessource(url: string, data: any) {
    const headers=new  HttpHeaders({"authorization": "Bearer "+this.authService.jwt});
    return this.http.put(url, data,{ headers : headers});
  }
  patchRessource(url: string, data: any) {
    const headers=new  HttpHeaders({"authorization": "Bearer "+this.authService.jwt});
    return this.http.patch(url, data,{ headers : headers});
  }
}







