import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bcf_Extractor } from 'src/app/models/Bcf_Extractor';
import { CodeExtractionService } from 'src/app/services/api/code-extraction.service';

@Component({
  selector: 'app-readonly-code-extraction',
  templateUrl: './readonly-code-extraction.component.html',
  styleUrls: ['./readonly-code-extraction.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ReadOnlyCodeExtractionComponent implements OnInit {
  basic: boolean = false;
  id: number;
  bcf_Extractor: Bcf_Extractor;
  constructor(
      private router: Router,
      private route: ActivatedRoute,
       private codeExtractionService: CodeExtractionService
  ) { }

  ngOnInit() {
      this.getById();
  }

  getById() {
      this.bcf_Extractor = new Bcf_Extractor();
      this.id = this.route.snapshot.params['id'];
      this.codeExtractionService.getById(this.id)
          .subscribe(data => {
              console.log(data);
              this.bcf_Extractor = data;
          });
  }

  goToWhoColumns() {
      this.basic = !this.basic;
  }

  back() {
      this.router.navigate(['../../all'], {relativeTo: this.route});
  }

  goToEdit(index: number) {
    this.router.navigate(['../../all'], {relativeTo: this.route});
  }
}

