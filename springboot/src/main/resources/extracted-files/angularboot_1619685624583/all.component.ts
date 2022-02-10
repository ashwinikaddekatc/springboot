import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';
import { ExtensionService } from 'src/app/services/api/extension.service';

@Component({
  selector: 'app-all',
  templateUrl: './all.component.html',
  styleUrls: ['./all.component.scss']
})
export class AllComponent implements OnInit {


	@ViewChild("instructorById") instructorById: TemplateRef<any>;
@ViewChild("txId") txId: TemplateRef<any>;
@ViewChild(DatatableComponent) table: DatatableComponent;

  basic: boolean = false;

  columns: any[];
  rows: any[];
  temp = [];
   extention: any
 fieldname: any
 mapping: any

  filterData: string;
  isLoading: boolean = false;
  sales: any = [];
  constructor(private router: Router,
    private route: ActivatedRoute,
    private salesNewService: SalesService,
	 private extentionservice: ExtensionService) { }

	

  ngOnInit(): void {
    this.getData();
    this.columns = [
      { prop: "id", name: "Actions", width: 65 },
      { prop: "name", name: " Name", width: 105 },
      { prop: "department", name: "Dept", width: 150 },
      { prop: "joining_date", name: " Date", width: 190 },
      { prop: "status", name: "Status", width: 190 },
    ];
	
	
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
    this.isLoading = true;
    this.salesNewService.getAllData().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
      //console.log(data.items);
      this.sales = data.sales;
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


