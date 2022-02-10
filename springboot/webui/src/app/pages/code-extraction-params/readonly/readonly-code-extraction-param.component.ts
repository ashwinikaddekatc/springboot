import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bcf_Extractor_Params } from 'src/app/models/Bcf_Extractor_Params';
import { CodeExtractionService } from 'src/app/services/api/code-extraction.service';

@Component({
  selector: 'app-readonly-code-extraction-param',
  templateUrl: './readonly-code-extraction-param.component.html',
  styleUrls: ['./readonly-code-extraction-param.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ReadonlyCodeExtractionParamComponent implements OnInit {
  basic: boolean = false;
  id: number;
  extractor_param: Bcf_Extractor_Params;
  constructor(
      private router: Router,
      private route: ActivatedRoute,
       private codeExtractionService: CodeExtractionService
  ) { }

  header_id: number;
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.header_id = +params['header_id'];
    });
      this.getById();
  }

  getById() {
      this.extractor_param = new Bcf_Extractor_Params();
      this.id = this.route.snapshot.params['id'];
      console.log("my id",this.id);
      
      this.codeExtractionService.getCodeExtractionParamById(this.id)
          .subscribe(data => {
              console.log(data);
              this.extractor_param = data;
          });
  }

  goToWhoColumns() {
      this.basic = !this.basic;
  }

  back() {
      this.router.navigate(['../../all'], { relativeTo: this.route, queryParams: { header_id: this.header_id } });
  }

}
