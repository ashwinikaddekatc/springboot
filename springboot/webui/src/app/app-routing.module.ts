import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home.component';

import { LoginComponent } from './pages/login/login.component';
import { LogoutComponent } from './pages/logout/logout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProductStatsComponent } from './pages/product_stats/product_stats.component';
import { OrderStatsComponent } from './pages/order_stats/order_stats.component';
import { ProductsComponent } from './pages/products/products.component';
import { CustomersComponent } from './pages/customers/customers.component';
import { OrdersComponent } from './pages/orders/orders.component';
import { OrderDetailsComponent } from './pages/order_details/order_details.component';
import { EmployeesComponent } from './pages/employees/employees.component';

import { AuthGuard } from './services/auth_guard.service';
import { PageNotFoundComponent } from './pages/404/page-not-found.component';
import { InstructorComponent } from './pages/instructor/instructor.component';
import { AddInstructorComponent } from './pages/instructor/add-instructor/add-instructor.component';
import { ReadOnlyInstructorComponent } from './pages/instructor/read-only/readonly-instructor.component';
import { EditInstructorComponent } from './pages/instructor/edit/edit-instructor.component';
import { AllInstructorComponent } from './pages/instructor/all/all-instructor.component';

// HEADER LINE FORM
import { UniversityComponent } from './pages/university/university.component';
import { AllTeacherComponent } from './pages/university/teacher/all/all-teacher.component';
import { AddTeacherComponent } from './pages/university/teacher/add/add-teacher.component';
import { EditTeacherComponent } from './pages/university/teacher/edit/edit-teacher.component';
import { ReadOnlyTeacherComponent } from './pages/university/teacher/read-only/readonly-teacher.component';
import { AllStudentComponent } from './pages/university/student/all/all-student.component';
import { AddStudentComponent } from './pages/university/student/add/add-student.component';
import { EditStudentComponent } from './pages/university/student/edit/edit-student.component';
import { ReadOnlyStudentComponent } from './pages/university/student/read-only/readonly-student.component';
import { ExtensionComponent } from './pages/extension/extension.component';
import { AllExtensionComponent } from './pages/extension/all-extension/all-extension.component';
import { AddExtensionComponent } from './pages/extension/add-extension/add-extension.component';
import { ReadOnlyExtensionComponent } from './pages/extension/read-only/readonly-extension.component';
import { EditExtensionComponent } from './pages/extension/edit-extension/edit-extension.component';
import { FormsSetupComponent } from './pages/forms-setup/forms-setup.component';
import { AllFormsSetupComponent } from './pages/forms-setup/all/all-forms-setup.component';
import { AddFormsSetupComponent } from './pages/forms-setup/add/add-forms-setup.component';
import { ReadOnlyFormsSetupComponent } from './pages/forms-setup/read-only/readonly-forms-setup.component';
import { EditFormsSetupComponent } from './pages/forms-setup/edit/edit-forms-setup.component';
import { DynamicFormComponent } from './pages/dynamic-form/dynamic-form.component';
import { EditDynamicFormComponent } from './pages/dynamic-form/edit/edit-dynamic-form.component';
import { ReadOnlyDynamicFormComponent } from './pages/dynamic-form/read-only/read-only-dynamic-form.component';
import { AddDynamicFormComponent } from './pages/dynamic-form/add/add-dynamic-form.component';
import { AllDynamicFormComponent } from './pages/dynamic-form/all/all-dynamic-form.component';
import { MenuRegisterComponent } from './pages/menu-register/menu-register.component';
import { ReadOnlyMenuRegisterComponent } from './pages/menu-register/read-only/readonly-menu-register.component';
import { AllMenuRegisterComponent } from './pages/menu-register/all/all-menu-register.component';
import { AddMenuRegisterComponent } from './pages/menu-register/add/add-menu-register.component';
import { EditMenuRegisterComponent } from './pages/menu-register/edit/edit-menu-register.component';
import { AllFunctionRegisterComponent } from './pages/function-register/all/all-function-register.component';
import { AddFunctionRegisterComponent } from './pages/function-register/add/add-function-register.component';
import { ReadOnlyFunctionRegisterComponent } from './pages/function-register/read-only/readonly-function-register.component';
import { EditFunctionRegisterComponent } from './pages/function-register/edit/edit-function-register.component';
import { FunctionRegisterComponent } from './pages/function-register/function-register.component';
import { MenuGroupComponent } from './pages/menu-group/menu-group.component';
import { AllMenuGroupComponent } from './pages/menu-group/all/all-menu-group.component';
import { AddMenuGroupComponent } from './pages/menu-group/add/add-menu-group.component';
import { ReadOnlyMenuGroupComponent } from './pages/menu-group/read-only/readonly-menu-group.component';
import { EditMenuGroupComponent } from './pages/menu-group/edit/edit-menu-group.component';
import { ForbiddenComponent } from './pages/403/forbidden.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { SocialLoginComponent } from './pages/social-login/social-login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { AccountSetupComponent } from './pages/account-setup/account-setup.component';
import { UserProfileSettingsComponent } from './pages/user-profile-settings/user-profile-settings.component';
import { UserAccountComponent } from './pages/user-account/user-account.component';
import { AddOrgUserComponent } from './pages/org-user/add/add-org-user.component';
import { ReadOnlyOrgUserComponent } from './pages/org-user/read-only/readonly-org-user.component';
import { OrgUserComponent } from './pages/org-user/org-user.component';
import { EditOrgUserComponent } from './pages/org-user/edit/edit-org-user.component';
import { AllOrgUserComponent } from './pages/org-user/all/all-org-user.component';
import { PasswordResetComponent } from './pages/password-reset/password-reset.component';
import { CodeExtractionComponent } from './pages/code-extraction/code-extraction.component';
import { AllCodeExtractionComponent } from './pages/code-extraction/all/all-code-extraction.component';
import { AddCodeExtractionComponent } from './pages/code-extraction/add/add-code-extraction.component';
import { ReadOnlyCodeExtractionComponent } from './pages/code-extraction/readonly/readonly-code-extraction.component';
import { AllCodeExtractionParamComponent } from './pages/code-extraction-params/all/all-code-extraction-param.component';
import { CodeExtractionParamsComponent } from './pages/code-extraction-params/code-extraction-params.component';
import { EditCodeExtractionParamComponent } from './pages/code-extraction-params/edit/edit-code-extraction-param.component';
import { ReadonlyCodeExtractionParamComponent } from './pages/code-extraction-params/readonly/readonly-code-extraction-param.component';
import { RuleLibraryComponent } from './pages/rule-library/rule-library.component';
import { AllRuleLibraryComponent } from './pages/rule-library/all/all-rule-library.component';
import { AddRuleLibraryComponent } from './pages/rule-library/add/add-rule-library.component';
import { ReadonlyRuleLibraryComponent } from './pages/rule-library/readonly/readonly-rule-library.component';
import { EditRuleLibraryComponent } from './pages/rule-library/edit/edit-rule-library.component';
import { ExceptionRuleLibraryComponent } from './pages/exception-rule-library/exception-rule-library.component';
import { AllExceptionRuleLibraryComponent } from './pages/exception-rule-library/all/all-exception-rule-library.component';
import { AddExceptionRuleLibraryComponent } from './pages/exception-rule-library/add/add-exception-rule-library.component';
import { ReadonlyExceptionRuleLibraryComponent } from './pages/exception-rule-library/readonly/readonly-exception-rule-library.component';
import { EditExceptionRuleLibraryComponent } from './pages/exception-rule-library/edit/edit-exception-rule-library.component';
import { TechnologyStackComponent } from './pages/technology-stack/technology-stack.component';
import { AllTechnologyStackComponent } from './pages/technology-stack/all/all-technology-stack.component';
import { AddTechnologyStackComponent } from './pages/technology-stack/add/add-technology-stack.component';
import { ReadonlyTechnologyStackComponent } from './pages/technology-stack/readonly/readonly-technology-stack.component';
import { ProjectSetupComponent } from './pages/project-setup/project-setup.component';
import { AllProjectSetupComponent } from './pages/project-setup/all/all-project-setup.component';
import { AddProjectSetupComponent } from './pages/project-setup/add/add-project-setup.component';
import { ReadonlyProjectSetupComponent } from './pages/project-setup/readonly/readonly-project-setup.component';
import { EditProjectSetupComponent } from './pages/project-setup/edit/edit-project-setup.component';
import { ModuleSetupComponent } from './pages/module-setup/module-setup.component';
import { AllModuleSetupComponent } from './pages/module-setup/all/all-module-setup.component';
import { EditModuleSetupComponent } from './pages/module-setup/edit/edit-module-setup.component';
import { ReadonlyModuleSetupComponent } from './pages/module-setup/readonly/readonly-module-setup.component';
import { AddModuleSetupComponent } from './pages/module-setup/add/add-module-setup.component';
import { ActionsComponent } from './pages/wireframe/actions/actions.component';
import { WireframeComponent } from './pages/wireframe/wireframe.component';
import { AllWireframeComponent } from './pages/wireframe/all/all-wireframe.component';
import { AddWireframeComponent } from './pages/wireframe/add/add-wireframe.component';
import { EditWireframeComponent } from './pages/wireframe/edit/edit-wireframe.component';
import { ReadonlyWireframeComponent } from './pages/wireframe/readonly/readonly-wireframe.component';
import { WireframeTypeComponent } from './pages/wireframe/type/wireframe-type.component';
import { AddActionBuilderComponent } from './pages/action-builder/add/add-action-builder.component';
import { ReadonlyActionBuilderComponent } from './pages/action-builder/readonly/readonly-action-builder.component';
import { EditActionBuilderComponent } from './pages/action-builder/edit/edit-action-builder.component';
import { AllActionBuilderComponent } from './pages/action-builder/all/all-action-builder.component';
import { ActionBuilderComponent } from './pages/action-builder/action-builder.component';
import { ActionBuilderLineComponent } from './pages/action-builder/action-builder-line/action-builder-line.component';
import { AllActionBuilderLineComponent } from './pages/action-builder/action-builder-line/all/all-action-builder-line.component';
import { AddActionBuilderLineComponent } from './pages/action-builder/action-builder-line/add/add-action-builder-line.component';
import { EditActionBuilderLineComponent } from './pages/action-builder/action-builder-line/edit/edit-action-builder-line.component';
import { ReadonlyActionBuilderLineComponent } from './pages/action-builder/action-builder-line/readonly/readonly-action-builder-line.component';
import { FieldPropertyComponent } from './pages/wireframe/properties/field-property.component';
import { FileEditorComponent } from './pages/file-editor/file-editor.component';
import { FileEditorNewComponent } from './pages/file-editor-new/file-editor-new.component';
import { ReportBuilderComponent } from './pages/report-builder/report-builder.component';
import { AddComponent } from './pages/report-builder/add/add.component';
import { AllComponent } from './pages/report-builder/all/all.component';
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




//conflict from here - satyam - 16 May
import { UinameeditComponent } from './pages/wireframe/uinameedit/uinameedit.component';


//conflict till here - satyam - 16 May
import { Flf_builderaddsalesComponent } from "./pages/flf_builder/sales-new/addsales/Flf_builderaddsales.component";
import { Flf_builderallsalesComponent } from "./pages/flf_builder/sales-new/all/Flf_builderallsales.component";
import { Flf_builderreadonlynewComponent } from "./pages/flf_builder/sales-new/readonly/Flf_builderreadonlynew.component";
import { Flf_buildereditsalesComponent } from "./pages/flf_builder/sales-new/edit/Flf_buildereditsales.component";
import { Flf_buildersalesnewComponent } from "./pages/flf_builder/sales-new/Flf_buildersalesnew.component";
import { instructorreport } from './pages/instructorreport/instructorreport.component';
import { newreport } from './pages/newreport/newreport.component';


import { ganeshtestreport } from './pages/ganeshtestreport/ganeshtestreport.component';
import { satyamreporttest } from './pages/satyamreporttest/satyamreporttest.component';
import { satyamtest } from './pages/satyamtest/satyamtest.component';
import { sktestreport } from './pages/sktestreport/sktestreport.component';
import { testreport_17 } from './pages/testreport_17/testreport_17.component';
import { test_19 } from './pages/test_19/test_19.component';
import { test_21 } from './pages/test_21/test_21.component';

import { dash_21 } from './pages/dash_21/dash_21.component';
import { EditWidgetsComponent } from './pages/bi-widgets/edit-widgets/edit-widgets.component';
import { test_24 } from './pages/test_24/test_24.component';
import { entityreporttest } from './pages/entityreporttest/entityreporttest.component';
import { science } from './pages/science/science.component';
import { test } from './pages/test/test.component';
//add_import

import { AbtuisalesnewComponent } from './pages/Module/sales-new/Abtuisalesnew.component';
import { AbtuiaddComponent } from './pages/Module/sales-new/add/Abtuiadd.component';
import { AbtuiallComponent } from './pages/Module/sales-new/all/Abtuiall.component';
import { AbtuireadonlyComponent } from './pages/Module/sales-new/readonly/Abtuireadonly.component';
import { AbtuiupdateComponent } from './pages/Module/sales-new/update/Abtuiupdate.component';
import { Reportbuilder2Component } from './pages/reportbuilder2/reportbuilder2.component';
import { ReporttypeComponent } from './pages/report2/reporttype/reporttype.component';
import { Report2Component } from './pages/report2/report2.component';
import { entityreportall } from './pages/report2/all/all.component';
import { entityreportadd } from './pages/report2/add/add.component';
import { entityreportedit } from './pages/report2/edit/edit.component';
import { servicereport } from './pages/servicereport/satyamreporttest.component';
import { ActionbuildermanualComponent } from './pages/actionbuildermanual/actionbuildermanual.component';



export const routes: Routes = [
  //Important: The sequence of path is important as the router go over then in sequential manner
  { path: '', redirectTo: '/home/dashboard/order', pathMatch: 'full' },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AuthGuard],
    children: [
      // Children paths are appended to the parent path
      {
        path: '',
        redirectTo: '/home/dashboard/order',
        pathMatch: 'full',
        data: [{ selectedHeaderItemIndex: 1, selectedSubNavItemIndex: -1 }],
      }, // Default path (if no deep path is specified for home component like webui/home then it will by default show ProductsComponent )
      {
        path: 'dashboard',
        component: DashboardComponent,
        data: [{ selectedHeaderItemIndex: 0, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: '/home/dashboard/order', pathMatch: 'full' },
          {
            path: 'order',
            component: OrderStatsComponent,
            data: [{ selectedHeaderItemIndex: 0, selectedSubNavItemIndex: 0 }],
          },
          {
            path: 'product',
            component: ProductStatsComponent,
            data: [{ selectedHeaderItemIndex: 0, selectedSubNavItemIndex: 1 }],
          },
        ],
      },
      {
        path: 'orders',
        component: OrdersComponent,
        data: [{ selectedHeaderItemIndex: 1, selectedSubNavItemIndex: -1 }],
      },
      {
        path: 'orders/:id',
        component: OrderDetailsComponent,
        data: [{ selectedHeaderItemIndex: 1, selectedSubNavItemIndex: -1 }],
      },
      {
        path: 'products',
        component: ProductsComponent,
        data: [{ selectedHeaderItemIndex: 2, selectedSubNavItemIndex: -1 }],
      },
      {
        path: 'customers',
        component: CustomersComponent,
        data: [{ selectedHeaderItemIndex: 3, selectedSubNavItemIndex: -1 }],
      },
      {
        path: 'employees',
        component: EmployeesComponent,
        data: [{ selectedHeaderItemIndex: 4, selectedSubNavItemIndex: -1 }],
      },

      // INSTRUCTOR
      {
        path: 'instructors',
        component: InstructorComponent,
        data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          {
            path: 'all',
            component: AllInstructorComponent,
            data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'add',
            component: AddInstructorComponent,
            data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'edit/:id',
            component: EditInstructorComponent,
            data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'readonly/:id',
            component: ReadOnlyInstructorComponent,
            data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }],
          },
        ],
      },
      // UNIVERSITY
      {
        path: 'university',
        component: UniversityComponent,
        data: [{ selectedHeaderItemIndex: 6, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          {
            path: 'all',
            component: AllTeacherComponent,
            data: [{ selectedHeaderItemIndex: 6, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'add',
            component: AddTeacherComponent,
            data: [{ selectedHeaderItemIndex: 6, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'readonly/:h_id',
            component: ReadOnlyTeacherComponent,
            data: [{ selectedHeaderItemIndex: 6, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'edit/:h_id',
            component: EditTeacherComponent,
            data: [{ selectedHeaderItemIndex: 6, selectedSubNavItemIndex: -1 }],
            /* ,children : [
                { path: "", redirectTo: "all-line", pathMatch: 'full' },
                { path: "all-line", component: AllStudentComponent },
                { path: "add-line", component: AddStudentComponent },
                { path: "edit-line/:l_id", component: EditStudentComponent },
                { path: "line-details/:l_id", component: ReadOnlyStudentComponent }
              ] */
          },
        ],
      },

      {
        path:'report2',
        component: Reportbuilder2Component,
        pathMatch:'full'
      },

      {
        path: 'abtui', component: AbtuisalesnewComponent, data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AbtuiallComponent, data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }] },
          { path: 'add', component: AbtuiaddComponent, data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }] },
          { path: 'read/:id', component: AbtuireadonlyComponent, data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }] },
          { path: 'update/:id', component: AbtuiupdateComponent, data: [{ selectedHeaderItemIndex: 5, selectedSubNavItemIndex: -1 }] },
        ]
      },

      // EXTENSION FIELDS
      // university/add/extension
      {
        path: 'extension',
        component: ExtensionComponent,
        data: [{ selectedHeaderItemIndex: 7, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: '/all', pathMatch: 'full' },
          {
            path: 'all',
            component: AllExtensionComponent,
            data: [{ selectedHeaderItemIndex: 7, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'add',
            component: AddExtensionComponent,
            data: [{ selectedHeaderItemIndex: 7, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'readonly/:id',
            component: ReadOnlyExtensionComponent,
            data: [{ selectedHeaderItemIndex: 7, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'edit/:id',
            component: EditExtensionComponent,
            data: [{ selectedHeaderItemIndex: 7, selectedSubNavItemIndex: -1 }],
          },
        ],
      },
      // DYNAMIC FORM SETUP START
      {
        path: 'dynamic-form-setup',
        component: FormsSetupComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AllFormsSetupComponent },
          { path: 'add', component: AddFormsSetupComponent },
          { path: 'readonly/:id', component: ReadOnlyFormsSetupComponent },
          { path: 'edit/:id', component: EditFormsSetupComponent },
        ],
      },
      {
        path: 'flf',
        component: Flf_buildersalesnewComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: Flf_builderallsalesComponent },
          { path: 'add', component: Flf_builderaddsalesComponent },
          { path: 'readonly/:id', component: Flf_builderreadonlynewComponent },
          { path: 'edit/:id', component: Flf_buildereditsalesComponent },
        ],
      },
      // DYNAMIC FORM SETUP END


      // DYNAMIC FORMS START
      {
        path: 'dynamic-form-test1',
        component: DynamicFormComponent,
        data: [{ selectedHeaderItemIndex: 9, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          {
            path: 'all',
            component: AllDynamicFormComponent,
            data: [{ selectedHeaderItemIndex: 9, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'add',
            component: AddDynamicFormComponent,
            data: [{ selectedHeaderItemIndex: 9, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'readonly/:id',
            component: ReadOnlyDynamicFormComponent,
            data: [{ selectedHeaderItemIndex: 9, selectedSubNavItemIndex: -1 }],
          },
          {
            path: 'edit/:id',
            component: EditDynamicFormComponent,
            data: [{ selectedHeaderItemIndex: 9, selectedSubNavItemIndex: -1 }],
          },
        ],
      },
      // DYNAMIC FORMS END

      // MENU REGISTER START
      {
        path: 'menu-register',
        component: MenuRegisterComponent,
        data: [{ selectedHeaderItemIndex: 10, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          {
            path: 'all',
            component: AllMenuRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 10, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'add',
            component: AddMenuRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 10, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'readonly/:id',
            component: ReadOnlyMenuRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 10, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'edit/:id',
            component: EditMenuRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 10, selectedSubNavItemIndex: -1 },
            ],
          },
        ],
      }, // MENU REGISTER END
      // FUNCTION REGISTER START
      {
        path: 'function-register',
        component: FunctionRegisterComponent,
        data: [{ selectedHeaderItemIndex: 11, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          {
            path: 'all',
            component: AllFunctionRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 11, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'add',
            component: AddFunctionRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 11, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'readonly/:id',
            component: ReadOnlyFunctionRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 11, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'edit/:id',
            component: EditFunctionRegisterComponent,
            data: [
              { selectedHeaderItemIndex: 11, selectedSubNavItemIndex: -1 },
            ],
          },
        ],
      }, // FUNCTION REGISTER END
      // MENU GROUP START
      {
        path: 'menu-group',
        component: MenuGroupComponent,
        data: [{ selectedHeaderItemIndex: 12, selectedSubNavItemIndex: -1 }],
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          {
            path: 'all',
            component: AllMenuGroupComponent,
            data: [
              { selectedHeaderItemIndex: 12, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'add',
            component: AddMenuGroupComponent,
            data: [
              { selectedHeaderItemIndex: 12, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'readonly/:id',
            component: ReadOnlyMenuGroupComponent,
            data: [
              { selectedHeaderItemIndex: 12, selectedSubNavItemIndex: -1 },
            ],
          },
          {
            path: 'edit/:id',
            component: EditMenuGroupComponent,
            data: [
              { selectedHeaderItemIndex: 12, selectedSubNavItemIndex: -1 },
            ],
          },
        ],
      }, // MENU GROUP END

      { path: 'profile-settings', component: UserProfileSettingsComponent },
      { path: 'user-account', component: UserAccountComponent },
      { path: 'reset-password', component: PasswordResetComponent },

      // ORG-USERS START
      {
        path: 'users',
        component: OrgUserComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AllOrgUserComponent },
          { path: 'add', component: AddOrgUserComponent },
          { path: 'readonly/:id', component: ReadOnlyOrgUserComponent },
          { path: 'edit/:id', component: EditOrgUserComponent },
        ],
      }, // ORG-USERS END
      // code extraction start
      {
        path: 'code-extraction',
        component: CodeExtractionComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AllCodeExtractionComponent },
          { path: 'add', component: AddCodeExtractionComponent },
          { path: 'readonly/:id', component: ReadOnlyCodeExtractionComponent },
          //{ path: 'edit/:id'   , component: EditOrgUserComponent     },
          { path: 'file-editor', component: FileEditorComponent },
          {
            path: 'params',
            component: CodeExtractionParamsComponent,
            children: [
              { path: '', redirectTo: 'all', pathMatch: 'full' },
              { path: 'all', component: AllCodeExtractionParamComponent },
              { path: 'edit/:id', component: EditCodeExtractionParamComponent },
              {
                path: 'readonly/:id',
                component: ReadonlyCodeExtractionParamComponent,
              },
              {
                path: 'file-editor-new/:id',
                component: FileEditorNewComponent,
              },
            ],
          },
        ],
      }, // code extraction end

      // Rule Library Start
      {
        path: 'rule-library',
        component: RuleLibraryComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AllRuleLibraryComponent },
          { path: 'add', component: AddRuleLibraryComponent },
          { path: 'readonly/:id', component: ReadonlyRuleLibraryComponent },
          { path: 'edit/:id', component: EditRuleLibraryComponent },
        ],
      }, // Rule library END
      // Exception Rule Library Start
      {
        path: 'exception-rule-library',
        component: ExceptionRuleLibraryComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AllExceptionRuleLibraryComponent },
          { path: 'add', component: AddExceptionRuleLibraryComponent },
          {
            path: 'readonly/:id',
            component: ReadonlyExceptionRuleLibraryComponent,
          },
          { path: 'edit/:id', component: EditExceptionRuleLibraryComponent },
        ],
      }, // Exception Rule library END

      // TECHNOLOGY STACK Start
      {
        path: 'tech-stack',
        component: TechnologyStackComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AllTechnologyStackComponent },
          { path: 'add', component: AddTechnologyStackComponent },
          { path: 'readonly/:id', component: ReadonlyTechnologyStackComponent },
          /* { path: 'edit/:id'   , component: EditTechn }, */
        ],
      }, // TECHNOLOGY STACK END


       // PROJECT SETUP AND MODULE SETUP
        // project setup start
       { path:'projects' , component: ProjectSetupComponent,
          children : [
            { path: ''        , redirectTo: 'all', pathMatch: 'full'},
            { path: 'all'   , component: AllProjectSetupComponent},
            { path: 'add'   , component: AddProjectSetupComponent},
            { path: 'readonly/:id'   , component: ReadonlyProjectSetupComponent},
            { path: 'edit/:id'   , component: EditProjectSetupComponent},
            { path: 'modules'   , component: ModuleSetupComponent,
              children : [
                { path: ''        , redirectTo: 'all', pathMatch: 'full'},
                { path: 'all'   , component: AllModuleSetupComponent },
                { path: 'add'   , component: AddModuleSetupComponent },
                { path: 'edit/:id'   , component: EditModuleSetupComponent },
                { path: 'readonly/:id'   , component: ReadonlyModuleSetupComponent },
                {path:'actions' , component: ActionsComponent },
                {path:'bi-build' , component: SelectBiComponent },
                 // wireframe start
                { path:'wireframe' , component: WireframeComponent,
                  children : [
                    { path: ''        , redirectTo: 'all', pathMatch: 'full'},
                    { path: 'all'   , component: AllWireframeComponent },
                    { path: 'add'   , component: AddWireframeComponent },
                    {path:'types' , component: WireframeTypeComponent },
                    { path: 'readonly/:id'   , component: ReadonlyWireframeComponent },
                    { path: 'edit/:id'   , component: EditWireframeComponent },
                    { path: 'edit/:id/properties'   , component: FieldPropertyComponent },
                    { path: 'edit/:id/uinameedit'   , component: UinameeditComponent }
                  ]
                },
                  //type_report
                  {
                    path: 'reporttype'   , component: ReporttypeComponent
                  },

                  //report builder entity

                  { path:'entity-report' , component: Report2Component,
                  children : [
                    { path: ''        , redirectTo: 'all', pathMatch: 'full'},
                    { path: 'all'   , component: entityreportall  },
                    { path: 'add'   , component: entityreportadd },
                    { path: 'edit/:id'   , component: entityreportedit },
                    { path: 'entityreporttest'   , component:  entityreporttest},
                    {path:'servicereport' , component:servicereport},
{ path: 'science'   , component:  science},
//add_routingreport
                  ]
                },

                //report builder
                { path:'report-builder' , component: ReportBuilderComponent,
                  children : [
                    { path: ''        , redirectTo: 'all', pathMatch: 'full'},
                    { path: 'all'   , component:  AllComponent},
                    { path: 'add'   , component: AddComponent },
                    { path: 'edit/:id'   , component: EditComponent },
                    { path: 'table-setup'   , component: RbTableSetupComponent},
                    { path: 'column-setup'   , component: RbColumnSetupComponent},
                    { path: 'where-param'   , component: RbWhereColumnSetupComponent},
                    { path: 'date-param'   , component: RbDateParamSetupComponent},
                    { path: 'adhoc-param'   , component: RbAdhocParamSetupComponent},
                    { path: 'std-param'   , component: RbStdParamComponent},
                    { path: 'query-param'   , component: RbQueryBuildComponent},
                    { path: 'query-runner'   , component: QueryRunnerComponent},
                    { path: 'viratreport'   , component: viratreport},
                   // { path: 'readonly/:id'   , component: ReadonlyWireframeComponent },
                   // { path: 'edit/:id'   , component: EditWireframeComponent },
                   // { path: 'edit/:id/properties'   , component: FieldPropertyComponent }
                   { path: 'test_21'   , component:  test_21},

{ path: 'test_24'   , component:  test_24},

{ path: 'science'   , component:  science},
//add_routingreport
                  ]
                },

                // wireframe end
                {
                  path:'bi-widgets' , component: BiWidgetsComponent,
                  children : [{ path: ''        , redirectTo: 'all', pathMatch: 'full'},
                  { path: 'all'   , component:  AllWidgetComponent},
                  { path: 'add-widget'   , component: AddWidgetComponent },
                  { path: 'edit-widget/:id'   , component: EditWidgetsComponent }
                  ]

                },
                {
                  path:'bi-dashboard' , component: BiDashboardComponent,
                  children : [{ path: ''        , redirectTo: 'all-dash', pathMatch: 'full'},
                  { path: 'all-dash'   , component:  AllDashComponent},
                  { path: 'add-dash'   , component: AddDashComponent },
                  { path: 'widget-dash/:id'   , component: WidgetDashboardComponent },
                  { path: 'ganesh'   , component:  ganesh},

              { path: 'test_19'   , component:  test_19},

                { path: 'dash_21'   , component:  dash_21},
//add_routing



                  { path: 'edit-dash/:id'   , component: EditDashComponent,
                  children:[
                  { path: ''        , redirectTo: 'add-defination', pathMatch: 'full'},
                  { path: 'add-defination'   , component: AddDefinationComponent}]},
                  { path: 'add-def/:id'   , component: AddDefinationComponent},
                  { path: 'update-modal/:id/:name',component:UpdateWidgetModalComponent}

                 ]
                }
              ]
            }
          ]
        }, // project setup end

        {
          path:'servicereport' , component:servicereport
        },
        {
          path:'actionbtnui' , component:ActionbuildermanualComponent
        },
        { path: 'entityreporttest'   , component:  entityreporttest},


      {
        path: 'action-builder',
        component: ActionBuilderComponent,
        children: [
          { path: '', redirectTo: 'all', pathMatch: 'full' },
          { path: 'all', component: AllActionBuilderComponent },
          { path: 'add', component: AddActionBuilderComponent },
          { path: 'readonly/:id', component: ReadonlyActionBuilderComponent },
          { path: 'edit/:id', component: EditActionBuilderComponent },
          {
            path: 'lines',
            component: ActionBuilderLineComponent,
            children: [
              { path: '', redirectTo: 'all', pathMatch: 'full' },
              { path: 'all', component: AllActionBuilderLineComponent },
              { path: 'add', component: AddActionBuilderLineComponent },
              { path: 'edit/:id', component: EditActionBuilderLineComponent },
              {
                path: 'readonly/:id',
                component: ReadonlyActionBuilderLineComponent,
              },
            ],
          },
        ],
      },

    // Action Builder END


    ],
  },
  /* { // this will be real structure of profile settings
    path: 'profile-settings',
    component: UserProfileSettingsComponent,
    canActivate:[AuthGuard],
    children:[  // Children paths are appended to the parent path
        { path: '', redirectTo: '/profile', pathMatch: 'full', data:[{selectedHeaderItemIndex:1, selectedSubNavItemIndex:-1}] },
        { path     : 'profile', component: ProfileComponent, data     : [{selectedHeaderItemIndex:0, selectedSubNavItemIndex:-1}]},
        { path: 'notifications'   , component: NotificationComponent     , data:[{selectedHeaderItemIndex:0, selectedSubNavItemIndex:0}]  },
        { path: 'accounts' , component: AccountsComponent   , data:[{selectedHeaderItemIndex:0, selectedSubNavItemIndex:1}]  }
    ]
  }, */
  //{ path: 'profile-settings' , component: UserProfileSettingsComponent , canActivate:[AuthGuard] },
  //{ path: '', redirectTo: '/create-account', pathMatch: 'full' },

  {
    path: 'create-account',
    component: UserRegistrationComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
  {
    path: 'varify-account',
    component: SocialLoginComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
  {
    path: 'signup',
    component: SignupComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
  {
    path: 'account-setup',
    component: AccountSetupComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
  {
    path: 'login',
    component: LoginComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
  {
    path: 'logout',
    component: LogoutComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
  {
    path: 'forbidden',
    component: ForbiddenComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
  {
    path: '**',
    component: PageNotFoundComponent,
    data: [{ selectedHeaderItemIndex: -1, selectedSubNavItemIndex: -1 }],
  },
];
@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })], //{useHash:true}
  exports: [RouterModule],
  declarations: [PageNotFoundComponent, ForbiddenComponent],
})

export class AppRoutingModule {}

