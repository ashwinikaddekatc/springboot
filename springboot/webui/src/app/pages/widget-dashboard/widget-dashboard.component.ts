import { Component, OnInit } from '@angular/core';
import { mergeMap } from 'rxjs/operators';
import { OrderService } from 'src/app/services/api/order.service';
import { Router, ActivatedRoute } from '@angular/router';
import { productSalesMulti } from 'src/app/data/products';
import { BiWidgetSetupService } from 'src/app/services/api/bi-widget-setup.service';

@Component({
  selector: 'app-widget-dashboard',
  templateUrl: './widget-dashboard.component.html',
  styleUrls: ['./widget-dashboard.component.scss']
})
export class WidgetDashboardComponent implements OnInit {

    view: any[] = [460, 180];

    ordersByStatusData : any[] = [];
    ordersByPaymentData: any[] = [];
    ordersByCountryData: any[] = [];

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
        private orderService: BiWidgetSetupService,
        private route: ActivatedRoute,
        ) {}

    ngOnInit() {
        this.id = this.route.snapshot.params["id"];
        //this.getPageData()
        this.getPageData1()
    }

    getPageData1() {
         var me = this;
         me.orderService.getOrderStats2("select count(*) as value, payment_type as name from realnet_rest.orders group by order_status").subscribe(function(countryData)
         {
            me.ordersByCountryData = countryData.items;
        });

        me.orderService.getOrderStats2("select count(*) as value, state as name from realnet_rest.customers group by state").subscribe(function(statusData)
         {
            me.ordersByStatusData = statusData.items;
        });

        me.orderService.getOrderStats2("select count(*) as value, department as name from realnet_rest.employees group by department").subscribe(function(payTypeData)
         {
            me.ordersByPaymentData = payTypeData.items;
        });
    }


}
