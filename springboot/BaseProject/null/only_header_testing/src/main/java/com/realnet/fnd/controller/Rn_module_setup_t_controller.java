
package com.realnet.fnd.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import javax.transaction.Transactional;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
// import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.realnet.codeextractor.dao.Rn_Bcf_Rule_Library_Dao;
// import java.util.ArrayList;
// import java.sql.CallableStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import com.google.gson.Gson;
// import java.util.Date;
import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.dao.Rn_Project_Setup_Dao;
import com.realnet.fnd.dao.Rn_module_setup_t_dao;
import com.realnet.fnd.model.Rn_Project_Setup;
import com.realnet.fnd.model.Rn_module_setup_t;
import com.realnet.fnd.service.Rn_Project_Setup_Service;
import com.realnet.fnd.service.Rn_module_setup_t_service;
import com.realnet.wfb.dao.Rn_Wireframe_Dao;
// import com.realnet.wfb.model.Rn_Fb_Header;
import com.realnet.wfb.model.Rn_Fb_Header;

// import java.text.SimpleDateFormat;
import java.text.ParseException;

@Controller
public class Rn_module_setup_t_controller {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private Rn_module_setup_t_service rn_module_setup_t_service;
	@Autowired
	HibernateConfiguration hibernateConfiguration;
	@Autowired
	private Rn_module_setup_t_dao rn_module_setup_t_dao;
	@Autowired
	private Rn_Bcf_Rule_Library_Dao rn_bcf_rule_library_dao;
	
	@Autowired
	private Rn_Wireframe_Dao rn_wireframe_dao;

	@Autowired
	private Rn_Project_Setup_Service rn_project_setup_service;

	@Autowired
	private Rn_Project_Setup_Dao rn_project_setup_dao;

	// @Autowired
	// private Environment environment;

	// ----------------------entry form
	// sbmit------------------------------------------
	@Transactional
	@RequestMapping(value = "/rn_module_setup_t_submit", method = RequestMethod.POST)
	public ModelAndView saveServiceRequest(@ModelAttribute Rn_module_setup_t rn_module_setup_t,
			BindingResult resultKoel_user, ModelMap map, ModelAndView model, HttpServletRequest request) {
//		String projectPath = rn_project_setup_dao.getProjectPath();
		int user_id = (Integer) request.getSession().getAttribute("userid");

		String module_name = request.getParameter("module_name");
		String description = request.getParameter("description");
		String module_prefix = request.getParameter("module_prefix");

		String attribute1 = request.getParameter("attribute1");
		String attribute2 = request.getParameter("attribute2");
		String attribute3 = request.getParameter("attribute3");
		String attribute4 = request.getParameter("attribute4");
		String attribute5 = request.getParameter("attribute5");
		String attribute6 = request.getParameter("attribute6");
		String attribute7 = request.getParameter("attribute7");
		String attribute8 = request.getParameter("attribute8");
		String attribute9 = request.getParameter("attribute9");
		String attribute10 = request.getParameter("attribute10");
		String attribute11 = request.getParameter("attribute11");
		String attribute12 = request.getParameter("attribute12");
		String attribute13 = request.getParameter("attribute13");
		String attribute14 = request.getParameter("attribute14");
		String attribute15 = request.getParameter("attribute15");
		String flex1 = request.getParameter("flex1");
		String flex2 = request.getParameter("flex2");
		String flex3 = request.getParameter("flex3");
		String flex4 = request.getParameter("flex4");
		String flex5 = request.getParameter("flex5");

		rn_module_setup_t.setModule_name(module_name);

		rn_module_setup_t.setDescription(description);
		rn_module_setup_t.setModule_prefix(module_prefix);
		rn_module_setup_t.setAttribute1(attribute1);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute2(attribute2);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute3(attribute3);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute4(attribute4);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute5(attribute5);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute6(attribute6);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute7(attribute7);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute8(attribute8);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute9(attribute9);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute10(attribute10);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute11(attribute11);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute12(attribute12);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute13(attribute13);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute14(attribute14);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setAttribute15(attribute15);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setFlex1(flex1);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setFlex2(flex2);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setFlex3(flex3);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setFlex4(flex4);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		rn_module_setup_t.setFlex5(flex5);
		rn_module_setup_t.setCreated_by(user_id);
		rn_module_setup_t.setLast_updated_by(user_id);
		// hibernateTemplate.saveOrUpdate(rn_module_setup_t);

		int pid = (Integer) request.getSession().getAttribute("pid");
		rn_module_setup_t.setProject_id(pid);

		String copy_to1 = (String) request.getSession().getAttribute("copy_to");
		rn_module_setup_t.setCopy_to(copy_to1);

		/*
		 * get technology_stack from session (from project_setup_controller)
		 */
		String technology_stack = (String) request.getSession().getAttribute("technology_stack");
		rn_module_setup_t.setTechnology_stack(technology_stack);
		System.out.println("technology_stack............ :: " + technology_stack);
		hibernateTemplate.saveOrUpdate(rn_module_setup_t);

//		System.out.println("export porjectf stat");
//		// get project id by session
//		int u_id = (Integer) request.getSession().getAttribute("pid");

//		// get project details
//		List<Rn_Project_Setup> report = rn_project_setup_service.prefield(u_id);
//		String project_name = report.get(0).getProject_name();
//		String project_prefix = report.get(0).getProject_prefix();
//		String db_name = report.get(0).getDb_name();
//		String db_user = report.get(0).getDb_user();
//		String db_password = report.get(0).getDb_password();
//		String db_port_no = report.get(0).getPort_no();

//		//create project directry
//				final String FOLDER ="D:\\Sonali Office\\ris\\"+project_name+"\\"+module_name;
//				File newFolder = new File(FOLDER);
//			    boolean created =  newFolder.mkdir();

//			   //add base source code
//			    File source = new File("F:\\Sonali Office\\ris\\"+project_name+"\\src\\main\\java\\com\\realnet");
//			    File dest = new File("F:\\Sonali Office\\ris\\"+project_name+"\\src\\main\\java\\com\\realnet\\"+module_name);
//				try {
//				    FileUtils.copyDirectory(source, dest);
//				} catch (IOException e) {
//				    e.printStackTrace();
//				}

//		// create project directry
//
//		final String FOLDER0 = projectPath + "\\" + project_name + "\\src\\main\\java\\com\\realnet\\" + module_name;
//		File newFolder0 = new File(FOLDER0);
//		boolean created0 = newFolder0.mkdir();
//
//		final String FOLDER = projectPath + "\\" + project_name + "\\src\\main\\java\\com\\realnet\\" + module_name
//				+ "\\controller";
//		File newFolder = new File(FOLDER);
//		boolean created = newFolder.mkdir();
//
//		final String FOLDER1 = projectPath + "\\" + project_name + "\\src\\main\\java\\com\\realnet\\" + module_name
//				+ "\\model";
//		File newFolder1 = new File(FOLDER1);
//		boolean created1 = newFolder1.mkdir();
//
//		final String FOLDER2 = projectPath + "\\" + project_name + "\\src\\main\\java\\com\\realnet\\" + module_name
//				+ "\\dao";
//		File newFolder2 = new File(FOLDER2);
//		boolean created2 = newFolder2.mkdir();
//
//		final String FOLDER3 = projectPath + "\\" + project_name + "\\src\\main\\java\\com\\realnet\\" + module_name
//				+ "\\service";
//		File newFolder3 = new File(FOLDER3);
//		boolean created3 = newFolder3.mkdir();

		return new ModelAndView("redirect:rn_module_setup_t_grid_view?pid=" + pid + "");

	}

	@RequestMapping("/rn_module_setup_t_entryform")
	public ModelAndView input_form3(HttpServletRequest request,  ModelMap map, Model model) {
		int pid = (Integer) request.getSession().getAttribute("pid");
		System.out.println("rn_module_setup_t_entryform pid = ........." + pid);

		String copy_to = (String) request.getSession().getAttribute("copy_to");
		System.out.println("copy to........." + copy_to);
		
//		String technology_stack = (String) request.getSession().getAttribute("technology_stack");
//		System.out.println("technology_stack to........."+technology_stack);

		return new ModelAndView("Rn_module_setup_t_view");
	}

	// -----------------------------------for grid view
	// only--------------------------------------------------

	@RequestMapping(value = "/rn_module_setup_t_grid_view")
	public ModelAndView rn_module_setup_tDetails(@RequestParam(value = "pid") String pid, ModelAndView model,
			HttpServletRequest request) throws IOException {

		int p_id = Integer.parseInt(pid);
		System.out.println("project_id in  rn_module_setup_t_grid_view :: " + p_id);

		HttpSession session1 = request.getSession();
		session1.setAttribute("pid", p_id);

//		String technology_stack = (String) request.getSession().getAttribute("technology_stack");
//		System.out.println("technology_stack to........."+technology_stack);

		List<Rn_module_setup_t> rn_module_setup_t = rn_module_setup_t_dao.rn_module_values_for_pid(p_id);
		model.addObject("rn_module_setup_t", rn_module_setup_t);

		List<Rn_Project_Setup> rn_project_values_by_id = rn_wireframe_dao.project_values_by_id(pid);

		model.addObject("rn_project_setup", rn_project_values_by_id);

		model.setViewName("Rn_module_setup_t_grid");
		return model;
	}

	// -----------------------for prefield
	// part-----------------------------------

	@RequestMapping(value = "/rn_module_setup_t_update", method = RequestMethod.GET)
	public ModelAndView loadReport1(@RequestParam(value = "id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);
		Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();
		map.addAttribute("rn_module_setup_t_updt", rn_module_setup_t);
		List<Rn_module_setup_t> report = rn_module_setup_t_service.prefield(u_id);
		map.addAttribute("rn_module_setup_t_update", report);
		return new ModelAndView("Rn_module_setup_t_update");
	}

	// --------------------for
	// readonly------------------------------------------------

	@RequestMapping(value = "/rn_module_setup_t_readonly", method = RequestMethod.GET)
	public ModelAndView loadReport2(@RequestParam(value = "id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);
		Rn_module_setup_t rn_module_setup_t = new Rn_module_setup_t();
		map.addAttribute("rn_module_setup_t_updt", rn_module_setup_t);
		List<Rn_module_setup_t> report = rn_module_setup_t_service.prefield(u_id);
		map.addAttribute("rn_module_setup_t_update", report);
		return new ModelAndView("Rn_module_setup_t_readonly");
	}

	// --------------------------submit update
	// part---------------------------------------------------

	@RequestMapping(value = "/rn_module_setup_t_update_submit", method = RequestMethod.POST)
	public ModelAndView saveReportRegister(@ModelAttribute Rn_module_setup_t rn_module_setup_t,
			BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
		int report = 0;
		String id = request.getParameter("id");
		String module_name = request.getParameter("module_name");
		String description = request.getParameter("description");
		String module_prefix = request.getParameter("module_prefix");
		String project_id = request.getParameter("project_id");
		String copy_to = request.getParameter("copy_to");

		int rowcount = id.length();
		report = rn_module_setup_t_service.save(rowcount, id, module_name, description, module_prefix, project_id,
				copy_to);
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("report", report);
		Rn_module_setup_t rep_reg = new Rn_module_setup_t();
		map.addAttribute("rep_reg", rep_reg);
		List<Rn_module_setup_t> report_list = rn_module_setup_t_service.rn_module_values();
		map.addAttribute("report_list", report_list);
		return new ModelAndView("redirect:rn_module_setup_t_grid_view");
	}

//	// ----------------------selectActionForm------------------------------------------
//	@RequestMapping("/rn_select_action_Form")
//	public ModelAndView selectAction(@RequestParam(value = "mid") String id, @RequestParam(value = "pid") String pid,
//			HttpServletRequest request, ModelMap map, ModelAndView model) {
//
//		int id1 = Integer.parseInt(id);
//		System.out.println("module_id::::::::::::::::::::"+id1);
//		
//		HttpSession session1 = request.getSession();
//		session1.setAttribute("module_id", id1); 
//		
//		int p_id = Integer.parseInt(pid);
//		System.out.println("project id" + p_id);
//		HttpSession session = request.getSession();
//		session.setAttribute("project_id", p_id);
//
//		List<Rn_Project_Setup> rn_project_values_by_id = rn_wireframe_dao.project_values_by_id(pid);
//		String project_name = rn_project_values_by_id.get(0).getProject_name();
//		System.out.println(project_name + " :: is project name in rn_module_setup_t_grid_view controller");
//		model.addObject("rn_project_setup", rn_project_values_by_id);
//
//		System.out.println("rn_select_action_Form");
//		return new ModelAndView("Rn_Select_Action");
//	}

	/*
	 * @Author: Niladri Sen
	 * 22.9.20 Working
	 * */
	@Transactional
	@RequestMapping(value = "/moduleCopy", method = RequestMethod.POST)
	public ModelAndView moduleCopy(@ModelAttribute Rn_module_setup_t rn_module_setup_t, HttpServletRequest request,
			BindingResult result, ModelAndView model) throws IOException {

		HttpSession session0 = request.getSession(false);
		int project_id = (Integer) session0.getAttribute("project_id");
		System.out.println("moduleCopy project_id = " + project_id);
//
//		HttpSession session1 = request.getSession(false);
//		int module_id = (Integer) session1.getAttribute("module_id");
		
//		int project_id = 76;
//		int module_id = 41;
//		Rn_module_setup_t rn_module_setup_t_object = rn_module_setup_t_dao.copyModule(project_id, module_id);
		
		int from_project_id = Integer.parseInt(request.getParameter("from_project_id"));
		int from_module_id = Integer.parseInt(request.getParameter("from_module_id"));
		
//		int to_project_id = Integer.parseInt(request.getParameter("to_project_id"));
		
		// if its not null logic
		String to_module_name = request.getParameter("to_module_name");

		Rn_module_setup_t rn_module_setup_t_object = rn_module_setup_t_dao.copyModule(from_project_id, from_module_id);
		
		rn_module_setup_t_object.setModule_name(to_module_name);
		int new_module_id = rn_module_setup_t_dao.saveheader(rn_module_setup_t_object);
		// RETURN NEW GENERATED ID
		//System.out.println("new SAVED MODULE id : " + new_module_id);

		//List<Rn_Fb_Header> rn_fb_header_object = rn_module_setup_t_dao.copyHeaders(project_id, module_id);
		List<Rn_Fb_Header> rn_fb_header_object = rn_module_setup_t_dao.copyHeaders(from_project_id, from_module_id);
		
		for (Rn_Fb_Header header :  rn_fb_header_object) {
			int header_id = header.getId();
			header.setId(0);
			header.setModule_id(new_module_id);
			int new_header_id = rn_wireframe_dao.save(header);
			//System.out.println("new SAVED HEADER ID = " + new_header_id);
			//System.out.println("COPY HEADER VALUE = \n" +  header);
			
			
			String table_name = "RN_FB_LINES_T";
			String insert_string = "header_id, account_id, project_id, module_id, line_table_name, line_table_no, field_name, \r\n"
					+ "	mapping, data_type, type_field, form_code, key1, type1, type2, form_type, seq,\r\n"
					+ "	section_num, button_num, mandatory, hidden, readonly, dependent, dependent_on, dependent_sp, dependent_sp_param,\r\n"
					+ "	validation_1, val_type, val_sp, val_sp_param, sequence, seq_name, seq_sp, seq_sp_param,\r\n"
					+ "	default_1, default_type, default_value, default_sp, default_sp_param, calculated_field, cal_sp,\r\n"
					+ "	cal_sp_param, add_to_grid, sp_for_autocomplete, sp_for_dropdown, sp_name_for_autocomplete";

			String select_string = "" + new_header_id
					+ ", account_id, " + project_id + ", " + new_module_id + ", line_table_name, line_table_no, field_name, \r\n"
					+ "	mapping, data_type, type_field, form_code, key1, type1, type2, form_type, seq,\r\n"
					+ "	section_num, button_num, mandatory, hidden, readonly, dependent, dependent_on, dependent_sp, dependent_sp_param,\r\n"
					+ "	validation_1, val_type, val_sp, val_sp_param, sequence, seq_name, seq_sp, seq_sp_param,\r\n"
					+ "	default_1, default_type, default_value, default_sp, default_sp_param, calculated_field, cal_sp,\r\n"
					+ "	cal_sp_param, add_to_grid, sp_for_autocomplete, sp_for_dropdown, sp_name_for_autocomplete";

			//String where_string = "AND PROJECT_ID = " + project_id + " AND MODULE_ID = " + module_id + " AND HEADER_ID = " + header_id +"";
			String where_string = "AND PROJECT_ID = " + from_project_id + " AND MODULE_ID = " + from_module_id + " AND HEADER_ID = " + header_id +"";
			rn_bcf_rule_library_dao.dynamicInsert(table_name, insert_string, select_string, where_string);
		}
		return new ModelAndView("redirect:rn_module_setup_t_grid_view?pid=" + project_id + "");
	}

}