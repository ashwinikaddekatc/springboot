import { Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { ModuleSetup } from 'src/app/models/Module_Setup';
import { AlertService } from 'src/app/services/alert.service';
import { ModuleSetupService } from 'src/app/services/api/module-setup.service';
import { ProjectSetupService } from 'src/app/services/api/project-setup.service';
import { WireframeService } from 'src/app/services/api/wireframe.service';

@Component({
  selector: 'app-all-module-setup',
  templateUrl: './all-module-setup.component.html',
  styleUrls: ['./all-module-setup.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AllModuleSetupComponent implements OnInit {
  @ViewChild("getById") getById: TemplateRef<any>;
  @ViewChild("txId") txId: TemplateRef<any>;
  @ViewChild(DatatableComponent) table: DatatableComponent;
  
  basic: boolean = false;
  columns: any[];
  rows: any[];
  temp = [];
  isLoading: boolean = false;
  modules: ModuleSetup[];

  // copy rules
  tech_stacks = ['SpringMVC-Hibernate-Mysql', 'Angular-SpringBoot-Mysql', 'React-ReactNative-Mysql', 'React-ReactNative-MongoDB', 'Angular-SpringBoot-MongoDB', 'Php-Laravel-Mysql', 'MEAN'];
  object_types = ['form', 'bi', 'report', 'api'];
  sub_object_types = ['only header', 'only line', 'header line', 'header multiline', 'wrokflow', 'setup', 'std report', 'bi report', 'rest api'];

  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private moduleSetupService: ModuleSetupService,
    private projectSetupService: ProjectSetupService,
    private alertService: AlertService,
    private wireframeService: WireframeService
  ) {}

  projectId: number;
  ngOnInit() {
    this.wireframeService.removeModuleId();
    this.route.queryParams.subscribe(params => {
      this.projectId = +params['p_id'];
    });
    //this.initCopyRuleForm();
    this.getProjectModules(this.projectId);

    this.columns = [
     /*  { prop: "id", name: "Actions", width: 80, cellTemplate: this.getById }, */
      { prop: "moduleName", name: "Module Name", width: 120 },
      { prop: "modulePrefix", name: "Prefix", width: 100 },
      { prop: "description", name: "Description", width: 100 },
      { prop: "technologyStack", name: "Technology Stack", width: 100 },
    ];
  }
  getProjectModules(id: number) {
    this.isLoading = true;
    this.moduleSetupService.getProjectModules(id).subscribe((data) => {
      this.isLoading = false;
      console.log(data);
      this.modules = data.items;
      this.rows = this.modules;
      this.temp = [...this.modules];
    });
  }

  // rule copy code
  /* public copyRuleForm: FormGroup;
  submitted = false;
  initCopyRuleForm() {
    this.copyRuleForm = this._fb.group({
      from_tech_stack: [null],
      from_object_type: [null],
      from_sub_object_type: [null],
      to_tech_stack: [null],
      to_object_type: [null],
      to_sub_object_type: [null]
    });
  }
  onSubmit() {
    console.log(this.copyRuleForm.value);
    this.submitted = true;
    if (this.copyRuleForm.invalid) {
      return;
    }
    this.onCopy();
  }
  onCopy() {
    this.ruleLibraryService.copy(this.copyRuleForm.value).subscribe(
      (data) => {
        console.log(data);
        // alert service
        this.alertService.success('Rule Copied successfully');
        this.getData();
      },(err) => {
        console.log(err);
        this.alertService.error('Rule Copy Failed');
      }
    );
  } */
      




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
    this.router.navigate(["../add"], { relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readonly/" + id], { relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
  goToEdit(id: number) {
    this.router.navigate(["../edit/" + id], { relativeTo: this.route,  queryParams: { p_id: this.projectId } });
  }

  goToAction(id: number) {
    this.router.navigate(["../actions"], { relativeTo: this.route, queryParams: { m_id: id } });
  }
}
