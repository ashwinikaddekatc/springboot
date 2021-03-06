import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-readonly',
 templateUrl: './Testonlyheaderreadonly.component.html',  styleUrls: ['./Testonlyheaderreadonly.component.scss']})
export class TestonlyheaderreadonlyComponent implements OnInit {   basic: boolean = false;
  sales;
  header_id;
  constructor(private route: ActivatedRoute, private service: SalesService, private routing: Router) { }

  ngOnInit(): void {
    this.header_id = this.route.snapshot.params['id'];
    this.service.getDataById(this.header_id).subscribe(data => {
      this.sales = data;
    })
  }
  back() {
    this.routing.navigate(['home/sales-new/all']);
  }
  goToWhoColumns() {
    this.basic = true
}

}