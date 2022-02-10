import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorserviceService } from 'src/app/services/api/authorservice.service';
import { TechnologyStackService } from 'src/app/services/api/technology-stack.service';


@Component({
  selector: 'app-edit',
 templateUrl: './Flf_builderedit.component.html', 
  styleUrls: ['./Flf_builderedit.component.scss']})
export class Flf_buildereditsalesComponent implements OnInit {  
  
  
  tech_stacks=[];

  object_types = ['form', 'bi', 'report', 'api'];

  sub_object_types = ['only_header', 'only_line', 'header_line'];

  file_type=['html','ts','java'];

  typeFields: string[] = ['textfield', 'textarea', 'url', 'email', 'dropdown', 'checkbox',
    'togglebutton', 'datetime', 'autocomplete', 'upload_field', 'currency_field', 'contact_field',
    'multiselect_autocomplete', 'multiselect_dropdown', 'masked'];

    operation_type=['add','update','readonly','model','modelgettersetter'];

    form_type=['header', 'line'];
	
  
  updated = false;
  sales=[] ;
  sid: number;
  salesperson;


  constructor(private router: Router,
    private route:ActivatedRoute,
    private authorservice:AuthorserviceService,
   private  techstackservice:TechnologyStackService
    ) { }

  ngOnInit(): void {

    this.sales;
    this.sid = this.route.snapshot.params["id"];
    console.log("update with id = ", this.sid);
    this.getById(this.sid);


    this.techstackservice.getAll().subscribe((data)=>{
    
      console.log(data)
      
      for(let ts of data.items)
      {
        if(ts.tech_stack==null)
        {
          return;
        }
        // console.warn(ts.tech_stack);
       
        this.tech_stacks.push(ts.tech_stack)
        
      }

    });

  }

  getById(sid: number) {
    this.authorservice.getById(sid).subscribe((data) => {
      this.sales = data;
      console.log("data   ",this.sales);
      console.log(data.book)
      
      
      //this.students = data.students;
      
    });
  }

    update() {
      this.authorservice.update(this.sid, this.sales).subscribe(
        (data) => {
          console.log(data);
          this.router.navigate(["/home/flf/all"]);
        },
        (error: HttpErrorResponse) => {
          console.log(error.message);
        }
      );
    }

    onSubmit() {
      this.updated = true;
      this.update();
    }

    build_flf(id)
    {
      console.log("flf id" +id);
      this.authorservice.flf_builder(id).subscribe((data)=>{
          console.log(data);
      });
      
    }
  
    

}