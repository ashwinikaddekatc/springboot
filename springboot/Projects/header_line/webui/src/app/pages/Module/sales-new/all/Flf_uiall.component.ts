import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';

import { ExtensionService } from 'src/app/services/api/extension.service';
import { SalesNewService } from 'src/app/services/api/sales-new.service';

@Component({
  selector: 'app-all',
 templateUrl: './Flf_uiall.component.html',  styleUrls: ['./Flf_uiall.component.scss']})
export class Flf_uiallComponent implements OnInit {

  @ViewChild("instructorById") instructorById: TemplateRef<any>;
  @ViewChild("txId") txId: TemplateRef<any>;
  @ViewChild(DatatableComponent) table: DatatableComponent;

  basic: boolean = false;

  columns: any[];
  rows: any[];
  temp = [];

  filterData: string;
  isLoading: boolean = false;
  sales: any = [];
  constructor(private router: Router,
    private route: ActivatedRoute,
    private salesNewService: SalesNewService,
    private extentionservice: ExtensionService
  ) { }

  extention: any
  fieldname: any
  mapping: any



  ngOnInit(): void {

    this.getData();
    this.columns = [
      { prop: "tech_stack", name: "tech_stack", width: 150 },
      { prop: "object_type", name: "object_type", width: 150 },
      { prop: "sub_object_type", name: "sub_object_type", width: 150 },
]


   
  }

  getData() {
    this.isLoading = true;
    this.salesNewService.getAll().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
      console.log(data.sales);

      this.sales = data.sales


      this.temp = [...this.sales];
      this.rows = this.sales;
    });
  }


  goToAdd() {
    this.router.navigate(["../addsales"], { relativeTo: this.route });
  }
  goToEdit(id: number) {
    this.router.navigate(["../update/", id], { relativeTo: this.route });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readsales", id], { relativeTo: this.route });
  }

}

