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
      salesPerson: this._fb.array([this.initLinesForm()]),


    });
    //this.service.getDataById(1).subscribe(data => { console.log(data) });
  }



  initLinesForm() {
    return this._fb.group({
      name: [null],
    });
  }

  get controls() {
    return (this.entryForm.get("salesPerson") as FormArray).controls;
  }
  onAddLines() {
    (<FormArray>this.entryForm.get("salesPerson")).push(this.initLinesForm());
  }
  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("salesPerson")).removeAt(index);
  }



  // onSubmit() {
  //   //console.log(this.entryForm.value);
  //   this.service.addData(this.entryForm.value).subscribe(data => {
  //     console.log(data);
  //     this.route.navigate(["/home/sales-new/all"]);
  //   }
  //   );

  // }

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
