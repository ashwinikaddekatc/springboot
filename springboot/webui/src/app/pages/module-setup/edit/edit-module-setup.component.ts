import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModuleSetup } from 'src/app/models/Module_Setup';
import { ValidationError } from 'src/app/models/ValidationError';
import { ModuleSetupService } from 'src/app/services/api/module-setup.service';

@Component({
  selector: 'app-edit-module-setup',
  templateUrl: './edit-module-setup.component.html',
  styleUrls: ['./edit-module-setup.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class EditModuleSetupComponent implements OnInit {
  updated = false;
  module: ModuleSetup;
  id: number;
  projectId: number;

  fieldErors: ValidationError[] = []; // backend validation field error message

  tech_stacks = ['SpringMVC-Hibernate-Mysql', 'Angular-SpringBoot-Mysql', 'React-ReactNative-Mysql', 'React-ReactNative-MongoDB', 'Angular-SpringBoot-MongoDB', 'Php-Laravel-Mysql', 'MEAN'];

  

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private moduleSetupService: ModuleSetupService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.projectId = +params['p_id'];
    });
    this.module = new ModuleSetup();
    this.id = this.route.snapshot.params["id"];
    console.log("update with id = ", this.id);
    this.getById(this.id);
  }

  getById(id: number) {
    this.moduleSetupService.getById(id).subscribe(
      (data) => {
        this.module = data;
        console.log('Module : ', this.module);
      },
      (err) => {
        console.log(err);
      }
    );
  }
  update() {
    this.moduleSetupService.update(this.id, this.module).subscribe(
      (data) => {
        console.log(data);
        //this.router.navigate(["../../all"], { relativeTo: this.route });
        this.router.navigate(["../../all"],{ relativeTo: this.route, queryParams: { p_id: this.projectId } });
      },
      (error) => {
        console.log(error);
        const objectArray = Object.entries(error.error.fieldErrors);
        objectArray.forEach(([k, v]) => {
          console.log(k);
          console.log(v);
          this.fieldErors.push({ field: k, message: v });
        });
        console.log(this.fieldErors); // this will come from backend
      }
    );
    this.module = new ModuleSetup();
  }

  onSubmit() {
    this.updated = true;
    this.update();
  }

  back() {
    this.router.navigate(["../../all"], { relativeTo: this.route });
  }

}
