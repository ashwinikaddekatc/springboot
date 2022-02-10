"import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DatatableComponent } from '@swimlane/ngx-datatable';" + "\r\n" + 
"" + "\r\n" + 
"import { DepartmentService } from 'src/app/services/api/department.service';" + "\r\n" + 
"import { ExtensionService } from 'src/app/services/api/extension.service';" + "\r\n" + 
"import { Department } from '../departmentmodel';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-alldepart'," + "\r\n" + 
 " templateUrl: './"+sbolallts1+".html',"
 
+
 "  styleUrls: ['./"+sbolallts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbolallts2+"Component implements OnInit {"
+
+
"" + "\r\n" + 
"  @ViewChild(\"instructorById\") instructorById: TemplateRef<any>;" + "\r\n" + 
"  @ViewChild(\"txId\") txId: TemplateRef<any>;" + "\r\n" + 
"  @ViewChild(DatatableComponent) table: DatatableComponent;" + "\r\n" + 
"" + "\r\n" + 
"  " + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"  columns: any[];" + "\r\n" + 
"  rows: any[];" + "\r\n" + 
"  temp = [];" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  extention: any" + "\r\n" + 
"  fieldname: any" + "\r\n" + 
"  mapping: any" + "\r\n" + 
"" + "\r\n" + 
"  filterData: string;" + "\r\n" + 
"  isLoading: boolean = false;" + "\r\n" + 
"  Department:Department[];" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private router: Router," + "\r\n" + 
"    private route: ActivatedRoute," + "\r\n" + 
"    private deptserv:DepartmentService," + "\r\n" + 
"    private extentionservice: ExtensionService" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"" + "\r\n" + 
"    this.getData();" + "\r\n" + 
"    this.columns = [" + "\r\n" + 
"      { prop: \"did\", name: \"id\", width: 65 }," + "\r\n" + 
"      { prop: \"dname\", name: \" Name\", width: 105 }," + "\r\n" + 
"      { prop: \"dhead\", name: \"head\", width: 150 }," + "\r\n" + 
"      { prop: \"dcontact\", name: \" contact\", width: 190 }," + "\r\n" + 
"      { prop: \"no_ofEmp\", name: \"noofEmp\", width: 190 }," + "\r\n" + 
"    ];" + "\r\n" + 
"" + "\r\n" + 
"    //adding extentions to array if its true" + "\r\n" + 
"    this.extentionservice.getAll().subscribe(ext => {" + "\r\n" + 
"      console.warn(`total extentions    :`, ext)" + "\r\n" + 
"      //   let id=ext.id" + "\r\n" + 
"      // console.log(\"for loop id \", id);" + "\r\n" + 
"      this.extention = ext" + "\r\n" + 
"      for (let id of this.extention) {" + "\r\n" + 
"        if (id.isActive == true) {" + "\r\n" + 
"          console.warn(id);" + "\r\n" + 
"          this.fieldname = id.field_name" + "\r\n" + 
"          this.mapping = id.mapping" + "\r\n" + 
"          console.warn(this.fieldname, this.mapping)," + "\r\n" + 
"            this.columns.push(" + "\r\n" + 
"              { prop: this.mapping, name: this.fieldname, width: 90 })" + "\r\n" + 
"      }" + "\r\n" + 
"      }" + "\r\n" + 
"    })  " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  getData() {" + "\r\n" + 
"    this.isLoading = true;" + "\r\n" + 
"    this.deptserv.getAll().subscribe((data) => {" + "\r\n" + 
"      console.log(`calling getall service`);" + "\r\n" + 
"" + "\r\n" + 
"      this.isLoading = false;" + "\r\n" + 
"      console.log(data);" + "\r\n" + 
"     console.log(data.department);" + "\r\n" + 
"     " + "\r\n" + 
"      this.Department = data.department" + "\r\n" + 
"      this.temp = [...this.Department];" + "\r\n" + 
"      this.rows = this.Department;" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToAdd() {" + "\r\n" + 
"    this.router.navigate([\"../adddept\"], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToEdit(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../editdep/\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToReadOnly(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../readdep\", id],{ relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"}" 
