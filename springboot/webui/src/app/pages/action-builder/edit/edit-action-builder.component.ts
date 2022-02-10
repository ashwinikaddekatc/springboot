import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Rn_Cff_ActionBuilder_Header } from 'src/app/models/Rn_Cff_ActionBuilder_Header';
import { ValidationError } from 'src/app/models/ValidationError';
import { ActionBuilderService } from 'src/app/services/api/action-builder.service';

@Component({
  selector: 'app-edit-action-builder',
  templateUrl: './edit-action-builder.component.html',
  styleUrls: ['./edit-action-builder.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class EditActionBuilderComponent implements OnInit {
  updated = false;
  actionBuilder_Header: Rn_Cff_ActionBuilder_Header;
  id: number;
  //projectId: number;

  fieldErors: ValidationError[] = []; // backend validation field error message
  tech_stacks = [
    'SpringMVC-Hibernate-Mysql',
    'Angular-SpringBoot-Mysql', 
    'React-ReactNative-Mysql',
    'React-ReactNative-MongoDB', 
    'Angular-SpringBoot-MongoDB', 
    'Php-Laravel-Mysql', 
    'MEAN'
  ];

  

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private actionBuilderService: ActionBuilderService
  ) {}

  ngOnInit() {
    /* this.route.queryParams.subscribe(params => {
      this.projectId = +params['p_id'];
    }); */
    this.actionBuilder_Header = new Rn_Cff_ActionBuilder_Header();
    this.id = this.route.snapshot.params["id"];
    console.log("update with id = ", this.id);
    this.getById(this.id);
  }

  getById(id: number) {
    this.actionBuilderService.getById(id).subscribe(
      (data) => {
        console.log(data);
        this.actionBuilder_Header = data;
      },
      (err) => {
        console.log(err);
      }
    );
  }
  update() {
    this.actionBuilderService.update(this.id, this.actionBuilder_Header).subscribe(
      (data) => {
        console.log(data);
        this.router.navigate(["../../all"], { relativeTo: this.route });
        //this.router.navigate(["../../all"],{ relativeTo: this.route, queryParams: { p_id: this.projectId } });
      },
      (error) => {
        console.log(error);
        const objectArray = Object.entries(error.error.fieldErrors);
        objectArray.forEach(([k, v]) => {
          console.log(k);
          console.log(v);
          this.fieldErors.push({ field: k, message: v });
        });
        console.log(this.fieldErors); // this will come from backend
      }
    );
    this.actionBuilder_Header = new Rn_Cff_ActionBuilder_Header();
  }

  onSubmit() {
    this.updated = true;
    this.update();
  }

  back() {
    this.router.navigate(["../../all"], { relativeTo: this.route });
  }

}
