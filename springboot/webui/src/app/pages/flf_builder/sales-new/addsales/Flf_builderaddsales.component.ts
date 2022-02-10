
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorserviceService } from 'src/app/services/api/authorservice.service';
import { TechnologyStackService } from 'src/app/services/api/technology-stack.service';



interface errorMsg {
  field: any;
  message: any;
}

@Component({
  selector: 'app-addsales',
 templateUrl: './Flf_builderaddsales.component.html',  styleUrls: ['./Flf_builderaddsales.component.scss']})
export class Flf_builderaddsalesComponent implements OnInit {

  tech_stacks=[];

  object_types = ['form', 'bi', 'report', 'api'];

  sub_object_types = ['only_header', 'only_line', 'header_line'];

  file_type=['html','ts','java'];

  typeFields: string[] = ['textfield', 'textarea', 'url', 'email', 'dropdown', 'checkbox',
    'togglebutton', 'datetime', 'autocomplete', 'upload_field', 'currency_field', 'contact_field',
    'multiselect_autocomplete', 'multiselect_dropdown', 'masked'];

    operation_type=['add','update','readonly','model','modelgettersetter'];
	

   form_type = ['header', 'line'];

  basic: boolean = false;

  public entryForm: FormGroup;
  submitted = false;
  errorMsg: errorMsg[] = [];

  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authorservice:AuthorserviceService,
    private techstackservice:TechnologyStackService

  ) { }



  ngOnInit() {



    this.techstackservice.getAll().subscribe((data)=>{
    
      console.log(data)
      
      for(let ts of data.items)
      {
        if(ts.tech_stack==null)
        {
          return;
        }
        console.warn(ts.tech_stack);
       
        this.tech_stacks.push(ts.tech_stack)
        
      }

    });

    this.entryForm = this._fb.group({
    techstack:[null] ,
    object_type:[null] ,
    sub_object_type:[null] ,
    isbuild:[null] ,
      

      book: this._fb.array([this.initLinesForm()]),


      
    });
  }



  initLinesForm() {
    return this._fb.group({

    filetype:[null] ,
    formtype:[null] ,
    field_type:[null] ,
    operation_type:[null] ,
    code:[null] ,


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
      this.router.navigate(["/home/flf/all"]);
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