import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.scss']
})
export class AddComponent implements OnInit {
  //create a object of entry form
  public entryForm: FormGroup;
  constructor(private _fb: FormBuilder, private service: SalesService, private route: Router) { }

  ngOnInit(): void {
    this.entryForm = this._fb.group({
      sname: [null],
      department: [null],
      joiningDate: [null],
      status: [null],


    });
    //this.service.getDataById(1).subscribe(data => { console.log(data) });
  }
  onSubmit() {
    console.warn("my data"+this.entryForm.value);
    
    //console.log(this.entryForm.value);
    this.service.addData(this.entryForm.value).subscribe(data => {
      console.log(data);
      this.route.navigate(["/home/sales-new/all"]);
    }
    );
  }

}
