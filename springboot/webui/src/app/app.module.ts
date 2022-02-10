import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from "@angular/core";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

//Third Party Modules
import { NgxDatatableModule } from "@swimlane/ngx-datatable";
import { NgxChartsModule } from "@swimlane/ngx-charts";
import { ClarityModule, ClrIconModule } from "@clr/angular";

//Local App Modules
import { AppRoutingModule } from "./app-routing.module";

// Directives
import { TrackScrollDirective } from "./directives/track_scroll/track_scroll.directive";

// Components
import { BadgeComponent } from "./components/badge/badge.component";
import { LegendComponent } from "./components/legend/legend.component";
import { LogoComponent } from "./components/logo/logo.component";

//Pages  -- Pages too are components, they contain other components
import { AppComponent } from "./app.component";
import { HomeComponent } from "./home.component";
import { LoginComponent } from "./pages/login/login.component";
import { LogoutComponent } from "./pages/logout/logout.component";
import { DashboardComponent } from "./pages/dashboard/dashboard.component";
import { OrderStatsComponent } from "./pages/order_stats/order_stats.component";
import { ProductStatsComponent } from "./pages/product_stats/product_stats.component";
import { ProductsComponent } from "./pages/products/products.component";
import { CustomersComponent } from "./pages/customers/customers.component";
import { OrdersComponent } from "./pages/orders/orders.component";
import { OrderDetailsComponent } from "./pages/order_details/order_details.component";
import { EmployeesComponent } from "./pages/employees/employees.component";
// HEADER-ONLY FORM
import { InstructorComponent } from "./pages/instructor/instructor.component";
import { AllInstructorComponent } from "./pages/instructor/all/all-instructor.component";
import { AddInstructorComponent } from "./pages/instructor/add-instructor/add-instructor.component";
import { EditInstructorComponent } from "./pages/instructor/edit/edit-instructor.component";
import { ReadOnlyInstructorComponent } from "./pages/instructor/read-only/readonly-instructor.component";

// HEADER-LINE FORM
import { UniversityComponent } from "./pages/university/university.component";
import { AllTeacherComponent } from "./pages/university/teacher/all/all-teacher.component";
import { AddTeacherComponent } from "./pages/university/teacher/add/add-teacher.component";
import { EditTeacherComponent } from "./pages/university/teacher/edit/edit-teacher.component";
import { ReadOnlyTeacherComponent } from "./pages/university/teacher/read-only/readonly-teacher.component";
import { AllStudentComponent } from "./pages/university/student/all/all-student.component";
import { AddStudentComponent } from "./pages/university/student/add/add-student.component";
import { EditStudentComponent } from "./pages/university/student/edit/edit-student.component";
import { ReadOnlyStudentComponent } from "./pages/university/student/read-only/readonly-student.component";

// Services
import { AppConfig } from "./app-config";
import { UserInfoService } from "./services/user-info.service";
import { AuthGuard } from "./services/auth_guard.service";
import { ApiRequestService } from "./services/api/api-request.service";
import { TranslateService } from "./services/api/translate.service";
import { LoginService } from "./services/api/login.service";
import { OrderService } from "./services/api/order.service";
import { ProductService } from "./services/api/product.service";
import { CustomerService } from "./services/api/customer.service";
import { EmployeeService } from "./services/api/employee.service";
import { InstructorService } from "./services/api/rn_instructor.service";
import { TeacherService } from "./services/api/teacher.service";
import { StudentService } from "./services/api/student.service";
import { TeacherAddExtensionComponent } from "./pages/university/teacher/extensions/add-ext/teacher-add-extension.component";
import { ExtensionComponent } from "./pages/extension/extension.component";
import { AllExtensionComponent } from "./pages/extension/all-extension/all-extension.component";
import { EditExtensionComponent } from "./pages/extension/edit-extension/edit-extension.component";
import { AddExtensionComponent } from "./pages/extension/add-extension/add-extension.component";
import { ExtensionService } from "./services/api/extension.service";
import { ReadOnlyExtensionComponent } from "./pages/extension/read-only/readonly-extension.component";
import { TeacherGridExtensionComponent } from "./pages/university/teacher/extensions/grid-ext/teacher-grid-extension.component";
import { TeacherReadonlyExtensionComponent } from "./pages/university/teacher/extensions/readonly-ext/teacher-readonly-extension.component";

import { DynamicFormComponent } from "./pages/dynamic-form/dynamic-form.component";
import { ReadOnlyDynamicFormComponent } from "./pages/dynamic-form/read-only/read-only-dynamic-form.component";
import { AddDynamicFormComponent } from "./pages/dynamic-form/add/add-dynamic-form.component";
import { AllDynamicFormComponent } from "./pages/dynamic-form/all/all-dynamic-form.component";
import { EditDynamicFormComponent } from "./pages/dynamic-form/edit/edit-dynamic-form.component";
import { FormSetupService } from "./services/api/form-setup.service";
import { DynamicTransactionService } from "./services/api/dynamic-transaction.service";
import { FormsSetupComponent } from "./pages/forms-setup/forms-setup.component";
import { AddFormsSetupComponent } from "./pages/forms-setup/add/add-forms-setup.component";
import { AllFormsSetupComponent } from "./pages/forms-setup/all/all-forms-setup.component";
import { ReadOnlyFormsSetupComponent } from "./pages/forms-setup/read-only/readonly-forms-setup.component";
import { EditFormsSetupComponent } from "./pages/forms-setup/edit/edit-forms-setup.component";
import { AddMenuRegisterComponent } from "./pages/menu-register/add/add-menu-register.component";
import { EditMenuRegisterComponent } from "./pages/menu-register/edit/edit-menu-register.component";
import { ReadOnlyMenuRegisterComponent } from "./pages/menu-register/read-only/readonly-menu-register.component";
import { AllMenuRegisterComponent } from "./pages/menu-register/all/all-menu-register.component";
import { MenuRegisterComponent } from "./pages/menu-register/menu-register.component";
import { MenuRegisterService } from "./services/api/menu-register.service";
import { FunctionRegisterService } from "./services/api/function-register.service";
import { FunctionRegisterComponent } from "./pages/function-register/function-register.component";
import { AddFunctionRegisterComponent } from "./pages/function-register/add/add-function-register.component";
import { ReadOnlyFunctionRegisterComponent } from "./pages/function-register/read-only/readonly-function-register.component";
import { EditFunctionRegisterComponent } from "./pages/function-register/edit/edit-function-register.component";
import { AllFunctionRegisterComponent } from "./pages/function-register/all/all-function-register.component";
import { MenuGroupService } from "./services/api/menu-group.service";
import { MenuGroupComponent } from "./pages/menu-group/menu-group.component";
import { AddMenuGroupComponent } from "./pages/menu-group/add/add-menu-group.component";
import { AllMenuGroupComponent } from "./pages/menu-group/all/all-menu-group.component";
import { ReadOnlyMenuGroupComponent } from "./pages/menu-group/read-only/readonly-menu-group.component";
import { EditMenuGroupComponent } from "./pages/menu-group/edit/edit-menu-group.component";
import { RealnetMenuService } from "./services/api/realnet-menu.service";
import { UserRegistrationService } from "./services/api/user-registration.service";
import { UserRegistrationComponent } from "./pages/user-registration/user-registration.component";
import { SocialLoginComponent } from "./pages/social-login/social-login.component";
import { JwtInterceptor } from "./services/jwt.interceptor";
import { SignupComponent } from "./pages/signup/signup.component";
import { AccountSetupComponent } from "./pages/account-setup/account-setup.component";
import { AlertComponent } from "./components/alert/alert.component";
import { AlertService } from "./services/alert.service";
import { UserProfileSettingsComponent } from "./pages/user-profile-settings/user-profile-settings.component";
import { UserProfileService } from "./services/api/user-profile.service";
import { UserAccountComponent } from "./pages/user-account/user-account.component";
import { OrgUserService } from "./services/api/org-user.service";
import { OrgUserComponent } from "./pages/org-user/org-user.component";
import { AddOrgUserComponent } from "./pages/org-user/add/add-org-user.component";
import { AllOrgUserComponent } from "./pages/org-user/all/all-org-user.component";
import { ReadOnlyOrgUserComponent } from "./pages/org-user/read-only/readonly-org-user.component";
import { EditOrgUserComponent } from "./pages/org-user/edit/edit-org-user.component";
import { PasswordResetComponent } from "./pages/password-reset/password-reset.component";
import { TeacherEditExtensionComponent } from "./pages/university/teacher/extensions/edit-ext/teacher-edit-extension.component";
import { CodeExtractionComponent } from "./pages/code-extraction/code-extraction.component";
import { AllCodeExtractionComponent } from "./pages/code-extraction/all/all-code-extraction.component";
import { ReadOnlyCodeExtractionComponent } from "./pages/code-extraction/readonly/readonly-code-extraction.component";
import { AddCodeExtractionComponent } from "./pages/code-extraction/add/add-code-extraction.component";
import { CodeExtractionService } from "./services/api/code-extraction.service";

import { CodeExtractionParamsComponent } from "./pages/code-extraction-params/code-extraction-params.component";
import { AllCodeExtractionParamComponent } from "./pages/code-extraction-params/all/all-code-extraction-param.component";
import { EditCodeExtractionParamComponent } from "./pages/code-extraction-params/edit/edit-code-extraction-param.component";
import { ReadonlyCodeExtractionParamComponent } from "./pages/code-extraction-params/readonly/readonly-code-extraction-param.component";
import { RuleLibraryComponent } from "./pages/rule-library/rule-library.component";
import { AddRuleLibraryComponent } from "./pages/rule-library/add/add-rule-library.component";
import { AllRuleLibraryComponent } from "./pages/rule-library/all/all-rule-library.component";
import { ReadonlyRuleLibraryComponent } from "./pages/rule-library/readonly/readonly-rule-library.component";
import { EditRuleLibraryComponent } from "./pages/rule-library/edit/edit-rule-library.component";
import { ExceptionRuleLibraryComponent } from "./pages/exception-rule-library/exception-rule-library.component";
import { AddExceptionRuleLibraryComponent } from "./pages/exception-rule-library/add/add-exception-rule-library.component";
import { AllExceptionRuleLibraryComponent } from "./pages/exception-rule-library/all/all-exception-rule-library.component";
import { ReadonlyExceptionRuleLibraryComponent } from "./pages/exception-rule-library/readonly/readonly-exception-rule-library.component";
import { EditExceptionRuleLibraryComponent } from "./pages/exception-rule-library/edit/edit-exception-rule-library.component";
import { RuleLibraryService } from "./services/api/rule-library.service";
import { ExceptionRuleLibraryService } from "./services/api/exception-rule-library.service";
import { TechnologyStackComponent } from "./pages/technology-stack/technology-stack.component";
import { ReadonlyTechnologyStackComponent } from "./pages/technology-stack/readonly/readonly-technology-stack.component";
import { AddTechnologyStackComponent } from "./pages/technology-stack/add/add-technology-stack.component";
import { TechnologyStackService } from "./services/api/technology-stack.service";
import { AllTechnologyStackComponent } from "./pages/technology-stack/all/all-technology-stack.component";
import { ProjectSetupComponent } from "./pages/project-setup/project-setup.component";
import { AllProjectSetupComponent } from './pages/project-setup/all/all-project-setup.component';
import { AddProjectSetupComponent } from './pages/project-setup/add/add-project-setup.component';
import { ReadonlyProjectSetupComponent } from './pages/project-setup/readonly/readonly-project-setup.component';
import { EditProjectSetupComponent } from './pages/project-setup/edit/edit-project-setup.component';
import { ModuleSetupComponent } from './pages/module-setup/module-setup.component';
import { AllModuleSetupComponent } from './pages/module-setup/all/all-module-setup.component';
import { ReadonlyModuleSetupComponent } from './pages/module-setup/readonly/readonly-module-setup.component';
import { EditModuleSetupComponent } from './pages/module-setup/edit/edit-module-setup.component';
import { AddModuleSetupComponent } from './pages/module-setup/add/add-module-setup.component';
import { ProjectSetupService } from "./services/api/project-setup.service";
import { ModuleSetupService } from "./services/api/module-setup.service";

import { WireframeComponent } from './pages/wireframe/wireframe.component';
import { AddWireframeComponent } from './pages/wireframe/add/add-wireframe.component';
import { AllWireframeComponent } from './pages/wireframe/all/all-wireframe.component';
import { EditWireframeComponent } from './pages/wireframe/edit/edit-wireframe.component';
import { ReadonlyWireframeComponent } from './pages/wireframe/readonly/readonly-wireframe.component';
import { ActionsComponent } from './pages/wireframe/actions/actions.component';
import { WireframeService } from "./services/api/wireframe.service";
import { WireframeTypeComponent } from "./pages/wireframe/type/wireframe-type.component";
import { ActionBuilderComponent } from './pages/action-builder/action-builder.component';
import { AddActionBuilderComponent } from './pages/action-builder/add/add-action-builder.component';
import { ReadonlyActionBuilderComponent } from './pages/action-builder/readonly/readonly-action-builder.component';
import { EditActionBuilderComponent } from './pages/action-builder/edit/edit-action-builder.component';
import { AllActionBuilderComponent } from './pages/action-builder/all/all-action-builder.component';
import { ActionBuilderService } from "./services/api/action-builder.service";
import { ActionBuilderLineComponent } from "./pages/action-builder/action-builder-line/action-builder-line.component";
import { AddActionBuilderLineComponent } from "./pages/action-builder/action-builder-line/add/add-action-builder-line.component";
import { AllActionBuilderLineComponent } from "./pages/action-builder/action-builder-line/all/all-action-builder-line.component";
import { EditActionBuilderLineComponent } from "./pages/action-builder/action-builder-line/edit/edit-action-builder-line.component";
import { ReadonlyActionBuilderLineComponent } from "./pages/action-builder/action-builder-line/readonly/readonly-action-builder-line.component";
import { DropdownService } from "./services/api/dropdown.service";
import { FieldPropertyComponent } from './pages/wireframe/properties/field-property.component';
import { FileEditorComponent } from './pages/file-editor/file-editor.component';
import { CodemirrorModule } from "@ctrl/ngx-codemirror";
import { FileEditorNewComponent } from './pages/file-editor-new/file-editor-new.component';
import { ReportBuilderComponent } from './pages/report-builder/report-builder.component';
import { AllComponent } from './pages/report-builder/all/all.component';
import { AddComponent } from './pages/report-builder/add/add.component';
import { RbTableSetupComponent } from './pages/rb-table-setup/rb-table-setup.component';
import { RbColumnSetupComponent } from './pages/rb-column-setup/rb-column-setup.component';
import { RbWhereColumnSetupComponent } from './pages/rb-where-column-setup/rb-where-column-setup.component';
import { RbDateParamSetupComponent } from './pages/rb-date-param-setup/rb-date-param-setup.component';
import { RbAdhocParamSetupComponent } from './pages/rb-adhoc-param-setup/rb-adhoc-param-setup.component';
import { RbStdParamComponent } from './pages/rb-std-param/rb-std-param.component';
import { RbQueryBuildComponent } from './pages/rb-query-build/rb-query-build.component';
import { BiWidgetsComponent } from './pages/bi-widgets/bi-widgets.component';
import { AllWidgetComponent } from './pages/bi-widgets/all-widget/all-widget.component';
import { AddWidgetComponent } from './pages/bi-widgets/add-widget/add-widget.component';
import { SelectBiComponent } from './pages/bi-widgets/select-bi/select-bi.component';
import { BiDashboardComponent } from './pages/bi-dashboard/bi-dashboard.component';
import { AllDashComponent } from './pages/bi-dashboard/all-dash/all-dash.component';
import { AddDashComponent } from './pages/bi-dashboard/add-dash/add-dash.component';
import { QueryRunnerComponent } from './pages/query-runner/query-runner.component';
import { EditComponent } from './pages/report-builder/edit/edit.component';
import { viratreport } from './pages/viratreport/viratreport.component';
import { EditDashComponent } from './pages/bi-dashboard/edit-dash/edit-dash.component';
import { WidgetDashboardComponent } from './pages/widget-dashboard/widget-dashboard.component';
import { AddDefinationComponent } from './pages/bi-dashboard/add-defination/add-defination.component';
import { UpdateWidgetModalComponent } from './pages/bi-dashboard/update-widget-modal/update-widget-modal.component';
import { ganesh } from './pages/ganesh/ganesh.component';
import { satyamtest } from './pages/satyamtest/satyamtest.component';
import { test_19 } from './pages/test_19/test_19.component';
import { dash_21 } from './pages/dash_21/dash_21.component';
import { test_24 } from './pages/test_24/test_24.component';

import { entityreporttest } from './pages/entityreporttest/entityreporttest.component';
//add_module_import

import { UinameeditComponent } from './pages/wireframe/uinameedit/uinameedit.component';

import { Flf_builderaddsalesComponent } from "./pages/flf_builder/sales-new/addsales/Flf_builderaddsales.component";
import { Flf_builderallsalesComponent } from "./pages/flf_builder/sales-new/all/Flf_builderallsales.component";
import { Flf_builderreadonlynewComponent } from "./pages/flf_builder/sales-new/readonly/Flf_builderreadonlynew.component";
import { Flf_buildereditsalesComponent } from "./pages/flf_builder/sales-new/edit/Flf_buildereditsales.component";
import { Flf_buildersalesnewComponent } from "./pages/flf_builder/sales-new/Flf_buildersalesnew.component";
import { instructorreport } from "./pages/instructorreport/instructorreport.component";
import { newreport } from "./pages/newreport/newreport.component";
import { ganeshtestreport } from "./pages/ganeshtestreport/ganeshtestreport.component";
import { satyamreporttest } from "./pages/satyamreporttest/satyamreporttest.component";
import { sktestreport } from "./pages/sktestreport/sktestreport.component";
import { testreport_17 } from "./pages/testreport_17/testreport_17.component";
import { test } from "./pages/test/test.component";
import { test_21 } from "./pages/test_21/test_21.component";
import { EditWidgetsComponent } from './pages/bi-widgets/edit-widgets/edit-widgets.component';
import { AbtuisalesnewComponent } from "./pages/Module/sales-new/Abtuisalesnew.component";
import { AbtuiaddComponent } from "./pages/Module/sales-new/add/Abtuiadd.component";
import { AbtuiallComponent } from "./pages/Module/sales-new/all/Abtuiall.component";
import { AbtuireadonlyComponent } from "./pages/Module/sales-new/readonly/Abtuireadonly.component";
import { AbtuiupdateComponent } from "./pages/Module/sales-new/update/Abtuiupdate.component";
import { Reportbuilder2Component } from './pages/reportbuilder2/reportbuilder2.component';
import { Report2Component } from './pages/report2/report2.component';
import { ReporttypeComponent } from './pages/report2/reporttype/reporttype.component';
import { entityreportadd } from "./pages/report2/add/add.component";
import { entityreportall } from "./pages/report2/all/all.component";
import { entityreportedit } from "./pages/report2/edit/edit.component";
import { servicereport } from "./pages/servicereport/satyamreporttest.component";
import { ActionbuildermanualComponent } from './pages/actionbuildermanual/actionbuildermanual.component';


//import { ErrorInterceptor } from './services/error.interceptor';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,

    // Thirdparty Module
    NgxDatatableModule,
    NgxChartsModule,
    //ClarityModule.forChild(),
    ClarityModule,
    ClrIconModule,
    CodemirrorModule,
    // Local App Modules
    AppRoutingModule,
  ],

  declarations: [
    // Components
    BadgeComponent,
    LegendComponent,
    LogoComponent,
    //Pages -- Pages too are components, they contain other components
    AppComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    DashboardComponent,
    ProductStatsComponent,
    OrderStatsComponent,
    ProductsComponent,
    EmployeesComponent,
    CustomersComponent,
    OrdersComponent,
    OrderDetailsComponent,
    // instructor
    InstructorComponent,
    AllInstructorComponent,
    AddInstructorComponent,
    EditInstructorComponent,
    ReadOnlyInstructorComponent,
    // HEADER-LINE COMPONENTS
    UniversityComponent,
    AllTeacherComponent,
    AddTeacherComponent,
    EditTeacherComponent,
    ReadOnlyTeacherComponent,
    AllStudentComponent,
    AddStudentComponent,
    EditStudentComponent,
    ReadOnlyStudentComponent,
    // extensions [TEACHER]
    TeacherAddExtensionComponent,
    TeacherGridExtensionComponent,
    TeacherReadonlyExtensionComponent,
    TeacherEditExtensionComponent,
    //Directives
    TrackScrollDirective,
    // EXTENSION
    ExtensionComponent,
    AllExtensionComponent,
    EditExtensionComponent,
    AddExtensionComponent,
    ReadOnlyExtensionComponent,
    // DYNAMIC FORM COMPONENTS
    DynamicFormComponent,
    AddDynamicFormComponent,
    AllDynamicFormComponent,
    EditDynamicFormComponent,
    ReadOnlyDynamicFormComponent,

    // form setup for dynamic form
    FormsSetupComponent,
    AddFormsSetupComponent,
    AllFormsSetupComponent,
    ReadOnlyFormsSetupComponent,
    EditFormsSetupComponent,
    // menu register
    MenuRegisterComponent,
    AddMenuRegisterComponent,
    AllMenuRegisterComponent,
    ReadOnlyMenuRegisterComponent,
    EditMenuRegisterComponent,
    // function register
    FunctionRegisterComponent,
    AddFunctionRegisterComponent,
    AllFunctionRegisterComponent,
    ReadOnlyFunctionRegisterComponent,
    EditFunctionRegisterComponent,
    // menu group component
    MenuGroupComponent,
    AddMenuGroupComponent,
    AllMenuGroupComponent,
    ReadOnlyMenuGroupComponent,
    EditMenuGroupComponent,
    // user registration
    UserRegistrationComponent,
    SocialLoginComponent,
    SignupComponent,
    AccountSetupComponent,
    ProjectSetupComponent,
    AlertComponent,
    UserProfileSettingsComponent,
    UserAccountComponent,
    // users by admin
    OrgUserComponent,
    AddOrgUserComponent,
    AllOrgUserComponent,
    ReadOnlyOrgUserComponent,
    EditOrgUserComponent,
    PasswordResetComponent,
    // code extraction component start
    CodeExtractionComponent,
    AddCodeExtractionComponent,
    AllCodeExtractionComponent,
    ReadOnlyCodeExtractionComponent,
    CodeExtractionParamsComponent,
    AllCodeExtractionParamComponent,
    EditCodeExtractionParamComponent,
    ReadonlyCodeExtractionParamComponent, // code extraction component end

    RuleLibraryComponent, // rule library start
    AddRuleLibraryComponent,
    AllRuleLibraryComponent,
    ReadonlyRuleLibraryComponent,
    EditRuleLibraryComponent,
    ExceptionRuleLibraryComponent,
    AddExceptionRuleLibraryComponent,
    AllExceptionRuleLibraryComponent,
    ReadonlyExceptionRuleLibraryComponent,
    EditExceptionRuleLibraryComponent,
    TechnologyStackComponent,
    AllTechnologyStackComponent,
    ReadonlyTechnologyStackComponent,
    AddTechnologyStackComponent,
    AllProjectSetupComponent,
    AddProjectSetupComponent,
    ReadonlyProjectSetupComponent,
    EditProjectSetupComponent,
    ModuleSetupComponent,
    AllModuleSetupComponent,
    ReadonlyModuleSetupComponent,
    EditModuleSetupComponent,
    AddModuleSetupComponent,
    WireframeComponent,
    AddWireframeComponent,
    AllWireframeComponent,
    EditWireframeComponent,
    ReadonlyWireframeComponent,
    WireframeTypeComponent,
    ActionsComponent,
    // action builder
    ActionBuilderComponent,
    AddActionBuilderComponent,
    ReadonlyActionBuilderComponent,
    EditActionBuilderComponent,
    AllActionBuilderComponent,
    ActionBuilderLineComponent,
    AddActionBuilderLineComponent,
    AllActionBuilderLineComponent,
    EditActionBuilderLineComponent,
    ReadonlyActionBuilderLineComponent,
    FieldPropertyComponent,
    FileEditorComponent,
    FileEditorNewComponent,
    ReportBuilderComponent,
    AllComponent,
    AddComponent,
    RbTableSetupComponent,
    RbColumnSetupComponent,
    RbWhereColumnSetupComponent,
    RbDateParamSetupComponent,
    RbAdhocParamSetupComponent,
    RbStdParamComponent,
    RbQueryBuildComponent,
    BiWidgetsComponent,
    AllWidgetComponent,
    AddWidgetComponent,
    SelectBiComponent,

    BiDashboardComponent,
    AllDashComponent,
    AddDashComponent,
    QueryRunnerComponent,
    EditComponent,
    viratreport,
    EditDashComponent,
    WidgetDashboardComponent,
    AddDefinationComponent,
    UpdateWidgetModalComponent,
    ganesh,


    UinameeditComponent,

    Flf_builderaddsalesComponent,
    Flf_builderallsalesComponent,
    Flf_builderreadonlynewComponent,
    Flf_buildereditsalesComponent,
    Flf_buildersalesnewComponent,
    instructorreport,
    viratreport,
    newreport,
    ganeshtestreport,
    satyamreporttest,
    satyamtest,
    servicereport,

entityreporttest,
//add_module
test_24,
test,
test_19,
test_21,
dash_21,
EditWidgetsComponent,
AbtuisalesnewComponent,
AbtuiaddComponent,
AbtuiallComponent,
AbtuireadonlyComponent,
AbtuiupdateComponent,
Reportbuilder2Component,
Report2Component,
ReporttypeComponent,
entityreportadd,
entityreportall,
entityreportedit,
ActionbuildermanualComponent,
sktestreport,
testreport_17

  ],

  providers: [
    AuthGuard,
    UserInfoService,
    TranslateService,
    ApiRequestService,
    LoginService,
    RealnetMenuService, // responsible for side nav
    OrderService,
    ProductService,
    CustomerService,
    EmployeeService,
    InstructorService,
    TeacherService,
    StudentService,
    AppConfig,
    ExtensionService,
    FormSetupService,
    DynamicTransactionService,
    MenuRegisterService,
    FunctionRegisterService,
    MenuGroupService,
    UserRegistrationService,
    AlertService,
    UserProfileService,
    OrgUserService,
    CodeExtractionService,
    RuleLibraryService,
    ExceptionRuleLibraryService,
    TechnologyStackService,
    ProjectSetupService,
    ModuleSetupService,
    WireframeService,
    ActionBuilderService,
    DropdownService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
  ],

  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule {}
