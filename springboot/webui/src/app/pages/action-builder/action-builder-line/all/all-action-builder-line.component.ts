import { Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation, ChangeDetectorRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { Rn_Cff_ActionBuilder_Line } from 'src/app/models/Rn_Cff_ActionBuilder_Line';
import { AlertService } from 'src/app/services/alert.service';
import { ActionBuilderService } from 'src/app/services/api/action-builder.service';

@Component({
  selector: 'app-all-action-builder-line',
  templateUrl: './all-action-builder-line.component.html',
  styleUrls: ['./all-action-builder-line.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AllActionBuilderLineComponent implements OnInit {
  @ViewChild("getById") getById: TemplateRef<any>;
  @ViewChild("txId") txId: TemplateRef<any>;
  @ViewChild(DatatableComponent) table: DatatableComponent;
  
  basic: boolean = false;
  columns: any[];
  rows: any[];
  temp = [];
  isLoading: boolean = false;
  actionBuilder_Lines: Rn_Cff_ActionBuilder_Line[];

  constructor(
    private ref: ChangeDetectorRef,
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private actionBuilderService: ActionBuilderService,
    private alertService: AlertService,
    
  ) {}

  headerId: number;
  ngOnInit() {
    //this.wireframeService.removeModuleId();
    this.route.queryParams.subscribe(params => {
      this.headerId = +params['headerId'];
    });
    //this.initCopyRuleForm();
    this.getActionBuilderLines(this.headerId);

    this.columns = [
      /* { prop: "id", name: "Actions", width: 80, cellTemplate: this.getById }, */
      { prop: "actionType1", name: "Action Type 1", width: 120 },
      { prop: "actionType2", name: "Action Type2", width: 100 },
      { prop: "dataType", name: "Data Type", width: 100 },
      { prop: "variableName", name: "Variable Name", width: 100 },
      { prop: "assignment", name: "Assignment", width: 100 },
      { prop: "message", name: "Message", width: 100 },
      { prop: "conditions", name: "Conditions", width: 100 },
      { prop: "forward", name: "Forward", width: 100 },
      { prop: "equation", name: "equation", width: 100 },
      { prop: "seq", name: "seq", width: 100 },
      { prop: "action", name: "action", width: 100 },
      { prop: "groupId", name: "groupId", width: 100 }
    ];
  }
  getActionBuilderLines(headerId: number) {
    this.isLoading = true;
    this.actionBuilderService.getLinesByHeaderId(headerId).subscribe((data) => {
      this.isLoading = false;
      console.log(data);
      this.actionBuilder_Lines = data.items;
      this.rows = this.actionBuilder_Lines;
      this.temp = [...this.actionBuilder_Lines];
    });
  }

  doFilter(event) {
    let val = event.target.value.toLowerCase();
    // filter our data
    let temp = this.temp.filter((d) => {
      return (
        d.actionType1.toLowerCase().indexOf(val) !== -1 || !val ||
        d.actionType2.toLowerCase().indexOf(val) !== -1 || !val ||
        d.dataType.toLowerCase().indexOf(val) !== -1 || !val ||
        d.variableName.toLowerCase().indexOf(val) !== -1 || !val ||
        d.assignment.toLowerCase().indexOf(val) !== -1 || !val ||
        d.message.toLowerCase().indexOf(val) !== -1 || !val ||
        d.conditions.toLowerCase().indexOf(val) !== -1 || !val ||
        d.forward.toLowerCase().indexOf(val) !== -1 || !val ||
        d.equation.toLowerCase().indexOf(val) !== -1 || !val ||
        d.seq.toLowerCase().indexOf(val) !== -1 || !val ||
        d.action.toLowerCase().indexOf(val) !== -1 || !val
      );
    });
    // update the rows
    this.rows = temp;
    // Whenever the filter changes, always go back to the first page
    this.table.offset = 0;
  }

/*   goToAdd() {
    this.router.navigate(["../add"], { relativeTo: this.route });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readonly/" + id], { relativeTo: this.route });
  }
  goToEdit(id: number) {
    this.router.navigate(["../edit/" + id], { relativeTo: this.route });
  } */
  goToAdd() {
    this.router.navigate(["../add"], { relativeTo: this.route, queryParams: { headerId: this.headerId } });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readonly/" + id], { relativeTo: this.route, queryParams: { headerId: this.headerId } });
  }
  goToEdit(id: number) {
    this.router.navigate(["../edit/" + id], { relativeTo: this.route,  queryParams: { headerId: this.headerId } });
  }

  // ========= build layout and data ===========//
  // buildLayOut() {
  //   this.actionBuilderService.cffLayOut(this.headerId).subscribe(res => {
  //     console.log(res);
  //   }, (err) => {
  //     console.log(err);
  //   });
  // }
  // buildData() {
  //   this.actionBuilderService.cffData(this.headerId).subscribe(res => {
  //     console.log(res);
  //   }, (err) => {
  //     console.log(err);
  //   });
  // }

  buildAction() {
    this.actionBuilderService.cffData(this.headerId).subscribe(res => {
      console.log(res);
    }, (err) => {
      console.log(err);
    });
  }

  toggleStatus(id: any, value: any) {
    console.log("id : ", id, " value : ", value);
    this.actionBuilderService.setActive(id).subscribe(
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
}
