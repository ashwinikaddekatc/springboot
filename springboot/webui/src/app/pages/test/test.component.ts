import { Component, OnInit } from '@angular/core';
import { mergeMap } from 'rxjs/operators';
import { OrderService } from 'src/app/services/api/order.service';
import { Router, ActivatedRoute } from '@angular/router';
import { productSalesMulti } from 'src/app/data/products';
import { BiWidgetSetupService } from 'src/app/services/api/bi-widget-setup.service';

@Component({
  selector: 'test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class test implements OnInit {

    view: any[] = [460, 180];
    

    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'Country';
    showYAxisLabel = true;
    yAxisLabel = 'Sales';
    timeline = true;
    xAxis: boolean = true;
    yAxis: boolean = true;
    id:number;
    
    barColorScheme = {
        domain: ['#007cbb']
    }
    colorScheme = {
      domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
    };

   constructor(private router: Router, 
        private route: ActivatedRoute,
        private orderService: BiWidgetSetupService        ) {}

    ngOnInit() {
        this.id = this.route.snapshot.params["id"];
        //this.getPageData()
        this.getPageData()
    }

getPageData() {
         var me = this;
    }
}