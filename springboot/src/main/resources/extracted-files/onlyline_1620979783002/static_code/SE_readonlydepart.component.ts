"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DepartmentService } from 'src/app/services/api/department.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-readonlydepart'," + "\r\n" + 
 " templateUrl: './"+sbolreadonlyts1+".html',"
 
+
 "  styleUrls: ['./"+sbolreadonlyts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbolreadonlyts2+"Component implements OnInit {"
+
+
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"  department;" + "\r\n" + 
"  header_id:number;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, private service: DepartmentService, private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"" + "\r\n" + 
"    console.log(\"in the getdept \");" + "\r\n" + 
"    " + "\r\n" + 
"    this.header_id = this.route.snapshot.params['did'];" + "\r\n" + 
"    this.service.getDataById(this.header_id).subscribe(data => {" + "\r\n" + 
"      this.department = data;" + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToWhoColumns() {" + "\r\n" + 
"    this.basic = !this.basic;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"  back() {" + "\r\n" + 
"    this.routing.navigate(['home/department/']);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
