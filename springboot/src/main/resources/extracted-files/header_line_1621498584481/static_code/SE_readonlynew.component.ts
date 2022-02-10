"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { SalesNewService } from 'src/app/services/api/sales-new.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-readonly-new'," + "\r\n" + 
 " templateUrl: './"+sbhlreadonlyts1+".html',"
 
+
 "  styleUrls: ['./"+sbhlreadonlyts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhlreadonlyts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"  sales;" + "\r\n" + 
"  header_id;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, private service: SalesNewService, private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.header_id = this.route.snapshot.params['id'];" + "\r\n" + 
"    this.service.getDataById(this.header_id).subscribe(data => {" + "\r\n" + 
"      this.sales = data;" + "\r\n" + 
"      console.log(this.sales);" + "\r\n" + 
"      " + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToWhoColumns() {" + "\r\n" + 
"    this.basic = !this.basic;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"  back() {" + "\r\n" + 
"    this.routing.navigate(['home/sales-new/all']);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
