import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Rn_Fb_Header } from 'src/app/models/Rn_Fb_Header';
import { WireframeService } from 'src/app/services/api/wireframe.service';

@Component({
  selector: 'app-uinameedit',
  templateUrl: './uinameedit.component.html',
  styleUrls: ['./uinameedit.component.scss']
})
export class UinameeditComponent implements OnInit {

  headerId:number;
  header:Rn_Fb_Header;
  fieldErors:any;
  data={
    uiName:""
  }

  constructor(
    private wireFrameService: WireframeService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {

    this.headerId = this.route.snapshot.params["id"];
    console.log(this.headerId);

    this.wireFrameService.getById(this.headerId).subscribe(
      (data) => {
        this.header = data;
        console.log('header data ', this.header);
      },
      (err) => {
        console.log(err);
      }
    );

  }


  uinameupdate()
  {
    console.warn("updating uiname with "+this.headerId +"and "+this.data);

    console.log(this.data);

      this.wireFrameService.updateuiname(this.headerId,this.data).subscribe(data1=>{
        console.log(data1);

      })

      this.router.navigate(["../"],{ relativeTo: this.route });
  }

}
