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
import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Lines;
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
import com.realnet.fnd.response.PageResponse;
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
public class Angular_SpringBoot_Mysql_form_only_header_Builder {


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
	@GetMapping(value = "/Angular_SpringBoot_Mysql_form_only_header_Builder")
	public ResponseEntity<?> build_wireframe(@RequestParam("header_id") Integer id) throws IOException {
System.out.println("First line ");

 lookUpService.createTable(id);		// HEADER VALUE
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


String sbohallts = 	ui_name +"all.component.ts";
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


String sbohupdatescss = 	ui_name +"update.component.scss";
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
					+ "import java.text.ParseException;\r\n"
					+ "import java.time.LocalDateTime;\r\n"
					+ "import java.time.format.DateTimeFormatter;\r\n"
					+ "import java.util.List;\r\n" 
					+ "\r\n" 
					+ "import javax.servlet.http.HttpServletRequest;\r\n"
					+ "import javax.validation.Valid;\r\n" + "\r\n"
					+ "import org.springframework.beans.factory.annotation.Autowired;\r\n"
					+ "import org.springframework.stereotype.Controller;\r\n"
					+ "import org.springframework.ui.ModelMap;\r\n"
					+ "import org.springframework.validation.BindingResult;\r\n"
					+ "import org.springframework.web.bind.annotation.GetMapping;\r\n"
					+ "import org.springframework.web.bind.annotation.ModelAttribute;\r\n"
					+ "import org.springframework.web.bind.annotation.PostMapping;\r\n"
					+ "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
					+ "import org.springframework.web.bind.annotation.RequestMethod;\r\n"
					+ "import org.springframework.web.bind.annotation.RequestParam;\r\n"
					+ "import org.springframework.web.servlet.ModelAndView;\r\n" + "\r\n"
					+ "import com.realnet." + module_name + ".model." + table_name_first_upper + ";\n"
					+ "import com.realnet." + module_name + ".dao." + dao_name_first_upper + ";\n"
					+ "import com.realnet." + module_name + ".service." + service_name_first_upper + ";\n" + "");

			// CONTROLLER NAME FOR EACH BUTTON TYPE
			String controllerName = method_name.substring(0, 1).toUpperCase()
					+ method_name.substring(1).concat("Controller");
			System.out.println("Niladri controllerName = " + controllerName);
			// CONTROLLER PREFIX
			cff_add_button_controller_code.append(
					cff_add_button_controller_imports + "@Controller\r\n" + "public class " + controllerName + " {\r\n" 
							+ "	@Autowired" + "	private " + dao_name_first_upper + "\t" + dao_name_lower + ";\n");

				cff_add_button_controller_code.append("	// INSERT FIELDS USING ACTION BUILDER\r\n"
						+ "@GetMapping(value = \"/" + method_name + "\")\r\n" + "	public ModelAndView " + method_name
						+ "(@RequestParam(value = \"id\") String h_id) throws IOException {\r\n"
						+ "		int hId = Integer.parseInt(h_id);\r\n"
						+ "		//System.out.println(\"JSP ID = \" + hId);\r\n" 
						+ "	// CFF_LAYOUT_CONTROLLER_START\r\n"
						+ "		System.out.println(\"PLEASE INSERT CODE... GO TO ACTION BUILDER... \");\r\n" 
						+ "	// CFF_LAYOUT_CONTROLLER_END\r\n" 
						+ "		return new ModelAndView(\"redirect:" + table_name_lower + "_update?id=\" + hId);\r\n" 
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
				header.setActionName(method_name);
				header.setType("update-table");
				header.setService_name(sbohservice1);// action name and method name is same
		   Rn_cff_ActionBuilder_Header actionbuilder=actionBuilderService.save(header);
		   
		   
		   String[] inVar = {"id","label1","label2"};
		   String[] inVarDataType = {"int","varchar","varchar"};
		   
		   for(int i=0;i<3;i++) {
			   Rn_cff_ActionBuilder_Lines actionParam=new Rn_cff_ActionBuilder_Lines();
			   actionParam.setRn_cff_actionBuilderHeader(actionbuilder);
			   actionParam.setVariableName(inVar[i]);
			   actionParam.setActionType1("in_var");
			   actionParam.setDataType(inVarDataType[i]);
			   actionParam.setSeq(i+1);
			   actionBuilderService.save(actionParam); 
		   }
		   
		   
		   Rn_cff_ActionBuilder_Lines actionLines1=new Rn_cff_ActionBuilder_Lines();
		   actionLines1.setRn_cff_actionBuilderHeader(actionbuilder);
		   actionLines1.setVariableName(sbohentity1);
		   actionLines1.setActionType1("model");
		   actionLines1.setSeq(4);
		   actionBuilderService.save(actionLines1);
		   int count=5;
		   for(Rn_Fb_Line lineList:rn_fb_lines) {
			   String fieldName=lineList.getFieldName();
			   String dataType=lineList.getDataType();
			   String typeField=lineList.getType_field();
			   
			   System.out.println("Type Field::"+typeField);
			   if(!typeField.equals("section") && !typeField.equals("id") && !typeField.equals("button")) {
				   Rn_cff_ActionBuilder_Lines actionLines=new Rn_cff_ActionBuilder_Lines();
				   actionLines.setRn_cff_actionBuilderHeader(actionbuilder);
				   actionLines.setActionType1("variable");
				   actionLines.setVariableName(fieldName);
				   actionLines.setDataType(dataType);
				   actionLines.setAssignment("null");
				   actionLines.setSeq(count);
				   actionBuilderService.save(actionLines);  
				   count++;
			   }
			   
			    
		   }
		  
		   Rn_cff_ActionBuilder_Lines actionLinesservice=new Rn_cff_ActionBuilder_Lines();
		   actionLinesservice.setRn_cff_actionBuilderHeader(actionbuilder);
		   actionLinesservice.setVariableName(sbohservice1);
		   actionLinesservice.setActionType1("service");
		   actionLinesservice.setSeq(count);
		   actionBuilderService.save(actionLinesservice);
		   
		   String[] outVar = {"label1","label2"};
		   String[] outDataType = {"varchar","varchar"};
		   
		   for(int i=0;i<outVar.length;i++) 
		   {
			   Rn_cff_ActionBuilder_Lines actionParamOut=new Rn_cff_ActionBuilder_Lines();
			   actionParamOut.setRn_cff_actionBuilderHeader(actionbuilder);
			   actionParamOut.setVariableName(outVar[i]);
			   actionParamOut.setDataType(outDataType[i]);
			   actionParamOut.setActionType1("out_var");
			   actionParamOut.setSeq(count+1+i);
			   actionBuilderService.save(actionParamOut);
		   }
		   
		   
		}
		// EXTRA BUTTON LOOP END
 StringBuilder sbohcontrollerCode = new StringBuilder();
 sbohcontrollerCode.append("package com.realnet.comm." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.HashMap;" + "\r\n" + 
"import java.util.Map;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 

"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.Module.salesnew.entity."+sbohentity1+";\r\n"+
"import com.realnet.Module.salesnew.repository."+sbohrepository1+";\r\n"+
 "import com.realnet.Module.salesnew.responce."+sbohresponce1+";\r\n"+
 "import com.realnet.Module.salesnew.service."+sbohservice1+";"+

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
"	"+sbohservice1+" saleservice;" + "\r\n" + 
"" + "\r\n" + 
"	// get all data" + "\r\n" + 
"	@ApiOperation(value = \"List of sales\", response = "+sbohresponce+".class)" + "\r\n" + 
"	@GetMapping(path = \"/getsales\")" + "\r\n" + 
"	public "+sbohresponce+" getdata(@RequestParam(value = \"page\", defaultValue = \"0\", required = false) Integer page," + "\r\n" + 
"			@RequestParam(value = \"size\", defaultValue = \"20\", required = false) Integer size) {" + "\r\n" + 
"		"+sbohresponce+" resp = new "+sbohresponce+"();" + "\r\n" + 
"		Pageable paging = PageRequest.of(page, size);" + "\r\n" + 
"		Page<"+sbohentity+"> result = this.saleservice.getlist(paging);" + "\r\n" + 
"		resp.setPageStats(result, true);" + "\r\n" + 
"		resp.setSales(result.getContent());" + "\r\n" + 
"" + "\r\n" + 
"		return resp;" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// @RequestParam(\"file\") MultipartFile file ," + "\r\n" + 
"	// add data" + "\r\n" + 
"	@ApiOperation(value = \"add a sales\", response = "+sbohentity+".class)" + "\r\n" + 
"	@PostMapping(path = \"/addsales\")" + "\r\n" + 
"	public ResponseEntity<?> savedata(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken, @Valid" + "\r\n" + 
"" + "\r\n" + 
"			@RequestBody "+sbohentity+" sale) {" + "\r\n" + 
"		// String filename=file.getOriginalFilename();" + "\r\n" + 
"		// System.err.println(filename);" + "\r\n" + 
"		// sale.setUploadprofile(filename);" + "\r\n" + 
"		//" + "\r\n" + 
"		"+sbohentity+" data = this.saleservice.createsale(sale);" + "\r\n" + 
"		if (data == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"sales not saved\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.status(HttpStatus.CREATED).body(data);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// get data by id" + "\r\n" + 
"	@ApiOperation(value = \"Get a sales\", response = "+sbohentity+".class)" + "\r\n" + 
"	@GetMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<"+sbohentity+"> getbyid(@PathVariable(\"id\") Integer id) {" + "\r\n" + 
"		"+sbohentity+" getid = this.saleservice.getid(id);" + "\r\n" + 
"		if (getid == null) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"id not found with id \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		return ResponseEntity.ok().body(getid);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// UPDATE" + "\r\n" + 
"	@ApiOperation(value = \"update a sale\", response = "+sbohentity+".class)" + "\r\n" + 
"	@PutMapping(\"/getsales/{id}\")" + "\r\n" + 
"	public ResponseEntity<"+sbohentity+"> updateTeacher(" + "\r\n" + 
"			@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authToken," + "\r\n" + 
"			@PathVariable(value = \"id\") Integer id, @Valid @RequestBody "+sbohentity+" sale) {" + "\r\n" + 
"" + "\r\n" + 
"		"+sbohentity+" updatedsale = saleservice.updateById(id, sale);" + "\r\n" + 
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

	File sbohcontrollerFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/src/main/java/com/realnet/Module/salesnew/controller/" + sbohcontroller);
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
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbohentity1 + "{"+
"	" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	@Column(name = \"sales_id\")" + "\r\n" + 
"	private int sid;" + "\r\n" + 
"	" + "\r\n" );
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
 	 {
 	
 		sbohentityCode.append("    private String "+rn_Fb_linefield.getFieldName()+";" + "\r\n" );
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
"" + "\r\n" ); 
 
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
}
 
 
sbohentityCode.append(
"	" + "\r\n" + 
"}" 
);

	File sbohentityFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/src/main/java/com/realnet/Module/salesnew/entity/" + sbohentity);
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
"import com.realnet.Module.salesnew.entity."+sbohentity1+";\r\n"+
"import com.realnet.Module.salesnew.repository."+sbohrepository1+";\r\n"+
 "import com.realnet.Module.salesnew.responce."+sbohresponce1+";\r\n"+
 "import com.realnet.Module.salesnew.service."+sbohservice1+";"+
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface "+sbohrepository1+" extends JpaRepository<"+sbohentity1+", Integer>{" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
);

	File sbohrepositoryFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/src/main/java/com/realnet/Module/salesnew/repository/" + sbohrepository);
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
"import com.realnet.fnd.response.PageResponse;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"import lombok.EqualsAndHashCode;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@EqualsAndHashCode(callSuper=false)" + "\r\n" + 
"public class " + sbohresponce1 + " extends PageResponse{"+
"	 @ApiModelProperty(required = true, value = \"\")" + "\r\n" + 
"	  private List<"+sbohentity1+"> sales;" + "\r\n" + 
"}" 
);

	File sbohresponceFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/src/main/java/com/realnet/Module/salesnew/responce/" + sbohresponce);
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
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import com.realnet.Module.salesnew.entity."+sbohentity1+";\r\n"+
"import com.realnet.Module.salesnew.repository."+sbohrepository1+";\r\n"+
 "import com.realnet.Module.salesnew.responce."+sbohresponce1+";\r\n"+
 "import com.realnet.Module.salesnew.service."+sbohservice1+";"+
"" + "\r\n" + 
"public interface " + sbohservice1 + "{"+
"" + "\r\n" + 
"    public Page<"+sbohentity1+"> getlist(Pageable page);" + "\r\n" + 
"    public "+sbohentity1+" createsale("+sbohentity1+" data);" + "\r\n" + 
"    public "+sbohentity1+" getid(int id);" + "\r\n" + 
"    public "+sbohentity1+" updateById(int id, "+sbohentity1+" sale);" + "\r\n" + 
"    public boolean deleteById(int id);" + "\r\n" + 
"    " + "\r\n" + 
"}" 
);

	File sbohserviceFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/src/main/java/com/realnet/Module/salesnew/service/" + sbohservice);
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
 sbohserviceimplCode.append("package com.realnet.Module.salesnew.service;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
		 "import org.springframework.data.domain.Page;" + "\r\n" + 
		 "import org.springframework.data.domain.Pageable;" + "\r\n" + 
		 "import org.springframework.stereotype.Service;" + "\r\n" + 
		 "" + "\r\n" + 
		 "import com.realnet.Module.salesnew.entity."+sbohentity1+";\r\n"+
		 "import com.realnet.Module.salesnew.repository."+sbohrepository1+";\r\n"+
		  "import com.realnet.Module.salesnew.responce."+sbohresponce1+";\r\n"+
		  "import com.realnet.Module.salesnew.service."+sbohservice1+";"+
		 "import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" +
		"" + "\r\n" + 
		"@Service" + "\r\n" + 
		"public class " + sbohserviceimpl1 + " implements "+sbohservice1+"{ "+
		"" + "\r\n" + 
		"	@Autowired" + "\r\n" + 
		"	"+sbohrepository1+" salesrepo;" + "\r\n" + 
		"	" + "\r\n" + 
		"" + "\r\n" + 
		"	" + "\r\n" + 
		"	//getting data" + "\r\n" + 
		"	public Page<"+sbohentity1+"> getlist(Pageable page)" + "\r\n" + 
		"	{" + "\r\n" + 
		"		Page<"+sbohentity1+"> find = this.salesrepo.findAll(page);" + "\r\n" + 
		"		return find;" + "\r\n" + 
		"	}" + "\r\n" + 
		"	" + "\r\n" + 
		"	//creating sales " + "\r\n" + 
		"	public "+sbohentity1+" createsale("+sbohentity1+" data)" + "\r\n" + 
		"	{" + "\r\n" + 
		"		"+sbohentity1+" save = this.salesrepo.save(data);" + "\r\n" + 
		"		return save;" + "\r\n" + 
		"	}" + "\r\n" + 
		"	" + "\r\n" + 
		"	//get by id" + "\r\n" + 
		"	public "+sbohentity1+" getid(int id)" + "\r\n" + 
		"	{" + "\r\n" + 
		"			" + "\r\n" + 
		"			"+sbohentity1+" teacher = salesrepo.findById(id)" + "\r\n" + 
		"					.orElseThrow(() -> new ResourceNotFoundException(\"Te not found :: \" + id));" + "\r\n" + 
		"			return teacher;" + "\r\n" + 
		"" + "\r\n" + 
		"	}" + "\r\n" + 
		"	" + "\r\n" + 
		"	//update by id" + "\r\n" + 
		"	public "+sbohentity1+" updateById(int id, "+sbohentity1+" sale) {" + "\r\n" + 
		"		"+sbohentity1+" old_sale = salesrepo.findById(id)" + "\r\n" + 
		"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
		"" + "\r\n" + 
		"" + "\r\n" + 
		"		final "+sbohentity1+" updated_sale = salesrepo.save(old_sale);" + "\r\n" + 
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
		"		"+sbohentity1+" sale = salesrepo.findById(id).orElse(null);" + "\r\n" + 
		"		salesrepo.delete(sale);" + "\r\n" + 
		"		return true;" + "\r\n" + 
		"	}" + "\r\n" + 
		"}" 
		);

	File sbohserviceimplFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/src/main/java/com/realnet/Module/salesnew/service/" + sbohserviceimpl);
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
"    <div class=\"section\">" + "\r\n" + 
"        <p> Menu Group Lines Details</p>" + "\r\n" + 
"    </div>" + "\r\n" + 
"" + "\r\n" + 
"    <section class=\"form-block\" style=\"margin-top:32px\">" + "\r\n" + 
"        <!-- entry form-->" + "\r\n" + 
"        <form [formGroup]=\"entryForm\" (ngSubmit)=\"onSubmit()\">" + "\r\n"  );
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
 	 {
 	
 		sbohaddhtmlCode.append(
 			
 			 			"<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
 			 			"                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
 			 			"                      <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
 			 			"formControlName=\""+rn_Fb_linefield.getFieldName()+"\"  placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
 			 			"</div>\r\n");
 	 			} 	
 }
  	
 sbohaddhtmlCode.append(
"" + "\r\n" + 
"" + "\r\n" + 
"            " + "\r\n" + 
"            <br>" + "\r\n" + 
"            <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"!entryForm.valid\">SUBMIT</button>" + "\r\n" + 
"        </form>" + "\r\n" + 
"    </section>" + "\r\n" + 
"" + "\r\n" + 
"</div>" 
);

	File sbohaddhtmlFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/add/" + sbohaddhtml);
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
"    color: #212529;" + "\r\n" + 
"    border: 1px solid #ced4da;" + "\r\n" + 
"    border-radius: 0.25rem;" + "\r\n" + 
"    padding: 0.75rem 0.75rem;" + "\r\n" + 
"    margin-top: 3px;" + "\r\n" + 
"    width: 100%;" + "\r\n" + 
"    margin-bottom: 10px;" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"  .section p {" + "\r\n" + 
"    //color: white;" + "\r\n" + 
"    padding: 10px;" + "\r\n" + 
"    font-size: 18px;" + "\r\n" + 
"  }" + "\r\n" + 
"  " + "\r\n" + 
".center {" + "\r\n" + 
"  text-align: center;" + "\r\n" + 
"}" 
);

	File sbohaddscssFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/add/" + sbohaddscss);
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
"export class "+sbohaddts2+" implements OnInit {"
+
"  //create a object of entry form" + "\r\n" + 
"  public entryForm: FormGroup;" + "\r\n" + 
"  submitted = false;" + "\r\n" + 
"" + "\r\n" + 
"  constructor(" + "\r\n" + 
"    private _fb: FormBuilder," + "\r\n" + 
"    private service: SalesService," + "\r\n" + 
"    private route: Router" + "\r\n" + 
"  ) { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.entryForm = this._fb.group({" + "\r\n"  );

 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
 	 {
 	
 		sbohaddtsCode.append("    "+rn_Fb_linefield.getFieldName()+":[null] ," + "\r\n" );
 	 }
  }
	

 sbohaddtsCode.append(
"     " + "\r\n" + 
"" + "\r\n" + 
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

	File sbohaddtsFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/add/" + sbohaddts);
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

	File sbohallhtmlFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/all/" + sbohallhtml);
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

	File sbohallscssFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/all/" + sbohallscss);
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
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-all'," + "\r\n" + 
 " templateUrl: './"+sbohallts1+".html',"
 
+
 "  styleUrls: ['./"+sbohallts1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohallts2+" implements OnInit {"
+
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
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
"    private salesNewService: SalesService) { }" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"    this.getData();" + "\r\n" + 
"    this.columns = [" + "\r\n"  );
	
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
 	 {
 	
 		sbohalltsCode.append("      { prop: \""+rn_Fb_linefield.getFieldName()+"\", name: \""+rn_Fb_linefield.getFieldName()+"\", width: 150 }," + "\r\n" );
 	 }
  }
	


 sbohalltsCode.append("    ];" + "\r\n" + 
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
"      this.sales = data.items;" + "\r\n" + 
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

	File sbohalltsFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/all/" + sbohallts);
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
"    <!-- <clr-tabs>" + "\r\n" + 
"        <clr-tab> -->" + "\r\n" + 
"    <!-- <clr-tab-content> -->" + "\r\n" + 
"    <br />" + "\r\n" + 
"    <table class=\"s-header\">" + "\r\n"  );
 
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
  }
	

 sbohreadonllyhtmlCode.append(
"    </table>" + "\r\n" + 
"    <br />" + "\r\n" + 
"" + "\r\n" + 
"           <button (click)=\"back()\" class=\"btn btn-primary\">" + "\r\n" + 
"            <clr-icon shape=\"caret left\"></clr-icon>Back" + "\r\n" + 
"        </button>" + "\r\n" + 
"    </div>" 
);

	File sbohreadonllyhtmlFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/readonly/" + sbohreadonllyhtml);
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

	File sbohreadonlyscssFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/readonly/" + sbohreadonlyscss);
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
"export class "+sbohreadonlyts2+" implements OnInit {"
+
"" + "\r\n" + 
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
"" + "\r\n" + 
"}" 
);

	File sbohreadonlytsFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/readonly/" + sbohreadonlyts);
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
"            <form (ngSubmit)=\"onSubmit()\">" + "\r\n"  );


 
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
 	 if(rn_Fb_linefield.getType_field()==null)
 	 {
 		 continue;
 	 }
 	
 	 if(rn_Fb_linefield.getType_field().equals("textfield"))
 	 	{
 	
 		sbohupdatehtmlCode.append( "<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"+
 						 "                  <label>" +rn_Fb_linefield.getFieldName()+ ": </label>\r\n" + "                  \r\n"+
 						  "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""+
 						  "name=\""+rn_Fb_linefield.getFieldName()+"\" [(ngModel)]=\"student."+rn_Fb_linefield.getFieldName()+"\" placeholder=\"Enter " +rn_Fb_linefield.getFieldName()+ "\" >"+
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

	File sbohupdatehtmlFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/update/" + sbohupdatehtml);
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

	File sbohupdatescssFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/update/" + sbohupdatescss);
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
"import { "+sbohts1+" } from '../Test_wireframesales';"+ "\r\n" + 
"" + "\r\n" + 
"@Component({" + "\r\n" + 
"  selector: 'app-update'," + "\r\n" + 
 " templateUrl: './"+sbohupdatets1+".html',"
 
+
 "  styleUrls: ['./"+sbohupdatets1+".scss']"
 
+
"})" + "\r\n" + 
"export class "+sbohupdatets2+" implements OnInit {"
+
"" + "\r\n" + 
"  header_id;" + "\r\n" + 
"  //student;" + "\r\n" + 
"" + "\r\n" + 
"  student: "+sbohts1+";" + "\r\n" + 
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

	File sbohupdatetsFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/update/" + sbohupdatets);
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
"export class "+sbohts1+" implements OnInit {" );
 
 for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
	 if(rn_Fb_linefield.getType_field()==null)
	 {
		 continue;
	 }
	
	 if(rn_Fb_linefield.getType_field().equals("textfield"))
	 {
	
		 sbohtsCode.append(""+rn_Fb_linefield.getFieldName()+";\n" );
	 }
 }
	 
 sbohtsCode.append( 
"" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbohtsFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/" + sbohts);
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

	File sbohnewhtmlFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/" + sbohnewhtml);
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

	File sbohnewscssFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/" + sbohnewscss);
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
"export class "+sbohnewts2+" implements OnInit {"
+
"" + "\r\n" + 
"  constructor() { }" + "\r\n" + 
"" + "\r\n" + 
"  ngOnInit(): void {" + "\r\n" + 
"  }" + "\r\n" + 
"" + "\r\n" + 
"}" 
);

	File sbohnewtsFile = new File(projectPath + "/Projects/" + project_name + "/ProjectName/webui/src/app/pages/Module/sales-new/" + sbohnewts);
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


File file1 = new File(angularProjectPath + "/ProjectName/src/main/java/com/realnet/Module/salesnew/" + module_name + "/controller/");
if(!file1.exists()) {
	file1.mkdirs();
}
File file2 = new File(angularProjectPath + "/ProjectName/src/main/java/com/realnet/Module/salesnew/" + module_name + "/entity/");
if(!file2.exists()) {
	file2.mkdirs();
}
File file3 = new File(angularProjectPath + "/ProjectName/src/main/java/com/realnet/Module/salesnew/" + module_name + "/repository/");
if(!file3.exists()) {
	file3.mkdirs();
}
File file4 = new File(angularProjectPath + "/ProjectName/src/main/java/com/realnet/Module/salesnew/" + module_name + "/responce/");
if(!file4.exists()) {
	file4.mkdirs();
}
File file5 = new File(angularProjectPath + "/ProjectName/src/main/java/com/realnet/Module/salesnew/" + module_name + "/service/");
if(!file5.exists()) {
	file5.mkdirs();
}
File file6 = new File(angularProjectPath + "/ProjectName/src/main/java/com/realnet/Module/salesnew/" + module_name + "/service/");
if(!file6.exists()) {
	file6.mkdirs();
}
File file7 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/add/" + module_name + "/");
if(!file7.exists()) {
	file7.mkdirs();
}
File file8 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/add/" + module_name + "/");
if(!file8.exists()) {
	file8.mkdirs();
}
File file9 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/add/" + module_name + "/");
if(!file9.exists()) {
	file9.mkdirs();
}
File file10 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/all/" + module_name + "/");
if(!file10.exists()) {
	file10.mkdirs();
}
File file11 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/all/" + module_name + "/");
if(!file11.exists()) {
	file11.mkdirs();
}
File file12 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/all/" + module_name + "/");
if(!file12.exists()) {
	file12.mkdirs();
}
File file13 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/readonly/" + module_name + "/");
if(!file13.exists()) {
	file13.mkdirs();
}
File file14 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/readonly/" + module_name + "/");
if(!file14.exists()) {
	file14.mkdirs();
}
File file15 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/readonly/" + module_name + "/");
if(!file15.exists()) {
	file15.mkdirs();
}
File file16 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/update/" + module_name + "/");
if(!file16.exists()) {
	file16.mkdirs();
}
File file17 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/update/" + module_name + "/");
if(!file17.exists()) {
	file17.mkdirs();
}
File file18 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/update/" + module_name + "/");
if(!file18.exists()) {
	file18.mkdirs();
}
File file19 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/" + module_name + "/");
if(!file19.exists()) {
	file19.mkdirs();
}
File file20 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/" + module_name + "/");
if(!file20.exists()) {
	file20.mkdirs();
}
File file21 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/" + module_name + "/");
if(!file21.exists()) {
	file21.mkdirs();
}
File file22 = new File(angularProjectPath + "/ProjectName/webui/src/app/pages/Module/sales-new/" + module_name + "/");
if(!file22.exists()) {
	file22.mkdirs();
}

/*-----------------------UPDATE ADMIN ROUTING TS FILE --------------------*/
		//	String frontEndDir = angularProjectPath.concat("/frontend/");
//			File adminRoutingModule = new File(projectPath+"/Projects/"+project_name+ "/webui/src/app/app-routing.module.ts");
//			File tempRoutingModule = new File(projectPath+"/Projects/"+project_name+"/webui/src/app/temp-routing.module.ts");
//
//			BufferedReader reader = new BufferedReader(new FileReader(adminRoutingModule));
//			BufferedWriter writer = new BufferedWriter(new FileWriter(tempRoutingModule));
//			String removeStr = "]}];@NgModule({imports: [RouterModule.forChild(routes)],exports: [RouterModule]})export class AdminRoutingModule{}";
//			String currentLine;
//			System.out.println(adminRoutingModule.getName());
//			while ((currentLine = reader.readLine()) != null) {
//				String trimmedLine = currentLine.trim();
//				if (trimmedLine.equals(removeStr)) {
//					currentLine = "";
//				}
//				writer.write(currentLine + System.getProperty("line.separator"));
//
//			}
//			writer.close();
//			reader.close();
//			boolean delete = adminRoutingModule.delete();
//			boolean b22 = tempRoutingModule.renameTo(adminRoutingModule);
//
//			StringBuilder admin_routing_module_string = new StringBuilder();
//			admin_routing_module_string.append("");
//			String adminRoutingModuleName = projectPath+"/Projects/"+project_name+ "/webui/src/app/app-routing.module.ts";
//
//			fw = new FileWriter(adminRoutingModuleName, true);
//			fw.write(admin_routing_module_string.toString());
//			fw.close();

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