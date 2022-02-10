import { Component, OnInit, TemplateRef, ViewChild, ViewEncapsulation } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { ProjectSetup } from 'src/app/models/Project_setup';
import { AlertService } from 'src/app/services/alert.service';
import { ProjectSetupService } from 'src/app/services/api/project-setup.service';

@Component({
  selector: 'app-all-project-setup',
  templateUrl: './all-project-setup.component.html',
  styleUrls: ['./all-project-setup.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AllProjectSetupComponent implements OnInit {

  @ViewChild("getById") getById: TemplateRef<any>;
  @ViewChild("txId") txId: TemplateRef<any>;
  @ViewChild(DatatableComponent) table: DatatableComponent;
  
  basic: boolean = false;
  columns: any[];
  rows: any[];
  temp = [];
  isLoading: boolean = false;
  projects: ProjectSetup[];

  // copy rules
  tech_stacks = ['SpringMVC-Hibernate-Mysql', 'Angular-SpringBoot-Mysql', 'React-ReactNative-Mysql', 'React-ReactNative-MongoDB', 'Angular-SpringBoot-MongoDB', 'Php-Laravel-Mysql', 'MEAN'];
  object_types = ['form', 'bi', 'report', 'api'];
  sub_object_types = ['only header', 'only line', 'header line', 'header multiline', 'wrokflow', 'setup', 'std report', 'bi report', 'rest api'];

  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private projectSetupService: ProjectSetupService,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    //this.initCopyRuleForm();

    this.getData();
    this.columns = [
      /* { prop: "id", name: "Actions", width: 80, cellTemplate: this.getById }, */
      { prop: "projectName", name: "Project Name", width: 120 },
      { prop: "projectPrefix", name: "Prefix", width: 100 },
      { prop: "technologyStack", name: "Technology Stack", width: 120 },
      { prop: "description", name: "Description", width: 100 },
      { prop: "dbName", name: "Database Name", width: 100 },
      { prop: "dbUserName", name: "Database Username", width: 105 },
      { prop: "dbPassword", name: "Database Password", width: 105 },
      { prop: "portNumber", name: "Port Number", width: 105 }
    ];
  }
  getData() {
    this.isLoading = true;
    this.projectSetupService.getAll().subscribe((data) => {
      this.isLoading = false;
      console.log(data);
      //console.log(data.items);
      this.projects = data.items;
      this.rows = this.projects;
      this.temp = [...this.projects];
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
    this.projectSetupService.copy(this.copyRuleForm.value).subscribe(
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
        d.tech_stack.toLowerCase().indexOf(val) !== -1 || !val ||
        d.object_type.toLowerCase().indexOf(val) !== -1 || !val ||
        d.sub_object_type.toLowerCase().indexOf(val) !== -1 || !val ||
        d.rule_name.toLowerCase().indexOf(val) !== -1 || !val ||
        d.rule_type.toLowerCase().indexOf(val) !== -1 || !val ||
        d.file_code.toLowerCase().indexOf(val) !== -1 || !val ||
        d.identifier_start_string.toLowerCase().indexOf(val) !== -1 || !val ||
        d.identifier_end_string.toLowerCase().indexOf(val) !== -1 || !val ||
        d.replacement_string.toLowerCase().indexOf(val) !== -1 || !val
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

  goToModule(id: number) {
    this.router.navigate(["../modules"], {relativeTo: this.route, queryParams: { p_id: id } });
  }
}
