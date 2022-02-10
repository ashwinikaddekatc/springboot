import { Component, OnInit } from '@angular/core';
import { ReportBuilderService } from 'src/app/services/api/report-builder.service';

@Component({
  selector: 'app-reportbuilder2',
  templateUrl: './reportbuilder2.component.html',
  styleUrls: ['./reportbuilder2.component.scss']
})
export class Reportbuilder2Component implements OnInit {

  

  constructor( private reportservice:ReportBuilderService) { }

     

  ngOnInit(): void {
  }

  list=["Rn_Cff_ActionBuilder_Service","Rn_Cff_ActionBuilderRules_Service","Rn_Dashboard_Header_Service","Rn_dashboard_widget_service","Rn_Bcf_Exception_Rule_Library_Service","Rn_Bcf_Extractor_Params_Service",
"Rn_Bcf_Extractor_Service","Rn_Bcf_Rule_Library_Service","Rn_Bcf_TechnologyStack_Service","Rn_Instructor_Service","StudentService","TeacherService","FieldTypeService","Rn_FLF_Service","ExtFieldService","Rn_DynamicTransactionService","Rn_Forms_Setup_Service","Rn_Function_Register_Service",
"Rn_LookUp_Service","Rn_Main_Menu_Service","Rn_Menu_Group_Service","Rn_Menu_Register_Service","Rn_ModuleSetup_Service","Rn_ProjectSetup_Service","Rn_CreateQuery_Service","Rn_report_builder_service","CompanyService","RoleService","UserService","Rn_WireFrame_Service"];


dropval=""

  reportvalue()
  {
    console.log("in report value", this.dropval);
    
    this.reportservice.report2(this.dropval).subscribe((data)=>{
      console.log(data);
      
    })
    
  }
}
