import { Component, OnInit } from '@angular/core';
import { ActionBuilderService } from 'src/app/services/api/action-builder.service';

@Component({
  selector: 'app-actionbuildermanual',
  templateUrl: './actionbuildermanual.component.html',
  styleUrls: ['./actionbuildermanual.component.scss']
})
export class ActionbuildermanualComponent implements OnInit {

  constructor(private  actionservice:ActionBuilderService) { }

  ngOnInit(): void {
  }


  insert(param,btnname)
  {
      this.actionservice.actioninsert(param,btnname).subscribe((data)=>{
        console.log(data);
      })
  }

  update(id,param,btnname)
  {
    this.actionservice.actionupdate(id,param,btnname).subscribe((data)=>{
      console.log(data);
      
    })

  }
}
