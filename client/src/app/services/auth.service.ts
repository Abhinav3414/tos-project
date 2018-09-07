import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpModule, Http, Headers } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { Token } from '../token';
import { UrlService } from '../services/url.service';
import { LocalStorageService } from './localStorage.service';

import 'rxjs/add/observable/of';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {
  AccessToken: string = "";
  authCode: string;
  grantType: string;
  token: any;

  constructor(private http: Http, private localStorageService: LocalStorageService, private urlService: UrlService) {
  }

  getToken() {
    if (this.localStorageService.getValueFromLocalStorage() !== null)
      return this.token = '?access_token=' + this.localStorageService.getValueFromLocalStorage().access_token;
  }

  login(usercreds): Promise<Token> {
    this.authCode = "Basic VFNDTUlULTU5RDY5NDhEUzgyOEYyMzRLSkhKS0g5QzU4NEM3NDlDNzU6OThENDczNjA4REQzMUNERjRBQTczNjA4MDVFNDgzRDQ3MzYw";
    this.grantType = "password";
    // Authorization code is :
    // Basic VFNDTUlULTU5RDY5NDhEUzgyOEYyMzRLSkhKS0g5QzU4NEM3NDlDNzU6OThENDczNjA4REQzMUNERjRBQTczNjA4MDVFNDgzRDQ3MzYw
    let headersForTokenAPI = new Headers();
    headersForTokenAPI.append("Content-Type", "application/x-www-form-urlencoded");
    headersForTokenAPI.append("Authorization", this.authCode);

    var data = "?grant_type=" + this.grantType + "&username=" + usercreds.username + "&password=" + usercreds.password;

    return this.http.post(this.urlService.getTokenApiUrl() + data, null, { headers: headersForTokenAPI })
      .map(res => {
        this.AccessToken = res.json().access_token;
        return res.json();
      }).toPromise();
  }

  getCurrentUser(username: String) {
    return this.http.get(this.urlService.getBaseResourceUserUrl() + "currentuser/" + username + this.getToken())
    .map(res => res.json() ).toPromise();

  }

}
