import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rn_Forms_Setup } from 'src/app/models/Rn_Forms_Setup';
import { ApiRequestService } from 'src/app/services/api/api-request.service';

@Injectable({
  providedIn: 'root'
})
export class ServicedepartmentService {


  updateData(id: number, data) {
    let url = this.baseUrl + "/" + id;
    console.log(data.value);
    //const body = JSON.stringify(data);
    return this.apiRequest.put(url, data);
    //this.route.navigate(['/home/sales-new/all']);
  }

  baseUrl = "api/department";

  constructor(private apiRequest: ApiRequestService) { }

  getAllData() {
    return this.apiRequest.get(this.baseUrl);
  }

  create(data) {
    console.log("In a service");
    console.log(data);

    return this.apiRequest.post(this.baseUrl, data)
  }

  getDataById(id: number) {
    let url = this.baseUrl + "/" + id;
    console.log(url)
    return this.apiRequest.get(url);
  }

}
