import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthorserviceService } from 'src/app/services/api/authorservice.service';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditsalesComponent implements OnInit {
  updated = false;
  sales ;
  sid: number;
  salesperson;


  constructor(private router: Router,
    private route:ActivatedRoute,
    private authorservice:AuthorserviceService
    ) { }

  ngOnInit(): void {

    this.sales;
    this.sid = this.route.snapshot.params["id"];
    console.log("update with id = ", this.sid);
    this.getById(this.sid);
  }

  getById(sid: number) {
    this.authorservice.getById(sid).subscribe((data) => {
      this.sales = data;
      console.log("data   ",this.sales);
      
      
      //this.students = data.students;
      
    });
  }

    update() {
      this.authorservice.update(this.sid, this.sales).subscribe(
        (data) => {
          console.log(data);
          this.router.navigate(["/home/author/all"]);
        },
        (error: HttpErrorResponse) => {
          console.log(error.message);
        }
      );
    }

    onSubmit() {
      this.updated = true;
      this.update();
    }
  
    

}
