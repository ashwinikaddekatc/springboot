"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-readonly'," + "\r\n" + 
 " templateUrl: './"+readonlyts1+".html',"
+
 "  styleUrls: ['./"+readonlyts1+".scss']"
+
"})" + "\r\n" + 
"export class"+readonlyts2+" implements OnInit {"
+
"" + "\r\n" + 
"  sales;" + "\r\n" + 
"  header_id;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, private service: SalesService, private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.header_id = this.route.snapshot.params['id'];" + "\r\n" + 
"    this.service.getDataById(this.header_id).subscribe(data => {" + "\r\n" + 
"      this.sales = data;" + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"  back() {" + "\r\n" + 
"    this.routing.navigate(['home/sales-new/all']);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
