import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Bcf_Extractor_Params } from "src/app/models/Bcf_Extractor_Params";
import { CodeExtractionService } from "src/app/services/api/code-extraction.service";
import { TechnologyStackService } from "src/app/services/api/technology-stack.service";

@Component({
  selector: "app-edit-code-extraction-param",
  templateUrl: "./edit-code-extraction-param.component.html",
  styleUrls: ["./edit-code-extraction-param.component.scss"],
  encapsulation: ViewEncapsulation.Emulated,
})
export class EditCodeExtractionParamComponent implements OnInit {
  tech_stacks = [];
  tech_stack_key = ['aspmy', 'sphmy'];
  object_types = ['form', 'bi', 'report'];
  sub_object_types = ['only header', 'only line'];

  updated = false;
  bcf_Extractor_param: Bcf_Extractor_Params;
  
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private codeExtractionService: CodeExtractionService,
    private techstackservice:TechnologyStackService
  ) {}
  header_id: number;
  id: number;
  ngOnInit() {
    // path variable
    this.id = this.route.snapshot.params["id"];
    // query param
    this.route.queryParams.subscribe((params) => {
      this.header_id = +params["header_id"];
    });

    this.bcf_Extractor_param = new Bcf_Extractor_Params();
    this.getById(this.id);

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

  getById(id: number) {
    this.codeExtractionService.getCodeExtractionParamById(id).subscribe((data) => {
      console.log(data);
      this.bcf_Extractor_param = data;
    });
  }
  update() {
    this.codeExtractionService.updateExtractionParams(this.id, this.header_id, this.bcf_Extractor_param).subscribe(
      (data) => {
        console.log(data);
      
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );

    this.router.navigate(["/home/code-extraction/params"], {relativeTo: this.route, queryParams: { header_id: this.header_id } });
   // this.bcf_Extractor_param = new Bcf_Extractor_Params();
  }

  onSubmit() {
    this.updated = true;
    this.update();
  }


}
