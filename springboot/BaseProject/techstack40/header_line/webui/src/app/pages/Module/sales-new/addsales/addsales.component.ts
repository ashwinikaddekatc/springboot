
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorserviceService } from 'src/app/services/api/authorservice.service';



interface errorMsg {
  field: any;
  message: any;
}

@Component({
  selector: 'app-addsales',
  templateUrl: './addsales.component.html',
  styleUrls: ['./addsales.component.scss']
})
export class AddsalesComponent implements OnInit {


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
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authorservice:AuthorserviceService

  ) { }



  ngOnInit() {

    this.storage.setItem(this.key, this.formCode);
    console.log(this.storage.getItem(this.key));

    this.entryForm = this._fb.group({
      afname: [null],
      lname: [null],
      

      book: this._fb.array([this.initLinesForm()]),


      //Extentions
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



  initLinesForm() {
    return this._fb.group({

      bname: [null],
      btitle: [null],


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

    this.authorservice.create(this.entryForm.value).subscribe(data => {
      console.log(data)
      this.router.navigate(["/home/author/all"]);
    },
      (error) => {
        console.log(error);
      }
    );
  }



  get controls() {
    return (this.entryForm.get("book") as FormArray).controls;
  }
  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("book")).removeAt(index);
  }
  onAddLines() {
    (<FormArray>this.entryForm.get("book")).push(this.initLinesForm());
  }



  extensionBuild() {
    this.basic = !this.basic;
    //this.basic = true;
    console.log("button status: ", this.basic);
  }

  goToExt() {
    this.router.navigate(['extension/all'], { relativeTo: this.route });
  }
  
  method:string="";
  onClickMe(buttonname) {

  this.method=buttonname;
  console.log(this.method);
  
  
  this.authorservice.buttonmethod(this.method).subscribe(data=>{
    console.log("in add ts service and data is "+data);
  });
   
    
  }

}
