import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { builder } from '../builder';
import { ServicebuilderService } from '../servicebuilder.service';

@Component({
  selector: 'app-updatebuilder',
  templateUrl: './updatebuilder.component.html',
  styleUrls: ['./updatebuilder.component.scss']
})
export class UpdatebuilderComponent implements OnInit {
  builder: builder[];
  header_id;
  form_id;

  constructor(
    private servicebuilder: ServicebuilderService,
    private route: ActivatedRoute,
    private routing: Router
  ) { }

  ngOnInit(): void {
    this.header_id = this.route.snapshot.params['id'];
    this.form_id = this.route.snapshot.params['form_id'];
    this.servicebuilder.getDataById(this.header_id, this.form_id).subscribe((data) => {
      this.builder = data;


    });

  }


  onSubmit() {
    //this.header_id = this.route.snapshot.params['id'];

    console.log(this.builder);
    this.servicebuilder.updateData(this.header_id, this.form_id, this.builder).subscribe(data => {
      console.log(data);
      this.routing.navigate(['/home/builder/allbuilder']);
    })
  }

}
