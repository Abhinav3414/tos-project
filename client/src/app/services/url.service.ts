import { Injectable } from '@angular/core';

@Injectable()
export class UrlService {
  tokenApiUrl = '/tos-app/oauth/token';
  appBaseUrl = '/tos-app/index.html';
  baseResourceUrl = '/tos-app/tos/';
  fileUrl = '/tos-app/tosfiles/'
  baseResourceUserUrl = '/tos-app/tosuser/';
  propertyUrl = '/properties/uri/';  // Not used for now
  usersUrl = '/tos-app/tosuser/'

  baseUrl: string;

  constructor() {
    this.baseUrl = (location.origin === 'http://localhost:4200') ? 'http://localhost:8080' : location.origin;
  }

  getTokenApiUrl() {
    return this.baseUrl + this.tokenApiUrl;
  }

  getUserUrl() {
    return this.baseUrl + this.usersUrl;
  }

  getAppBaseUrl() {
    return this.baseUrl + this.appBaseUrl;
  }

  getBaseResourceUrl() {
    return this.baseUrl + this.baseResourceUrl;
  }

  getBaseResourceUserUrl() {
    return this.baseUrl + this.baseResourceUserUrl;
  }

  getFileUrl() {
    return this.baseUrl + this.fileUrl;
  }

}
