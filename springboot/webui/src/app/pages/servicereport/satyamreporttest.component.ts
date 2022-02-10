import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { ValidationError } from 'src/app/models/ValidationError';
import { QueryRunnerService } from 'src/app/services/api/query-runner.service';
import { ReportBuilderService } from 'src/app/services/api/report-builder.service';

@Component({
  selector: 'satyamreporttest',
  templateUrl: './satyamreporttest.component.html',
  styleUrls: ['./satyamreporttest.component.scss']
})
export class servicereport implements OnInit {

  public entryForm: FormGroup;
  submitted = false;
  basic: boolean = false;
  fieldErrors: ValidationError[] = [];
  report_id: number;
  columns: any[];
  rows:any[];
  
  constructor(private _fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private queryRunnerService: QueryRunnerService,
    private reportBuilderService: ReportBuilderService) { }
    
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.report_id = +params['report_id'];
    });
    console.log("Report id:",this.report_id);
 }

        runreport(apiname)
        {
          console.log(apiname);

          this.reportBuilderService.getreportdata(apiname).subscribe((data)=>{
            console.log("child report data",data);
            this.rows=data
            var j;
            var cart = [];
          for(var i = 0; i < data.length; i++) 
          { 
            var columnsIn = data[i]; 
            if(i==1)
            {
                for(var key in columnsIn)
                { 
                j={prop:key , name: key};
                cart.push(j)
                  
                } 
            }
          }
          this.columns = cart;

          })

        }

}