import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SalesService } from 'src/app/services/sales.service';
import { Ganesh_uisales } from '../Test_wireframesales';

@Component({
  selector: 'app-update',
 templateUrl: './Ganesh_uiupdate.component.html',  styleUrls: ['./Ganesh_uiupdate.component.scss']})
export class Ganesh_uiupdate implements OnInit {
  header_id;
  //student;

  student: Ganesh_uisales;
  constructor(private route: ActivatedRoute, private service: SalesService, private routing: Router) { }

  ngOnInit(): void {
    this.header_id = this.route.snapshot.params['id'];
    //console.log(this.header_id);
    this.service.getDataById(this.header_id).subscribe((data) => {
      this.student = data;
      

    });
  }



  onSubmit() {

    console.log(this.student);
    this.service.updateData(this.header_id, this.student).subscribe(data => {
      console.log(data);
      this.routing.navigate(['/home/sales-new/all']);
    })
  }
}