import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectSetup } from 'src/app/models/Project_setup';
import { ProjectSetupService } from 'src/app/services/api/project-setup.service';

@Component({
  selector: 'app-readonly-project-setup',
  templateUrl: './readonly-project-setup.component.html',
  styleUrls: ['./readonly-project-setup.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ReadonlyProjectSetupComponent implements OnInit {
  basic: boolean = false;
  id: number;
  project: ProjectSetup;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private projectSetupService: ProjectSetupService
  ) {}

  ngOnInit() {
    this.getById();
  }

  getById() {
    this.project = new ProjectSetup();
    this.id = this.route.snapshot.params["id"];

    this.projectSetupService.getById(this.id).subscribe((data) => {
      console.log(data);
      this.project = data;
    });
  }

  goToWhoColumns() {
    this.basic = !this.basic;
  }

  back() {
    this.router.navigate(["../../all"], { relativeTo: this.route });
  }

}
