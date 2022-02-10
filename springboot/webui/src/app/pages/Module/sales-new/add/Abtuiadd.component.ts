import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-add',
 templateUrl: './Abtuiadd.component.html',  styleUrls: ['./Abtuiadd.component.scss']})
export class AbtuiaddComponent implements OnInit {  //create a object of entry form
  public entryForm: FormGroup;
  submitted = false;
	
	dropdownval=["yes","no","dont know"];
	autocomlist= ["1000","1001","1002","1003","1004","1005","1006","1007","1008","1009","1010"];
	
	private formCode: string = 'sales_form';
	// STORE FORM CODE IN SESSION
	public key: string = "formCode";
	public storage: Storage = sessionStorage;

  constructor(
    private _fb: FormBuilder,
    private service: SalesService,
    private route: Router
  ) { }

  ngOnInit(): void {
	   this.storage.setItem(this.key, this.formCode);
		console.log(this.storage.getItem(this.key));
	  
    this.entryForm = this._fb.group({
      
	  
	  
	  
    label1:[null] ,
    label2:[null] ,
    label3:[null] ,
    label4:[null] ,
					
			// EXTENSION
			 extn1: [null],
			 extn2: [null],
			 extn3: [null],
			 extn4: [null],
			 extn5: [null],
			 extn6: [null],
			 extn7: [null],
			 extn8: [null],
			 extn9: [null],
			 extn10: [null],
			 extn11: [null],
			 extn12: [null],
			 extn13: [null],
			 extn14: [null],
			 extn15: [null],
			 // FLEX        
			 flex1: [null], 
			 flex2: [null], 
			 flex3: [null], 
			 flex4: [null],
			 flex5: [null],

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
        this.route.navigate(["home/abtui/all"]);
      },
      (error) => {
        console.log(error);


      });

  }
  
  
  method:string="";
  onClickMe(buttonname) {

  this.method=buttonname;
  console.log(this.method);
  
  
  this.service.buttonmethod(this.method).subscribe(data=>{
    console.log("in add ts service and data is "+data);
  });
   
    
  }


  insert(param,btnname)
  {
      this.service.actioninsert(param,btnname).subscribe((data)=>{
        console.log(data);
      })
  }

  update(id,param,btnname)
  {
    this.service.actionupdate(id,param,btnname).subscribe((data)=>{
      console.log(data);
      
    })

  }


}