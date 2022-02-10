import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-all',
  templateUrl: './all.component.html',
  styleUrls: ['./all.component.scss']
})
export class AllComponent implements OnInit {




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
      { prop: "id", name: "Actions", width: 65 },
      { prop: "name", name: " Name", width: 105 },
      { prop: "department", name: "Dept", width: 150 },
      { prop: "joining_date", name: " Date", width: 190 },
      { prop: "status", name: "Status", width: 190 },
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


