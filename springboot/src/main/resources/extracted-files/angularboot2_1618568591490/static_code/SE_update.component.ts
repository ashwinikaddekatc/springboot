"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"import { sales } from '../sales';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-update'," + "\r\n" + 
 " templateUrl: './"+sbohupdatets1+".html',"
 
+
 "  styleUrls: ['./"+sbohupdatets1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohupdatets2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  header_id;" + "\r\n" + 
"  //student;" + "\r\n" + 
"" + "\r\n" + 
"  student: sales;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, private service: SalesService, private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.header_id = this.route.snapshot.params['id'];" + "\r\n" + 
"    //console.log(this.header_id);" + "\r\n" + 
"    this.service.getDataById(this.header_id).subscribe((data) => {" + "\r\n" + 
"      this.student = data;" + "\r\n" + 
"      " + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"" + "\r\n" + 
"    console.log(this.student);" + "\r\n" + 
"    this.service.updateData(this.header_id, this.student).subscribe(data => {" + "\r\n" + 
"      console.log(data);" + "\r\n" + 
"      this.routing.navigate(['/home/sales-new/all']);" + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"}" 
