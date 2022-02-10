
package com.realnet.fnd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.realnet.api.dao.Rn_Api_Builder_Dao;
import com.realnet.api.model.Rn_Api_Builder;
import com.realnet.bi.dao.Rn_Dashboard_Header_Dao;
import com.realnet.bi.model.Rn_Dashboard_Header;
import com.realnet.codeextractor.dao.Rn_Bcf_Rule_Library_Dao;
import com.realnet.codeextractor.model.Rn_Bcf_Technology_Stack;
import com.realnet.codeextractor.service.Rn_Bcf_Technology_Stack_Service;
import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.dao.Rn_Distributor_Details_Dao;
import com.realnet.fnd.dao.Rn_Instance_Type_Dao;
import com.realnet.fnd.dao.Rn_Project_Setup_Dao;
import com.realnet.fnd.dao.Rn_User_Registration_Dao;
import com.realnet.fnd.dao.Rn_module_setup_t_dao;
import com.realnet.fnd.model.Rn_Instance_Type;
import com.realnet.fnd.model.Rn_Patch;
import com.realnet.fnd.model.Rn_Project_Setup;
import com.realnet.fnd.model.Rn_module_setup_t;
import com.realnet.fnd.service.Rn_Project_Setup_Service;
import com.realnet.fnd.service.Rn_module_setup_t_service;

import javax.transaction.Transactional;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.realnet.qb.dao.Rn_Create_Query_Dao;
import com.realnet.qb.model.Rn_Create_Query;
import com.realnet.rb.model.Rn_Function_Register;
import com.realnet.rb.model.Rn_Report_Builder;
import com.realnet.wfb.dao.Rn_Wireframe_Dao;
import com.realnet.wfb.model.Rn_Fb_Header;
import java.text.ParseException;

@Controller
public class Rn_Project_Setup_Controller {
	@Autowired
	private HibernateConfiguration hibernateConfiguration;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private Rn_Project_Setup_Service rn_project_setup_service;

	@Autowired
	private Rn_Project_Setup_Dao rn_project_setup_dao;

	@Autowired
	private Rn_Distributor_Details_Dao rn_distributor_details_dao;

	@Autowired
	private Rn_Bcf_Technology_Stack_Service rn_bcf_technology_stack_service;

	@Autowired
	private Rn_module_setup_t_dao rn_module_setup_t_dao;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Rn_User_Registration_Dao rn_user_registration_dao;

	@Autowired
	private Rn_Api_Builder_Dao rn_api_builder_dao;

	@Autowired
	private Rn_Create_Query_Dao rn_create_query_dao;

	@Autowired
	private Environment environment;

	@Autowired
	private Rn_Dashboard_Header_Dao rn_dashboard_header_dao;

	@Autowired
	private Rn_Instance_Type_Dao rn_instance_type_dao;

	@Autowired
	private Rn_module_setup_t_service rn_module_setup_t_service;

	@Autowired
	private Rn_Bcf_Rule_Library_Dao rn_bcf_rule_library_dao;

	@Autowired
	private Rn_Wireframe_Dao rn_wireframe_dao;

	private static final Logger logger = Logger.getLogger(Rn_Project_Setup_Controller.class);

	/*
	 * @Author: Niladri Sen 22.9.20
	 */
	@Transactional
	@RequestMapping(value = "/projectCopy", method = RequestMethod.POST)
	public ModelAndView projectCopy(HttpServletRequest request, ModelAndView model) throws IOException {
		
		int project_id = Integer.parseInt(request.getParameter("from_project_id"));
		String to_project_name = request.getParameter("to_project_name");
		String to_tech_stack = request.getParameter("to_tech_stack");

		Rn_Project_Setup rn_project_setup_t = rn_project_setup_dao.copyProject(project_id);
		rn_project_setup_t.setProject_name(to_project_name);
		rn_project_setup_t.setTechnology_stack(to_tech_stack);
		// COPY PROJECT
		int new_project_id = rn_project_setup_dao.saveheader(rn_project_setup_t);

		// COPY MODULES
		List<Rn_module_setup_t> rn_module_setup_t_object = rn_project_setup_dao.copyModules(project_id);
		// int moduleOuterForloopCount = 0;
		for (Rn_module_setup_t module : rn_module_setup_t_object) {
			// int moduleInnerForloopCount = 0;
			// System.out.println("MODULE OUTER FOR = " + moduleOuterForloopCount++);
			// System.out.println("COPY FROM MODULE VALUE = \n" + module);
			int module_id = module.getId();
			module.setId(0);
			module.setProject_id(new_project_id);
			module.setTechnology_stack(to_tech_stack); // FOR NEW TECH_STACK
			int new_module_id = rn_module_setup_t_service.saveheader(module);
			// RETURN NEW GENERATED ID
			// System.out.println("NEWLY SAVED MODULE id : " + new_module_id);

			// COPY HEADER
			List<Rn_Fb_Header> rn_fb_header_object = rn_project_setup_dao.copyHeaders(project_id, module_id);
			for (Rn_Fb_Header header : rn_fb_header_object) {
				try {
					// System.out.println("MODULE INNER FOR = " + moduleInnerForloopCount++);
					// System.out.println("HEADER FOR = " + headerForloopCount++);
					int header_id = header.getId();
					header.setId(0);
					header.setModule_id(new_module_id);
					header.setProject_id(new_project_id);
					int new_header_id = rn_wireframe_dao.save(header);
					// System.out.println("new SAVED HEADER ID = " + new_header_id);
					// System.out.println("COPY HEADER VALUE = \n" + header);

					// COPY LINE
					String table_name = "RN_FB_LINES_T";
					String insert_string = "header_id, account_id, project_id, module_id, line_table_name, line_table_no, field_name, \r\n"
							+ "	mapping, data_type, type_field, form_code, key1, type1, type2, form_type, seq,\r\n"
							+ "	section_num, button_num, mandatory, hidden, readonly, dependent, dependent_on, dependent_sp, dependent_sp_param,\r\n"
							+ "	validation_1, val_type, val_sp, val_sp_param, sequence, seq_name, seq_sp, seq_sp_param,\r\n"
							+ "	default_1, default_type, default_value, default_sp, default_sp_param, calculated_field, cal_sp,\r\n"
							+ "	cal_sp_param, add_to_grid, sp_for_autocomplete, sp_for_dropdown, sp_name_for_autocomplete";

					String select_string = "" + new_header_id + ", account_id, " + new_project_id + ", " + new_module_id
							+ ", line_table_name, line_table_no, field_name, \r\n"
							+ "	mapping, data_type, type_field, form_code, key1, type1, type2, form_type, seq,\r\n"
							+ "	section_num, button_num, mandatory, hidden, readonly, dependent, dependent_on, dependent_sp, dependent_sp_param,\r\n"
							+ "	validation_1, val_type, val_sp, val_sp_param, sequence, seq_name, seq_sp, seq_sp_param,\r\n"
							+ "	default_1, default_type, default_value, default_sp, default_sp_param, calculated_field, cal_sp,\r\n"
							+ "	cal_sp_param, add_to_grid, sp_for_autocomplete, sp_for_dropdown, sp_name_for_autocomplete";

					String where_string = "AND PROJECT_ID = " + project_id + " AND MODULE_ID = " + module_id
							+ " AND HEADER_ID = " + header_id;
					rn_bcf_rule_library_dao.dynamicInsert(table_name, insert_string, select_string, where_string);
				} catch (NullPointerException e) {
					System.out.println("NullPointerException Caught");
					e.printStackTrace();
				}
			}

		}

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	@Transactional
	@RequestMapping(value = "/clone_wireframe1", method = RequestMethod.POST)
	public ModelAndView clone_wireframe(@ModelAttribute Rn_Fb_Header rn_fb_header, BindingResult resultKoel_user,
			Model model, ModelMap map, ModelAndView model1, HttpServletRequest request) {

		int header_id = rn_fb_header.getId();
		System.out.println("header_id :: " + header_id);

		HttpSession session = request.getSession();
		session.setAttribute("header_id1", header_id);

		int f_code = (Integer) request.getSession().getAttribute("header_id1");
		System.out.println("header_id1 in submit section " + f_code);

		HttpSession session0 = request.getSession(false);
		int project_id = (Integer) session0.getAttribute("project_id");

		HttpSession session1 = request.getSession(false);
		int module_id = (Integer) session1.getAttribute("module_id");

		/**
		 * For Form Builder Header Table Default Values
		 * 
		 */

//			HttpSession session_p = request.getSession(false);
//			int project_id = (Integer) session_p.getAttribute("project_id");
//
//			HttpSession session1 = request.getSession(false);
//			int module_id = (Integer) session1.getAttribute("module_id");

		String project_id1 = request.getParameter("project_id");
		System.out.println("t_stack :: " + project_id1);

		String module_id1 = request.getParameter("module_id");
		System.out.println("obj_type :: " + module_id1);

		String ui_name1 = request.getParameter("ui_name1");
		System.out.println("ui_name............" + ui_name1);

		String sql2 = "insert into rn_fb_header_t(ID, PROJECT_ID,MODULE_ID, UI_NAME, MENU_NAME, TABLE_NAME, CONVERTED_TABLE_NAME , HEADER_NAME, JSP_NAME, FORM_CODE, CONTROLLER_NAME,DAO_NAME,"
				+ " DAO_IMPL_NAME, SERVICE_NAME, SERVICE_IMPL_NAME, FORM_TYPE , LINE_TABLE_NAME, MULTILINE_TABLE_NAME) SELECT  ID IS NULL, "
				+ project_id1 + "," + module_id1 + ", '" + ui_name1
				+ "', MENU_NAME, TABLE_NAME, CONVERTED_TABLE_NAME , HEADER_NAME, JSP_NAME, FORM_CODE, CONTROLLER_NAME,DAO_NAME,"
				+ " DAO_IMPL_NAME, SERVICE_NAME, SERVICE_IMPL_NAME, FORM_TYPE , LINE_TABLE_NAME, MULTILINE_TABLE_NAME FROM RN_FB_HEADER_T WHERE  project_id="
				+ project_id + " and module_id = " + module_id + " ";

		jdbcTemplate.update(sql2);

		Rn_Fb_Header rn_fb_header1 = new Rn_Fb_Header();

		hibernateTemplate.saveOrUpdate(rn_fb_header1);

		/**
		 * Save Header id in Session
		 */

		int header_id2 = rn_fb_header1.getId();
		System.out.println("header_id :: " + header_id2);

		HttpSession session5 = request.getSession();
		session5.setAttribute("header_id1", header_id2);

		String sql1 = "insert into rn_fb_lines_t ( ID,HEADER_ID, ACCOUNT_ID, PROJECT_ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,TYPE2, FORM_TYPE,SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE ) SELECT   ID IS NULL,  "
				+ header_id2 + ", " + 0 + ", " + project_id1
				+ ",FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,TYPE2, FORM_TYPE, SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE  FROM rn_fb_lines_t WHERE project_id="
				+ project_id + "";

		jdbcTemplate.update(sql1);

//				StringBuilder sqlQuery = new StringBuilder();

//		 List<Rn_Fb_Line> rn_fb_line=rn_wireframe_dao.selectLineTableValues(header_id);
//		 String field_name=rn_fb_line.get(0).getField_name();
//		 String mapping=rn_fb_line.get(0).getMapping();
//		 String data_type=rn_fb_line.get(0).getData_type();
//		 String type_field=rn_fb_line.get(0).getType_field();
//		 int header_id1=rn_fb_line.get(0).getHeader_id();

//		 System.out.println("header_id......................"+header_id);
//		 for (int i = 0; i < rn_fb_line.size(); i++) {
//			  field_name=rn_fb_line.get(i).getField_name();
//			  mapping=rn_fb_line.get(i).getMapping();	
//			  data_type=rn_fb_line.get(i).getData_type();
//			  type_field=rn_fb_line.get(i).getType_field();
//			  header_id1=rn_fb_line.get(i).getHeader_id();	
//			  
//			  
//			  if(data_type.equals("longtext")||data_type.equals("datetime")||data_type.equals("double")){
//				  sqlQuery.append(mapping + " " + data_type + ", ");
//			  }
//			  else if(type_field.equals("textfield")||type_field.equals("checkbox")||type_field.equals("url")){
//				  sqlQuery.append(mapping + " " + type_field + ", ");
//			  }
//			  else {
//					sqlQuery.append(mapping + " " + data_type + "(45), ");
//				}
//			  }

//		 

//		String sql ="insert into rn_fb_line_t (ID,HEADER_ID, ACCOUNT_ID, PROJECT_ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,TYPE2, FORM_TYPE,"
//				+ " SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,"
//				+ "SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,"
//				+ "DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,"
//				+ "SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE )VALUES ( ID IS NULL, "+ui_name1+",ACCOUNT_ID,"+project_id1+",FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,TYPE2, FORM_TYPE,"
//						+" SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,"
//										+ "SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,"
//										+ "DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,"
//										+ "SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE),VALUES ( ID IS NULL, "+ui_name1+",ACCOUNT_ID,"+project_id1+",FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,TYPE2, FORM_TYPE,"
//												+" SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,"
//												+ "SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE,"
//												+ "DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,"
//												+ "SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE),VALUES ( ID IS NULL, "+ui_name1+",ACCOUNT_ID,"+project_id1+",FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE,KEY1,TYPE1,TYPE2, FORM_TYPE,"
//																		+" SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAM,"
//																						+ "SEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUE," 
//																						+ "DEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWN,"
//																					+ "SP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE)";
//				 + " FROM rn_fb_lines_t WHERE  HEADER_ID = "+f_code+"";

//		int header_id2 = rn_fb_header.getId();
//		System.out.println("header_id :: " + header_id2);
//
//		HttpSession session4 = request.getSession();
//		session4.setAttribute("header_id1", header_id2);
//
//		int header_id1 = (Integer) request.getSession().getAttribute("header_id1");
//		System.out.println("header_id1 in submit section " + header_id1);
//		
//		
//		
//		Rn_Fb_Line rn_fb_line= new  Rn_Fb_Line();
//	
//		String Field_name=request.getParameter("Field_name");
//		String mapping=request.getParameter("mapping");
//
//		

//		String sql ="insert into rn_fb_lines_t (ID,HEADER_ID, ACCOUNT_ID, PROJECT_ID,FIELD_NAME) select (not null ,'"+header_id+"', '0','68','"+Field_name+"'), (not null ,'"+header_id+"', '0','68','"+Field_name+"'),(not null ,'"+header_id+"', '0','68','label1'),(not null ,'"+header_id+"', '0','68','"+Field_name+"'),(not null ,'"+header_id+"', '0','68','"+Field_name+"')";  

//		String sql ="insert into rn_fb_lines_t (ID,HEADER_ID, ACCOUNT_ID, PROJECT_ID,FIELD_NAME) select not null ,"+1176+", 0,"+71+","+Field_name+" FROM rn_fb_lines_t   WHERE header_id ="+1176+"";
//		jdbcTemplate.update(sql);
//System.out.println("................"+sql);
//	

//insert into rn_fb_lines_t(ID,HEADER_ID, ACCOUNT_ID, PROJECT_ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE, LINE_TABLE_NAME, LINE_TABLE_NO, KEY1,TYPE1, TYPE2,FORM_TYPE, SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAMSEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUEDEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWNSP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE ,LINE_TABLE_NO)  select  null ,HEADER_ID, ACCOUNT_ID, PROJECT_ID,FIELD_NAME,MAPPING,DATA_TYPE,TYPE_FIELD,FORM_CODE, LINE_TABLE_NAME, LINE_TABLE_NO, KEY1,TYPE1, TYPE2,FORM_TYPE, SEQ, SECTION_NUM, BUTTON_NUM,MANDATORY,HIDDEN,READONLY,DEPENDENT,DEPENDENT_ON,DEPENDENT_SP,DEPENDENT_SP_PARAMSEQUENCE,SEQ_NAME,SEQ_SP,SEQ_SP_PARAM,VALIDATION_1,VAL_TYPE,VAL_SP,VAL_SP_PARAM,DEFAULT_1,DEFAULT_TYPE,DEFAULT_VALUEDEFAULT_SP,DEFAULT_SP_PARAM,CALCULATED_FIELD,CAL_SP,CAL_SP_PARAM,ADD_TO_GRID,SP_FOR_AUTOCOMPLETE,SP_FOR_DROPDOWNSP_NAME_FOR_DROPDOWN,SP_NAME_FOR_AUTOCOMPLETE ,LINE_TABLE_NO  FROM rn_fb_lines_t  WHERE ID IN (1836,1837,1838,1839,1840,1841,1842)
		return new ModelAndView("redirect:rn_wireframe_grid_view");

	}

	// ----------------------entry form ------------------------------------------
	@RequestMapping("/rn_project_setup_entryform")
	public ModelAndView input_form3(HttpServletRequest request, ModelMap map, Model model) {

//		List<Rn_Project_Setup> projectList = rn_project_setup_dao.userlist();
//		model.addAttribute("projectList", projectList);

		try {
			String logger_level = (String) request.getSession().getAttribute("logger_level");
			// System.out.println("logger_level is :: " + logger_level);

			if (logger_level.equals("INFO")) {
				logger.info("logger_level info message");

			} else if (logger_level.equals("WARN")) {
				logger.warn("logger_level WARN message");

			} else if (logger_level.equals("DEBUG")) {
				logger.debug("logger_level DEBUG message");

			} else if (logger_level.equals("ERROR")) {
				logger.error("logger_level ERROR message");

			}
		} catch (Exception e) {
			logger.error("Exceptions happen!", e);

		}
		return new ModelAndView("Rn_Project_Setup_EntryForm");

	}

//	// ----------------------selectActionForm------------------------------------------
//	@RequestMapping("/rn_select_action_Form")
//	public ModelAndView selectAction(@RequestParam(value = "id") String id, @RequestParam String technology_stack, HttpServletRequest request, ModelMap map) {
//		int p_id = Integer.parseInt(id);
//		System.out.println("project id=" + p_id + " Technology Stack =" + technology_stack);
//		HttpSession session = request.getSession();
//		session.setAttribute("project_id", p_id);
//		HttpSession tech_session = request.getSession();
//		tech_session.setAttribute("technology_stack", technology_stack);
//		map.addAttribute("technology_stack", technology_stack);
//		return new ModelAndView("Rn_Select_Action");
//
//		}
//		else if(logger_level.equals("WARN")){
//			logger.warn("logger_level WARN message");
//
//		}
//		else if(logger_level.equals("DEBUG")){
//			logger.debug("logger_level DEBUG message");
//
//		}
//		else if(logger_level.equals("ERROR")){
//			logger.error("logger_level ERROR message");
//
//		}
//		}catch(Exception e){
//			
//			logger.error("Exceptions happen!", e);
//			
//		}
//		return new ModelAndView("Rn_Project_Setup_EntryForm");
//		
//		
//
//	}

	// ----------------------selectActionForm------------------------------------------
	@RequestMapping("/rn_select_action_Form")
	public ModelAndView selectAction(@RequestParam(value = "mid") String id, @RequestParam(value = "pid") String pid,
			@RequestParam String technology_stack, HttpServletRequest request, ModelMap map, ModelAndView model) {

		int id1 = Integer.parseInt(id);
		System.out.println("module_id::::::::::::::::::::" + id1);

		HttpSession session1 = request.getSession();
		session1.setAttribute("module_id", id1);

		int p_id = Integer.parseInt(pid);
		System.out.println("project id" + p_id);
		HttpSession session = request.getSession();
		session.setAttribute("project_id", p_id);

		Rn_module_setup_t module = rn_module_setup_t_dao.getOneById(id1);
		String t_stack = module.getTechnology_stack();

		System.out.println("project id=" + p_id + " Technology Stack =" + t_stack);

		HttpSession tech_session = request.getSession();
		tech_session.setAttribute("technology_stack", t_stack);
		map.addAttribute("technology_stack", t_stack);

		List<Rn_Project_Setup> rn_project_values_by_id = rn_wireframe_dao.project_values_by_id(pid);
		String project_name = rn_project_values_by_id.get(0).getProject_name();
		System.out.println(project_name + " :: is project name in rn_module_setup_t_grid_view controller");
		model.addObject("rn_project_setup", rn_project_values_by_id);

		System.out.println("rn_select_action_Form");
		return new ModelAndView("Rn_Select_Action");
	}

	// ----------------------createQuery ------------------------------------------
	@RequestMapping("/createQuery")
	public ModelAndView CrateQuery(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("createQuery");
	}

	// ----------------------entry form
	// sbmit------------------------------------------
	/*
	 * @Transactional
	 * 
	 * @RequestMapping(value = "/rn_project_setup_submit", method =
	 * RequestMethod.POST) public ModelAndView saveServiceRequest(@ModelAttribute
	 * Rn_Project_Setup rn_project_setup, BindingResult resultKoel_user, ModelMap
	 * map, HttpServletRequest request) { String project_name =
	 * request.getParameter("project_name"); String description =
	 * request.getParameter("description");
	 * 
	 * String project_prefix = request.getParameter("project_prefix"); String
	 * db_name = request.getParameter("db_name"); String db_user =
	 * request.getParameter("db_user"); String db_password =
	 * request.getParameter("db_password"); String port_no =
	 * request.getParameter("port_no");
	 * 
	 * rn_project_setup.setProject_name(project_name);
	 * rn_project_setup.setDescription(description);
	 * rn_project_setup.setProject_prefix(project_prefix);
	 * rn_project_setup.setDb_name(db_name); rn_project_setup.setDb_user(db_user);
	 * rn_project_setup.setDb_password(db_password);
	 * rn_project_setup.setPort_no(port_no);
	 * 
	 * hibernateTemplate.saveOrUpdate(rn_project_setup); int project_id =
	 * Integer.parseInt(request.getParameter("project_id")); HttpSession session =
	 * request.getSession(); session.setAttribute("project_id", project_id);
	 * 
	 * 
	 * return new ModelAndView("redirect:rn_project_setup_grid_view"); }
	 */

	// ------------entry form sbmit-----------------------

	@Transactional
	@RequestMapping(value = "/rn_project_setup_submit", method = RequestMethod.POST)
	public ModelAndView saveServiceRequest(@ModelAttribute Rn_Project_Setup rn_project_setup_t,
			BindingResult resultKoel_user, ModelMap map, HttpServletRequest request) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		String project_name = request.getParameter("project_name");
		String description = request.getParameter("description");

		String project_prefix = request.getParameter("project_prefix");
		String technology_stack = request.getParameter("technology_stack");
		String db_name = request.getParameter("db_name");
		String db_user = request.getParameter("db_user");
		String db_password = request.getParameter("db_password");
		String port_no = request.getParameter("port_no");
		String copy_to = request.getParameter("copy_to");
		String db_port_no = request.getParameter("db_port_no");

		rn_project_setup_t.setProject_name(project_name);
		rn_project_setup_t.setTechnology_stack(technology_stack);
		rn_project_setup_t.setDescription(description);
		rn_project_setup_t.setProject_prefix(project_prefix);
		rn_project_setup_t.setDb_name(db_name);
		rn_project_setup_t.setDb_user(db_user);
		rn_project_setup_t.setDb_password(db_password);
		rn_project_setup_t.setPort_no(port_no);
		rn_project_setup_t.setCopy_to(copy_to);
		rn_project_setup_t.setPort_no(db_port_no);

		/*
		 * @SET tech_stack in session.
		 * 
		 * @GET tech_stack in module setup (module_setup_t_controller)
		 */
		HttpSession session = request.getSession();
		session.setAttribute("technology_stack", technology_stack);

		/*
		 * File file = new
		 * File("F:\\sonali_8_1_20\\REAL_NET_GB2\\src\\main\\java\\com\\"+project_name);
		 * boolean successful = file.mkdirs();
		 * 
		 * String source =
		 * "F:\\Ganesh\\CreatedProjectForExport\\base_source\\src\\main\\java\\com\\realnet";
		 * File srcDir = new File(source);
		 * 
		 * String destination =
		 * "F:\\sonali_8_1_20\\REAL_NET_GB2\\src\\main\\java\\com\\"+project_name; File
		 * destDir = new File(destination);
		 * 
		 * try { FileUtils.copyDirectory(srcDir, destDir); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

//		
//		File file = new File("D:\\Sonali Office\ris\\"+project_name);
//        boolean successful = file.mkdirs();
//        
//        String source = "F:\\Ganesh\\CreatedProjectForExport\\base_source";
//        File srcDir = new File(source);
//        
//        String destination = "D:\\Sonali Office\\ris\\"+project_name;
//        File destDir = new File(destination);
//
//        try {
//            FileUtils.copyDirectory(srcDir, destDir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//		
//		 System.out.println("export porjectf stat");
//         //get project id by session
//			HttpSession session = request.getSession(false);
//			int u_id = (Integer) session.getAttribute("project_id");

//			//get project details
//			List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);
//			String project_name = report.get(0).getProject_name();
//			String project_prefix = report.get(0).getProject_prefix();
//			String db_name = report.get(0).getDb_name();
//			String db_user = report.get(0).getDb_user();
//			String db_password = report.get(0).getDb_password();
//			String db_port_no = report.get(0).getPort_no();
//			

////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// create project directry
//		final String FOLDER = projectPath + "\\" + project_name;
//		File newFolder = new File(FOLDER);
//		boolean created = newFolder.mkdir();

		// DYNAMIC PATH CODE
		/*
		 * @Author : Niladri Sen
		 * 
		 * will take technology stack id as input 
		 * and create a folder named technology_stack( Ex. Spring-MVC, Angular)
		 * in BaseProject folder.
		 * 
		 * Source Path will be : D:/Office Project/ris/BaseProject/Spring-MVC/zip_file_name
		 * Destination Path : D:/Office Project/ris/Projects/project_name  || (project name comes from rn_project_setup_t table)
		 * 
		 * Uploaded project will be copied from source to destination.
		 * If application.properties file is present, The properties file will be overridden with our user given db name and password.
		 * or else create new properties file.
		 * */
		HttpSession session1 = request.getSession();
		int t_id = (Integer) session1.getAttribute("tech_stack_id");
		Rn_Bcf_Technology_Stack rn_bcf_technology_stack = rn_bcf_technology_stack_service.getOneById(t_id);
		// uploaded zip file name
		String base_prj_folder_name = rn_bcf_technology_stack.getBase_prj_file_name();
		System.out.println("Base project folder name = " + base_prj_folder_name);
		String dynamicPath = projectPath + "/BaseProject/" + technology_stack + "/" + base_prj_folder_name;
		System.out.println("Dynamic Path = " + dynamicPath);

		// add base source code
		File source = new File(dynamicPath);
		// File source = new File(projectPath + "/" + "BaseProject/ris");
		File dest = new File(projectPath + "/Projects/" + project_name);

		System.out.println("SOURCE = " + source + "\nDEST = " + dest);
		FileUtils.copyDirectory(source, dest);

		StringBuilder properties = new StringBuilder();

		properties.append("\njdbc.driverClassName = com.mysql.jdbc.Driver"
				+ "\njdbc.url = jdbc:mysql://@localhost:3306/" + db_name + "\njdbc.username = " + db_user
				+ "\njdbc.password = " + db_password + "\nhibernate.dialect = org.hibernate.dialect.MySQLDialect"
				+ "\nhibernate.show_sql = true" + "\nhibernate.format_sql = true6"
				+ "\nhibernate.use_sql_comments = true" + "\nhibernate.hbm2ddl.auto=update"
				+ "\nhibernate.enable_lazy_load_no_trans=true" + "\nprojectPath=" + projectPath);

		File file0 = new File(projectPath + "/Projects/" + project_name + "/src/main/resources/application.properties");
		File parentDir = new File(file0.getParent());
		parentDir.mkdirs();
		if (!file0.exists()) {
			file0.createNewFile();
		}
		FileWriter fw = new FileWriter(file0.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(properties.toString());
		bw.close();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

		hibernateTemplate.saveOrUpdate(rn_project_setup_t);
		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	// ------------for grid view only-------------------

	@RequestMapping(value = "/rn_project_setup_grid_view")
	public ModelAndView rn_project_setup_Details(ModelAndView model, HttpServletRequest request) throws IOException {

		List<Rn_Project_Setup> rn_project_setup = rn_project_setup_dao.userlist();
		model.addObject("rn_project_setup", rn_project_setup);

		try {
			String logger_level = (String) request.getSession().getAttribute("logger_level");
//			System.out.println("logger_level is :: " + logger_level);

			/*
			 * String copy_to=(String)request.getSession().getAttribute("copy_to");
			 * System.out.println("copy_to is :: "+copy_to);
			 * 
			 * 
			 * System.out.println("rn_project_setup_grid_view copy_to "+copy_to);
			 * HttpSession session1 = request.getSession(); session1.setAttribute("copy_to",
			 * copy_to);
			 */

			if (logger_level.equals("INFO")) {
				logger.info("logger_level info message");

			} else if (logger_level.equals("WARN")) {
				logger.warn("logger_level WARN message");

			} else if (logger_level.equals("DEBUG")) {
				logger.debug("logger_level DEBUG message");

			} else if (logger_level.equals("ERROR")) {
				logger.error("logger_level ERROR message");

			}

		} catch (Exception e) {
			logger.error("Exceptions happen!", e);

		}
		model.setViewName("Rn_Project_Setup_Grid");

		return model;
	}

	// -----------------------for prefield part-----------------------------------

	@RequestMapping(value = "/rn_project_setup_update", method = RequestMethod.GET)
	public ModelAndView loadReport1(@RequestParam(value = "id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);
		Rn_Project_Setup rn_project_setup = new Rn_Project_Setup();
		map.addAttribute("rn_project_setup", rn_project_setup);
		List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);
		map.addAttribute("rn_project_setup_update", report);
		return new ModelAndView("Rn_Project_Setup_Update");
	}

	// --------------------for
	// readonly------------------------------------------------

	@RequestMapping(value = "/rn_project_setup_readonly", method = RequestMethod.GET)
	public ModelAndView loadReport2(@RequestParam(value = "id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);
		Rn_Project_Setup rn_project_setup = new Rn_Project_Setup();
		map.addAttribute("rn_project_setup", rn_project_setup);
		List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);
		map.addAttribute("rn_project_setup_update", report);
		return new ModelAndView("Rn_Project_Setup_Readonly");
	}

	// --------------------------sbmit update
	// part---------------------------------------------------

	@RequestMapping(value = "/rn_project_setup_update_submit", method = RequestMethod.POST)
	public ModelAndView saveReportRegister(@ModelAttribute Rn_Project_Setup rn_project_setup,
			BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
		int report = 0;
		String[] id = request.getParameterValues("id");
		String[] project_name = request.getParameterValues("project_name");
		String[] technology_stack = request.getParameterValues("technology_stack");
		String[] description = request.getParameterValues("description");
		String[] project_prefix = request.getParameterValues("project_prefix");
		String[] db_name = request.getParameterValues("db_name");
		String[] db_user = request.getParameterValues("db_user");
		String[] db_password = request.getParameterValues("db_password");
		String[] port_no = request.getParameterValues("port_no");
		String[] copy_to = request.getParameterValues("copy_to");

		int rowcount = id.length;
		report = rn_project_setup_service.save(rowcount, id, project_name, technology_stack, description,
				project_prefix, db_name, db_user, db_password, port_no, copy_to);
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("report", report);
		Rn_Project_Setup rep_reg = new Rn_Project_Setup();
		map.addAttribute("rep_reg", rep_reg);
		List<Rn_Project_Setup> report_list = rn_project_setup_service.userlist();
		map.addAttribute("report_list", report_list);
		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	List<String> compileFiles = new ArrayList<String>();

	/*
	 * 
	 * @RequestMapping("/rn_project_setup_entryform") public ModelAndView
	 * newfunction(HttpServletRequest request, ModelMap map, Model model) { //
	 * System.out.println("Load"); Rn_Project_Setup rn_project_setup = new
	 * Rn_Project_Setup(); map.addAttribute("rn_project_setup", rn_project_setup);
	 * 
	 * return new ModelAndView("Rn_Project_Setup_EntryForm"); }
	 */

	@RequestMapping(value = "/loadMenus11", method = RequestMethod.GET)
	public @ResponseBody List<Rn_Project_Setup> loadMenuName11(HttpServletRequest request,
			HttpServletResponse response) {
		List<Rn_Project_Setup> menus = new ArrayList<>();
//			String json = null;
		CallableStatement cStmt;
		try {

			cStmt = hibernateConfiguration.dataSource().getConnection().prepareCall("{call RN_SP_PROJECT_NAMES()}");
			System.out.println(cStmt);
			// cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			ResultSet rs = cStmt.executeQuery();

			// ResultSet rs1 = (ResultSet) cStmt.getObject(1);
			while (rs.next()) {
				int data = rs.getInt(1);
				String data1 = rs.getString(2);
				System.out.println("data " + data);
				// System.out.println(data);
				System.out.println(data1);
				menus.add(new Rn_Project_Setup(data, data1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Rn_Project_Setup vc : menus) {
			vc.getId();
			vc.getProject_name();
			System.out.println(
					" loadMenus11 controller id :: " + vc.getId() + " Project_name ::   " + vc.getProject_name());

		}

		return menus;
	}

	@RequestMapping(value = "/loadMenus12", method = RequestMethod.GET)
	public @ResponseBody List<Rn_module_setup_t> loadMenuName12(HttpServletRequest request,
			HttpServletResponse response) {
		List<Rn_module_setup_t> menus = new ArrayList<>();
//			String json = null;
		CallableStatement cStmt;
		try {

			cStmt = hibernateConfiguration.dataSource().getConnection().prepareCall("{call RN_SP_MODULE_NAMES()}");
			System.out.println(cStmt);
			// cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			ResultSet rs = cStmt.executeQuery();

			// ResultSet rs1 = (ResultSet) cStmt.getObject(1);
			while (rs.next()) {
				int data = rs.getInt(1);
				String data1 = rs.getString(2);
				System.out.println("data " + data);
				// System.out.println(data);
				System.out.println(data1);
				menus.add(new Rn_module_setup_t(data, data1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Rn_module_setup_t vc : menus) {
			vc.getId();
			vc.getModule_name();
			System.out.println(
					" loadMenus11 controller id :: " + vc.getId() + " Module_name ::   " + vc.getModule_name());

		}

		return menus;
	}

	@RequestMapping(value = "/loadMenus13", method = RequestMethod.GET)
	public @ResponseBody List<Rn_Fb_Header> loadMenuName13(HttpServletRequest request, HttpServletResponse response) {
		List<Rn_Fb_Header> menus = new ArrayList<>();
//			String json = null;
		CallableStatement cStmt;
		try {

			cStmt = hibernateConfiguration.dataSource().getConnection().prepareCall("{call RN_SP_UI_NAMES()}");
			System.out.println(cStmt);
			// cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			ResultSet rs = cStmt.executeQuery();

			// ResultSet rs1 = (ResultSet) cStmt.getObject(1);
			while (rs.next()) {
				int data = rs.getInt(1);
				String data1 = rs.getString(2);
				System.out.println("data " + data);
				// System.out.println(data);
				System.out.println(data1);
				menus.add(new Rn_Fb_Header(data, data1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Rn_Fb_Header vc : menus) {
			vc.getId();
			vc.getUi_name();
			System.out.println(" loadMenus11 controller id :: " + vc.getId() + " UI_name ::   " + vc.getUi_name());

		}

		return menus;
	}

	@RequestMapping(value = "/loadMenus14", method = RequestMethod.GET)
	public @ResponseBody List<Rn_Fb_Header> loadMenuName14(HttpServletRequest request, HttpServletResponse response) {
		List<Rn_Fb_Header> menus = new ArrayList<>();
//			String json = null;
		CallableStatement cStmt;
		try {

			cStmt = hibernateConfiguration.dataSource().getConnection().prepareCall("{call RN_SP_HEADER_ID2()}");
			System.out.println(cStmt);
			// cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			ResultSet rs = cStmt.executeQuery();

			// ResultSet rs1 = (ResultSet) cStmt.getObject(1);
			while (rs.next()) {
				int data = rs.getInt(1);

				System.out.println("data " + data);
				// System.out.println(data);

				menus.add(new Rn_Fb_Header(data));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Rn_Fb_Header vc : menus) {
			vc.getId();

			System.out.println(" loadMenus11 controller id :: " + vc.getId() + " Project_name ::   " + vc.getUi_name());

		}

		return menus;
	}

	// ---------------------------------------export db,war and source
	// part------------------------------------------------------------------

	@RequestMapping("/exportDB")
	public ModelAndView exportDB(HttpServletRequest request, ModelMap map) throws Exception, IOException {

		String projectPath = rn_project_setup_dao.getProjectPath();

		// get project id by session
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("project_id");

		// get project details
		List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);
		String project_name = report.get(0).getProject_name();
		String project_prefix = report.get(0).getProject_prefix();

		// sql details
		List<Rn_Create_Query> queries = rn_create_query_dao.sqlDetails(u_id);

		File file = new File("F:/Ganesh/CreatedProjectForExport/DbExport/base_sql.sql");
		File toCopy = new File("F:/Ganesh/CreatedProjectForExport/DbExport/exported_sql/base_sql.sql");
		copyFile(file, toCopy);

		for (int i = 0; i < queries.size(); i++) {
			String createQuery = queries.get(i).getCreate_query();
			String table_name = queries.get(i).getTable_name();
			String database = "realnet";
			int id = queries.get(i).getId();

			StringBuilder insert_query = new StringBuilder();

			// call sp for insert data
			CallableStatement cStmt;
			String data = null;
			try {
				cStmt = hibernateConfiguration.dataSource().getConnection()
						.prepareCall("{call sp_for_insert_stmt(?,?)}");
				cStmt.setString(1, database);
				cStmt.setString(2, table_name);
				ResultSet rs = cStmt.executeQuery();

				while (rs.next()) {
					data = rs.getString(1);
					System.out.println("insert queries::" + data);
					insert_query.append(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// add content to the file
			FileWriter fr = new FileWriter(toCopy, true);
			BufferedWriter br = new BufferedWriter(fr);
			br.write(createQuery + "\n" + insert_query);
			br.close();
			fr.close();

			// rename file name
			File f1 = new File("F:/Ganesh/CreatedProjectForExport/DbExport/exported_sql/base_sql.sql");
			File f2 = new File("F:/Ganesh/CreatedProjectForExport/DbExport/exported_sql/" + project_name + ".sql");

			if (f2.exists()) {
				f2.delete();
			}

			boolean b = f1.renameTo(f2);

			// update
			String update = "UPDATE rn_create_query_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(update);

		}

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	@RequestMapping("/exportWar")
	public ModelAndView exportWar(HttpServletRequest request, ModelMap map) throws Exception, IOException {
		System.out.println("export porjectf stat");
		// get project id by session
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("project_id");

		// get project details
		List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);
		String project_name = report.get(0).getProject_name();
		String project_prefix = report.get(0).getProject_prefix();
		String db_name = report.get(0).getDb_name();
		String db_user = report.get(0).getDb_user();
		String db_password = report.get(0).getDb_password();
		String db_port_no = report.get(0).getPort_no();

		// create project directry
		final String FOLDER = "F:\\Ganesh\\CreatedProjectForExport\\" + project_name;
		File newFolder = new File(FOLDER);
		boolean created = newFolder.mkdir();
		if (created) {
			System.out.println("Folder was created !");
		} else {
			System.out.println("Unable to create folder");
		}

		// copy based project
		File source = new File("F:\\Ganesh\\MyProject\\REAL_NET_GB1\\src\\main\\java\\base_project");
		File dest = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name);
		try {
			FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("based project copied");

		// add database details to property file
		StringBuilder properties = new StringBuilder();
		properties.append("\njdbc.driverClassName = com.mysql.jdbc.Driver" + "\njdbc.url = jdbc:mysql://@localhost:"
				+ db_port_no + "/" + db_name + "\njdbc.username = " + db_user + "\njdbc.password = " + db_password
				+ "\nhibernate.dialect = org.hibernate.dialect.MySQLDialect" + "\nhibernate.show_sql = true"
				+ "\nhibernate.format_sql = true6" + "\nhibernate.use_sql_comments = true"
				+ "\nhibernate.hbm2ddl.auto=update" + "\nhibernate.enable_lazy_load_no_trans=true");

		File file1 = new File(
				"F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/classes/application.properties");
		if (!file1.exists()) {
			file1.createNewFile();
		}
		FileWriter fw = new FileWriter(file1.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(properties.toString());
		bw.close();

		// add form builder export
		formBuilderExport(u_id, project_name);

		// add report export
		reportExport(u_id, project_name);

		// add api export classes
		apiExport(u_id, project_name);

		// add BI export classes
		dashboardExport(u_id, project_name);

		/*
		 * //war builded project try {
		 * 
		 * files.add("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\resources")
		 * ;
		 * files.add("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\META-INF");
		 * files.add("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\WEB-INF");
		 * 
		 * String base = "F:\\Ganesh\\CreatedProjectForExport\\"+project_name; String
		 * dest2 = "F:\\Ganesh\\CreatedProjectForExport\\war\\" + project_name+ ".war";
		 * 
		 * zipFolder(base, dest2);
		 * 
		 * }catch(FileNotFoundException e) { e.printStackTrace(); }catch (IOException e)
		 * { e.printStackTrace(); }
		 */

		// approch 2nd
		/*
		 * String dest2 =
		 * "F:\\Ganesh\\CreatedProjectForExport\\war\\" + project_name+ ".war";
		 * List<String> files = new ArrayList<String>();
		 * files.add("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\WEB-INF");
		 * files.add("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\resources")
		 * ;
		 * files.add("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\META-INF");
		 * zipFiles(files,dest2);
		 */

		// approach 3rd prefered
		/*
		 * String OUTPUT_ZIP_FILE =
		 * "F:\\Ganesh\\CreatedProjectForExport\\war\\" + project_name+ ".war"; String
		 * SOURCE_FOLDER = "F:\\Ganesh\\CreatedProjectForExport\\"+project_name; //
		 * SourceFolder path
		 * 
		 * Path p = Files.createFile(Paths.get(OUTPUT_ZIP_FILE)); try (ZipOutputStream
		 * zs = new ZipOutputStream(Files.newOutputStream(p))) { Path pp =
		 * Paths.get(SOURCE_FOLDER); Files.walk(pp) .filter(path ->
		 * !Files.isDirectory(path)) .forEach(path -> { ZipEntry zipEntry = new
		 * ZipEntry(pp.relativize(path).toString()); try { zs.putNextEntry(zipEntry);
		 * Files.copy(path, zs); zs.closeEntry(); } catch (IOException e) {
		 * System.err.println(e); } }); }
		 */

		/*
		 * List<String> srcFiles =
		 * Arrays.asList("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\WEB-
		 * INF", "F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\resources","F:\
		 * \Ganesh\\CreatedProjectForExport\\"+project_name+"\\META-INF");
		 * FileOutputStream fos = new
		 * FileOutputStream("F:\\Ganesh\\CreatedProjectForExport\\war\\" + project_name+ "
		 * .war"); ZipOutputStream zipOut = new ZipOutputStream(fos); for (String
		 * srcFile : srcFiles) { File fileToZip = new File(srcFile); FileInputStream fis
		 * = new FileInputStream(fileToZip); ZipEntry zipEntry = new
		 * ZipEntry(fileToZip.getName()); zipOut.putNextEntry(zipEntry);
		 * 
		 * byte[] bytes = new byte[1024]; int length; while((length = fis.read(bytes))
		 * >= 0) { zipOut.write(bytes, 0, length); } fis.close(); } zipOut.close();
		 * fos.close();
		 */

		String folderToZip = "F:/Ganesh/CreatedProjectForExport/" + project_name;
		String zipName = "F:/Ganesh/CreatedProjectForExport/war/" + project_name + ".war";
		zipFolder(Paths.get(folderToZip), Paths.get(zipName));

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	private void zipFolder(Path sourceFolderPath, Path zipPath) throws Exception {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
		Files.walkFileTree(sourceFolderPath, new SimpleFileVisitor<Path>() {
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				zos.putNextEntry(new ZipEntry(sourceFolderPath.relativize(file).toString()));
				Files.copy(file, zos);
				zos.closeEntry();
				return FileVisitResult.CONTINUE;
			}
		});
		zos.close();
	}

	// export as source
	@RequestMapping("/exportSource")
	public ModelAndView exportSource(HttpServletRequest request, ModelMap map) throws Exception, IOException {

		// get project id by session
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("project_id");

		HttpSession session1 = request.getSession(false);
		int module_id = (Integer) session1.getAttribute("module_id");

		System.out.println("exportSource" + module_id);

		// get project details
		List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);

		String project_name = report.get(0).getProject_name();
		int module_id1 = report.get(0).getModule_id();
		String db_name = report.get(0).getDb_name();
		String db_user = report.get(0).getDb_user();
		String db_password = report.get(0).getDb_password();
		String db_port_no = report.get(0).getPort_no();
		System.out.println("my project name is:::" + project_name);

		/* System.out.println("exportSource.................."+module_id1); */

		// create project directry
		final String FOLDER = "D:\\Sonali Office\\ris\\" + project_name + "\\src\\main\\java\\com\\realnet\\"
				+ module_id;
		File newFolder = new File(FOLDER);
		boolean created = newFolder.mkdir();

		// add base source code
		File source = new File("F:\\Ganesh\\CreatedProjectForExport\\base_source");
		File dest = new File(
				"D:\\Sonali Office\\ris\\" + project_name + "\\src\\main\\java\\com\\realnet\\" + module_id);
		try {
			FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder properties = new StringBuilder();

		properties.append("\njdbc.driverClassName = com.mysql.jdbc.Driver" + "\njdbc.url = jdbc:mysql://@localhost:"
				+ db_port_no + "/" + db_name + "\njdbc.username = " + db_user + "\njdbc.password = " + db_password
				+ "\nhibernate.dialect = org.hibernate.dialect.MySQLDialect" + "\nhibernate.show_sql = true"
				+ "\nhibernate.format_sql = true6" + "\nhibernate.use_sql_comments = true"
				+ "\nhibernate.hbm2ddl.auto=update" + "\nhibernate.enable_lazy_load_no_trans=true");

		File file0 = new File("D:/Sonali Office/ris/" + project_name + "/src/main/resources/application.properties");
		if (!file0.exists()) {
			file0.createNewFile();
		}
		FileWriter fw = new FileWriter(file0.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(properties.toString());
		bw.close();

		// add form builder source code
		formBuilderSource(u_id, project_name);

		// add report Builder source code
		reportBuilderSource(u_id, project_name);

		// add api Builder source code
		apiBuilderSource(u_id, project_name);

		// add dashboard Builder source code
		dashboardBuilderSource(u_id, project_name);

		// config file

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	/**
	 * Exporting as source......
	 * 
	 */

	@RequestMapping("/exportFormBuilderPatch")
	public ModelAndView exportPatch(HttpServletRequest request, ModelMap map) throws Exception, IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		StringBuilder insert_query = new StringBuilder();

		// get project id by session
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("project_id");

		// get project details
		List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);
		String project_name = report.get(0).getProject_name();

		Files.walk(Paths.get(projectPath + "/src/main/java/exp_compilefiles_wip")).filter(Files::isRegularFile)
				.map(Path::toFile).forEach(File::delete);

		// form builder patch
		List<Rn_Fb_Header> rn_userlist = rn_distributor_details_dao.fb_headerlistByProjectId(u_id);
		for (int i = 0; i < rn_userlist.size(); i++) {
			int id = rn_userlist.get(i).getId();
			String table_name = rn_userlist.get(i).getTable_name();
			String controllerName = rn_userlist.get(i).getController_name();
			String daoName = rn_userlist.get(i).getDao_name();
			String daoImplName = rn_userlist.get(i).getDao_impl_name();
			String serviceName = rn_userlist.get(i).getService_name();
			String serviceImplName = rn_userlist.get(i).getService_impl_name();
			String jspName = rn_userlist.get(i).getJsp_name();

			System.out.println("table name::" + table_name + "\nid for update" + id);

			// lower
			String tableLower = table_name.toLowerCase();
			String controllerNameLower = controllerName.toLowerCase();
			String daoNameLower = daoName.toLowerCase();
			String daoImplNameLower = daoImplName.toLowerCase();
			String serviceNameLower = serviceName.toLowerCase();
			String serviceImplNameLower = serviceImplName.toLowerCase();

			// first upper
			String tableFirstUpper = tableLower.substring(0, 1).toUpperCase() + tableLower.substring(1);
			String controllerNameFirstUpper = controllerNameLower.substring(0, 1).toUpperCase()
					+ controllerNameLower.substring(1);
			String daoNameFirstUpper = daoNameLower.substring(0, 1).toUpperCase() + daoNameLower.substring(1);
			String daoImplNameFirstUpper = daoImplNameLower.substring(0, 1).toUpperCase()
					+ daoImplNameLower.substring(1);
			String serviceNameFirstUpper = serviceNameLower.substring(0, 1).toUpperCase()
					+ serviceNameLower.substring(1);
			String serviceImplNameFirstUpper = serviceImplNameLower.substring(0, 1).toUpperCase()
					+ serviceImplNameLower.substring(1);

			String moveTo = projectPath + "\\src\\main\\java\\exp_compilefiles_wip\\";

			// java files
			File file1 = new File(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + controllerNameFirstUpper + ".java");
			File file2 = new File(projectPath + "/src/main/java/com/realnet/comm/dao/" + daoNameFirstUpper + ".java");
			File file3 = new File(
					projectPath + "/src/main/java/com/realnet/comm/dao/" + daoImplNameFirstUpper + ".java");
			File file4 = new File(
					projectPath + "/src/main/java/com/realnet/comm/service/" + serviceNameFirstUpper + ".java");
			File file5 = new File(
					projectPath + "/src/main/java/com/realnet/comm/service/" + serviceImplNameFirstUpper + ".java");
			File file7 = new File(projectPath + "/src/main/java/com/realnet/comm/model/" + tableFirstUpper + ".java");

			// crud jsp
			File file6 = new File(
					projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\" + jspName + ".jsp");
			File file8 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_grid.jsp");
			File file9 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_update.jsp");
			File file10 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_readonly.jsp");

			// extension jsps
			File file15 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_extension.jsp");
			File file18 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_add_grid.jsp");
			File file19 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_add_grid2.jsp");
			File file21 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_ext_Update.jsp");
			File file22 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_ext_Readonly.jsp");

			// tiles lacaiton
			File tiles = new File(projectPath + "/src/main/webapp/WEB-INF/tiles.xml");

			// destination for copy crud jsp
			File toCopyFB1 = new File(moveTo + jspName + ".jsp");
			File toCopyFB2 = new File(moveTo + tableFirstUpper + "_grid.jsp");
			File toCopyFB3 = new File(moveTo + tableFirstUpper + "_update.jsp");
			File toCopyFB4 = new File(moveTo + tableFirstUpper + "_readonly.jsp");

			// destination for copy extension jsp
			File toCopyExt1 = new File(moveTo + tableLower + "_extension.jsp");
			File toCopyExt2 = new File(moveTo + tableLower + "_add_grid.jsp");
			File toCopyExt3 = new File(moveTo + tableLower + "_add_grid2.jsp");
			File toCopyExt4 = new File(moveTo + tableLower + "_ext_Update.jsp");
			File toCopyExt5 = new File(moveTo + tableLower + "_ext_Readonly.jsp");

			try {
				// for fb jsp
				copyFile(file6, toCopyFB1);
				copyFile(file8, toCopyFB2);
				copyFile(file9, toCopyFB3);
				copyFile(file10, toCopyFB4);

				// for ext jsp
				copyFile(file15, toCopyExt1);
				copyFile(file18, toCopyExt2);
				copyFile(file19, toCopyExt3);
				copyFile(file21, toCopyExt4);
				copyFile(file22, toCopyExt5);

				// tiles copy
				// FileUtils.copyDirectory(tiles, copyTiles);

			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("\n" + tableFirstUpper + "\n" + daoNameFirstUpper + "\n" + daoImplNameFirstUpper + "\n"
					+ serviceNameFirstUpper + "\n" + serviceImplNameFirstUpper + "\n" + controllerNameFirstUpper);

			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/model/" + tableFirstUpper + ".java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + daoNameFirstUpper + ".java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + daoImplNameFirstUpper + ".java");
			compileFiles
					.add(projectPath + "/src/main/java/com/realnet/comm/service/" + serviceNameFirstUpper + ".java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/service/" + serviceImplNameFirstUpper + ".java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + controllerNameFirstUpper + ".java");

			TomcatCompilerForPatch(project_name);

			// tilesAdd(table_name,jspName,project_name);

			String sql = "UPDATE rn_fb_header_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(sql);

			/*
			 * insert_query.append("CREATE TABLE IF NOT EXISTS `rn_patch_table_t` ("
			 * +" \n`id` int(11) NOT NULL AUTO_INCREMENT,"
			 * +" \n`filename` varchar(200) DEFAULT NULL,"
			 * +" \n`location` varchar(1000) DEFAULT NULL,"
			 * +" \n`status` varchar(45) DEFAULT NULL," +"  \nPRIMARY KEY (`id`)"
			 * +" \n) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;");
			 */

			// java files
			insert_query.append(
					"\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + tableFirstUpper + ".class', '" + project_name
							+ "', 'fb', 'model', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/model', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + daoNameFirstUpper + ".class', '" + project_name
							+ "', 'fb', 'dao', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/dao', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + daoImplNameFirstUpper + ".class', '" + project_name
							+ "', 'fb', 'dao', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/dao', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + serviceNameFirstUpper + ".class', '" + project_name
							+ "', 'fb', 'service', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/service', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + serviceImplNameFirstUpper + ".class', '" + project_name
							+ "', 'fb', 'service', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/service', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + controllerNameFirstUpper + ".class', '" + project_name
							+ "', 'fb', 'controller', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/controller', 'N');");

			// jsp files
			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + jspName + ".jsp', '" + project_name
							+ "', 'fb', 'jsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/FB_jsp', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + tableFirstUpper + "_grid.jsp', '" + project_name
							+ "', 'fb', 'jsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/FB_jsp', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + tableFirstUpper + "_update.jsp', '" + project_name
							+ "', 'fb', 'jsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/FB_jsp', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + tableFirstUpper + "_readonly.jsp', '" + project_name
							+ "', 'fb', 'jsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/FB_jsp', 'N');");

			// jsp files
			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t "
							+ "\nVALUES ('" + tableLower + "_extension.jsp', '" + project_name
							+ "', 'fb', 'extjsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/extpages', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('" + tableLower + "_add_grid.jsp', '" + project_name
							+ "', 'fb', 'extjsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/extpages', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t "
							+ "\nVALUES ('" + tableLower + "_add_grid2.jsp', '" + project_name
							+ "', 'fb', 'extjsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/extpages', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('" + tableLower + "_ext_Update.jsp', '" + project_name
							+ "', 'fb', 'extjsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/extpages', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('" + tableLower + "_ext_Readonly.jsp', '" + project_name
							+ "', 'fb', 'extjsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/extpages', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('tiles.xml', '" + project_name
							+ "', 'fb', 'tiles', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('xyz.xml', '" + project_name
							+ "', 'fb', 'dummy_tiles', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF', 'N');");

		}

		// genrate sql file
		List<Rn_Create_Query> queries = rn_create_query_dao.sqlDetails(u_id);

		File toCopy = new File(projectPath + "\\src\\main\\java\\exp_compilefiles_wip\\sqlFile.sql");

		if (!toCopy.exists()) {
			toCopy.createNewFile();

		}

		String createQuery = "";
		for (int i = 0; i < queries.size(); i++) {
			createQuery = queries.get(i).getCreate_query();
			String table_name = queries.get(i).getTable_name();
			String database = "realnet";
			int id = queries.get(i).getId();

			// call sp for insert queries and data
			CallableStatement cStmt;
			String data = null;
			try {
				cStmt = hibernateConfiguration.dataSource().getConnection()
						.prepareCall("{call sp_for_insert_stmt(?,?)}");
				cStmt.setString(1, database);
				cStmt.setString(2, table_name);
				ResultSet rs = cStmt.executeQuery();

				while (rs.next()) {
					data = rs.getString(1);
					System.out.println("insert queries::" + data);
					insert_query.append(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// update
			String update = "UPDATE rn_create_query_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(update);

		}
		// add content to the file
		FileWriter fr = new FileWriter(toCopy.getAbsoluteFile());
		BufferedWriter br = new BufferedWriter(fr);
		br.write(createQuery + "\n" + insert_query);
		br.close();
		fr.close();

		// create zip patch
		/*
		 * String base=null; try { base =
		 * "F:\\Ganesh\\MyProject\\REAL_NET_GB1\\src\\main\\java\\exp_compilefiles_wip";
		 * String dest2 =
		 * "F:\\Ganesh\\MyProject\\REAL_NET_GB1\\src\\main\\java\\exp_patchzip_wip\\"+project_name+"FbPatch
		 * .zip"; zipFolder(base, dest2); }catch(FileNotFoundException e) {
		 * e.printStackTrace(); }catch (IOException e) { e.printStackTrace(); }
		 */

		String OUTPUT_ZIP_FILE = projectPath + "\\src\\main\\java\\exp_patchzip_wip\\" + project_name + "FbPatch.zip";
		String SOURCE_FOLDER = projectPath + "\\src\\main\\java\\exp_compilefiles_wip"; // SourceFolder path

		Path p = Files.createFile(Paths.get(OUTPUT_ZIP_FILE));

		try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
			Path pp = Paths.get(SOURCE_FOLDER);
			Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
				ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
				try {
					zs.putNextEntry(zipEntry);
					Files.copy(path, zs);
					zs.closeEntry();
				} catch (IOException e) {
					System.err.println(e);
				}
			});
		}

		// File directory = new
		// File("F:\\Ganesh\\MyProject\\REAL_NET_GB1\\src\\main\\java\\ext_compilefiles_wip");
		/*
		 * File[] files = directory.listFiles(); for (File file : files) { if
		 * (file.delete()) { System.out.println("delete "+file); } }
		 */

		/*
		 * for(File file: directory.listFiles()) if (!file.isDirectory()) {
		 * file.delete(); System.out.println("file deleted"); }
		 */

		// get all the files from a directory

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	// report patch
	@RequestMapping("/exportReportPatch")
	public ModelAndView exportReportPatch(HttpServletRequest request, ModelMap map) throws Exception, IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		StringBuilder insert_query = new StringBuilder();

		// get project id by session
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("project_id");

		// get project details
		List<Rn_Project_Setup> projectDetails = rn_project_setup_service.prefield(u_id);
		String project_name = projectDetails.get(0).getProject_name();

		Files.walk(Paths.get(projectPath + "/src/main/java/exp_compilefiles_wip")).filter(Files::isRegularFile)
				.map(Path::toFile).forEach(File::delete);

		List<Rn_Report_Builder> reportDetails = rn_user_registration_dao.updateReportById(u_id);

		for (int i = 0; i < reportDetails.size(); i++) {
			String report_name = reportDetails.get(i).getReport_name();
			int id = reportDetails.get(i).getId();

			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/model/" + report_name + "_model.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/model/Rn_Report_Builder.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + report_name + "_DAO.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + report_name + "_DAOImpl.java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + report_name + "_controller.java");

			TomcatCompilerForPatch(project_name);

			File mainJsp = new File(
					projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\Report\\" + report_name + ".jsp");
			File mainJspCopy = new File(
					projectPath + "\\src\\main\\java\\exp_compilefiles_wip\\" + report_name + ".jsp");

			try {
				copyFile(mainJsp, mainJspCopy);
			} catch (IOException e) {
				e.printStackTrace();
			}

			insert_query.append("CREATE TABLE IF NOT EXISTS `rn_patch_table_t` ("
					+ " \n`id` int(11) NOT NULL AUTO_INCREMENT," + " \n`filename` varchar(200) DEFAULT NULL,"
					+ " \n`location` varchar(1000) DEFAULT NULL," + " \n`status` varchar(45) DEFAULT NULL,"
					+ "  \nPRIMARY KEY (`id`)" + " \n) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;");

			insert_query.append(
					"\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + report_name + "_model.class', '" + project_name
							+ "', 'rb', 'model', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/model', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + report_name + "_DAO.class', '" + project_name
							+ "', 'rb', 'dao', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/dao', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + report_name + "_DAOImpl.class', '" + project_name
							+ "', 'rb', 'dao', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/dao', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + report_name + "_controller.class', '" + project_name
							+ "', 'rb', 'controller', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/controller', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + report_name + ".jsp', '" + project_name
							+ "', 'rb', 'jsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/Report', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('tiles.xml', '" + project_name
							+ "', 'rb', 'tiles', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('xyz.xml', '" + project_name
							+ "', 'rb', 'dumytiles', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF', 'N');");

			String sql = "UPDATE rn_rb_reports_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(sql);
		}

		// genrate sql file
		List<Rn_Create_Query> queries = rn_create_query_dao.sqlDetails(u_id);

		File toCopy = new File(projectPath + "\\src\\main\\java\\exp_compilefiles_wip\\sqlFile.sql");

		if (!toCopy.exists()) {
			toCopy.createNewFile();

		}

		String createQuery = "";
		for (int i = 0; i < queries.size(); i++) {
			createQuery = queries.get(i).getCreate_query();
			String table_name = queries.get(i).getTable_name();
			String database = "realnet";
			int id = queries.get(i).getId();

			// call sp for insert queries and data
			CallableStatement cStmt;
			String data = null;
			try {
				cStmt = hibernateConfiguration.dataSource().getConnection()
						.prepareCall("{call sp_for_insert_stmt(?,?)}");
				cStmt.setString(1, database);
				cStmt.setString(2, table_name);
				ResultSet rs = cStmt.executeQuery();

				while (rs.next()) {
					data = rs.getString(1);
					System.out.println("insert queries::" + data);
					insert_query.append(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// update
			String update = "UPDATE rn_create_query_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(update);

		}

		// add content to the file
		FileWriter fr = new FileWriter(toCopy.getAbsoluteFile());
		BufferedWriter br = new BufferedWriter(fr);
		br.write(createQuery + "\n" + insert_query);
		br.close();
		fr.close();

		// create zip patch

		String OUTPUT_ZIP_FILE = projectPath + "\\src\\main\\java\\exp_patchzip_wip\\" + project_name
				+ "ReportPatch.zip";
		String SOURCE_FOLDER = projectPath + "\\src\\main\\java\\exp_compilefiles_wip"; // SourceFolder path

		Path p = Files.createFile(Paths.get(OUTPUT_ZIP_FILE));

		try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
			Path pp = Paths.get(SOURCE_FOLDER);
			Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
				ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
				try {
					zs.putNextEntry(zipEntry);
					Files.copy(path, zs);
					zs.closeEntry();
				} catch (IOException e) {
					System.err.println(e);
				}
			});
		}

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	public static boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return false;
				}
			}
		}
		System.out.println("removing file or directory : " + dir.getName());

		return dir.delete();
	}
	// file
	// or
	// an
	// empty
	// directory
	// System.out.println("removing
	// file
	// or
	// directory
	// :
	// "
	// +
	// dir.getName());
	// return
	// dir.delete();
	// }

	// api patch
	@RequestMapping("/exportApiPatch")
	public ModelAndView exportApiPatch(HttpServletRequest request, ModelMap map) throws Exception, IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		StringBuilder insert_query = new StringBuilder();

		// get project id by session
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("project_id");

		// get project details
		List<Rn_Project_Setup> projectDetails = rn_project_setup_service.prefield(u_id);
		String project_name = projectDetails.get(0).getProject_name();

		List<Rn_Api_Builder> apiList = rn_api_builder_dao.apiUpdateByProjectId(u_id);

		for (int i = 0; i < apiList.size(); i++) {
			int id = apiList.get(i).getId();
			String reportName = apiList.get(i).getName();
			String reportLower = reportName.toLowerCase();
			String first_upper = reportLower.substring(0, 1).toUpperCase() + reportLower.substring(1);

			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/model/" + first_upper + "_model.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + first_upper + "Api_DAO.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + first_upper + "Api_DAOImpl.java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + first_upper + "Api_controller.java");

			TomcatCompilerForPatch(project_name);

			/*
			 * insert_query.append("CREATE TABLE IF NOT EXISTS `rn_patch_table_t` ("
			 * +" \n`id` int(11) NOT NULL AUTO_INCREMENT,"
			 * +" \n`filename` varchar(200) DEFAULT NULL,"
			 * +" \n`location` varchar(1000) DEFAULT NULL,"
			 * +" \n`status` varchar(45) DEFAULT NULL," +"  \nPRIMARY KEY (`id`)" +" \n);");
			 */

			insert_query.append(
					"\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + first_upper + "_model.class', '" + project_name
							+ "', 'api', 'model', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/model', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + first_upper + "Api_DAO.class', '" + project_name
							+ "', 'api', 'dao', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/dao', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + first_upper + "Api_DAOImpl.class', '" + project_name
							+ "', 'api', 'dao', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/dao', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + first_upper + "Api_controller.class', '" + project_name
							+ "', 'api', 'controller', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/controller', 'N');");

			String sql = "UPDATE rn_create_api_header_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(sql);

			System.out.println("complete api  loop ");
		}

		// genrate sql file
		List<Rn_Create_Query> queries = rn_create_query_dao.sqlDetails(u_id);

		File toCopy = new File(projectPath + "\\src\\main\\java\\exp_compilefiles_wip\\sqlFile.sql");

		if (!toCopy.exists()) {
			toCopy.createNewFile();

		}

		System.out.println("be fore loop::" + queries);
		String createQuery = null;
		for (int i = 0; i < queries.size(); i++) {
			System.out.println("under queries loop");
			createQuery = queries.get(i).getCreate_query();
			String table_name = queries.get(i).getTable_name();
			String database = "realnet";
			int id = queries.get(i).getId();

			// call sp for insert queries and data
			CallableStatement cStmt;
			String data = null;
			try {
				cStmt = hibernateConfiguration.dataSource().getConnection()
						.prepareCall("{call sp_for_insert_stmt(?,?)}");
				cStmt.setString(1, database);
				cStmt.setString(2, table_name);
				ResultSet rs = cStmt.executeQuery();

				while (rs.next()) {
					data = rs.getString(1);
					System.out.println("insert queries::" + data);
					insert_query.append(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// update
			String update = "UPDATE rn_create_query_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(update);

		}

		// add content to the file
		FileWriter fr = new FileWriter(toCopy.getAbsoluteFile());
		BufferedWriter br = new BufferedWriter(fr);
		br.write(createQuery + "\n" + insert_query);
		br.close();
		fr.close();

		// add the content in the

		// create zip patch
		/*
		 * String base=null; try { base =
		 * "F:\\Ganesh\\MyProject\\REAL_NET_GB1\\src\\main\\java\\exp_compilefiles_wip";
		 * String dest2 =
		 * "F:\\Ganesh\\MyProject\\REAL_NET_GB1\\src\\main\\java\\exp_patchzip_wip\\"+project_name+"ApiPatch
		 * .zip"; zipFolder(base, dest2); }catch(FileNotFoundException e) {
		 * e.printStackTrace(); }catch (IOException e) { e.printStackTrace(); }
		 */

		String OUTPUT_ZIP_FILE = projectPath + "\\src\\main\\java\\exp_patchzip_wip\\" + project_name + "ApiPatch.zip";
		String SOURCE_FOLDER = projectPath + "\\src\\main\\java\\exp_compilefiles_wip"; // SourceFolder path

		Path p = Files.createFile(Paths.get(OUTPUT_ZIP_FILE));

		try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
			Path pp = Paths.get(SOURCE_FOLDER);
			Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
				ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
				try {
					zs.putNextEntry(zipEntry);
					Files.copy(path, zs);
					zs.closeEntry();
				} catch (IOException e) {
					System.err.println(e);
				}
			});
		}

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	// export bi patch
	@RequestMapping("/exportBiPatch")
	public ModelAndView exportBiPatch(HttpServletRequest request, ModelMap map) throws Exception, IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		StringBuilder insert_query = new StringBuilder();

		// get project id by session
		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("project_id");

		// get project details
		List<Rn_Project_Setup> projectDetails = rn_project_setup_service.prefield(u_id);
		String project_name = projectDetails.get(0).getProject_name();

		List<Rn_Dashboard_Header> dashDetails = rn_dashboard_header_dao.dashByProjectId(u_id);

		for (int i = 0; i < dashDetails.size(); i++) {
			int id = dashDetails.get(i).getHeader_id();
			String dash_name = dashDetails.get(i).getDashboard_name();
			String dashLower = dash_name.toLowerCase();
			String first_upper = dashLower.substring(0, 1).toUpperCase() + dashLower.substring(1);

			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + first_upper + "DashController.java");
			TomcatCompilerForPatch(project_name);

			File file2 = new File(
					projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/dashboard/" + dashLower + "Dashboard.jsp");
			// File mainJspCopy = new
			// File("F:\\Ganesh\\CreatedProjectForExport\\"+project_name+"\\WEB-INF\\tiles\\acemaster\\dashboard\\"+dashLower+"Dashboard.jsp");
			File mainJspCopy = new File(
					projectPath + "\\src\\main\\java\\exp_compilefiles_wip\\" + dashLower + "Dashboard.jsp");

			try {
				copyFile(file2, mainJspCopy);

			} catch (IOException e) {
				e.printStackTrace();
			}

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + first_upper + "DashController.class', '" + project_name
							+ ", 'bi', 'controller', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/classes/com/springmvc/controller', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status)\t"
							+ "\nVALUES ('" + dashLower + "Dashboard.jsp', '" + project_name
							+ ", 'bi', 'jsp', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF/tiles/acemaster/dashboard', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('tiles.xml', '" + project_name
							+ ", 'bi', 'tiles', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF', 'N');");

			insert_query.append(
					"\n\nINSERT INTO rn_patch_table_t (filename, project_name, object_type, comp_type, location, status) \t"
							+ "\nVALUES ('xyz.xml', '" + project_name
							+ ", 'bi', 'dumytiles', 'C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/"
							+ project_name + "/WEB-INF', 'N');");

			// tilesAddForDashboard(project_name,dashLower);

			String sql = "UPDATE rn_dashboard_header_t SET is_build='Y' WHERE header_id='" + id + "'";
			jdbcTemplate.update(sql);
		}

		// genrate sql file
		List<Rn_Create_Query> queries = rn_create_query_dao.sqlDetails(u_id);

		File toCopy = new File(projectPath + "\\src\\main\\java\\exp_compilefiles_wip\\sqlFile.sql");

		if (!toCopy.exists()) {
			toCopy.createNewFile();

		}

		String createQuery = "";
		for (int i = 0; i < queries.size(); i++) {
			createQuery = queries.get(i).getCreate_query();
			String table_name = queries.get(i).getTable_name();
			String database = "realnet";
			int id = queries.get(i).getId();

			// call sp for insert queries and data
			CallableStatement cStmt;
			String data = null;
			try {
				cStmt = hibernateConfiguration.dataSource().getConnection()
						.prepareCall("{call sp_for_insert_stmt(?,?)}");
				cStmt.setString(1, database);
				cStmt.setString(2, table_name);
				ResultSet rs = cStmt.executeQuery();

				while (rs.next()) {
					data = rs.getString(1);
					System.out.println("insert queries::" + data);
					insert_query.append(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// update
			String update = "UPDATE rn_create_query_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(update);

		}

		// add content to the file
		FileWriter fr = new FileWriter(toCopy.getAbsoluteFile());
		BufferedWriter br = new BufferedWriter(fr);
		br.write(createQuery + "\n" + insert_query);
		br.close();
		fr.close();

		// create zip patch

		String OUTPUT_ZIP_FILE = projectPath + "\\src\\main\\java\\exp_patchzip_wip\\" + project_name + "BIPatch.zip";
		String SOURCE_FOLDER = projectPath + "\\src\\main\\java\\exp_compilefiles_wip"; // SourceFolder path

		Path p = Files.createFile(Paths.get(OUTPUT_ZIP_FILE));

		try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
			Path pp = Paths.get(SOURCE_FOLDER);
			Files.walk(pp).filter(path -> !Files.isDirectory(path)).forEach(path -> {
				ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
				try {
					zs.putNextEntry(zipEntry);
					Files.copy(path, zs);
					zs.closeEntry();
				} catch (IOException e) {
					System.err.println(e);
				}
			});
		}

		return new ModelAndView("redirect:rn_project_setup_grid_view");
	}

	// import patch
	@RequestMapping(value = "/importPatch", method = RequestMethod.GET)
	public ModelAndView importPatch(HttpServletRequest request, ModelAndView model) throws Exception, IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		String filename = request.getParameter("filename");
		System.out.println("file name ::" + filename);

		String src = projectPath + "\\src\\main\\java\\patch_import\\" + filename + "";
		String dest = projectPath + "\\src\\main\\java\\patch_import_wip\\";
		unzip2(src, dest);

		System.out.println("unzip done");

		File file = new File(projectPath + "\\src\\main\\java\\patch_import\\" + filename);

		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}

		System.out.println("zip file delted done");

		/*
		 * ----------------------------execute sql
		 * file----------------------------------------------
		 */

		String sqlFile = projectPath + "/src/main/java/patch_import_wip/sqlFile.sql";
		String URL = "jdbc:mysql://@localhost:3306/realnet";
		String USER = "test123";
		String PASSWORD = "root123";

		String s = new String();
		StringBuffer sb = new StringBuffer();
		String DRIVER_NAME = "com.mysql.jdbc.Driver";

		try {
			Class.forName(DRIVER_NAME).newInstance();
			System.out.println("*** Driver loaded");

			FileReader fr = new FileReader(new File(sqlFile));
			BufferedReader br = new BufferedReader(fr);

			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			br.close();

			String[] inst = sb.toString().split(";");

			Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st = c.createStatement();

			for (int i = 0; i < inst.length; i++) {

				if (!inst[i].trim().equals("")) {
					st.executeUpdate(inst[i]);
					System.out.println(">>" + inst[i]);
				}
			}

		} catch (Exception e) {
			System.out.println("*** Error : " + e.toString());
			System.out.println("*** ");
			System.out.println("*** Error : ");
			e.printStackTrace();
			System.out.println("################################################");
			System.out.println(sb.toString());
		}

		System.out.println("sql file executed done");

		/*
		 * ----------------------------patch details and transfer
		 * files----------------------------------------------
		 */
		List<Rn_Patch> patchDetails = rn_project_setup_dao.patchDetails();

		StringBuilder tiles = new StringBuilder();
		String tilesPath = null;
		String dumyPath = null;
		// String object_type1=patchDetails.get(0).getObject_type();

		for (int i = 0; i < patchDetails.size(); i++) {
			String object_type = patchDetails.get(i).getObject_type();
			String name = patchDetails.get(i).getFilename();
			String name2 = patchDetails.get(i).getFilename();
			String location = patchDetails.get(i).getLocation() + "/";
			String status = patchDetails.get(i).getStatus();

			int id = patchDetails.get(i).getId();

			if (object_type.equals("fb")) {

				if (i == 0) {
					System.out.println("under if::" + i);
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/model/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);
				}

				if (i == 1) {

					System.out.println("under if::" + i);
					File model1 = new File(projectPath + "\\src\\main\\java\\patch_import_wip\\com\\realnet/comm\\dao");
					System.out.println("form file ::" + model1);
					File cp1 = new File(
							"C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\TestProject\\WEB-INF\\classes\\com\\realnet\\comm\\dao");
					System.out.println("to file  ::" + cp1);
					// copyFile(model1, cp1);
					try {
						FileUtils.copyDirectory(model1, cp1);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				if (i == 3 || i == 4) {
					System.out.println("under if::" + i);
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/service/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);
				}

				if (i == 5) {
					System.out.println("under if::" + i);
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/controller/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);
				}

				if (i == 6 || i == 7 || i == 8 || i == 9) {
					System.out.println("under if::" + i);
					File model1 = new File(projectPath + "/src/main/java/patch_import_wip/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);

					String NewString = name2.replaceAll(".jsp", "");

					System.out.println("new string value ==" + NewString);

					tiles.append("\n<definition name=\"" + NewString + "\" extends=\"acemaster.definition\">"
							+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
							+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/" + name + "\"/>"
							+ "\n</definition>");

				}

				if (i == 10 || i == 11 || i == 12 || i == 13 || i == 14) {
					System.out.println("under if::" + i);
					File model1 = new File(projectPath + "/src/main/java/patch_import_wip/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);
				}

				if (i == 15) {
					System.out.println("under if::" + i + "\n" + location + name);
					tilesPath = location + name;
				}

				if (i == 16) {
					System.out.println("under if::" + i + "\n" + location + name);
					dumyPath = location + name;
				}

				String sql = "UPDATE rn_patch_table_t SET status='Y' where id='" + id + "'";
				jdbcTemplate.update(sql);

			} // form builder if

			if (object_type.equals("rb")) {

				if (i == 0) {
					System.out.println("under if::" + i);
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/model/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);
				}

				if (i == 1) {

					System.out.println("under if::" + i);
					File model1 = new File(
							projectPath + "\\src\\main\\java\\patch_import_wip\\com\\realnet\\comm\\dao");
					System.out.println("form file ::" + model1);
					File cp1 = new File(
							"C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\TestProject\\WEB-INF\\classes\\com\\realnet\\comm\\dao");
					System.out.println("to file  ::" + cp1);
					// copyFile(model1, cp1);
					try {
						FileUtils.copyDirectory(model1, cp1);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				if (i == 3) {
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/controller/" + name);
					File cp1 = new File(location + name);
					copyFile(model1, cp1);
				}

				if (i == 4) {
					System.out.println("under if::" + i);
					File model1 = new File(projectPath + "/src/main/java/patch_import_wip/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);

					String NewString = name2.replaceAll(".jsp", "");

					System.out.println("new string value ==" + NewString);

					tiles.append("\n<definition name=\"" + NewString + "\" extends=\"acemaster.definition\">"
							+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
							+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/Report/" + name + "\"/>"
							+ "\n</definition>");

				}

				if (i == 5) {
					System.out.println("under if::" + i + "\n" + location + name);
					tilesPath = location + name;
				}

				if (i == 6) {
					System.out.println("under if::" + i + "\n" + location + name);
					dumyPath = location + name;
				}

				String sql = "UPDATE rn_patch_table_t SET status='Y' where id='" + id + "'";
				jdbcTemplate.update(sql);

			} // report builder

			if (object_type.equals("api")) {
				if (i == 0) {
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/model/" + name);
					File cp1 = new File(location + name);
					copyFile(model1, cp1);
				}

				if (i == 1) {
					File model1 = new File(projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/dao");
					File cp1 = new File(location);
					try {
						FileUtils.copyDirectory(model1, cp1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (i == 3) {
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/controller/" + name);
					File cp1 = new File(location + name);
					copyFile(model1, cp1);
				}

				String sql = "UPDATE rn_patch_table_t SET status='Y' where id='" + id + "'";
				jdbcTemplate.update(sql);

			} // api builder

			if (object_type.equals("bi")) {

				if (i == 0) {
					File model1 = new File(
							projectPath + "/src/main/java/patch_import_wip/com/realnet/comm/controller/" + name);
					File cp1 = new File(location + name);
					copyFile(model1, cp1);
				}

				if (i == 1) {
					System.out.println("under if::" + i);
					File model1 = new File(projectPath + "/src/main/java/patch_import_wip/" + name);
					System.out.println("form file ::" + model1);
					File cp1 = new File(location + name);
					System.out.println("to file  ::" + cp1);
					copyFile(model1, cp1);

					String NewString = name2.replaceAll(".jsp", "");

					System.out.println("new string value ==" + NewString);

					tiles.append("\n<definition name=\"" + NewString + "\" extends=\"acemaster.definition\">"
							+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
							+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/dashboard/" + name
							+ "\"/>" + "\n</definition>");
				}

				if (i == 2) {
					System.out.println("under if::" + i + "\n" + location + name);
					tilesPath = location + name;
				}

				if (i == 3) {
					System.out.println("under if::" + i + "\n" + location + name);
					dumyPath = location + name;
				}
				String sql = "UPDATE rn_patch_table_t SET status='Y' where id='" + id + "'";
				jdbcTemplate.update(sql);

			} // bi builder

		} // for loop

		// add tiles
		tilesForPatch(tiles, tilesPath, dumyPath);

		return new ModelAndView("redirect:importPatchList");
	}

	void tilesForPatch(StringBuilder tiles, String tilesPath, String dumyPath) throws IOException {
		System.out.println("tiles path::" + tilesPath);

		if (tilesPath != null) {
			tiles.append("\n</tiles-definitions>");
			File temp = new File(tilesPath);
			File newtemp = new File(dumyPath);
			BufferedReader br = new BufferedReader(new FileReader(temp));
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
			String removeStr = "</tiles-definitions>";
			String currentLine;
			System.out.println(temp.getName());
			while ((currentLine = br.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				if (trimmedLine.equals(removeStr)) {
					currentLine = "";
				}
				bw1.write(currentLine + System.getProperty("line.separator"));

			}
			bw1.close();
			br.close();
			boolean delete = temp.delete();
			boolean b = newtemp.renameTo(temp);
			try {
				String tfilename = tilesPath;

				FileWriter fw = new FileWriter(tfilename, true);
				fw.write(tiles.toString());
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// zip patch list
	@RequestMapping("/importPatchList")
	public ModelAndView importPatchList(HttpServletRequest request, ModelAndView model) throws Exception, IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		File directory = new File(projectPath + "\\src\\main\\java\\patch_import");
		// get all the files from a directory
		File[] fList = directory.listFiles();

		ArrayList filPaths = new ArrayList();
		for (File file : fList) {
			System.out.println(file.getName());
			filPaths.add(file.getName());
		}

		model.addObject("fList", filPaths);

		model.setViewName("ZipFileList");
		return model;
	}

	// form builder source code
	void formBuilderSource(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		System.out.println("builder method ");
		System.out.println("  \n project id ::" + u_id);

		List<Rn_Fb_Header> rn_userlist = rn_distributor_details_dao.fb_headerlistByProjectId(u_id);

		for (int i = 0; i < rn_userlist.size(); i++) {
			System.out.println("undr for loop  ::");
			int id = rn_userlist.get(i).getId();
			String table_name = rn_userlist.get(i).getTable_name();
			String controllerName = rn_userlist.get(i).getController_name();
			String daoName = rn_userlist.get(i).getDao_name();
			String daoImplName = rn_userlist.get(i).getDao_impl_name();
			String serviceName = rn_userlist.get(i).getService_name();
			String serviceImplName = rn_userlist.get(i).getService_impl_name();
			String jspName = rn_userlist.get(i).getJsp_name();

			System.out.println("table name::" + table_name + "\nid for update" + id);

			// lower
			String tableLower = table_name.toLowerCase();
			String controllerNameLower = controllerName.toLowerCase();
			String daoNameLower = daoName.toLowerCase();
			String daoImplNameLower = daoImplName.toLowerCase();
			String serviceNameLower = serviceName.toLowerCase();
			String serviceImplNameLower = serviceImplName.toLowerCase();

			// first upper
			String tableFirstUpper = tableLower.substring(0, 1).toUpperCase() + tableLower.substring(1);
			String controllerNameFirstUpper = controllerNameLower.substring(0, 1).toUpperCase()
					+ controllerNameLower.substring(1);
			String daoNameFirstUpper = daoNameLower.substring(0, 1).toUpperCase() + daoNameLower.substring(1);
			String daoImplNameFirstUpper = daoImplNameLower.substring(0, 1).toUpperCase()
					+ daoImplNameLower.substring(1);
			String serviceNameFirstUpper = serviceNameLower.substring(0, 1).toUpperCase()
					+ serviceNameLower.substring(1);
			String serviceImplNameFirstUpper = serviceImplNameLower.substring(0, 1).toUpperCase()
					+ serviceImplNameLower.substring(1);

			// java files
			File file1 = new File(projectPath + "\\src\\main\\java\\com\\realnet\\comm\\controller\\"
					+ controllerNameFirstUpper + ".java");
			File file2 = new File(
					projectPath + "\\src\\main\\java\\com\\realnet\\comm\\dao\\" + daoNameFirstUpper + ".java");
			File file3 = new File(
					projectPath + "\\src\\main\\java\\com\\realnet\\comm\\dao\\" + daoImplNameFirstUpper + ".java");
			File file4 = new File(
					projectPath + "\\src\\main\\java\\com\\realnet\\comm\\service\\" + serviceNameFirstUpper + ".java");
			File file5 = new File(projectPath + "\\src\\main\\java\\com\\realnet\\comm\\service\\"
					+ serviceImplNameFirstUpper + ".java");
			File file7 = new File(
					projectPath + "\\src\\main\\java\\com\\realnet\\comm\\model\\" + tableFirstUpper + ".java");

			// crud jsp
			File file6 = new File(
					projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\" + jspName + ".jsp");
			File file8 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_grid.jsp");
			File file9 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_update.jsp");
			File file10 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_readonly.jsp");

			// extension jsps
			File file15 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_extension.jsp");
			File file18 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_add_grid.jsp");
			File file19 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_add_grid2.jsp");
			File file21 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_ext_Update.jsp");
			File file22 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_ext_Readonly.jsp");

			String javaBasePath = "F:\\Ganesh\\CreatedProjectForExport\\ExportSource\\" + project_name
					+ "\\src\\main\\java\\com\\realnet\\comm";
			String jspBasePath = "F:\\Ganesh\\CreatedProjectForExport\\ExportSource\\" + project_name
					+ "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp";
			String extJspBasePath = "F:\\Ganesh\\CreatedProjectForExport\\ExportSource\\" + project_name
					+ "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages";

			// destination for java files
			File copy1 = new File(javaBasePath + "\\controller\\" + controllerNameFirstUpper + ".java");
			File copy2 = new File(javaBasePath + "\\dao\\" + daoNameFirstUpper + ".java");
			File copy3 = new File(javaBasePath + "\\dao\\" + daoImplNameFirstUpper + ".java");
			File copy4 = new File(javaBasePath + "\\service\\" + serviceNameFirstUpper + ".java");
			File copy5 = new File(javaBasePath + "\\service\\" + serviceImplNameFirstUpper + ".java");
			File copy7 = new File(javaBasePath + "\\model\\" + tableFirstUpper + ".java");

			// destination for copy crud jsp
			File toCopyFB1 = new File(jspBasePath + "\\" + jspName + ".jsp");
			File toCopyFB2 = new File(jspBasePath + "\\" + tableFirstUpper + "_grid.jsp");
			File toCopyFB3 = new File(jspBasePath + "\\" + tableFirstUpper + "_update.jsp");
			File toCopyFB4 = new File(jspBasePath + "\\" + tableFirstUpper + "_readonly.jsp");

			// destination for copy extension jsp
			File toCopyExt1 = new File(extJspBasePath + "\\" + tableLower + "_extension.jsp");
			File toCopyExt2 = new File(extJspBasePath + "\\" + tableLower + "_add_grid.jsp");
			File toCopyExt3 = new File(extJspBasePath + "\\" + tableLower + "_add_grid2.jsp");
			File toCopyExt4 = new File(extJspBasePath + "\\" + tableLower + "_ext_Update.jsp");
			File toCopyExt5 = new File(extJspBasePath + "\\" + tableLower + "_ext_Readonly.jsp");

			System.out.println("from file ::" + file1);
			System.out.println("To file ::" + copy1);

			try {
				// for java files
				copyFile(file1, copy1);
				copyFile(file2, copy2);
				copyFile(file3, copy3);
				copyFile(file4, copy4);
				copyFile(file5, copy5);
				copyFile(file7, copy7);

				// for fb jsp
				copyFile(file6, toCopyFB1);
				copyFile(file8, toCopyFB2);
				copyFile(file9, toCopyFB3);
				copyFile(file10, toCopyFB4);

				// for ext jsp
				copyFile(file15, toCopyExt1);
				copyFile(file18, toCopyExt2);
				copyFile(file19, toCopyExt3);
				copyFile(file21, toCopyExt4);
				copyFile(file22, toCopyExt5);

			} catch (IOException e) {
				e.printStackTrace();
			}

			tilesForSource(table_name, jspName, project_name);

			// String sql = "UPDATE rn_fb_header_t SET is_build='Y' WHERE id='"+id+"'";
			// jdbcTemplate.update(sql);

		} // close for loop
	}

	// report builder source code
	void reportBuilderSource(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		List<Rn_Report_Builder> report = rn_user_registration_dao.updateReportById(u_id);

		for (int i = 0; i < report.size(); i++) {
			String report_name = report.get(i).getReport_name();
			int id = report.get(i).getId();

			File file1 = new File(
					projectPath + "\\src\\main\\java\\com\\realnet\\comm\\model\\" + report_name + "_model.java");
			File file2 = new File(
					projectPath + "\\src\\main\\java\\com\\realnet\\comm\\dao\\" + report_name + "_DAO.java");
			File file3 = new File(
					projectPath + "\\src\\main\\java\\com\\realnet\\comm\\dao\\" + report_name + "_DAOImpl.java");
			File file4 = new File(projectPath + "\\src\\main\\java\\com\\realnet\\comm\\controller\\" + report_name
					+ "_controller.java");

			String javaBasePath = "F:\\Ganesh\\CreatedProjectForExport\\ExportSource\\" + project_name
					+ "\\src\\main\\java\\com\\realnet\\comm";

			File copyTo1 = new File(javaBasePath + "\\model\\" + report_name + "_model.java");
			File copyTo2 = new File(javaBasePath + "\\dao\\" + report_name + "_DAO.java");
			File copyTo3 = new File(javaBasePath + "\\dao\\" + report_name + "_DAOImpl.java");
			File copyTo4 = new File(javaBasePath + "\\controller\\" + report_name + "_controller.java");

			File mainJsp = new File(
					projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\Report\\" + report_name + ".jsp");

			File mainJspCopy = new File("F:\\Ganesh\\CreatedProjectForExport\\ExportSource\\" + project_name
					+ "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\Report\\" + report_name + ".jsp");

			try {
				// java files
				copyFile(file1, copyTo1);
				copyFile(file2, copyTo2);
				copyFile(file3, copyTo3);
				copyFile(file4, copyTo4);

				// jsp
				copyFile(mainJsp, mainJspCopy);

			} catch (IOException e) {
				e.printStackTrace();
			}

			tilesAddForReportSource(project_name, report_name);

			// String sql = "UPDATE rn_rb_reports_t SET is_build='Y' WHERE id='"+id+"'";
			// jdbcTemplate.update(sql);

		}

	}

	// api source code
	void apiBuilderSource(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		List<Rn_Api_Builder> apiList = rn_api_builder_dao.apiUpdateByProjectId(u_id);

		for (int i = 0; i < apiList.size(); i++) {
			int id = apiList.get(i).getId();
			String reportName = apiList.get(i).getName();
			String reportLower = reportName.toLowerCase();
			String first_upper = reportLower.substring(0, 1).toUpperCase() + reportLower.substring(1);

			File file1 = new File(projectPath + "/src/main/java/com/realnet\\comm/dao/" + first_upper + "Api_DAO.java");
			File file2 = new File(
					projectPath + "/src/main/java/com/realnet\\comm/dao/" + first_upper + "Api_DAOImpl.java");
			File file3 = new File(
					projectPath + "/src/main/java/com/realnet\\comm/model/" + first_upper + "Api_model.java");
			File file4 = new File(
					projectPath + "/src/main/java/com/realnet\\comm/controller/" + first_upper + "Api_controller.java");

			String javaBasePath = "F:\\Ganesh\\CreatedProjectForExport\\ExportSource\\" + project_name
					+ "\\src\\main\\java\\com\\realnet\\comm";

			File copyTo1 = new File(javaBasePath + "\\dao\\" + first_upper + "Api_DAO.java");
			File copyTo2 = new File(javaBasePath + "\\dao\\" + first_upper + "Api_DAOImpl.java");
			File copyTo3 = new File(javaBasePath + "\\model\\" + first_upper + "Api_model.java");
			File copyTo4 = new File(javaBasePath + "\\controller\\" + first_upper + "Api_controller.java");

			try {
				// java files
				copyFile(file1, copyTo1);
				copyFile(file2, copyTo2);
				copyFile(file3, copyTo3);
				copyFile(file4, copyTo4);

			} catch (IOException e) {
				e.printStackTrace();
			}

			String sql = "UPDATE rn_create_api_header_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(sql);
		}

	}

	// dashbord source code
	void dashboardBuilderSource(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		List<Rn_Dashboard_Header> dashDetails = rn_dashboard_header_dao.dashByProjectId(u_id);

		for (int i = 0; i < dashDetails.size(); i++) {
			int id = dashDetails.get(i).getHeader_id();
			String dash_name = dashDetails.get(i).getDashboard_name();
			String dashLower = dash_name.toLowerCase();
			String first_upper = dashLower.substring(0, 1).toUpperCase() + dashLower.substring(1);

			File file1 = new File(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + first_upper + "DashController.java");
			File copyController = new File("F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name
					+ "/src/main/java/com/realnet/comm/controller/" + first_upper + "DashController.java");

			File file2 = new File(
					projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/dashboard/" + dashLower + "Dashboard.jsp");
			File mainJspCopy = new File("F:\\Ganesh\\CreatedProjectForExport\\ExportSource\\" + project_name
					+ "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\dashboard\\" + dashLower + "Dashboard.jsp");

			try {
				copyFile(file1, copyController);
				copyFile(file2, mainJspCopy);

			} catch (IOException e) {
				e.printStackTrace();
			}

			tilesAddForDashboardSource(project_name, dashLower);

			String sql = "UPDATE rn_dashboard_header_t SET is_build='Y' WHERE header_id='" + id + "'";
			jdbcTemplate.update(sql);
		}
	}

	/*---------------------------------method for create war--------------------------------------------------------*/

	// form builder export war
	void formBuilderExport(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		List<Rn_Fb_Header> rn_userlist = rn_distributor_details_dao.fb_headerlistByProjectId(u_id);
		for (int i = 0; i < rn_userlist.size(); i++) {
			int id = rn_userlist.get(i).getId();
			String table_name = rn_userlist.get(i).getTable_name();
			String controllerName = rn_userlist.get(i).getController_name();
			String daoName = rn_userlist.get(i).getDao_name();
			String daoImplName = rn_userlist.get(i).getDao_impl_name();
			String serviceName = rn_userlist.get(i).getService_name();
			String serviceImplName = rn_userlist.get(i).getService_impl_name();
			String jspName = rn_userlist.get(i).getJsp_name();

			System.out.println("table name::" + table_name + "\nid for update" + id);

			// lower
			String tableLower = table_name.toLowerCase();
			String controllerNameLower = controllerName.toLowerCase();
			String daoNameLower = daoName.toLowerCase();
			String daoImplNameLower = daoImplName.toLowerCase();
			String serviceNameLower = serviceName.toLowerCase();
			String serviceImplNameLower = serviceImplName.toLowerCase();

			// first upper
			String tableFirstUpper = tableLower.substring(0, 1).toUpperCase() + tableLower.substring(1);
			String controllerNameFirstUpper = controllerNameLower.substring(0, 1).toUpperCase()
					+ controllerNameLower.substring(1);
			String daoNameFirstUpper = daoNameLower.substring(0, 1).toUpperCase() + daoNameLower.substring(1);
			String daoImplNameFirstUpper = daoImplNameLower.substring(0, 1).toUpperCase()
					+ daoImplNameLower.substring(1);
			String serviceNameFirstUpper = serviceNameLower.substring(0, 1).toUpperCase()
					+ serviceNameLower.substring(1);
			String serviceImplNameFirstUpper = serviceImplNameLower.substring(0, 1).toUpperCase()
					+ serviceImplNameLower.substring(1);

			/*
			 * //java files File file1 = new File(
			 * "F:/Ganesh/MyProject/REAL_NET_GB1/src/main/java/com/springmvc/controller/"+
			 * controllerNameFirstUpper+".java"); File file2 = new
			 * File("F:/Ganesh/MyProject/REAL_NET_GB1/src/main/java/com/springmvc/dao/"+
			 * daoNameFirstUpper+".java"); File file3 = new
			 * File("F:/Ganesh/MyProject/REAL_NET_GB1/src/main/java/com/springmvc/dao/"+
			 * daoImplNameFirstUpper+".java"); File file4 = new
			 * File("F:/Ganesh/MyProject/REAL_NET_GB1/src/main/java/com/springmvc/service/"+
			 * serviceNameFirstUpper+".java"); File file5 = new
			 * File("F:/Ganesh/MyProject/REAL_NET_GB1/src/main/java/com/springmvc/service/"+
			 * serviceImplNameFirstUpper+".java"); File file7 = new
			 * File("F:/Ganesh/MyProject/REAL_NET_GB1/src/main/java/com/springmvc/model/"+
			 * tableFirstUpper+".java");
			 */

			// crud jsp
			File file6 = new File(
					projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\" + jspName + ".jsp");
			File file8 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_grid.jsp");
			File file9 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_update.jsp");
			File file10 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\FB_jsp\\"
					+ tableFirstUpper + "_readonly.jsp");

			// extension jsps
			File file15 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_extension.jsp");
			File file18 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_add_grid.jsp");
			File file19 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_add_grid2.jsp");
			File file21 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_ext_Update.jsp");
			File file22 = new File(projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\extpages\\"
					+ tableLower + "_ext_Readonly.jsp");

			// tiles lacaiton
			File tiles = new File(projectPath + "/src/main/webapp/WEB-INF/tiles.xml");

			// destination for copy crud jsp
			File toCopyFB1 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\FB_jsp\\" + jspName + ".jsp");
			File toCopyFB2 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\FB_jsp\\" + tableFirstUpper + "_grid.jsp");
			File toCopyFB3 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\FB_jsp\\" + tableFirstUpper + "_update.jsp");
			File toCopyFB4 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\FB_jsp\\" + tableFirstUpper + "_readonly.jsp");

			// destination for copy extension jsp
			File toCopyExt1 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\extpages\\" + tableLower + "_extension.jsp");
			File toCopyExt2 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\extpages\\" + tableLower + "_add_grid.jsp");
			File toCopyExt3 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\extpages\\" + tableLower + "_add_grid2.jsp");
			File toCopyExt4 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\extpages\\" + tableLower + "_ext_Update.jsp");
			File toCopyExt5 = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\extpages\\" + tableLower + "_ext_Readonly.jsp");

			// destination for copy tiles file
			File copyTiles = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name + "\\WEB-INF\\tiles.xml");

			// Path s = Paths.get(file8);
			// Path d = Paths.get(toCopyFB);
			// Files.copy(s.toFile(), dest.toFile());

			// copy(file8,toCopyFB);

			try {
				// for fb jsp
				copyFile(file6, toCopyFB1);
				copyFile(file8, toCopyFB2);
				copyFile(file9, toCopyFB3);
				copyFile(file10, toCopyFB4);

				// for ext jsp
				copyFile(file15, toCopyExt1);
				copyFile(file18, toCopyExt2);
				copyFile(file19, toCopyExt3);
				copyFile(file21, toCopyExt4);
				copyFile(file22, toCopyExt5);

				// tiles copy
				// FileUtils.copyDirectory(tiles, copyTiles);

			} catch (IOException e) {
				e.printStackTrace();
			}

			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/model/" + tableFirstUpper + ".java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + daoNameFirstUpper + ".java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + daoImplNameFirstUpper + ".java");
			compileFiles
					.add(projectPath + "/src/main/java/com/realnet/comm/service/" + serviceNameFirstUpper + ".java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/service/" + serviceImplNameFirstUpper + ".java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + controllerNameFirstUpper + ".java");

			TomcatCompiler(project_name);

			tilesAdd(table_name, jspName, project_name);

			String sql = "UPDATE rn_fb_header_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(sql);

		}
	}

	// report export war
	void reportExport(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		List<Rn_Report_Builder> report = rn_user_registration_dao.updateReportById(u_id);

		for (int i = 0; i < report.size(); i++) {
			String report_name = report.get(i).getReport_name();
			int id = report.get(i).getId();

			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/model/" + report_name + "_model.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + report_name + "_DAO.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + report_name + "_DAOImpl.java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + report_name + "_controller.java");

			TomcatCompiler(project_name);

			File mainJsp = new File(
					projectPath + "\\src\\main\\webapp\\WEB-INF\\tiles\\acemaster\\Report\\" + report_name + ".jsp");
			File mainJspCopy = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\Report\\" + report_name + ".jsp");

			try {
				copyFile(mainJsp, mainJspCopy);

			} catch (IOException e) {
				e.printStackTrace();
			}

			tilesAddForReport(project_name, report_name);

			String sql = "UPDATE rn_rb_reports_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(sql);

		}

	}

	// api export war
	void apiExport(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		List<Rn_Api_Builder> apiList = rn_api_builder_dao.apiUpdateByProjectId(u_id);

		for (int i = 0; i < apiList.size(); i++) {
			int id = apiList.get(i).getId();
			String reportName = apiList.get(i).getName();
			String reportLower = reportName.toLowerCase();
			String first_upper = reportLower.substring(0, 1).toUpperCase() + reportLower.substring(1);

			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/model/" + first_upper + "_model.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + first_upper + "Api_DAO.java");
			compileFiles.add(projectPath + "/src/main/java/com/realnet/comm/dao/" + first_upper + "Api_DAOImpl.java");
			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + first_upper + "Api_controller.java");

			TomcatCompiler(project_name);

			String sql = "UPDATE rn_create_api_header_t SET is_build='Y' WHERE id='" + id + "'";
			jdbcTemplate.update(sql);
		}
	}

	// dashboard bi export war
	void dashboardExport(int u_id, String project_name) throws IOException {
		String projectPath = rn_project_setup_dao.getProjectPath();

		List<Rn_Dashboard_Header> dashDetails = rn_dashboard_header_dao.dashByProjectId(u_id);

		for (int i = 0; i < dashDetails.size(); i++) {
			int id = dashDetails.get(i).getHeader_id();
			String dash_name = dashDetails.get(i).getDashboard_name();
			String dashLower = dash_name.toLowerCase();
			String first_upper = dashLower.substring(0, 1).toUpperCase() + dashLower.substring(1);

			compileFiles.add(
					projectPath + "/src/main/java/com/realnet/comm/controller/" + first_upper + "DashController.java");
			TomcatCompiler(project_name);

			File file2 = new File(
					projectPath + "/main/webapp/WEB-INF/tiles/acemaster/dashboard/" + dashLower + "Dashboard.jsp");
			File mainJspCopy = new File("F:\\Ganesh\\CreatedProjectForExport\\" + project_name
					+ "\\WEB-INF\\tiles\\acemaster\\dashboard\\" + dashLower + "Dashboard.jsp");

			try {
				copyFile(file2, mainJspCopy);

			} catch (IOException e) {
				e.printStackTrace();
			}

			tilesAddForDashboard(project_name, dashLower);

			String sql = "UPDATE rn_dashboard_header_t SET is_build='Y' WHERE header_id='" + id + "'";
			jdbcTemplate.update(sql);
		}
	}

	private static void copyFile(File src, File dest) throws IOException {
		Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	public void zipFolder(String srcFolder, String destZipFile) throws Exception {
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;

		fileWriter = new FileOutputStream(destZipFile);
		zip = new ZipOutputStream(fileWriter);

		addFolderToZip("", srcFolder, zip);

		zip.flush();
		zip.close();
	}

	private void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws Exception {
		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
		}
	}

	private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);
		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
			}
		}
	}

	// tomcat java file compiler
	void TomcatCompiler(String project_name) {
		String changedir = "pwd";
		System.out.println("ganesh bute call compile method" + compileFiles + "\n");

		for (int i = 0; i < compileFiles.size(); i++) {
			System.out.println("under for loop");
			String moveToclasses = "F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/classes";
			String librarypath = "F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/lib";
			System.out.println("move to::" + moveToclasses);
			System.out.println(
					"check envirment path::" + environment.getRequiredProperty("ControllerPath") + compileFiles.get(i));

			String ServerCompile = "javac -classpath " + moveToclasses
					+ environment.getRequiredProperty("tomcatcmdPath") + librarypath
					+ environment.getRequiredProperty("controllerNamePath") + "*"
					+ environment.getRequiredProperty("tomcatcmdPath") + "." + " -d " + moveToclasses + " "
					+ compileFiles.get(i) + "";
			System.out.println("path=" + moveToclasses + "\ncommand=" + ServerCompile);
			Process proc = null;
			String line = "";

			try {
				proc = Runtime.getRuntime().exec(ServerCompile);
				System.out.println("compile done");
				BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

				while ((line = reader.readLine()) != null) {
					System.out.print(line + "\n");
				}
				proc.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void TomcatCompilerForPatch(String project_name) {
		String projectPath = rn_project_setup_dao.getProjectPath();

		String changedir = "pwd";
		System.out.println("ganesh bute call compile method" + compileFiles + "\n");

		for (int i = 0; i < compileFiles.size(); i++) {
			System.out.println("under for loop");
			// F:\Ganesh\MyProject\REAL_NET_GB1\src\main\java\compilefiles
			String moveToclasses = projectPath + "/src/main/java/exp_compilefiles_wip";
			// String
			// librarypath="F:/Ganesh/CreatedProjectForExport/"+project_name+"/WEB-INF/lib";
			String librarypath = request.getServletContext().getRealPath("/WEB-INF/lib");
			System.out.println("move to::" + moveToclasses + "\nlibrary path::" + librarypath);
			System.out.println(
					"check envirment path::" + environment.getRequiredProperty("ControllerPath") + compileFiles.get(i));

			String ServerCompile = "javac -classpath " + moveToclasses
					+ environment.getRequiredProperty("tomcatcmdPath") + librarypath
					+ environment.getRequiredProperty("controllerNamePath") + "*"
					+ environment.getRequiredProperty("tomcatcmdPath") + "." + " -d " + moveToclasses + " "
					+ compileFiles.get(i) + "";
			System.out.println("path=" + moveToclasses + "\ncommand=" + ServerCompile);
			Process proc = null;
			String line = "";

			try {
				proc = Runtime.getRuntime().exec(ServerCompile);
				System.out.println("compile done");
				BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

				while ((line = reader.readLine()) != null) {
					System.out.print(line + "\n");
				}
				proc.waitFor();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// copy code
	public static void copy(File src, File dest) throws IOException {

		System.out.println();
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest);

			// buffer size 1K
			byte[] buf = new byte[1024];

			int bytesRead;
			while ((bytesRead = is.read(buf)) > 0) {
				os.write(buf, 0, bytesRead);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	// tiles for form builder war
	void tilesAdd(String table_name, String jsp_name, String project_name) throws IOException {
		File temp = new File("F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/tiles.xml");
		File newtemp = new File("F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/xyz.xml");
		BufferedReader br = new BufferedReader(new FileReader(temp));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
		String removeStr = "</tiles-definitions>";
		String currentLine;

		System.out.println(temp.getName());
		while ((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if (trimmedLine.equals(removeStr)) {
				currentLine = "";
			}
			bw1.write(currentLine + System.getProperty("line.separator"));

		}
		bw1.close();
		br.close();
		boolean delete = temp.delete();
		boolean b = newtemp.renameTo(temp);

		StringBuilder tiles = new StringBuilder();

		try {
			String tableLower = table_name.toLowerCase();
			String table_name_first_upper = tableLower.substring(0, 1).toUpperCase() + tableLower.substring(1);

			tiles.append("\n<definition name=\"" + table_name_first_upper + "_grid\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/" + table_name_first_upper
					+ "_grid.jsp\"/>" + "\n</definition>");
			tiles.append("\n<definition name=\"" + jsp_name + "\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/" + jsp_name + ".jsp\"/>"
					+ "\n</definition>");
			tiles.append(
					"\n<definition name=\"" + table_name_first_upper + "_update\" extends=\"acemaster.definition\">"
							+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
							+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"
							+ table_name_first_upper + "_update.jsp\"/>" + "\n</definition>");
			tiles.append("\n<definition name=\"" + table_name_first_upper
					+ "_readonly\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/" + table_name_first_upper
					+ "_readonly.jsp\"/>" + "\n</definition>" + "\n</tiles-definitions>");

			// String
			// filename="F:/Ganesh/MyProject/REAL_NET_GB1/src/main/webapp/WEB-INF/tiles.xml";
			String filename = "F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/tiles.xml";

			FileWriter fw = new FileWriter(filename, true);
			fw.write(tiles.toString());
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// tiles for report war
	void tilesAddForReport(String project_name, String report_name) throws IOException {
		File temp = new File("F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/tiles.xml");
		File newtemp = new File("F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/xyz.xml");
		BufferedReader br = new BufferedReader(new FileReader(temp));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
		String removeStr = "</tiles-definitions>";
		String currentLine;

		System.out.println(temp.getName());
		while ((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if (trimmedLine.equals(removeStr)) {
				currentLine = "";
			}
			bw1.write(currentLine + System.getProperty("line.separator"));

		}
		bw1.close();
		br.close();
		boolean delete = temp.delete();
		boolean b = newtemp.renameTo(temp);

		StringBuilder tiles = new StringBuilder();

		try {

			tiles.append("\n<definition name=\"" + report_name + "\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/Report/" + report_name
					+ ".jsp\"/>" + "\n</definition>" + "\n</tiles-definitions>");

			String filename = "F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/tiles.xml";

			FileWriter fw = new FileWriter(filename, true);
			fw.write(tiles.toString());
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// tiles for dashboard war
	void tilesAddForDashboard(String project_name, String dash_name) throws IOException {
		File temp = new File("F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/tiles.xml");
		File newtemp = new File("F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/xyz.xml");
		BufferedReader br = new BufferedReader(new FileReader(temp));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
		String removeStr = "</tiles-definitions>";
		String currentLine;

		System.out.println(temp.getName());
		while ((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if (trimmedLine.equals(removeStr)) {
				currentLine = "";
			}
			bw1.write(currentLine + System.getProperty("line.separator"));

		}
		bw1.close();
		br.close();
		boolean delete = temp.delete();
		boolean b = newtemp.renameTo(temp);

		StringBuilder tiles = new StringBuilder();

		try {

			tiles.append("\n<definition name=\"" + dash_name + "Dashboard\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/dashboard/" + dash_name
					+ "Dashboard.jsp\"/>" + "\n</definition>" + "\n</tiles-definitions>");

			String filename = "F:/Ganesh/CreatedProjectForExport/" + project_name + "/WEB-INF/tiles.xml";

			FileWriter fw = new FileWriter(filename, true);
			fw.write(tiles.toString());
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// tiles for form builder source
	void tilesForSource(String table_name, String jsp_name, String project_name) throws IOException {
		File temp = new File("F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name
				+ "/src/main/webapp/WEB-INF/tiles.xml");
		File newtemp = new File(
				"F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name + "/src/main/webapp/WEB-INF/xyz.xml");
		BufferedReader br = new BufferedReader(new FileReader(temp));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
		String removeStr = "</tiles-definitions>";
		String currentLine;

		System.out.println(temp.getName());
		while ((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if (trimmedLine.equals(removeStr)) {
				currentLine = "";
			}
			bw1.write(currentLine + System.getProperty("line.separator"));

		}
		bw1.close();
		br.close();
		boolean delete = temp.delete();
		boolean b = newtemp.renameTo(temp);

		StringBuilder tiles = new StringBuilder();

		try {
			String tableLower = table_name.toLowerCase();
			String table_name_first_upper = tableLower.substring(0, 1).toUpperCase() + tableLower.substring(1);

			tiles.append("\n<definition name=\"" + table_name_first_upper + "_grid\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/" + table_name_first_upper
					+ "_grid.jsp\"/>" + "\n</definition>");
			tiles.append("\n<definition name=\"" + jsp_name + "\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/" + jsp_name + ".jsp\"/>"
					+ "\n</definition>");
			tiles.append(
					"\n<definition name=\"" + table_name_first_upper + "_update\" extends=\"acemaster.definition\">"
							+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
							+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"
							+ table_name_first_upper + "_update.jsp\"/>" + "\n</definition>");
			tiles.append("\n<definition name=\"" + table_name_first_upper
					+ "_readonly\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/" + table_name_first_upper
					+ "_readonly.jsp\"/>" + "\n</definition>" + "\n</tiles-definitions>");

			// String
			// filename="F:/Ganesh/MyProject/REAL_NET_GB1/src/main/webapp/WEB-INF/tiles.xml";
			String filename = "F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name
					+ "/src/main/webapp/WEB-INF/tiles.xml";

			FileWriter fw = new FileWriter(filename, true);
			fw.write(tiles.toString());
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// tiles for report source
	void tilesAddForReportSource(String project_name, String report_name) throws IOException {
		File temp = new File("F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name
				+ "/src/main/webapp/WEB-INF/tiles.xml");
		File newtemp = new File(
				"F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name + "/src/main/webapp/WEB-INF/xyz.xml");
		BufferedReader br = new BufferedReader(new FileReader(temp));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
		String removeStr = "</tiles-definitions>";
		String currentLine;

		System.out.println(temp.getName());
		while ((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if (trimmedLine.equals(removeStr)) {
				currentLine = "";
			}
			bw1.write(currentLine + System.getProperty("line.separator"));

		}

		bw1.close();
		br.close();
		boolean delete = temp.delete();
		boolean b = newtemp.renameTo(temp);

		StringBuilder tiles = new StringBuilder();

		try {

			tiles.append("\n<definition name=\"" + report_name + "\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/Report/" + report_name
					+ ".jsp\"/>" + "\n</definition>" + "\n</tiles-definitions>");

			String filename = "F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name
					+ "/src/main/webapp/WEB-INF/tiles.xml";

			FileWriter fw = new FileWriter(filename, true);
			fw.write(tiles.toString());
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// tiles for dashboard source
	void tilesAddForDashboardSource(String project_name, String dash_name) throws IOException {
		File temp = new File("F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name
				+ "/src/main/webapp/WEB-INF/tiles.xml");
		File newtemp = new File(
				"F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name + "/src/main/webapp/WEB-INF/xyz.xml");
		BufferedReader br = new BufferedReader(new FileReader(temp));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
		String removeStr = "</tiles-definitions>";
		String currentLine;

		System.out.println(temp.getName());
		while ((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if (trimmedLine.equals(removeStr)) {
				currentLine = "";
			}
			bw1.write(currentLine + System.getProperty("line.separator"));

		}

		bw1.close();
		br.close();
		boolean delete = temp.delete();
		boolean b = newtemp.renameTo(temp);

		StringBuilder tiles = new StringBuilder();

		try {

			tiles.append("\n<definition name=\"" + dash_name + "Dashboard\" extends=\"acemaster.definition\">"
					+ "\n<put-attribute name=\"title\" value=\"WASIB\"/>"
					+ "\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/dashboard/" + dash_name
					+ "Dashboard.jsp\"/>" + "\n</definition>" + "\n</tiles-definitions>");

			String filename = "F:/Ganesh/CreatedProjectForExport/ExportSource/" + project_name
					+ "/src/main/webapp/WEB-INF/tiles.xml";

			FileWriter fw = new FileWriter(filename, true);
			fw.write(tiles.toString());
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void unzip2(String zipFilePath, String destDir) {
		File dir = new File(destDir);
		// create output directory if it doesn't exist
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;

		// buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {
				String fileName = ze.getName();

				File newFile = new File(destDir + fileName);

				newFile.setWritable(true, false);

				System.out.println("new file ::" + newFile + " \nfile name::" + fileName);

				System.out.println("Unzipping to " + newFile.getAbsolutePath());
				// create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void zipFiles(List<String> files, String path) {

		FileOutputStream fos = null;
		ZipOutputStream zipOut = null;
		FileInputStream fis = null;
		try {
			fos = new FileOutputStream(path);
			zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
			for (String filePath : files) {
				File input = new File(filePath);
				System.out.println("input path in files::" + input);
				fis = new FileInputStream(input);
				ZipEntry ze = new ZipEntry(input.getName());
				System.out.println("Zipping the file: " + input.getName());
				zipOut.putNextEntry(ze);
				byte[] tmp = new byte[4 * 1024];
				int size = 0;
				while ((size = fis.read(tmp)) != -1) {
					zipOut.write(tmp, 0, size);
				}
				zipOut.flush();
				fis.close();
			}
			zipOut.close();
			System.out.println("Done... Zipped the files...");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception ex) {

			}
		}
	}

	public static File zip(List<File> files, String filename) {
		File zipfile = new File(filename);
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			// compress the files
			for (int i = 0; i < files.size(); i++) {
				FileInputStream in = new FileInputStream(files.get(i).getCanonicalPath());
				// add ZIP entry to output stream
				out.putNextEntry(new ZipEntry(files.get(i).getName()));
				// transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// complete the entry
				out.closeEntry();
				in.close();
			}
			// complete the ZIP file
			out.close();
			return zipfile;
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
		return null;
	}

}