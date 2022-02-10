"" + "\r\n" + 
"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { FormArray, FormBuilder, FormGroup } from '@angular/forms';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { AuthorserviceService } from 'src/app/services/api/authorservice.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"interface errorMsg {" + "\r\n" + 
"  field: any;" + "\r\n" + 
"  message: any;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-addsales'," + "\r\n" + 
 " templateUrl: './"+sbhladdts1+".html',"
 
+
 "  styleUrls: ['./"+sbhladdts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhladdts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"" + "\r\n" + 
"	dropdownval=[\"yes\",\"no\",\"dont know\"];" + "\r\n" + 
"	autocomlist= [\"1000\",\"1001\",\"1002\",\"1003\",\"1004\",\"1005\",\"1006\",\"1007\",\"1008\",\"1009\",\"1010\"];" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"  private formCode: string = 'sales_form';" + "\r\n" + 
"  // STORE FORM CODE IN SESSION" + "\r\n" + 
"  public key: string = \"formCode\";" + "\r\n" + 
"  public storage: Storage = sessionStorage;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"  public entryForm: FormGroup;" + "\r\n" + 
"  submitted = false;" + "\r\n" + 
"  errorMsg: errorMsg[] = [];" + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private _fb: FormBuilder," + "\r\n" + 
"    private router: Router," + "\r\n" + 
"    private route: ActivatedRoute," + "\r\n" + 
"    private authorservice:AuthorserviceService" + "\r\n" + 
"" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit() {" + "\r\n" + 
"" + "\r\n" + 
"    this.storage.setItem(this.key, this.formCode);" + "\r\n" + 
"    console.log(this.storage.getItem(this.key));" + "\r\n" + 
"" + "\r\n" + 
"    this.entryForm = this._fb.group({" + "\r\n" + 
"      afname: [null]," + "\r\n" + 
"      lname: [null]," + "\r\n" + 
"      " + "\r\n" + 
"" + "\r\n" + 
"      book: this._fb.array([this.initLinesForm()])," + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"      //Extentions" + "\r\n" + 
"      extn1: [null]," + "\r\n" + 
"      extn2: [null]," + "\r\n" + 
"      extn3: [null]," + "\r\n" + 
"      extn4: [null]," + "\r\n" + 
"      extn5: [null]," + "\r\n" + 
"      extn6: [null]," + "\r\n" + 
"      extn7: [null]," + "\r\n" + 
"      extn8: [null]," + "\r\n" + 
"      extn9: [null]," + "\r\n" + 
"      extn10: [null]," + "\r\n" + 
"      extn11: [null]," + "\r\n" + 
"      extn12: [null]," + "\r\n" + 
"      extn13: [null]," + "\r\n" + 
"      extn14: [null]," + "\r\n" + 
"      extn15: [null]," + "\r\n" + 
"      // FLEX" + "\r\n" + 
"      flex1: [null]," + "\r\n" + 
"      flex2: [null]," + "\r\n" + 
"      flex3: [null]," + "\r\n" + 
"      flex4: [null]," + "\r\n" + 
"      flex5: [null]," + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  initLinesForm() {" + "\r\n" + 
"    return this._fb.group({" + "\r\n" + 
"" + "\r\n" + 
"      bname: [null]," + "\r\n" + 
"      btitle: [null]," + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    console.warn(\"calling submit\");" + "\r\n" + 
"" + "\r\n" + 
"    //console.log(this.entryForm.value);" + "\r\n" + 
"    this.submitted = true;" + "\r\n" + 
"    if (this.entryForm.invalid) {" + "\r\n" + 
"      return;" + "\r\n" + 
"    }" + "\r\n" + 
"    this.onCreate();" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onCreate() {" + "\r\n" + 
"    console.warn(\"in the oncreate \");" + "\r\n" + 
"" + "\r\n" + 
"    this.authorservice.create(this.entryForm.value).subscribe(data => {" + "\r\n" + 
"      console.log(data)" + "\r\n" + 
"      this.router.navigate([\"/home/author/all\"]);" + "\r\n" + 
"    }," + "\r\n" + 
"      (error) => {" + "\r\n" + 
"        console.log(error);" + "\r\n" + 
"      }" + "\r\n" + 
"    );" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  get controls() {" + "\r\n" + 
"    return (this.entryForm.get(\"book\") as FormArray).controls;" + "\r\n" + 
"  }" + "\r\n" + 
"  onRemoveLines(index: number) {" + "\r\n" + 
"    (<FormArray>this.entryForm.get(\"book\")).removeAt(index);" + "\r\n" + 
"  }" + "\r\n" + 
"  onAddLines() {" + "\r\n" + 
"    (<FormArray>this.entryForm.get(\"book\")).push(this.initLinesForm());" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  extensionBuild() {" + "\r\n" + 
"    this.basic = !this.basic;" + "\r\n" + 
"    //this.basic = true;" + "\r\n" + 
"    console.log(\"button status: \", this.basic);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToExt() {" + "\r\n" + 
"    this.router.navigate(['extension/all'], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
"  method:string=\"\";" + "\r\n" + 
"  onClickMe(buttonname) {" + "\r\n" + 
"" + "\r\n" + 
"  this.method=buttonname;" + "\r\n" + 
"  console.log(this.method);" + "\r\n" + 
"  " + "\r\n" + 
"  " + "\r\n" + 
"  this.authorservice.buttonmethod(this.method).subscribe(data=>{" + "\r\n" + 
"    console.log(\"in add ts service and data is \"+data);" + "\r\n" + 
"  });" + "\r\n" + 
"   " + "\r\n" + 
"    " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
