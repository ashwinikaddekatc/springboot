import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Rn_Cff_ActionBuilder_Header } from 'src/app/models/Rn_Cff_ActionBuilder_Header';
import { ActionBuilderService } from 'src/app/services/api/action-builder.service';
import { WireframeService } from 'src/app/services/api/wireframe.service';

@Component({
  selector: 'app-readonly-action-builder',
  templateUrl: './readonly-action-builder.component.html',
  styleUrls: ['./readonly-action-builder.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ReadonlyActionBuilderComponent implements OnInit {
  basic: boolean = false;
  id: number;
  actionBuilder_Header: Rn_Cff_ActionBuilder_Header;
  projectId:number;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private actionBuilderService: ActionBuilderService,
    private wireFrameServie: WireframeService
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.params["id"];
    /* this.route.queryParams.subscribe(params => {
      this.projectId = +params['p_id'];
    }); */
    this.getById(this.id);
  }

  getById(id: number) {
    this.actionBuilder_Header = new Rn_Cff_ActionBuilder_Header();
    this.actionBuilderService.getById(id).subscribe((data) => {
      console.log(data);
      this.actionBuilder_Header = data;
      //this.fbHeaderName(this.actionBuilder_Header.rn_fb_header.id)
    }, (err) => {
      console.log(err);
    });
  }


  goToWhoColumns() {
    this.basic = !this.basic;
  }
  back() {
    this.router.navigate(["../../all"], { relativeTo: this.route });
    //this.router.navigate(["../../all"],{ relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }
  wfName: string;
  fbHeaderName(id: number) {
    this.wireFrameServie.getById(id).subscribe(data=> {
      
    })
  }

}
