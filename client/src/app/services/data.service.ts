import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { UtilityService } from './utility.service';
import { LocalStorageService } from './localStorage.service';
import { UrlService } from './url.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Accept': 'application/json'
  })
};

@Injectable()
export class DataService {
  token: any;
  baseResourceURL: string;
  baseResourceUserUrl: string;
  httpOptions : any

  constructor(private httpClient: HttpClient, private localStorageService: LocalStorageService,
    private utilityService: UtilityService, private urlService: UrlService) {
  //  let headers = new HttpHeaders().set('Content-Type', 'application/json')
  //    .set('Accept', 'application/json');
      this.baseResourceURL = this.urlService.getBaseResourceUrl();
      this.baseResourceUserUrl = this.urlService.getBaseResourceUserUrl();
  }

  getToken() {
    if (this.localStorageService.getValueFromLocalStorage() !== null)
      return this.token = '?access_token=' + this.localStorageService.getValueFromLocalStorage().access_token;
  }

  getEntityData(entityName: string, id: number): Promise<any> {
    return this.httpClient.get<any>(this.baseResourceURL + entityName + "/" + id + this.getToken()).toPromise()
      .then((response: Response) => response);
  }

  getEntityAllData(entityName: any): Promise<any> {
    return this.httpClient.get<any>(this.baseResourceURL + entityName + "/" + this.getToken()).toPromise()
      .then((response: Response) => response);
  }

  getUsers(): Promise<any> {
    return this.httpClient.get<any>(this.urlService.getUserUrl() + "users/" + this.getToken()).toPromise()
      .then((response: Response) => response);
  }

  updateUserRoles(entityName: string, id: number, entity: any) {
    return this.httpClient.put(this.baseResourceUserUrl + entityName + "/" + id + "/roles" +this.getToken(), entity).toPromise()
      .then((response: Response) => response);
  }

  postEntity(entityName: string, entity: any) {
    return this.httpClient.post(this.baseResourceURL + entityName + "/" + this.getToken(), entity).toPromise()
      .then((response: Response) => response);
  }

  updateEntity(entityName: string, id: number, entity: any) {
    return this.httpClient.put(this.baseResourceURL + entityName + "/" + id + this.getToken(), entity).toPromise()
      .then((response: Response) => response);
  }

  delelteEntity(entityName: string, id: number) {
    return this.httpClient.delete(this.baseResourceURL + entityName + "/" + id + this.getToken()).toPromise()
      .then((response: Response) => response);
  }

  delelteUserEntity(entityName: string, id: number) {
    return this.httpClient.delete(this.urlService.getBaseResourceUserUrl() + entityName + "/" + id + this.getToken()).toPromise()
      .then((response: Response) => response);
  }

  postUserEntity(entityName: string, entity: any) {
    return this.httpClient.post(this.urlService.getBaseResourceUserUrl() + entityName + "/", entity).toPromise()
      .then((response: Response) => response);
  }

}
