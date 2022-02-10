"import { HttpErrorResponse } from '@angular/common/http';" + "\r\n" + 
"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DepartmentService } from 'src/app/services/api/department.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-editdepart'," + "\r\n" + 
 " templateUrl: './"+sboleditts1+".html',"
 
+
 "  styleUrls: ['./"+sboleditts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sboleditts2+"Component implements OnInit {"
+
+
"  updated = false;" + "\r\n" + 
"  depart:any=[]" + "\r\n" + 
"  did: number;" + "\r\n" + 
"  " + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private router: Router," + "\r\n" + 
"    private route:ActivatedRoute," + "\r\n" + 
"    private departserv:DepartmentService" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.depart;" + "\r\n" + 
"    this.did = this.route.snapshot.params[\"did\"];" + "\r\n" + 
"    console.warn(\"my id\"+this.did);" + "\r\n" + 
"    " + "\r\n" + 
"    console.log(\"update with id = \", this.did);" + "\r\n" + 
"    this.getById(this.did);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  getById(id) {" + "\r\n" + 
"    this.departserv.getDataById(id).subscribe((data) => {" + "\r\n" + 
"      this.depart = data;" + "\r\n" + 
"      console.warn(\"getby data   \" +this.depart);" + "\r\n" + 
"      " + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  update() {" + "\r\n" + 
"    this.departserv.update(this.did,this.depart).subscribe(" + "\r\n" + 
"      (data) => {" + "\r\n" + 
"        console.log(data);" + "\r\n" + 
"        this.router.navigate([\"/home/department/\"]);" + "\r\n" + 
"      }," + "\r\n" + 
"      (error: HttpErrorResponse) => {" + "\r\n" + 
"        console.log(error.message);" + "\r\n" + 
"      }" + "\r\n" + 
"    );" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"    this.updated = true;" + "\r\n" + 
"    this.update();" + "\r\n" + 
"  }" + "\r\n" + 
"}" 
