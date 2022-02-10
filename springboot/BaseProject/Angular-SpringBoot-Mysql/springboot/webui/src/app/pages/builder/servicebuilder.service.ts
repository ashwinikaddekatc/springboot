import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiRequestService } from 'src/app/services/api/api-request.service';

@Injectable({
  providedIn: 'root'
})
export class ServicebuilderService {
  private buildDynamicFormURL = 'api/dynamic_form_build';


  baseUrl = "api/dynamic_transaction_line/"
  constructor(
    private apiRequest: ApiRequestService,


  ) { }


  getAllData() {
    var _http = this.baseUrl + "all";
    return this.apiRequest.get(_http);
  }

  create(data) {
    console.log("In a service");
    console.log(data);

    return this.apiRequest.post(this.baseUrl, data)
  }
  getDataById(id: number, form_id: number) {
    let url = this.baseUrl + id + "?form_id=" + form_id;
    console.log(url)
    return this.apiRequest.get(url);
  }
  updateData(id: number, form_id: number, data) {
    let url = this.baseUrl + id + "?form_id=" + form_id;
    console.log(data.value);
    //const body = JSON.stringify(data);
    return this.apiRequest.put(url, data);
    //this.route.navigate(['/home/sales-new/all']);
  }


  // //dynamic form
  // buildDynamicForm(form_id: any) {
  //   let params: HttpParams = new HttpParams();
  //   params = params.append('form_id', form_id.toString());
  //   return this.apiRequest.get(this.buildDynamicFormURL, params);
  // }

  buildDynamicForm(form_id?: number): Observable<any> {
    let params: HttpParams = new HttpParams();
    params = params.append('form_id', form_id.toString());
    return this.apiRequest.get(this.buildDynamicFormURL, params);

  }
}

