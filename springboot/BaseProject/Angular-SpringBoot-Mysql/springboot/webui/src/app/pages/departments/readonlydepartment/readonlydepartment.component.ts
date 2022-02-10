import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicedepartmentService } from '../servicedepartment.service';

@Component({
  selector: 'app-readonlydepartment',
  templateUrl: './readonlydepartment.component.html',
  styleUrls: ['./readonlydepartment.component.scss']
})
export class ReadonlydepartmentComponent implements OnInit {
  departments;
  line_id;

  constructor(
    private route: ActivatedRoute,
    private service: ServicedepartmentService,
    private routing: Router
  ) { }

  ngOnInit(): void {
    this.line_id = this.route.snapshot.params['id'];
    this.service.getDataById(this.line_id).subscribe(data => {
      this.departments = data;
    })
  }

  back() {
    this.routing.navigate(['home/departments/alldepartments']);
  }

}
