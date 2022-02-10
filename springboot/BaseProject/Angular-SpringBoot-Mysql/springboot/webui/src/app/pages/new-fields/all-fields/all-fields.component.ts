import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FieldServiceService } from '../field-service.service';

@Component({
  selector: 'app-all-fields',
  templateUrl: './all-fields.component.html',
  styleUrls: ['./all-fields.component.scss']
})
export class AllFieldsComponent implements OnInit {
  columns: any[];
  isLoading: boolean = false;
  fields: any[];
  rows: any;
  temp: any[];

  constructor(
    private router: Router,
    private FieldServiceService: FieldServiceService,
  ) { }

  ngOnInit(): void {
    this.getData();
    this.columns = [
      { prop: "id", name: "Id", width: 45 },
      { prop: "name", name: " Name", width: 105 },
      { prop: "about", name: "About", width: 150 },
      { prop: "department", name: "Department", width: 190 },
      { prop: "status", name: "Status", width: 190 },
      { prop: "iscontactbase", name: "Active", width: 190 },
      { prop: "datetime", name: "Date", width: 190 },
      { prop: "city", name: "City", width: 190 },
      { prop: "contact", name: "Mobile", width: 190 },
      { prop: "userurl", name: "userUrl", width: 190 },
      { prop: "email", name: "Email", width: 190 },
      { prop: "profile", name: "Profile", width: 190 },
      { prop: "contact", name: "Mobile", width: 190 },
      { prop: "country", name: "Country", width: 190 },
      { prop: "currency", name: "Currency", width: 190 },
      { prop: "creditnumber", name: "creditNo", width: 190 },
      { prop: "vehicles", name: "Vehicles", width: 190 },
    ];
  }

  getData() {
    this.isLoading = true;
    this.FieldServiceService.getAllData().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
      //console.log(data.items);
      this.fields = data.items;
      this.rows = this.fields;
      this.temp = [...this.fields];
    });
  }

  goToAdd() {
    this.router.navigate(["/home/fields/addFields"]);
  }
  goToEdit(id: number) {
    this.router.navigate(["home/departments/updatedepartments", id]);
  }
  goToReadOnly(id: number) {
    this.router.navigate(["home/departments/readonlydepartment", id]);
  }

}
