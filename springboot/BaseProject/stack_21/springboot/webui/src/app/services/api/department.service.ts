import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { ApiRequestService } from './api-request.service';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseURL = 'api/';
  constructor(private apiRequest: ApiRequestService) { }

  getAll(page?: number, size?: number): Observable<any> {
    console.log("calling get department ");

    //Create Request URL params
    let me = this;
    let params: HttpParams = new HttpParams();
    params = params.append('page', typeof page === "number" ? page.toString() : "0");
    params = params.append('size', typeof size === "number" ? size.toString() : "1000");
    // get all
    // return this.apiRequest.get('api/instructors');
    // paginated data
    return this.apiRequest.get(this.baseURL+"getdept", params);

  }


    getDataById(id: number) {
    let url = this.baseURL+"getdept/"+ id;
    console.log(url)
    return this.apiRequest.get(url);
  }

  create(department): Observable<any> {
    console.log("in the department service "+department);
    
    const _http = this.baseURL + "savedept";
    return this.apiRequest.post(_http, department)
   
  }

  update(id: number, depart): Observable<any> {
    const _http = this.baseURL+"update/"+id;
    return this.apiRequest.put(_http, depart);
  }

}
