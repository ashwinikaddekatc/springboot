import { ChangeDetectorRef, Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { Audit } from 'src/app/models/Audit';
import { Bcf_Extractor_Params } from 'src/app/models/Bcf_Extractor_Params';
import { CodeExtractionService } from 'src/app/services/api/code-extraction.service';

@Component({
  selector: 'app-all-code-extraction-param',
  templateUrl: './all-code-extraction-param.component.html',
  styleUrls: ['./all-code-extraction-param.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AllCodeExtractionParamComponent implements OnInit {
  @ViewChild('getById') selectById: TemplateRef<any>;
  @ViewChild('extSwitch') extById: TemplateRef<any>;
  @ViewChild("creationSwitch") creationById: TemplateRef<any>;
  
  @ViewChild(DatatableComponent) table: DatatableComponent;
  basic: boolean=false;
  columns:any[];
  rows:any[];
  temp = [];

  filterData: string;
  isLoading:boolean=false;
  bcf_extractor: Bcf_Extractor_Params[];
  whoColumns: Audit;
  constructor(
      private router: Router, 
      private route: ActivatedRoute,
      private codeExtractionService: CodeExtractionService,
      private ref: ChangeDetectorRef
  ) { }
  header_id: number;
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.header_id = +params['header_id'];
    });

      this.getData(this.header_id);
      // COLUMNS
      this.columns = [
          /* {prop:"id"         , name: "Actions"           , width:65, cellTemplate: this.selectById   },
          {prop:"is_extraction_enabled"     , name: "Extraction Enable", width:90, cellTemplate: this.extById }, */
          {prop:"tech_stack"       , name: "Technology Stack"   , width:100 },
          {prop:"object_type"    , name: "Object Type"         , width:80 },
          {prop:"sub_object_type"     , name: "Sub Object Type"       , width:80 },
          {prop:"file_code"     , name: "File Code"       , width:60 },
          {prop: "name_string", name: "Name", width: 90},
          /* {prop: "address_string", name: "Address", width: 60},
          {prop: "moved_address_string", name: "File Name", width: 120}, */
          {prop: "reference_address_string", name: "Address String", width: 180},
          {prop: "description", name: "Description", width: 60},
          {prop:"file_name_var"     , name: "Name Variable"       , width:90 },
          {prop:"file_name_dynamic_string"     , name: "Dynamic String", width:100 },
          {prop:"total_project_path_dynamic_string"     , name: "total path String", width:100 },
          /* {prop:"is_creation_enabled"     , name: "Creation Enable", width:90, cellTemplate: this.creationById } */
      ];
  }

      getData(id: number) {
          this.isLoading=true;
          this.codeExtractionService.getCodeExtractionParams(id)
          .subscribe(data => {
              this.isLoading=false;
              console.log(data);
              this.bcf_extractor = data.items;
              this.rows = this.bcf_extractor;
              this.temp = [...this.bcf_extractor];
          });
      }


    doFilter(event) {
      let val = event.target.value.toLowerCase();
      // filter our data
      let temp = this.temp.filter((d) => {
        return (
          (d.tech_stack.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.object_type.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.sub_object_type.toLowerCase().indexOf(val) !== -1 || !val) || 
          (d.file_code.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.name_string.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.address_string.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.moved_address_string.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.reference_address_string.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.description.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.file_name_var.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.file_name_dynamic_string.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.total_project_path_dynamic_string.toLowerCase().indexOf(val) !== -1 || !val)
        );
      });
      // update the rows
      this.rows = temp;
      // Whenever the filter changes, always go back to the first page
      this.table.offset = 0;
    }

  goToAdd() {
    this.router.navigate(["../add"], { relativeTo: this.route, queryParams: { header_id: this.header_id } });
  }

  goToReadOnly(id: number) {
    this.router.navigate(["../readonly/" + id], { relativeTo: this.route, queryParams: { header_id: this.header_id } });
  }

  goToEdit(id: number) {
    this.router.navigate(["../edit/" + id], { relativeTo: this.route,  queryParams: { header_id: this.header_id }  });
  }

  creationToggle(id: any, value?: any) {
    console.log("id : ", id, "creation value : ", value);
    this.codeExtractionService.creationStatusChange(id).subscribe(
      (data) => {
        console.log(data);
        //this.getData();
        this.ref.detectChanges();
      },
      (err) => {
        console.log(err);
      }
    );
  }

  extractionToggle(id: any, value: any) {
    console.log("id : ", id, " extraction value : ", value);
    this.codeExtractionService.extractionStatusChange(id).subscribe(
      (data) => {
        console.log(data);
        this.ref.detectChanges();
      },
      (err) => {
        console.log(err);
      }
    );
  }
  goToEditor(id: number) {
    this.router.navigate(["../file-editor-new/"+id], {relativeTo: this.route, queryParams: { header_id: this.header_id } });
  }

}
