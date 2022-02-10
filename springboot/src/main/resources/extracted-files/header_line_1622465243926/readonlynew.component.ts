import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorserviceService } from 'src/app/services/api/authorservice.service';


@Component({
  selector: 'app-readonly-new',
  templateUrl: './readonlynew.component.html',
  styleUrls: ['./readonlynew.component.scss']
})
export class ReadonlyNewComponent implements OnInit {

  basic: boolean = false;
  sales;
  header_id;
  constructor(private route: ActivatedRoute, 
    private service: AuthorserviceService, 
    private routing: Router) { }

  ngOnInit(): void {
    this.header_id = this.route.snapshot.params['id'];
    this.service.getById(this.header_id).subscribe(data => {
      this.sales = data;
      console.log(this.sales);
      
    })
  }

  goToWhoColumns() {
    this.basic = !this.basic;
}

  back() {
    this.routing.navigate(['home/author/all']);
  }

}
