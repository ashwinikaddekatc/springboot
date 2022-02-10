package com.realnet.builders;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Header;
import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Header;
import com.realnet.actionbuilder.service.Rn_Cff_ActionBuilder_Service;
import com.realnet.flf.service.FieldTypeService;
import com.realnet.fnd.entity.Error;
import com.realnet.fnd.entity.ErrorPojo;
import com.realnet.fnd.entity.Rn_Lookup_Values;
import com.realnet.fnd.entity.Rn_Module_Setup;
import com.realnet.fnd.entity.Rn_Project_Setup;
import com.realnet.fnd.entity.Success;
import com.realnet.fnd.entity.SuccessPojo;
import com.realnet.fnd.service.Rn_LookUp_Service;
import com.realnet.utils.Constant;
import com.realnet.utils.RealNetUtils;
import com.realnet.wfb.entity.Rn_Fb_Header;
import com.realnet.wfb.entity.Rn_Fb_Line;
import com.realnet.wfb.entity.Rn_Fb_Line_DTO;
import com.realnet.wfb.service.Rn_WireFrame_Service;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Master Builder" })
public class techstack35_form_only_line_Builder {


 @Value("${angularProjectPath}")
	private String angularProjectPath; @Value("${projectPath}")
	private String projectPath; @Autowired
	private Rn_WireFrame_Service wireFrameService;

	@Autowired
	private Rn_LookUp_Service lookUpService;

	@Autowired
	private Rn_Cff_ActionBuilder_Service actionBuilderService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FieldTypeService fieldTypeService;
	@GetMapping(value = "/techstack35_form_only_line_Builder")
	public ResponseEntity<?> build_wireframe(@RequestParam("header_id") Integer id) throws IOException {

				System.out.println("id ::"+id);
			 lookUpService.createTable(id); 
 // extra button    
    List<Rn_Fb_Line> extraButton = wireFrameService.getExtraButton(id);	
	// HEADER VALUE
		Rn_Fb_Header rn_fb_header = wireFrameService.getById(id);
		
		// LINE VALUES
		List<Rn_Fb_Line> rn_fb_lines = rn_fb_header.getRn_fb_lines();
		
		// MODULE DETAILS
		Rn_Module_Setup module = rn_fb_header.getModule();
		
		// PROJECT DETAILS
		Rn_Project_Setup project = module.getProject();
		
		// ATTRIBUTE FLEX VALUES
		List<Rn_Lookup_Values> attribute_flex_values = lookUpService.getExtensions();
		String project_name = project.getProjectName();
		String module_name = module.getModuleName();		/*
		 *	Header Table Values
		 *  @GET UI_NAME
		 *  @SET controller, model, repository, service name.
		 */
		String technology_stack = rn_fb_header.getTechStack();
		String ui_name = RealNetUtils.toFirstUpperCase(rn_fb_header.getUiName());
		String form_code = rn_fb_header.getFormCode(); // value will come from db
		String controller_name = ui_name.concat("_Controller");
		String dao_name = ui_name.concat("_Dao");
		String dao_name_lower = dao_name.toLowerCase();		String dao_impl_name = ui_name.concat("_DaoImpl");
		String repository_name = ui_name.concat("_Repository");
		String service_name = ui_name.concat("_Service");
		String service_impl_name = ui_name.concat("_ServiceImpl");

		String table_name = ui_name.concat("_t"); // For @Column(table="table_name") && Model class name

		/*----First Upper names (back-end)----------*/
		String controller_name_first_upper = RealNetUtils.toFirstUpperCase(controller_name);
		String repository_name_first_upper = RealNetUtils.toFirstUpperCase(repository_name);
		String dao_name_first_upper = RealNetUtils.toFirstUpperCase(dao_name);
		String dao_impl_name_first_upper = RealNetUtils.toFirstUpperCase(dao_impl_name);
		String service_name_first_upper = RealNetUtils.toFirstUpperCase(service_name);
		String service_impl_name_first_upper = RealNetUtils.toFirstUpperCase(service_impl_name);
		String table_name_first_upper = RealNetUtils.toFirstUpperCase(table_name);
		String table_name_upper = table_name.toUpperCase(); // For Model class

		/*-------------lower names (back-end)----------*/
		String table_name_lower = table_name.toLowerCase();
		String repository_name_lower = repository_name.toLowerCase();
		String service_name_lower = service_name.toLowerCase();

		List<Rn_Fb_Line_DTO> lineListDto = rn_fb_lines.stream().map(line -> modelMapper.map(line, Rn_Fb_Line_DTO.class))
				.collect(Collectors.toList());
		
		// set table name in the dto class
		for(Rn_Fb_Line_DTO dto : lineListDto) {
			dto.setTable_name(table_name_lower);
		}
// ===========FRONT END FILE NAMES DEPENDS ON UI NAME===============
		String ng_ui_name = RealNetUtils.toFirstUpperCase(ui_name);
		String ng_model_ts_name = ng_ui_name.concat("_t");
		String ng_component_ts_name = ng_ui_name.concat("Component");
		String ng_module_ts_name = ng_ui_name.concat("Module");
		String ng_service_ts_name = ng_ui_name.concat("Service");
		String ng_routing_module_ts_name = ng_ui_name.concat("RoutingModule");

		String ng_service_ts_name_lower = ui_name.toLowerCase().concat("Service");
		String ng_model_ts_name_lower = ng_model_ts_name.toLowerCase();
		// Routing Path names
		String ng_path_name = ui_name.toLowerCase();
		// CRUD Components name
		String ng_all_grid_view_component_name = "All" + ng_ui_name.concat("Component");
		String ng_add_form_component_name = "Add" + ng_ui_name.concat("Component");
		String ng_edit_component_name = "Edit" + ng_ui_name.concat("Component");
		String ng_read_only_component_name = ng_ui_name + "DetailsComponent";
		// Extension Components name
		String ng_extension_add_component_name = "AddExt" + ng_ui_name.concat("Component");
		// File Folder name
		String ng_folder_name = ui_name.toLowerCase();
		String ng_file_name = ui_name.toLowerCase();

String sbolcontroller = ui_name +"departmentcontroller.java";
String  mainstr0 = sbolcontroller;
String sbolcontroller1=mainstr0.replace(".java", "");


String sbolentity = ui_name +"Department.java";
String  mainstr1 = sbolentity;
String sbolentity1=mainstr1.replace(".java", "");


String sbolrepository = ui_name +"departmentrepository.java";
String  mainstr2 = sbolrepository;
String sbolrepository1=mainstr2.replace(".java", "");


String sbolresponse = ui_name +"departmentresponse.java";
String  mainstr3 = sbolresponse;
String sbolresponse1=mainstr3.replace(".java", "");


String sbolservice = ui_name +"departmentservice.java";
String  mainstr4 = sbolservice;
String sbolservice1=mainstr4.replace(".java", "");


String sbolserviceimpl = ui_name +"departmentserviceimpl.java";
String  mainstr5 = sbolserviceimpl;
String sbolserviceimpl1=mainstr5.replace(".java", "");


String sboladdhtml = ui_name +"addlinedepart.component.html";
String  mainstr6 = sboladdhtml;
String sboladdhtml1=mainstr6.replace(".html", "");


String sboladdscss = ui_name +"addlinedepart.component.scss";
String  mainstr7 = sboladdscss;
String sboladdscss1=mainstr7.replace(".scss", "");


String sboladdts = ui_name +"addlinedepart.component.ts";
String  mainstr8 = sboladdts;
String sboladdts1=mainstr8.replace(".ts", "");

String sboladdts2=mainstr8.replace(".component.ts", "");


String sbolallhtml = ui_name +"alldepart.component.html";
String  mainstr9 = sbolallhtml;
String sbolallhtml1=mainstr9.replace(".html", "");


String sbolallscss = ui_name +"alldepart.component.scss";
String  mainstr10 = sbolallscss;
String sbolallscss1=mainstr10.replace(".scss", "");


String sbolallts = ui_name +"alldepart.component.ts";
String  mainstr11 = sbolallts;
String sbolallts1=mainstr11.replace(".ts", "");

String sbolallts2=mainstr11.replace(".component.ts", "");


String sboledithtml = ui_name +"editdepart.component.html";
String  mainstr12 = sboledithtml;
String sboledithtml1=mainstr12.replace(".html", "");


String sboleditscss = ui_name +"editdepart.component.scss";
String  mainstr13 = sboleditscss;
String sboleditscss1=mainstr13.replace(".scss", "");


String sboleditts = ui_name +"editdepart.component.ts";
String  mainstr14 = sboleditts;
String sboleditts1=mainstr14.replace(".ts", "");

String sboleditts2=mainstr14.replace(".component.ts", "");


String sbolreadonlyhtml = ui_name +"readonlydepart.component.html";
String  mainstr15 = sbolreadonlyhtml;
String sbolreadonlyhtml1=mainstr15.replace(".html", "");


String sbolreadonlyscss = ui_name +"readonlydepart.component.scss";
String  mainstr16 = sbolreadonlyscss;
String sbolreadonlyscss1=mainstr16.replace(".scss", "");


String sbolreadonlyts = ui_name +"readonlydepart.component.ts";
String  mainstr17 = sbolreadonlyts;
String sbolreadonlyts1=mainstr17.replace(".ts", "");

String sbolreadonlyts2=mainstr17.replace(".component.ts", "");


String sbolnewhtml = ui_name +"department-new.component.html";
String  mainstr18 = sbolnewhtml;
String sbolnewhtml1=mainstr18.replace(".html", "");


String sbolnewscss = ui_name +"department-new.component.scss";
String  mainstr19 = sbolnewscss;
String sbolnewscss1=mainstr19.replace(".scss", "");


String sbolts = ui_name +"departmentmodel.ts";
String  mainstr20 = sbolts;
String sbolts1=mainstr20.replace(".ts", "");

String sbolts2=mainstr20.replace(".component.ts", "");


String sbolnewts = ui_name +"departmentnew.component.ts";
String  mainstr21 = sbolnewts;
String sbolnewts1=mainstr21.replace(".ts", "");

String sbolnewts2=mainstr21.replace(".component.ts", "");



			FileWriter fw = null;
			BufferedWriter bw = null;
			try { 
		// EXTRA BUTTON LIST (IF USER ADD EXTRA BUTTON THEN THIS WILL CALL)
		List<Rn_Fb_Line> extraButtonList = wireFrameService.getExtraButton(id);
 	// CFF ACTION BUILDER
		StringBuilder cff_add_button_update_controller = new StringBuilder();
 	StringBuilder cff_add_button_code_in_update_jsp = new StringBuilder();
		int buttonLoopCount = 0;
		for (Rn_Fb_Line btn : extraButtonList) {
			System.out.println("BUTON LOOP COUNT = " + ++buttonLoopCount);
			String field_name = btn.getFieldName();
			String mapping = btn.getMapping();
			String method_name = btn.getMethodName();

			System.out.println("BUTTON-LOOP field name = " + field_name + " || mapping = " + mapping
					+ " || method_name = " + method_name);
			
		// CFF ACTION BUILDER CODE IN CONTROLLER..
		cff_add_button_update_controller.append("\nmap.addAttribute(\"" + table_name_lower + "_update\"," + table_name_lower + ");"
				+ "\nList<" + table_name_first_upper + "> report =" + service_name_lower + ".prefield(u_id);\n"
				+ "int updt_id = report.get(0).getId();\n" 
				// 
				+ "map.addAttribute(\"" + "cff_id\", update_id);\n" 
				+ "\nmap.addAttribute(\"" + table_name_lower + "_update\", report);");
			// JSP CODE FOR EXTRA BUTTON
			cff_add_button_code_in_update_jsp.append("\r\n<div>\r\n"
					+ "			<a href=\""
					+ method_name + "?id=${cff_id}\">\r\n"
					+ "					<button type=\"button\" id=\"" + mapping + "\" name = \"" + mapping
					+ "\" class=\"btn btn-success btn-sm btn-next\">" + field_name + "</button>\r\n"
					+ "			</a>\r\n" 
					+ "		</div>\r\n");
			StringBuilder cff_add_button_controller_code = new StringBuilder();
			StringBuilder cff_add_button_controller_imports = new StringBuilder();
			cff_add_button_controller_imports.append("package com.realnet." + module_name + ".controller;\r\n" 
					+ "\r\n"
					+ "import java.io.IOException;\r\n" 
					+ "import org.springframework.http.HttpStatus;\r\n" 
					+ "import org.springframework.http.ResponseEntity;\r\n" 
					+ "import org.springframework.web.bind.annotation.GetMapping;\r\n" 
					+ "import org.springframework.web.bind.annotation.RestController;\n" + "");

			// CONTROLLER NAME FOR EACH BUTTON TYPE
			String controllerName = method_name.substring(0, 1).toUpperCase()
					+ method_name.substring(1).concat("Controller");
			System.out.println("Niladri controllerName = " + controllerName);
			// CONTROLLER PREFIX
			cff_add_button_controller_code.append(
					cff_add_button_controller_imports +
 "\n @RestController\r\n" + "public class " + controllerName + " {\r\n" 
);

				cff_add_button_controller_code.append("	// INSERT FIELDS USING ACTION BUILDER\r\n"
						+ "@GetMapping(value = \"/" + method_name + "\")\r\n" + "	public ResponseEntity<?> " + method_name
						+ "() throws IOException {\r\n"
						+ "	// CFF_LAYOUT_CONTROLLER_START\r\n"
						+ "		System.out.println(\"PLEASE INSERT CODE... GO TO ACTION BUILDER... \");\r\n" 
						+ "	// CFF_LAYOUT_CONTROLLER_END\r\n" 
						+ "		return ResponseEntity.status(HttpStatus.OK).build();\r\n" 
						+ "	}\n" 
						+ "}");

			// FILE PATH CHANGE NEEDED...
			File file = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/" + module_name + "/controller/" + controllerName + ".java");
			System.out.println("Niladri file path = " + file.getAbsolutePath());
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(cff_add_button_controller_code.toString());
			bw.close();

			/*
			 * CONTROLLER NAME AND FILE-LOCATION SHOULD SAVE IN CFF ACTION BUILDER HEADER
			 * TABLE
			 */
			String file_location = file.getAbsolutePath().replace("\\", "/");
			// SHOULD BE LIKE = /Projects/only_header_testing/Demo.java
			file_location = file_location.substring(file_location.lastIndexOf("/Projects/"));
			/*
			 * ====== MODIFICATION ==== path =
			 * path.substring(path.lastIndexOf("/Projects/")+1); path =
			 * path.substring(path.indexOf("/")); 
			 * OP = /only_header_testing/Demo.java
			 */
			System.out.println("FILE LOCATION SUBSTRING = " + file_location);
			controllerName = controllerName.concat(".java");
			Rn_cff_ActionBuilder_Header header = new Rn_cff_ActionBuilder_Header();
				header.setRn_fb_header(rn_fb_header);// SAVE rn_fb_header_id
				header.setTechnologyStack(technology_stack);
				header.setControllerName(controllerName); // NO NEED
				header.setMethodName(method_name);
				header.setFileLocation(file_location);
				header.setActionName(method_name); // action name and method name is same
				actionBuilderService.save(header);
		}
		// EXTRA BUTTON LOOP END
 StringBuilder sbolcontrollerCode = new StringBuilder();
 sbolcontrollerCode.append(//import_change_controller_start "package com.realnet.comm.controller;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Department;" + "\r\n" + 
"import com.realnet.comm.response.departmentresponse;" + "\r\n" + 
"import com.realnet.comm.service.departmentservice;" + "\r\n" + 
"import com.realnet.comm.service.departmentserviceimpl;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import java.util.Optional;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.http.HttpHeaders;" + "\r\n" + 
"import org.springframework.http.HttpStatus;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.data.domain.PageRequest;" + "\r\n" + 
"import org.springframework.web.bind.annotation.DeleteMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PathVariable;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PutMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestBody;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestHeader;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestParam;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.Api;" + "\r\n" + 
"import io.swagger.annotations.ApiOperation;" + "\r\n" + //import_change_controller_end
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"@Api(tags = { \"Department\" })" + "\r\n" + 
"public class " + sbolcontroller1 + "{"+
"" + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    departmentservice deptser;" + "\r\n" + 
"    " + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    departmentserviceimpl dser;" + "\r\n" + 
"" + "\r\n" + 
"    // get all data" + "\r\n" + 
"    @ApiOperation(value = \"list of department\", response = departmentresponse.class)" + "\r\n" + 
"	@GetMapping(path = \"/getdept\")" + "\r\n" + 
"	public departmentresponse getdata(@RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer page," + "\r\n" + 
"			@RequestParam(value = \"size\", defaultValue = \"20\", required = false) Integer size)" + "\r\n" + 
"	{" + "\r\n" + 
"		departmentresponse resp=new departmentresponse();" + "\r\n" + 
"		Pageable paging = PageRequest.of(page, size);" + "\r\n" + 
"		Page<Department> result = this.deptser.getAll(paging);" + "\r\n" + 
"		resp.setPageStats(result, true);" + "\r\n" + 
"		resp.setDepartment(result.getContent());" + "\r\n" + 
"		return resp;" + "\r\n" + 
"    }" + "\r\n" + 
"    " + "\r\n" + 
"//    @GetMapping(\"/getdept\")" + "\r\n" + 
"//    public List<Department> getall()" + "\r\n" + 
"//    {" + "\r\n" + 
"//    	List<Department> getdept = dser.getdept();" + "\r\n" + 
"//    	return getdept;" + "\r\n" + 
"//    }" + "\r\n" + 
"    " + "\r\n" + 
"    @ApiOperation(value = \"add a sales\", response = Department.class)" + "\r\n" + 
"    @PostMapping(\"/savedept\")" + "\r\n" + 
"    public ResponseEntity<?> adddept(@RequestHeader(value = HttpHeaders.AUTHORIZATION, " + "\r\n" + 
"    required = false) String authToken," + "\r\n" + 
"    @RequestBody List<Department> department )" + "\r\n" + 
"    {" + "\r\n" + 
"		System.out.println(\"dept fields\" + department.get(0).getExtn1());" + "\r\n" + 
"" + "\r\n" + 
"		List<Department> save = deptser.save(department);" + "\r\n" + 
"    	" + "\r\n" + 
"		if(save==null)" + "\r\n" + 
"		{" + "\r\n" + 
"			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(\"department not created value empty\");" + "\r\n" + 
"		}" + "\r\n" + 
"		{" + "\r\n" + 
"    	return ResponseEntity.status(HttpStatus.CREATED).body(save);  " + "\r\n" + 
"		}" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	@ApiOperation(value = \"Get a det\", response = Department.class)" + "\r\n" + 
"	@GetMapping(\"/getdept/{id}\")" + "\r\n" + 
"	public ResponseEntity<?> getbyid(@PathVariable(\"id\") Integer id)" + "\r\n" + 
"	{" + "\r\n" + 
"		 Department getid = this.deptser.getone(id);" + "\r\n" + 
"		if (getid == null) {" + "\r\n" + 
"			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(\"department not found\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.ok().body(getid);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	@ApiOperation(value = \"update a dept\", response = Department.class)" + "\r\n" + 
"	@PutMapping(\"/update/{id}\")" + "\r\n" + 
"	public ResponseEntity<?> update(" + "\r\n" + 
"		@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"		@PathVariable(value = \"id\") Integer id, @Valid @RequestBody Department dept)" + "\r\n" + 
"	{" + "\r\n" + 
"			Department dep= deptser.updatedept(id, dept);" + "\r\n" + 
"			if (dep == null) {" + "\r\n" + 
"				return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(\"department empty\");" + "\r\n" + 
"			}" + "\r\n" + 
"			return ResponseEntity.ok().body(dep);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	@DeleteMapping(\"/delete/{id}\")" + "\r\n" + 
"	public ResponseEntity<String> deleteTeacher(@PathVariable(value =\"id\") Integer id) {" + "\r\n" + 
"		boolean delete = deptser.delete(id);" + "\r\n" + 
"		if(delete!=true)" + "\r\n" + 
"		{" + "\r\n" + 
"			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(delete+\"department not deleted\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(delete+\"   department deleted\");" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbolcontrollerFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/controller/" + sbolcontroller);
			System.out.println("Directory name = " + sbolcontrollerFile);
			File sbolcontrollerFileParentDir = new File(sbolcontrollerFile.getParent());
			if(!sbolcontrollerFileParentDir.exists()) {
				sbolcontrollerFileParentDir.mkdirs();
			}
			if (!sbolcontrollerFile.exists()) {
				sbolcontrollerFile.createNewFile();
			}
			fw = new FileWriter(sbolcontrollerFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolcontrollerCode.toString());
			bw.close();

 StringBuilder sbolentityCode = new StringBuilder();
 sbolentityCode.append("package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_AuditEntity;" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_Who_Columns;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbolentity1 + "{"+
"    " + "\r\n" + 
"    @Id" + "\r\n" + 
"    @GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"    private int Did;" + "\r\n" + 
"    private String Dname;" + "\r\n" + 
"    private String Dhead;" + "\r\n" + 
"    private long Dcontact;" + "\r\n" + 
"    private int No_ofEmp;" + "\r\n" + 
"}" 
);

	File sbolentityFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/entity/" + sbolentity);
			System.out.println("Directory name = " + sbolentityFile);
			File sbolentityFileParentDir = new File(sbolentityFile.getParent());
			if(!sbolentityFileParentDir.exists()) {
				sbolentityFileParentDir.mkdirs();
			}
			if (!sbolentityFile.exists()) {
				sbolentityFile.createNewFile();
			}
			fw = new FileWriter(sbolentityFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolentityCode.toString());
			bw.close();

 StringBuilder sbolrepositoryCode = new StringBuilder();
 sbolrepositoryCode.append("package com.realnet.comm." + module_name + ".repository;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Department;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.jpa.repository.JpaRepository;" + "\r\n" + 
"import org.springframework.stereotype.Repository;" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface " + sbolrepository1 + "{"+
"" + "\r\n" + 
"    " + "\r\n" + 
"    " + "\r\n" + 
"}" 
);

	File sbolrepositoryFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/repository/" + sbolrepository);
			System.out.println("Directory name = " + sbolrepositoryFile);
			File sbolrepositoryFileParentDir = new File(sbolrepositoryFile.getParent());
			if(!sbolrepositoryFileParentDir.exists()) {
				sbolrepositoryFileParentDir.mkdirs();
			}
			if (!sbolrepositoryFile.exists()) {
				sbolrepositoryFile.createNewFile();
			}
			fw = new FileWriter(sbolrepositoryFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolrepositoryCode.toString());
			bw.close();

 StringBuilder sbolresponseCode = new StringBuilder();
 sbolresponseCode.append("package com.realnet.comm." + module_name + ".response;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Department;" + "\r\n" + 
"import com.realnet.fnd.response.PageResponse;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"import lombok.EqualsAndHashCode;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@EqualsAndHashCode(callSuper=false)" + "\r\n" + 
"public class " + sbolresponse1 + "{"+
"    " + "\r\n" + 
"    @ApiModelProperty(required = true, value = \"\")" + "\r\n" + 
"    private List<Department> department;" + "\r\n" + 
"}" 
);

	File sbolresponseFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/responce/" + sbolresponse);
			System.out.println("Directory name = " + sbolresponseFile);
			File sbolresponseFileParentDir = new File(sbolresponseFile.getParent());
			if(!sbolresponseFileParentDir.exists()) {
				sbolresponseFileParentDir.mkdirs();
			}
			if (!sbolresponseFile.exists()) {
				sbolresponseFile.createNewFile();
			}
			fw = new FileWriter(sbolresponseFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolresponseCode.toString());
			bw.close();

 StringBuilder sbolserviceCode = new StringBuilder();
 sbolserviceCode.append("package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Department;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"" + "\r\n" + 
"public interface " + sbolservice1 + "{"+
"    Page<Department> getAll(Pageable paging);" + "\r\n" + 
"    List<Department> save(List<Department> department);" + "\r\n" + 
"    Department getone(int id);" + "\r\n" + 
"    Department updatedept(int id,Department dept);" + "\r\n" + 
"    boolean delete(int id);" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbolserviceFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/service/" + sbolservice);
			System.out.println("Directory name = " + sbolserviceFile);
			File sbolserviceFileParentDir = new File(sbolserviceFile.getParent());
			if(!sbolserviceFileParentDir.exists()) {
				sbolserviceFileParentDir.mkdirs();
			}
			if (!sbolserviceFile.exists()) {
				sbolserviceFile.createNewFile();
			}
			fw = new FileWriter(sbolserviceFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolserviceCode.toString());
			bw.close();

 StringBuilder sbolserviceimplCode = new StringBuilder();
 sbolserviceimplCode.append("package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Department;" + "\r\n" + 
"import com.realnet.comm.repository.departmentrepository;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class " + sbolserviceimpl1 + "{"+
"" + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    private departmentrepository deptrepo;" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public Page<Department> getAll(Pageable page) {" + "\r\n" + 
"" + "\r\n" + 
"        Page<Department> find = this.deptrepo.findAll(page);" + "\r\n" + 
"" + "\r\n" + 
"        return find;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    // public List<Department> getdept()" + "\r\n" + 
"    // {" + "\r\n" + 
"    // List<Department> findAll = deptrepo.findAll();" + "\r\n" + 
"    // return findAll;" + "\r\n" + 
"    // }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public List<Department> save(List<Department> department) {" + "\r\n" + 
"        List<Department> saveAll = deptrepo.saveAll(department);" + "\r\n" + 
"        return saveAll;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public Department getone(int id) {" + "\r\n" + 
"        Department dept = deptrepo.findById(id)." + "\r\n" + 
"        		orElseThrow(() -> new ResourceNotFoundException(\"department not found :: \" + id));;" + "\r\n" + 
"        return dept;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public Department updatedept(int id, Department dept) {" + "\r\n" + 
"        Department dep = deptrepo.findById(id)" + "\r\n" + 
"                .orElseThrow(() -> new ResourceNotFoundException(\"department not found :: \" + id));" + "\r\n" + 
"" + "\r\n" + 
"        dep.setDcontact(dept.getDcontact());" + "\r\n" + 
"        dep.setDhead(dept.getDhead());" + "\r\n" + 
"        dep.setDname(dept.getDname());" + "\r\n" + 
"        dep.setNo_ofEmp(dept.getNo_ofEmp());" + "\r\n" + 
"        final Department newdept = deptrepo.save(dep);" + "\r\n" + 
"" + "\r\n" + 
"        return newdept;" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @Override" + "\r\n" + 
"    public boolean delete(int id) {" + "\r\n" + 
"        if (!deptrepo.existsById(id)) {" + "\r\n" + 
"			" + "\r\n" + 
"            throw new ResourceNotFoundException(\" not found :: \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"		Department sale = deptrepo.findById(id).orElse(null);" + "\r\n" + 
"		deptrepo.delete(sale);" + "\r\n" + 
"        return true;" + "\r\n" + 
"    }" + "\r\n" + 
"    " + "\r\n" + 
"    " + "\r\n" + 
"}" 
);

	File sbolserviceimplFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/service/" + sbolserviceimpl);
			System.out.println("Directory name = " + sbolserviceimplFile);
			File sbolserviceimplFileParentDir = new File(sbolserviceimplFile.getParent());
			if(!sbolserviceimplFileParentDir.exists()) {
				sbolserviceimplFileParentDir.mkdirs();
			}
			if (!sbolserviceimplFile.exists()) {
				sbolserviceimplFile.createNewFile();
			}
			fw = new FileWriter(sbolserviceimplFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolserviceimplCode.toString());
			bw.close();

 StringBuilder sboladdhtmlCode = new StringBuilder();
 sboladdhtmlCode.append("<div class=\"container\">" + "\r\n" + 
"    <h3 class=\"center\"><b>LINE ONLY ENTRY FORM</b>" + "\r\n" + 
"    </h3>" + "\r\n" + 
"" + "\r\n" + 
"    <div>" + "\r\n" + 
"        <a style=\" display: block;margin-left: 90%;\" [routerLink]=\"['../../extension/all']\">" + "\r\n" + 
"            <clr-icon shape=\"airplane\" size=\"32\"></clr-icon>" + "\r\n" + 
"        </a>" + "\r\n" + 
"    </div>" + "\r\n" + 
"" + "\r\n" + 
"    <!-- Header form start -->" + "\r\n" + 
"    <br />" + "\r\n" + 
"" + "\r\n" + 
"    <div class=\"section\">" + "\r\n" + 
"        <p> Header </p>" + "\r\n" + 
"    </div>" + "\r\n" + 
"    <!-- entry form-->" + "\r\n" + 
"    <section>" + "\r\n" + 
"" + "\r\n" + 
"        <form [formGroup]=\"entryForm\" (ngSubmit)=\"onSubmit()\">" + "\r\n" + 
"            <div class=\"clr-row\">" + "\r\n" + 
"                <div class=\"clr-col-lg-12\">" + "\r\n" + 
"                    <!-- <table class=\"table\" style=\"width:1100%;\" formArrayName=\"department\">" + "\r\n" + 
"                        <thead>" + "\r\n" + 
"                            <tr>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"                                <th class=\"left\" style=\"width:125px;\">Dname</th>" + "\r\n" + 
"                                <th class=\"left\" style=\"width:125px;\">Dhead</th>" + "\r\n" + 
"                                <th class=\"left\" style=\"width:125px;\">Dcontact</th>" + "\r\n" + 
"                                <th class=\"left\" style=\"width:125px;\">No_ofEmp</th>" + "\r\n" + 
"                                " + "\r\n" + 
"                                " + "\r\n" + 
"                                <th class=\"right\" style=\"width:125px;\">{{ controls.length > 1 ? 'Actions' : '' }}</th>" + "\r\n" + 
"" + "\r\n" + 
"                            </tr>" + "\r\n" + 
"                        </thead>" + "\r\n" + 
"                        <tbody>" + "\r\n" + 
"                            <tr *ngFor=\"let item of controls; let i=index\" [formGroupName]=\"i\">" + "\r\n" + 
"" + "\r\n" + 
"                                <td class=\"left\">" + "\r\n" + 
"" + "\r\n" + 
"                                    <input colspan=\"2\" type=\"text\" formControlName=\"dname\" class=\"clr-input\"" + "\r\n" + 
"                                        placeholder=\"Enter name\" />" + "\r\n" + 
"                                </td>" + "\r\n" + 
"" + "\r\n" + 
"                                <td class=\"left\">" + "\r\n" + 
"" + "\r\n" + 
"                                    <input colspan=\"2\" type=\"text\" formControlName=\"dhead\" class=\"clr-input\"" + "\r\n" + 
"                                        placeholder=\"Enter name\" />" + "\r\n" + 
"                                </td>" + "\r\n" + 
"" + "\r\n" + 
"                                <td class=\"left\">" + "\r\n" + 
"" + "\r\n" + 
"                                    <input colspan=\"2\" type=\"number\" formControlName=\"dcontact\" class=\"clr-input\"" + "\r\n" + 
"                                        placeholder=\"Enter contactno\" />" + "\r\n" + 
"                                </td>" + "\r\n" + 
"" + "\r\n" + 
"                                <td class=\"left\">" + "\r\n" + 
"" + "\r\n" + 
"                                    <input colspan=\"2\" type=\"number\" formControlName=\"no_ofEmp\" class=\"clr-input\"" + "\r\n" + 
"                                        placeholder=\"Enter no of emp\" />" + "\r\n" + 
"                                </td>" + "\r\n" + 
"" + "\r\n" + 
"                                " + "\r\n" + 
"                             " + "\r\n" + 
"                                <td class=\"left\" form >" + "\r\n" + 
"                                 <!-- EXTENSION FIELDS START  -->" + "\r\n" + 
"                                 <!-- <teacher-add-extension [extensionForm]=\"extention\"></teacher-add-extension> -->" + "\r\n" + 
"                                 <!-- EXTENSION FIELDS END -->" + "\r\n" + 
"                                <!-- </td> -->" + "\r\n" + 
"                               " + "\r\n" + 
"" + "\r\n" + 
"                    " + "\r\n" + 
"" + "\r\n" + 
"                                <!-- <td style=\"width:40px;\">" + "\r\n" + 
"                                    <a *ngIf=\"controls.length > 1\" (click)=\"onRemoveLines(i)\">" + "\r\n" + 
"                                        <clr-icon shape=\"trash\" class=\"is-error\"></clr-icon>" + "\r\n" + 
"                                    </a>" + "\r\n" + 
"                                </td>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"                            </tr>" + "\r\n" + 
"                        </tbody>" + "\r\n" + 
"                        <button type=\"button\" class=\"btn btn-primary button1\" (click)=\"onAddLines()\">" + "\r\n" + 
"                            <clr-icon shape=\"plus\"></clr-icon>" + "\r\n" + 
"                        </button>" + "\r\n" + 
"                    </table> --> " + "\r\n" + 
"                    <div  formArrayName=\"department\">" + "\r\n" + 
"                        <div class=\"clr-row\" *ngFor=\"let item of controls; let i=index\" [formGroupName]=\"i\">" + "\r\n" + 
"                            <div class=\"clr-col-md-2 clr-col-sm-12\"  > <label for=\"\"> Dname</label> " + "\r\n" + 
"                                <input colspan=\"2\" type=\"text\" formControlName=\"dname\" class=\"clr-input\"" + "\r\n" + 
"                                        placeholder=\"Enter name\" />" + "\r\n" + 
"                            </div>" + "\r\n" + 
"" + "\r\n" + 
"                            <div class=\"clr-col-md-2 clr-col-sm-12\"  > <label for=\"\"> Dhead</label> " + "\r\n" + 
"                                <input colspan=\"2\" type=\"text\" formControlName=\"extn1\" class=\"clr-input\"" + "\r\n" + 
"                                placeholder=\"Enter name\" />" + "\r\n" + 
"                            </div>" + "\r\n" + 
"" + "\r\n" + 
"                           " + "\r\n" + 
"                                <teacher-add-extension [extensionForm]=\"extention\"></teacher-add-extension>" + "\r\n" + 
"                            " + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"                            <div class=\"clr-col-md-2 clr-col-sm-12\"  >" + "\r\n" + 
"                            <a *ngIf=\"controls.length > 1\" (click)=\"onRemoveLines(i)\">" + "\r\n" + 
"                                <clr-icon shape=\"trash\" class=\"is-error\"></clr-icon>" + "\r\n" + 
"                            </a>" + "\r\n" + 
"                        </div>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"                        </div>" + "\r\n" + 
"" + "\r\n" + 
"                        <button type=\"button\" class=\"btn btn-primary button1\" (click)=\"onAddLines()\">" + "\r\n" + 
"                            <clr-icon shape=\"plus\"></clr-icon>" + "\r\n" + 
"                        </button>" + "\r\n" + 
"" + "\r\n" + 
"                    </div>" + "\r\n" + 
"                " + "\r\n" + 
"                " + "\r\n" + 
"                " + "\r\n" + 
"                </div>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"            </div>" + "\r\n" + 
"" + "\r\n" + 
"            <br>" + "\r\n" + 
"            <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"!entryForm.valid\">SUBMIT</button>" + "\r\n" + 
"        </form>" + "\r\n" + 
"    </section>" + "\r\n" + 
"</div>" 
);

	File sboladdhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/addlinedepart/" + sboladdhtml);
			System.out.println("Directory name = " + sboladdhtmlFile);
			File sboladdhtmlFileParentDir = new File(sboladdhtmlFile.getParent());
			if(!sboladdhtmlFileParentDir.exists()) {
				sboladdhtmlFileParentDir.mkdirs();
			}
			if (!sboladdhtmlFile.exists()) {
				sboladdhtmlFile.createNewFile();
			}
			fw = new FileWriter(sboladdhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sboladdhtmlCode.toString());
			bw.close();

 StringBuilder sboladdscssCode = new StringBuilder();
 sboladdscssCode.append(".clr-input {" + "\r\n" + 
"    color: #212529;" + "\r\n" + 
"    border: 1px solid #ced4da;" + "\r\n" + 
"    border-radius: 0.25rem;" + "\r\n" + 
"    padding: 0.75rem 0.75rem;" + "\r\n" + 
"    margin-top: 3px;" + "\r\n" + 
"    width: 100%;" + "\r\n" + 
"    margin-bottom: 10px;" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
"  .center {" + "\r\n" + 
"    text-align: center;" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
"  $bg-color: #dddddd;" + "\r\n" + 
"  .section {" + "\r\n" + 
"    background-color: $bg-color;" + "\r\n" + 
"    height: 40px;" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
"  .section p {" + "\r\n" + 
"    //color: white;" + "\r\n" + 
"    padding: 10px;" + "\r\n" + 
"    font-size: 18px;" + "\r\n" + 
"  }" + "\r\n" + 
"  #doj{" + "\r\n" + 
"    border: 5px;" + "\r\n" + 
"    background: gray;" + "\r\n" + 
"    text-transform: uppercase;" + "\r\n" + 
"    margin: 10px;" + "\r\n" + 
"    padding: 5px;" + "\r\n" + 
"  }" 
);

	File sboladdscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/addlinedepart/" + sboladdscss);
			System.out.println("Directory name = " + sboladdscssFile);
			File sboladdscssFileParentDir = new File(sboladdscssFile.getParent());
			if(!sboladdscssFileParentDir.exists()) {
				sboladdscssFileParentDir.mkdirs();
			}
			if (!sboladdscssFile.exists()) {
				sboladdscssFile.createNewFile();
			}
			fw = new FileWriter(sboladdscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sboladdscssCode.toString());
			bw.close();

 StringBuilder sboladdtsCode = new StringBuilder();
 sboladdtsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { FormArray, FormBuilder, FormGroup } from '@angular/forms';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DepartmentService } from 'src/app/services/api/department.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"interface errorMsg {" + "\r\n" + 
"  field: any;" + "\r\n" + 
"  message: any;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-addlinedepart'," + "\r\n" + 
 " templateUrl: './"+sboladdts1+".html',"
 
+
 "  styleUrls: ['./"+sboladdts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sboladdts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  public entryForm: FormGroup;" + "\r\n" + 
"  " + "\r\n" + 
"" + "\r\n" + 
"  submitted = false;" + "\r\n" + 
"  errorMsg: errorMsg[] = [];" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"    dropdownval=[\"yes\",\"no\",\"dont know\"];" + "\r\n" + 
"  	autocomlist= [\"1000\",\"1001\",\"1002\",\"1003\",\"1004\",\"1005\",\"1006\",\"1007\",\"1008\",\"1009\",\"1010\"];" + "\r\n" + 
"	" + "\r\n" + 
"  	private formCode: string = 'department_form';" + "\r\n" + 
"	  // STORE FORM CODE IN SESSION" + "\r\n" + 
"	  public key: string = \"formCode\";" + "\r\n" + 
"	  public storage: Storage = sessionStorage;" + "\r\n" + 
"  " + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private _fb: FormBuilder," + "\r\n" + 
"    private router: Router," + "\r\n" + 
"    private deptser:DepartmentService," + "\r\n" + 
"    private route:ActivatedRoute" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.storage.setItem(this.key, this.formCode);" + "\r\n" + 
"		console.log(this.storage.getItem(this.key));" + "\r\n" + 
"" + "\r\n" + 
"   " + "\r\n" + 
"" + "\r\n" + 
"    this.entryForm = this._fb.group({" + "\r\n" + 
"      department: this._fb.array([this.initLinesForm()])," + "\r\n" + 
"      " + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"  " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  initLinesForm() {" + "\r\n" + 
"    return this._fb.group({" + "\r\n" + 
"      dname: [null]," + "\r\n" + 
"      dhead: [null]," + "\r\n" + 
"      dcontact: [null]," + "\r\n" + 
"      no_ofEmp: [null]," + "\r\n" + 
"      " + "\r\n" + 
"" + "\r\n" + 
"                  // EXTENSION" + "\r\n" + 
"              extn1: [null]," + "\r\n" + 
"              extn2: [null]," + "\r\n" + 
"              extn3: [null]," + "\r\n" + 
"              extn4: [null]," + "\r\n" + 
"              extn5: [null]," + "\r\n" + 
"              extn6: [null]," + "\r\n" + 
"              extn7: [null]," + "\r\n" + 
"              extn8: [null]," + "\r\n" + 
"              extn9: [null]," + "\r\n" + 
"              extn10: [null]," + "\r\n" + 
"              extn11: [null]," + "\r\n" + 
"              extn12: [null]," + "\r\n" + 
"              extn13: [null]," + "\r\n" + 
"              extn14: [null]," + "\r\n" + 
"              extn15: [null]," + "\r\n" + 
"              // FLEX" + "\r\n" + 
"              flex1: [null]," + "\r\n" + 
"              flex2: [null]," + "\r\n" + 
"              flex3: [null]," + "\r\n" + 
"              flex4: [null]," + "\r\n" + 
"              flex5: [null]," + "\r\n" + 
"" + "\r\n" + 
"          " + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  " + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"    console.log(this.entryForm.getRawValue()['department']);" + "\r\n" + 
"    console.warn(\"my data\"+this.entryForm.value);" + "\r\n" + 
"    " + "\r\n" + 
"    //console.log(this.entryForm.value);" + "\r\n" + 
"    this.submitted = true;" + "\r\n" + 
"    if (this.entryForm.invalid) {" + "\r\n" + 
"      return;" + "\r\n" + 
"    }" + "\r\n" + 
"    this.onCreate();" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  onCreate() {" + "\r\n" + 
"    console.warn(\"in the oncreate \");" + "\r\n" + 
"    " + "\r\n" + 
"     this.deptser.create(this.entryForm.getRawValue()['department']).subscribe(data => {" + "\r\n" + 
"       console.log(data)" + "\r\n" + 
"       this.router.navigate([\"/home/department/\"]);" + "\r\n" + 
"       }," + "\r\n" + 
"       (error) => {" + "\r\n" + 
"         console.log(error);" + "\r\n" + 
"       }" + "\r\n" + 
"     );" + "\r\n" + 
"   }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"   get controls() {" + "\r\n" + 
"    return (this.entryForm.get(\"department\") as FormArray).controls;" + "\r\n" + 
"  }" + "\r\n" + 
"  onRemoveLines(index: number) {" + "\r\n" + 
"    (<FormArray>this.entryForm.get(\"department\")).removeAt(index);" + "\r\n" + 
"  }" + "\r\n" + 
"  onAddLines() {" + "\r\n" + 
"    (<FormArray>this.entryForm.get(\"department\")).push(this.initLinesForm());" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  extensionBuild() {" + "\r\n" + 
"    this.basic = !this.basic;" + "\r\n" + 
"    //this.basic = true;" + "\r\n" + 
"    console.log(\"button status: \", this.basic);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToExt() {" + "\r\n" + 
"    this.router.navigate(['extension/all'], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"" 
);

	File sboladdtsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/addlinedepart/" + sboladdts);
			System.out.println("Directory name = " + sboladdtsFile);
			File sboladdtsFileParentDir = new File(sboladdtsFile.getParent());
			if(!sboladdtsFileParentDir.exists()) {
				sboladdtsFileParentDir.mkdirs();
			}
			if (!sboladdtsFile.exists()) {
				sboladdtsFile.createNewFile();
			}
			fw = new FileWriter(sboladdtsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sboladdtsCode.toString());
			bw.close();

 StringBuilder sbolallhtmlCode = new StringBuilder();
 sbolallhtmlCode.append("<div class=\"grid-pg  pad-16\">" + "\r\n" + 
"    <h3>GRID VIEW</h3>" + "\r\n" + 
"    <button id=\"add\" class=\"btn btn-primary\" (click)=\"goToAdd()\">" + "\r\n" + 
"" + "\r\n" + 
"        <clr-icon shape=\"plus\"></clr-icon>ADD" + "\r\n" + 
"    </button>" + "\r\n" + 
"    <!-- <a ><img height=\"16px\" width=\"16px\" src=\"assets/images/who.png\"/><clr-icon shape=\"help\"></clr-icon></a> -->" + "\r\n" + 
"    <!-- <a id=\"who_column\" (click)=\"goToWhoColumns()\"><clr-icon shape=\"help\"  class=\"is-solid\" size=\"16\"></clr-icon></a> -->" + "\r\n" + 
"" + "\r\n" + 
"    <!-- who column start -->" + "\r\n" + 
"" + "\r\n" + 
"    <!-- who column end -->" + "\r\n" + 
"" + "\r\n" + 
"    <br>" + "\r\n" + 
"    <div class=\"row\">" + "\r\n" + 
"        <div class=\"col-lg-12\">" + "\r\n" + 
"            <div style=\"width:1140px\">" + "\r\n" + 
"                <div class=\"s-info-bar\">" + "\r\n" + 
"                    <!-- FILTER BUTTON-->" + "\r\n" + 
"                    <input type=\"text\" style=\"padding:8px;margin:15px auto;width:30%;\"" + "\r\n" + 
"                        placeholder=\"Type to filter the name column...\" (keyup)=\"doFilter($event)\" />" + "\r\n" + 
"                    <div style=\"flex:1\"></div>" + "\r\n" + 
"                    <div *ngIf=\"isLoading\" class=\"spinner spinner-md\" style=\"margin:0 10px;\">Loading...</div>" + "\r\n" + 
"                </div>" + "\r\n" + 
"                <ngx-datatable #table style='height:450px; width:100%;' [rows]=\"rows\" [columnMode]=\"'force'\"" + "\r\n" + 
"                    [columnMode]=\"'standard'\" [headerHeight]=\"45\" [footerHeight]=\"45\" [rowHeight]=\"36\"" + "\r\n" + 
"                    [scrollbarV]=\"true\">" + "\r\n" + 
"                    <ngx-datatable-column name=\"Actions\" sortable=\"false\">" + "\r\n" + 
"                        <!-- //readonly action -->" + "\r\n" + 
"                        <ng-template let-row=\"row\" let-value=\"value\" ngx-datatable-cell-template>" + "\r\n" + 
"                            <a (click)=\"goToReadOnly(row.did)\">" + "\r\n" + 
"                                <clr-icon shape=\"eye\" class=\"is-info\"></clr-icon>" + "\r\n" + 
"                            </a>" + "\r\n" + 
"                            <!-- uodate action -->" + "\r\n" + 
"                            <a (click)=\"goToEdit(row.did)\">" + "\r\n" + 
"                                <clr-icon shape=\"edit\" class=\"is-error\"></clr-icon>" + "\r\n" + 
"                            </a>" + "\r\n" + 
"                        </ng-template>" + "\r\n" + 
"                    </ngx-datatable-column>" + "\r\n" + 
"" + "\r\n" + 
"                    <ngx-datatable-column *ngFor=\"let col of columns\" [name]=\"col.name\" [prop]=\"col.prop\"" + "\r\n" + 
"                        [width]=\"col.width\">" + "\r\n" + 
"                    </ngx-datatable-column>" + "\r\n" + 
"" + "\r\n" + 
"                    " + "\r\n" + 
"                        <!-- EXTENSION -->" + "\r\n" + 
"                        <teacher-grid-extension></teacher-grid-extension>" + "\r\n" + 
"                        <!-- EXTENSION -->" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"                </ngx-datatable>" + "\r\n" + 
"            </div>" + "\r\n" + 
"        </div>" + "\r\n" + 
"    </div>" + "\r\n" + 
"</div>" 
);

	File sbolallhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/alldepart/" + sbolallhtml);
			System.out.println("Directory name = " + sbolallhtmlFile);
			File sbolallhtmlFileParentDir = new File(sbolallhtmlFile.getParent());
			if(!sbolallhtmlFileParentDir.exists()) {
				sbolallhtmlFileParentDir.mkdirs();
			}
			if (!sbolallhtmlFile.exists()) {
				sbolallhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbolallhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolallhtmlCode.toString());
			bw.close();

 StringBuilder sbolallscssCode = new StringBuilder();
 sbolallscssCode.append("//@import '../../../../../assets/scss/var';" + "\r\n" + 
".s-info-bar {" + "\r\n" + 
"    display: flex;" + "\r\n" + 
"    flex-direction: row;" + "\r\n" + 
"    justify-content: space-between;" + "\r\n" + 
"    button {" + "\r\n" + 
"        outline: none;" + "\r\n" + 
"    }" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
".grid-pg {" + "\r\n" + 
"    width:750px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"#add {" + "\r\n" + 
"    position:absolute;" + "\r\n" + 
"    //position: fixed !important;" + "\r\n" + 
"    transition: .5s ease;" + "\r\n" + 
"    top: 16%; " + "\r\n" + 
"    left: 79%; " + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"#who_column {" + "\r\n" + 
"    position:absolute;" + "\r\n" + 
"    //position: fixed !important;" + "\r\n" + 
"    transition: .5s ease;" + "\r\n" + 
"    //top: 16%; // 28 down // barale niche nambe" + "\r\n" + 
"    //left: 13%; // komale left e jabe" + "\r\n" + 
"    top: 97px; // barale niche" + "\r\n" + 
"    left: 145px; // barale dan" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbolallscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/alldepart/" + sbolallscss);
			System.out.println("Directory name = " + sbolallscssFile);
			File sbolallscssFileParentDir = new File(sbolallscssFile.getParent());
			if(!sbolallscssFileParentDir.exists()) {
				sbolallscssFileParentDir.mkdirs();
			}
			if (!sbolallscssFile.exists()) {
				sbolallscssFile.createNewFile();
			}
			fw = new FileWriter(sbolallscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolallscssCode.toString());
			bw.close();

 StringBuilder sbolalltsCode = new StringBuilder();
 sbolalltsCode.append("import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DatatableComponent } from '@swimlane/ngx-datatable';" + "\r\n" + 
"" + "\r\n" + 
"import { DepartmentService } from 'src/app/services/api/department.service';" + "\r\n" + 
"import { ExtensionService } from 'src/app/services/api/extension.service';" + "\r\n" + 
"import { Department } from '../departmentmodel';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-alldepart'," + "\r\n" + 
 " templateUrl: './"+sbolallts1+".html',"
 
+
 "  styleUrls: ['./"+sbolallts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbolallts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  @ViewChild(\"instructorById\") instructorById: TemplateRef<any>;" + "\r\n" + 
"  @ViewChild(\"txId\") txId: TemplateRef<any>;" + "\r\n" + 
"  @ViewChild(DatatableComponent) table: DatatableComponent;" + "\r\n" + 
"" + "\r\n" + 
"  " + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"  columns: any[];" + "\r\n" + 
"  rows: any[];" + "\r\n" + 
"  temp = [];" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  extention: any" + "\r\n" + 
"  fieldname: any" + "\r\n" + 
"  mapping: any" + "\r\n" + 
"" + "\r\n" + 
"  filterData: string;" + "\r\n" + 
"  isLoading: boolean = false;" + "\r\n" + 
"  Department:Department[];" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private router: Router," + "\r\n" + 
"    private route: ActivatedRoute," + "\r\n" + 
"    private deptserv:DepartmentService," + "\r\n" + 
"    private extentionservice: ExtensionService" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"" + "\r\n" + 
"    this.getData();" + "\r\n" + 
"    this.columns = [" + "\r\n" + 
"      { prop: \"did\", name: \"id\", width: 65 }," + "\r\n" + 
"      { prop: \"dname\", name: \" Name\", width: 105 }," + "\r\n" + 
"      { prop: \"dhead\", name: \"head\", width: 150 }," + "\r\n" + 
"      { prop: \"dcontact\", name: \" contact\", width: 190 }," + "\r\n" + 
"      { prop: \"no_ofEmp\", name: \"noofEmp\", width: 190 }," + "\r\n" + 
"    ];" + "\r\n" + 
"" + "\r\n" + 
"    //adding extentions to array if its true" + "\r\n" + 
"    this.extentionservice.getAll().subscribe(ext => {" + "\r\n" + 
"      console.warn(`total extentions    :`, ext)" + "\r\n" + 
"      //   let id=ext.id" + "\r\n" + 
"      // console.log(\"for loop id \", id);" + "\r\n" + 
"      this.extention = ext" + "\r\n" + 
"      for (let id of this.extention) {" + "\r\n" + 
"        if (id.isActive == true) {" + "\r\n" + 
"          console.warn(id);" + "\r\n" + 
"          this.fieldname = id.field_name" + "\r\n" + 
"          this.mapping = id.mapping" + "\r\n" + 
"          console.warn(this.fieldname, this.mapping)," + "\r\n" + 
"            this.columns.push(" + "\r\n" + 
"              { prop: this.mapping, name: this.fieldname, width: 90 })" + "\r\n" + 
"      }" + "\r\n" + 
"      }" + "\r\n" + 
"    })  " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  getData() {" + "\r\n" + 
"    this.isLoading = true;" + "\r\n" + 
"    this.deptserv.getAll().subscribe((data) => {" + "\r\n" + 
"      console.log(`calling getall service`);" + "\r\n" + 
"" + "\r\n" + 
"      this.isLoading = false;" + "\r\n" + 
"      console.log(data);" + "\r\n" + 
"     console.log(data.department);" + "\r\n" + 
"     " + "\r\n" + 
"      this.Department = data.department" + "\r\n" + 
"      this.temp = [...this.Department];" + "\r\n" + 
"      this.rows = this.Department;" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToAdd() {" + "\r\n" + 
"    this.router.navigate([\"../adddept\"], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToEdit(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../editdep/\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToReadOnly(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../readdep\", id],{ relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"}" 
);

	File sbolalltsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/alldepart/" + sbolallts);
			System.out.println("Directory name = " + sbolalltsFile);
			File sbolalltsFileParentDir = new File(sbolalltsFile.getParent());
			if(!sbolalltsFileParentDir.exists()) {
				sbolalltsFileParentDir.mkdirs();
			}
			if (!sbolalltsFile.exists()) {
				sbolalltsFile.createNewFile();
			}
			fw = new FileWriter(sbolalltsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolalltsCode.toString());
			bw.close();

 StringBuilder sboledithtmlCode = new StringBuilder();
 sboledithtmlCode.append("<div class=\"container\">" + "\r\n" + 
"    <div class=\"section\">" + "\r\n" + 
"        <p> Edit form </p>" + "\r\n" + 
"    </div>" + "\r\n" + 
"    <br />" + "\r\n" + 
"    <form (ngSubmit)=\"onSubmit()\">" + "\r\n" + 
"        <div class=\"clr-row\">" + "\r\n" + 
"            <div class=\"clr-col-md-4 clr-col-sm-12\">" + "\r\n" + 
"                <label >dname</label>" + "\r\n" + 
"                <input type=\"text\" [(ngModel)]=\"depart.dname\" id=\"main_menu_name\" name=\"dname\"  placeholder=\"Enter Name\" class=\"clr-input\">" + "\r\n" + 
"                    <br>" + "\r\n" + 
"            </div>" + "\r\n" + 
"            <div class=\"clr-col-md-4 clr-col-sm-12\">" + "\r\n" + 
"                <label >Dhead</label>" + "\r\n" + 
"                <input type=\"text\" id=\"main_menu_action_name\" name=\"dhead\"  class=\"clr-input\" [(ngModel)]=\"depart.dhead\"" + "\r\n" + 
"                    placeholder=\"Enter \" class=\"clr-input\">" + "\r\n" + 
"                    <br>" + "\r\n" + 
"            </div>" + "\r\n" + 
"" + "\r\n" + 
"            <div class=\"clr-col-md-4 clr-col-sm-12\">" + "\r\n" + 
"                <label >Contact no</label>" + "\r\n" + 
"                <input class=\"clr-input\" id=\"main_menu_icon\" type=\"number\"  class=\"clr-input\" name=\"dcontact\" [(ngModel)]=\"depart.dcontact\" >" + "\r\n" + 
"                <br>" + "\r\n" + 
"            </div>" + "\r\n" + 
"                <br>" + "\r\n" + 
"            <div class=\"clr-col-md-4 clr-col-sm-12\">" + "\r\n" + 
"                <label >Employees </label>" + "\r\n" + 
"                <input id=\"main_menu_icon\" type=\"number\" name=\"no_ofEmp\"  class=\"clr-input\" placeholder=\"Enter icon\"" + "\r\n" + 
"                    [(ngModel)]=\"depart.no_ofEmp\" class=\"clr-input\">" + "\r\n" + 
"                    <br>" + "\r\n" + 
"            </div>" + "\r\n" + 
"        </div>" + "\r\n" + 
"        <br>" + "\r\n" + 
"        <div class=\"clr-row\">" + "\r\n" + 
"            " + "\r\n" + 
"            <teacher-edit-extension [(teacherExtension)]=\"depart\"></teacher-edit-extension>" + "\r\n" + 
"        </div>" + "\r\n" + 
"        <button type=\"submit\" form-control class=\"btn btn-primary\">UPDATE</button>" + "\r\n" + 
"    </form>" + "\r\n" + 
"</div>" 
);

	File sboledithtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/editdepart/" + sboledithtml);
			System.out.println("Directory name = " + sboledithtmlFile);
			File sboledithtmlFileParentDir = new File(sboledithtmlFile.getParent());
			if(!sboledithtmlFileParentDir.exists()) {
				sboledithtmlFileParentDir.mkdirs();
			}
			if (!sboledithtmlFile.exists()) {
				sboledithtmlFile.createNewFile();
			}
			fw = new FileWriter(sboledithtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sboledithtmlCode.toString());
			bw.close();

 StringBuilder sboleditscssCode = new StringBuilder();
 sboleditscssCode.append(".clr-input {" + "\r\n" + 
"    color: #212529;" + "\r\n" + 
"    border: 1px solid #ced4da;" + "\r\n" + 
"    border-radius: 0.25rem;" + "\r\n" + 
"    padding: 0.75rem 0.75rem;" + "\r\n" + 
"    margin-top: 3px;" + "\r\n" + 
"    width: 100%;" + "\r\n" + 
"    margin-bottom: 10px;" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
"  .center {" + "\r\n" + 
"    text-align: center;" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
"  $bg-color: #dddddd;" + "\r\n" + 
"  .section {" + "\r\n" + 
"    background-color: $bg-color;" + "\r\n" + 
"    height: 40px;" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
"  .section p {" + "\r\n" + 
"    //color: white;" + "\r\n" + 
"    padding: 10px;" + "\r\n" + 
"    font-size: 18px;" + "\r\n" + 
"  }" 
);

	File sboleditscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/editdepart/" + sboleditscss);
			System.out.println("Directory name = " + sboleditscssFile);
			File sboleditscssFileParentDir = new File(sboleditscssFile.getParent());
			if(!sboleditscssFileParentDir.exists()) {
				sboleditscssFileParentDir.mkdirs();
			}
			if (!sboleditscssFile.exists()) {
				sboleditscssFile.createNewFile();
			}
			fw = new FileWriter(sboleditscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sboleditscssCode.toString());
			bw.close();

 StringBuilder sboledittsCode = new StringBuilder();
 sboledittsCode.append("import { HttpErrorResponse } from '@angular/common/http';" + "\r\n" + 
"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DepartmentService } from 'src/app/services/api/department.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-editdepart'," + "\r\n" + 
 " templateUrl: './"+sboleditts1+".html',"
 
+
 "  styleUrls: ['./"+sboleditts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sboleditts2+"Component implements OnInit {"
+
"  updated = false;" + "\r\n" + 
"  depart:any=[]" + "\r\n" + 
"  did: number;" + "\r\n" + 
"  " + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private router: Router," + "\r\n" + 
"    private route:ActivatedRoute," + "\r\n" + 
"    private departserv:DepartmentService" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.depart;" + "\r\n" + 
"    this.did = this.route.snapshot.params[\"did\"];" + "\r\n" + 
"    console.warn(\"my id\"+this.did);" + "\r\n" + 
"    " + "\r\n" + 
"    console.log(\"update with id = \", this.did);" + "\r\n" + 
"    this.getById(this.did);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  getById(id) {" + "\r\n" + 
"    this.departserv.getDataById(id).subscribe((data) => {" + "\r\n" + 
"      this.depart = data;" + "\r\n" + 
"      console.warn(\"getby data   \" +this.depart);" + "\r\n" + 
"      " + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  update() {" + "\r\n" + 
"    this.departserv.update(this.did,this.depart).subscribe(" + "\r\n" + 
"      (data) => {" + "\r\n" + 
"        console.log(data);" + "\r\n" + 
"        this.router.navigate([\"/home/department/\"]);" + "\r\n" + 
"      }," + "\r\n" + 
"      (error: HttpErrorResponse) => {" + "\r\n" + 
"        console.log(error.message);" + "\r\n" + 
"      }" + "\r\n" + 
"    );" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"    this.updated = true;" + "\r\n" + 
"    this.update();" + "\r\n" + 
"  }" + "\r\n" + 
"}" 
);

	File sboledittsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/editdepart/" + sboleditts);
			System.out.println("Directory name = " + sboledittsFile);
			File sboledittsFileParentDir = new File(sboledittsFile.getParent());
			if(!sboledittsFileParentDir.exists()) {
				sboledittsFileParentDir.mkdirs();
			}
			if (!sboledittsFile.exists()) {
				sboledittsFile.createNewFile();
			}
			fw = new FileWriter(sboledittsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sboledittsCode.toString());
			bw.close();

 StringBuilder sbolreadonlyhtmlCode = new StringBuilder();
 sbolreadonlyhtmlCode.append("<div class=\"read-only-pg pad-16\">" + "\r\n" + 
"    <h3>READ ONLY FORM" + "\r\n" + 
"        <a (click)=\"goToWhoColumns()\">" + "\r\n" + 
"            <clr-icon shape=\"help\"  class=\"is-solid\" size=\"32\"></clr-icon>" + "\r\n" + 
"        </a>" + "\r\n" + 
"    </h3>" + "\r\n" + 
"    <br />" + "\r\n" + 
"        <!-- whoo column -->" + "\r\n" + 
"    <clr-modal [(clrModalOpen)]=\"basic\">" + "\r\n" + 
"        <h3 class=\"modal-title\"><b>Transaction History</b></h3>" + "\r\n" + 
"        <div class=\"modal-body\">" + "\r\n" + 
"            <table s-header>" + "\r\n" + 
"                <tr>" + "\r\n" + 
"                    <td>AccountId</td>" + "\r\n" + 
"                    <td> {{department.accountId}}</td>" + "\r\n" + 
"                </tr>" + "\r\n" + 
"                <tr>" + "\r\n" + 
"                    <td>CreatedAt </td>" + "\r\n" + 
"                    <td> {{department.createdAt}} </td>" + "\r\n" + 
"                </tr>" + "\r\n" + 
"                <tr>" + "\r\n" + 
"                    <td>CreatedBy </td>" + "\r\n" + 
"                    <td> {{department.createdBy}} </td>" + "\r\n" + 
"                </tr>" + "\r\n" + 
"                <tr>" + "\r\n" + 
"                    <td>UpdatedAt </td>" + "\r\n" + 
"                    <td> {{department.updatedAt}} </td>" + "\r\n" + 
"                </tr>" + "\r\n" + 
"                <tr>" + "\r\n" + 
"                    <td>UpdatedBy </td>" + "\r\n" + 
"                    <td> {{department.updatedBy}} </td>" + "\r\n" + 
"                </tr>" + "\r\n" + 
"            </table>" + "\r\n" + 
"        " + "\r\n" + 
"        </div>" + "\r\n" + 
"    </clr-modal>" + "\r\n" + 
"   " + "\r\n" + 
"" + "\r\n" + 
"        <!-- fields start -->" + "\r\n" + 
"" + "\r\n" + 
"    <br />" + "\r\n" + 
"    <table class=\"s-header\">" + "\r\n" + 
"        <tr>" + "\r\n" + 
"            <td style=\"width:125px\">D_Id</td>" + "\r\n" + 
"            <td> {{department.did}}</td>" + "\r\n" + 
"        </tr>" + "\r\n" + 
"        <tr>" + "\r\n" + 
"            <td>D_Name </td>" + "\r\n" + 
"            <td> {{department.dname}} </td>" + "\r\n" + 
"        </tr>" + "\r\n" + 
"        <tr>" + "\r\n" + 
"            <td>D_head </td>" + "\r\n" + 
"            <td> {{department.dhead}} </td>" + "\r\n" + 
"        </tr>" + "\r\n" + 
"        <tr>" + "\r\n" + 
"            <td>D_contact </td>" + "\r\n" + 
"            <td> {{department.dcontact }} </td>" + "\r\n" + 
"        </tr>" + "\r\n" + 
"        <tr>" + "\r\n" + 
"            <td>No.of_Emp </td>" + "\r\n" + 
"            <td> {{department.no_ofEmp}} </td>" + "\r\n" + 
"        </tr>" + "\r\n" + 
"" + "\r\n" + 
"    </table>" + "\r\n" + 
"" + "\r\n" + 
"        <!-- EXTENSION FIELDS START -->" + "\r\n" + 
"     <teacher-readonly-extension [teacherExtension]=\"department\"></teacher-readonly-extension>" + "\r\n" + 
"     <!-- EXTENSION FIELDS END-->" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    <br />" + "\r\n" + 
"    <button (click)=\"back()\" class=\"btn btn-primary\">" + "\r\n" + 
"        <clr-icon shape=\"caret left\"></clr-icon>Back" + "\r\n" + 
"    </button>" + "\r\n" + 
"   </div>" 
);

	File sbolreadonlyhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/readonlydepart/" + sbolreadonlyhtml);
			System.out.println("Directory name = " + sbolreadonlyhtmlFile);
			File sbolreadonlyhtmlFileParentDir = new File(sbolreadonlyhtmlFile.getParent());
			if(!sbolreadonlyhtmlFileParentDir.exists()) {
				sbolreadonlyhtmlFileParentDir.mkdirs();
			}
			if (!sbolreadonlyhtmlFile.exists()) {
				sbolreadonlyhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbolreadonlyhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolreadonlyhtmlCode.toString());
			bw.close();

 StringBuilder sbolreadonlyscssCode = new StringBuilder();
 sbolreadonlyscssCode.append("");

	File sbolreadonlyscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/readonlydepart/" + sbolreadonlyscss);
			System.out.println("Directory name = " + sbolreadonlyscssFile);
			File sbolreadonlyscssFileParentDir = new File(sbolreadonlyscssFile.getParent());
			if(!sbolreadonlyscssFileParentDir.exists()) {
				sbolreadonlyscssFileParentDir.mkdirs();
			}
			if (!sbolreadonlyscssFile.exists()) {
				sbolreadonlyscssFile.createNewFile();
			}
			fw = new FileWriter(sbolreadonlyscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolreadonlyscssCode.toString());
			bw.close();

 StringBuilder sbolreadonlytsCode = new StringBuilder();
 sbolreadonlytsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DepartmentService } from 'src/app/services/api/department.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-readonlydepart'," + "\r\n" + 
 " templateUrl: './"+sbolreadonlyts1+".html',"
 
+
 "  styleUrls: ['./"+sbolreadonlyts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbolreadonlyts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"  department;" + "\r\n" + 
"  header_id:number;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, private service: DepartmentService, private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"" + "\r\n" + 
"    console.log(\"in the getdept \");" + "\r\n" + 
"    " + "\r\n" + 
"    this.header_id = this.route.snapshot.params['did'];" + "\r\n" + 
"    this.service.getDataById(this.header_id).subscribe(data => {" + "\r\n" + 
"      this.department = data;" + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToWhoColumns() {" + "\r\n" + 
"    this.basic = !this.basic;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"  back() {" + "\r\n" + 
"    this.routing.navigate(['home/department/']);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbolreadonlytsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/readonlydepart/" + sbolreadonlyts);
			System.out.println("Directory name = " + sbolreadonlytsFile);
			File sbolreadonlytsFileParentDir = new File(sbolreadonlytsFile.getParent());
			if(!sbolreadonlytsFileParentDir.exists()) {
				sbolreadonlytsFileParentDir.mkdirs();
			}
			if (!sbolreadonlytsFile.exists()) {
				sbolreadonlytsFile.createNewFile();
			}
			fw = new FileWriter(sbolreadonlytsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolreadonlytsCode.toString());
			bw.close();

 StringBuilder sbolnewhtmlCode = new StringBuilder();
 sbolnewhtmlCode.append("<router-outlet></router-outlet>" 
);

	File sbolnewhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/" + sbolnewhtml);
			System.out.println("Directory name = " + sbolnewhtmlFile);
			File sbolnewhtmlFileParentDir = new File(sbolnewhtmlFile.getParent());
			if(!sbolnewhtmlFileParentDir.exists()) {
				sbolnewhtmlFileParentDir.mkdirs();
			}
			if (!sbolnewhtmlFile.exists()) {
				sbolnewhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbolnewhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolnewhtmlCode.toString());
			bw.close();

 StringBuilder sbolnewscssCode = new StringBuilder();
 sbolnewscssCode.append("");

	File sbolnewscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/" + sbolnewscss);
			System.out.println("Directory name = " + sbolnewscssFile);
			File sbolnewscssFileParentDir = new File(sbolnewscssFile.getParent());
			if(!sbolnewscssFileParentDir.exists()) {
				sbolnewscssFileParentDir.mkdirs();
			}
			if (!sbolnewscssFile.exists()) {
				sbolnewscssFile.createNewFile();
			}
			fw = new FileWriter(sbolnewscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolnewscssCode.toString());
			bw.close();

 StringBuilder sboltsCode = new StringBuilder();
 sboltsCode.append("import { Audit } from \"src/app/models/Audit\";" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"export class "+sbolts2+"Component implements OnInit {"
+
"    public Did:number;" + "\r\n" + 
"    public Dname:String;" + "\r\n" + 
"    public  Dhead:String;" + "\r\n" + 
"    public Dcontact:number;" + "\r\n" + 
"    public No_ofEmp:number;" + "\r\n" + 
"" + "\r\n" + 
"   " + "\r\n" + 
"   " + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sboltsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/" + sbolts);
			System.out.println("Directory name = " + sboltsFile);
			File sboltsFileParentDir = new File(sboltsFile.getParent());
			if(!sboltsFileParentDir.exists()) {
				sboltsFileParentDir.mkdirs();
			}
			if (!sboltsFile.exists()) {
				sboltsFile.createNewFile();
			}
			fw = new FileWriter(sboltsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sboltsCode.toString());
			bw.close();

 StringBuilder sbolnewtsCode = new StringBuilder();
 sbolnewtsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-department-new'," + "\r\n" + 
 " templateUrl: './"+sbolnewts1+".html',"
 
+
 "  styleUrls: ['./"+sbolnewts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbolnewts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  constructor() { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbolnewtsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/departmentnew/" + sbolnewts);
			System.out.println("Directory name = " + sbolnewtsFile);
			File sbolnewtsFileParentDir = new File(sbolnewtsFile.getParent());
			if(!sbolnewtsFileParentDir.exists()) {
				sbolnewtsFileParentDir.mkdirs();
			}
			if (!sbolnewtsFile.exists()) {
				sbolnewtsFile.createNewFile();
			}
			fw = new FileWriter(sbolnewtsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbolnewtsCode.toString());
			bw.close();



/*-----------------------UPDATE ADMIN ROUTING TS FILE --------------------*/
		//	String frontEndDir = angularProjectPath.concat("/frontend/");
			File adminRoutingModule = new File(projectPath+"/Projects/"+project_name+ "/webui/src/app/app-routing.module.ts");
			File tempRoutingModule = new File(projectPath+"/Projects/"+project_name+"/webui/src/app/temp-routing.module.ts");

			BufferedReader reader = new BufferedReader(new FileReader(adminRoutingModule));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempRoutingModule));
			String removeStr = "]}];@NgModule({imports: [RouterModule.forChild(routes)],exports: [RouterModule]})export class AdminRoutingModule{}";
			String currentLine;
			System.out.println(adminRoutingModule.getName());
			while ((currentLine = reader.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equals(removeStr)) {
					currentLine = "";
				}
				writer.write(currentLine + System.getProperty("line.separator"));

			}
			writer.close();
			reader.close();
			boolean delete = adminRoutingModule.delete();
			boolean b22 = tempRoutingModule.renameTo(adminRoutingModule);

			StringBuilder admin_routing_module_string = new StringBuilder();
			admin_routing_module_string.append(" ");
			String adminRoutingModuleName = projectPath+"/Projects/"+project_name+ "/webui/src/app/app-routing.module.ts";

			fw = new FileWriter(adminRoutingModuleName, true);
			fw.write(admin_routing_module_string.toString());
			fw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ErrorPojo errorPojo = new ErrorPojo();
			Error error = new Error();
			error.setTitle(Constant.FORM_BUILDER_API_TITLE);
			error.setMessage(Constant.FORM_BUILD_FAILURE);
			errorPojo.setError(error);
			return new ResponseEntity<ErrorPojo>(errorPojo, HttpStatus.EXPECTATION_FAILED);
		}
		SuccessPojo successPojo = new SuccessPojo();
		Success success = new Success();
		success.setTitle(Constant.FORM_BUILDER_API_TITLE);
		success.setMessage(Constant.FORM_BUILD_SUCCESS);
		successPojo.setSuccess(success);
		return new ResponseEntity<SuccessPojo>(successPojo, HttpStatus.OK);


}}