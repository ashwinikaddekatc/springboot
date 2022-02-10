import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ModuleSetup } from 'src/app/models/Module_Setup';
import { ModuleSetupService } from 'src/app/services/api/module-setup.service';

@Component({
  selector: 'app-readonly-module-setup',
  templateUrl: './readonly-module-setup.component.html',
  styleUrls: ['./readonly-module-setup.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ReadonlyModuleSetupComponent implements OnInit {
  basic: boolean = false;
  id: number;
  module: ModuleSetup;
  projectId:number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private moduleSetupService: ModuleSetupService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.projectId = +params['p_id'];
    });
    this.getById();
  }

  getById() {
    this.module = new ModuleSetup();
    this.id = this.route.snapshot.params["id"];

    this.moduleSetupService.getById(this.id).subscribe((data) => {
      console.log(data);
      this.module = data;
    });
  }

  goToWhoColumns() {
    this.basic = !this.basic;
  }

  back() {
    //this.router.navigate(["../../all"], { relativeTo: this.route });
    this.router.navigate(["../../all"],{ relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
}
