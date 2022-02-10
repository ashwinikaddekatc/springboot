import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicebuilderService } from '../servicebuilder.service';

@Component({
  selector: 'app-allbuilder',
  templateUrl: './allbuilder.component.html',
  styleUrls: ['./allbuilder.component.scss']
})
export class AllbuilderComponent implements OnInit {
  columns: any[];
  isLoading: boolean = false;
  builder: any = [];
  rows: any;
  temp: any[];


  constructor(
    private serviceBuilder: ServicebuilderService,
    private route: Router,

  ) { }

  ngOnInit(): void {

    this.getData();
    this.columns = [
      { prop: "id", name: "Id", width: 65 },
      { prop: "form_id", name: "Form_id", width: 190 },
      { prop: "form_version", name: "Form_Ver", width: 190 },
      { prop: "header_id", name: "Header_id", width: 190 },
      { prop: "comp1", name: " Com1", width: 105 },
      { prop: "comp2", name: "Com2", width: 150 },
      { prop: "comp3", name: " Com3", width: 190 },
      // { prop: "comp4", name: "Com4", width: 190 },
      // { prop: "comp5", name: "Com5", width: 190 },
      // { prop: "comp6", name: "Com6", width: 190 },
      // { prop: "comp7", name: "Com7", width: 190 },
      // { prop: "comp8", name: "Com8", width: 190 },
      // { prop: "comp9", name: "Com9", width: 190 },
      // { prop: "comp10", name: "Com10", width: 190 },
      // { prop: "comp11", name: "Com11", width: 190 },
      // { prop: "comp12", name: "Com12", width: 190 },
      // { prop: "comp13", name: "Com13", width: 190 },
      // { prop: "comp14", name: "Com14", width: 190 },
      // { prop: "comp15", name: "Com15", width: 190 },
      // { prop: "comp16", name: "Com16", width: 190 },
      // { prop: "comp17", name: "Com17", width: 190 },
      // { prop: "comp18", name: "Com18", width: 190 },
      // { prop: "comp19", name: "Com19", width: 190 },
      // { prop: "comp20", name: "Com20", width: 190 },
      // { prop: "comp21", name: "Com21", width: 190 },
      // { prop: "comp22", name: "Com22", width: 190 },
      // { prop: "comp23", name: "Com23", width: 190 },
      // { prop: "comp24", name: "Com24", width: 190 },
      // { prop: "comp25", name: "Com25", width: 190 },


    ];
  }

  getData() {
    this.isLoading = true;
    this.serviceBuilder.getAllData().subscribe((data) => {
      console.log(`calling getall service`);

      this.isLoading = false;
      console.log(data);
      //console.log(data.items);
      this.builder = data.items;
      this.rows = this.builder;
      this.temp = [...this.builder];
    });
  }


  goToAdd() {
    this.route.navigate(["home/builder/addbuilder"]);
  }

  goToEdit(id: number, form_id: number) {
    this.route.navigate(["home/builder/updatebuilder", id, form_id]);
  }

  goToReadOnly(id: number, form_id: number) {
    this.route.navigate(["home/builder/readonlybuilder", id, form_id]);
  }

  goToForm(id: number) {
    this.route.navigate(['/home/dynamic-form-test1/all'], { queryParams: { form_id: id } });

  }

}
