import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Department } from 'src/app/models/departmentmodel';
import { DepartmentService } from 'src/app/services/api/department.service';

@Component({
  selector: 'app-viewdept',
  templateUrl: './viewdept.component.html',
  styleUrls: ['./viewdept.component.scss']
})
export class ViewdeptComponent implements OnInit {


  basic: boolean = false;

  columns: any[];
  rows: any[];
  temp = [];

  filterData: string;
  isLoading: boolean = false;
  Department:Department[];

  constructor(private router: Router,
    private route: ActivatedRoute,
    private deptserv:DepartmentService
    ) { }

  ngOnInit(): void {
    this.getData();
    this.columns = [
      { prop: "did", name: "id", width: 65 },
      { prop: "dname", name: " Name", width: 105 },
      { prop: "dhead", name: "head", width: 150 },
      { prop: "dcontact", name: " contact", width: 190 },
      { prop: "no_ofEmp", name: "noofEmp", width: 190 },
    ];
  }

  getData() {
    this.isLoading = true;
    this.deptserv.getAll().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
     console.log(data.department);
     
      this.Department = data.department
      this.temp = [...this.Department];
      this.rows = this.Department;
    });
  }

  goToAdd() {
    this.router.navigate(["../dept/adddept"], { relativeTo: this.route });
  }
  goToEdit(id: number) {
    this.router.navigate(["../dept/updatedep/", id], { relativeTo: this.route });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../dept/readdep", id],{ relativeTo: this.route });
  }


}
