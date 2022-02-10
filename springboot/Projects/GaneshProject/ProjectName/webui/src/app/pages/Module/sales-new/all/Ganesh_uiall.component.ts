import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-all',
 templateUrl: './Ganesh_uiall.component.html',  styleUrls: ['./Ganesh_uiall.component.scss']})
export class Ganesh_uiall implements OnInit {



  basic: boolean = false;

  columns: any[];
  rows: any[];
  temp = [];

  filterData: string;
  isLoading: boolean = false;
  sales: any = [];
  constructor(private router: Router,
    private route: ActivatedRoute,
    private salesNewService: SalesService) { }



  ngOnInit(): void {
    this.getData();
    this.columns = [
      { prop: "label1", name: "label1", width: 150 },
      { prop: "label2", name: "label2", width: 150 },
      { prop: "label3", name: "label3", width: 150 },
      { prop: "label4", name: "label4", width: 150 },
    ];
  }

  getData() {
    this.isLoading = true;
    this.salesNewService.getAllData().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
      //console.log(data.items);
      this.sales = data.items;
      this.rows = this.sales;
      this.temp = [...this.sales];
    });
  }
  goToAdd() {
    this.router.navigate(["../addsales"], { relativeTo: this.route });
  }
  goToEdit(id: number) {
    this.router.navigate(["../update", id], { relativeTo: this.route });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readsales", id], { relativeTo: this.route });
  }

}

