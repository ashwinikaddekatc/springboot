"import { HttpErrorResponse } from '@angular/common/http';" + "\r\n" + 
"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { SalesNewService } from 'src/app/services/api/sales-new.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-edit'," + "\r\n" + 
 " templateUrl: './"+sbhleditts1+".html',"
 
+
 "  styleUrls: ['./"+sbhleditts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhleditts2+"Component implements OnInit {"
+
"  updated = false;" + "\r\n" + 
"  sales ;" + "\r\n" + 
"  sid: number;" + "\r\n" + 
"  salesperson;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  constructor(private router: Router," + "\r\n" + 
"    private route:ActivatedRoute," + "\r\n" + 
"    private salesservice:SalesNewService" + "\r\n" + 
"    ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"" + "\r\n" + 
"    this.sales;" + "\r\n" + 
"    this.sid = this.route.snapshot.params[\"id\"];" + "\r\n" + 
"    console.log(\"update with id = \", this.sid);" + "\r\n" + 
"    this.getById(this.sid);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  getById(sid: number) {" + "\r\n" + 
"    this.salesservice.getDataById(sid).subscribe((data) => {" + "\r\n" + 
"      this.sales = data;" + "\r\n" + 
"      this.salesperson=data.salesperson;" + "\r\n" + 
"      //this.students = data.students;" + "\r\n" + 
"      " + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"    update() {" + "\r\n" + 
"      this.salesservice.update(this.sid, this.sales).subscribe(" + "\r\n" + 
"        (data) => {" + "\r\n" + 
"          console.log(data);" + "\r\n" + 
"          this.router.navigate([\"/home/sales-new/all\"]);" + "\r\n" + 
"        }," + "\r\n" + 
"        (error: HttpErrorResponse) => {" + "\r\n" + 
"          console.log(error.message);" + "\r\n" + 
"        }" + "\r\n" + 
"      );" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    onSubmit() {" + "\r\n" + 
"      this.updated = true;" + "\r\n" + 
"      this.update();" + "\r\n" + 
"    }" + "\r\n" + 
"  " + "\r\n" + 
"    " + "\r\n" + 
"" + "\r\n" + 
"}" 
