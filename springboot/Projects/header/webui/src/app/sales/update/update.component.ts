import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';
import { sales } from '../sales';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.scss']
})
export class UpdateComponent implements OnInit {

  header_id;
  //student;
  student: sales;
  constructor(private route: ActivatedRoute, private service: SalesService, private routing: Router) { }

  ngOnInit(): void {
    this.header_id = this.route.snapshot.params['id'];
    //console.log(this.header_id);
    this.service.getDataById(this.header_id).subscribe((data) => { this.student = data; });
  }



  onSubmit() {

    console.log(this.student);
    this.service.updateData(this.header_id, this.student).subscribe(data => {
      console.log(data);
      this.routing.navigate(['/home/sales-new/all']);
    })
  }
}
