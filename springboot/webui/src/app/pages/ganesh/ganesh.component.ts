import { Component, OnInit } from '@angular/core';
import { mergeMap } from 'rxjs/operators';
import { OrderService } from 'src/app/services/api/order.service';
import { Router, ActivatedRoute } from '@angular/router';
import { productSalesMulti } from 'src/app/data/products';
import { BiWidgetSetupService } from 'src/app/services/api/bi-widget-setup.service';

@Component({
  selector: 'ganesh',
  templateUrl: './ganesh.component.html',
  styleUrls: ['./ganesh.component.scss']
})
export class ganesh implements OnInit {

    view: any[] = [460, 180];
    
gb11Data : any[] = [];
gb12Data : any[] = [];
gb23Data : any[] = [];
gb13Data : any[] = [];
gb24Data : any[] = [];
gb15Data : any[] = [];

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


me.orderService.getOrderStats2("select count(*) as value, payment_type as name from realnet_rest.orders group by order_status").subscribe(function(gb11Data2)
         {
            me.gb11Data = gb11Data2.items;
        });

me.orderService.getOrderStats2("select count(*) as value, payment_type as name from realnet_rest.orders group by order_status").subscribe(function(gb12Data2)
         {
            me.gb12Data = gb12Data2.items;
        });me.orderService.getOrderStats2("select count(*) as value, state as name from realnet_rest.customers group by state").subscribe(function(gb23Data2)
         {
            me.gb23Data = gb23Data2.items;
        });

me.orderService.getOrderStats2("select count(*) as value, payment_type as name from realnet_rest.orders group by order_status").subscribe(function(gb13Data2)
         {
            me.gb13Data = gb13Data2.items;
        });me.orderService.getOrderStats2("select count(*) as value, state as name from realnet_rest.customers group by state").subscribe(function(gb24Data2)
         {
            me.gb24Data = gb24Data2.items;
        });me.orderService.getOrderStats2("select count(*) as value, payment_type as name from realnet_rest.orders group by order_status").subscribe(function(gb15Data2)
         {
            me.gb15Data = gb15Data2.items;
        });    }
}