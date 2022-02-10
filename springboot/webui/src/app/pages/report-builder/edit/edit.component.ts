import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReportBuilderService } from 'src/app/services/api/report-builder.service';
import { ReportBuilder } from 'src/app/models/ReportBuilder';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {

  id:number;
  project:ReportBuilder;
  entryForm: FormGroup;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private reportBuilderService: ReportBuilderService,
    private _fb: FormBuilder) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params["id"];
    this.entryForm = this._fb.group({
      report_name: [null],
      description: [null],
      report_tags: [null],
   });
   this.getById(this.id);
  }

  getById(id: number) {
    this.reportBuilderService.getById(id).subscribe(
      (data) => {
        this.project = data;
        console.log("reportdata",data );
        
      },
      (err) => {
        console.log(err);
      }
    );
  }
  
  buildReport(){
    console.log("id in build report:",this.id);
    this.reportBuilderService.buildReport(this.id).subscribe(
      (res) => {
        console.log(res);
      },
      (err) => {
        console.log(err);
      }
    );
    
  }

}
