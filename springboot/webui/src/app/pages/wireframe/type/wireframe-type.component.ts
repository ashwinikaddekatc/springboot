import { Component, OnInit, ViewEncapsulation } from '@angular/core';

export interface WFType {
  title: string;
  image: String;
  href: string;
  formType: string;
}
@Component({
  selector: 'app-wireframe-type',
  templateUrl: './wireframe-type.component.html',
  styleUrls: ['./wireframe-type.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class WireframeTypeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  items : WFType[] = [
    {title: 'Header Only', image: '/assets/images/Header.png', href: '../add', formType: 'header_only'},
    {title: 'Header Lines', image: '/assets/images/HeaderLines.png', href: '../add', formType: 'header_line'},
    {title: 'Only Lines', image: '/assets/images/OnlyLines.png', href: '../add', formType: 'line_only'},
    {title: 'Multiple Lines', image: '/assets/images/MultipleLines.png', href: '../add', formType: 'multiline'},
  ]

}
