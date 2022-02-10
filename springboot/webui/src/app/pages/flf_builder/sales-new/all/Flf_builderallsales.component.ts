import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { AuthorserviceService } from 'src/app/services/api/authorservice.service';

import { ExtensionService } from 'src/app/services/api/extension.service';


@Component({
  selector: 'app-all',
 templateUrl: './Flf_builderall.component.html', 
  styleUrls: ['./Flf_builderall.component.scss']})
export class Flf_builderallsalesComponent implements OnInit {

 
  basic: boolean = false;

  columns: any[];
  rows: any[];
  temp = [];

  filterData: string;
  isLoading: boolean = false;
  sales: any = [];
  constructor(private router: Router,
    private route: ActivatedRoute,
    private authorservice: AuthorserviceService,
    private extentionservice: ExtensionService
  ) { }

  extention: any
  fieldname: any
  mapping: any



  ngOnInit(): void {

    this.getData();
    this.columns = [
     
      { prop: "techstack", name: "techstack", width: 150 },
      { prop: "object_type", name: "object_type", width: 150 },
      { prop: "sub_object_type", name: "sub_object_type", width: 150 },
      { prop: "isbuild", name: "isbuild", width: 150 },
       ];
     
   
  }

  getData() {
    console.log("in  getdata");
    
    this.isLoading = true;
    this.authorservice.getAll().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
      console.log(data.author);

      this.sales = data.author


      this.temp = [...this.sales];
      this.rows = this.sales;
    });
  }


  goToAdd() {
    this.router.navigate(["../add"], { relativeTo: this.route });
  }
  goToEdit(id: number) {
    this.router.navigate(["../edit/", id], { relativeTo: this.route });
  }
  goToReadOnly(id: number) {
    this.router.navigate(["../readonly", id], { relativeTo: this.route });
  }

}

