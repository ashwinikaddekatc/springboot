import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Rn_Fb_Header } from 'src/app/models/Rn_Fb_Header';
import { WireframeService } from 'src/app/services/api/wireframe.service';

@Component({
  selector: 'app-readonly-wireframe',
  templateUrl: './readonly-wireframe.component.html',
  styleUrls: ['./readonly-wireframe.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ReadonlyWireframeComponent implements OnInit {
  basic: boolean = false;
  id: number;
  //projectId:number;
  wireframe: Rn_Fb_Header

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private wireframeService: WireframeService
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.params["id"];
    /* this.route.queryParams.subscribe(params => {
      this.projectId = +params['p_id'];
    }); */
    this.getById(this.id);
  }

  getById(id: number) {
    this.wireframe = new Rn_Fb_Header();

    this.wireframeService.getById(id).subscribe((data) => {
      console.log(data);
      this.wireframe = data;
    });
  }

  goToWhoColumns() {
    this.basic = !this.basic;
  }

  back() {
    this.router.navigate(["../../all"], { relativeTo: this.route });
    //this.router.navigate(["../../all"],{ relativeTo: this.route, queryParams: { p_id: this.projectId } });
  }

}
