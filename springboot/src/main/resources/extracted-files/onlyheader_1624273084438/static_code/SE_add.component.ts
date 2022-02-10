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
"	" + "\r\n" + 
"	dropdownval=[\"yes\",\"no\",\"dont know\"];" + "\r\n" + 
"	autocomlist= [\"1000\",\"1001\",\"1002\",\"1003\",\"1004\",\"1005\",\"1006\",\"1007\",\"1008\",\"1009\",\"1010\"];" + "\r\n" + 
"	" + "\r\n" + 
"	private formCode: string = 'sales_form';" + "\r\n" + 
"	// STORE FORM CODE IN SESSION" + "\r\n" + 
"	public key: string = \"formCode\";" + "\r\n" + 
"	public storage: Storage = sessionStorage;" + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private _fb: FormBuilder," + "\r\n" + 
"    private service: SalesService," + "\r\n" + 
"    private route: Router" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"	   this.storage.setItem(this.key, this.formCode);" + "\r\n" + 
"		console.log(this.storage.getItem(this.key));" + "\r\n" + 
"	  " + "\r\n" + 
"    this.entryForm = this._fb.group({" + "\r\n" + 
"      " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"	  " + "\r\n" + 
"     " + "\r\n" + 
"					" + "\r\n" + 
"			// EXTENSION" + "\r\n" + 
"			 extn1: [null]," + "\r\n" + 
"			 extn2: [null]," + "\r\n" + 
"			 extn3: [null]," + "\r\n" + 
"			 extn4: [null]," + "\r\n" + 
"			 extn5: [null]," + "\r\n" + 
"			 extn6: [null]," + "\r\n" + 
"			 extn7: [null]," + "\r\n" + 
"			 extn8: [null]," + "\r\n" + 
"			 extn9: [null]," + "\r\n" + 
"			 extn10: [null]," + "\r\n" + 
"			 extn11: [null]," + "\r\n" + 
"			 extn12: [null]," + "\r\n" + 
"			 extn13: [null]," + "\r\n" + 
"			 extn14: [null]," + "\r\n" + 
"			 extn15: [null]," + "\r\n" + 
"			 // FLEX        " + "\r\n" + 
"			 flex1: [null], " + "\r\n" + 
"			 flex2: [null], " + "\r\n" + 
"			 flex3: [null], " + "\r\n" + 
"			 flex4: [null]," + "\r\n" + 
"			 flex5: [null]," + "\r\n" + 
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
"  " + "\r\n" + 
"  " + "\r\n" + 
"  method:string=\"\";" + "\r\n" + 
"  onClickMe(buttonname) {" + "\r\n" + 
"" + "\r\n" + 
"  this.method=buttonname;" + "\r\n" + 
"  console.log(this.method);" + "\r\n" + 
"  " + "\r\n" + 
"  " + "\r\n" + 
"  this.service.buttonmethod(this.method).subscribe(data=>{" + "\r\n" + 
"    console.log(\"in add ts service and data is \"+data);" + "\r\n" + 
"  });" + "\r\n" + 
"   " + "\r\n" + 
"    " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" 
