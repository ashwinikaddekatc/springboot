import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicedepartmentService } from '../servicedepartment.service';

@Component({
  selector: 'app-alldepartments',
  templateUrl: './alldepartments.component.html',
  styleUrls: ['./alldepartments.component.scss']
})
export class AlldepartmentsComponent implements OnInit {

  columns: any[];
  isLoading: boolean = false;
  department: any = [];
  rows: any;
  temp: any[];


  constructor(
    private servicedepartment: ServicedepartmentService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getData();
    this.columns = [
      { prop: "id", name: "Id", width: 65 },
      { prop: "dname", name: " DName", width: 105 },
      { prop: "dhead", name: "Dhead", width: 150 },
      { prop: "dcontact", name: " Dcontact", width: 190 },
      { prop: "empCount", name: "EmpCount", width: 190 },
    ];
  }
  getData() {
    this.isLoading = true;
    this.servicedepartment.getAllData().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
      //console.log(data.items);
      this.department = data.items;
      this.rows = this.department;
      this.temp = [...this.department];
    });
  }

  goToAdd() {
    this.router.navigate(["home/departments/adddepartments"]);
  }
  goToEdit(id: number) {
    this.router.navigate(["home/departments/updatedepartments", id]);
  }
  goToReadOnly(id: number) {
    this.router.navigate(["home/departments/readonlydepartment", id]);
  }

}
