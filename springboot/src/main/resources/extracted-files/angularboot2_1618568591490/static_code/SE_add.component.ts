"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { FormArray, FormBuilder, FormGroup } from '@angular/forms';" + "\r\n" + 
"import { Router } from '@angular/router';" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-add'," + "\r\n" + 
 " templateUrl: './"+sbohaddts1+".html',"
 
+
 "  styleUrls: ['./"+sbohaddts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohaddts2+"Component implements OnInit {"
+
"  //create a object of entry form" + "\r\n" + 
"  public entryForm: FormGroup;" + "\r\n" + 
"  submitted = false;" + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private _fb: FormBuilder," + "\r\n" + 
"    private service: SalesService," + "\r\n" + 
"    private route: Router" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.entryForm = this._fb.group({" + "\r\n" + 
"      name: [null]," + "\r\n" + 
"      department: [null]," + "\r\n" + 
"      joining_date: [null]," + "\r\n" + 
"      status: [null]," + "\r\n" + 
"     " + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"   " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
" " + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"    console.log(\"call on submit\", this.entryForm.value);" + "\r\n" + 
"    this.submitted = true;" + "\r\n" + 
"    if (this.entryForm.invalid) {" + "\r\n" + 
"      console.log(\"invalid\");" + "\r\n" + 
"" + "\r\n" + 
"      return;" + "\r\n" + 
"    }" + "\r\n" + 
"    this.onCreate();" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  onCreate() {" + "\r\n" + 
"" + "\r\n" + 
"    this.service.addData(this.entryForm.value).subscribe(" + "\r\n" + 
"      (data) => {" + "\r\n" + 
"        console.log(\"my dta\", data);" + "\r\n" + 
"        this.route.navigate([\"home/sales-new/all\"]);" + "\r\n" + 
"      }," + "\r\n" + 
"      (error) => {" + "\r\n" + 
"        console.log(error);" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"      });" + "\r\n" + 
"" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" 
