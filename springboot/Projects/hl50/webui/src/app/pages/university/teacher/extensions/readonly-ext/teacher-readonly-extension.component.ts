import { Component, Input, OnInit } from "@angular/core";
import { sales } from "src/app/sales/sales";



@Component({
  selector: "teacher-readonly-extension",
  templateUrl: "./teacher-readonly-extension.component.html",
  styleUrls: [ './teacher-readonly-extension.scss'],
})
export class TeacherReadonlyExtensionComponent implements OnInit {
  
  @Input() teacherExtension: sales;

  constructor() {}
  ngOnInit() {
  }
}
