import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiRequestService } from './api-request.service';


@Injectable({
  providedIn: 'root'
})
export class AuthorserviceService {

  private baseURL = 'api/author';

  /*   let headers = new HttpHeaders();
    headers = headers.set('h1', 'v1').set('h2','v2'); */
   

    constructor(
        private apiRequest: ApiRequestService
    ) {}

    getAll(page?:number, size?:number): Observable<any> {
        //Create Request URL params
        let me = this;
        let params: HttpParams = new HttpParams();
        params = params.append('page', typeof page === "number"? page.toString():"0");
        params = params.append('size', typeof size === "number"? size.toString():"1000");
        // get all
        // return this.apiRequest.get('api/instructors');
        // paginated data
        return this.apiRequest.get(this.baseURL, params);
        
    }

    getById(id: number) :Observable<any> {
        const _http = this.baseURL + '/' + id;
        return this.apiRequest.get(_http);
        
    }

    create(teacher: any) :Observable<any> {
        //`${this.baseURL}`
        return this.apiRequest.post(this.baseURL, teacher);
    }

    update(id: number, teacher: any) :Observable<any> {
        const _http = this.baseURL + '/' + id;
        return this.apiRequest.put(_http, teacher);
    }


    buttonmethod(methodname:string):Observable<any>{
        let url=methodname+"_method";
        console.log(url);
    
        return this.apiRequest.get(url);
      }
}
