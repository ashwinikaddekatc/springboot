import { Component, OnInit } from '@angular/core';
import { mergeMap } from 'rxjs/operators';
import { OrderService } from 'src/app/services/api/order.service';
import { Router, ActivatedRoute } from '@angular/router';
import { productSalesMulti } from 'src/app/data/products';
import { BiWidgetSetupService } from 'src/app/services/api/bi-widget-setup.service';

@Component({
  selector: 'dash_21',
  templateUrl: './dash_21.component.html',
  styleUrls: ['./dash_21.component.scss']
})
export class dash_21 implements OnInit {

    view: any[] = [460, 180];
    
testdb1Data : any[] = [];
testdb2Data : any[] = [];
wig24Data : any[] = [];

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


me.orderService.getOrderStats2("select count(*) as value, state as name from cnsdb_new.customers group by state").subscribe(function(testdb1Data2)
         {
            me.testdb1Data = testdb1Data2.items;
        });

me.orderService.getOrderStats2("select count(*) as value, state as name from cnsdb_new.customers group by state").subscribe(function(testdb2Data2)
         {
            me.testdb2Data = testdb2Data2.items;
        });me.orderService.getOrderStats2("select count(*) as value, state as name from cnsdb_new.customers group by state").subscribe(function(wig24Data2)
         {
            me.wig24Data = wig24Data2.items;
        });    }
}