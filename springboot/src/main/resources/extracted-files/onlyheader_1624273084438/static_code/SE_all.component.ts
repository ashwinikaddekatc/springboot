"import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"import { ExtensionService } from 'src/app/services/api/extension.service';" + "\r\n" + 
"import { DatatableComponent } from '@swimlane/ngx-datatable';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-all'," + "\r\n" + 
 " templateUrl: './"+sbohallts1+".html',"
 
+
 "  styleUrls: ['./"+sbohallts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohallts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"" + "\r\n" + 
"	@ViewChild(\"instructorById\") instructorById: TemplateRef<any>;" + "\r\n" + 
"@ViewChild(\"txId\") txId: TemplateRef<any>;" + "\r\n" + 
"@ViewChild(DatatableComponent) table: DatatableComponent;" + "\r\n" + 
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"  columns: any[];" + "\r\n" + 
"  rows: any[];" + "\r\n" + 
"  temp = [];" + "\r\n" + 
"   extention: any" + "\r\n" + 
" fieldname: any" + "\r\n" + 
" mapping: any" + "\r\n" + 
"" + "\r\n" + 
"  filterData: string;" + "\r\n" + 
"  isLoading: boolean = false;" + "\r\n" + 
"  sales: any = [];" + "\r\n" + 
"  constructor(private router: Router," + "\r\n" + 
"    private route: ActivatedRoute," + "\r\n" + 
"    private salesNewService: SalesService," + "\r\n" + 
"	 private extentionservice: ExtensionService) { }" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.getData();" + "\r\n" + 
"    this.columns = [" + "\r\n" + 
"      " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"    ];" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	  //adding extentions to array if its true" + "\r\n" + 
"		this.extentionservice.getAll().subscribe(ext => {" + "\r\n" + 
"	console.warn(`total extentions    :`, ext)" + "\r\n" + 
"	//   let id=ext.id" + "\r\n" + 
"	// console.log(\"for loop id \", id);" + "\r\n" + 
"	this.extention = ext" + "\r\n" + 
"	for (let id of this.extention) {" + "\r\n" + 
"    if (id.isActive == true) {" + "\r\n" + 
"      console.warn(id);" + "\r\n" + 
"      this.fieldname = id.field_name" + "\r\n" + 
"      this.mapping = id.mapping" + "\r\n" + 
"      console.warn(this.fieldname, this.mapping)," + "\r\n" + 
"        this.columns.push(" + "\r\n" + 
"          { prop: this.mapping, name: this.fieldname, width: 90 })" + "\r\n" + 
"  }" + "\r\n" + 
"  }" + "\r\n" + 
"})  " + "\r\n" + 
"	" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  getData() {" + "\r\n" + 
"    this.isLoading = true;" + "\r\n" + 
"    this.salesNewService.getAllData().subscribe((data) => {" + "\r\n" + 
"      console.log(`calling getall service`);" + "\r\n" + 
"" + "\r\n" + 
"      this.isLoading = false;" + "\r\n" + 
"      console.log(data);" + "\r\n" + 
"      //console.log(data.items);" + "\r\n" + 
"      this.sales = data.sales;" + "\r\n" + 
"      this.rows = this.sales;" + "\r\n" + 
"      this.temp = [...this.sales];" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToAdd() {" + "\r\n" + 
"    this.router.navigate([\"../addsales\"], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToEdit(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../update\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToReadOnly(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../readsales\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" 
