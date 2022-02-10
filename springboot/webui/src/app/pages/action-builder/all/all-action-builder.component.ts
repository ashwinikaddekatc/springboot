import { Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { Rn_Cff_ActionBuilder_Header } from 'src/app/models/Rn_Cff_ActionBuilder_Header';
import { AlertService } from 'src/app/services/alert.service';
import { ActionBuilderService } from 'src/app/services/api/action-builder.service';

@Component({
  selector: 'app-all-action-builder',
  templateUrl: './all-action-builder.component.html',
  styleUrls: ['./all-action-builder.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AllActionBuilderComponent implements OnInit {
  @ViewChild("getById") getById: TemplateRef<any>;
  @ViewChild("txId") txId: TemplateRef<any>;
  @ViewChild(DatatableComponent) table: DatatableComponent;
  
  basic: boolean = false;
  columns: any[];
  rows: any[];
  temp = [];
  isLoading: boolean = false;
  actionBuilder_Header: Rn_Cff_ActionBuilder_Header[];

  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private actionBuilderService: ActionBuilderService,
    private alertService: AlertService,
  ) {}

  projectId: number;
  ngOnInit() {
    //this.wireframeService.removeModuleId();
    /* this.route.queryParams.subscribe(params => {
      this.projectId = +params['p_id'];
    }); */
    this.columns = [
      /* { prop: "id", name: "Actions", width: 80, cellTemplate: this.getById }, */
      { prop: "technologyStack", name: "Module Name", width: 120 },
      { prop: "actionName", name: "Technology Stack", width: 100 },
      { prop: "controllerName", name: "Prefix", width: 100 },
      { prop: "methodName", name: "Method Name", width: 100 },
      { prop: "fileLocation", name: "File Location", width: 100 },
      { prop: "rn_fb_header", name: "Wireframe Name", width: 100 },
    ];
    this.getAll();
  }
  getAll() {
    this.isLoading = true;
    this.actionBuilderService.getAll().subscribe((data) => {
      this.isLoading = false;
      console.log(data);
      this.actionBuilder_Header = data.items;
      this.rows = this.actionBuilder_Header;
      this.temp = [...this.actionBuilder_Header];
    });
  }

  doFilter(event) {
    let val = event.target.value.toLowerCase();
    // filter our data
    let temp = this.temp.filter((d) => {
      return (
        d.actionName.toLowerCase().indexOf(val) !== -1 || !val ||
        d.controllerName.toLowerCase().indexOf(val) !== -1 || !val ||
        d.methodName.toLowerCase().indexOf(val) !== -1 || !val ||
        d.fileLocation.toLowerCase().indexOf(val) !== -1 || !val ||
        d.technologyStack.toLowerCase().indexOf(val) !== -1 || !val
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
    this.router.navigate(["../edit/" + id], { relativeTo: this.route });
  }
  /* goToAdd() {
    this.router.navigate(["../add"], { relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readonly/" + id], { relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
  goToEdit(id: number) {
    this.router.navigate(["../edit/" + id], { relativeTo: this.route,  queryParams: { p_id: this.projectId } });
  } */

  goToLines(id: number) {
    this.router.navigate(["../lines"], { relativeTo: this.route, queryParams: { headerId: id } });
  }

}
