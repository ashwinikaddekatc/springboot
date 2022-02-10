import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.scss']
})
export class ShowComponent implements OnInit {
  columns;
  rows;
  constructor(private service: SalesService, private route: Router) { }

  ngOnInit(): void {
    this.service.getAllData().subscribe(data => { this.rows = data; });
    this.columns = [
      /*  {prop:"form_id"         , name: "Actions"           , width:65}, */
      { prop: "id", name: "Id", width: 120 },
      { prop: "name", name: "Name", width: 150 },
      { prop: "department", name: "Department", width: 190 },
      { prop: "joining_date", name: "Date_Of_Joining", width: 190 },
      { prop: "status", name: "Status", width: 200 },
      /* {prop:"form_id"         , name: "Go To Form"}, */

    ];

  }
  goToAdd() {
    this.route.navigate(['/home/add']);
  }

  goToReadOnly(id: number) {
    console.log(id);
  }
  goToEdit(id: number) {
    this.route.navigate(['/home/update', id]);
  }

}
