import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DepartmentService } from 'src/app/services/api/department.service';


interface errorMsg {
  field: any;
  message: any;
}

@Component({
  selector: 'app-addlinedepart',
  templateUrl: './addlinedepart.component.html',
  styleUrls: ['./addlinedepart.component.scss']
})
export class AddlinedepartComponent implements OnInit {

  public entryForm: FormGroup;
  submitted = false;
  errorMsg: errorMsg[] = [];
  
  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private deptser:DepartmentService
  ) { }

  ngOnInit(): void {
  
    this.entryForm = this._fb.group({
      department: this._fb.array([this.initLinesForm()]),
    });
  }

  initLinesForm() {
    return this._fb.group({
      dname: [null],
      dhead: [null],
      dcontact: [null],
      no_ofEmp: [null],
    });
  }

  onSubmit() {
    console.log(this.entryForm.getRawValue()['department']);
    console.warn("my data"+this.entryForm.value);
    
    //console.log(this.entryForm.value);
    this.submitted = true;
    if (this.entryForm.invalid) {
      return;
    }
    this.onCreate();
  }

  onCreate() {
    console.warn("in the oncreate ");
    
     this.deptser.create(this.entryForm.getRawValue()['department']).subscribe(data => {
       console.log(data)
       this.router.navigate(["/home/department/"]);
       },
       (error) => {
         console.log(error);
       }
     );
   }


   get controls() {
    return (this.entryForm.get("department") as FormArray).controls;
  }
  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("department")).removeAt(index);
  }
  onAddLines() {
    (<FormArray>this.entryForm.get("department")).push(this.initLinesForm());
  }

}

