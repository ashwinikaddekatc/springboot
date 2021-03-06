import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentService } from 'src/app/services/api/department.service';

@Component({
  selector: 'app-readonlydepart',
 templateUrl: './Line_onlyreadonlydepart.component.html',  styleUrls: ['./Line_onlyreadonlydepart.component.scss']})
export class Line_onlyreadonlydepartComponent implements OnInit {
  basic: boolean = false;
  department;
  header_id:number;
  constructor(private route: ActivatedRoute, private service: DepartmentService, private routing: Router) { }

  ngOnInit(): void {

    console.log("in the getdept ");
    
    this.header_id = this.route.snapshot.params['did'];
    this.service.getDataById(this.header_id).subscribe(data => {
      this.department = data;
    })
  }

  goToWhoColumns() {
    this.basic = !this.basic;
}

  back() {
    this.routing.navigate(['home/department/']);
  }

}