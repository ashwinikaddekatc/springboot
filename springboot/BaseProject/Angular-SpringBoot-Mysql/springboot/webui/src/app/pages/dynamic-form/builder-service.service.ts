import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DynamicForm } from 'src/app/models/DynamicForm';
import { ApiRequestService } from 'src/app/services/api/api-request.service';

@Injectable({
  providedIn: 'root'
})
export class BuilderServiceService {
  private baseURL = 'api/dynamic_form_build';

  myUrl = "api/dynamic_transaction_line/"

  constructor(
    private apirequest: ApiRequestService,

  ) { }


  save(data) {
    return this.apirequest.post(this.myUrl, data);
  }
  create(data) {
    return this.apirequest.post(this.baseURL, data);
  }

}
