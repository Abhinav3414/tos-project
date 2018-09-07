import { Injectable } from '@angular/core';
import { Token } from '../token';

@Injectable()
export class LocalStorageService {

  public setAuthorizationData(auth: Token): void {
    localStorage.setItem("Authorization", JSON.stringify(auth));
  }

  public getValueFromLocalStorage(): Token {
    let tokendate = JSON.parse(localStorage.getItem("Authorization"));
    return tokendate == null ? null : tokendate;
  }

  public setUserData(userData: any): void {
    localStorage.setItem("Userdata", JSON.stringify(userData));
  }

  public getUserDataValueFromLocalStorage(): any {
    let userdata = JSON.parse(localStorage.getItem("Userdata"));
    return userdata == null ? null : userdata;
  }

}
