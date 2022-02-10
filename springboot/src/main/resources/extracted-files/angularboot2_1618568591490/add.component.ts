import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {
  //create a object of entry form
  public entryForm: FormGroup;
  submitted = false;

  constructor(
    private _fb: FormBuilder,
    private service: SalesService,
    private route: Router
  ) { }

  ngOnInit(): void {
    this.entryForm = this._fb.group({
      name: [null],
      department: [null],
      joining_date: [null],
      status: [null],
     


    });
   
  }




 



  onSubmit() {
    console.log("call on submit", this.entryForm.value);
    this.submitted = true;
    if (this.entryForm.invalid) {
      console.log("invalid");

      return;
    }
    this.onCreate();
  }

  onCreate() {

    this.service.addData(this.entryForm.value).subscribe(
      (data) => {
        console.log("my dta", data);
        this.route.navigate(["home/sales-new/all"]);
      },
      (error) => {
        console.log(error);


      });

  }


}
