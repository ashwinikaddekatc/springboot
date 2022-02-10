import { Component, OnInit,ViewEncapsulation, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ValidationError } from 'src/app/models/ValidationError';
import { BiDashLine } from 'src/app/models/BiDashLine';
import { AddDefinationService } from 'src/app/services/api/add-defination.service';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DropDown } from 'src/app/services/api/dropdown.service';
import { BiWidgetSetupService } from 'src/app/services/api/bi-widget-setup.service';

@Component({
  selector: 'app-update-widget-modal',
  templateUrl: './update-widget-modal.component.html',
  styleUrls: ['./update-widget-modal.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class UpdateWidgetModalComponent implements OnInit {


  updated = false;
  moduleId:number;
  fieldErrors: ValidationError[] = [];
  rn_widgets: BiDashLine;
  id:number;
  name2:string[];
  public entryForm: FormGroup;


  constructor(private router: Router,
    private route: ActivatedRoute,
    private addDefService: AddDefinationService,
    private _fb: FormBuilder,
     private widgetService: BiWidgetSetupService) { }
  basic: boolean = false;
  name:string;
  ngOnInit(): void {
    this.rn_widgets=new BiDashLine();
    this.name = this.route.snapshot.params["name"];
    console.log(this.name);
    this.id=this.route.snapshot.params["id"];
    this.moduleId = this.widgetService.getModuleId();
    this.entryForm = this._fb.group({
      widgets1: [null],
      widgets2: [null],
      widgets3: [null],
      widgets4: [null],
      widgets5: [null],
      widgets6: [null]
      
    });
    this.getAllWidgets(this.moduleId);
    this.getbyId(this.id);
 }

 onSubmit() {
  //this.updated = true;
  //this.name="widgets1"
  this.update();
}

getbyId(id:number){
  this.addDefService.getById(id).subscribe((data) => {
    this.rn_widgets = data;
    //this.components = data.components;
    console.log("data in get by id::",data);
    
   console.log(this.id);
    
  });
}


update() {
  //this.fieldErrors = [];
  this.addDefService.update(this.id, this.entryForm.value).subscribe(
    (data) => {
      console.log(data);
      //this.router.navigate(["../../all"], { relativeTo: this.route });
    },
    (error: HttpErrorResponse) => {
      /* (error: HttpErrorResponse) => { */
      console.log(error);
      const objectArray = Object.entries(error.error.fieldErrors);
      objectArray.forEach(([k, v]) => {
        console.log(k);
        console.log(v);
        this.fieldErrors.push({ field: k, message: v });
      });
      console.log(this.fieldErrors); // this will come from backend
      this.rn_widgets = new BiDashLine();
    }
  );
}

widgetList: DropDown[];
    getAllWidgets(id: number) {
      this.widgetService.getwidgetList(id).subscribe((data) => {
        console.log("my data",data);
        
        this.widgetList = data;
      }, (err) => {
      });
    }
  

}
