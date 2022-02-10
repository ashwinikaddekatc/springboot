import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentService } from 'src/app/services/api/department.service';

@Component({
  selector: 'app-readonlydept',
  templateUrl: './readonlydept.component.html',
  styleUrls: ['./readonlydept.component.scss']
})
export class ReadonlydeptComponent implements OnInit {

  basic: boolean = false;
  department;
  header_id;
  constructor(private route: ActivatedRoute, private service: DepartmentService, private routing: Router) { }

  ngOnInit(): void {
    this.header_id = this.route.snapshot.params['id'];
    this.service.getDataById(this.header_id).subscribe(data => {
      this.department = data;
    })
  }

  goToWhoColumns() {
    this.basic = !this.basic;
}

  back() {
    this.routing.navigate(['home/dept/all']);
  }


}
