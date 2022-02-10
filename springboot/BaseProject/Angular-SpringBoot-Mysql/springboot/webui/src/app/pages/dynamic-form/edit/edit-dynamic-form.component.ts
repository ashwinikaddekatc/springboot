import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DynamicForm } from 'src/app/models/DynamicForm';
import { DynamicTransactionService } from 'src/app/services/api/dynamic-transaction.service';
import { FormSetupService } from 'src/app/services/api/form-setup.service';

@Component({
  selector: 'app-edit-dynamic-form',
  templateUrl: './edit-dynamic-form.component.html',
  styleUrls: ['./edit-dynamic-form.component.scss'],
})
export class EditDynamicFormComponent implements OnInit {
  updated = false;
  dynamicForm: DynamicForm;
  header_id;

  id: number;
  form_id: number;
  //uiData: any[];
  form_name: string;
  componentsData: any[];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formSetupService: FormSetupService,
    private dynamicTransactionService: DynamicTransactionService
  ) { }


  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const userId = params['form_id'];
      this.form_id = userId;
      console.log(userId);
    });
    this.dynamicForm = new DynamicForm();
    this.id = this.route.snapshot.params["id"];
    console.log("update with id = ", this.id);
    this.getComponentsData(this.form_id);
    this.getByIdAndFormId(this.id, this.form_id);
  }

  // FOR UI
  getComponentsData(id: number) {
    this.formSetupService.getById(id).subscribe(data => {
      console.log('edit-dynamic-form components data: ', data);
      this.componentsData = data.components;
      this.form_name = data.form_name;
    }, err => console.log(err)
    );


    // onSubmit() {
    //   this.header_id = this.route.snapshot.params['id'];

    //   console.log(this.componentsData);
    //   this.dynamicTransactionService.updateData(this.header_id, this.department).subscribe(data => {
    //     console.log(data);
    //     this.routing.navigate(['/home/departments/alldepartments']);
    //   })
    // }

  }

  // GET THE DATA
  getByIdAndFormId(id: number, form_id: number) {
    this.dynamicTransactionService.getByIdAndFormId(id, form_id).subscribe((data) => {
      console.log(data);
      this.dynamicForm = data;
      /* // working yes
       const keys = this.componentsData.map((item, i) => {
        const container = { label: '', type: '', value: ''};
        container.label = item.label;
        container.type = item.type;
        container.value = this.dynamicForm[item.mapping];
          return container;
        });
      console.log(keys);
      this.uiData = keys; */
    }, err => {
      return console.log(err);
    }
    );
  }

  // UPDATE THE DATA
  update() {
    this.dynamicTransactionService.updateByIdAndFormId(this.id, this.form_id, this.dynamicForm).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(["../../all"], { relativeTo: this.route, queryParams: { form_id: this.form_id } });
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
    this.dynamicForm = new DynamicForm();
  }

  onSubmit() {
    this.updated = true;
    this.update();
  }

  back() {
    this.router.navigate(["../../all"], { relativeTo: this.route, queryParams: { form_id: this.form_id } });
  }

}
