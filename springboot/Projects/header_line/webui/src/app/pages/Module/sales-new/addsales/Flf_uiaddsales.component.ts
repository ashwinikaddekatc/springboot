import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableGroupHeaderTemplateDirective } from '@swimlane/ngx-datatable';
import { SalesNewService } from 'src/app/services/api/sales-new.service';


interface errorMsg {
  field: any;
  message: any;
}

@Component({
  selector: 'app-addsales',
 templateUrl: './Flf_uiaddsales.component.html',  styleUrls: ['./Flf_uiaddsales.component.scss']})
export class Flf_uiaddsalesComponent implements OnInit {

	
	dropdownval=["yes","no","dont know"];
	autocomlist= ["1000","1001","1002","1003","1004","1005","1006","1007","1008","1009","1010"];
	

  private formCode: string = 'sales_form';
  // STORE FORM CODE IN SESSION
  public key: string = "formCode";
  public storage: Storage = sessionStorage;


  basic: boolean = false;

  public entryForm: FormGroup;
  submitted = false;
  errorMsg: errorMsg[] = [];

  constructor(
    public datepipe: DatePipe,
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private SalesNewService: SalesNewService,

  ) { }



  ngOnInit() {

    this.storage.setItem(this.key, this.formCode);
    console.log(this.storage.getItem(this.key));

    this.entryForm = this._fb.group({
    tech_stack:[null] ,
    object_type:[null] ,
    sub_object_type:[null] ,

      salesperson: this._fb.array([this.initLinesForm()]),

    });
  }



  initLinesForm() {
    return this._fb.group({

    operation_type:[null] ,
    field_type:[null] ,
    code:[null] ,
    file_type:[null] ,

    });
  }



  onSubmit() {



    console.warn("calling submit");

    //console.log(this.entryForm.value);
    this.submitted = true;
    if (this.entryForm.invalid) {
      return;
    }
    this.onCreate();
  }



  onCreate() {
    console.warn("in the oncreate ");

    this.SalesNewService.create(this.entryForm.value).subscribe(data => {
      console.log(data)
      this.router.navigate(["/home/sales-new/all"]);
    },
      (error) => {
        console.log(error);
      }
    );
  }



  get controls() {
    return (this.entryForm.get("salesperson") as FormArray).controls;
  }
  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("salesperson")).removeAt(index);
  }
  onAddLines() {
    (<FormArray>this.entryForm.get("salesperson")).push(this.initLinesForm());
  }



  extensionBuild() {
    this.basic = !this.basic;
    //this.basic = true;
    console.log("button status: ", this.basic);
  }

  
 method:string="";
  onClickMe(buttonname) {

  this.method=buttonname;
  console.log(this.method);
  
  
  this.SalesNewService.buttonmethod(this.method).subscribe(data=>{
    console.log("in add ts service and data is "+data);
  });
   
    
  }
}