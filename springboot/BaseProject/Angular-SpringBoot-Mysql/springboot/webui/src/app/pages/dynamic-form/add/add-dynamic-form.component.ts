import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormArray } from '@angular/forms';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FormSetupService } from 'src/app/services/api/form-setup.service';
import { DynamicTransactionService } from '../../../services/api/dynamic-transaction.service';
import { BuilderServiceService } from '../builder-service.service';
@Component({
  selector: 'app-add-dynamic-form',
  templateUrl: './add-dynamic-form.component.html',
  styleUrls: ['./add-dynamic-form.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AddDynamicFormComponent implements OnInit {
  public entryForm: FormGroup;
  submitted = false;
  basic: boolean = false;
  selected = "true";
  toggle = "true";

  //DynamicForm
  constructor(
    private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private builderService: BuilderServiceService,
    private formSetupService: FormSetupService
  ) { }
  form_id: number;
  ngOnInit() {
    this.valuechange();
    this.route.queryParams.subscribe(params => {
      const userId = params['form_id'];
      this.form_id = userId;
      console.log(userId);
      this.getUIData(userId);

      //builder
      this.entryForm = this._fb.group({
        uiData: this._fb.array([this.initLinesForm()]),
      })
    });
    //this.getUIData(this.form_id);
    // this.entryForm = this._fb.group({

    //});
  }
  initLinesForm() {
    return this._fb.group({
      form_id: [this.form_id], // shoud come from param
      form_version: [null],
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
      comp_l30: [null],
      //components: this._fb.array([this.initLinesForm()]),

    })
  }
  // for value change in checkbox
  valuechange() {
    this.selected = "true"
    console.log(this.selected)
  }
  togglechange() {
    this.selected = "true"
    console.log(this.selected)
  }
  form_name: string;
  uiData: any[];
  getUIData(id: number) {
    this.formSetupService.getById(id).subscribe(data => {
      console.log("hi");
      console.log('add-dynamic form data ', data);
      this.uiData = data.components;
      this.form_name = data.form_name;
    }, err => console.log(err)
    );
  }
  onSubmit() {
    console.log(this.entryForm.value);
    this.submitted = true;
    if (this.entryForm.invalid) {
      return;
    }
    this.onCreate();
  }
  onCreate() {
    this.builderService.save(this.entryForm.getRawValue()['uiData']).subscribe(
      (data) => {
        this.router.navigate(["../all"], { relativeTo: this.route, queryParams: { form_id: this.form_id } });
      },
      (error) => { console.log(error); }
    );
  }
  get controls() {
    return (this.entryForm.get("uiData") as FormArray).controls;
  }

  onAddLines() {
    (<FormArray>this.entryForm.get("uiData")).push(this.initLinesForm());
  }
  onRemoveLines(index: number) {
    (<FormArray>this.entryForm.get("uiData")).removeAt(index);
  }

}



