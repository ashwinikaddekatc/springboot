import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatatableComponent } from '@swimlane/ngx-datatable';
import { AuthorserviceService } from 'src/app/services/api/authorservice.service';

import { ExtensionService } from 'src/app/services/api/extension.service';


@Component({
  selector: 'app-all',
  templateUrl: './all.component.html',
  styleUrls: ['./all.component.scss']
})
export class AllsalesComponent implements OnInit {


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
    private authorservice: AuthorserviceService,
    private extentionservice: ExtensionService
  ) { }

  extention: any
  fieldname: any
  mapping: any



  ngOnInit(): void {

    this.getData();
    this.columns = [
     
      { prop: "afname", name: " fname", width: 90 },
      { prop: "lname", name: "lname", width: 90 }
       ];
      // { prop: "uploadprofile", name: "uploadprofile", width: 90 },



        //adding extentions to array if its true
      this.extentionservice.getAll().subscribe(ext => {
        console.warn(`total extentions    :`, ext)
        //   let id=ext.id
        // console.log("for loop id ", id);
        this.extention = ext
        for (let id of this.extention) {
          if (id.isActive == true) {
            console.warn(id);
            this.fieldname = id.field_name
            this.mapping = id.mapping
            console.warn(this.fieldname, this.mapping),
              this.columns.push(
                { prop: this.mapping, name: this.fieldname, width: 90 })
        }
        }
      })  
   
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


