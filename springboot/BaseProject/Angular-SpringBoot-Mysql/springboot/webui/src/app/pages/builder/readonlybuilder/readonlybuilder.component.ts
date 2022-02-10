import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { builder } from '../builder';
import { ServicebuilderService } from '../servicebuilder.service';

@Component({
  selector: 'app-readonlybuilder',
  templateUrl: './readonlybuilder.component.html',
  styleUrls: ['./readonlybuilder.component.scss']
})
export class ReadonlybuilderComponent implements OnInit {

  builder;
  line_id;
  form_id;
  basic: boolean = false;

  constructor(
    private routing: Router,
    private route: ActivatedRoute,
    private buildservice: ServicebuilderService,
  ) { }

  ngOnInit(): void {
    this.line_id = this.route.snapshot.params['id'];
    this.form_id = this.route.snapshot.params['form_id'];
    this.buildservice.getDataById(this.line_id, this.form_id).subscribe(data => {
      this.builder = data;
    })
  }

  goToWhoColumns() {
    this.basic = !this.basic;
  }

  back() {
    this.routing.navigate(['home/builder/allbuilder']);
  }
  //extra
  alertType: string;
  alertMessage: string = "";
  alert = [
    { type: "success", message: "Build Successfully" },
    { type: "danger", message: "Some error Happens" },
  ];

  buildDynamicForm() {
    console.log("buildDynamicForm() Form_id = " + this.form_id);
    if (this.form_id === null) {
      this.alertType = this.alert[1].type;
      this.alertMessage = "form_code is null";
      return;
    }
    this.buildservice.buildDynamicForm(this.form_id).subscribe(data => {
      console.log("Success Message: ", data);
      this.alertType = this.alert[0].type;
      this.alertMessage = this.alert[0].message;
    }, (err) => {
      console.log("Error Message ", err);
      this.alertType = this.alert[1].type;
      this.alertMessage = this.alert[1].message;
    });

  }


}
