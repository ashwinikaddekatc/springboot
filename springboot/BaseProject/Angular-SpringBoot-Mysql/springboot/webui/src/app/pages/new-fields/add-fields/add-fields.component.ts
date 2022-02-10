import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Mapping } from 'src/app/models/Mapping';
import { ValidationError } from 'src/app/models/ValidationError';
import { FieldServiceService } from '../field-service.service';

@Component({
  selector: 'app-add-fields',
  templateUrl: './add-fields.component.html',
  styleUrls: ['./add-fields.component.scss']
})
export class AddFieldsComponent implements OnInit {


  public entryForm: FormGroup;
  // form = new FormGroup({
  //   name: new FormControl('', Validators.required)

  // })
  //sumbmitted = false;
  basic: boolean = false;
  fieldErrors: ValidationError[] = [];
  mappings: Mapping[];
  field_type = ['Computer', 'Mechanical', 'Electronics', 'Metatronic', 'Electrical', 'Civil'];
  field_type1 = ['Mumbai', 'Delhi', 'kashmir', 'Benglore', 'Pune', 'Kolhapur'];
  submitted: boolean;


  constructor(
    private _fb: FormBuilder,
    private fieldservice: FieldServiceService,
    private router: Router,
    private route: ActivatedRoute,
    private httpService: HttpClient,

  ) { }

  ngOnInit(): void {
    this.getMapings();
    //this.storage.setItem(this.key, this.formCode);

    this.entryForm = this._fb.group({
      fields: this._fb.array([this.initLinesForm()]),
    });
  }

  public mask = {
    guide: true,
    showMask: true,
    mask: [/\d/, /\d/, /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/]
  };

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
      name: [null],
      about: [null],
      department: [null],
      status: [null],
      iscontactbase: [null],
      datetime: [null],
      city: [null],
      contact: [null],
      userurl: [null],
      email: [null],
      profile: [null],
      country: [null],
      currency: [null],
      creditnumber: [null],
      vehicles: [null],
    });
  }

  get controls() {
    return (this.entryForm.get("fields") as FormArray).controls;
  }

  onAddLines() {
    (<FormArray>this.entryForm.get("fields")).push(this.initLinesForm());
  }

  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("fields")).removeAt(index);
  }

  onSubmit() {

    console.log(this.entryForm.getRawValue()['fields']);
    this.submitted = true;
    if (this.entryForm.invalid) {
      return;
    }
    this.onCreate();
  }
  onCreate() {
    this.fieldErrors = [];
    this.fieldservice.create(this.entryForm.getRawValue()['fields']).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(["home/fields/allFields"]);
      },
      (error) => {
        console.log(error);
        const objectArray = Object.entries(error.error.fieldErrors);
        // objectArray.forEach(([k, v]) => {
        //   console.log(k);
        //   console.log(v);
        //   this.fieldErrors.push({ field: k, message: v });
        // });
        // console.log(this.fieldErrors); // this will come from backend
      }
    );
  }



}
