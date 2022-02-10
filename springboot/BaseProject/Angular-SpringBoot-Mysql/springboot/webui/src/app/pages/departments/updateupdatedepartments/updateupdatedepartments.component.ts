import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { department } from '../departments';
import { ServicedepartmentService } from '../servicedepartment.service';

@Component({
  selector: 'app-updateupdatedepartments',
  templateUrl: './updateupdatedepartments.component.html',
  styleUrls: ['./updateupdatedepartments.component.scss']
})
export class UpdateupdatedepartmentsComponent implements OnInit {
  department: department[];
  header_id;





  constructor(private servicedepartment: ServicedepartmentService,
    private route: ActivatedRoute, private routing: Router) { }

  ngOnInit(): void {
    this.header_id = this.route.snapshot.params['id'];
    this.servicedepartment.getDataById(this.header_id).subscribe((data) => {
      this.department = data;


    });
  }

  onSubmit() {
    this.header_id = this.route.snapshot.params['id'];

    console.log(this.department);
    this.servicedepartment.updateData(this.header_id, this.department).subscribe(data => {
      console.log(data);
      this.routing.navigate(['/home/departments/alldepartments']);
    })
  }

}
