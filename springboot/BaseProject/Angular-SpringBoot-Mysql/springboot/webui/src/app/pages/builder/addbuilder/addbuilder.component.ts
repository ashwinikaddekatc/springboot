import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ValidationError } from 'src/app/models/ValidationError';
import { ServicebuilderService } from '../servicebuilder.service';

@Component({
  selector: 'app-addbuilder',
  templateUrl: './addbuilder.component.html',
  styleUrls: ['./addbuilder.component.scss']
})
export class AddbuilderComponent implements OnInit {
  public entryForm: FormGroup;
  submitted = false;
  fieldErrors: ValidationError[] = [];

  constructor(
    private _fb: FormBuilder,
    private builderservice: ServicebuilderService,
    private router: Router,
    private route: ActivatedRoute,
    private httpService: HttpClient,
  ) { }

  ngOnInit(): void {

    this.entryForm = this._fb.group({
      builder: this._fb.array([this.initLinesForm()]),
    });
  }

  initLinesForm() {
    return this._fb.group({
      form_id: [null],
      form_version: [null],
      header_id: [null],
      comp1: [null],
      comp2: [null],
      comp3: [null],
      comp4: [null],
      comp5: [null],
      comp6: [null],
      comp7: [null],
      comp8: [null],
      comp9: [null],
      comp10: [null],
      comp11: [null],
      comp12: [null],
      comp13: [null],
      comp14: [null],
      comp15: [null],
      comp16: [null],
      comp17: [null],
      comp18: [null],
      comp19: [null],
      comp20: [null],
      comp21: [null],
      comp22: [null],
      comp23: [null],
      comp24: [null],
      comp25: [null],
      comp_l26: [null],
      comp_l27: [null],
      comp_l28: [null],
      comp_l29: [null],
      comp_l30: [null]
    });
  }
  get controls() {
    return (this.entryForm.get("builder") as FormArray).controls;
  }

  onAddLines() {
    (<FormArray>this.entryForm.get("builder")).push(this.initLinesForm());
  }
  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("builder")).removeAt(index);
  }

  onSubmit() {
    console.log(this.entryForm.getRawValue()['builder']);
    this.submitted = true;
    if (this.entryForm.invalid) {
      return;
    }
    this.onCreate();
  }
  onCreate() {
    this.fieldErrors = [];
    this.builderservice.create(this.entryForm.getRawValue()['builder']).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(["home/builder/allbuilder"]);
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

}
