import { Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { Audit } from 'src/app/models/Audit';
import { Bcf_Extractor } from 'src/app/models/Bcf_Extractor';
import { AlertService } from 'src/app/services/alert.service';
import { CodeExtractionService } from 'src/app/services/api/code-extraction.service';

@Component({
  selector: 'app-all-code-extraction',
  templateUrl: './all-code-extraction.component.html',
  styleUrls: ['./all-code-extraction.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AllCodeExtractionComponent implements OnInit {
  @ViewChild('getById') selectById: TemplateRef<any>;
  @ViewChild('txId') txId: TemplateRef<any>;
  @ViewChild(DatatableComponent) table: DatatableComponent;
  basic: boolean=false;
  columns:any[];
  rows:any[];
  temp = [];

  filterData: string;
  isLoading:boolean=false;
  bcf_extractor: Bcf_Extractor[];
  whoColumns: Audit;
  constructor(
      private router: Router, 
      private route: ActivatedRoute,
      private codeExtractionService: CodeExtractionService,
      private alertService: AlertService
  ) { }

  ngOnInit() {
      this.getData();
      // COLUMNS
      this.columns = [
         /*  {prop:"id"         , name: "Actions"           , width:150, cellTemplate: this.selectById   }, */
          {prop:"tech_stack"       , name: "Technology Stack"   , width:180 },
          {prop:"object_type"    , name: "Object Type"         , width:100 },
          {prop:"sub_object_type"     , name: "Sub Object Type"       , width:100 },
          {prop:"form_type_name"     , name: "Form Type"       , width:80 },
          {prop: "std_wf_name", name: "WireFrame Name", width: 150},
          {prop: "icon_file_name", name: "Icon", width: 80},
          {prop: "sample_file_name", name: "File Name", width: 150},
          {prop: "extractor_stage", name: "Extractor Stage", width: 60},
          /* {prop:"id" , name: "Go To Params"           , width:100, cellTemplate: this.txId}, */

      ];
  }

      getData() {
          this.isLoading=true;
          this.codeExtractionService.getAll()
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
          (d.tech_stack_key.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.object_type.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.sub_object_type.toLowerCase().indexOf(val) !== -1 || !val) || 
          (d.form_type_name.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.std_wf_name.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.icon_file_name.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.sample_file_name.toLowerCase().indexOf(val) !== -1 || !val) ||
          (d.extractor_stage.toLowerCase().indexOf(val) !== -1 || !val)
        );
      });
      // update the rows
      this.rows = temp;
      // Whenever the filter changes, always go back to the first page
      this.table.offset = 0;
    }

  goToAdd() {
      this.router.navigate(["../add"], { relativeTo: this.route });
  }

  goToReadOnly(id: number) {
      this.router.navigate(["../readonly/" + id], { relativeTo: this.route });
  }

  goToEdit(id: number) {
      this.router.navigate(["../edit/" + id], {relativeTo: this.route });
  }

  getById(id: number) {
      this.codeExtractionService.getById(id)
          .subscribe(data => {
              console.log("data from sarver", data);
              this.whoColumns = data;
              console.log("who columns: ",this.whoColumns);
          });
  }

  goToWhoColumns(id: number) {
      this.basic = !this.basic;
      this.getById(id);
  }

  goToParams(id: number) {
    this.router.navigate(["../params"], {relativeTo: this.route, queryParams: { header_id: id } });
  }

  goToEditor(id: number) {
    this.router.navigate(["../file-editor"], {relativeTo: this.route, queryParams: { header_id: id } });
  }

  staticExtraction(id: number) {
    this.codeExtractionService.staticCodeExtraction(id).subscribe(res => {
      console.log(res);
      this.alertService.success(res.success.message);
    }, err => {
      console.log(err);
    });
  }
  dynamicExtraction(id: number) {
    this.codeExtractionService.dynamicCodeExtraction(id).subscribe(res => {
      console.log(res);
      this.alertService.success(res.success.message);
    }, err => {console.log(err);
    });
  }

  

  buildMasterBuilder(id: number) {
    this.codeExtractionService.buildMasterBuilder(id).subscribe(res => {
      console.log(res);
    }, (err) => {
      console.log(err);
    });
  }
}
