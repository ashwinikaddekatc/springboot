import { Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { Rn_Fb_Header } from 'src/app/models/Rn_Fb_Header';
import { AlertService } from 'src/app/services/alert.service';
import { ModuleSetupService } from 'src/app/services/api/module-setup.service';
import { WireframeService } from 'src/app/services/api/wireframe.service';

@Component({
  selector: 'app-all-wireframe',
  templateUrl: './all-wireframe.component.html',
  styleUrls: ['./all-wireframe.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AllWireframeComponent implements OnInit {
  @ViewChild("getById") getById: TemplateRef<any>;
  @ViewChild("txId") txId: TemplateRef<any>;
  @ViewChild(DatatableComponent) table: DatatableComponent;
  
  basic: boolean = false;
  columns: any[];
  rows: any[];
  temp = [];
  isLoading: boolean = false;

  moduleId: number;
  wireFrames: Rn_Fb_Header[];

  
  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private moduleService: ModuleSetupService,
    private wireframeService: WireframeService,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    this.moduleId = this.wireframeService.getModuleId(); // get from session storage
    console.log(this.moduleId);
   
    this.getModuleWireframes(this.moduleId);

    this.columns = [
      /* { prop: "id", name: "Actions", cellTemplate: this.getById }, */
      { prop: "uiName", name: "UI Name" },
      { prop: "formType", name: "Form Type" }
    ];
  }
  getModuleWireframes(id: number) {
    this.isLoading = true;
    //this.moduleService.getById(id).subscribe((data) => {
      this.wireframeService.getAll(id).subscribe((data) => {
      this.isLoading = false;
      console.log(data);
      //this.wireFrames = data.rn_fb_headers;
      this.wireFrames = data.items;
      console.log('wireframes: ', this.wireFrames);
      this.rows = this.wireFrames;
      this.temp = [...this.wireFrames];
    });
  }

  doFilter(event) {
    let val = event.target.value.toLowerCase();
    // filter our data
    let temp = this.temp.filter((d) => {
      return (
        d.moduleName.toLowerCase().indexOf(val) !== -1 || !val ||
        d.description.toLowerCase().indexOf(val) !== -1 || !val ||
        d.modulePrefix.toLowerCase().indexOf(val) !== -1 || !val ||
        d.technologyStack.toLowerCase().indexOf(val) !== -1 || !val
      );
    });
    // update the rows
    this.rows = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

  goToAdd() {
    //this.router.navigate(["../add"], { relativeTo: this.route });
    this.router.navigate(["../types"], { relativeTo: this.route });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readonly/" + id], { relativeTo: this.route });
  }
  goToEdit(id: number) {
    this.router.navigate(["../edit/" + id], { relativeTo: this.route });
  }

/*   goToAdd() {
    this.router.navigate(["../add"], { relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readonly/" + id], { relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
  goToEdit(id: number) {
    this.router.navigate(["../edit/" + id], { relativeTo: this.route,  queryParams: { p_id: this.projectId } });
  } */

}
