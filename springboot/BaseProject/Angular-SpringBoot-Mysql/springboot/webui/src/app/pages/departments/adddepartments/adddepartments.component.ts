import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Mapping } from 'src/app/models/Mapping';
import { ValidationError } from 'src/app/models/ValidationError';
import { ServicedepartmentService } from '../servicedepartment.service';

@Component({
  selector: 'app-adddepartments',
  templateUrl: './adddepartments.component.html',
  styleUrls: ['./adddepartments.component.scss']
})
export class AdddepartmentsComponent implements OnInit {
  public entryForm: FormGroup;
  basic: boolean = false;
  submitted = false;
  fieldErrors: ValidationError[] = [];
  private formCode: string = 'teacher_form';
  // STORE FORM CODE IN SESSION
  public key: string = "formCode";
  public storage: Storage = sessionStorage;

  related_to = ['Menu', 'Related To'];
  page_event = ['OnClick', 'OnBlur'];
  field_type = ['textfield', 'textfield', 'textfield', 'textfield'];
  /*   mapping = ['comp1', 'comp2', 'comp3', 'comp4', 'comp5', 'comp6', 'comp7', 'comp8', 'comp9',
                'comp10', 'comp11', 'comp12', 'comp13', 'comp14', 'comp15', 'comp16', 'comp17',  	
                'comp18', 'comp19', 'comp20', 'comp21', 'comp22', 'comp23', 'comp24', 'comp25', 
                'comp_l26', 'comp_l27', 'comp_l28', 'comp_l29', 'comp_l30']; */
  mappings: Mapping[];



  constructor(
    private _fb: FormBuilder,
    private departmentservice: ServicedepartmentService,
    private router: Router,
    private route: ActivatedRoute,
    private httpService: HttpClient,
  ) { }

  ngOnInit(): void {
    this.storage.setItem(this.key, this.formCode);
    this.getMapings();

    // this.formCode = this.storage.getItem(this.key);
    // console.log('form_code in ext : ', this.formCode);



    this.entryForm = this._fb.group({
      department: this._fb.array([this.initLinesForm()]),

      // EXTENSION
      extn1: [null],
      extn2: [null],
      extn3: [null],
      extn4: [null],
      extn5: [null],
      extn6: [null],
      extn7: [null],
      extn8: [null],
      extn9: [null],
      extn10: [null],
      extn11: [null],
      extn12: [null],
      extn13: [null],
      extn14: [null],
      extn15: [null],
    });

  }
  getMapings() {
    this.httpService
      .get<Mapping[]>('./assets/json/form-setup-mapping.json')
      .subscribe(data => {
        console.log(data);
        this.mappings = data;
      }, err => console.log(err)
      )
  }

  initLinesForm() {
    return this._fb.group({
      dname: [null],
      dhead: [null],
      dcontact: [null],
      empCount: [null],
      // EXTENSION
      extn1: [null],
      extn2: [null],
      extn3: [null],
      extn4: [null],
      extn5: [null],
      extn6: [null],
      extn7: [null],
      extn8: [null],
      extn9: [null],
      extn10: [null],
      extn11: [null],
      extn12: [null],
      extn13: [null],
      extn14: [null],
      extn15: [null],


    });
  }

  get controls() {
    return (this.entryForm.get("department") as FormArray).controls;
  }

  onAddLines() {
    (<FormArray>this.entryForm.get("department")).push(this.initLinesForm());
  }

  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("department")).removeAt(index);
  }

  onSubmit() {
    console.log(this.entryForm.getRawValue()['department']);
    this.submitted = true;
    if (this.entryForm.invalid) {
      return;
    }
    this.onCreate();
  }

  onCreate() {
    this.fieldErrors = [];
    this.departmentservice.create(this.entryForm.getRawValue()['department']).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(["home/departments/alldepartments"]);
      },
      (error) => {
        console.log(error);
        // const objectArray = Object.entries(error.error.fieldErrors);
        // objectArray.forEach(([k, v]) => {
        //   console.log(k);
        //   console.log(v);
        //   this.fieldErrors.push({ field: k, message: v });
        // });
        console.log(this.fieldErrors); // this will come from backend
      }
    );
  }

  extensionBuild() {
    this.basic = !this.basic;
    //this.basic = true;
    console.log("button status: ", this.basic);
  }

  goToExt() {
    this.router.navigate(['extension/all'], { relativeTo: this.route });
  }


}
