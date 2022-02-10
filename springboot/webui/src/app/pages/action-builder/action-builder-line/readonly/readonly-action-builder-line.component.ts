import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Rn_Cff_ActionBuilder_Line } from 'src/app/models/Rn_Cff_ActionBuilder_Line';
import { ActionBuilderService } from 'src/app/services/api/action-builder.service';

@Component({
  selector: 'app-readonly-action-builder-line',
  templateUrl: './readonly-action-builder-line.component.html',
  styleUrls: ['./readonly-action-builder-line.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ReadonlyActionBuilderLineComponent implements OnInit {
  basic: boolean = false;
  id: number;
  actionBuilder_Line: Rn_Cff_ActionBuilder_Line;
  headerId: number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private actionBuilderService: ActionBuilderService
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.params["id"];
    this.route.queryParams.subscribe(params => {
      this.headerId = +params['headerId'];
    });
    this.getById(this.id);
  }

  getById(id: number) {
    this.actionBuilder_Line = new Rn_Cff_ActionBuilder_Line();

    this.actionBuilderService.getLineById(id).subscribe((data) => {
      console.log(data);
      this.actionBuilder_Line = data;
    });
  }

  goToWhoColumns() {
    this.basic = !this.basic;
  }
  back() {
    //this.router.navigate(["../../all"], { relativeTo: this.route });
    this.router.navigate(["../../all"],{ relativeTo: this.route, queryParams: { headerId: this.headerId } });
  }

}
