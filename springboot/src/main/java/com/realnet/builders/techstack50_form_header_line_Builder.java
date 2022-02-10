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
public class techstack50_form_header_line_Builder {


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
	@GetMapping(value = "/techstack50_form_header_line_Builder")
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

String sbhlcontroller = ui_name +"authorcontroller.java";
String  mainstr0 = sbhlcontroller;
String sbhlcontroller1=mainstr0.replace(".java", "");


String sbhlentityheader = ui_name +"author.java";
String  mainstr1 = sbhlentityheader;
String sbhlentityheader1=mainstr1.replace(".java", "");


String sbhlentityline = ui_name +"book.java";
String  mainstr2 = sbhlentityline;
String sbhlentityline1=mainstr2.replace(".java", "");


String sbhlrepository = ui_name +"authorrepo.java";
String  mainstr3 = sbhlrepository;
String sbhlrepository1=mainstr3.replace(".java", "");


String sbhlresponse = ui_name +"authorres.java";
String  mainstr4 = sbhlresponse;
String sbhlresponse1=mainstr4.replace(".java", "");


String sbhlservice = ui_name +"authorservice.java";
String  mainstr5 = sbhlservice;
String sbhlservice1=mainstr5.replace(".java", "");


String sbhlserviceimpl = ui_name +"authorserviceimpl.java";
String  mainstr6 = sbhlserviceimpl;
String sbhlserviceimpl1=mainstr6.replace(".java", "");


String sbhladdhtml = ui_name +"addsales.component.html";
String  mainstr7 = sbhladdhtml;
String sbhladdhtml1=mainstr7.replace(".html", "");


String sbhladdscss = ui_name +"addsales.component.scss";
String  mainstr8 = sbhladdscss;
String sbhladdscss1=mainstr8.replace(".scss", "");


String sbhladdts = ui_name +"addsales.component.ts";
String  mainstr9 = sbhladdts;
String sbhladdts1=mainstr9.replace(".ts", "");

String sbhladdts2=mainstr9.replace(".component.ts", "");


String sbohallhtml = ui_name +"all.component.html";
String  mainstr10 = sbohallhtml;
String sbohallhtml1=mainstr10.replace(".html", "");


String sbhlallscss = ui_name +"all.component.scss";
String  mainstr11 = sbhlallscss;
String sbhlallscss1=mainstr11.replace(".scss", "");


String sbhlallts = ui_name +"allsales.component.ts";
String  mainstr12 = sbhlallts;
String sbhlallts1=mainstr12.replace(".ts", "");

String sbhlallts2=mainstr12.replace(".component.ts", "");


String sbhledithtml = ui_name +"edit.component.html";
String  mainstr13 = sbhledithtml;
String sbhledithtml1=mainstr13.replace(".html", "");


String sbhleditscss = ui_name +"edit.component.scss";
String  mainstr14 = sbhleditscss;
String sbhleditscss1=mainstr14.replace(".scss", "");


String sbhleditts = ui_name +"editsales.component.ts";
String  mainstr15 = sbhleditts;
String sbhleditts1=mainstr15.replace(".ts", "");

String sbhleditts2=mainstr15.replace(".component.ts", "");


String sbhlreadonlyhtml = ui_name +"readonlynew.component.html";
String  mainstr16 = sbhlreadonlyhtml;
String sbhlreadonlyhtml1=mainstr16.replace(".html", "");


String sbhlreadonlyscss = ui_name +"readonlynew.component.scss";
String  mainstr17 = sbhlreadonlyscss;
String sbhlreadonlyscss1=mainstr17.replace(".scss", "");


String sbhlreadonlyts = ui_name +"readonlynew.component.ts";
String  mainstr18 = sbhlreadonlyts;
String sbhlreadonlyts1=mainstr18.replace(".ts", "");

String sbhlreadonlyts2=mainstr18.replace(".component.ts", "");


String sbhlhtml = ui_name +"salesnew.component.html";
String  mainstr19 = sbhlhtml;
String sbhlhtml1=mainstr19.replace(".html", "");


String sbhlscss = ui_name +"salesnew.component.scss";
String  mainstr20 = sbhlscss;
String sbhlscss1=mainstr20.replace(".scss", "");


String sbhlts = ui_name +"salesnew.component.ts";
String  mainstr21 = sbhlts;
String sbhlts1=mainstr21.replace(".ts", "");

String sbhlts2=mainstr21.replace(".component.ts", "");



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
 StringBuilder sbhlcontrollerCode = new StringBuilder();
 sbhlcontrollerCode.append("package com.realnet.Module.salesnew.controller;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import java.util.HashMap;" + "\r\n" + 
		 "import java.util.Map;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import javax.validation.Valid;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity.*;\r\n"
		 + "import com.realnet.Module.salesnew.repository.*;\r\n"
		 + "import com.realnet.Module.salesnew.responce.*;\r\n"
		 + "import com.realnet.Module.salesnew.service.*;"+ "\r\n" + 
		 "import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
		 "import org.springframework.data.domain.Page;" + "\r\n" + 
		 "import org.springframework.data.domain.PageRequest;" + "\r\n" + 
		 "import org.springframework.data.domain.Pageable;" + "\r\n" + 
		 "import org.springframework.http.HttpHeaders;" + "\r\n" + 
		 "import org.springframework.http.HttpStatus;" + "\r\n" + 
		 "import org.springframework.http.ResponseEntity;" + "\r\n" + 
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
		 " " + "\r\n" + 
		 "import io.swagger.annotations.Api;" + "\r\n" + 
		 "import io.swagger.annotations.ApiOperation;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@RequestMapping(value = \"/api\", produces = MediaType.APPLICATION_JSON_VALUE)" + "\r\n" + 
"@Api(tags = { \"Teacher\" })" + "\r\n" + 
"public class " + sbhlcontroller1 + "{"+
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private "+sbhlservice1+" authorservice;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	// GET ALL" + "\r\n" + 
"	@ApiOperation(value = \"List of Teachers\", response = "+sbhlresponse1+".class)" + "\r\n" + 
"	@GetMapping(\"/author\")" + "\r\n" + 
"	public "+sbhlresponse1+" getTeachers(@RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer page," + "\r\n" + 
"			@RequestParam(value = \"size\", defaultValue = \"20\", required = false) Integer size) {" + "\r\n" + 
"		"+sbhlresponse1+" resp = new "+sbhlresponse1+"();" + "\r\n" + 
"		Pageable paging = PageRequest.of(page, size);" + "\r\n" + 
"		Page<"+sbhlentityheader1+"> result = authorservice.getAll(paging);" + "\r\n" + 
"		resp.setPageStats(result, true);" + "\r\n" + 
"		resp.setAuthor(result.getContent());" + "\r\n" + 
"		return resp;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// GET BY ID" + "\r\n" + 
"	@ApiOperation(value = \"Get a teacher\", response = "+sbhlentityheader1+".class)" + "\r\n" + 
"	@GetMapping(\"/author/{id}\")" + "\r\n" + 
"	public ResponseEntity<"+sbhlentityheader1+"> getTeacherById(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		"+sbhlentityheader1+" teacher = authorservice.getById(id);" + "\r\n" + 
"		if (teacher == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"teacher not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.ok().body(teacher);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// SAVE" + "\r\n" + 
"	@ApiOperation(value = \"Add new teacher\", response = "+sbhlentityheader1+".class)" + "\r\n" + 
"	@PostMapping(\"/author\")" + "\r\n" + 
"	public ResponseEntity<"+sbhlentityheader1+"> createTeacher(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"			@Valid @RequestBody "+sbhlentityheader1+" teacher) {" + "\r\n" + 
"" + "\r\n" + 
"		"+sbhlentityheader1+" savedTeacher = authorservice.save(teacher);" + "\r\n" + 
"		if (savedTeacher == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"teacher is not saved\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// UPDATE" + "\r\n" + 
"	@ApiOperation(value = \"update a teacher\", response = "+sbhlentityheader1+".class)" + "\r\n" + 
"	@PutMapping(\"/author/{id}\")" + "\r\n" + 
"	public ResponseEntity<"+sbhlentityheader1+"> updateTeacher(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"			@PathVariable(value = \"id\") Integer id, @Valid @RequestBody "+sbhlentityheader1+" teacher) {" + "\r\n" + 
"" + "\r\n" + 
"		"+sbhlentityheader1+" updatedTeacher = authorservice.updateById(id, teacher);" + "\r\n" + 
"		if (updatedTeacher == null || id != updatedTeacher.getId()) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"teacher not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedTeacher);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@DeleteMapping(\"/author/{id}\")" + "\r\n" + 
"	public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		boolean deleted = authorservice.deleteById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		if (deleted) {" + "\r\n" + 
"			response.put(\"deleted\", Boolean.TRUE);" + "\r\n" + 
"			return ResponseEntity.status(HttpStatus.OK).body(response);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbhlcontrollerFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/controller/" + sbhlcontroller);
			System.out.println("Directory name = " + sbhlcontrollerFile);
			File sbhlcontrollerFileParentDir = new File(sbhlcontrollerFile.getParent());
			if(!sbhlcontrollerFileParentDir.exists()) {
				sbhlcontrollerFileParentDir.mkdirs();
			}
			if (!sbhlcontrollerFile.exists()) {
				sbhlcontrollerFile.createNewFile();
			}
			fw = new FileWriter(sbhlcontrollerFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlcontrollerCode.toString());
			bw.close();

 StringBuilder sbhlentityheaderCode = new StringBuilder();
 sbhlentityheaderCode.append("package com.realnet.Module.salesnew.entity;" + "\r\n" + 
		 "" + "\r\n" + 
		 "" + "\r\n" + 
		 "import java.util.Set;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import javax.persistence.CascadeType;" + "\r\n" + 
		 "import javax.persistence.Column;" + "\r\n" + 
		 "import javax.persistence.Entity;" + "\r\n" + 
		 "import javax.persistence.GeneratedValue;" + "\r\n" + 
		 "import javax.persistence.GenerationType;" + "\r\n" + 
		 "import javax.persistence.Id;" + "\r\n" + 
		 "import javax.persistence.OneToMany;" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity.*;"+
		 "" + "\r\n" + 
		 "" + "\r\n" + 
		 "import com.fasterxml.jackson.annotation.JsonManagedReference;" + "\r\n" + 
		 "import com.realnet.fnd.entity.Rn_AuditEntity;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbhlentityheader1 + "  extends Rn_AuditEntity{"+
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.IDENTITY)" + "\r\n" + 
"	@Column(name = \"id\")" + "\r\n" + 
"	private Integer id;" + "\r\n" 
		 );
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ) )
 	 {
 	
 		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
 	 }
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private Date  "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("autocomplete") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("masked") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlentityheaderCode.append("    private long  "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	
  }
 
 sbhlentityheaderCode.append(	
"	" + "\r\n" + 
"	@OneToMany(mappedBy = \"author\", cascade = CascadeType.ALL)" + "\r\n" + 
"	@JsonManagedReference" + "\r\n" + 
"	private List<"+sbhlentityline1+"> book;" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
);

	File sbhlentityheaderFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/entity/" + sbhlentityheader);
			System.out.println("Directory name = " + sbhlentityheaderFile);
			File sbhlentityheaderFileParentDir = new File(sbhlentityheaderFile.getParent());
			if(!sbhlentityheaderFileParentDir.exists()) {
				sbhlentityheaderFileParentDir.mkdirs();
			}
			if (!sbhlentityheaderFile.exists()) {
				sbhlentityheaderFile.createNewFile();
			}
			fw = new FileWriter(sbhlentityheaderFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlentityheaderCode.toString());
			bw.close();

 StringBuilder sbhlentitylineCode = new StringBuilder();
 sbhlentitylineCode.append("package com.realnet.Module.salesnew.entity;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import javax.persistence.Entity;" + "\r\n" + 
		 "import javax.persistence.FetchType;" + "\r\n" + 
		 "import javax.persistence.GeneratedValue;" + "\r\n" + 
		 "import javax.persistence.GenerationType;" + "\r\n" + 
		 "import javax.persistence.Id;" + "\r\n" + 
		 "import javax.persistence.ManyToOne;" + "\r\n" + 
		 "import javax.persistence.Table;" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity.*;"+
		 "" + "\r\n" + 
		 "import com.fasterxml.jackson.annotation.JsonBackReference;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import lombok.Data;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbhlentityline1 + "{"+
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.IDENTITY)" + "\r\n" + 
"	@Column(name = \"id\")" + "\r\n" + 
"	private Integer id;" + "\r\n" + 
"" + "\r\n" );
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line") )
 	 {
 	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
 	 }
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private Date  "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlentitylineCode.append("    private long  "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
	 }
 	
 	
  }
 
 sbhlentitylineCode.append(	
"	" + "\r\n" + 
"	@ManyToOne(fetch = FetchType.LAZY, optional = false)" + "\r\n" + 
"	@JsonBackReference" + "\r\n" + 
"	private "+sbhlentityheader1+" author;" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
);

	File sbhlentitylineFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/entity/" + sbhlentityline);
			System.out.println("Directory name = " + sbhlentitylineFile);
			File sbhlentitylineFileParentDir = new File(sbhlentitylineFile.getParent());
			if(!sbhlentitylineFileParentDir.exists()) {
				sbhlentitylineFileParentDir.mkdirs();
			}
			if (!sbhlentitylineFile.exists()) {
				sbhlentitylineFile.createNewFile();
			}
			fw = new FileWriter(sbhlentitylineFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlentitylineCode.toString());
			bw.close();

 StringBuilder sbhlrepositoryCode = new StringBuilder();
 sbhlrepositoryCode.append("package com.realnet.Module.salesnew.repository;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import org.springframework.data.domain.Page;" + "\r\n" + 
		 "import org.springframework.data.domain.Pageable;" + "\r\n" + 
		 "import org.springframework.data.jpa.repository.JpaRepository;" + "\r\n" + 
		 "import org.springframework.stereotype.Repository;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity.*;" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface HlwSalesrepository extends JpaRepository<"+sbhlentityheader1+", Integer>{"+
"" + "\r\n" + 
"}" 
);

	File sbhlrepositoryFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/repository/" + sbhlrepository);
			System.out.println("Directory name = " + sbhlrepositoryFile);
			File sbhlrepositoryFileParentDir = new File(sbhlrepositoryFile.getParent());
			if(!sbhlrepositoryFileParentDir.exists()) {
				sbhlrepositoryFileParentDir.mkdirs();
			}
			if (!sbhlrepositoryFile.exists()) {
				sbhlrepositoryFile.createNewFile();
			}
			fw = new FileWriter(sbhlrepositoryFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlrepositoryCode.toString());
			bw.close();

 StringBuilder sbhlresponseCode = new StringBuilder();
 sbhlresponseCode.append("package com.realnet.Module.salesnew.responce;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import java.util.List;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity.*;" + "\r\n" + 
		 "import com.realnet.fnd.response.PageResponse;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
		 "import lombok.Data;" + "\r\n" + 
		 "import lombok.EqualsAndHashCode;" + "\r\n" +  
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@EqualsAndHashCode(callSuper=false)" + "\r\n" + 
"public class " + sbhlresponse1 + "  extends PageResponse{"+
"	@ApiModelProperty(required = true, value = \"\")" + "\r\n" + 
"	  private List<"+sbhlentityheader1+"> author;" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbhlresponseFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/responce/" + sbhlresponse);
			System.out.println("Directory name = " + sbhlresponseFile);
			File sbhlresponseFileParentDir = new File(sbhlresponseFile.getParent());
			if(!sbhlresponseFileParentDir.exists()) {
				sbhlresponseFileParentDir.mkdirs();
			}
			if (!sbhlresponseFile.exists()) {
				sbhlresponseFile.createNewFile();
			}
			fw = new FileWriter(sbhlresponseFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlresponseCode.toString());
			bw.close();

 StringBuilder sbhlserviceCode = new StringBuilder();
 sbhlserviceCode.append("package com.realnet.Module.salesnew.service;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity.*;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import org.springframework.data.domain.Page;" + "\r\n" + 
		 "import org.springframework.data.domain.Pageable;" + "\r\n" + 
"" + "\r\n" + 
"public interface " + sbhlservice1 + "{"+
"" + "\r\n" + 
"	public Page<"+sbhlentityheader1+"> getAll(Pageable page);" + "\r\n" + 
"	public "+sbhlentityheader1+" getById(int id);" + "\r\n" + 
"	public "+sbhlentityheader1+" save("+sbhlentityheader1+" teacher);" + "\r\n" + 
"	public "+sbhlentityheader1+" updateById(int id, "+sbhlentityheader1+" teacherRequest);" + "\r\n" + 
"	public boolean deleteById(int id);" + "\r\n" + 
"}" 
);

	File sbhlserviceFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/service/" + sbhlservice);
			System.out.println("Directory name = " + sbhlserviceFile);
			File sbhlserviceFileParentDir = new File(sbhlserviceFile.getParent());
			if(!sbhlserviceFileParentDir.exists()) {
				sbhlserviceFileParentDir.mkdirs();
			}
			if (!sbhlserviceFile.exists()) {
				sbhlserviceFile.createNewFile();
			}
			fw = new FileWriter(sbhlserviceFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlserviceCode.toString());
			bw.close();

 StringBuilder sbhlserviceimplCode = new StringBuilder();
 sbhlserviceimplCode.append("package com.realnet.Module.salesnew.service;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
		 "import org.springframework.data.domain.Page;" + "\r\n" + 
		 "import org.springframework.data.domain.Pageable;" + "\r\n" + 
		 "import org.springframework.stereotype.Service;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity.*;\r\n"
		 + "import com.realnet.Module.salesnew.repository.*;"+ "\r\n" + 
		 "import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class " + sbhlserviceimpl1 + "  implements "+sbhlservice1+"{"+
"" + "\r\n" + 
"	" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private "+sbhlrepository1+" authrepo;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public Page<"+sbhlentityheader1+"> getAll(Pageable page) {" + "\r\n" + 
"		return authrepo.findAll(page);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public "+sbhlentityheader1+" getById(int id) {" + "\r\n" + 
"		" + "\r\n" + 
"		"+sbhlentityheader1+" teacher = authrepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"" + "\r\n" + 
"		return teacher;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"	public "+sbhlentityheader1+" save("+sbhlentityheader1+" teacher) {" + "\r\n" + 
"	" + "\r\n" + 
"		"+sbhlentityheader1+" savedTeacher = authrepo.save(teacher);" + "\r\n" + 
"		return savedTeacher;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public "+sbhlentityheader1+" updateById(int id, "+sbhlentityheader1+" teacherRequest) {" + "\r\n" + 
"		"+sbhlentityheader1+" old_teacher = authrepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"		old_teacher.setExtn1(sale.getExtn1());" + "\r\n" + 
"		old_teacher .setExtn2(sale.getExtn2());" + "\r\n" + 
"		old_teacher.setExtn3(sale.getExtn3());" + "\r\n" + 
"		old_teacher.setExtn4(sale.getExtn4());" + "\r\n" + 
"		old_teacher.setExtn5(sale.getExtn5());" + "\r\n" + 
"		old_teacher.setExtn6(sale.getExtn6());" + "\r\n" + 
"		old_teacher.setExtn7(sale.getExtn7());" + "\r\n" + 
"		old_teacher.setExtn8(sale.getExtn8());" + "\r\n" + 
"		old_teacher.setExtn9(sale.getExtn9());" + "\r\n" + 
"		old_teacher.setExtn10(sale.getExtn10());" + "\r\n" + 
"		old_teacher.setExtn11(sale.getExtn11());" + "\r\n" + 
"		old_teacher.setExtn12(sale.getExtn12());" + "\r\n" + 
"		old_teacher.setExtn13(sale.getExtn13());" + "\r\n" + 
"		old_teacher.setExtn14(sale.getExtn14());" + "\r\n" + 
"		old_teacher.setExtn15(sale.getExtn15());" + "\r\n" + 
"		old_teacher.setFlex1(sale.getFlex1());" + "\r\n" + 
"		old_teacher.setFlex2(sale.getFlex2());" + "\r\n" + 
"		old_teacher.setFlex3(sale.getFlex3());" + "\r\n" + 
"		old_teacher.setFlex4(sale.getFlex4());" + "\r\n" + 
"		old_teacher.setFlex5(sale.getFlex5());" + "\r\n" ); 
 
		 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
		 if(rn_Fb_linefield.getType_field()==null)
		 {
		 	 continue;
		 }

		 if(rn_Fb_linefield.getType_field().equals("textfield") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("textarea") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("url") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("email") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("dropdown") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("checkbox") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("togglebutton") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("datetime") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("autocomplete") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("currency_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("masked") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 if(rn_Fb_linefield.getType_field().equals("contact_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		 {

			 sbhlserviceimplCode.append( 
		 			 "old_teacher.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
		 			 		+ "(teacherRequest.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
		 			  );
		 }
		 
		 }
		 
		 
		 sbhlserviceimplCode.append( 
					 "old_teacher.setBook"
					 		+ "(teacherRequest.getBook());" + "\r\n" 
					  );
		 
		 
		 sbhlserviceimplCode.append(
"		" + "\r\n" + 
"		" + "\r\n" + 
"		final "+sbhlentityheader1+" updated_teacher = authrepo.save(old_teacher);" + "\r\n" + 
"" + "\r\n" + 
"		return updated_teacher;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	public boolean deleteById(int id) {" + "\r\n" + 
"		if (!authrepo.existsById(id)) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"author not found :: \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"		"+sbhlentityheader1+" teacher = authrepo.findById(id).orElse(null);" + "\r\n" + 
"		authrepo.delete(teacher);" + "\r\n" + 
"		return true;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbhlserviceimplFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/service/" + sbhlserviceimpl);
			System.out.println("Directory name = " + sbhlserviceimplFile);
			File sbhlserviceimplFileParentDir = new File(sbhlserviceimplFile.getParent());
			if(!sbhlserviceimplFileParentDir.exists()) {
				sbhlserviceimplFileParentDir.mkdirs();
			}
			if (!sbhlserviceimplFile.exists()) {
				sbhlserviceimplFile.createNewFile();
			}
			fw = new FileWriter(sbhlserviceimplFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlserviceimplCode.toString());
			bw.close();

 StringBuilder sbhladdhtmlCode = new StringBuilder();
 sbhladdhtmlCode.append("<div class=\"container\">" + "\r\n" + 
"  <h3 class=\"center\"><b>ENTRY FORM</b></h3>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  <div>" + "\r\n" + 
"    <a style=\" display: block;margin-left: 90%;\" [routerLink]=\"['../../extension/all']\">" + "\r\n" + 
"      <clr-icon shape=\"airplane\" size=\"32\"></clr-icon>" + "\r\n" + 
"    </a>" + "\r\n" + 
"  </div>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  <!-- Header form start -->" + "\r\n" + 
"  <br />" + "\r\n" + 
"" + "\r\n" + 
"  <div class=\"section\">" + "\r\n" + 
"    <p> Header </p>" + "\r\n" + 
"  </div>" + "\r\n" + 
"  <!-- entry form-->" + "\r\n" + 
"  <section>" + "\r\n" + 
"" + "\r\n" + 
"    <form [formGroup]=\"entryForm\" (ngSubmit)=\"onSubmit()\">" + "\r\n" + 
"" + "\r\n" + 
"      <div class=\"clr-row\" style=\"height: fit-content;\">" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" 		 );

	
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

        if(rn_Fb_linefield.getType_field().equals("section") )
	 {
 		sbhladdhtmlCode.append("</div> \r\n <div class=\"section\" style=\" background-color: #dddddd; height: 40px;\">\r\n"
				+ "    <p style=\" padding: 10px; font-size: 18px;\">"+rn_Fb_linefield.getFieldName()+"</p>\r\n"
				+ "  </div>  \r\n      <div class=\"clr-row\" >");
	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 	 {
 		sbhladdhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
 			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
 			 			"                      <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
 			 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
 			 			"</div>\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhladdhtmlCode.append(
			
			 			"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
			 			"                      <textarea  rows=\"3\" cols=\"40\" 	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
			 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" ></textarea>"+
			 			"</div>\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhladdhtmlCode.append(
 	 			
		 			"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 			" <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
		 			" <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"url\""+
		 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"https://www.facebook.com\" >"+
		 			"</div>\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhladdhtmlCode.append(
	 			
		 			"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 			" <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
		 			" <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"email\""+
		 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\" Enter email\" >"+
		 			"</div>\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhladdhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				"<label >" +rn_Fb_linefield.getFieldName()+ ":</label>\r\n"
				+ "            <select      class=\"clr-dropdown\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
				+ "                <option  value=\"null\">Choose ...</option>\r\n"
				+"				   <option *ngFor=\"let val of dropdownval\" value={{val}}>{{val}}</option>"	
				+ "              </select>"	
				+"</div>\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhladdhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
				+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
				+ "                  <input type=\"checkbox\" clrCheckbox  formControlName=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
				+ "                   value=\"true \"  />\r\n"
				+ "            </div>");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
				+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
				+ "                  <input type=\"checkbox\" clrToggle formControlName=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
				+ "                   value=\"true \"  />\r\n"
				+ "            </div>");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		{
					
 		sbhladdhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
								+ "                <label for=\"doj\">"+rn_Fb_linefield.getFieldName()+":</label>\r\n"
								+ "                <input id=\"doj\"  type=\"date\" class=\"clr-input\"   formControlName=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
								+ "            </div>");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 	 {
 		sbhladdhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
 			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "   \r\n"+	 			
 						" <input type=\"text\"  formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  list=\"datalistauto\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" />\r\n"
 						+ " <datalist  id=\"datalistauto\">\r\n"
 						+ "     <option *ngFor=\"let item of autocomlist\" value={{item}}></option>\r\n"
 						+ "     </datalist>"
 						+ "</div>\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
			 			"                     <span style='font-size:25px;'>&#8377;</span> <input type=\"number\" class=\"clr-input\" \r\n"+
			 			"        		 placeholder=\"Enter  currency\" 	formControlName=\""+rn_Fb_linefield.getFieldName()+"\" style=\"width: fit-content;\"/>"+
			 			"</div>\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
			 			" <input type=\"tel\" mask=\"(000) 000-0000\" style=\"width: fit-content;\" class=\"clr-input\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\">"+
			 			"</div>\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" +
			 			" <input type=\"password\" placeholder=\"Password\" style=\"width: fit-content;\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\">"+
			 			"</div>\r\n");
	 } 	
 	 
 }
 sbhladdhtmlCode.append(
"" + "\r\n" + 
"" + "\r\n" + 
"        <!-- EXTENSION FIELDS START -->" + "\r\n" + 
"        <teacher-add-extension [extensionForm]=\"entryForm\"></teacher-add-extension>" + "\r\n" + 
"        <!-- EXTENSION FIELDS END-->" + "\r\n" + 
"" + "\r\n" + 
"      </div>" + "\r\n" + 
"      <br>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"      <div class=\"section\">" + "\r\n" + 
"        <p> Lines Details</p>" + "\r\n" + 
"      </div>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"      <div class=\"clr-row\">" + "\r\n" + 
"        <div class=\"clr-col-lg-12\">" + "\r\n" + 
"          <table class=\"table\" style=\"width:100%;\" formArrayName=\"book\">" + "\r\n" + 
"            <thead>" + "\r\n" + 
"              <tr>" + "\r\n" + 
"                " + "\r\n" );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line")) 
	 {
	
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line")) 
	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
		{		
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	 
 }
 sbhladdhtmlCode.append(
"                " + "\r\n" + 
"              </tr>" + "\r\n" + 
"            </thead>" + "\r\n" + 
"            <tbody>" + "\r\n" + 
"              <tr *ngFor=\"let item of controls; let i=index\" [formGroupName]=\"i\">" + "\r\n" + 
"               " + "\r\n" );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\"  type=\"text\"   formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"enter \" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"                      <textarea  rows=\"3\" cols=\"40\" 	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
	 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" ></textarea>"+ "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\"  type=\"url\"   formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"https://www.facebook.com\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\"  type=\"email\"  formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" 
 				+ "            <select      class=\"clr-dropdown\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
				+ "                <option  value=\"null\">Choose ...</option>\r\n"
				+"				   <option *ngFor=\"let val of dropdownval\" value={{val}}>{{val}}</option>"	
				+ "              </select>" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\"  type=\"checkbox\" clrCheckbox  formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\"  type=\"checkbox\" clrToggle formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
		{		
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"date\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"text\" list=\"datalistauto\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				
 				" <datalist  id=\"datalistauto\">\r\n"
					+ "     <option *ngFor=\"let item of autocomlist\" value={{item}}></option>\r\n"
					+ "     </datalist>"
					+"      </td>" + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + "                     <span style='font-size:25px;'>&#8377;</span>"+
 				"        <input colspan=\"2\" type=\"number\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"tel\" mask=\"(000) 000-0000\"  formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdhtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"password\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n" );
	 } 	
 	 
 }
 sbhladdhtmlCode.append(
"" + "\r\n" + 
"                <td style=\"width:40px;\">" + "\r\n" + 
"                  <a *ngIf=\"controls.length > 1\" (click)=\"onRemoveLines(i)\">" + "\r\n" + 
"                    <clr-icon shape=\"trash\" class=\"is-error\"></clr-icon>" + "\r\n" + 
"                  </a>" + "\r\n" + 
"                </td>" + "\r\n" + 
"              </tr>" + "\r\n" + 
"            </tbody>" + "\r\n" + 
"            <button type=\"button\" class=\"btn btn-primary button1\" (click)=\"onAddLines()\">" + "\r\n" + 
"              <clr-icon shape=\"plus\"></clr-icon>" + "\r\n" + 
"            </button>" + "\r\n" + 
"          </table>" + "\r\n" + 
"        </div>" + "\r\n" + 
"      </div>" + "\r\n" + 
"      <br>" + "\r\n" + 
"      <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"!entryForm.valid\">SUBMIT</button>" + "\r\n" + 
"    </form>" + "\r\n" + 
"" + "\r\n" + 
"  </section>" + "\r\n" + 
"</div>" 
);

	File sbhladdhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/addsales/" + sbhladdhtml);
			System.out.println("Directory name = " + sbhladdhtmlFile);
			File sbhladdhtmlFileParentDir = new File(sbhladdhtmlFile.getParent());
			if(!sbhladdhtmlFileParentDir.exists()) {
				sbhladdhtmlFileParentDir.mkdirs();
			}
			if (!sbhladdhtmlFile.exists()) {
				sbhladdhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbhladdhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhladdhtmlCode.toString());
			bw.close();

 StringBuilder sbhladdscssCode = new StringBuilder();
 sbhladdscssCode.append(".clr-input {" + "\r\n" + 
"  color: #212529;" + "\r\n" + 
"  border: 1px solid #ced4da;" + "\r\n" + 
"  border-radius: 0.25rem;" + "\r\n" + 
"  padding: 0.75rem 0.75rem;" + "\r\n" + 
"  margin-top: 3px;" + "\r\n" + 
"  width: fit-content;" + "\r\n" + 
"  margin-bottom: 10px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
".center {" + "\r\n" + 
"  text-align: center;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"$bg-color: #dddddd;" + "\r\n" + 
".section {" + "\r\n" + 
"  background-color: $bg-color;" + "\r\n" + 
"  height: 40px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
".section p {" + "\r\n" + 
"  //color: white;" + "\r\n" + 
"  padding: 10px;" + "\r\n" + 
"  font-size: 18px;" + "\r\n" + 
"}" + "\r\n" + 
"#doj{" + "\r\n" + 
" " + "\r\n" + 
"  text-transform: uppercase;" + "\r\n" + 
"  margin: 10px;" + "\r\n" + 
"  padding: 5px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"#build_extension {" + "\r\n" + 
"  cursor:pointer;" + "\r\n" + 
" padding: 95% ;" + "\r\n" + 
"  transition: .5s ease;" + "\r\n" + 
"  top: 97px; // barale niche" + "\r\n" + 
"  // left: 665px; // barale dan" + "\r\n" + 
"  right: 623px; // barale bam" + "\r\n" + 
"  text-align: right;" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"#select" + "\r\n" + 
"{" + "\r\n" + 
"    width: 200px;" + "\r\n" + 
"}" + "\r\n" + 
"option{" + "\r\n" + 
"  border: 5px solid;" + "\r\n" + 
"}" 
);

	File sbhladdscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/addsales/" + sbhladdscss);
			System.out.println("Directory name = " + sbhladdscssFile);
			File sbhladdscssFileParentDir = new File(sbhladdscssFile.getParent());
			if(!sbhladdscssFileParentDir.exists()) {
				sbhladdscssFileParentDir.mkdirs();
			}
			if (!sbhladdscssFile.exists()) {
				sbhladdscssFile.createNewFile();
			}
			fw = new FileWriter(sbhladdscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhladdscssCode.toString());
			bw.close();

 StringBuilder sbhladdtsCode = new StringBuilder();
 sbhladdtsCode.append("" + "\r\n" + 
"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { FormArray, FormBuilder, FormGroup } from '@angular/forms';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { AuthorserviceService } from 'src/app/services/api/authorservice.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"interface errorMsg {" + "\r\n" + 
"  field: any;" + "\r\n" + 
"  message: any;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-addsales'," + "\r\n" + 
 " templateUrl: './"+sbhladdts1+".html',"
 
+
 "  styleUrls: ['./"+sbhladdts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhladdts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"" + "\r\n" + 
"	dropdownval=[\"yes\",\"no\",\"dont know\"];" + "\r\n" + 
"	autocomlist= [\"1000\",\"1001\",\"1002\",\"1003\",\"1004\",\"1005\",\"1006\",\"1007\",\"1008\",\"1009\",\"1010\"];" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"  private formCode: string = 'sales_form';" + "\r\n" + 
"  // STORE FORM CODE IN SESSION" + "\r\n" + 
"  public key: string = \"formCode\";" + "\r\n" + 
"  public storage: Storage = sessionStorage;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"  public entryForm: FormGroup;" + "\r\n" + 
"  submitted = false;" + "\r\n" + 
"  errorMsg: errorMsg[] = [];" + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private _fb: FormBuilder," + "\r\n" + 
"    private router: Router," + "\r\n" + 
"    private route: ActivatedRoute," + "\r\n" + 
"    private authorservice:AuthorserviceService" + "\r\n" + 
"" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit() {" + "\r\n" + 
"" + "\r\n" + 
"    this.storage.setItem(this.key, this.formCode);" + "\r\n" + 
"    console.log(this.storage.getItem(this.key));" + "\r\n" + 
"" + "\r\n" + 
"    this.entryForm = this._fb.group({" + "\r\n" );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") )) 
	 {
	
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") )) 
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
		{		
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	 
 }
 sbhladdtsCode.append(
"      " + "\r\n" + 
"" + "\r\n" + 
"      book: this._fb.array([this.initLinesForm()])," + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"      //Extentions" + "\r\n" + 
"      extn1: [null]," + "\r\n" + 
"      extn2: [null]," + "\r\n" + 
"      extn3: [null]," + "\r\n" + 
"      extn4: [null]," + "\r\n" + 
"      extn5: [null]," + "\r\n" + 
"      extn6: [null]," + "\r\n" + 
"      extn7: [null]," + "\r\n" + 
"      extn8: [null]," + "\r\n" + 
"      extn9: [null]," + "\r\n" + 
"      extn10: [null]," + "\r\n" + 
"      extn11: [null]," + "\r\n" + 
"      extn12: [null]," + "\r\n" + 
"      extn13: [null]," + "\r\n" + 
"      extn14: [null]," + "\r\n" + 
"      extn15: [null]," + "\r\n" + 
"      // FLEX" + "\r\n" + 
"      flex1: [null]," + "\r\n" + 
"      flex2: [null]," + "\r\n" + 
"      flex3: [null]," + "\r\n" + 
"      flex4: [null]," + "\r\n" + 
"      flex5: [null]," + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  initLinesForm() {" + "\r\n" + 
"    return this._fb.group({" + "\r\n" + 
"" + "\r\n");

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line")) 
	 {
	
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line")) 
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
		{		
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhladdtsCode.append(	"    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n");
	 } 	
 	 
 }
 sbhladdtsCode.append(
"" + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    console.warn(\"calling submit\");" + "\r\n" + 
"" + "\r\n" + 
"    //console.log(this.entryForm.value);" + "\r\n" + 
"    this.submitted = true;" + "\r\n" + 
"    if (this.entryForm.invalid) {" + "\r\n" + 
"      return;" + "\r\n" + 
"    }" + "\r\n" + 
"    this.onCreate();" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onCreate() {" + "\r\n" + 
"    console.warn(\"in the oncreate \");" + "\r\n" + 
"" + "\r\n" + 
"    this.authorservice.create(this.entryForm.value).subscribe(data => {" + "\r\n" + 
"      console.log(data)" + "\r\n" + 
"      this.router.navigate([\"/home/author/all\"]);" + "\r\n" + 
"    }," + "\r\n" + 
"      (error) => {" + "\r\n" + 
"        console.log(error);" + "\r\n" + 
"      }" + "\r\n" + 
"    );" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  get controls() {" + "\r\n" + 
"    return (this.entryForm.get(\"book\") as FormArray).controls;" + "\r\n" + 
"  }" + "\r\n" + 
"  onRemoveLines(index: number) {" + "\r\n" + 
"    (<FormArray>this.entryForm.get(\"book\")).removeAt(index);" + "\r\n" + 
"  }" + "\r\n" + 
"  onAddLines() {" + "\r\n" + 
"    (<FormArray>this.entryForm.get(\"book\")).push(this.initLinesForm());" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
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
"  " + "\r\n" + 
"  method:string=\"\";" + "\r\n" + 
"  onClickMe(buttonname) {" + "\r\n" + 
"" + "\r\n" + 
"  this.method=buttonname;" + "\r\n" + 
"  console.log(this.method);" + "\r\n" + 
"  " + "\r\n" + 
"  " + "\r\n" + 
"  this.authorservice.buttonmethod(this.method).subscribe(data=>{" + "\r\n" + 
"    console.log(\"in add ts service and data is \"+data);" + "\r\n" + 
"  });" + "\r\n" + 
"   " + "\r\n" + 
"    " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbhladdtsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/addsales/" + sbhladdts);
			System.out.println("Directory name = " + sbhladdtsFile);
			File sbhladdtsFileParentDir = new File(sbhladdtsFile.getParent());
			if(!sbhladdtsFileParentDir.exists()) {
				sbhladdtsFileParentDir.mkdirs();
			}
			if (!sbhladdtsFile.exists()) {
				sbhladdtsFile.createNewFile();
			}
			fw = new FileWriter(sbhladdtsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhladdtsCode.toString());
			bw.close();

 StringBuilder sbohallhtmlCode = new StringBuilder();
 sbohallhtmlCode.append("<div class=\"grid-pg  pad-16\">" + "\r\n" + 
"    <h3>GRID VIEW</h3>" + "\r\n" + 
"    <button id=\"add\" class=\"btn btn-primary\" (click)=\"goToAdd()\">" + "\r\n" + 
"" + "\r\n" + 
"        <clr-icon shape=\"plus\"></clr-icon>ADD" + "\r\n" + 
"    </button>" + "\r\n" + 
"    " + "\r\n" + 
"" + "\r\n" + 
"    <br>" + "\r\n" + 
"    <div class=\"row\">" + "\r\n" + 
"        <div class=\"col-lg-12\">" + "\r\n" + 
"            <div style=\"width:1400px\">" + "\r\n" + 
"                <div class=\"s-info-bar\">" + "\r\n" + 
"                    <!-- FILTER BUTTON-->" + "\r\n" + 
"                    <input type=\"text\" style=\"padding:8px;margin:15px auto;width:15%;\"" + "\r\n" + 
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
"                            <a (click)=\"goToReadOnly(row.id)\">" + "\r\n" + 
"                                <clr-icon shape=\"eye\" class=\"is-info\"></clr-icon>" + "\r\n" + 
"                            </a>" + "\r\n" + 
"                            <!-- uodate action -->" + "\r\n" + 
"                            <a (click)=\"goToEdit(row.id)\">" + "\r\n" + 
"                                <clr-icon shape=\"edit\" class=\"is-error\"></clr-icon>" + "\r\n" + 
"                            </a>" + "\r\n" + 
"                        </ng-template>" + "\r\n" + 
"                    </ngx-datatable-column>" + "\r\n" + 
"" + "\r\n" + 
"                    <ngx-datatable-column *ngFor=\"let col of columns\" [name]=\"col.name\" [prop]=\"col.prop\"" + "\r\n" + 
"                        [width]=\"col.width\">" + "\r\n" + 
"                    </ngx-datatable-column>" + "\r\n" + 
"" + "\r\n" + 
"                   " + "\r\n" + 
"                    <!-- EXTENSION -->" + "\r\n" + 
"            <teacher-grid-extension></teacher-grid-extension>" + "\r\n" + 
"            <!-- EXTENSION -->" + "\r\n" + 
"       " + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"                </ngx-datatable>" + "\r\n" + 
"            </div>" + "\r\n" + 
"        </div>" + "\r\n" + 
"    </div>" + "\r\n" + 
"</div>" 
);

	File sbohallhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/all/" + sbohallhtml);
			System.out.println("Directory name = " + sbohallhtmlFile);
			File sbohallhtmlFileParentDir = new File(sbohallhtmlFile.getParent());
			if(!sbohallhtmlFileParentDir.exists()) {
				sbohallhtmlFileParentDir.mkdirs();
			}
			if (!sbohallhtmlFile.exists()) {
				sbohallhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbohallhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohallhtmlCode.toString());
			bw.close();

 StringBuilder sbhlallscssCode = new StringBuilder();
 sbhlallscssCode.append("//@import '../../../../../assets/scss/var';" + "\r\n" + 
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

	File sbhlallscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/all/" + sbhlallscss);
			System.out.println("Directory name = " + sbhlallscssFile);
			File sbhlallscssFileParentDir = new File(sbhlallscssFile.getParent());
			if(!sbhlallscssFileParentDir.exists()) {
				sbhlallscssFileParentDir.mkdirs();
			}
			if (!sbhlallscssFile.exists()) {
				sbhlallscssFile.createNewFile();
			}
			fw = new FileWriter(sbhlallscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlallscssCode.toString());
			bw.close();

 StringBuilder sbhlalltsCode = new StringBuilder();
 sbhlalltsCode.append("import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { DatatableComponent } from '@swimlane/ngx-datatable';" + "\r\n" + 
"import { AuthorserviceService } from 'src/app/services/api/authorservice.service';" + "\r\n" + 
"" + "\r\n" + 
"import { ExtensionService } from 'src/app/services/api/extension.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-all'," + "\r\n" + 
 " templateUrl: './"+sbhlallts1+".html',"
 
+
 "  styleUrls: ['./"+sbhlallts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhlallts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"" + "\r\n" + 
"  @ViewChild(\"instructorById\") instructorById: TemplateRef<any>;" + "\r\n" + 
"  @ViewChild(\"txId\") txId: TemplateRef<any>;" + "\r\n" + 
"  @ViewChild(DatatableComponent) table: DatatableComponent;" + "\r\n" + 
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"  columns: any[];" + "\r\n" + 
"  rows: any[];" + "\r\n" + 
"  temp = [];" + "\r\n" + 
"" + "\r\n" + 
"  filterData: string;" + "\r\n" + 
"  isLoading: boolean = false;" + "\r\n" + 
"  sales: any = [];" + "\r\n" + 
"  constructor(private router: Router," + "\r\n" + 
"    private route: ActivatedRoute," + "\r\n" + 
"    private authorservice: AuthorserviceService," + "\r\n" + 
"    private extentionservice: ExtensionService" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  extention: any" + "\r\n" + 
"  fieldname: any" + "\r\n" + 
"  mapping: any" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"" + "\r\n" + 
"    this.getData();" + "\r\n" + 
"    this.columns = [" + "\r\n" + 
"     " + "\r\n" );
	
for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
	 if(rn_Fb_linefield.getType_field()==null)
	 {
		 continue;
	 }
	
	 if(rn_Fb_linefield.getType_field().equals("textfield") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		 sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	 
	 if(rn_Fb_linefield.getType_field().equals("textarea") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		 sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("url") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("email") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("dropdown") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("checkbox") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("togglebutton") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("datetime") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("autocomplete") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("currency_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("masked") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("contact_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhlalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
	 }
}

sbhlalltsCode.append(
"       ];" + "\r\n" + 
"      // { prop: \"uploadprofile\", name: \"uploadprofile\", width: 90 }," + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"        //adding extentions to array if its true" + "\r\n" + 
"      this.extentionservice.getAll().subscribe(ext => {" + "\r\n" + 
"        console.warn(`total extentions    :`, ext)" + "\r\n" + 
"        //   let id=ext.id" + "\r\n" + 
"        // console.log(\"for loop id \", id);" + "\r\n" + 
"        this.extention = ext" + "\r\n" + 
"        for (let id of this.extention) {" + "\r\n" + 
"          if (id.isActive == true) {" + "\r\n" + 
"            console.warn(id);" + "\r\n" + 
"            this.fieldname = id.field_name" + "\r\n" + 
"            this.mapping = id.mapping" + "\r\n" + 
"            console.warn(this.fieldname, this.mapping)," + "\r\n" + 
"              this.columns.push(" + "\r\n" + 
"                { prop: this.mapping, name: this.fieldname, width: 90 })" + "\r\n" + 
"        }" + "\r\n" + 
"        }" + "\r\n" + 
"      })  " + "\r\n" + 
"   " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  getData() {" + "\r\n" + 
"    console.log(\"in  getdata\");" + "\r\n" + 
"    " + "\r\n" + 
"    this.isLoading = true;" + "\r\n" + 
"    this.authorservice.getAll().subscribe((data) => {" + "\r\n" + 
"      console.log(`calling getall service`);" + "\r\n" + 
"" + "\r\n" + 
"      this.isLoading = false;" + "\r\n" + 
"      console.log(data);" + "\r\n" + 
"      console.log(data.author);" + "\r\n" + 
"" + "\r\n" + 
"      this.sales = data.author" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"      this.temp = [...this.sales];" + "\r\n" + 
"      this.rows = this.sales;" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  goToAdd() {" + "\r\n" + 
"    this.router.navigate([\"../add\"], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToEdit(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../edit/\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToReadOnly(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../readonly\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" 
);

	File sbhlalltsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/all/" + sbhlallts);
			System.out.println("Directory name = " + sbhlalltsFile);
			File sbhlalltsFileParentDir = new File(sbhlalltsFile.getParent());
			if(!sbhlalltsFileParentDir.exists()) {
				sbhlalltsFileParentDir.mkdirs();
			}
			if (!sbhlalltsFile.exists()) {
				sbhlalltsFile.createNewFile();
			}
			fw = new FileWriter(sbhlalltsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlalltsCode.toString());
			bw.close();

 StringBuilder sbhledithtmlCode = new StringBuilder();
 sbhledithtmlCode.append("<div class=\"container\">" + "\r\n" + 
"  <div class=\"section\" style=\"width: fit-content;\">" + "\r\n" + 
"    <p>EDIT FORM</p>" + "\r\n" + 
"  </div>" + "\r\n" + 
"  <br>" + "\r\n" + 
"  <form (ngSubmit)=\"onSubmit()\">" + "\r\n" + 
"    <div class=\"clr-row\">" + "\r\n" + 
"" + "\r\n" + 
"      " + "\r\n");

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

         if(rn_Fb_linefield.getType_field().equals("section") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
        	 sbhledithtmlCode.append("\n</div>\n <div class=\"section\" style=\" background-color: #dddddd; height: 40px;\">\r\n"
				+ "    <p style=\" padding: 10px; font-size: 18px;\">"+rn_Fb_linefield.getFieldName()+"</p>\r\n"
				+ "  </div> \n <div class=\"clr-row\"> \n");
	   }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 	 	{
 	
 		sbhledithtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
 						 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
 						  "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"text\""+
 						  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
 						  "</div>\r\n");
 	 	}
 	 
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhledithtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
				  "                      <textarea   rows=\"3\" cols=\"40\"	 style=\"width:fit-content;\" 	 type=\"text\""+
				  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
				  "</div>\r\n");
	 }
	
	if(rn_Fb_linefield.getType_field().equals("url") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhledithtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
				  "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"url\""+
				  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
				  "</div>\r\n");
	 }
	
	if(rn_Fb_linefield.getType_field().equals("email") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhledithtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
				  "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"email\""+
				  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
				  "</div>\r\n");
	 }
	
	if(rn_Fb_linefield.getType_field().equals("dropdown") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhledithtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + 
				 "            <select   [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\"   class=\"clr-dropdown\"  >\r\n"
					+ "                <option value=\"null\">Choose ...</option>\r\n"
					+ "                <option value=\"medicine\">medicine</option>\r\n"
					+ "                <option value=\"herbals\">herbals</option>\r\n"
					+ "                <option value=\"beauty\">beauty</option>\r\n"
					+ "              </select>"	+
				  "</div>\r\n");
	 }
	
	if(rn_Fb_linefield.getType_field().equals("checkbox") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhledithtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
				+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
				+ "                  <input type=\"checkbox\" clrCheckbox  [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
				+ "                   value=\"true \"  />\r\n"
				+ "            </div>");
	 }
	
	if(rn_Fb_linefield.getType_field().equals("togglebutton") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
		sbhledithtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
				+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
				+ "                  <input type=\"checkbox\" clrToggle  [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
				+ "                   value=\"true \"  />\r\n"
				+ "            </div>");
	 }
	
	if(rn_Fb_linefield.getType_field().equals("datetime") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	{
				
		sbhledithtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
							+ "                <label for=\"doj\">"+rn_Fb_linefield.getFieldName()+":</label>\r\n"
							+ "                <input id=\"doj\"  type=\"date\" class=\"clr-input\"   [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
							+ "            </div>");
 } 
	
	
	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
		 sbhledithtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
			 			"                      <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\" autocomplete=\"on\"  "+
			 			"	[(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
			 			"</div>\r\n");
	 } 
	 
	if(rn_Fb_linefield.getType_field().equals("currency_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 {
		sbhledithtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
		 			"                     <span style='font-size:25px;'>&#8377;</span> <input type=\"number\" class=\"clr-input\" \r\n"+
		 			"        		 placeholder=\"Enter  currency\" 	[(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" style=\"width: fit-content;\"/>"+
		 			"</div>\r\n");
 } 
	 
	if(rn_Fb_linefield.getType_field().equals("contact_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") )) 
 {
		sbhledithtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
		 			" <input type=\"tel\" mask=\"(000) 000-0000\" style=\"width: fit-content;\" class=\"clr-input\" [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\">"+
		 			"</div>\r\n");
 } 	
	 
	if(rn_Fb_linefield.getType_field().equals("masked") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 {
		sbhledithtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" +
		 			" <input type=\"password\" placeholder=\"Password\" style=\"width: fit-content;\" [(ngModel)]=\"sales."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\">"+
		 			"</div>\r\n");
 } 	
 						 	 
 }
 
 sbhledithtmlCode.append(
"" + "\r\n" + 
"" + "\r\n" + 
"     " + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    </div>" + "\r\n" + 
"    <br>" + "\r\n" + 
"    <div class=\"center\">" + "\r\n" + 
"      <teacher-edit-extension [teacherExtension]=\"sales\"></teacher-edit-extension>" + "\r\n" + 
"    </div>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    <div class=\"section\" style=\"width: fit-content;\">" + "\r\n" + 
"      <p>Menu Group Lines Details</p>" + "\r\n" + 
"    </div>" + "\r\n" + 
"" + "\r\n" + 
"    <!-----H-L LINE part(FormArray For Multiple Lines)---->" + "\r\n" + 
"" + "\r\n" + 
"    <div class=\"clr-row\">" + "\r\n" + 
"      <div class=\"clr-col-lg-12\">" + "\r\n" + 
"        <table id=\"lines\" class=\"table\" style=\"width:100%;\">" + "\r\n" + 
"          <thead>" + "\r\n" + 
"            <tr>" + "\r\n" );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line")) 
	 {
	
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line")) 
	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
		{		
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	 
 }
 sbhledithtmlCode.append(
"           " + "\r\n" + 
"            </tr>" + "\r\n" + 
"          </thead>" + "\r\n" + 
"          <tbody>" + "\r\n" + 
"            <tr *ngFor=\"let person of sales.book\">" + "\r\n" );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhledithtmlCode.append(	" <td class=\"left\">\r\n"
 				+ "                <input colspan=\"2\" type=\"text\" [(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter Name\"\r\n"
 				+ "                  class=\"clr-input\" />\r\n"
 				+ "              </td>" + "\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"                      <textarea  rows=\"3\" cols=\"40\" 	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
	 			"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" ></textarea>"+ "\r\n" + 
 				"      </td>"+ "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <td class=\"left\">\r\n"
 				+ "                <input colspan=\"2\" type=\"url\" [(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter Name\"\r\n"
 				+ "                  class=\"clr-input\" />\r\n"
 				+ "              </td>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	" <td class=\"left\">\r\n"
 				+ "                <input colspan=\"2\" type=\"email\" [(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter Name\"\r\n"
 				+ "                  class=\"clr-input\" />\r\n"
 				+ "              </td>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line")) 
	 {
	
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" 
 				+ "            <select      class=\"clr-dropdown\" [(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
 				+ "                <option value=\"null\">Choose ...</option>\r\n"
				+ "                <option value=\"medicine\">medicine</option>\r\n"
				+ "                <option value=\"herbals\">herbals</option>\r\n"
				+ "                <option value=\"beauty\">beauty</option>\r\n"
				+ "              </select>" + "\r\n" + 
 				"      </td>" + "\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\"  type=\"checkbox\" clrCheckbox "
 				+"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\""
 						+ "class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>"+"</th>" + "\r\n");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line")) 
	 {
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\"  type=\"checkbox\" clrToggle "
 				+"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\""
 						+ " class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>"  + "\r\n");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
		{		
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"date\""
 				+"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\""
 						+ "class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"text\" "
 				+"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\""
 						+ "class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"number\" "
 				+"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\""
 						+ "class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"number\" "
 				+"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\""
 						+ "class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhledithtmlCode.append(	"     <td class=\"left\">" + "\r\n" + 
 				"" + "\r\n" + 
 				"        <input colspan=\"2\" type=\"password\" "
 				+"[(ngModel)]=\"person."+rn_Fb_linefield.getFieldName()+"\" name=\""+rn_Fb_linefield.getFieldName()+"\""
 						+ "class=\"clr-input\"" + "\r\n" + 
 				"    	  placeholder=\"Enter name\" />" + "\r\n" + 
 				"      </td>" + "\r\n");
	 } 	
 	 
 }
 sbhledithtmlCode.append(
"              " + "\r\n" + 
"" + "\r\n" + 
"            </tr>" + "\r\n" + 
"          </tbody>" + "\r\n" + 
"" + "\r\n" + 
"        </table>" + "\r\n" + 
"      </div>" + "\r\n" + 
"    </div>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"    <button type=\"submit\" form-control class=\"btn btn-primary\">UPDATE</button>" + "\r\n" + 
"  </form>" + "\r\n" + 
"</div>" 
);

	File sbhledithtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/edit/" + sbhledithtml);
			System.out.println("Directory name = " + sbhledithtmlFile);
			File sbhledithtmlFileParentDir = new File(sbhledithtmlFile.getParent());
			if(!sbhledithtmlFileParentDir.exists()) {
				sbhledithtmlFileParentDir.mkdirs();
			}
			if (!sbhledithtmlFile.exists()) {
				sbhledithtmlFile.createNewFile();
			}
			fw = new FileWriter(sbhledithtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhledithtmlCode.toString());
			bw.close();

 StringBuilder sbhleditscssCode = new StringBuilder();
 sbhleditscssCode.append("" + "\r\n" + 
".s-info-bar{" + "\r\n" + 
"    display:flex;" + "\r\n" + 
"    flex-direction: row;" + "\r\n" + 
"    justify-content:space-between;" + "\r\n" + 
"    button{outline:none;}" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
".edit-pg {" + "\r\n" + 
"    width:750px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"#lines {" + "\r\n" + 
"    table, th, td {" + "\r\n" + 
"    border: 0.5px solid  #f1f0f0;" + "\r\n" + 
"    border-collapse: collapse;" + "\r\n" + 
"  }" + "\r\n" + 
"  input, input :focus {" + "\r\n" + 
"    //-webkit-appearance: none;" + "\r\n" + 
"    outline: none;" + "\r\n" + 
"    border-width:0px;" + "\r\n" + 
"    border:none;" + "\r\n" + 
"  }" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
".clr-input {" + "\r\n" + 
"  color: #212529;" + "\r\n" + 
"  border: 1px solid #ced4da;" + "\r\n" + 
"  border-radius: 0.25rem;" + "\r\n" + 
"  padding: 0.75rem 0.75rem;" + "\r\n" + 
"  margin-top: 3px;" + "\r\n" + 
"  width: fit-content;" + "\r\n" + 
"  margin-bottom: 10px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"$bg-color: #dddddd;" + "\r\n" + 
".section {" + "\r\n" + 
"  background-color: $bg-color;" + "\r\n" + 
"  height: 40px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
".section p {" + "\r\n" + 
"  //color: white;" + "\r\n" + 
"  padding: 10px;" + "\r\n" + 
"  font-size: 18px;" + "\r\n" + 
"}" + "\r\n" + 
"#doj{" + "\r\n" + 
" " + "\r\n" + 
" " + "\r\n" + 
"  margin: 10px;" + "\r\n" + 
"  padding: 5px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"#build_extension {" + "\r\n" + 
"  cursor:pointer;" + "\r\n" + 
" padding: 95% ;" + "\r\n" + 
"  transition: .5s ease;" + "\r\n" + 
"  top: 97px; // barale niche" + "\r\n" + 
"  // left: 665px; // barale dan" + "\r\n" + 
"  right: 623px; // barale bam" + "\r\n" + 
"  text-align: right;" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"#select" + "\r\n" + 
"{" + "\r\n" + 
"    width: 200px;" + "\r\n" + 
"}" + "\r\n" + 
"option{" + "\r\n" + 
"  border: 5px solid;" + "\r\n" + 
"}" 
);

	File sbhleditscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/edit/" + sbhleditscss);
			System.out.println("Directory name = " + sbhleditscssFile);
			File sbhleditscssFileParentDir = new File(sbhleditscssFile.getParent());
			if(!sbhleditscssFileParentDir.exists()) {
				sbhleditscssFileParentDir.mkdirs();
			}
			if (!sbhleditscssFile.exists()) {
				sbhleditscssFile.createNewFile();
			}
			fw = new FileWriter(sbhleditscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhleditscssCode.toString());
			bw.close();

 StringBuilder sbhledittsCode = new StringBuilder();
 sbhledittsCode.append("import { HttpErrorResponse } from '@angular/common/http';" + "\r\n" + 
"import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { AuthorserviceService } from 'src/app/services/api/authorservice.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-edit'," + "\r\n" + 
 " templateUrl: './"+sbhleditts1+".html',"
 
+
 "  styleUrls: ['./"+sbhleditts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhleditts2+"Component implements OnInit {"
+
"  updated = false;" + "\r\n" + 
"  sales ;" + "\r\n" + 
"  sid: number;" + "\r\n" + 
"  salesperson;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  constructor(private router: Router," + "\r\n" + 
"    private route:ActivatedRoute," + "\r\n" + 
"    private authorservice:AuthorserviceService" + "\r\n" + 
"    ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"" + "\r\n" + 
"    this.sales;" + "\r\n" + 
"    this.sid = this.route.snapshot.params[\"id\"];" + "\r\n" + 
"    console.log(\"update with id = \", this.sid);" + "\r\n" + 
"    this.getById(this.sid);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  getById(sid: number) {" + "\r\n" + 
"    this.authorservice.getById(sid).subscribe((data) => {" + "\r\n" + 
"      this.sales = data;" + "\r\n" + 
"      console.log(\"data   \",this.sales);" + "\r\n" + 
"      " + "\r\n" + 
"      " + "\r\n" + 
"      //this.students = data.students;" + "\r\n" + 
"      " + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"    update() {" + "\r\n" + 
"      this.authorservice.update(this.sid, this.sales).subscribe(" + "\r\n" + 
"        (data) => {" + "\r\n" + 
"          console.log(data);" + "\r\n" + 
"          this.router.navigate([\"/home/author/all\"]);" + "\r\n" + 
"        }," + "\r\n" + 
"        (error: HttpErrorResponse) => {" + "\r\n" + 
"          console.log(error.message);" + "\r\n" + 
"        }" + "\r\n" + 
"      );" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    onSubmit() {" + "\r\n" + 
"      this.updated = true;" + "\r\n" + 
"      this.update();" + "\r\n" + 
"    }" + "\r\n" + 
"  " + "\r\n" + 
"    " + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbhledittsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/edit/" + sbhleditts);
			System.out.println("Directory name = " + sbhledittsFile);
			File sbhledittsFileParentDir = new File(sbhledittsFile.getParent());
			if(!sbhledittsFileParentDir.exists()) {
				sbhledittsFileParentDir.mkdirs();
			}
			if (!sbhledittsFile.exists()) {
				sbhledittsFile.createNewFile();
			}
			fw = new FileWriter(sbhledittsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhledittsCode.toString());
			bw.close();

 StringBuilder sbhlreadonlyhtmlCode = new StringBuilder();
 sbhlreadonlyhtmlCode.append("<div class=\"read-only-pg pad-16\">" + "\r\n" + 
"  <h3>READ ONLY FORM" + "\r\n" + 
"    <a (click)=\"goToWhoColumns()\">" + "\r\n" + 
"      <clr-icon shape=\"help\" class=\"is-solid\" size=\"32\"></clr-icon>" + "\r\n" + 
"    </a>" + "\r\n" + 
"  </h3>" + "\r\n" + 
"  <br />" + "\r\n" + 
"  <!-- whoo column -->" + "\r\n" + 
"" + "\r\n" + 
"  <clr-modal [(clrModalOpen)]=\"basic\">" + "\r\n" + 
"    <h3 class=\"modal-title\"><b>Transaction History</b></h3>" + "\r\n" + 
"    <div class=\"modal-body\">" + "\r\n" + 
"        <table s-header>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td>AccountId</td>" + "\r\n" + 
"                <td> {{sales.accountId}}</td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td>CreatedAt </td>" + "\r\n" + 
"                <td> {{sales.createdAt}} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td>CreatedBy </td>" + "\r\n" + 
"                <td> {{sales.createdBy}} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td>UpdatedAt </td>" + "\r\n" + 
"                <td> {{sales.updatedAt}} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td>UpdatedBy </td>" + "\r\n" + 
"                <td> {{sales.updatedBy}} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"        </table>" + "\r\n" + 
"    " + "\r\n" + 
"    </div>" + "\r\n" + 
"</clr-modal>" + "\r\n" + 
"" + "\r\n" + 
"  <!-- <clr-tabs>" + "\r\n" + 
"        <clr-tab> -->" + "\r\n" + 
"  <!-- <clr-tab-content> -->" + "\r\n" + 
"  <br />" + "\r\n" + 
"  <table class=\"s-header\">" + "\r\n" );
 
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
 	 {
 	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
 	 }
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("autocomplete") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("masked") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && (rn_Fb_linefield.getType2().equals("header_only") || rn_Fb_linefield.getType2().equals("header") ))
	 {
	
 		sbhlreadonlyhtmlCode.append("        <tr>" + "\r\n" + 
 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
 				"        </tr>" + "\r\n" );
	 }
  }
	

 sbhlreadonlyhtmlCode.append(
"  " + "\r\n" + 
"" + "\r\n" + 
"  </table>" + "\r\n" + 
"" + "\r\n" + 
"  <!-- EXTENSION FIELDS START -->" + "\r\n" + 
"  <teacher-readonly-extension [teacherExtension]=\"sales\"></teacher-readonly-extension>" + "\r\n" + 
"  <!-- EXTENSION FIELDS END-->" + "\r\n" + 
"" + "\r\n" + 
"  <br />" + "\r\n" + 
"  <button (click)=\"back()\" class=\"btn btn-primary\">" + "\r\n" + 
"    <clr-icon shape=\"caret left\"></clr-icon>Back" + "\r\n" + 
"  </button>" + "\r\n" + 
"</div>" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"<!-- line form satrt -->" + "\r\n" + 
"<div class=\"row\">" + "\r\n" + 
"  <div class=\"col-lg-12\">" + "\r\n" + 
"    <table class=\"s-order-line table\" style=\"width:100%;\">" + "\r\n" + 
"      <thead>" + "\r\n" + 
"        <tr>" + "\r\n" );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line")) 
	 {
	
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line")) 
	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
		{		
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append(	" <th class=\"left\" style=\"width:125px;\">"+rn_Fb_linefield.getFieldName()+"</th>" + "\r\n");
	 } 	
 	 
 }
 sbhlreadonlyhtmlCode.append(
"        </tr>" + "\r\n" + 
"      </thead>" + "\r\n" + 
"      <tbody *ngIf=\"sales.book\">" + "\r\n" + 
"        <tr *ngFor=\"let book of sales.book\">" + "\r\n" + 
"          " + "\r\n");

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n" );
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append(	"          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown") && rn_Fb_linefield.getType2().equals("line")) 
	 {
	
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox") && rn_Fb_linefield.getType2().equals("line"))
	 {
	
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton") && rn_Fb_linefield.getType2().equals("line")) 
	 {
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime") && rn_Fb_linefield.getType2().equals("line"))
		{		
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete") && rn_Fb_linefield.getType2().equals("line"))
 	 {
 		sbhlreadonlyhtmlCode.append(	"          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked") && rn_Fb_linefield.getType2().equals("line"))
	 {
 		sbhlreadonlyhtmlCode.append("          <td class=\"left\">{{ salesperson."+rn_Fb_linefield.getFieldName()+" }}</td>" + "\r\n");
	 } 	
 	 
 }
 sbhlreadonlyhtmlCode.append( 
"         " + "\r\n" + 
"        </tr>" + "\r\n" + 
"      </tbody>" + "\r\n" + 
"    </table>" + "\r\n" + 
"  </div>" + "\r\n" + 
"  <button (click)=\"back()\" class=\"btn btn-primary\">" + "\r\n" + 
"    <clr-icon shape=\"caret left\"></clr-icon>Back" + "\r\n" + 
"  </button>" + "\r\n" + 
"</div>" 
);

	File sbhlreadonlyhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/readonly/" + sbhlreadonlyhtml);
			System.out.println("Directory name = " + sbhlreadonlyhtmlFile);
			File sbhlreadonlyhtmlFileParentDir = new File(sbhlreadonlyhtmlFile.getParent());
			if(!sbhlreadonlyhtmlFileParentDir.exists()) {
				sbhlreadonlyhtmlFileParentDir.mkdirs();
			}
			if (!sbhlreadonlyhtmlFile.exists()) {
				sbhlreadonlyhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbhlreadonlyhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlreadonlyhtmlCode.toString());
			bw.close();

 StringBuilder sbhlreadonlyscssCode = new StringBuilder();
 sbhlreadonlyscssCode.append("");

	File sbhlreadonlyscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/readonly/" + sbhlreadonlyscss);
			System.out.println("Directory name = " + sbhlreadonlyscssFile);
			File sbhlreadonlyscssFileParentDir = new File(sbhlreadonlyscssFile.getParent());
			if(!sbhlreadonlyscssFileParentDir.exists()) {
				sbhlreadonlyscssFileParentDir.mkdirs();
			}
			if (!sbhlreadonlyscssFile.exists()) {
				sbhlreadonlyscssFile.createNewFile();
			}
			fw = new FileWriter(sbhlreadonlyscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlreadonlyscssCode.toString());
			bw.close();

 StringBuilder sbhlreadonlytsCode = new StringBuilder();
 sbhlreadonlytsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { AuthorserviceService } from 'src/app/services/api/authorservice.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-readonly-new'," + "\r\n" + 
 " templateUrl: './"+sbhlreadonlyts1+".html',"
 
+
 "  styleUrls: ['./"+sbhlreadonlyts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhlreadonlyts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"  sales;" + "\r\n" + 
"  header_id;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, " + "\r\n" + 
"    private service: AuthorserviceService, " + "\r\n" + 
"    private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.header_id = this.route.snapshot.params['id'];" + "\r\n" + 
"    this.service.getById(this.header_id).subscribe(data => {" + "\r\n" + 
"      this.sales = data;" + "\r\n" + 
"      console.log(this.sales);" + "\r\n" + 
"      " + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  goToWhoColumns() {" + "\r\n" + 
"    this.basic = !this.basic;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"  back() {" + "\r\n" + 
"    this.routing.navigate(['home/author/all']);" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbhlreadonlytsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/readonly/" + sbhlreadonlyts);
			System.out.println("Directory name = " + sbhlreadonlytsFile);
			File sbhlreadonlytsFileParentDir = new File(sbhlreadonlytsFile.getParent());
			if(!sbhlreadonlytsFileParentDir.exists()) {
				sbhlreadonlytsFileParentDir.mkdirs();
			}
			if (!sbhlreadonlytsFile.exists()) {
				sbhlreadonlytsFile.createNewFile();
			}
			fw = new FileWriter(sbhlreadonlytsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlreadonlytsCode.toString());
			bw.close();

 StringBuilder sbhlhtmlCode = new StringBuilder();
 sbhlhtmlCode.append("<router-outlet></router-outlet>" 
);

	File sbhlhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/" + sbhlhtml);
			System.out.println("Directory name = " + sbhlhtmlFile);
			File sbhlhtmlFileParentDir = new File(sbhlhtmlFile.getParent());
			if(!sbhlhtmlFileParentDir.exists()) {
				sbhlhtmlFileParentDir.mkdirs();
			}
			if (!sbhlhtmlFile.exists()) {
				sbhlhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbhlhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlhtmlCode.toString());
			bw.close();

 StringBuilder sbhlscssCode = new StringBuilder();
 sbhlscssCode.append("");

	File sbhlscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/" + sbhlscss);
			System.out.println("Directory name = " + sbhlscssFile);
			File sbhlscssFileParentDir = new File(sbhlscssFile.getParent());
			if(!sbhlscssFileParentDir.exists()) {
				sbhlscssFileParentDir.mkdirs();
			}
			if (!sbhlscssFile.exists()) {
				sbhlscssFile.createNewFile();
			}
			fw = new FileWriter(sbhlscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhlscssCode.toString());
			bw.close();

 StringBuilder sbhltsCode = new StringBuilder();
 sbhltsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-sales-new'," + "\r\n" + 
 " templateUrl: './"+sbhlts1+".html',"
 
+
 "  styleUrls: ['./"+sbhlts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbhlts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  constructor() { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbhltsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/" + sbhlts);
			System.out.println("Directory name = " + sbhltsFile);
			File sbhltsFileParentDir = new File(sbhltsFile.getParent());
			if(!sbhltsFileParentDir.exists()) {
				sbhltsFileParentDir.mkdirs();
			}
			if (!sbhltsFile.exists()) {
				sbhltsFile.createNewFile();
			}
			fw = new FileWriter(sbhltsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbhltsCode.toString());
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