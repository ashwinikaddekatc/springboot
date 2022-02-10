import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CodeExtractionService } from 'src/app/services/api/code-extraction.service';
import { Bcf_Extractor } from 'src/app/models/Bcf_Extractor';
import { TechnologyStackService } from 'src/app/services/api/technology-stack.service';
import { Bcf_TechnologyStack } from 'src/app/models/Bcf_TechnologyStack';
@Component({
  selector: 'app-add-code-extraction',
  templateUrl: './add-code-extraction.component.html',
  styleUrls: ['./add-code-extraction.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AddCodeExtractionComponent implements OnInit {

  public entryForm: FormGroup;
  submitted = false;
  basic: boolean = false;  

  tech_stack_key = ['aspmy', 'sphmy'];
  // tech_stacks = ['SpringMVC-Hibernate-Mysql', 'Angular-SpringBoot-Mysql', 'React-ReactNative-Mysql', 'React-ReactNative-MongoDB', 'Angular-SpringBoot-MongoDB', 'Php-Laravel-Mysql', 'MEAN'];
  tech_stacks=[] ;

  object_types = ['form', 'bi', 'report', 'api'];
  sub_object_types = ['only header', 'only line', 'header line', 'header multiline', 'wrokflow', 'setup', 'std report', 'bi report', 'rest api'];
 
  get f() { return this.entryForm.controls; }
  setStackKey(): string {
    if (this.f.tech_stack.value === "Angular-SpringBoot-Mysql") {
      return "aspmy";
    } else if (this.f.tech_stack.value === "SpringMVC-Hibernate-Mysql") {
      return "sphmy";
    }
  }


  
  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private codeExtractionServie: CodeExtractionService,
    private techstackservice:TechnologyStackService
  ) {}

  ngOnInit() {
    this.entryForm = this._fb.group({
      tech_stack: [null],
      tech_stack_key: [null],
      object_type: [null],
      sub_object_type: [null],
      form_type_name: [null],
      std_wf_name: [null],
      icon_file_name: [null]
    });

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
   
  }

 

  public zipFile: File = null;
  onSelectFile(event) {
    
    const size = event.target.files[0].size;
   
      this.zipFile = <File> event.target.files[0];
      console.log(this.zipFile);
      
    }

  onSubmit() {
    //console.log(this.entryForm.value);
    this.submitted = true;
    if (this.entryForm.invalid) {
      return;
    }
    this.onCreate();
  }

   onCreate() {
     let bcf_extractor = JSON.stringify(this.entryForm.value);
     console.log(bcf_extractor);
     /* this.codeExtractionServie.create(bcf_extractor).subscribe(res => {
       console.log(res);
      }, err => {console.log(err);}
     ); */
     const formData = new FormData();
     //formData.append('bcf_extractor', JSON.stringify(bcf_extractor));
     formData.append('bcf_extractor', bcf_extractor);
     formData.append('file', this.zipFile, this.zipFile.name);
     
     this.codeExtractionServie.saveFormAndUploadFile(formData).subscribe(res => {
       console.log(res);
       this.router.navigate(['../all'], {relativeTo: this.route});
     }, err => {console.log(err);
     });
   }



}
