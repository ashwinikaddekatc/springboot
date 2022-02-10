import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SalesService } from 'src/app/services/sales.service';


@Component({
  selector: 'app-update',
 templateUrl: './Abtuiupdate.component.html',  styleUrls: ['./Abtuiupdate.component.scss']})
export class AbtuiupdateComponent implements OnInit {
  header_id;


  student;
  
  
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