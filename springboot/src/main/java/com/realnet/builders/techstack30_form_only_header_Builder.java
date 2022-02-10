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

import com.realnet.Module.salesnew.service.techstack50formonly_headerimpl;
import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Header;
import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Lines;
import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Header;
import com.realnet.actionbuilder.service.Rn_Cff_ActionBuilder_Service;
import com.realnet.actionbuilder.service.rn_cff_actionbuilder_button_service;
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
import com.realnet.wfb.service.angbootonlyheaderservice;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Master Builder" })
public class techstack30_form_only_header_Builder {


 @Value("${angularProjectPath}")
	private String angularProjectPath; @Value("${projectPath}")
	private String projectPath;
	@Autowired
	private Rn_WireFrame_Service wireFrameService;
		
	@Autowired
	private techstack50formonly_headerimpl flfser;
	@Autowired
	private Rn_LookUp_Service lookUpService;

	@Autowired
	private Rn_Cff_ActionBuilder_Service actionBuilderService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private FieldTypeService fieldTypeService;
	
	@Autowired
	private rn_cff_actionbuilder_button_service btnservise;
	
	
	@GetMapping(value = "/techstack30_form_only_header_Builder")
	public ResponseEntity<?> build_wireframe(@RequestParam("header_id") Integer id) throws IOException {

				System.out.println("id ::"+id);
			 lookUpService.createTable(id); 
 // extra button    
    List<Rn_Fb_Line> extraButton = wireFrameService.getExtraButton(id);	
	// HEADER VALUE
		Rn_Fb_Header rn_fb_header = wireFrameService.getById(id);
		
		// LINE VALUES
		List<Rn_Fb_Line> rn_fb_lines = rn_fb_header.getRn_fb_lines();
//		String addhtmlfields = fieldservice.addhtmlfields(id);
//		String updatefields = fieldservice.updatefields(id);
//		String readonlyfields = fieldservice.readonlyfields(id);
		
//		String addhtml = fieldservice.dynamicfields(id,"html", "addhtml");
//		String updatehtml = fieldservice.dynamicfields(id,"html", "updatehtml");
//		String readonlyhtml = fieldservice.dynamicfields(id,"html", "readonlyhtml");
//		String modelentity = fieldservice.dynamicfields(id,"java", "modelentity");
//		String modelgetset = fieldservice.dynamicfields(id,"java", "modelgettersetter");
//		String updatefield = fieldservice.dynamicfields(id,"java", "updatefields");
//		String  addtsfield = fieldservice.dynamicfields(id,"ts", "addtsfield");
//		String alltsfield = fieldservice.dynamicfields(id,"ts", "alltsfield");
//		String modeltsfield = fieldservice.dynamicfields(id,"ts", " ");
		
		String addhtml = flfser.dynamicfields(id, "html","update", "header");
		
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

String sbohcontroller = ui_name +"SalesController.java";
String  mainstr0 = sbohcontroller;
String sbohcontroller1=mainstr0.replace(".java", "");


String sbohentity = ui_name +"sales.java";
String  mainstr1 = sbohentity;
String sbohentity1=mainstr1.replace(".java", "");


String sbohrepository = ui_name +"Salesrepository.java";
String  mainstr2 = sbohrepository;
String sbohrepository1=mainstr2.replace(".java", "");


String sbohresponce = ui_name +"SalesResponce.java";
String  mainstr3 = sbohresponce;
String sbohresponce1=mainstr3.replace(".java", "");


String sbohservice = ui_name +"salesservice.java";
String  mainstr4 = sbohservice;
String sbohservice1=mainstr4.replace(".java", "");


String sbohserviceimpl = ui_name +"Salesserviceimpl.java";
String  mainstr5 = sbohserviceimpl;
String sbohserviceimpl1=mainstr5.replace(".java", "");


String sbohaddhtml = ui_name +"add.component.html";
String  mainstr6 = sbohaddhtml;
String sbohaddhtml1=mainstr6.replace(".html", "");


String sbohaddscss = ui_name +"add.component.scss";
String  mainstr7 = sbohaddscss;
String sbohaddscss1=mainstr7.replace(".scss", "");


String sbohaddts = ui_name +"add.component.ts";
String  mainstr8 = sbohaddts;
String sbohaddts1=mainstr8.replace(".ts", "");

String sbohaddts2=mainstr8.replace(".component.ts", "");


String sbohallhtml = ui_name +"all.component.html";
String  mainstr9 = sbohallhtml;
String sbohallhtml1=mainstr9.replace(".html", "");


String sbohallscss = ui_name +"all.component.scss";
String  mainstr10 = sbohallscss;
String sbohallscss1=mainstr10.replace(".scss", "");


String sbohallts = ui_name +"all.component.ts";
String  mainstr11 = sbohallts;
String sbohallts1=mainstr11.replace(".ts", "");

String sbohallts2=mainstr11.replace(".component.ts", "");


String sbohreadonllyhtml = ui_name +"readonly.component.html";
String  mainstr12 = sbohreadonllyhtml;
String sbohreadonllyhtml1=mainstr12.replace(".html", "");


String sbohreadonlyscss = ui_name +"readonly.component.scss";
String  mainstr13 = sbohreadonlyscss;
String sbohreadonlyscss1=mainstr13.replace(".scss", "");


String sbohreadonlyts = ui_name +"readonly.component.ts";
String  mainstr14 = sbohreadonlyts;
String sbohreadonlyts1=mainstr14.replace(".ts", "");

String sbohreadonlyts2=mainstr14.replace(".component.ts", "");


String sbohupdatehtml = ui_name +"update.component.html";
String  mainstr15 = sbohupdatehtml;
String sbohupdatehtml1=mainstr15.replace(".html", "");


String sbohupdatescss = ui_name +"update.component.scss";
String  mainstr16 = sbohupdatescss;
String sbohupdatescss1=mainstr16.replace(".scss", "");


String sbohupdatets = ui_name +"update.component.ts";
String  mainstr17 = sbohupdatets;
String sbohupdatets1=mainstr17.replace(".ts", "");

String sbohupdatets2=mainstr17.replace(".component.ts", "");


String sbohts = ui_name +"sales.ts";
String  mainstr18 = sbohts;
String sbohts1=mainstr18.replace(".ts", "");

String sbohts2=mainstr18.replace(".component.ts", "");


String sbohnewhtml = ui_name +"salesnew.component.html";
String  mainstr19 = sbohnewhtml;
String sbohnewhtml1=mainstr19.replace(".html", "");


String sbohnewscss = ui_name +"salesnew.component.scss";
String  mainstr20 = sbohnewscss;
String sbohnewscss1=mainstr20.replace(".scss", "");


String sbohnewts = ui_name +"salesnew.component.ts";
String  mainstr21 = sbohnewts;
String sbohnewts1=mainstr21.replace(".ts", "");

String sbohnewts2=mainstr21.replace(".component.ts", "");



			FileWriter fw = null;
			BufferedWriter bw = null;
			try { 
		// EXTRA BUTTON LIST (IF USER ADD EXTRA BUTTON THEN THIS WILL CALL)
		List<Rn_Fb_Line> extraButtonList = wireFrameService.getExtraButton(id);
 	// CFF ACTION BUILDER
		StringBuilder cff_add_button_update_controller = new StringBuilder();
 	StringBuilder cff_add_button_code_in_update_jsp = new StringBuilder();
		int buttonLoopCount = 0;
		
		String controllerName=null;
		String method_name=null;
		String file_location=null;
		for (Rn_Fb_Line btn : extraButtonList) {
			System.out.println("BUTON LOOP COUNT = " + ++buttonLoopCount);
			String field_name = btn.getFieldName();
			String mapping = btn.getMapping();
			method_name = btn.getMethodName();

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
			 controllerName = method_name.substring(0, 1).toUpperCase()
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
		file_location = file.getAbsolutePath().replace("\\", "/");
			// SHOULD BE LIKE = /Projects/only_header_testing/Demo.java
			file_location = file_location.substring(file_location.lastIndexOf("/Projects/"));
			/*
			 * ====== MODIFICATION ==== path =
			 * path.substring(path.lastIndexOf("/Projects/")+1); path =
			 * path.substring(path.indexOf("/")); 
			 * OP = /only_header_testing/Demo.java
			 */
			
			
		}
		btnservise.actionbuilderbutton( id,controllerName, technology_stack,
			 file_location, sbohservice1, sbohentity1);
		
		
		// EXTRA BUTTON LOOP END
 StringBuilder sbohcontrollerCode = new StringBuilder();
 sbohcontrollerCode.append("package com.realnet.comm." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.HashMap;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"import com.realnet.comm.response.SalesResponse;" + "\r\n" + 
"import com.realnet.comm.service.salesservice;" + "\r\n" + 
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
"@RequestMapping(\"/api\")" + "\r\n" + 
"@Api(tags = { \"Sales\" })" + "\r\n" + 
"public class " + sbohcontroller1 + "{"+
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	salesservice saleservice;" + "\r\n" + 
"" + "\r\n" + 
"	// get all data" + "\r\n" + 
"	@ApiOperation(value = \"List of sales\", response = SalesResponse.class)" + "\r\n" + 
"	@GetMapping(path = \"/getsales\")" + "\r\n" + 
"	public SalesResponse getdata(@RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer page," + "\r\n" + 
"			@RequestParam(value = \"size\", defaultValue = \"20\", required = false) Integer size) {" + "\r\n" + 
"		SalesResponse resp = new SalesResponse();" + "\r\n" + 
"		Pageable paging = PageRequest.of(page, size);" + "\r\n" + 
"		Page<"+sbohentity1+"> result = this.saleservice.getlist(paging);" + "\r\n" + 
"		resp.setPageStats(result, true);" + "\r\n" + 
"		resp.setSales(result.getContent());" + "\r\n" + 
"" + "\r\n" + 
"		return resp;" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// @RequestParam(\"file\") MultipartFile file ," + "\r\n" + 
"	// add data" + "\r\n" + 
"	@ApiOperation(value = \"add a sales\", response = "+sbohentity1+".class)" + "\r\n" + 
"	@PostMapping(path = \"/addsales\")" + "\r\n" + 
"	public ResponseEntity<?> savedata(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken, @Valid" + "\r\n" + 
"" + "\r\n" + 
"			@RequestBody sales sale) {" + "\r\n" + 
"		// String filename=file.getOriginalFilename();" + "\r\n" + 
"		// System.err.println(filename);" + "\r\n" + 
"		// sale.setUploadprofile(filename);" + "\r\n" + 
"		//" + "\r\n" + 
"		sales data = this.saleservice.createsale(sale);" + "\r\n" + 
"		if (data == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"sales not saved\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.CREATED).body(data);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// get data by id" + "\r\n" + 
"	@ApiOperation(value = \"Get a sales\", response = sales.class)" + "\r\n" + 
"	@GetMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<sales> getbyid(@PathVariable(\"id\") Integer id) {" + "\r\n" + 
"		sales getid = this.saleservice.getid(id);" + "\r\n" + 
"		if (getid == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"id not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.ok().body(getid);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// UPDATE" + "\r\n" + 
"	@ApiOperation(value = \"update a sale\", response = sales.class)" + "\r\n" + 
"	@PutMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<sales> updateTeacher(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"			@PathVariable(value = \"id\") Integer id, @Valid @RequestBody sales sale) {" + "\r\n" + 
"" + "\r\n" + 
"		sales updatedsale = saleservice.updateById(id, sale);" + "\r\n" + 
"		if (updatedsale == null || id != updatedsale.getSid()) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"sales not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedsale);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// DELETE" + "\r\n" + 
"	@DeleteMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable(value = \"id\") Integer id) {" + "\r\n" + 
"		boolean delete = saleservice.deleteById(id);" + "\r\n" + 
"		Map<String, Boolean> response = new HashMap<>();" + "\r\n" + 
"		if (delete) {" + "\r\n" + 
"			response.put(\"delete\", Boolean.TRUE);" + "\r\n" + 
"			return ResponseEntity.status(HttpStatus.OK).body(response);" + "\r\n" + 
"		}" + "\r\n" + 
"" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbohcontrollerFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/controller/" + sbohcontroller);
			System.out.println("Directory name = " + sbohcontrollerFile);
			File sbohcontrollerFileParentDir = new File(sbohcontrollerFile.getParent());
			if(!sbohcontrollerFileParentDir.exists()) {
				sbohcontrollerFileParentDir.mkdirs();
			}
			if (!sbohcontrollerFile.exists()) {
				sbohcontrollerFile.createNewFile();
			}
			fw = new FileWriter(sbohcontrollerFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohcontrollerCode.toString());
			bw.close();

 StringBuilder sbohentityCode = new StringBuilder();
 sbohentityCode.append("package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"import java.util.Set;" + "\r\n" +  
"" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_AuditEntity;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"sales\")" + "\r\n" + 
"public class " + sbohentity1 + "{"+
"	" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	@Column(name = \"sales_id\")" + "\r\n" + 
"	private int sid;" + "\r\n" + 
"	" + "\r\n" 
		 );
		 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
		 	 if(rn_Fb_linefield.getType_field()==null)
		 	 {
		 		 continue;
		 	 }
		 	
		 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
		 	 {
		 	
		 		sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
		 	 }
		 	 
		 	if(rn_Fb_linefield.getType_field().equals("textarea"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("url"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("email"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("dropdown"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("checkbox"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("togglebutton"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("datetime"))
			 {
			
				sbohentityCode.append("    private Date  "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("autocomplete"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("currency_field"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("masked"))
			 {
			
				sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	if(rn_Fb_linefield.getType_field().equals("contact_field"))
			 {
			
				sbohentityCode.append("    private long  "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
			 }
		 	
		 	
		  }
		 
		 sbohentityCode.append(	


"" + "\r\n" + 
"" + "\r\n" + 
"	public int getSid() {" + "\r\n" + 
"		return sid;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setSid(int sid) {" + "\r\n" + 
"		this.sid = sid;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" 
); 
 
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 if(rn_Fb_linefield.getType_field()==null)
 {
 	 continue;
 }

 if(rn_Fb_linefield.getType_field().equals("textfield"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("textarea"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("url"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("email"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("dropdown"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("checkbox"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("togglebutton"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("datetime"))
 {

 	sbohentityCode.append( "	public Date get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(Date "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("autocomplete"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("currency_field"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("masked"))
 {

 	sbohentityCode.append( "	public String get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(String "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 if(rn_Fb_linefield.getType_field().equals("contact_field"))
 {

 	sbohentityCode.append( "	public long get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"() {" + "\r\n" + 
 			 "		return "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" + 
 			 "" + "\r\n" + 
 			 "	public void set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"(long "+rn_Fb_linefield.getFieldName()+") {" + "\r\n" + 
 			 "		this."+rn_Fb_linefield.getFieldName()+" = "+rn_Fb_linefield.getFieldName()+";" + "\r\n" + 
 			 "	}" + "\r\n" );
 }
 
 
 }
  
 sbohentityCode.append(
"	" + "\r\n" + 
"}" 
);

	File sbohentityFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/entity/" + sbohentity);
			System.out.println("Directory name = " + sbohentityFile);
			File sbohentityFileParentDir = new File(sbohentityFile.getParent());
			if(!sbohentityFileParentDir.exists()) {
				sbohentityFileParentDir.mkdirs();
			}
			if (!sbohentityFile.exists()) {
				sbohentityFile.createNewFile();
			}
			fw = new FileWriter(sbohentityFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohentityCode.toString());
			bw.close();

 StringBuilder sbohrepositoryCode = new StringBuilder();
 sbohrepositoryCode.append("package com.realnet.comm." + module_name + ".repository;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.jpa.repository.JpaRepository;" + "\r\n" + 
"import org.springframework.stereotype.Repository;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface " + sbohrepository1 + "{"+
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
);

	File sbohrepositoryFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/repository/" + sbohrepository);
			System.out.println("Directory name = " + sbohrepositoryFile);
			File sbohrepositoryFileParentDir = new File(sbohrepositoryFile.getParent());
			if(!sbohrepositoryFileParentDir.exists()) {
				sbohrepositoryFileParentDir.mkdirs();
			}
			if (!sbohrepositoryFile.exists()) {
				sbohrepositoryFile.createNewFile();
			}
			fw = new FileWriter(sbohrepositoryFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohrepositoryCode.toString());
			bw.close();

 StringBuilder sbohresponceCode = new StringBuilder();
 sbohresponceCode.append("package com.realnet.comm." + module_name + ".response;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"import com.realnet.fnd.response.PageResponse;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"import lombok.EqualsAndHashCode;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@EqualsAndHashCode(callSuper=false)" + "\r\n" + 
"public class " + sbohresponce1 + "{"+
"	 @ApiModelProperty(required = true, value = \"\")" + "\r\n" + 
"	  private List<sales> sales;" + "\r\n" + 
"}" 
);

	File sbohresponceFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/responce/" + sbohresponce);
			System.out.println("Directory name = " + sbohresponceFile);
			File sbohresponceFileParentDir = new File(sbohresponceFile.getParent());
			if(!sbohresponceFileParentDir.exists()) {
				sbohresponceFileParentDir.mkdirs();
			}
			if (!sbohresponceFile.exists()) {
				sbohresponceFile.createNewFile();
			}
			fw = new FileWriter(sbohresponceFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohresponceCode.toString());
			bw.close();

 StringBuilder sbohserviceCode = new StringBuilder();
 sbohserviceCode.append("package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"" + "\r\n" + 
"public interface " + sbohservice1 + "{"+
"" + "\r\n" + 
"    public Page<sales> getlist(Pageable page);" + "\r\n" + 
"    public sales createsale(sales data);" + "\r\n" + 
"    public sales getid(int id);" + "\r\n" + 
"    public sales updateById(int id, sales sale);" + "\r\n" + 
"    public boolean deleteById(int id);" + "\r\n" + 
"    " + "\r\n" + 
"}" 
);

	File sbohserviceFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/service/" + sbohservice);
			System.out.println("Directory name = " + sbohserviceFile);
			File sbohserviceFileParentDir = new File(sbohserviceFile.getParent());
			if(!sbohserviceFileParentDir.exists()) {
				sbohserviceFileParentDir.mkdirs();
			}
			if (!sbohserviceFile.exists()) {
				sbohserviceFile.createNewFile();
			}
			fw = new FileWriter(sbohserviceFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohserviceCode.toString());
			bw.close();

 StringBuilder sbohserviceimplCode = new StringBuilder();
 sbohserviceimplCode.append("package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"import com.realnet.comm.repository.Salesrepository;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class " + sbohserviceimpl1 + "{"+
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	Salesrepository salesrepo;" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"	//getting data" + "\r\n" + 
"	public Page<sales> getlist(Pageable page)" + "\r\n" + 
"	{" + "\r\n" + 
"		Page<sales> find = this.salesrepo.findAll(page);" + "\r\n" + 
"		return find;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//creating sales " + "\r\n" + 
"	public sales createsale(sales data)" + "\r\n" + 
"	{" + "\r\n" + 
"		sales save = this.salesrepo.save(data);" + "\r\n" + 
"		return save;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//get by id" + "\r\n" + 
"	public sales getid(int id)" + "\r\n" + 
"	{" + "\r\n" + 
"			" + "\r\n" + 
"			sales teacher = salesrepo.findById(id)" + "\r\n" + 
"					.orElseThrow(() -> new ResourceNotFoundException(\"Te not found :: \" + id));" + "\r\n" + 
"			return teacher;" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//update by id" + "\r\n" + 
"	public sales updateById(int id, sales sale) {" + "\r\n" + 
"		sales old_sale = salesrepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"" + "\r\n" 
		 ); 
 
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 if(rn_Fb_linefield.getType_field()==null)
 {
 	 continue;
 }

 if(rn_Fb_linefield.getType_field().equals("textfield"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("textarea"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("url"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("email"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("dropdown"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("checkbox"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("togglebutton"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("datetime"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("autocomplete"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("currency_field"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("masked"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 if(rn_Fb_linefield.getType_field().equals("contact_field"))
 {

	 sbohserviceimplCode.append( 
 			 "old_sale.set"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+""
 			 		+ "(sale.get"+rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()+rn_Fb_linefield.getFieldName().substring(1)+"());" + "\r\n" 
 			  );
 }
 
 }
 
 sbohserviceimplCode.append(

"" + "\r\n" + 
"		final sales updated_sale = salesrepo.save(old_sale);" + "\r\n" + 
"" + "\r\n" + 
"		return updated_sale;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//delete" + "\r\n" + 
"	public boolean deleteById(int id) {" + "\r\n" + 
"		if (!salesrepo.existsById(id)) {" + "\r\n" + 
"			" + "\r\n" + 
"			throw new ResourceNotFoundException(\"Sales not found :: \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"		sales sale = salesrepo.findById(id).orElse(null);" + "\r\n" + 
"		salesrepo.delete(sale);" + "\r\n" + 
"		return true;" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
);

	File sbohserviceimplFile = new File(projectPath + "/Projects/" + project_name + "/src/main/java/com/realnet/Module/salesnew/service/" + sbohserviceimpl);
			System.out.println("Directory name = " + sbohserviceimplFile);
			File sbohserviceimplFileParentDir = new File(sbohserviceimplFile.getParent());
			if(!sbohserviceimplFileParentDir.exists()) {
				sbohserviceimplFileParentDir.mkdirs();
			}
			if (!sbohserviceimplFile.exists()) {
				sbohserviceimplFile.createNewFile();
			}
			fw = new FileWriter(sbohserviceimplFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohserviceimplCode.toString());
			bw.close();

 StringBuilder sbohaddhtmlCode = new StringBuilder();
 sbohaddhtmlCode.append("<div class=\"entry-pg pad-16\">" + "\r\n" + 
"    <h3 class=\"center\"><b>SALES GROUP ENTRY FORM</b></h3>" + "\r\n" + 
"	" + "\r\n" + 
"    <a id=\"build_extension\" [routerLink] = \"['../../extension/all']\">" + "\r\n" + 
"        <clr-icon shape=\"airplane\" size=\"32\"></clr-icon>" + "\r\n" + 
"    </a>" + "\r\n" + 
"" + "\r\n" + 
"    <section class=\"form-block\" style=\"margin-top:32px\">" + "\r\n" + 
"        <!-- entry form-->" + "\r\n" + 
"        <form [formGroup]=\"entryForm\" (ngSubmit)=\"onSubmit()\">" + "\r\n"
		 );

	
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }

        if(rn_Fb_linefield.getType_field().equals("section"))
	 {
        	sbohaddhtmlCode.append(" <div class=\"section\" style=\" background-color: #dddddd; height: 40px;\">\r\n"
				+ "    <p style=\" padding: 10px; font-size: 18px;\">"+rn_Fb_linefield.getFieldName()+"</p>\r\n"
				+ "  </div>");
	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
 	 {
 		sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
 			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
 			 			"                      <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
 			 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
 			 			"</div>\r\n");
 	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea"))
	 {
	
		sbohaddhtmlCode.append(
			
			 			"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
			 			"                      <textarea  rows=\"3\" cols=\"40\" 	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
			 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" ></textarea>"+
			 			"</div>\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("url"))
	 {
	
 		sbohaddhtmlCode.append(
 	 			
		 			"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 			" <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
		 			" <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"url\""+
		 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"https://www.facebook.com\" >"+
		 			"</div>\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("email"))
	 {
	
		sbohaddhtmlCode.append(
	 			
		 			"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 			" <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
		 			" <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"email\""+
		 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\" Enter email\" >"+
		 			"</div>\r\n");
	 } 	
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown"))
	 {
	
		sbohaddhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				"<label >" +rn_Fb_linefield.getFieldName()+ ":</label>\r\n"
				+ "            <select      class=\"clr-dropdown\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
				+ "                <option  value=\"null\">Choose ...</option>\r\n"
				+"				   <option *ngFor=\"let val of dropdownval\" value={{val}}>{{val}}</option>"	
				+ "              </select>"	
				+"</div>\r\n");
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox"))
	 {
	
						sbohaddhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
				+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
				+ "                  <input type=\"checkbox\" clrCheckbox  formControlName=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
				+ "                   value=\"true \"  />\r\n"
				+ "            </div>");
	 } 
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton"))
	 {
						sbohaddhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
				+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
				+ "                  <input type=\"checkbox\" clrToggle formControlName=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
				+ "                   value=\"true \"  />\r\n"
				+ "            </div>");
	 } 			
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime"))
		{
					
						sbohaddhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
								+ "                <label for=\"doj\">"+rn_Fb_linefield.getFieldName()+":</label>\r\n"
								+ "                <input id=\"doj\"  type=\"date\" class=\"clr-input\"   formControlName=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
								+ "            </div>");
	 } 
 	
 	
 	 if(rn_Fb_linefield.getType_field().equals("autocomplete"))
 	 {
 		sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
 			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "   \r\n"+	 			
 						" <input type=\"text\"  formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  list=\"datalistauto\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" />\r\n"
 						+ " <datalist  id=\"datalistauto\">\r\n"
 						+ "     <option *ngFor=\"let item of autocomlist\" value={{item}}></option>\r\n"
 						+ "     </datalist>"
 						+ "</div>\r\n");
 	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("currency_field"))
	 {
		sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
			 			"                     <span style='font-size:25px;'>&#8377;</span> <input type=\"number\" class=\"clr-input\" \r\n"+
			 			"        		 placeholder=\"Enter  currency\" 	formControlName=\""+rn_Fb_linefield.getFieldName()+"\" style=\"width: fit-content;\"/>"+
			 			"</div>\r\n");
	 } 
 	 
 	if(rn_Fb_linefield.getType_field().equals("contact_field"))
	 {
		sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
			 			" <input type=\"tel\" mask=\"(000) 000-0000\" style=\"width: fit-content;\" class=\"clr-input\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\">"+
			 			"</div>\r\n");
	 } 	
 	 
 	if(rn_Fb_linefield.getType_field().equals("masked"))
	 {
		sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" +
			 			" <input type=\"password\" placeholder=\"Password\" style=\"width: fit-content;\" formControlName=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\">"+
			 			"</div>\r\n");
	 } 	
 	 
 }
 sbohaddhtmlCode.append(
"			" + "\r\n" + 
"			<!-- EXTENSION FIELDS START -->" + "\r\n" + 
"<teacher-add-extension [extensionForm]=\"entryForm\"></teacher-add-extension>" + "\r\n" + 
"<!-- EXTENSION FIELDS END-->" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"            " + "\r\n" + 
"            <br>" + "\r\n" + 
"            <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"!entryForm.valid\">SUBMIT</button>" + "\r\n" 
		 );
 
 for (Rn_Fb_Line button : extraButton) {
 	 System.out.println(button.getFieldName());
 	sbohaddhtmlCode.append("\n <button type=\"button\" class=\"btn btn-primary\"  (click)=\"onClickMe('"+button.getFieldName()+"')\">"+button.getFieldName()+"</button>\n");
 }
 
  
 sbohaddhtmlCode.append(

"        </form>" + "\r\n" + 
"    </section>" + "\r\n" + 
"" + "\r\n" + 
"</div>" 
);

	File sbohaddhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/add/" + sbohaddhtml);
			System.out.println("Directory name = " + sbohaddhtmlFile);
			File sbohaddhtmlFileParentDir = new File(sbohaddhtmlFile.getParent());
			if(!sbohaddhtmlFileParentDir.exists()) {
				sbohaddhtmlFileParentDir.mkdirs();
			}
			if (!sbohaddhtmlFile.exists()) {
				sbohaddhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbohaddhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohaddhtmlCode.toString());
			bw.close();

 StringBuilder sbohaddscssCode = new StringBuilder();
 sbohaddscssCode.append(".clr-input {" + "\r\n" + 
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

	File sbohaddscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/add/" + sbohaddscss);
			System.out.println("Directory name = " + sbohaddscssFile);
			File sbohaddscssFileParentDir = new File(sbohaddscssFile.getParent());
			if(!sbohaddscssFileParentDir.exists()) {
				sbohaddscssFileParentDir.mkdirs();
			}
			if (!sbohaddscssFile.exists()) {
				sbohaddscssFile.createNewFile();
			}
			fw = new FileWriter(sbohaddscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohaddscssCode.toString());
			bw.close();

 StringBuilder sbohaddtsCode = new StringBuilder();
 sbohaddtsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { FormArray, FormBuilder, FormGroup } from '@angular/forms';" + "\r\n" + 
"import { Router } from '@angular/router';" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-add'," + "\r\n" + 
 " templateUrl: './"+sbohaddts1+".html',"
 
+
 "  styleUrls: ['./"+sbohaddts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohaddts2+"Component implements OnInit {"
+
"  //create a object of entry form" + "\r\n" + 
"  public entryForm: FormGroup;" + "\r\n" + 
"  submitted = false;" + "\r\n" + 
"	" + "\r\n" + 
"	dropdownval=[\"yes\",\"no\",\"dont know\"];" + "\r\n" + 
"	autocomlist= [\"1000\",\"1001\",\"1002\",\"1003\",\"1004\",\"1005\",\"1006\",\"1007\",\"1008\",\"1009\",\"1010\"];" + "\r\n" + 
"	" + "\r\n" + 
"	private formCode: string = 'sales_form';" + "\r\n" + 
"	// STORE FORM CODE IN SESSION" + "\r\n" + 
"	public key: string = \"formCode\";" + "\r\n" + 
"	public storage: Storage = sessionStorage;" + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private _fb: FormBuilder," + "\r\n" + 
"    private service: SalesService," + "\r\n" + 
"    private route: Router" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"	   this.storage.setItem(this.key, this.formCode);" + "\r\n" + 
"		console.log(this.storage.getItem(this.key));" + "\r\n" + 
"	  " + "\r\n" + 
"    this.entryForm = this._fb.group({" + "\r\n" 
		 );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
 	 {
 	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
 	 }
 	 
 	if(rn_Fb_linefield.getType_field().equals("textarea"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("url"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("email"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("dropdown"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("checkbox"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("togglebutton"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("datetime"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("autocomplete"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("currency_field"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("masked"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
 	
 	if(rn_Fb_linefield.getType_field().equals("contact_field"))
	 {
	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
	 }
  }
	

sbohaddtsCode.append(

"     " + "\r\n" + 
"		" + "\r\n" + 
"		 // EXTENSION" + "\r\n" + 
" extn1: [null]," + "\r\n" + 
" extn2: [null]," + "\r\n" + 
" extn3: [null]," + "\r\n" + 
" extn4: [null]," + "\r\n" + 
" extn5: [null]," + "\r\n" + 
" extn6: [null]," + "\r\n" + 
" extn7: [null]," + "\r\n" + 
" extn8: [null]," + "\r\n" + 
" extn9: [null]," + "\r\n" + 
" extn10: [null]," + "\r\n" + 
" extn11: [null]," + "\r\n" + 
" extn12: [null]," + "\r\n" + 
" extn13: [null]," + "\r\n" + 
" extn14: [null]," + "\r\n" + 
" extn15: [null]," + "\r\n" + 
" // FLEX        " + "\r\n" + 
" flex1: [null], " + "\r\n" + 
" flex2: [null], " + "\r\n" + 
" flex3: [null], " + "\r\n" + 
" flex4: [null]," + "\r\n" + 
" flex5: [null]," + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"   " + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
" " + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"    console.log(\"call on submit\", this.entryForm.value);" + "\r\n" + 
"    this.submitted = true;" + "\r\n" + 
"    if (this.entryForm.invalid) {" + "\r\n" + 
"      console.log(\"invalid\");" + "\r\n" + 
"" + "\r\n" + 
"      return;" + "\r\n" + 
"    }" + "\r\n" + 
"    this.onCreate();" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  onCreate() {" + "\r\n" + 
"" + "\r\n" + 
"    this.service.addData(this.entryForm.value).subscribe(" + "\r\n" + 
"      (data) => {" + "\r\n" + 
"        console.log(\"my dta\", data);" + "\r\n" + 
"        this.route.navigate([\"home/sales-new/all\"]);" + "\r\n" + 
"      }," + "\r\n" + 
"      (error) => {" + "\r\n" + 
"        console.log(error);" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"      });" + "\r\n" + 
"" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbohaddtsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/add/" + sbohaddts);
			System.out.println("Directory name = " + sbohaddtsFile);
			File sbohaddtsFileParentDir = new File(sbohaddtsFile.getParent());
			if(!sbohaddtsFileParentDir.exists()) {
				sbohaddtsFileParentDir.mkdirs();
			}
			if (!sbohaddtsFile.exists()) {
				sbohaddtsFile.createNewFile();
			}
			fw = new FileWriter(sbohaddtsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohaddtsCode.toString());
			bw.close();

 StringBuilder sbohallhtmlCode = new StringBuilder();
 sbohallhtmlCode.append("<div class=\"grid-pg  pad-16\">" + "\r\n" + 
"    <h3>GRID VIEW</h3>" + "\r\n" + 
"    <button id=\"add\" class=\"btn btn-primary\" (click)=\"goToAdd()\">" + "\r\n" + 
"" + "\r\n" + 
"        <clr-icon shape=\"plus\"></clr-icon>ADD" + "\r\n" + 
"    </button>" + "\r\n" + 
"  " + "\r\n" + 
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

 StringBuilder sbohallscssCode = new StringBuilder();
 sbohallscssCode.append("//@import '../../../../../assets/scss/var';" + "\r\n" + 
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

	File sbohallscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/all/" + sbohallscss);
			System.out.println("Directory name = " + sbohallscssFile);
			File sbohallscssFileParentDir = new File(sbohallscssFile.getParent());
			if(!sbohallscssFileParentDir.exists()) {
				sbohallscssFileParentDir.mkdirs();
			}
			if (!sbohallscssFile.exists()) {
				sbohallscssFile.createNewFile();
			}
			fw = new FileWriter(sbohallscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohallscssCode.toString());
			bw.close();

 StringBuilder sbohalltsCode = new StringBuilder();
 sbohalltsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"import { ExtensionService } from 'src/app/services/api/extension.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-all'," + "\r\n" + 
 " templateUrl: './"+sbohallts1+".html',"
 
+
 "  styleUrls: ['./"+sbohallts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohallts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"" + "\r\n" + 
"	@ViewChild(\"instructorById\") instructorById: TemplateRef<any>;" + "\r\n" + 
"@ViewChild(\"txId\") txId: TemplateRef<any>;" + "\r\n" + 
"@ViewChild(DatatableComponent) table: DatatableComponent;" + "\r\n" + 
"" + "\r\n" + 
"  basic: boolean = false;" + "\r\n" + 
"" + "\r\n" + 
"  columns: any[];" + "\r\n" + 
"  rows: any[];" + "\r\n" + 
"  temp = [];" + "\r\n" + 
"   extention: any" + "\r\n" + 
" fieldname: any" + "\r\n" + 
" mapping: any" + "\r\n" + 
"" + "\r\n" + 
"  filterData: string;" + "\r\n" + 
"  isLoading: boolean = false;" + "\r\n" + 
"  sales: any = [];" + "\r\n" + 
"  constructor(private router: Router," + "\r\n" + 
"    private route: ActivatedRoute," + "\r\n" + 
"    private salesNewService: SalesService," + "\r\n" + 
"	 private extentionservice: ExtensionService) { }" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.getData();" + "\r\n" + 
"    this.columns = [" + "\r\n" 
		 );
	
		 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
		 	 if(rn_Fb_linefield.getType_field()==null)
		 	 {
		 		 continue;
		 	 }
		 	
		 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
		 	 {
		 	
		 		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		 	 
		 	 if(rn_Fb_linefield.getType_field().equals("textarea"))
		 	 {
		 	
		 		 sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("url"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("email"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("dropdown"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("checkbox"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("togglebutton"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("datetime"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("autocomplete"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("currency_field"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("masked"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		  	
		  	if(rn_Fb_linefield.getType_field().equals("contact_field"))
		 	 {
		 	
		  		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
		 	 }
		 }

		 sbohalltsCode.append(

"    ];" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	  //adding extentions to array if its true" + "\r\n" + 
"		this.extentionservice.getAll().subscribe(ext => {" + "\r\n" + 
"	console.warn(`total extentions    :`, ext)" + "\r\n" + 
"	//   let id=ext.id" + "\r\n" + 
"	// console.log(\"for loop id \", id);" + "\r\n" + 
"	this.extention = ext" + "\r\n" + 
"	for (let id of this.extention) {" + "\r\n" + 
"    if (id.isActive == true) {" + "\r\n" + 
"      console.warn(id);" + "\r\n" + 
"      this.fieldname = id.field_name" + "\r\n" + 
"      this.mapping = id.mapping" + "\r\n" + 
"      console.warn(this.fieldname, this.mapping)," + "\r\n" + 
"        this.columns.push(" + "\r\n" + 
"          { prop: this.mapping, name: this.fieldname, width: 90 })" + "\r\n" + 
"  }" + "\r\n" + 
"  }" + "\r\n" + 
"})  " + "\r\n" + 
"	" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  getData() {" + "\r\n" + 
"    this.isLoading = true;" + "\r\n" + 
"    this.salesNewService.getAllData().subscribe((data) => {" + "\r\n" + 
"      console.log(`calling getall service`);" + "\r\n" + 
"" + "\r\n" + 
"      this.isLoading = false;" + "\r\n" + 
"      console.log(data);" + "\r\n" + 
"      //console.log(data.items);" + "\r\n" + 
"      this.sales = data.sales;" + "\r\n" + 
"      this.rows = this.sales;" + "\r\n" + 
"      this.temp = [...this.sales];" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToAdd() {" + "\r\n" + 
"    this.router.navigate([\"../addsales\"], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToEdit(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../update\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"  goToReadOnly(id: number) {" + "\r\n" + 
"    this.router.navigate([\"../readsales\", id], { relativeTo: this.route });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" 
);

	File sbohalltsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/all/" + sbohallts);
			System.out.println("Directory name = " + sbohalltsFile);
			File sbohalltsFileParentDir = new File(sbohalltsFile.getParent());
			if(!sbohalltsFileParentDir.exists()) {
				sbohalltsFileParentDir.mkdirs();
			}
			if (!sbohalltsFile.exists()) {
				sbohalltsFile.createNewFile();
			}
			fw = new FileWriter(sbohalltsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohalltsCode.toString());
			bw.close();

 StringBuilder sbohreadonllyhtmlCode = new StringBuilder();
 sbohreadonllyhtmlCode.append("<div class=\"read-only-pg pad-16\">" + "\r\n" + 
"    <h3>READ ONLY FORM</h3>" + "\r\n" + 
"    <br />" + "\r\n" + 
"" + "\r\n" + 
"   " + "\r\n" + 
"	<a (click)=\"goToWhoColumns()\" style=\" display: block;margin-left: 100%;\">" + "\r\n" + 
"	<clr-icon shape=\"help\"  class=\"is-solid\" size=\"32\"></clr-icon>" + "\r\n" + 
"	</a>" + "\r\n" + 
"	<clr-modal [(clrModalOpen)]=\"basic\">" + "\r\n" + 
"    <h3 class=\"modal-title\"><b>Transaction History</b></h3>" + "\r\n" + 
"    <div class=\"modal-body\">" + "\r\n" + 
"        <table s-header>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td><b>Account Id: </b></td>" + "\r\n" + 
"                <td> {{ sales.accountId }} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td><b>Created By: </b></td>" + "\r\n" + 
"                <td> {{ sales.createdBy }} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td><b>Created At: </b></td>" + "\r\n" + 
"                <td> {{ sales.createdAt | date:'medium' }} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td><b>Updated At: </b></td>" + "\r\n" + 
"                <td> {{ sales.updatedAt | date:'medium' }} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"            <tr>" + "\r\n" + 
"                <td><b>Updated By: </b></td>" + "\r\n" + 
"                <td> {{ sales.updatedBy }} </td>" + "\r\n" + 
"            </tr>" + "\r\n" + 
"        </table>" + "\r\n" + 
"        " + "\r\n" + 
"    </div>" + "\r\n" + 
"    <div class=\"modal-footer\">" + "\r\n" + 
"        <button type=\"button\" class=\"btn btn-outline\" (click)=\"basic = false\">Cancel</button>" + "\r\n" + 
"        <button type=\"button\" class=\"btn btn-primary\" (click)=\"basic = false\">Ok</button>" + "\r\n" + 
"    </div>" + "\r\n" + 
"</clr-modal>" + "\r\n" + 
"<!-- who column end -->" + "\r\n" + 
"    <br />" + "\r\n" + 
"    <table class=\"s-header\">" + "\r\n"

		 );
 
	 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
	 	 if(rn_Fb_linefield.getType_field()==null)
	 	 {
	 		 continue;
	 	 }
	 	
	 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
	 	 {
	 	
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
	 	 }
	 	 
	 	if(rn_Fb_linefield.getType_field().equals("textarea"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("url"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("email"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("dropdown"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("checkbox"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("togglebutton"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("datetime"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("autocomplete"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("currency_field"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("masked"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	 	
	 	if(rn_Fb_linefield.getType_field().equals("contact_field"))
		 {
		
	 		sbohreadonllyhtmlCode.append("        <tr>" + "\r\n" + 
	 				"            <td style=\"width:125px\">"+rn_Fb_linefield.getFieldName()+"</td>" + "\r\n" + 
	 				"            <td> {{sales."+rn_Fb_linefield.getFieldName()+"}}</td>" + "\r\n" + 
	 				"        </tr>" + "\r\n" );
		 }
	  }
		

	 sbohreadonllyhtmlCode.append(


"" + "\r\n" + 
"    </table>" + "\r\n" + 
"    <br />" + "\r\n" + 
"	" + "\r\n" + 
"		 <!-- EXTENSION FIELDS START -->" + "\r\n" + 
" <teacher-readonly-extension [teacherExtension]=\"sales\"></teacher-readonly-extension>" + "\r\n" + 
" <!-- EXTENSION FIELDS END-->" + "\r\n" + 
"" + "\r\n" + 
"           <button (click)=\"back()\" class=\"btn btn-primary\">" + "\r\n" + 
"            <clr-icon shape=\"caret left\"></clr-icon>Back" + "\r\n" + 
"        </button>" + "\r\n" + 
"    </div>" 
);

	File sbohreadonllyhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/readonly/" + sbohreadonllyhtml);
			System.out.println("Directory name = " + sbohreadonllyhtmlFile);
			File sbohreadonllyhtmlFileParentDir = new File(sbohreadonllyhtmlFile.getParent());
			if(!sbohreadonllyhtmlFileParentDir.exists()) {
				sbohreadonllyhtmlFileParentDir.mkdirs();
			}
			if (!sbohreadonllyhtmlFile.exists()) {
				sbohreadonllyhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbohreadonllyhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohreadonllyhtmlCode.toString());
			bw.close();

 StringBuilder sbohreadonlyscssCode = new StringBuilder();
 sbohreadonlyscssCode.append("//@import '../../../../../assets/scss/var';" + "\r\n" + 
".s-info-bar {" + "\r\n" + 
"    display: flex;" + "\r\n" + 
"    flex-direction: row;" + "\r\n" + 
"    justify-content: space-between;" + "\r\n" + 
"    button {" + "\r\n" + 
"        outline: none;" + "\r\n" + 
"    }" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
".read-only-pg {" + "\r\n" + 
"    width:750px;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"#who_icon {" + "\r\n" + 
"    position:absolute;" + "\r\n" + 
"    transition: .5s ease;" + "\r\n" + 
"    top: 94px;" + "\r\n" + 
"    left: 227px;" + "\r\n" + 
"}" + "\r\n" + 
"" 
);

	File sbohreadonlyscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/readonly/" + sbohreadonlyscss);
			System.out.println("Directory name = " + sbohreadonlyscssFile);
			File sbohreadonlyscssFileParentDir = new File(sbohreadonlyscssFile.getParent());
			if(!sbohreadonlyscssFileParentDir.exists()) {
				sbohreadonlyscssFileParentDir.mkdirs();
			}
			if (!sbohreadonlyscssFile.exists()) {
				sbohreadonlyscssFile.createNewFile();
			}
			fw = new FileWriter(sbohreadonlyscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohreadonlyscssCode.toString());
			bw.close();

 StringBuilder sbohreadonlytsCode = new StringBuilder();
 sbohreadonlytsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-readonly'," + "\r\n" + 
 " templateUrl: './"+sbohreadonlyts1+".html',"
 
+
 "  styleUrls: ['./"+sbohreadonlyts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohreadonlyts2+"Component implements OnInit {"
+
"   basic: boolean = false;" + "\r\n" + 
"  sales;" + "\r\n" + 
"  header_id;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, private service: SalesService, private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.header_id = this.route.snapshot.params['id'];" + "\r\n" + 
"    this.service.getDataById(this.header_id).subscribe(data => {" + "\r\n" + 
"      this.sales = data;" + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"  back() {" + "\r\n" + 
"    this.routing.navigate(['home/sales-new/all']);" + "\r\n" + 
"  }" + "\r\n" + 
"  goToWhoColumns() {" + "\r\n" + 
"    this.basic = true" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbohreadonlytsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/readonly/" + sbohreadonlyts);
			System.out.println("Directory name = " + sbohreadonlytsFile);
			File sbohreadonlytsFileParentDir = new File(sbohreadonlytsFile.getParent());
			if(!sbohreadonlytsFileParentDir.exists()) {
				sbohreadonlytsFileParentDir.mkdirs();
			}
			if (!sbohreadonlytsFile.exists()) {
				sbohreadonlytsFile.createNewFile();
			}
			fw = new FileWriter(sbohreadonlytsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohreadonlytsCode.toString());
			bw.close();

 StringBuilder sbohupdatehtmlCode = new StringBuilder();
 sbohupdatehtmlCode.append("<!-- //1 -->" + "\r\n" + 
"<div class=\"container\">" + "\r\n" + 
"    <h3 class=\"center\"><b>Sales Group Edit Form</b></h3>" + "\r\n" + 
"    <br />" + "\r\n" + 
"" + "\r\n" + 
"    <div class=\"read-only-pg pad-16\">" + "\r\n" + 
"        <h3>SALES UPDATE FORM</h3>" + "\r\n" + 
"        <br />" + "\r\n" + 
"" + "\r\n" + 
"        <section class=\"form-block\" style=\"margin-top:32px\">" + "\r\n" + 
"            <form (ngSubmit)=\"onSubmit()\">" + "\r\n" 
		 );

		 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
		 	 if(rn_Fb_linefield.getType_field()==null)
		 	 {
		 		 continue;
		 	 }

		         if(rn_Fb_linefield.getType_field().equals("section"))
			 {
		 		sbohupdatehtmlCode.append(" <div class=\"section\" style=\" background-color: #dddddd; height: 40px;\">\r\n"
						+ "    <p style=\" padding: 10px; font-size: 18px;\">"+rn_Fb_linefield.getFieldName()+"</p>\r\n"
						+ "  </div>");
			   }
		 	
		 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
		 	 	{
		 	
		 		sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
		 						 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
		 						  "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"text\""+
		 						  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
		 						  "</div>\r\n");
		 	 	}
		 	 
		 	 
		 	if(rn_Fb_linefield.getType_field().equals("textarea"))
			 {
			
		 		sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
						 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
						  "                      <textarea   rows=\"3\" cols=\"40\"	 style=\"width:fit-content;\" 	 type=\"text\""+
						  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
						  "</div>\r\n");
			 }
			
			if(rn_Fb_linefield.getType_field().equals("url"))
			 {
			
				sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
						 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
						  "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"url\""+
						  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
						  "</div>\r\n");
			 }
			
			if(rn_Fb_linefield.getType_field().equals("email"))
			 {
			
				sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
						 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
						  "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"email\""+
						  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
						  "</div>\r\n");
			 }
			
			if(rn_Fb_linefield.getType_field().equals("dropdown"))
			 {
			
				sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
						 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + 
						 "            <select   [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\"   class=\"clr-dropdown\"  >\r\n"
							+ "                <option value=\"null\">Choose ...</option>\r\n"
							+ "                <option value=\"medicine\">medicine</option>\r\n"
							+ "                <option value=\"herbals\">herbals</option>\r\n"
							+ "                <option value=\"beauty\">beauty</option>\r\n"
							+ "              </select>"	+
						  "</div>\r\n");
			 }
			
			if(rn_Fb_linefield.getType_field().equals("checkbox"))
			 {
			
				sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
						+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
						+ "                  <input type=\"checkbox\" clrCheckbox  [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
						+ "                   value=\"true \"  />\r\n"
						+ "            </div>");
			 }
			
			if(rn_Fb_linefield.getType_field().equals("togglebutton"))
			 {
			
				sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
						+ "                    <label > "+rn_Fb_linefield.getFieldName()+":</label>\r\n"
						+ "                  <input type=\"checkbox\" clrToggle  [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" \r\n"
						+ "                   value=\"true \"  />\r\n"
						+ "            </div>");
			 }
			
			if(rn_Fb_linefield.getType_field().equals("datetime"))
			{
						
							sbohaddhtmlCode.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
									+ "                <label for=\"doj\">"+rn_Fb_linefield.getFieldName()+":</label>\r\n"
									+ "                <input id=\"doj\"  type=\"date\" class=\"clr-input\"   [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" >\r\n"
									+ "            </div>");
		 } 
			
			
			 if(rn_Fb_linefield.getType_field().equals("autocomplete"))
			 {
				sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
					 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
					 			"                      <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\" autocomplete=\"on\"  "+
					 			"	[(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
					 			"</div>\r\n");
			 } 
			 
			if(rn_Fb_linefield.getType_field().equals("currency_field"))
		 {
			sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
				 			"                     <span style='font-size:25px;'>&#8377;</span> <input type=\"number\" class=\"clr-input\" \r\n"+
				 			"        		 placeholder=\"Enter  currency\" 	[(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" style=\"width: fit-content;\"/>"+
				 			"</div>\r\n");
		 } 
			 
			if(rn_Fb_linefield.getType_field().equals("contact_field"))
		 {
			sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
				 			" <input type=\"tel\" mask=\"(000) 000-0000\" style=\"width: fit-content;\" class=\"clr-input\" [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\">"+
				 			"</div>\r\n");
		 } 	
			 
			if(rn_Fb_linefield.getType_field().equals("masked"))
		 {
			sbohaddhtmlCode.append(	"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
				 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" +
				 			" <input type=\"password\" placeholder=\"Password\" style=\"width: fit-content;\" [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\"  name=\""+rn_Fb_linefield.getFieldName()+"\" class=\"clr-input\">"+
				 			"</div>\r\n");
		 } 	
		 						 	 
		 }
		 
		 sbohupdatehtmlCode.append(
"                <br>" + "\r\n" + 
"" + "\r\n" + 
"               " + "\r\n" + 
"                <button type=\"submit\" form-control class=\"btn btn-primary\">UPDATE</button>" + "\r\n" + 
"            </form>" + "\r\n" + 
"        </section>" + "\r\n" + 
"    </div>" + "\r\n" + 
"	</div>" 
);

	File sbohupdatehtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/update/" + sbohupdatehtml);
			System.out.println("Directory name = " + sbohupdatehtmlFile);
			File sbohupdatehtmlFileParentDir = new File(sbohupdatehtmlFile.getParent());
			if(!sbohupdatehtmlFileParentDir.exists()) {
				sbohupdatehtmlFileParentDir.mkdirs();
			}
			if (!sbohupdatehtmlFile.exists()) {
				sbohupdatehtmlFile.createNewFile();
			}
			fw = new FileWriter(sbohupdatehtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohupdatehtmlCode.toString());
			bw.close();

 StringBuilder sbohupdatescssCode = new StringBuilder();
 sbohupdatescssCode.append("//@import '../../../../../assets/scss/var';" + "\r\n" + 
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
".center {" + "\r\n" + 
"    text-align: center;" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  " + "\r\n" + 
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
"}" 
);

	File sbohupdatescssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/update/" + sbohupdatescss);
			System.out.println("Directory name = " + sbohupdatescssFile);
			File sbohupdatescssFileParentDir = new File(sbohupdatescssFile.getParent());
			if(!sbohupdatescssFileParentDir.exists()) {
				sbohupdatescssFileParentDir.mkdirs();
			}
			if (!sbohupdatescssFile.exists()) {
				sbohupdatescssFile.createNewFile();
			}
			fw = new FileWriter(sbohupdatescssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohupdatescssCode.toString());
			bw.close();

 StringBuilder sbohupdatetsCode = new StringBuilder();
 sbohupdatetsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"import { ActivatedRoute, Router } from '@angular/router';" + "\r\n" + 
"" + "\r\n" + 
"import { SalesService } from 'src/app/services/sales.service';" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-update'," + "\r\n" + 
 " templateUrl: './"+sbohupdatets1+".html',"
 
+
 "  styleUrls: ['./"+sbohupdatets1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohupdatets2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  header_id;" + "\r\n" + 
"  //student;" + "\r\n" + 
"" + "\r\n" + 
"  student;" + "\r\n" + 
"  constructor(private route: ActivatedRoute, private service: SalesService, private routing: Router) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.header_id = this.route.snapshot.params['id'];" + "\r\n" + 
"    //console.log(this.header_id);" + "\r\n" + 
"    this.service.getDataById(this.header_id).subscribe((data) => {" + "\r\n" + 
"      this.student = data;" + "\r\n" + 
"      " + "\r\n" + 
"" + "\r\n" + 
"    });" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  onSubmit() {" + "\r\n" + 
"" + "\r\n" + 
"    console.log(this.student);" + "\r\n" + 
"    this.service.updateData(this.header_id, this.student).subscribe(data => {" + "\r\n" + 
"      console.log(data);" + "\r\n" + 
"      this.routing.navigate(['/home/sales-new/all']);" + "\r\n" + 
"    })" + "\r\n" + 
"  }" + "\r\n" + 
"}" 
);

	File sbohupdatetsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/update/" + sbohupdatets);
			System.out.println("Directory name = " + sbohupdatetsFile);
			File sbohupdatetsFileParentDir = new File(sbohupdatetsFile.getParent());
			if(!sbohupdatetsFileParentDir.exists()) {
				sbohupdatetsFileParentDir.mkdirs();
			}
			if (!sbohupdatetsFile.exists()) {
				sbohupdatetsFile.createNewFile();
			}
			fw = new FileWriter(sbohupdatetsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohupdatetsCode.toString());
			bw.close();

 StringBuilder sbohtsCode = new StringBuilder();
 sbohtsCode.append("" + "\r\n" + 
"" + "\r\n" + 
"export class "+sbohts2+"Component implements OnInit {"
);
 
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
	 if(rn_Fb_linefield.getType_field()==null)
	 {
		 continue;
	 }
	
	 if(rn_Fb_linefield.getType_field().equals("textfield"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
	 
	 if(rn_Fb_linefield.getType_field().equals("textarea"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("url"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("email"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("dropdown"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("checkbox"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("togglebutton"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
	
	if(rn_Fb_linefield.getType_field().equals("datetime"))
	{
				
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
 } 
	
	
	 if(rn_Fb_linefield.getType_field().equals("autocomplete"))
	 {
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 } 
	 
	if(rn_Fb_linefield.getType_field().equals("currency_field"))
 {
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
 } 
	 
	if(rn_Fb_linefield.getType_field().equals("contact_field"))
 {
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
 } 	
	 
	if(rn_Fb_linefield.getType_field().equals("masked"))
 {
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
 } 	
 }
	 
 sbohtsCode.append( 

"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbohtsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/" + sbohts);
			System.out.println("Directory name = " + sbohtsFile);
			File sbohtsFileParentDir = new File(sbohtsFile.getParent());
			if(!sbohtsFileParentDir.exists()) {
				sbohtsFileParentDir.mkdirs();
			}
			if (!sbohtsFile.exists()) {
				sbohtsFile.createNewFile();
			}
			fw = new FileWriter(sbohtsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohtsCode.toString());
			bw.close();

 StringBuilder sbohnewhtmlCode = new StringBuilder();
 sbohnewhtmlCode.append("<router-outlet></router-outlet>" 
);

	File sbohnewhtmlFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/" + sbohnewhtml);
			System.out.println("Directory name = " + sbohnewhtmlFile);
			File sbohnewhtmlFileParentDir = new File(sbohnewhtmlFile.getParent());
			if(!sbohnewhtmlFileParentDir.exists()) {
				sbohnewhtmlFileParentDir.mkdirs();
			}
			if (!sbohnewhtmlFile.exists()) {
				sbohnewhtmlFile.createNewFile();
			}
			fw = new FileWriter(sbohnewhtmlFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohnewhtmlCode.toString());
			bw.close();

 StringBuilder sbohnewscssCode = new StringBuilder();
 sbohnewscssCode.append("");

	File sbohnewscssFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/" + sbohnewscss);
			System.out.println("Directory name = " + sbohnewscssFile);
			File sbohnewscssFileParentDir = new File(sbohnewscssFile.getParent());
			if(!sbohnewscssFileParentDir.exists()) {
				sbohnewscssFileParentDir.mkdirs();
			}
			if (!sbohnewscssFile.exists()) {
				sbohnewscssFile.createNewFile();
			}
			fw = new FileWriter(sbohnewscssFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohnewscssCode.toString());
			bw.close();

 StringBuilder sbohnewtsCode = new StringBuilder();
 sbohnewtsCode.append("import { Component, OnInit } from '@angular/core';" + "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-sales-new'," + "\r\n" + 
 " templateUrl: './"+sbohnewts1+".html',"
 
+
 "  styleUrls: ['./"+sbohnewts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohnewts2+"Component implements OnInit {"
+
"" + "\r\n" + 
"  constructor() { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbohnewtsFile = new File(projectPath + "/Projects/" + project_name + "/webui/src/app/pages/Module/sales-new/" + sbohnewts);
			System.out.println("Directory name = " + sbohnewtsFile);
			File sbohnewtsFileParentDir = new File(sbohnewtsFile.getParent());
			if(!sbohnewtsFileParentDir.exists()) {
				sbohnewtsFileParentDir.mkdirs();
			}
			if (!sbohnewtsFile.exists()) {
				sbohnewtsFile.createNewFile();
			}
			fw = new FileWriter(sbohnewtsFile.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(sbohnewtsCode.toString());
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