import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rn_Forms_Setup } from 'src/app/models/Rn_Forms_Setup';
import { ApiRequestService } from 'src/app/services/api/api-request.service';
import { HttpClient } from "@angular/common/http";
@Injectable({
  providedIn: 'root'
})
export class FieldServiceService {
  baseUrl = "api/";

  getAllData() {
    var _http = this.baseUrl + "fields";
    return this.apiRequest.get(_http);
  }
  //private baseURL = 'api/field';




  constructor(
    private apiRequest: ApiRequestService,
  ) { }


  create(data) {
    console.log("In a service");
    console.log(data);
    var _http = this.baseUrl + "fields";
    return this.apiRequest.post(_http, data);
  }
}
