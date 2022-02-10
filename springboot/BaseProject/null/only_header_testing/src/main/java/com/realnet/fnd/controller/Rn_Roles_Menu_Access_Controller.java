package com.realnet.fnd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.dao.Rn_Distributor_Details_Dao;
import com.realnet.fnd.dao.Rn_Instance_Type_Dao;
import com.realnet.fnd.dao.Rn_Project_Setup_Dao;
import com.realnet.fnd.dao.Rn_User_Registration_Dao;
import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Instance_Type;
import com.realnet.fnd.model.Rn_List_Item_Description;
import com.realnet.fnd.model.Rn_Lookup_Values;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Header;
import com.realnet.fnd.model.Rn_Test_Dependent;
import com.realnet.fnd.model.Rn_User_Access_Menulist;
import com.realnet.fnd.model.Rn_User_Access_Reportist;
import com.realnet.fnd.model.Rn_User_Assignment;
import com.realnet.fnd.model.Rn_User_Role_Assignment;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.service.Rn_User_Registration_Service;
import com.realnet.rb.model.Rn_Report_Group_Header;
import com.realnet.wfb.model.Rn_Fb_Header;
import com.realnet.wfb.model.Rn_Fb_Line;

@Controller
public class Rn_Roles_Menu_Access_Controller 
{

	private static final String MENU_GROUP_HEADER_ID = null;

	@Autowired
	private HibernateConfiguration hibernateConfiguration;
	
	@Autowired
	private Rn_User_Registration_Dao rn_user_registration_dao;
	
	@Autowired
	private HibernateTemplate  hibernateTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private Rn_User_Registration_Service rn_user_registration_service;
	
	@Autowired
	private Rn_Project_Setup_Dao rn_project_setup_dao;
	
	@Autowired
	private Rn_Distributor_Details_Dao rn_distributor_details_dao;

	@Autowired
	private Rn_Instance_Type_Dao rn_instance_type_dao;
	
	List<String> controllerName = new ArrayList<String>();
	
	private String form_ext;
	
	
	// ----------------------for saving mapping data ------------------------------------------
	 
	 
	 
	 
	 @Transactional
	 @RequestMapping("/savemapping")
	 public ModelAndView saveGroupReport2(Model model, HttpServletRequest request) throws ParseException
	 {		 		
		 String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		 {			 
			 int user_id = (Integer) request.getSession().getAttribute("userid");		 			 	
			 
			 Rn_Ext_Fields assg= new Rn_Ext_Fields();	
			
			 String field_name=request.getParameter("field_name");
			 assg.setField_name(field_name);
			
			 String mapping=request.getParameter("mapping");
			 assg.setMapping(mapping);
			 
			 String data_type=request.getParameter("data_type");
			 assg.setData_type(data_type);
			 
			  hibernateTemplate.saveOrUpdate(assg);
			  	 		
			}
	
		 return new ModelAndView("redirect:extensiondetails");
	 }
	
	// ----------------------loadmenu ------------------------------------------

	@RequestMapping(value = "/loadmenu", method = RequestMethod.GET)
	 public void loadItem_Description( HttpServletRequest request, HttpServletResponse response)  {
  	 List<Rn_User_Access_Menulist> menulist = new ArrayList<Rn_User_Access_Menulist>();
  	 try {
			 
			 
			 int user_id = (Integer) request.getSession().getAttribute("userid");
			 System.out.println("user_id" +user_id);
			 if(user_id != 0)
				{

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call SP_RN_MENUGROUP_LIST}");
			 		System.out.println("ganesh sp procedure---------"+cStmt);
			 		ResultSet rs = cStmt.executeQuery();
			 		System.out.println("ganesh ResultSet---------"+rs);

			 		while (rs.next()) {
			 			int data = rs.getInt(1);
			 			System.out.println("data---------"+data);
			 			String data1 = rs.getString(2);
			 			System.out.println("data1---------"+data1);
			 			Rn_User_Access_Menulist menu = new Rn_User_Access_Menulist();
			 			menu.setMenu_group_header_id(data);
			 			menu.setMenu_name(data1);
			 			menulist.add(menu);
			 		}
			 		} catch (SQLException e) {
			 		e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				}
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

   }
	
	// ----------------------FOR MAPPING DROPDOWN LIST ------------------------------------------
	
	
	@RequestMapping(value = "/loadmapping", method = RequestMethod.GET)
	 public void loadItem_Description1( HttpServletRequest request, HttpServletResponse response)  {
 	 List<Rn_Lookup_Values> menulist = new ArrayList<Rn_Lookup_Values>();
 	 try {
			 
			 
			
					System.out.println("-----------------ganesh sp procedure mapping---------");
			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call SP_RN_MAPPING_LIST}");
			 		System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
			 		System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) {
			 			
			 			String data1 = rs.getString(1);
			 			System.out.println("data1---------"+data1);
			 			
			 			Rn_Lookup_Values mapping = new Rn_Lookup_Values();
			 			
			 			mapping.setLookup_code(data1);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) {
			 		e.printStackTrace();
			 		}
			 		catch (Exception e) 
			 		{
			 		   System.out.println(e.getMessage());
			 		}

					
				//}
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

  }
	
	
	// ----------------------loadtype ------------------------------------------
	
	
	@RequestMapping(value = "/loadtype", method = RequestMethod.GET)
	 public void loadItem_Description2( HttpServletRequest request, HttpServletResponse response)  {
	 List<Rn_Lookup_Values> menulist = new ArrayList<Rn_Lookup_Values>();
	 try {
			 
			System.out.println("-----------------ganesh sp procedure data type---------");

			System.out.println("-----------------ganesh sp procedure data type---------");
			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call SP_RN_DATA_TYPE_LIST}");
			 		System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
			 		System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data2 = rs.getString(1);
			 			System.out.println("data1---------"+data2);
			 			
			 			Rn_Lookup_Values mapping = new Rn_Lookup_Values();
			 			
			 			mapping.setMeaning(data2);
			 			menulist.add(mapping);
			 		}
			 		} catch (SQLException e) 
			 		{
			 		 e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				//}
			 String json = null;
			 
			 json = new Gson().toJson(menulist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

 }
	
	// ----------------------loadreport ------------------------------------------

    @RequestMapping(value = "/loadreport", method = RequestMethod.GET)
	 public void loadreport( HttpServletRequest request, HttpServletResponse response)  {
  	 List<Rn_User_Access_Reportist> reportlist = new ArrayList<Rn_User_Access_Reportist>();
  	 try {	 	 
			 int user_id = (Integer) request.getSession().getAttribute("userid");
			 System.out.println("user_id" +user_id);
			 if(user_id != 0)
				{

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call SP_RN_REPORTGROUP_LIST}");
			 		
			 		ResultSet rs = cStmt.executeQuery();
			 		while (rs.next()) {
			 			int data = rs.getInt(1);
			 			String data1 = rs.getString(2);

			 			Rn_User_Access_Reportist report = new Rn_User_Access_Reportist();
			 			report.setReport_group_header_id(data);
			 			report.setReport_name(data1);
			 			reportlist.add(report);
			 		}
			 		} catch (SQLException e) {
			 		e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				}
			 String json = null;
			 
			 json = new Gson().toJson(reportlist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

   }
    
	// ----------------------loaddashpro ------------------------------------------


   @RequestMapping(value = "/loaddashpro", method = RequestMethod.GET)
	 public void loaddashpro(@RequestParam(value = "dashboard_profile") String dashboard, HttpServletRequest request, HttpServletResponse response)
   {
  	 List<Rn_List_Item_Description> tautocomplete = new ArrayList<Rn_List_Item_Description>();
  	 try {
			 
  		 List<String> dashboard_list = new ArrayList<String>();
			 int user_id = (Integer) request.getSession().getAttribute("userid");
			 System.out.println("user_id" +user_id);
			 if(user_id != 0)
				{
				
					System.out.println("doa lookup " + dashboard);
					CallableStatement cStmt;
					try {
					cStmt = hibernateConfiguration.dataSource().getConnection()
							.prepareCall("{call SP_LOOKUP_VALUES(?)}");
					cStmt.setString(1,  dashboard );
					ResultSet rs = cStmt.executeQuery();
					while (rs.next()) {
			 			String data = rs.getString(1);
						dashboard_list.add(data);
					
					}
					} catch (SQLException e) {
					e.printStackTrace();
					}
					catch (Exception e) {
					System.out.println(e.getMessage());
					}
				}
			 String json = null;
			 
			 json = new Gson().toJson(dashboard_list);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

   }

	// ----------------------loadroles ------------------------------------------

  @RequestMapping(value = "/loadroles", method = RequestMethod.GET)
 	 public void loadrole(@RequestParam(value = "roles") String role, HttpServletRequest request, HttpServletResponse response)
    {
		try
		{
			List<String> role_list = new ArrayList<String>();
			
		 int user_id = (Integer) request.getSession().getAttribute("userid");
		 System.out.println("user_id=" +user_id);
		 if(user_id != 0)
			{
			
				System.out.println("doa lookup " + role);
				CallableStatement cStmt;
				try {
				cStmt = hibernateConfiguration.dataSource().getConnection()
						.prepareCall("{call SP_LOOKUP_VALUES(?)}");
				
				
				cStmt.setString(1,role );
				ResultSet rs = cStmt.executeQuery();
				while (rs.next()) {
					String data = rs.getString(1);
					role_list.add(data);
				
					System.out.println("data="+role_list.get(0));
				}
				} catch (SQLException e) {
				e.printStackTrace();
				}
				catch (Exception e) {
				System.out.println(e.getMessage());
				}
			}
		 String json = null;
		 
		 json = new Gson().toJson(role_list);
		 response.setContentType("application/json");
		 response.getWriter().write(json);
		 } catch (IOException e) {
		 	e.printStackTrace();
		 }
    }
   
   
	// ----------------------view new reports ------------------------------------------


	@RequestMapping(value = "/rn_view_new_reports", method = RequestMethod.GET)
	public ModelAndView viewReport(@RequestParam(value = "user_id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException, SQLException {

		int u_id = Integer.parseInt(id);
		System.out.println("ganesh user id" + u_id);

		Rn_Users rn = new Rn_Users();
		Rn_Menu_Group_Header mnh = new Rn_Menu_Group_Header();
		Rn_Report_Group_Header rgh = new Rn_Report_Group_Header();
		Rn_User_Role_Assignment ura = new Rn_User_Role_Assignment();
		
		map.addAttribute("rep_updt", rn);
		map.addAttribute("menu_updt", mnh);
		map.addAttribute("reportgrp_updt", rgh);
		map.addAttribute("role1", ura);
		
		List<Rn_Users> report = rn_user_registration_service.role_new_view_report(u_id);
		map.addAttribute("report_update", report);

		List<Rn_Menu_Group_Header> menu = rn_user_registration_service.view_menu_group(u_id);
		map.addAttribute("menugrp_update", menu);

		List<Rn_Report_Group_Header> reportgrp = rn_user_registration_service.view_report_group(u_id);
		map.addAttribute("reportgroup_update", reportgrp);

		List<Rn_User_Role_Assignment> role = rn_user_registration_service.view_role_group(u_id);
		map.addAttribute("user_List_role", role);

		Rn_User_Assignment uass = new Rn_User_Assignment();
		map.addAttribute("userid", uass);
		List<Rn_User_Assignment> role2 = rn_user_registration_service.view_assignment_id(u_id);
		map.addAttribute("user_id", role2);

		return new ModelAndView("UpdateRollMenuAccess");
	}

	// ----------------------save role assignment ------------------------------------------

   
	@RequestMapping("/rn_save_role_assignment")
	public ModelAndView saveGroupReport(@ModelAttribute Rn_User_Assignment userassign, BindingResult resultUser_Assignment,
			Model model, HttpServletRequest request) throws ParseException {
		int user_id = (Integer) request.getSession().getAttribute("userid");
		int id = 0;

		if (request.getParameter("user_id") != "") {
			id = Integer.parseInt(request.getParameter("User_id"));
			userassign.setUser_id(id);
		}

		int menuid = Integer.parseInt(request.getParameter("menu_name"));
		int reportid = Integer.parseInt(request.getParameter("report_name"));
		String dash = request.getParameter("dashboard_profile");

		userassign.setMenu_group_id(menuid);
		userassign.setReport_group_id(reportid);
		userassign.setAttribute1(dash);

		int menu_group_id = rn_user_registration_service.saveroll(userassign);

		
		return new ModelAndView("redirect:Rn_distributor_details1");
	}

	// ----------------------for roles menu update ------------------------------------------


	@RequestMapping(value = "/rn_roles_menu_update", method = RequestMethod.POST)
	public ModelAndView saveReportRegister(@ModelAttribute Rn_User_Assignment uass, BindingResult resultReportRegister,
			ModelMap map, HttpServletRequest request) throws ParseException {
		int report = 0;

		String[] user_id = request.getParameterValues("user_id");

		int menu_group_id = Integer.parseInt(request.getParameter("menu_name"));
		int report_group_id = Integer.parseInt(request.getParameter("report_name"));

		int rowcount = user_id.length;

		report = rn_user_registration_service.addRoll(rowcount, user_id, menu_group_id, report_group_id);
		System.out.println(report);
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("rep_updt", report);

		Rn_User_Assignment uss = new Rn_User_Assignment();
		map.addAttribute("rep_reg", uss);


		return new ModelAndView("Rn_distributor_details1");
	}

	
   
	// ----------------------for save role assign ------------------------------------------


	@Transactional
	@RequestMapping("/rn_save_role_assign")
	public ModelAndView saveGroupReport1(Model model, HttpServletRequest request) throws ParseException {
		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		{
			int user_id = (Integer) request.getSession().getAttribute("userid");
			int id = 0;

			Rn_User_Assignment assg = new Rn_User_Assignment();

			if (request.getParameter("user_assignment_id") != " ") {
				int roleid = Integer.parseInt(request.getParameter("user_assignment_id"));
				assg.setUser_assignment_id(roleid);
			}

			String u_name = request.getParameter("user_name");
			assg.setAttribute1(u_name);

			int userid = Integer.parseInt(request.getParameter("user_id"));
			assg.setUser_id(userid);

			int menuid = Integer.parseInt(request.getParameter("menu_name"));
			assg.setMenu_group_id(menuid);

			int reportid = Integer.parseInt(request.getParameter("report_name"));
			assg.setReport_group_id(reportid);

			
			assg.setCreated_by(user_id);
			assg.setLast_updated_by(user_id);

			hibernateTemplate.saveOrUpdate(assg);

			String user_role[] = request.getParameterValues("user_role_assignment_id");
			String userid1[] = request.getParameterValues("user_id");
			String roles[] = request.getParameterValues("roles");
			String active[] = request.getParameterValues("para");
			String enddate[] = request.getParameterValues("end_date");

			int rowcount = roles.length;
			System.out.println("Rowcount  " + rowcount);

			for (int i = 0; i < rowcount; i++) {
				int uid = 0;
				int infonum = 0;

				Rn_User_Role_Assignment assg1 = new Rn_User_Role_Assignment();

				uid = Integer.parseInt(userid1[i]);

				if (user_role[i] != null && user_role[i].length() > 0) {
					infonum = Integer.parseInt(user_role[i]);
				} else {
					infonum = assg1.getUser_role_assignment_id();
				}

				if (enddate[i] != null) {
					Date end_date = null;
					try {
						end_date = new SimpleDateFormat("dd-MM-yyyy").parse(enddate[i]);
					} catch (ParseException e) {

						e.printStackTrace();
					}
					assg1.setEnd_date(end_date);
				}

				assg1.setUser_role_assignment_id(infonum);
				assg1.setUser_id(uid);
				assg1.setEnable_flag(active[i]);
				assg1.setRole(roles[i]);
				assg1.setCreated_by(user_id);
				assg1.setLast_updated_by(user_id);

				hibernateTemplate.saveOrUpdate(assg1);
			}

			

		}

		return new ModelAndView("redirect:Rn_distributor_details1");
	}

	// ----------------------for user details1 show ------------------------------------------

	@RequestMapping(value = "/rn_user_details1_show")
	public ModelAndView forgotpassword(ModelAndView model, ModelMap map) throws IOException {
		return new ModelAndView("Rn_user_details1");

	}
   
    @RequestMapping(value = "/extfield_update", method = RequestMethod.GET)
	public ModelAndView loadReport2(@RequestParam(value = "id") String id1, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException 
	{
		int f_id=Integer.parseInt(id1);
		
		Rn_Ext_Fields rn = new Rn_Ext_Fields();
		map.addAttribute("rep_updt", rn);
		
		List<Rn_Ext_Fields> report=rn_user_registration_service.update_fields(f_id);
  		String mandatory= report.get(0).getMandatory();
  		System.out.println("mandatory---------------->"+mandatory);
  		String hidden= report.get(0).getHidden();
  		System.out.println("hidden---------------->"+hidden);
  		String readonly= report.get(0).getReadonly();
  		System.out.println("readonly---------------->"+readonly);
  		String dependent= report.get(0).getDependent();
  		System.out.println("dependent---------------->"+dependent);


		map.addAttribute("report_update", report);
		
		return new ModelAndView("UpdateExtFields");
	}
    
   
   @RequestMapping(value = "/extfield_submitUbdatedData", method = RequestMethod.POST)
	public ModelAndView saveReportRegister(@ModelAttribute Rn_Ext_Fields rn_users,
			BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
		
		System.out.println("<-----------controller begins------------>");

		String id[]=request.getParameterValues("ID");
		String field_name[] = request.getParameterValues("FIELD_NAME");
		String mapping[]= request.getParameterValues("MAPPING");
		String data_type[] = request.getParameterValues("DATA_TYPE");
		
		

		int rowcount=id.length;
		System.out.println("ROWCOUNT--------------->"+rowcount);

		int report = rn_user_registration_service.addFields(rowcount,id,field_name,mapping,data_type);
		System.out.println("REPORT--------------->"+report);
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("report", report);
		
		Rn_Ext_Fields rep_reg = new Rn_Ext_Fields();
		map.addAttribute("rep_reg", rep_reg);
		
		List<Rn_Ext_Fields> report_list = rn_user_registration_dao.ext_userlist();
		map.addAttribute("report_list", report_list);
		
		return new ModelAndView("UpdateExtFields");
	}
   
   
   @RequestMapping(value="/saverequest")
	public ModelAndView forgotpassword3(ModelAndView model,ModelMap map) throws IOException
	{		
		return new ModelAndView("ServiceRequestME");
		
	}
   
   
   
   
   
   @RequestMapping(value = "/extfield_update_2", method = RequestMethod.GET)
  	public ModelAndView updatestatus(@RequestParam(value = "id") String id1, ModelAndView modelview,
  			HttpServletRequest request, ModelMap map) throws IOException 
  	{
  		
	   System.out.println("------------update controller----------------------id-"+id1);
	   int f_id=Integer.parseInt(id1);
  		
  		Rn_Ext_Fields rn = new Rn_Ext_Fields();
  		
  		map.addAttribute("rep_updt", rn);
  		
  		List<Rn_Ext_Fields> report=rn_user_registration_service.update_fields_2(f_id);
  		
  		System.out.println("------sql query--------"+report);
  		
  		String mandatory= report.get(0).getMandatory();
  		String hidden= report.get(0).getHidden();
  		String val= report.get(0).getValidation_1();
  		String readonly= report.get(0).getReadonly();
  		String dependent= report.get(0).getDependent();
  		String seq= report.get(0).getSequence();
  		String sp_drp= report.get(0).getDropdown();
  		String field_name= report.get(0).getField_name();
  		
  		
  		System.out.println("mandatory---------------->"+mandatory);
  		System.out.println("hidden---------------->"+hidden);
  		System.out.println("readonly---------------->"+readonly);
  		System.out.println("dependent---------------->"+dependent);
  		System.out.println("validation---------------->"+val);
  		System.out.println("seq---------------->"+seq);
  		System.out.println("dropdown sp---------------->"+sp_drp);
  		
  		
  		System.out.println("d------field name---------->"+field_name);
  		
  		map.addAttribute("report_update", report);
  		
  		return new ModelAndView("UpdateExtFields_2");
  	}
   
   
   
   
   
   
   //-------------------------------------for property update part---------------------------------------------
   
   
   @RequestMapping(value = "/PropertyField", method = RequestMethod.GET)
 	public ModelAndView updatestatus3(@RequestParam(value = "id") String id1, ModelAndView modelview,
 			HttpServletRequest request, ModelMap map) throws IOException 
 	{
 		
	    System.out.println("------------update controller----------------------id-"+id1);
	    int f_id=Integer.parseInt(id1);
 		
 		Rn_Ext_Fields rn = new Rn_Ext_Fields();
 		map.addAttribute("rep_updt", rn);
 		
 		List<Rn_Ext_Fields> report=rn_distributor_details_dao.property_field(f_id);
 		
 		System.out.println("------sql query--------->"+report);
 		
 		String mandatory= report.get(0).getMandatory();
 		String hidden= report.get(0).getHidden();
 		String val= report.get(0).getValidation_1();
 		String readonly= report.get(0).getReadonly();
 		String dependent= report.get(0).getDependent();
 		String seq= report.get(0).getSequence();
 		String sp_drp= report.get(0).getDropdown();
 		String field_name= report.get(0).getField_name();
 		String default1= report.get(0).getDefault_1();
 		String cal_field= report.get(0).getCalculated_field();
 		String add_to_grid= report.get(0).getAdd_to_grid();
 		String sp_for_auto= report.get(0).getSp_for_autocomplete();
 		
 		
 		System.out.println("mandatory---------------->"+mandatory);
 		System.out.println("hidden------------------->"+hidden);
 		System.out.println("readonly----------------->"+readonly);
 		System.out.println("dependent---------------->"+dependent);
 		System.out.println("validation--------------->"+val);
 		System.out.println("seq---------------------->"+seq);
 		System.out.println("dropdown sp-------------->"+sp_drp);
 		System.out.println("------field name--------->"+field_name);
 		
 		System.out.println("default---------------------->"+default1);
 		System.out.println("cal field sp------------------>"+cal_field);
 		System.out.println("-add to grid------------------------->"+add_to_grid);
 		System.out.println("-sp for auto------------------------->"+sp_for_auto);
 		
 		map.addAttribute("report_update", report);
 		
 		return new ModelAndView("UpdateExtFields_2");
 	}
   
   
   
   @RequestMapping(value = "/PropertyField_form_builder", method = RequestMethod.GET)
	public ModelAndView updatestatus4(@RequestParam(value = "id") String id1, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException 
	{
		
	    System.out.println("------------update controller----------------------id-"+id1);
	    int f_id=Integer.parseInt(id1);
		
		Rn_Ext_Fields rn = new Rn_Ext_Fields();
		map.addAttribute("rep_updt", rn);
		
		List<Rn_Ext_Fields> report=rn_distributor_details_dao.property_field2(f_id);
		
		System.out.println("------sql query--------->"+report);
		
		String mandatory= report.get(0).getMandatory();
		String hidden= report.get(0).getHidden();
		String val= report.get(0).getValidation_1();
		String readonly= report.get(0).getReadonly();
		String dependent= report.get(0).getDependent();
		String seq= report.get(0).getSequence();
		String sp_drp= report.get(0).getDropdown();
		String field_name= report.get(0).getField_name();
		String default1= report.get(0).getDefault_1();
		String cal_field= report.get(0).getCalculated_field();
		String add_to_grid= report.get(0).getAdd_to_grid();
		String sp_for_auto= report.get(0).getSp_for_autocomplete();
		
		
		System.out.println("mandatory---------------->"+mandatory);
		System.out.println("hidden------------------->"+hidden);
		System.out.println("readonly----------------->"+readonly);
		System.out.println("dependent---------------->"+dependent);
		System.out.println("validation--------------->"+val);
		System.out.println("seq---------------------->"+seq);
		System.out.println("dropdown sp-------------->"+sp_drp);
		System.out.println("------field name--------->"+field_name);
		
		System.out.println("default---------------------->"+default1);
		System.out.println("cal field sp------------------>"+cal_field);
		System.out.println("-add to grid------------------------->"+add_to_grid);
		System.out.println("-sp for auto------------------------->"+sp_for_auto);
		
		map.addAttribute("report_update", report);
		
		return new ModelAndView("update_property_form_builder");
	}
   
   
   
   @RequestMapping(value = "/extfield_submitUbdatedData2", method = RequestMethod.POST)
 	public ModelAndView saveReportRegister3(@ModelAttribute Rn_Ext_Fields rn_users,
 			BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
 		
 		System.out.println("<-----------controller begins------------>");

 		//String id=request.getParameter("id");
 		
 		String field_name = request.getParameter("field_name");
 		String mapping = request.getParameter("mapping");
 		String data_type = request.getParameter("data_type");
 		String form_code = request.getParameter("form_code");
 		
 	
 		String mandatory = request.getParameter("mandatory");
 		if(mandatory!= null)
 		{
 			rn_users.setMandatory("Y");
 		}else
 		{
 			rn_users.setMandatory("N");
 		}
 		System.out.println("-------manadatory--"+mandatory);
 		
 		String hidden = request.getParameter("hidden");
 		if(hidden!= null)
 		{
 			rn_users.setHidden("Y");
 		}else
 		{
 			rn_users.setHidden("N");
 		}
 		
 		System.out.println("-------hidden--"+hidden);
 		
 		String readonly = request.getParameter("readonly");
 		if(readonly!= null)
 		{
 			rn_users.setReadonly("Y");
 		}else
 		{
 			rn_users.setReadonly("N");
 		}
 		
 		String dependent = request.getParameter("dependent");
 		if(dependent!= null)
 		{
 			rn_users.setDependent("Y");
 		}else
 		{
 			rn_users.setDependent("N");
 		}
 		
 		String dependent_on = request.getParameter("dependent_on");
 		String dependent_sp = request.getParameter("dependent_sp");
 		String dependent_sp_param = request.getParameter("dependent_sp_param");
 		
 		String validation= request.getParameter("validation_1");
 		if(validation!= null)
 		{
 			rn_users.setValidation_1("Y");
 		}else
 		{
 			rn_users.setValidation_1("N");
 		}
 		
 		String val_type = request.getParameter("val_type");
 		String val_sp = request.getParameter("val_sp");
 		String val_sp_param = request.getParameter("val_sp_param");
 		
 		String sequence = request.getParameter("sequence");
 		
 		if(sequence!= null)
 		{
 			rn_users.setSequence("Y");
 		}else
 		{
 			rn_users.setSequence("N");
 		}
 		String seq_name = request.getParameter("sequence_name");
 		String seq_sp = request.getParameter("sequence_sp");
 		String seq_sp_param = request.getParameter("sequence_sp_param");
 		
 		String default1= request.getParameter("default_1");
 		if(default1!= null)
 		{
 			rn_users.setDefault_1("Y");
 		}else
 		{
 			rn_users.setDefault_1("N");
 		}
 		
 		
 		String default_value = request.getParameter("default_value");
 		String default_sp = request.getParameter("default_sp");
 		String default_sp_param = request.getParameter("default_sp_param");
 		
 		String cal_field = request.getParameter("calculated_field");
 		if(cal_field!= null)
 		{
 			rn_users.setCalculated_field("Y");
 		}else
 		{
 			rn_users.setCalculated_field("N");
 		}
 		
 		
 		
 		
 		String cal_sp = request.getParameter("cal_sp");
 		String cal_sp_param = request.getParameter("cal_sp_param");

 		
 		String add_to_grid = request.getParameter("add_to_grid");
 		if(add_to_grid!= null)
 		{
 			rn_users.setAdd_to_grid("Y");
 		}else
 		{
 			rn_users.setAdd_to_grid("N");
 		}
 		String dropdown = request.getParameter("dropdown");
 		if(dropdown!= null)
 		{
 			rn_users.setDropdown("Y");
 		}else
 		{
 			rn_users.setDropdown("N");
 		}
 		
 		
 		
 		String sp_name = request.getParameter("sp_name");
 		
 		String sp_for_autocomplete = request.getParameter("sp_for_autocomplete");
 		
 		if(sp_for_autocomplete!= null)
 		{
 			rn_users.setSp_for_autocomplete("Y");
 		}else
 		{
 			rn_users.setSp_for_autocomplete("N");
 		}

 		String radio = request.getParameter("radio");
 		if(radio!= null)
 		{
 			rn_users.setRadio("Y");
 		}else
 		{
 			rn_users.setRadio("N");
 		}
 		
 		
 		String radio_option = request.getParameter("radio_option");
 		
 		//int rowcount=id.length;
 		//System.out.println("ROWCOUNT--------------->"+rowcount);

 		int report = rn_distributor_details_dao.addFields1(rn_users);
 		System.out.println("REPORT--------------->"+report);
 		
 		System.out.println("--end of report---");
 		
 		
 		
 		String check = request.getParameter("repupdt");
 		
 		map.addAttribute("check", check);
 		map.addAttribute("report", report);
 		
 		Rn_Ext_Fields rep_reg = new Rn_Ext_Fields();
 		map.addAttribute("rep_reg", rep_reg);
 		
 		List<Rn_Ext_Fields> report_list = rn_user_registration_dao.ext_userlist();
 		map.addAttribute("report_list", report_list);
 		System.out.println("-----------controller end--------");
 		
 		
 		return new ModelAndView("redirect:extensiondetails");
 	}
   
   
   
  
   @RequestMapping(value = "/extfield_submit_form_builder", method = RequestMethod.POST)
	public ModelAndView saveReportRegister5(@ModelAttribute Rn_Ext_Fields rn_users,
			BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
		
		System.out.println("<-----------controller begins------------>");

		//String id=request.getParameter("id");
		
		String field_name = request.getParameter("field_name");
		String mapping = request.getParameter("mapping");
		String data_type = request.getParameter("data_type");
		String form_code = request.getParameter("form_code");
		
		System.out.println("------value of from code-------"+form_code);
		
		String type = request.getParameter("type");
		
	
		String mandatory = request.getParameter("mandatory");
		if(mandatory!= null)
		{
			rn_users.setMandatory("Y");
		}else
		{
			rn_users.setMandatory("N");
		}
		System.out.println("-------manadatory--"+mandatory);
		
		String hidden = request.getParameter("hidden");
		if(hidden!= null)
		{
			rn_users.setHidden("Y");
		}else
		{
			rn_users.setHidden("N");
		}
		
		System.out.println("-------hidden--"+hidden);
		
		String readonly = request.getParameter("readonly");
		if(readonly!= null)
		{
			rn_users.setReadonly("Y");
		}else
		{
			rn_users.setReadonly("N");
		}
		
		String dependent = request.getParameter("dependent");
		if(dependent!= null)
		{
			rn_users.setDependent("Y");
		}else
		{
			rn_users.setDependent("N");
		}
		
		String dependent_on = request.getParameter("dependent_on");
		String dependent_sp = request.getParameter("dependent_sp");
		String dependent_sp_param = request.getParameter("dependent_sp_param");
		
		String validation= request.getParameter("validation_1");
		if(validation!= null)
		{
			rn_users.setValidation_1("Y");
		}else
		{
			rn_users.setValidation_1("N");
		}
		
		String val_type = request.getParameter("val_type");
		String val_sp = request.getParameter("val_sp");
		String val_sp_param = request.getParameter("val_sp_param");
		
		String sequence = request.getParameter("sequence");
		
		if(sequence!= null)
		{
			rn_users.setSequence("Y");
		}else
		{
			rn_users.setSequence("N");
		}
		String seq_name = request.getParameter("sequence_name");
		String seq_sp = request.getParameter("sequence_sp");
		String seq_sp_param = request.getParameter("sequence_sp_param");
		
		String default1= request.getParameter("default_1");
		if(default1!= null)
		{
			rn_users.setDefault_1("Y");
		}else
		{
			rn_users.setDefault_1("N");
		}
		
		
		String default_value = request.getParameter("default_value");
		String default_sp = request.getParameter("default_sp");
		String default_sp_param = request.getParameter("default_sp_param");
		
		String cal_field = request.getParameter("calculated_field");
		if(cal_field!= null)
		{
			rn_users.setCalculated_field("Y");
		}else
		{
			rn_users.setCalculated_field("N");
		}
		
		
		
		
		String cal_sp = request.getParameter("cal_sp");
		String cal_sp_param = request.getParameter("cal_sp_param");

		
		String add_to_grid = request.getParameter("add_to_grid");
		if(add_to_grid!= null)
		{
			rn_users.setAdd_to_grid("Y");
		}else
		{
			rn_users.setAdd_to_grid("N");
		}
		String dropdown = request.getParameter("dropdown");
		if(dropdown!= null)
		{
			rn_users.setDropdown("Y");
		}else
		{
			rn_users.setDropdown("N");
		}
		
		
		
		String sp_name = request.getParameter("sp_name");
		
		String sp_for_autocomplete = request.getParameter("sp_for_autocomplete");
		
		if(sp_for_autocomplete!= null)
		{
			rn_users.setSp_for_autocomplete("Y");
		}else
		{
			rn_users.setSp_for_autocomplete("N");
		}

		
		
		//int rowcount=id.length;
		//System.out.println("ROWCOUNT--------------->"+rowcount);

		int report = rn_distributor_details_dao.addFields2(rn_users);
		
		System.out.println("REPORT--------------->"+report);
		
		System.out.println("--end of report---");
		
		
		
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("report", report);
		
		Rn_Ext_Fields rep_reg = new Rn_Ext_Fields();
		map.addAttribute("rep_reg", rep_reg);
		
		List<Rn_Ext_Fields> report_list = rn_user_registration_dao.ext_userlist2(form_code);
		map.addAttribute("report_list", report_list);
		System.out.println("-----------controller end--------");
		
		
		return new ModelAndView("redirect:rn_form_builder_extension");
	}
   
   
   ///////////////load dependent///////////////////////
    @RequestMapping(value = "/load_dependent", method = RequestMethod.GET)
	 public void load_dependent( HttpServletRequest request, HttpServletResponse response) 
    {
	 List<Rn_Test_Dependent> dependent_list = new ArrayList<Rn_Test_Dependent>();
	 try {
			 
			 
			 //int user_id = (Integer) request.getSession().getAttribute("userid");
			 //System.out.println("user_id" +user_id);
			 //if(user_id != 0)
				//{

					System.out.println("-----------------ganesh sp procedure mapping---------");
			 		CallableStatement cStmt;
			 		try 
			 		{
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call TEST_DEP(?)}");
			 		System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		cStmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			 		
			 		ResultSet rs = cStmt.executeQuery();
			 		System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data1 = rs.getString(1);
			 			System.out.println("data1---------"+data1);
			 			
			 			Rn_Test_Dependent dep = new Rn_Test_Dependent();
			 			
			 			dep.setVal_b(data1);
			 			dependent_list.add(dep);
			 		  }
			 		} catch (SQLException e) {
			  		e.printStackTrace();
			 		}
			 		catch (Exception e) 
	 		 		{
			 		   System.out.println(e.getMessage());
			 		}

					
				//}
			 String json = null;
			 
			 json = new Gson().toJson(dependent_list);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

}
   
  
    
    
    @RequestMapping(value = "/load_dropdown_ext", method = RequestMethod.GET)
	 public void for_dropdown( HttpServletRequest request, HttpServletResponse response)  {
 	 List<Rn_Ext_Fields> droplist = new ArrayList<Rn_Ext_Fields>();
 	 try {
			 
			  HttpSession session=request.getSession(false);
 	          String sp_drop=(String)session.getAttribute("sp_for_dd_1");
 	          
 	          
 		      System.out.println("--------------sp for dropdown name ----------------------"+sp_drop);
 		       

			 		CallableStatement cStmt;
			 		try {
			 		cStmt = hibernateConfiguration.dataSource().getConnection()
			 			.prepareCall("{call "+sp_drop+"()}");
			 		
			 		
			 		System.out.println("ganesh sp procedure---------"+cStmt);
			 		
			 		ResultSet rs = cStmt.executeQuery();
			 		System.out.println("ganesh ResultSet---------"+rs);

			 		
			 		while (rs.next()) 
			 		{
			 			
			 			String data = rs.getString(1);
			 			System.out.println("data1---------"+data);
			 			
			 			Rn_Ext_Fields menu = new Rn_Ext_Fields();
			 			menu.setDrop_value(data);
			 			
			 			droplist.add(menu);
			 		}
			 		} catch (SQLException e) {
			 		e.printStackTrace();
			 		}
			 		catch (Exception e) {
			 		System.out.println(e.getMessage());
			 		}

					
				
			 String json = null;
			 
			 json = new Gson().toJson(droplist);
			 response.setContentType("application/json");
			 response.getWriter().write(json);
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }

  }
    


 // -----------------------------------for fb header grid view only--------------------------------------------------

 	@RequestMapping(value="/rn_fb_header_grid_view")
 	public ModelAndView fb_header(HttpServletRequest request,ModelAndView model) throws IOException
 	{
 		 HttpSession session=request.getSession(false);
 	     int project_id=(Integer)session.getAttribute("project_id");
 	     System.out.println("project id:::::::::::::::::"+project_id);
 	     
 	     HttpSession session1=request.getSession(false);
 	     int module_id=(Integer)session1.getAttribute("module_id");
 	     System.out.println("module_id id:::::::::::::::::"+module_id);
 	     
 		List<Rn_Fb_Header> rn_fb_header = rn_distributor_details_dao.fb_headerlist(project_id,module_id);
 		model.addObject("rn_fb_header", rn_fb_header);
 		
// 		int project_id1 = rn_fb_header.get(0).getProject_id();
// 		int module_id1 = rn_fb_header.get(0).getModule_id();
//
// 		System.out.println("sujit"+module_id1);
 		model.setViewName("Rn_Fb_Header_Grid");
 		return model;
 	}

     
    
 // ----------------------entry form ------------------------------------------
    
    @RequestMapping("/rn_fb_entryform")
	public ModelAndView input_form(HttpServletRequest request, ModelMap map,Model model) 
    {
		int project_id = (Integer) request.getSession().getAttribute("project_id");
	     System.out.println("project id rn_fb_entryform:::::::::::::::::"+project_id);
		int module_id = (Integer) request.getSession().getAttribute("module_id");
	     System.out.println("module id rn_fb_entryform:::::::::::::::::"+module_id);


		return new ModelAndView("Rn_Fb_EntryForm");
		
	}
    
 // ----------------------entry form sbmit------------------------------------------

    @Transactional
    @RequestMapping(value = "/rn_fb_entryform_submit", method = RequestMethod.POST)
    public ModelAndView saveServiceRequest(@ModelAttribute Rn_Fb_Header  fb,BindingResult resultKoel_user ,
    											Model model,ModelMap map, HttpServletRequest request)  
    {	 	 
    	
    		HttpSession session=request.getSession(false);
    	    int project_id=(Integer)session.getAttribute("project_id");
    	    
    	    HttpSession session1=request.getSession(false);
    	    int module_id=(Integer)session1.getAttribute("module_id");
    	    
    		String form_type = request.getParameter("form_type");
            String table_name = request.getParameter("table_name");
            String line_table_name = request.getParameter("line_table_name");
    		String jsp_name = request.getParameter("jsp_name");	
    		String form_code = request.getParameter("form_code");
    		String controller_name = request.getParameter("controller_name");
    		String service_name = request.getParameter("service_name");
    		String service_impl_name = request.getParameter("service_impl_name");	
    		String dao_name = request.getParameter("dao_name");
    		String dao_impl_name = request.getParameter("dao_impl_name");	
    		System.out.println("entry names:"+table_name+"\njsp_name: "+jsp_name+" \nform_code:"+form_code);
    		
    		String multi_line = request.getParameter("multiple_line_table_name");
    		
    		
    		System.out.println("multiple line::"+multi_line);
    		
    		
    		System.out.println("multiple lines ");
    		
    		fb.setProject_id(project_id);
    		fb.setModule_id(module_id);
    		fb.setForm_type(form_type);
    		fb.setTable_name(table_name);
    		fb.setJsp_name(jsp_name);
    		fb.setForm_code(form_code);
    		fb.setController_name(controller_name);
    		fb.setService_name(service_name);
    		fb.setService_impl_name(service_impl_name);
    		fb.setDao_name(dao_name);
    		fb.setDao_impl_name(dao_impl_name);
    		fb.setLine_table_name(line_table_name);
    		fb.setIs_build("N");
    		fb.setIs_updated("N");
    		
    		hibernateTemplate.saveOrUpdate(fb);
         
    		String [] abc = multi_line.split(",");

    		int i=0;
    		for(String name: abc)
    		{
    			i++;
    			System.out.println("multi :"+name+i);
    			
    		}
    		
    		System.out.println("project id in list field::"+project_id);
    		System.out.println("module id in list field::"+module_id);

    		
    //-----------for header table----------------------	
    		String type1="header";
    		String type_field="textfield";
            System.out.println("-----------sp start header----------");
    		CallableStatement cStmt;
     		try {
     		cStmt = hibernateConfiguration.dataSource().getConnection()
     			.prepareCall("{call add_fb_lines_sp(?,?,?,?,?,?)}");
     		cStmt.setString(1,table_name);
     		cStmt.setString(2,form_code);
     		cStmt.setString(3,type1);
     		cStmt.setString(4,type_field);
     		cStmt.setInt(5,project_id);
     		cStmt.setInt(6,module_id);
     		
     		System.out.println("-----------sp execute headre----------");
     		
     		} catch (SQLException e) {
     		e.printStackTrace();
     		}
     		catch (Exception e) {
     		System.out.println(e.getMessage());
     		}
    		
    //-----------for line table	----------------------	
     		
     		if(line_table_name !=null && !line_table_name.isEmpty())
     		{
     		      System.out.println("-----------sp start for line----------");
     				
     		      //String type11="header-line";
     		      String type11="line";
     		  
     		 		try 
     		 		{
    	 		 		cStmt = hibernateConfiguration.dataSource().getConnection()
    	 		 			.prepareCall("{call add_fb_lines_sp(?,?,?,?,?,?)}");
    	 		 		cStmt.setString(1,line_table_name);
    	 		 		cStmt.setString(2,form_code);
    	 		 		cStmt.setString(3,type11);
    	 		 		cStmt.setString(4,type_field);
    	 		 		cStmt.setInt(5,project_id);
    	 		 		cStmt.setInt(6,module_id);
    	 		 		
    	 		 		System.out.println("-----------sp execute line----------");
    	 		 		
     		 		} catch (SQLException e)
     		 		{
     		 		   e.printStackTrace();
     		 		}
     		 		catch (Exception e) {
     		 		System.out.println(e.getMessage());
     		 		}	
     		}
     					
    	return new ModelAndView("redirect:rn_fb_header_grid_view");					
    }
// --------------------for fb header readonly------------------------------------------------

    
    @RequestMapping(value = "/rn_fb_header_readonly", method = RequestMethod.GET)
    public ModelAndView fb_header(@RequestParam(value = "form_code") String f_code, ModelAndView modelview,
    		HttpServletRequest request, ModelMap map) throws IOException 
    {
    	List<Rn_Fb_Header> rn_fb_header = rn_user_registration_service.fb_header(f_code);
    	map.addAttribute("rn_fb_header", rn_fb_header);
    	
    	
    	return new ModelAndView("Rn_Fb_Header_Readonly");
    }



	//-----------------------------------for fb line grid view only--------------------------------------------------


		@RequestMapping(value = "/rn_fb_line_grid_view")
		public ModelAndView fb_line(HttpServletRequest request, ModelAndView model) throws IOException {

			String n = request.getParameter("form_code");
			HttpSession session = request.getSession();
			session.setAttribute("form_code", n);
			String url = environment.getRequiredProperty("jdbc.url");
			String user_name = environment.getRequiredProperty("jdbc.username");
			String password = environment.getRequiredProperty("jdbc.password");
			System.out.println("username pass url::" + url + user_name + password);
			session.setAttribute("url", url);
			session.setAttribute("user_name", user_name);
			session.setAttribute("password", password);

			List<Rn_Ext_Fields> rn_ext_fields = rn_distributor_details_dao.fb_linelist(n);

			model.addObject("rn_ext_fields", rn_ext_fields);
			System.out.println("sujit");
			model.setViewName("Rn_Fb_Line_Grid");

			return model;
		}

		// ----------------------User entry form ------------------------------------------

	    @RequestMapping("/rn_user_entryform")
		public ModelAndView input_form3(HttpServletRequest request, ModelMap map,Model model) 
	    {
			return new ModelAndView("Rn_User_EntryForm");
				 
			
		}
    
    

@Transactional
@RequestMapping(value = "/save_form_entry", method = RequestMethod.POST)
public ModelAndView saveLookup1(@ModelAttribute Rn_Fb_Header formbuilder, BindingResult result, 
		ModelMap map, HttpServletRequest request) throws ParseException
{
	
	//int user_id = (Integer) request.getSession().getAttribute("userid");
	
	
	
		//int id=Integer.parseInt(request.getParameter("id"));
		String table_name=request.getParameter("TABLE_NAME");
		String jsp_name=request.getParameter("JSP_NAME");
		String form_code=request.getParameter("FORM_CODE");
		String controller_name=request.getParameter("CONTROLLER_NAME");
		String service_name=request.getParameter("SERVICE_NAME");
		String service_impl_name=request.getParameter("SERVICE_IMPL_NAME");
		String dao_name=request.getParameter("DAO_NAME");
		String dao_impl_name=request.getParameter("DAO_IMPL_NAME");

		
		//formbuilder.setId(id);
		formbuilder.setTable_name(table_name);
		formbuilder.setJsp_name(jsp_name);
		formbuilder.setForm_code(form_code);
		formbuilder.setController_name(controller_name);
		formbuilder.setService_name(service_name);
		formbuilder.setService_impl_name(service_impl_name);
		formbuilder.setDao_name(dao_name);
		formbuilder.setDao_impl_name(dao_impl_name);
		

		hibernateTemplate.saveOrUpdate(formbuilder);
	
	return new ModelAndView("redirect:rn_fb_entry");
	//return new ModelAndView("MenuRegister");
		
}



@RequestMapping(value="/entrygrid")
public ModelAndView entrygrid(ModelAndView model) throws IOException
{
	System.out.println("--------------controller begins-----------");
	List<Rn_Fb_Header> ext_userlist = rn_distributor_details_dao.entry_list();
	
	model.addObject("ext_userlist", ext_userlist);
	
    System.out.println("sujit");
	model.setViewName("entry_grid");
	
	return model;
}
    

@RequestMapping(value ="/build_form", method = RequestMethod.GET)
public ModelAndView writeView(Rn_Menu_Header menu_header,HttpServletRequest request, HttpServletResponse response,ModelMap model) throws IOException {
	StringBuilder togglescript=new StringBuilder();
	String projectPath=rn_project_setup_dao.getProjectPath(); 
    StringBuilder strContent = new StringBuilder();
    StringBuilder strContentprefield = new StringBuilder();
    StringBuilder strContentreadonly = new StringBuilder();
    StringBuilder controller = new StringBuilder();
    StringBuilder dao = new StringBuilder();
    StringBuilder dao_impl = new StringBuilder();
    StringBuilder service = new StringBuilder();
    StringBuilder service_impl = new StringBuilder();
    StringBuilder view = new StringBuilder();
    StringBuilder para = new StringBuilder();
    StringBuilder set_para = new StringBuilder();
    StringBuilder model_class = new StringBuilder();
    StringBuilder model_class1 = new StringBuilder();
    StringBuilder model_class2 = new StringBuilder();
    StringBuilder model_class3 = new StringBuilder();
    StringBuilder model_class4 = new StringBuilder();
    StringBuilder table_grid_view = new StringBuilder();
    StringBuilder table_field=new StringBuilder();
    StringBuilder table_field_value=new StringBuilder();
    StringBuilder grid_controller=new StringBuilder();
    StringBuilder sqlField=new StringBuilder();
    StringBuilder setField=new StringBuilder();
    StringBuilder table_field_for_id=new StringBuilder();
    StringBuilder table_field_value_for_id=new StringBuilder();
    StringBuilder service_save_id=new StringBuilder();
    StringBuilder service_save_id1=new StringBuilder();
    StringBuilder service_save_field=new StringBuilder();
    StringBuilder service_save_field1=new StringBuilder();
    StringBuilder form_id=new StringBuilder();
    StringBuilder dao_save_id=new StringBuilder();
    StringBuilder dao_save_field=new StringBuilder();
    StringBuilder sqlField_id=new StringBuilder();
    StringBuilder setField_id=new StringBuilder();
    StringBuilder dao_impl_prefield=new StringBuilder();
    StringBuilder prefield_controller = new StringBuilder();
    StringBuilder prefield_controller_readonly = new StringBuilder();
    StringBuilder sbmit_parameterfield=new StringBuilder();
    StringBuilder sbmit_parameterid=new StringBuilder();
    StringBuilder save_controller=new StringBuilder();
    StringBuilder form = new StringBuilder();
    StringBuilder importsection = new StringBuilder();
    StringBuilder headsection = new StringBuilder();
    StringBuilder form_prefield = new StringBuilder();
    StringBuilder importsectionprefield = new StringBuilder();
    StringBuilder headsectionprefield = new StringBuilder();
    StringBuilder form_readonly = new StringBuilder();
    StringBuilder importsectionreadonly = new StringBuilder();
    StringBuilder headsectionreadonly = new StringBuilder();
    StringBuilder field_for_save_id = new StringBuilder();
    StringBuilder field_for_save_field = new StringBuilder();
    StringBuilder dao_impl_save = new StringBuilder();
    StringBuilder id_notpri_para = new StringBuilder();
    StringBuilder id_notpri_set = new StringBuilder();
    StringBuilder date_array_set_in_dao = new StringBuilder();
    StringBuilder id_para_submit = new StringBuilder();
    StringBuilder var_for_pass_para = new StringBuilder();
    StringBuilder var_for_pass_para_id = new StringBuilder();
    StringBuilder model_class_for_var = new StringBuilder();
    StringBuilder model_class_for_set = new StringBuilder();
    StringBuilder date_para = new StringBuilder();
    StringBuilder date_set = new StringBuilder();
    StringBuilder date_model_variable = new StringBuilder();
    StringBuilder date_model_set_variable = new StringBuilder();
    StringBuilder date_array_para = new StringBuilder();
    StringBuilder id_array_set_in_dao2 = new StringBuilder();
    StringBuilder date_array_value = new StringBuilder();
    StringBuilder id_array_set_in_dao = new StringBuilder();
    StringBuilder date_array_set_in_dao_for_date = new StringBuilder();
    StringBuilder var_for_pass_para_id_for_upper = new StringBuilder();
    StringBuilder date_array_value_for_lower = new StringBuilder();
    StringBuilder id_not_pri_field_for_grid = new StringBuilder();
    StringBuilder id_not_pri_field_for_grid_value = new StringBuilder();
    StringBuilder date_field_for_grid = new StringBuilder();
    StringBuilder date_field_for_grid_value = new StringBuilder();
    StringBuilder line_value = new StringBuilder();
    StringBuilder for_line_part = new StringBuilder();
    StringBuilder line_script = new StringBuilder();
    
    
    StringBuilder header_line_submit = new StringBuilder();
    StringBuilder header_line_submit_id = new StringBuilder();
    StringBuilder header_line_submit_varchar = new StringBuilder();
    
    
    
    StringBuilder get_id_for_sbmit_header_line = new StringBuilder();
    
    StringBuilder header_id_for_line = new StringBuilder();
    
    
    
    StringBuilder line_id_for_rowcount = new StringBuilder();
    
    StringBuilder array_para_for_id_line = new StringBuilder();

    StringBuilder array_para_for_varchar_line = new StringBuilder();
    
    
    StringBuilder action2 = new StringBuilder();

    
    
    
    StringBuilder parameter_for_header_id = new StringBuilder();
    
    StringBuilder parameter_for_line_id = new StringBuilder();
    StringBuilder parameter_for_line_varchar = new StringBuilder();

    
    StringBuilder dropdown_controller = new StringBuilder();
    
    StringBuilder get_parameter_for_id_line = new StringBuilder();
    
    StringBuilder set_parameter_header_id_line = new StringBuilder();
    StringBuilder set_parameter_for_id_line = new StringBuilder();
    StringBuilder set_parameter_for_varchar_line = new StringBuilder();
    
    
    StringBuilder dao_impl_save_line = new StringBuilder();
    
    StringBuilder model_class_for_line = new StringBuilder();
    
    StringBuilder model_class_line1 = new StringBuilder();
    StringBuilder model_class_line2 = new StringBuilder();
    StringBuilder model_class_line3 = new StringBuilder();
    StringBuilder model_class_line4 = new StringBuilder();
    
    
    StringBuilder id_primary_parameter_submit = new StringBuilder();
    
    
    StringBuilder set_id_primary_for_header = new StringBuilder();
    
    
    StringBuilder autocomplete_script_latest = new StringBuilder();
    
    
    StringBuilder header_id_for_line_for_comma = new StringBuilder();

    
    
    StringBuilder get_set_for_line_update_id = new StringBuilder();
    
    StringBuilder get_set_for_line_update_varchar = new StringBuilder();

    
    StringBuilder dao_impl_for_update_line = new StringBuilder();
    
    StringBuilder line_script_update = new StringBuilder();

    
    StringBuilder for_line_part_update = new StringBuilder();
    
    
    StringBuilder line_value_update = new StringBuilder();

    
    StringBuilder for_line_part_readonly = new StringBuilder();
    
    
    StringBuilder line_value_readonly = new StringBuilder();
    
    
    StringBuilder dropdown_script = new StringBuilder();
    StringBuilder dependent_dropdown = new StringBuilder();
    StringBuilder autocomplete_script = new StringBuilder();

    
    
    StringBuilder model_class_for_attribute = new StringBuilder();
    StringBuilder model_class_for_attribute2 = new StringBuilder();

    
    
    StringBuilder attribute_for_select_statment = new StringBuilder();

    
    
    StringBuilder attribute_set_for_grid_dao = new StringBuilder();

    
    
    StringBuilder attribute_for_select_statment_lower = new StringBuilder();

    
    StringBuilder attribute_set_for_grid_dao_line = new StringBuilder();

    
    
    StringBuilder get_parameter = new StringBuilder();
    
    StringBuilder set_parameter = new StringBuilder();
    
    StringBuilder action = new StringBuilder();
    
    
    StringBuilder date_script = new StringBuilder();
    StringBuilder dropdown_script_latest = new StringBuilder();

    
    StringBuilder dropdown_script_latest_for_line = new StringBuilder();
    
    HttpSession session=request.getSession(false);
    String f_code=(String)session.getAttribute("form_code");

    List<Rn_Fb_Line> rn_userlist = rn_distributor_details_dao.fb_linelist2(f_code);
    
    List<Rn_Ext_Fields> id_list = rn_distributor_details_dao.fb_listForId(f_code);
    
    List<Rn_Lookup_Values> attribute_flex = rn_distributor_details_dao.attribute_values();

    
    String field_name_for_id=id_list.get(0).getField_name();
    String field_name_for_id_lower=field_name_for_id.toLowerCase();
    String field_name_for_id_upper=field_name_for_id.toUpperCase();
    String field_name_first_upper = field_name_for_id_lower.substring(0, 1).toUpperCase() + field_name_for_id_lower.substring(1);

    List<Rn_Fb_Line> id_notprimary = rn_distributor_details_dao.fb_listForId_not_pri(f_code);
    
    List<Rn_Fb_Line> datetime_list = rn_distributor_details_dao.fb_listDatetime(f_code);
    
    
    
    System.out.println("-----------value of id-------"+f_code);
    
    System.out.println("-----------build from started-------");
     
	 List<Rn_Fb_Header> report = rn_user_registration_service.fb_header(f_code);
	 
     String controller_name=report.get(0).getController_name();
     String dao_name=report.get(0).getDao_name();
     String dao_impl_name=report.get(0).getDao_impl_name();
     String jsp_name=report.get(0).getJsp_name();
     String table_name=report.get(0).getTable_name();
     String service_name=report.get(0).getService_name();
     String service_impl_name=report.get(0).getService_impl_name();
     String line_table_name=report.get(0).getLine_table_name();
     
     System.out.println("---value of line table name---"+line_table_name);
     
     String controller_name_lower=controller_name.toLowerCase();
     String controller_name_first_upper=controller_name_lower.substring(0, 1).toUpperCase()+controller_name_lower.substring(1);

     String service_name_lower=service_name.toLowerCase();
     String service_name_first_upper=service_name_lower.substring(0, 1).toUpperCase()+service_name_lower.substring(1);
     String service_name_upper=service_name.toUpperCase();
     
     String service_impl_name_lower=service_impl_name.toLowerCase();
     String service_impl_name_first_upper=service_impl_name_lower.substring(0, 1).toUpperCase()+service_impl_name_lower.substring(1);
     String service_impl_name_upper=service_impl_name.toUpperCase();
     
     String dao_name_lower=dao_name.toLowerCase();
     String dao_name_first_upper=dao_name_lower.substring(0, 1).toUpperCase()+dao_name_lower.substring(1);
     String dao_name_upper=dao_name.toUpperCase();
     
     
     String dao_impl_name_lower=dao_impl_name.toLowerCase();
     String dao_impl_name_first_upper=dao_impl_name_lower.substring(0, 1).toUpperCase()+dao_impl_name_lower.substring(1);
     String dao_impl_name_upper=dao_impl_name.toUpperCase();
     
     
     String table_name_lower=table_name.toLowerCase();
     String table_name_first_upper=table_name_lower.substring(0, 1).toUpperCase() + table_name_lower.substring(1);
     String table_name_upper=table_name.toUpperCase();
     
   // if(line_table_name !=null)
    //{
    // String line_table_name_lower=line_table_name.toLowerCase();
    // String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
    // String line_table_name_upper=line_table_name.toUpperCase();
    // }
     
     
     System.out.println("table name  first upper::"+table_name_first_upper);
     System.out.println("controler name from build from::"+controller_name);
     System.out.println("service name from build from::"+dao_name);
     System.out.println("jsp name from build from::"+jsp_name);
     
   //---------------------for------line-----------------------------------  
     List<Rn_Ext_Fields> line_id_primary = rn_distributor_details_dao.line_primary_id(f_code);
     
     List<Rn_Fb_Line> line_varchar = rn_distributor_details_dao.for_line_part(f_code);
     
     List<Rn_Ext_Fields> line_id_not_primary = rn_distributor_details_dao.lineForId_not_pri(f_code);
     
     
     
     
     
   //-------------------------------attribute flex values---------------------------------------------------------------  
     
     for(int i=0;i<attribute_flex.size();i++)
		{
    	     
    	  String lookup_code=attribute_flex.get(i).getLookup_code();
    	  String lower_case=lookup_code.toLowerCase();
    	  String first_upper=lower_case.substring(0,1).toUpperCase()+lower_case.substring(1);
    	  String only_upper=lookup_code.toUpperCase();
    	  
    	  attribute_set_for_grid_dao.append("\n"+table_name_lower+".set"+first_upper+"(rs.getString(\""+only_upper+"\"));");
		  
    	  if(line_table_name != null && !line_table_name.isEmpty())
          {   
				System.out.println("---under iif------");
    		    String line_table_name_lower=line_table_name.toLowerCase();
		        String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
		        String line_table_name_upper=line_table_name.toUpperCase();
    	        attribute_set_for_grid_dao_line.append("\n"+line_table_name_lower+".set"+first_upper+"(rs.getString(\""+only_upper+"\"));");
          }
		  
    	  attribute_for_select_statment.append(only_upper);
    	  
    	  if(i<(attribute_flex.size()-1))
     	  {
    		  attribute_for_select_statment.append(",");
     	  }
		  
    	  attribute_for_select_statment.append("\t");
    	  
    	  
    	  
        attribute_for_select_statment_lower.append(lower_case);
    	  
    	  if(i<(attribute_flex.size()-1))
     	  {
    		  attribute_for_select_statment_lower.append(",");
     	  }
    	  attribute_for_select_statment_lower.append("\t");
    	  
    	  
    	  model_class_for_attribute.append("\n@Column(name = \""+only_upper+"\")"
                  +"\nprivate String \t"+lower_case+";");

          model_class_for_attribute2.append("\n\npublic String get"+first_upper+"() \n{"
                  +"\nreturn\t" +lower_case+";\n}"

                  +"\n\npublic void set"+first_upper+"(String\t" +lower_case+")\n{"
                  +"\nthis."+lower_case+ "=" +lower_case+";"
                  +"\n}");
    	  
    	  get_parameter.append("\nString \t"+lower_case+"=request.getParameter(\""+lower_case+"\");");
    	  
    	  set_parameter.append("\n"+table_name_lower+".set"+first_upper+"("+lower_case+");"
					    	  	   +"\n"+table_name_lower+".setCreated_by(user_id);"
						           +"\n"+table_name_lower+".setLast_updated_by(user_id);");
			
			
			}
     
     
    
     
     for(int i=0;i<id_list.size();i++)
		
		{
			
 	       String field_name=id_list.get(i).getField_name();
 	       String lower_case=field_name.toLowerCase();
 	       String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
 	       String only_upper=field_name.toUpperCase();
 	       
 	       sbmit_parameterid.append("\nString[]\t" +lower_case+"=request.getParameterValues(\""+lower_case+"\");");
 	       set_id_primary_for_header.append("\n"+table_name_lower+".set"+first_upper+"(id);");
 	       
 	       id_primary_parameter_submit.append("\nif(request.getParameter(\""+lower_case+"\")!=\"\")"
	                                           +"\n{"
 	       		                             + "\nint id=Integer.parseInt(request.getParameter(\""+lower_case+"\"));"+set_id_primary_for_header+"\n}");
 	       
 	       
 	       
 	    }
  

    for(int i=0;i<rn_userlist.size();i++)
		{
    	 String field_name=rn_userlist.get(i).getField_name();
    	 String lower_case=field_name.toLowerCase();
    	 String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
    	 System.out.println("------for lowecase string-----------::"+lower_case);
    	 System.out.println("------for uper first letter---------::"+first_upper);
    	 
    	 para.append("\n\t String "+lower_case+"=request.getParameter(\""+lower_case+"\");");
    	 
    	 set_para.append("\n\t"+table_name_lower+".set"+first_upper+"("+lower_case+");");
    	 
    	 
	     sbmit_parameterfield.append("\nString[]\t" +lower_case+"=request.getParameterValues(\""+lower_case+"\");");
       }
    
	
	for(int i=0;i<id_notprimary.size();i++)
       {
    	String field_name=id_notprimary.get(i).getField_name();
    	String data_type=id_notprimary.get(i).getData_type();
   	    String lower_case=field_name.toLowerCase();
   	    String only_upper=field_name.toUpperCase();
   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
   	    if(data_type.equals("int"))
   	    {
   	    	
   	    	
   	      id_para_submit.append("int\t" +lower_case+"=Integer.parseInt(request.getParameter(\""+lower_case+"\"));");
   	      
   	      id_notpri_para.append("\nString[]\t" +lower_case+"=request.getParameterValues(\""+lower_case+"\");");
   	      
   	      id_notpri_set.append("\n\t"+table_name_lower+".set"+first_upper+"("+lower_case+");");
   	      
   	      
   	      model_class_for_var.append("\n@Column(name = \""+only_upper+"\")"
               +"\nprivate int \t"+lower_case+";");

          model_class_for_set.append("\n\npublic int get"+first_upper+"() \n{"
               +"\nreturn\t" +lower_case+";\n}"

               +"\n\npublic void set"+first_upper+"(int\t" +lower_case+")\n{"
               +"\nthis."+lower_case+ "=" +lower_case+";"
               +"\n}");
          
          var_for_pass_para.append("String[]\t" +lower_case+",");
          
          var_for_pass_para_id.append(lower_case+",");
          
          var_for_pass_para_id_for_upper.append(only_upper+",");
          
  	      id_array_set_in_dao.append("\n"+table_name_lower+".set"+first_upper+"(rs.getInt(\""+only_upper+"\"));");
  	      id_array_set_in_dao2.append("\n"+table_name_lower+".set"+first_upper+"(Integer.parseInt("+lower_case+"[i]));");


   	    }
       }
    
    for(int i=0;i<datetime_list.size();i++)
    {
 	    String field_name=datetime_list.get(i).getField_name();
 	    String data_type=datetime_list.get(i).getData_type();
 	    
	    String lower_case=field_name.toLowerCase();
	    String only_upper=field_name.toUpperCase();
	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
	    
	    if(data_type.equals("datetime"))
   	    { 
	    	date_para.append("\n Date\t" +lower_case+" = null;"
			+"\ntry\n {"
				+"\n"+lower_case+" = new SimpleDateFormat(\"dd-MM-yyyy\").parse(request.getParameter(\""+lower_case+"\"));"
			+"\n} catch (ParseException e) \n{"
			
				+"\ne.printStackTrace();"
			+"\n}");
	       date_set.append("\n"+table_name_lower+".set"+first_upper+"("+lower_case+");");
	    
	       date_model_variable.append("\n\n@Column(name = \""+only_upper+"\")"
	                         +"\nprivate Date\t"+lower_case+";");
	    
	       date_model_set_variable.append("\n\npublic Date get"+first_upper+"(){"
		                         +"\nreturn\t"+ lower_case+";"
	                            +"\n }"

	                            +"\n\npublic void set"+first_upper+"(Date\t" +lower_case+")" 
	                            +"\n{"
		                        +"\nthis."+lower_case+" = "+lower_case+";"
	                           +"\n}");
	    date_array_para.append("Date\t"+ lower_case+",");
	    
	    
	    date_array_value.append(only_upper+",");
	    
	    date_array_value_for_lower.append(lower_case+",");
	    
	    date_array_set_in_dao.append("\n"+table_name_lower+".set"+first_upper+"(\""+only_upper+"\");");

	    date_array_set_in_dao_for_date.append("\n"+table_name_lower+".set"+first_upper+"(rs.getDate(\""+only_upper+"\"));");

   	
   	    }	    
    }
     
     prefield_controller.append("\n\n//-----------------------for prefield part-----------------------------------"
     		                     + "\n\n@RequestMapping(value = \"/"+table_name_lower+"_update\", method = RequestMethod.GET)"
	                             +"\npublic ModelAndView loadReport1(@RequestParam(value = \""+field_name_for_id_lower+"\") String\t" +field_name_for_id_lower+", ModelAndView modelview,"
			                     +"\nHttpServletRequest request, ModelMap map) throws IOException \n{"
		                         +"\nint u_id = Integer.parseInt("+field_name_for_id_lower+");"
		                         +"\n"+table_name_first_upper+"\t"+ table_name_lower+" = new\t" +table_name_first_upper+"();");
                                 if(line_table_name!=null && !line_table_name.isEmpty())
                                 {  
                                	 String line_table_name_lower=line_table_name.toLowerCase();
                                     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
                                     String line_table_name_upper=line_table_name.toUpperCase();
                                     
		                             prefield_controller.append("\n"+line_table_name_first_upper+"\t"+ line_table_name_lower+" = new\t" +line_table_name_first_upper+"();");
                                 }
                                 
		                         prefield_controller.append("\nmap.addAttribute(\""+table_name_lower+"_updt\"," +table_name_lower+");"
		                         +"\nList<"+table_name_first_upper+"> report =" +service_name_lower+".prefield(u_id);"
		                         +"\nmap.addAttribute(\""+table_name_lower+"_update\", report);");
		                         
		                         if(line_table_name!=null && !line_table_name.isEmpty())
                                 {  
		                        	 String line_table_name_lower=line_table_name.toLowerCase();
                                     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
                                     String line_table_name_upper=line_table_name.toUpperCase();       
		                             prefield_controller.append("List<"+line_table_name_first_upper+"> grp_menu_list_line = "+service_name_lower+".update_group_menu_line(u_id);"
		                              +"\nmap.addAttribute(\"linelist\", grp_menu_list_line);"
		                              +"\nmap.addAttribute(\""+line_table_name_lower+"_updt\"," +line_table_name_lower+");");
                                   }
		                         
		                         prefield_controller.append("\nreturn new ModelAndView(\""+table_name_first_upper+"_update\");"
	                            +"\n}");
     
    prefield_controller_readonly.append("\n\n//--------------------for readonly------------------------------------------------"
    		+ "\n\n@RequestMapping(value = \"/"+table_name_lower+"_readonly\", method = RequestMethod.GET)"
             +"\npublic ModelAndView loadReport2(@RequestParam(value = \""+field_name_for_id_lower+"\") String\t" +field_name_for_id_lower+", ModelAndView modelview,"
             +"\nHttpServletRequest request, ModelMap map) throws IOException \n{"
             +"\nint u_id = Integer.parseInt("+field_name_for_id_lower+");"
             +"\n"+table_name_first_upper+"\t"+ table_name_lower+" = new\t" +table_name_first_upper+"();");
             
             
             if(line_table_name!=null && !line_table_name.isEmpty())
             {  
		          String line_table_name_lower=line_table_name.toLowerCase();
                  String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
                  String line_table_name_upper=line_table_name.toUpperCase();
                  
              prefield_controller_readonly.append("\n"+line_table_name_first_upper+"\t"+ line_table_name_lower+" = new\t" +line_table_name_first_upper+"();");
             }
             prefield_controller_readonly.append("\nmap.addAttribute(\""+table_name_lower+"_updt\"," +table_name_lower+");"
             +"\nList<"+table_name_first_upper+"> report =" +service_name_lower+".prefield(u_id);"
             +"\nmap.addAttribute(\""+table_name_lower+"_update\", report);");
             
             if(line_table_name!=null && !line_table_name.isEmpty())
             {  
		          String line_table_name_lower=line_table_name.toLowerCase();
                  String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
                  String line_table_name_upper=line_table_name.toUpperCase();
                  prefield_controller_readonly.append( "List<"+line_table_name_first_upper+"> grp_menu_list_line = "+service_name_lower+".update_group_menu_line(u_id);"
		          +"\nmap.addAttribute(\"linelist\", grp_menu_list_line);"
		          +"\nmap.addAttribute(\""+line_table_name_lower+"_updt\"," +line_table_name_lower+");");
             }
             prefield_controller_readonly.append("\nreturn new ModelAndView(\""+table_name_first_upper+"_readonly\");"
             
             
             
            +"\n}");
     grid_controller.append("\n\n//-----------------------------------for grid view only--------------------------------------------------"
     		+ "\n\n@RequestMapping(value=\"/"+table_name_lower+"_grid_view\")"
                              +"\npublic ModelAndView\t" +table_name_lower+"Details(ModelAndView model) throws IOException"
                              +"\n{"
	                          +"\nList<"+table_name_first_upper+">\t" +table_name_lower+"="+dao_name_lower+".userlist();"
	                          +"\nmodel.addObject(\""+table_name_lower+"\", "+table_name_lower+");"
	                          +"\nSystem.out.println(\"sujit\");"
	                          +"\nmodel.setViewName(\""+table_name_first_upper+"_grid\");"
	                          +"\nreturn model;"
                              +"\n}");
     
     
     for(int i=0;i<id_list.size();i++)
	 {
	  String field_name=id_list.get(i).getField_name();
	  String lower_case=field_name.toLowerCase();
	  String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
	  String only_upper=field_name.toUpperCase();
	  
	  service_save_id.append("String[]\t" +lower_case+",");
	  service_save_id1.append(lower_case+",");
	  
	  header_id_for_line.append(lower_case);
	  
	  header_id_for_line_for_comma.append(lower_case+",");
	  
	  
	  parameter_for_line_id.append("int\t"+lower_case+",");
	  
	  if(line_table_name!=null && !line_table_name.isEmpty())
      {  
	          String line_table_name_lower=line_table_name.toLowerCase();
           String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
           String line_table_name_upper=line_table_name.toUpperCase();
	      set_parameter_header_id_line.append("\n"+line_table_name_lower+".set"+first_upper+"("+lower_case+");");
      }
	 }
	 for(int i=0;i<rn_userlist.size();i++)
	 {
	  String field_name=rn_userlist.get(i).getField_name();
	  String lower_case=field_name.toLowerCase();
	  String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
	  String only_upper=field_name.toUpperCase();
	  
	  service_save_field.append("String[]\t" +lower_case);
	  if(i<(rn_userlist.size()-1))
	  {
		  service_save_field.append(","); 
	  }
	  service_save_field.append("\t"); 
	  
	  service_save_field1.append(lower_case);
	  
	  if(i<(rn_userlist.size()-1))
	  {
		  service_save_field1.append(","); 
	  }
	  service_save_field1.append("\t");
	  
	 }
	 
	 
     save_controller.append("\n\n//--------------------------sbmit update part---------------------------------------------------"
     		+ "\n\n@RequestMapping(value = \"/"+table_name_lower+"_update_submit\", method = RequestMethod.POST)"
                              +"\npublic ModelAndView saveReportRegister(@ModelAttribute\t" +table_name_first_upper +"\t"+table_name_lower+","
		                      +"\nBindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {"
	                          +"\nint report = 0;"
	
	                          + sbmit_parameterid+id_notpri_para+date_para+sbmit_parameterfield
	                          
	                          +"\nint rowcount="+field_name_for_id_lower+".length;"
                               +"\nreport = "+service_name_lower+".save(rowcount,"+service_save_id1+var_for_pass_para_id+date_array_value_for_lower+service_save_field1+");"
	                           +"\nString check = request.getParameter(\"repupdt\");"
	                           +"\nmap.addAttribute(\"check\", check);"
	                          + "\nmap.addAttribute(\"report\", report);"
	
	                           +"\n\t"+table_name_first_upper+"\t rep_reg = new \t"+table_name_first_upper+"();"
	                          + "\nmap.addAttribute(\"rep_reg\", rep_reg);"
	
	                             +"\nList<"+table_name_first_upper+"> report_list = "+service_name_lower+".userlist();"
	                             + "\nmap.addAttribute(\"report_list\", report_list);"
	
	                              +"\nreturn new ModelAndView(\"redirect:"+table_name_lower+"_grid_view\");"
                                + "\n}"
                         );
     
     
     //----------------------header-line submit controller---------------------------------------------------
     
    
     
     if(line_table_name!=null && !line_table_name.isEmpty())
     {  
          String line_table_name_lower=line_table_name.toLowerCase();
          String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
          String line_table_name_upper=line_table_name.toUpperCase();
          
     
	 for(int i=0;i<line_id_primary.size();i++)
	 {
	  String field_name=line_id_primary.get(i).getField_name();
	  String lower_case=field_name.toLowerCase();
	  String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
	  String only_upper=field_name.toUpperCase();
	  
       header_line_submit_id.append("\nString \t"+lower_case+"[]=request.getParameterValues(\""+lower_case+"\");");
       
       line_id_for_rowcount.append(lower_case);
       
       //parameter_for_line_id.append("int\t"+header_id_for_line+",");
       parameter_for_line_id.append("String\t"+lower_case+"[],");
       
        
       array_para_for_id_line.append(lower_case+",");
       
       
       get_parameter_for_id_line.append("\nif ("+lower_case+"[i] != null && \t"+lower_case+"[i].length() > 0)"
			    +"\n{"
				+ " \ninfonum = Integer.parseInt("+lower_case+"[i]);"
			    +"\n}"
			    +"\nelse"
			    +"\n{"
				  +"\ninfonum =" +line_table_name_lower+".get"+first_upper+"();"
			   +" \n}");
       
       
      set_parameter_for_id_line.append("\n"+line_table_name_lower+".set"+first_upper+"(infonum);");
      
      model_class_line1.append("\n@Column(name = \""+only_upper+"\")"
              +"\nprivate int \t"+lower_case+";");

      model_class_line2.append("\n\npublic int get"+first_upper+"() \n{"
              +"\nreturn\t" +lower_case+";\n}"

              +"\n\npublic void set"+first_upper+"(int\t" +lower_case+")\n{"
              +"\nthis."+lower_case+ "=" +lower_case+";"
              +"\n}");
      
      get_set_for_line_update_id.append("\n"+line_table_name_lower+".set"+first_upper+"(rs.getInt(\""+only_upper+"\"));");
       
       
	 }
}
     
     
     if(line_table_name!=null && !line_table_name.isEmpty())
     {  
          String line_table_name_lower=line_table_name.toLowerCase();
          String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
          String line_table_name_upper=line_table_name.toUpperCase();  
     
     for(int i=0;i<line_varchar.size();i++)
	 {
	  String field_name=line_varchar.get(i).getField_name();
	  String lower_case=field_name.toLowerCase();
	  String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
	  String only_upper=field_name.toUpperCase();
	  
	  
	 
	  
	  String data_type=line_varchar.get(i).getData_type();
     
	  if(data_type.equals("datetime"))
	  {
	  header_line_submit_varchar.append("\nDate	\t"+lower_case+" = null;"
										  +"\ntry"
										  +"\n{"
										 	+"\n"+lower_case+" = new SimpleDateFormat(\"dd-MM-yyyy\").parse(request.getParameter(\""+lower_case+"\"));"
										+ "\n} catch (ParseException e) "
										 +"\n{"
										+ "\ne.printStackTrace();"
										+"\n}");
	  
	  model_class_line3.append("\n@Column(name = \""+only_upper+"\")"
              +"\nprivate Date \t"+lower_case+";");

      model_class_line4.append("\n\npublic Date get"+first_upper+"() \n{"
              +"\nreturn\t" +lower_case+";\n}"

              +"\n\npublic void set"+first_upper+"(Date\t" +lower_case+")\n{"
              +"\nthis."+lower_case+ "=" +lower_case+";"
              +"\n}");
	  
      get_set_for_line_update_varchar.append("\n"+line_table_name_lower+".set"+first_upper+"(rs.getDate(\""+only_upper+"\"));");
      set_parameter_for_varchar_line.append("\n"+line_table_name_lower+".set"+first_upper+"("+lower_case+");");

										  
	  }
	  
	  
	  if(!data_type.equals("datetime"))
	  {
	  
          header_line_submit_varchar.append("\nString \t"+lower_case+"[]=request.getParameterValues(\""+lower_case+"\");");
          
          model_class_line3.append("\n@Column(name = \""+only_upper+"\")"
                  +"\nprivate String \t"+lower_case+";");

          model_class_line4.append("\n\npublic String get"+first_upper+"() \n{"
                  +"\nreturn\t" +lower_case+";\n}"

                  +"\n\npublic void set"+first_upper+"(String\t" +lower_case+")\n{"
                  +"\nthis."+lower_case+ "=" +lower_case+";"
                  +"\n}");
          
         get_set_for_line_update_varchar.append("\n"+line_table_name_lower+".set"+first_upper+"(rs.getString(\""+only_upper+"\"));");
 
         set_parameter_for_varchar_line.append("\n"+line_table_name_lower+".set"+first_upper+"("+lower_case+"[i]);");

          
	  }
	  
	  
	  
	  if(data_type.equals("datetime"))
	  {
         parameter_for_line_varchar.append("Date\t"+lower_case+"");
	  }
	  if(!data_type.equals("datetime"))
	  {
	     parameter_for_line_varchar.append("String\t"+lower_case+"[]");
       }
      
       
      
      if(i<(line_varchar.size()-1))
 	  {
    	  parameter_for_line_varchar.append(",");
 	  }
      parameter_for_line_varchar.append("\t");
      
      
      
      array_para_for_varchar_line.append(lower_case);
      
      if(i<(line_varchar.size()-1))
 	  {
    	  array_para_for_varchar_line.append(",");
 	  }
       array_para_for_varchar_line.append("\t");
       
       
       
     
       
    
    
    
    
       
       
       
	 }
     
}
     if(line_table_name!=null && !line_table_name.isEmpty())
     {  
          String line_table_name_lower=line_table_name.toLowerCase();
          String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
          String line_table_name_upper=line_table_name.toUpperCase();
          
          action.append(line_table_name_lower+"_submit_HeaderLine");
          action2.append(line_table_name_lower+"_submit_HeaderLine");
     } 
     else{
    	   action.append(table_name_lower+"_submit");
    	   action2.append(table_name_lower+"_update_submit");
    	 }
     
     
     if(line_table_name!=null && !line_table_name.isEmpty())
     {  
          String line_table_name_lower=line_table_name.toLowerCase();
          String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
          String line_table_name_upper=line_table_name.toUpperCase();
          header_line_submit.append("\n\n//-----------------------------header line submit------------------------------------------------"
     		+ "\n\n@RequestMapping(\"/"+action+"\")"
	                              +"\npublic ModelAndView saveGroupReport(@ModelAttribute\t"+table_name_first_upper+"\t"+table_name_lower+", BindingResult resultMenuGroupHeader,"
			                     +"\n"+line_table_name_first_upper+"\t" +line_table_name_lower+", BindingResult resultReportMenuLines,"
				                  +"\nModelMap map, HttpServletRequest request) throws ParseException"
	                              +"\n{"
	                              +"int user_id=(Integer)request.getSession().getAttribute(\"userid\");"
	                              +"\n"+id_primary_parameter_submit+id_para_submit+para+date_para+get_parameter+"\n"+id_notpri_set+set_para+date_set+set_parameter
	                              +"\nint\t"+header_id_for_line+"="+service_name_lower+".saveheader("+table_name_lower+");"
	                              
	                                 
	                               +header_line_submit_id+header_line_submit_varchar
	                               +"\nint rowcount="+line_id_for_rowcount+".length;"
	                               
	                               +"\nString check = request.getParameter(\"menuupdt\");"
			                      + "\nmap.addAttribute(\"check\", check);"
			                        +"\nint group_line = "+service_name_lower+".addmenuGroupLine(rowcount,"+header_id_for_line_for_comma+array_para_for_id_line+array_para_for_varchar_line+");"
	                   			  +"\nmap.addAttribute(\"group_line\", group_line);"
			                      
	                   			  +"\nList<"+table_name_first_upper+"> header_list ="+dao_name_lower+".userlist();"
	                 			  +"\nmap.addAttribute(\"header_list\",header_list);"
	                   			   
    	                          +"\nreturn new ModelAndView(\"redirect:"+table_name_lower+"_grid_view\");"
    	  
	                              +" \n}");
     }
     
     
     
										    for(int i=0;i<rn_userlist.size();i++)
										    { 
									
									             String type_field=rn_userlist.get(i).getType_field();
									             String mapping=rn_userlist.get(i).getMapping();
									             String mapping_lower=mapping.toLowerCase();
									             String sp_name=rn_userlist.get(i).getSp_name_for_dropdown();
									            
									               if(type_field.equals("dropdown"))
									               {
									            	   dropdown_controller.append("\n@RequestMapping(value = \"/"+mapping_lower+"_list\", method = RequestMethod.GET)"
																					+"\npublic void \t"+mapping_lower+"_list( HttpServletRequest request, HttpServletResponse response) {"
																					+"\nList<LookupValues> droplist = new ArrayList<LookupValues>();"
																					+"\ntry {"
																						+"\nCallableStatement cStmt;"
																							 		+"\ntry {"
																							 		+"\ncStmt = hibernateConfiguration.dataSource().getConnection()"
																							 			+"\n.prepareCall(\"{call \t"+sp_name+"()}\");"
																							 		+"\nResultSet rs = cStmt.executeQuery();"
																							 		+"\nwhile (rs.next()) "
																							 		+"\n{"
																							 			+"\nString data = rs.getString(1);"
																							 			+"\nLookupValues menu = new LookupValues();"
																							 			+"\nmenu.setDrop_value(data);"
																							 			+"\ndroplist.add(menu);"
																							 		+"\n}"
																							 		+"\n} catch (SQLException e) {"
																							 		+"\ne.printStackTrace();"
																							 		+"\n}"
																							 		+"\ncatch (Exception e) {"
																							 		+"\nSystem.out.println(e.getMessage());"
																							 		+"\n}"
																					                 +"\nString json = null;"
																							+ "\njson = new Gson().toJson(droplist);"
																							+ "\nresponse.setContentType(\"application/json\");"
																							 +"\nresponse.getWriter().write(json);"
																							+ "\n} catch (IOException e) {"
																							 	+"\ne.printStackTrace();"
																							+" \n}}");
									               }
									               
									               
									               if(type_field.equals("autocomplete"))
									               {
									            	   dropdown_controller.append("\n@RequestMapping(value = \"/"+mapping_lower+"_list\", method = RequestMethod.GET)"
																					+"\npublic void \t"+mapping_lower+"_list( HttpServletRequest request, HttpServletResponse response) {"
																					+"\nList<LookupValues> droplist = new ArrayList<LookupValues>();"
																					+"\ntry {"
																						+"\nCallableStatement cStmt;"
																							 		+"\ntry {"
																							 		+"\ncStmt = hibernateConfiguration.dataSource().getConnection()"
																							 			+"\n.prepareCall(\"{call \t"+sp_name+"()}\");"
																							 		+"\nResultSet rs = cStmt.executeQuery();"
																							 		+"\nwhile (rs.next()) "
																							 		+"\n{"
																							 			+"\nString data = rs.getString(1);"
																							 			+"\nLookupValues menu = new LookupValues();"
																							 			+"\nmenu.setDrop_value(data);"
																							 			+"\ndroplist.add(menu);"
																							 		+"\n}"
																							 		+"\n} catch (SQLException e) {"
																							 		+"\ne.printStackTrace();"
																							 		+"\n}"
																							 		+"\ncatch (Exception e) {"
																							 		+"\nSystem.out.println(e.getMessage());"
																							 		+"\n}"
																					                 +"\nString json = null;"
																							+ "\njson = new Gson().toJson(droplist);"
																							+ "\nresponse.setContentType(\"application/json\");"
																							 +"\nresponse.getWriter().write(json);"
																							+ "\n} catch (IOException e) {"
																							 	+"\ne.printStackTrace();"
																							+" \n}}");
									               }
									               
									               
									               
									               
										    }
										     
										    for(int i=0;i<line_varchar.size();i++)
										    { 
									
									             String type_field=line_varchar.get(i).getType_field();
									             String mapping=line_varchar.get(i).getMapping();
									             String mapping_lower=mapping.toLowerCase();
									             String sp_name=line_varchar.get(i).getSp_name_for_dropdown();
									            
									               if(type_field.equals("dropdown"))
									               {
									            	   dropdown_controller.append("\n@RequestMapping(value = \"/"+mapping_lower+"_list_line\", method = RequestMethod.GET)"
																					+"\npublic void \t"+mapping_lower+"_list( HttpServletRequest request, HttpServletResponse response) {"
																					+"\nList<LookupValues> droplist = new ArrayList<LookupValues>();"
																					+"\ntry {"
																						+"\nCallableStatement cStmt;"
																							 		+"\ntry {"
																							 		+"\ncStmt = hibernateConfiguration.dataSource().getConnection()"
																							 			+"\n.prepareCall(\"{call \t"+sp_name+"()}\");"
																							 		+"\nResultSet rs = cStmt.executeQuery();"
																							 		+"\nwhile (rs.next()) "
																							 		+"\n{"
																							 			+"\nString data = rs.getString(1);"
																							 			+"\nLookupValues menu = new LookupValues();"
																							 			+"\nmenu.setDrop_value(data);"
																							 			+"\ndroplist.add(menu);"
																							 		+"\n}"
																							 		+"\n} catch (SQLException e) {"
																							 		+"\ne.printStackTrace();"
																							 		+"\n}"
																							 		+"\ncatch (Exception e) {"
																							 		+"\nSystem.out.println(e.getMessage());"
																							 		+"\n}"
																					                 +"\nString json = null;"
																							+ "\njson = new Gson().toJson(droplist);"
																							+ "\nresponse.setContentType(\"application/json\");"
																							 +"\nresponse.getWriter().write(json);"
																							+ "\n} catch (IOException e) {"
																							 	+"\ne.printStackTrace();"
																							+" \n}}");
									               }
									               
									               if(type_field.equals("autocomplete"))
									               {
									            	   dropdown_controller.append("\n@RequestMapping(value = \"/"+mapping_lower+"_list_line\", method = RequestMethod.GET)"
																					+"\npublic void \t"+mapping_lower+"_list( HttpServletRequest request, HttpServletResponse response) {"
																					+"\nList<LookupValues> droplist = new ArrayList<LookupValues>();"
																					+"\ntry {"
																						+"\nCallableStatement cStmt;"
																							 		+"\ntry {"
																							 		+"\ncStmt = hibernateConfiguration.dataSource().getConnection()"
																							 			+"\n.prepareCall(\"{call \t"+sp_name+"()}\");"
																							 		+"\nResultSet rs = cStmt.executeQuery();"
																							 		+"\nwhile (rs.next()) "
																							 		+"\n{"
																							 			+"\nString data = rs.getString(1);"
																							 			+"\nLookupValues menu = new LookupValues();"
																							 			+"\nmenu.setDrop_value(data);"
																							 			+"\ndroplist.add(menu);"
																							 		+"\n}"
																							 		+"\n} catch (SQLException e) {"
																							 		+"\ne.printStackTrace();"
																							 		+"\n}"
																							 		+"\ncatch (Exception e) {"
																							 		+"\nSystem.out.println(e.getMessage());"
																							 		+"\n}"
																					                 +"\nString json = null;"
																							+ "\njson = new Gson().toJson(droplist);"
																							+ "\nresponse.setContentType(\"application/json\");"
																							 +"\nresponse.getWriter().write(json);"
																							+ "\n} catch (IOException e) {"
																							 	+"\ne.printStackTrace();"
																							+" \n}}");
									               }
									               
									               
									               
									               
									               
									               
										    } 
										    
										    
										    
										    
										     
     
     
     
     
     
     
     controller.append("\npackage com.springmvc.controller;"
         		+ "\n import javax.servlet.http.HttpServletRequest;"
				+ "\nimport org.springframework.beans.factory.annotation.Autowired;"
				+ "\nimport org.springframework.stereotype.Controller;"
				+ "\nimport org.springframework.validation.BindingResult;"
				+ "\nimport org.springframework.web.bind.annotation.ModelAttribute;"
				+ "\nimport org.springframework.web.bind.annotation.RequestMapping;"
				+ "\nimport org.springframework.web.bind.annotation.RequestMethod;"
				+ "\nimport org.springframework.web.servlet.ModelAndView;"
				+ "\nimport java.io.IOException;"
				+ "\nimport java.util.List;"
				+ "\nimport org.springframework.orm.hibernate5.HibernateTemplate;"
				+ "\nimport org.springframework.web.bind.annotation.RequestParam;"
				+ "\nimport javax.transaction.Transactional;"
				+ "\nimport org.springframework.ui.ModelMap;"
				+ "\nimport javax.servlet.http.HttpServletResponse;"
				+ "\nimport java.util.ArrayList;"
				+ "\nimport java.sql.CallableStatement;"
				+ "\nimport java.sql.ResultSet;"
				+ "\nimport java.sql.SQLException;"
				+ "\nimport com.google.gson.Gson;"
				+ "\nimport java.util.Date;"
				+ "\nimport com.springmvc.model.LookupValues;"
				+ "\nimport com.springmvc.configuration.HibernateConfiguration;"
				+ "\nimport java.text.SimpleDateFormat;"
				+ "\nimport com.springmvc.dao."+dao_name_first_upper+";"
				+ "\nimport com.springmvc.service."+service_name_first_upper+";"
				 //"\nimport org.apache.poi.hssf.model.Model;"
				+ "\nimport com.springmvc.model."+table_name_first_upper+";"
				+ "import java.text.ParseException;");
				if(line_table_name!=null && !line_table_name.isEmpty())
	             {  
			          String line_table_name_lower=line_table_name.toLowerCase();
	                  String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
	                  String line_table_name_upper=line_table_name.toUpperCase();
				     controller.append( "import com.springmvc.model."+line_table_name_first_upper+";");
                  }
				controller.append("\n@Controller"
				+ "\npublic class\t" +controller_name_first_upper+"\n{"
				
	            + "\n@Autowired"
                + "\nprivate HibernateTemplate  hibernateTemplate;"
                + "\n@Autowired"
                 +"\nprivate\t"+service_name_first_upper+"\t"+service_name_lower+";"
                    +"\n@Autowired"
                    +"\nHibernateConfiguration hibernateConfiguration;"
                +"\n@Autowired"
                 +"\nprivate\t"+dao_name_first_upper+"\t"+dao_name_lower+";"
                 		+ "\n\n//----------------------entry form sbmit------------------------------------------"
                
                + "\n@Transactional"
                 + "\n@RequestMapping(value = \"/"+table_name_lower+"_submit\", method = RequestMethod.POST)"
                  + "\npublic ModelAndView saveServiceRequest(@ModelAttribute\t" +table_name_first_upper+  "\t"+table_name_lower+",BindingResult resultKoel_user ,"
											+"\nModelMap map, HttpServletRequest request)  "
                   +"\n{"
                   + "int user_id=(Integer)request.getSession().getAttribute(\"userid\");"
				  +id_para_submit+para+date_para+get_parameter+"\n"+id_notpri_set+set_para+date_set+set_parameter
	               +"\nhibernateTemplate.saveOrUpdate("+table_name_lower+");"
	               		+ "\nreturn new ModelAndView(\"redirect:"+table_name_lower+"_grid_view\");"
	               		+ "\n"
	               		+ "\n\n}"
	               		+ "\n\n"
	               	     +"\n@RequestMapping(\"/"+table_name_lower+"_entryform\")"
	               		 +"\npublic ModelAndView input_form3(HttpServletRequest request, ModelMap map) "
	               	     +"\n{"
	               			+"\nreturn new ModelAndView(\""+jsp_name+"\");"
	               		 +"\n}"
	               		  +grid_controller+prefield_controller+"\n\n"+prefield_controller_readonly+"\n\n"+save_controller+header_line_submit+dropdown_controller
	               		+ "\n}"
	            );
     
     
     
     
     for(int i=0;i<id_list.size();i++)
		{
    	 String field_name=id_list.get(i).getField_name();
    	 String lower_case=field_name.toLowerCase();
    	 String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
    	 String only_upper=field_name.toUpperCase();
    	 
    	 
    	 model_class3.append("\n@Column(name = \""+only_upper+"\")"
                 +"\nprivate int \t"+lower_case+";");

         model_class4.append("\n\npublic int get"+first_upper+"() \n{"
                 +"\nreturn\t" +lower_case+";\n}"

                 +"\n\npublic void set"+first_upper+"(int\t" +lower_case+")\n{"
                 +"\nthis."+lower_case+ "=" +lower_case+";"
                 +"\n}");
 	 
		}
     for(int i=0;i<rn_userlist.size();i++)
		{
    	 String field_name=rn_userlist.get(i).getField_name();
    	 String lower_case=field_name.toLowerCase();
    	 String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
    	 String only_upper=field_name.toUpperCase();
    	 
    	 model_class1.append("\n@Column(name = \""+only_upper+"\")"
	                          +"\nprivate String \t"+lower_case+";");
    	 
    	 model_class2.append("\n\npublic String get"+first_upper+"() \n{"
		                      +"\nreturn\t" +lower_case+";\n}"

	                          +"\n\npublic void set"+first_upper+"(String\t" +lower_case+")\n{"
		                      +"\nthis."+lower_case+ "=" +lower_case+";"
		                      +"\n}");
		}
     
     
     //--------------------------------ModeL class code------------------------------------------------//
     
    	 model_class.append("   package com.springmvc.model;"

                                 +"\nimport javax.persistence.Column;"
                                 +"\nimport javax.persistence.Entity;"
                                 +"\nimport javax.persistence.GeneratedValue;"
                                 +"\nimport javax.persistence.GenerationType;"
                                 +"\nimport javax.persistence.Id;"
                                 +"\nimport javax.persistence.SequenceGenerator;"
                                 +"\nimport javax.persistence.Table;"
                                 + "import org.hibernate.annotations.GenericGenerator;"
                                 + "import java.util.Date;"
                                 +"\n@Entity"
                                 +"\n@Table(name = \""+table_name_upper+"\")"
                                 +"\npublic class\t"+table_name_first_upper 
                                 +"\n{"
                                 + "\n@Id"
	                             + "\n@GeneratedValue(strategy = GenerationType.AUTO, generator =\"native\")"
	                             + "\n@GenericGenerator(name = \"native\", strategy = \"native\")"
                                 + model_class3+model_class_for_var+date_model_variable+"\n"+model_class4+"\n"+model_class1+"\n"+model_class2+date_model_set_variable+model_class_for_set+model_class_for_attribute+model_class_for_attribute2
                                 
                                 +"\n@Column(name = \"CREATED_BY\")"
                                 +"\nprivate int 	created_by;"

                                 +"\n@Column(name = \"LAST_UPDATED_BY\")"
                                 +"\nprivate int 	last_updated_by;"

                                 +"\n@Column(name = \"CREATION_DATE\")"
                                 +"\nprivate Date 	creation_date;"

                                 +"\n@Column(name = \"LAST_UPDATE_DATE\")"
                                 +"\nprivate Date 	last_update_date;"
                                 
                                    +"\npublic int getCreated_by() {"
                                	+"	\nreturn created_by;"
                                	+"\n}"

                                	+"\npublic void setCreated_by(int created_by) {"
                                	+"\n	this.created_by = created_by;"
                                	+"\n}"

                                	+"\npublic int getLast_updated_by() {"
                                	+"\n	return last_updated_by;"
                                	+"\n}"

                                	+"\npublic void setLast_updated_by(int last_updated_by) {"
                                	+"\n	this.last_updated_by = last_updated_by;"
                                	+"\n}"

                                	+"\npublic Date getCreation_date() {"
                                	+"\n	return creation_date;"
                                	+"\n}"

                                	+"\npublic void setCreation_date(Date creation_date) {"
                                	+"\n	this.creation_date = creation_date;"
                                	+"\n}"

                                	+"\npublic Date getLast_update_date() {"
                                    +"\n		return last_update_date;"
                                	+"\n}"

                                	+"\npublic void setLast_update_date(Date last_update_date) {"
                                	+"\n	this.last_update_date = last_update_date;"
                                	+"\n}"
                                	
                                	+"\n@Column(name = \"ACCOUNT_ID\")"
                                	+"\nprivate int 	account_id;"


                                	+"\npublic int getAccount_id() {"
                                	+"\n	return account_id;"
                                	+"\n}"

                                	+"\npublic void setAccount_id(int account_id) {"
                                	+"\n	this.account_id = account_id;"
                                	+"\n}"
                                 + "\n}");
    	 if(line_table_name!=null && !line_table_name.isEmpty())
         {  
	          String line_table_name_lower=line_table_name.toLowerCase();
              String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
              String line_table_name_upper=line_table_name.toUpperCase();
    	      model_class_for_line.append("   package com.springmvc.model;"

                                 +"\nimport javax.persistence.Column;"
                                 +"\nimport javax.persistence.Entity;"
                                 +"\nimport javax.persistence.GeneratedValue;"
                                 +"\nimport javax.persistence.GenerationType;"
                                 +"\nimport javax.persistence.Id;"
                                 +"\nimport javax.persistence.SequenceGenerator;"
                                 +"\nimport javax.persistence.Table;"
                                 + "import org.hibernate.annotations.GenericGenerator;"
                                 + "import java.util.Date;"
                                 +"\n@Entity"
                                 +"\n@Table(name = \""+line_table_name_upper+"\")"
                                 +"\npublic class\t"+line_table_name_first_upper 
                                 +"\n{"
                                 + "\n@Id"
	                             + "\n@GeneratedValue(strategy = GenerationType.AUTO, generator =\"native\")"
	                             + "\n@GenericGenerator(name = \"native\", strategy = \"native\")"
	                             +  model_class_line1+model_class_line3+model_class3+model_class_line2+model_class_line4+model_class4+model_class_for_attribute+model_class_for_attribute2
                                 + "\n}");
         }
     //------------------------------service part-------------------------------------//
     
    	 
    	
    	 service.append("\npackage com.springmvc.service;"
                         +"\nimport java.util.Date;"
                         +"\nimport java.util.ArrayList;"
                         +"\nimport java.util.List;"
                         +"\nimport org.springframework.stereotype.Service;"
                         +"\nimport com.springmvc.model."+table_name_first_upper+";");
                         if(line_table_name!=null && !line_table_name.isEmpty())
                         {  
		                   String line_table_name_lower=line_table_name.toLowerCase();
                           String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
                           String line_table_name_upper=line_table_name.toUpperCase();
                           service.append("\nimport com.springmvc.model."+line_table_name_first_upper+";");
                          }
                         service.append("\npublic interface\t"+service_name_first_upper+"\n{"
                        	 
                         +"\npublic List<"+table_name_first_upper+"> prefield(int u_id);"
                         +"\npublic List<"+table_name_first_upper+"> userlist();"
                         +"\npublic int save(int rowcount,"+service_save_id+var_for_pass_para+date_array_para+service_save_field+") ;" 
                         +"\npublic int saveheader("+table_name_first_upper+"\t"+table_name_lower+");");
    	 if(line_table_name!=null && !line_table_name.isEmpty())
         {  
               service.append("\npublic int addmenuGroupLine(int rowcount,"+parameter_for_line_id+parameter_for_line_varchar+");");

    		 
	          String line_table_name_lower=line_table_name.toLowerCase();
              String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
              String line_table_name_upper=line_table_name.toUpperCase();
                         service.append("\npublic List<"+line_table_name_first_upper+"> update_group_menu_line(int \t"+header_id_for_line+");");
                        
         } 
    	 
    	 service.append("\n}");
    	 
    	 service_impl.append("\npackage com.springmvc.service;"
                              +"\nimport java.util.Date;"
                              +"\nimport java.util.ArrayList;"
                              +"\nimport java.util.List;"
                              +"\nimport org.springframework.beans.factory.annotation.Autowired;"
                              +"\nimport org.springframework.stereotype.Component;"
                              +"\nimport org.springframework.stereotype.Service;"
                              +"\nimport com.springmvc.dao."+dao_name_first_upper+";"
                              +"\nimport com.springmvc.model."+table_name_first_upper+";");
    	                     if(line_table_name!=null && !line_table_name.isEmpty())
                             {  
	                           String line_table_name_lower=line_table_name.toLowerCase();
                               String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
                               String line_table_name_upper=line_table_name.toUpperCase();
                              service_impl.append("\nimport com.springmvc.model."+line_table_name_first_upper+";");
                              }
                              service_impl.append("\n@Service"
                              +"\npublic class\t"+ service_impl_name_first_upper+"\timplements\t" +service_name_first_upper+"\n {"
                              +"\n\n@Autowired"
	                          +"\nprivate \t"+dao_name_first_upper+"\t"+dao_name_lower+";"
	                          		+ "\n\n@Override"
                                      +" \npublic List<"+table_name_first_upper+"> prefield(int u_id)" 
                                        +"\n{"
                                         +"\nreturn\t" +dao_name_lower+".prefield(u_id);"
                                        +"\n }"
                                        + "\n@Override"
                                           +"\npublic List<"+table_name_first_upper+"> userlist() "
                                           +"\n{"
                                             +"\nreturn\t" +dao_name_lower+".userlist();"
                                           + "\n}"
                                           + "\n@Override"
                                              +"\npublic int save(int rowcount,"+service_save_id+var_for_pass_para+date_array_para+service_save_field+") "
                                              +"\n{"
	                                            +"\nreturn\t"+ dao_name_lower+".save(rowcount,"+service_save_id1+var_for_pass_para_id+date_array_value_for_lower+service_save_field1+");"
                                               +"\n}"
	                          		
                                               +"public int saveheader("+table_name_first_upper+"\t"+table_name_lower+")"
                                           	  + "{"
                                           		+"return \t"+dao_name_lower+".saveheader("+table_name_lower+");"
                                           	    +"}");
                                                        
                                           	  
                                           		
                                           		if(line_table_name!=null && !line_table_name.isEmpty())
                                                {  
                                           			service_impl.append("\npublic int addmenuGroupLine(int rowcount,"+parameter_for_line_id+parameter_for_line_varchar+")"
                                                       		+"\n{"
                                                       			+"return \t"+dao_name_lower+".addmenuGroupLine(rowcount,"+header_id_for_line_for_comma+array_para_for_id_line+array_para_for_varchar_line+");"
                                                       		+"\n}");
                                   		          String line_table_name_lower=line_table_name.toLowerCase();
                                                     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
                                                     String line_table_name_upper=line_table_name.toUpperCase();
                                           		service_impl.append("\npublic List<"+line_table_name_first_upper+"> update_group_menu_line(int \t"+header_id_for_line+")"
                                           		  +"\n{"
                                           		
                                         			+"\nreturn \t"+dao_name_lower+".update_group_menu_line("+header_id_for_line+");"

                            	                    +"\n}");
    	                                         }
                              service_impl.append("\n}"
                              		
    	 		);
    	 
    //------------------------------------dao code ------------------------------------------------//
    	 
    	 for(int i=0;i<id_list.size();i++)
    	 {
    	  String field_name=id_list.get(i).getField_name();
    	  String lower_case=field_name.toLowerCase();
    	  String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
    	  String only_upper=field_name.toUpperCase();
    	  
    	  dao_save_id.append("String[]\t" +lower_case+",");
    	  //service_save_id1.append(lower_case+",");
    	  
    	  
    	 }
    	 for(int i=0;i<rn_userlist.size();i++)
    	 {
    	  String field_name=rn_userlist.get(i).getField_name();
    	  String lower_case=field_name.toLowerCase();
    	  String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
    	  String only_upper=field_name.toUpperCase();
    	  
    	  dao_save_field.append("String[]\t" +lower_case);
    	  if(i<(rn_userlist.size()-1))
    	  {
    		  dao_save_field.append(",") ;
    		  
    	  }
    	  dao_save_field.append("\t") ;
    	  //service_save_field1.append(lower_case+",");
    	  
    	 }
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 dao.append("\npackage com.springmvc.dao;"
                     +"\nimport java.util.Date;"
                     +"\nimport java.util.List;"
                     +"\nimport com.springmvc.model."+table_name_first_upper+";");
                     if(line_table_name!=null && !line_table_name.isEmpty())
                     {  String line_table_name_lower=line_table_name.toLowerCase();
        		        String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
        		        String line_table_name_upper=line_table_name.toUpperCase();
                    	 dao.append("\nimport com.springmvc.model."+line_table_name_first_upper+";");
                     }
                     dao.append("\npublic interface\t" +dao_name_first_upper+" {	"	
                     + "\npublic List<"+table_name_first_upper+"> userlist();"
                     + "\npublic List<"+table_name_first_upper+"> prefield(int u_id);"
                     +"\npublic int save(int rowcount,"+dao_save_id+var_for_pass_para+date_array_para+dao_save_field+");" 

                     +"\npublic int saveheader("+table_name_first_upper+"\t"+table_name_lower+");");
                     if(line_table_name!=null && !line_table_name.isEmpty())
                     {  String line_table_name_lower=line_table_name.toLowerCase();
        		        String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
        		        String line_table_name_upper=line_table_name.toUpperCase();
                        dao.append("public int addmenuGroupLine(int rowcount,"+parameter_for_line_id+parameter_for_line_varchar+");");	

                        dao.append("\npublic List<"+line_table_name_first_upper+"> update_group_menu_line(int \t"+header_id_for_line+");");
                     }
                     dao.append("\n}"
    			 );
    	
    	 
    	 
    	 
    	 for(int i=0;i<id_list.size();i++)
  		{
      	     String field_name=id_list.get(i).getField_name();
    	     String lower_case=field_name.toLowerCase();
    	     String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
    	     String upper_case=field_name.toUpperCase();
    	  
      	 sqlField_id.append(upper_case+",");
      	 
  		 setField_id.append("\n"+table_name_lower+".set"+first_upper+"(rs.getInt(\""+upper_case+"\"));");
  		 
  		 field_for_save_id.append("\n"+table_name_lower+".set"+first_upper+"(infonum);");
  		 
  		get_id_for_sbmit_header_line.append(first_upper);
  		 
  		 
  		}
    	 for(int i=0;i<rn_userlist.size();i++)
 		{
     	 String field_name=rn_userlist.get(i).getField_name();
   	     String lower_case=field_name.toLowerCase();
   	     String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
   	     String upper_case=field_name.toUpperCase();
   	  
     	  sqlField.append(upper_case);
     	  
     	  if(i<(rn_userlist.size()-1))
     	  {
     		  sqlField.append(",");
     	  }
     	 sqlField.append("\t");
     	  
     	  
     	     
     	  setField.append("\n"+table_name_lower+".set"+first_upper+"(rs.getString(\""+upper_case+"\"));");
     	  
     	  field_for_save_field.append("\n"+table_name_lower+".set"+first_upper+"("+lower_case+"[i]);");
 		}
    	 
    	 
    	 
    	  dao_impl_prefield.append("\n\n@Override"
                 + "\npublic List<"+table_name_first_upper+"> prefield(int u_id)" 
                   +"\n{"
                 
	               + "\nString sql =\"SELECT "+sqlField_id+var_for_pass_para_id_for_upper+date_array_value+sqlField+","+attribute_for_select_statment+",ACCOUNT_ID,CREATED_BY,CREATION_DATE,LAST_UPDATED_BY,LAST_UPDATE_DATE FROM\t"+table_name_upper+"\tWHERE\t"+ field_name_for_id_upper +"= \"+u_id+\"\";"
	                +"\nList<"+table_name_first_upper+"> userlist = jdbcTemplate.query(sql, new RowMapper<"+table_name_first_upper+">() {"
				   + "\n@Override"
				    +"\npublic\t" +table_name_first_upper+ "\tmapRow(ResultSet rs, int rowNum) throws SQLException {"
					+"\n"+table_name_first_upper+"\t"+table_name_lower+" = new \t"+table_name_first_upper+"();"
					+setField_id+id_array_set_in_dao+date_array_set_in_dao_for_date+setField+attribute_set_for_grid_dao
					+"\n"+table_name_lower+".setAccount_id(rs.getInt(\"ACCOUNT_ID\"));"
					+"\n"+table_name_lower+".setCreated_by(rs.getInt(\"CREATED_BY\"));"
					+"\n"+table_name_lower+".setCreation_date(rs.getDate(\"CREATION_DATE\"));"
					+"\n"+table_name_lower+".setLast_updated_by(rs.getInt(\"LAST_UPDATED_BY\"));"
					+"\n"+table_name_lower+".setLast_update_date(rs.getDate(\"LAST_UPDATE_DATE\"));"
					+"\nreturn\t" +table_name_lower+";"
				  +"\n}"
				
			   + "\n});"
			
		         +"\nreturn userlist;"
             +"\n}");
    	  
    	  
    	  
    	  dao_impl_save.append("\n@Override"
                                +"\n@Transactional"
                                +"\npublic int save(int rowcount,"+dao_save_id+var_for_pass_para+date_array_para+dao_save_field+")" 
                                +"\n{"
                                 +"\nint infonum = 0;"
	                             +"\nfor (int i = 0; i < rowcount; i++) "
	                             +"\n{"
		                          +"\n"+table_name_first_upper+"\t"+ table_name_lower +"= new\t" +table_name_first_upper+"();"
			                      +"\nif ("+field_name_for_id_lower+" != null && "+field_name_for_id_lower+".length > 0) "
			                      +"\n{"
				                   +"\ninfonum = Integer.parseInt("+field_name_for_id_lower+"[i]);"
			                      +"\n} else "
			                       +"\n{"
				                    +"\ninfonum = "+table_name_lower+".get"+field_name_first_upper+"();"
			                       +"\n}"

			                      + field_for_save_id+id_array_set_in_dao2+date_set+field_for_save_field
			
			

			                              +"\nhibernateTemplate.saveOrUpdate("+table_name_lower+");"
		                              +"\n}"
	
	                                  +"\nreturn 1;"
                                   + "\n}"
    	  		);
    	  if(line_table_name !=null && !line_table_name.isEmpty())
    	  {
    		  String line_table_name_lower=line_table_name.toLowerCase();
			     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
			     String line_table_name_upper=line_table_name.toUpperCase();
			     
    	  dao_impl_save_line.append("\n@Override"
	                                + "\n@Transactional"
	                                +"\npublic int addmenuGroupLine(int rowcount,"+parameter_for_line_id+parameter_for_line_varchar+")"	                                 +"{"
		                              +"\nint infonum=0;"
		                               
		                             +" \nfor(int i=0; i<rowcount; i++)"
		                              +"\n{"
			                              +"\nif("+header_id_for_line+"!=0)"
                                          +"\n{"			
			                             
						                      +"\n"+line_table_name_first_upper+"\t"+line_table_name_lower+"=new\t"+line_table_name_first_upper+"();"
				 
							                     +get_parameter_for_id_line
							                     +set_parameter_header_id_line
							                     +set_parameter_for_id_line
							                     +set_parameter_for_varchar_line
							                  +"\nhibernateTemplate.saveOrUpdate("+line_table_name_lower+");"		 		
													
				                         +"\n }"
		                              +"\n}"
		                             +"\nreturn 1;"
				
	                                 +" \n}"
    	  		);
    	  //-----------------------------update line part-------------------------------------------
    	  dao_impl_for_update_line.append("\npublic List<"+line_table_name_first_upper+"> update_group_menu_line(int \t"+header_id_for_line+")"
	                                      +"\n{"
		                                    +"\nString sql = \"select\t"+array_para_for_id_line+array_para_for_varchar_line+","+attribute_for_select_statment_lower+"from\t"+line_table_name_lower+"\twhere\t"+header_id_for_line+"='\"+"+header_id_for_line+"+\"'\";"
		                                    +"\nList<"+line_table_name_first_upper+"> group_list = jdbcTemplate.query(sql, new RowMapper<"+line_table_name_first_upper+">() {"
					                        +"\n@Override"
					                        +"\npublic \t"+line_table_name_first_upper+"\tmapRow(ResultSet rs, int rowNum) throws SQLException {"
						
						                    +"\n"+line_table_name_first_upper+"\t" +line_table_name_lower+" = new \t"+line_table_name_first_upper+"();"						
						
											+get_set_for_line_update_id+get_set_for_line_update_varchar+attribute_set_for_grid_dao_line
											+"\nreturn \t"+line_table_name_lower+";"
					                        +"\n}"
					
				                          + "\n});"
				
				                            + "\nreturn group_list;"
		
	                                       + "\n}");
    	  }
    	  dao_impl.append("\npackage com.springmvc.dao;"
                         +"\nimport java.util.Date;"
                         +"\nimport java.sql.ResultSet;"
                         +"\nimport java.sql.SQLException;"
                         +"\nimport java.util.List;"
                         +"\nimport javax.sql.DataSource;"
                         +"\nimport javax.transaction.Transactional;"
                         +"\nimport org.hibernate.Criteria;"
                         +"\nimport org.hibernate.criterion.Restrictions;"
                         +"\nimport org.springframework.beans.factory.annotation.Autowired;"
                         +"\nimport org.springframework.dao.DataAccessException;"
                         +"\nimport org.springframework.jdbc.core.JdbcTemplate;"
                         +"\nimport org.springframework.jdbc.core.ResultSetExtractor;"
                         +"\nimport org.springframework.jdbc.core.RowMapper;"
                         +"\nimport org.springframework.jdbc.core.namedparam.MapSqlParameterSource;"
                         +"\nimport org.springframework.jdbc.core.namedparam.SqlParameterSource;"
                         +"\nimport org.springframework.orm.hibernate5.HibernateTemplate;"
                         +"\nimport org.springframework.stereotype.Repository;"
                         +"\nimport com.mysql.cj.xdevapi.SessionFactory;"
                         
    	                 +"\nimport com.springmvc.model."+table_name_first_upper+";");
    	                 if(line_table_name!=null && !line_table_name.isEmpty())
    	                 { 
    	                	 String line_table_name_lower=line_table_name.toLowerCase();
    	    		         String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
    	    		         String line_table_name_upper=line_table_name.toUpperCase();
    	    		         
    	                     dao_impl.append("import com.springmvc.model."+line_table_name_first_upper+";");
    	                }
    	                 
    	                 
    	                 dao_impl.append("\n@Repository(\""+dao_name_first_upper+"\")"
    	                 +"\npublic class\t"+dao_impl_name_first_upper+"\timplements\t"+dao_name_first_upper 
    	                 +"\n{"
    	                	+"\n@Autowired"
	                        +"\nprivate JdbcTemplate jdbcTemplate;"
                            +"\n@Autowired"
	                        +"\nprivate HibernateTemplate hibernateTemplate;"
	                        
	                        + "\n\n@Override"
                                   +"\npublic List<"+table_name_first_upper+"> userlist() "
                                   +"\n{"
                                   + "\nString sql =\"SELECT\t"+sqlField_id+var_for_pass_para_id_for_upper+date_array_value+sqlField+","+attribute_for_select_statment+" FROM\t"+table_name_upper+"\";"
                                   		+ "\nList<"+table_name_first_upper+"> userlist = jdbcTemplate.query(sql, new RowMapper<"+table_name_first_upper+">()"
                                           +"\n{"
                                   		+ "	\n@Override"
		                                    +"\npublic\t" +table_name_first_upper +"\tmapRow(ResultSet rs, int rowNum) throws SQLException"
		                                      +"\n{"
			                                    +"\n"+table_name_first_upper+"\t" +table_name_lower+" = new\t"+table_name_first_upper+"();"
			                                    		+ "\n"+setField_id+id_array_set_in_dao+date_array_set_in_dao_for_date+setField+attribute_set_for_grid_dao
			                                    		
			                                    		+"\nreturn\t"+table_name_lower+";"
                                                         +"\n}"

                                                      +"\n});"

                                              + "\nreturn userlist;"
                                             +"}"+dao_impl_prefield+dao_impl_save
                                             
                                            + "\n\n@Transactional"
                                         	+"\npublic int saveheader("+table_name_first_upper+"\t"+table_name_lower+")"
                                         	+"\n{"
                                         		+"\nhibernateTemplate.saveOrUpdate("+table_name_lower+");"
                                         		+"\nSystem.out.println("+table_name_lower+".get"+get_id_for_sbmit_header_line+"());"
                                         		+"\nreturn \t"+table_name_lower+".get"+get_id_for_sbmit_header_line+"();"
                                         	
                                         	+"\n} "  
                                         	+dao_impl_save_line+dao_impl_for_update_line
                                             
    	                + "}"
    	                 
    			);
    	 
    	 
    	                 
    	                 
    	                 
    	                 
     //----------------------------------jsp coding------------------------
     
     
                    importsection.append("\n<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>"
             
                                             +"\n<%@ page import=\"java.util.ArrayList\"%>"
                                        + "\n<%@ page import=\"java.util.Date\"%>"
                                         +"\n<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>"
                                        + "\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\"%>"
                                         +"\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>");
                                         		
				    headsection.append("\n<html lang=\"en\">\n<head>" 
				    +"\n<meta http-equiv=\"X-UA-Compatible\"  content=\"IE=edge,chrome=1\">"
				    +"\n<meta charset=\"utf-8\" />"
				    +"\n<title>Realnet Oil Engines Ltd</title>"
				    +"\n<meta name=\"description\" content=\"Common form elements and layouts\" />"
				    +"\n<meta name=\"viewport\""
	                +"\ncontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />"
				    +"\n<!-- bootstrap & fontawesome -->"
                    +"\n<link rel=\"stylesheet\""
	                +"\n href=\"<c:url value='/resources/assets/css/bootstrap.min.css'/>\" />"
                    +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css'/>\" />"
	                +"\n<!-- page specific plugin styles -->"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/jquery-ui.custom.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/chosen.min.css' />\" />"
	                
	                +"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css\">"
	                +"<link href=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css\" rel=\"stylesheet\" type=\"text/css\">"
	                +"<script src=\"//code.jquery.com/jquery-1.11.2.min.js\"></script>"
	                +"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js\" type=\"text/javascript\" ></script>"
	                 
	                
	                
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/datepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/bootstrap-timepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/daterangepicker.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/bootstrap-datetimepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/colorpicker.min.css' />\" />"
	                +"\n<!-- text fonts -->"
				    +"\n<link rel=\"stylesheet\""
				    +"\n\nhref=\"<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />\" />"
                    +"\n<!-- ace styles -->"
				    +"\n<link rel=\"stylesheet\""
				    +"\nhref=\"<c:url value='/resources/assets/css/ace.min.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style' />\" />"
				    +"\n<!-- inline styles related to this page -->"

				    +"\n<!-- ace settings handler -->"
				    +"\n<script src=\"<c:url value='/resources/assets/js/ace-extra.min.js'/>\""
				    	+"\ntype=\"text/javascript\"></script>"


				    		 +"\n<script>"
				    		    +"\nsubmitForms = function()"
				    		     +"\n{"
				                   +"\ndocument.forms[\"userdetails1\"].submit();"
				                   +"\ndocument.forms[\"userdetails2\"].submit();"
				                 +"}"
				            +"\n</script> "
				            +"\n<script src=\"resources/assets/js/ace-extra.min.js\"></script>"
				            +"\n<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>"
				            +"\n<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>"
				            
				    			
				    +" \n</head>");
     
     
     
				    form.append("\n<form action=\""+action+"\" class=\"form-horizontal\" id=\"Regi\" method=\"Post\">"
				    		+ "\n<input type=\"hidden\" name=\"test\" id=\"test\" value=\"\" />"
				    		+ " \n<table>"
							+ "\n<tr>"
							+ "<td  style=\"display:none;\">"								
	                            +" <input type=\"text\" id=\""+header_id_for_line+"\" class=\"col-xs-10 col-sm-11\"  name=\""+header_id_for_line+"\" value=\"\"/>"									
                              +"</td>");
				    
				    
				    for(int i=0;i<id_notprimary.size();i++)
	            	  {
				    	
				    	String field_name=id_notprimary.get(i).getField_name();
				    	String data_type=id_notprimary.get(i).getData_type();
				   	    String lower_case=field_name.toLowerCase();
				   	    String only_upper=field_name.toUpperCase();
				   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
				    	
				            if(data_type.equals("int"))
				            {
				            	form.append("\n<td>"
		          		    	+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
		          		    	+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
		          		    	 +first_upper
		          		    	 +"\n<i class=\"menu-icon fa red\"> *</i>"
		          		    	+"\n</label>"
		          		    	+"\n<div class=\"col-xs-12 col-sm-9\">"
		          		    	+"\n<div class=\"clearfix\">"
		          		    	+"\n<input   value=\"${"+table_name_lower+"_updt."+lower_case+"}\" type=\"text\" name=\""+lower_case+"\"id=\""+lower_case+"\"class=\"col-xs-12 col-sm-4\" required/>"
		          		    	+"\n</div>"
		          		    	+"\n</div>"
		          		    	+"\n</div>"
		          		    	+"\n</td>");
				            }
				    	
				    	
	            	  }
				    form.append("\n</tr>");
				    
				    form.append("\n<tr>");
				    
				    for(int i=0;i<datetime_list.size();i++)
	            	  {
				    	
				    	System.out.println("---under for loop latest fo date------");
				    	
				    	String field_name=datetime_list.get(i).getField_name();
				    	String data_type=datetime_list.get(i).getData_type();
				   	    String lower_case=field_name.toLowerCase();
				   	    String only_upper=field_name.toUpperCase();
				   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
				    	
				   	    String mandatory=datetime_list.get(i).getMandatory();
				   	    String readonly=datetime_list.get(i).getReadonly();
				   	    String hidden=datetime_list.get(i).getHidden();
				   	    
				   	    String mapping=datetime_list.get(i).getMapping();
				   	    
				   	    String mapping_lower=mapping.toLowerCase();
				   	    
				   	    
				   	     String dependent=datetime_list.get(i).getDependent();
				   	     String dependent_on=datetime_list.get(i).getDependent_on();
				   	     String dependent_sp=datetime_list.get(i).getDependent_sp();
				   	     String dependent_sp_param=datetime_list.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=datetime_list.get(i).getSequence();
				   	     String seq_name=datetime_list.get(i).getSeq_name();  
				   	     String seq_sp=datetime_list.get(i).getSeq_sp();
				   	     String seq_sp_param=datetime_list.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=datetime_list.get(i).getValidation_1();
				   	       String val_type=datetime_list.get(i).getVal_type();
				   	       String val_sp=datetime_list.get(i).getVal_sp();
				   	       String val_sp_param=datetime_list.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=datetime_list.get(i).getDefault_1();
				   	       String default_typename=datetime_list.get(i).getDefault_type();
				   	       String default_value=datetime_list.get(i).getDefault_value();
				   	       String default_sp=datetime_list.get(i).getDefault_sp();
				   	       String default_sp_param=datetime_list.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=datetime_list.get(i).getCalculated_field();
				   	       String cal_sp=datetime_list.get(i).getCal_sp();
				   	       String cal_sp_param=datetime_list.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=datetime_list.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=datetime_list.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=datetime_list.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=datetime_list.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=datetime_list.get(i).getSp_name_for_autocomplete();

                           System.out.println("-----value of mantadoryr laest-----"+mandatory);
				   	    
                           if(data_type.equals("datetime"))
                           {
                        	   
       				    	System.out.println("---under if condition loop latest------");

                                  form.append("\n<td>"
										+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											   +"\n "+field_name);
                                  
								     System.out.println("-------------mandatory for testing=------------------"+mandatory);

											  if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											

											form.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">"
												+"<div class=\"input-group input-append date\" id=\"datePicker"+i+"\">");
											
											
												 form.append("\n<input" );
												
													if(hidden.equals("Y"))
													{
														form.append("  type=\"hidden\" ");
													}else
													{
														form.append("  type=\"text\" ");
													}
													
													    form.append( "name=\""+mapping_lower+"\" id=\""+mapping_lower+"\" placeholder=\"picup Date\" class=\"form-control\"");
														
													
											if(mandatory.equals("Y"))
											{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form.append("required");
											}
											
											if(readonly.equals("Y"))
											{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form.append("  readonly");
											}
													form.append("/>\n"
															+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
															+"\n</span>"
															+"\n</div>"
															+ "\n</div>"
											+"\n</div>"
										+"\n</div>"
										+"\n</td>");
                           }
                           
                           date_script.append(" $('#datePicker"+i+"').datepicker({format : 'dd-mm-yyyy'}).on('changeDate', function(e) {$('#eventForm').formValidation('revalidateField', 'date');});\n\n");
				    	
				    	
	            	  }
				    
				    
				    form.append("\n</tr>");
				    
				    form.append("\n<tr>");
				    int count=1;
				    
				    
				    
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	//System.out.println("value of i in form builder::"+i);
	                	String field_name4=rn_userlist.get(i).getField_name();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			String lower_case=field_name4.toLowerCase();
						 System.out.println("field name ganesh-------"+field_name4+" and i"+i);
						 /*
						 * * System.out.println("mapping-------"+mapping4);
						 * System.out.println("data type-------"+data_type4);
						 */
	          		    
	          		    String mapping_lower=mapping4.toLowerCase();
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
			/*
			 * int dividend=count; int divisor=3; int remainder = dividend % divisor;
			 * System.out.println("reminder::"+remainder);
			 * System.out.println("count before::"+count); count++;
			 * System.out.println("count after::"+count);
			 * 
			 * if(i++ == rn_userlist.size() - 1){ // Last iteration
			 * System.out.println("data type fo last iteration::"+data_type4); }
			 */
			
			 int dividend=count; 
			 int divisor=3; 
			 int remainder = dividend % divisor;
			 
			 System.out.println("remainder::"+remainder);
			 System.out.println("count::"+count);
			 count++;
				   	       
	          		   // if(i<=2)
	          		   // {
	          		    	 if(type_field.equals("textfield"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  

												  if(mandatory.equals("Y"))
												  {   
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
														    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
														    form.append( "name=\""+mapping_lower+"\" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  
													form.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  
													form.append("  readonly");
												}
														form.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("date"))
	                         {
	          		    		form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                

												  if(mandatory.equals("Y"))
												  {  
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">"
													+"<div class=\"input-group input-append date\" id=\"datePicker\">");
												
												
												  form.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														
														form.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  
												form.append("required");
												}
												
												
												form.append("  readonly");
												
												form.append("/>\n"
																+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																+"\n</span>"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
												
												
	                         }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  

												  if(mandatory.equals("Y"))
												  {   
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
														    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
														    form.append( "name="+mapping_lower+" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" ");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
									   autocomplete_script_latest.append("<script>"
																		+"var options = {"
																		+"url: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
																		+"getValue: \"drop_value\","
																		+"list: {"	
																		+"match: {"
																		+"enabled: true"
																		+"}},"
																		+"theme: \"square\""
																		+"};"
																		+"$(\"#"+mapping_lower+"\").easyAutocomplete(options);"
																		+"</script>");				
																																
																								
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  

												  if(mandatory.equals("Y"))
												  {  
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form.append( "\n<select name=\""+mapping_lower+"\" id=\""+mapping_lower+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                                +"\n<option >---select---</option>"
                                                               +"\n</select>");
												}else{	
													
						
											    form.append("\n<select name=\""+mapping_lower+"\" id=\""+mapping_lower+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
											    form.append( "\t"+mapping4);
											    form.append( "\" >"
                                                  +"\n<option >---select---</option>"
                                                  +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  
													form.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  
													form.append("readonly");
												}
														form.append(">${drop_value}</option>\n"
                                                  +"</select>\n");
											}
                                                  form.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
                                                  
                                       dropdown_script_latest.append("\n$.ajax({"
																		+"\nurl: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
																		+"\nsuccess: function(data)"
																		+"\n{"
																		+"\nconsole.log(data);"
																		+"\nvar select = $('#"+mapping_lower+"');"
																		+"\n$('<option>').text('select').val(0).appendTo(select);"
																		+"\nselect.find('option').remove();"
																		+"\n$.each(data, function(index, value) {"
																		+"\n$('<option>').text(value.drop_value).appendTo(select);"
																		+"\n});"
																		+"\n}"
																		+"\n});");          
																		                                                  
                                                  
                                                  
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"checkbox\" ");
														}
														
														    form.append( "name="+mapping_lower+" id="+mapping_lower+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus"+mapping_lower+"()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												
														form.append("/>\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
									 togglescript.append("function CheckUserStatus"+mapping_lower+"() {"
									 		+ "\r\n" 
									        + "\r\n" 
											+ "	\n		if (document.getElementById(\""+mapping_lower+"\").checked == true) {\r\n" 
									 		+ "	\n			document.getElementById(\""+mapping_lower+"\").value = 'Y';\r\n" 
									 		+ "\r\n" 
									 		+ "			} else if (document.getElementById(\""+mapping_lower+"\").checked == false) {\r\n" 
									 		+ "				document.getElementById(\""+mapping_lower+"\").value = 'N';\r\n" 
									 		+ "			}\r\n" 
									 		+ "		}");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  

												  if(mandatory.equals("Y"))
												  {   
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													 form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"checkbox\" ");
														}
														
														    form.append( "name="+mapping_lower+" id="+mapping_lower+"  onblur=\"CheckUserStatus"+mapping_lower+"()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  
													form.append("required");
												}
												
												
														form.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														togglescript.append("function CheckUserStatus"+mapping_lower+"() {"
														 		+ "\r\n" 
														        + "\r\n" 
																+ "	\n		if (document.getElementById(\""+mapping_lower+"\").checked == true) {\r\n" 
														 		+ "	\n			document.getElementById(\""+mapping_lower+"\").value = 'Y';\r\n" 
														 		+ "\r\n" 
														 		+ "			} else if (document.getElementById(\""+mapping_lower+"\").checked == false) {\r\n" 
														 		+ "				document.getElementById(\""+mapping_lower+"\").value = 'N';\r\n" 
														 		+ "			}\r\n" 
														 		+ "		}");
	                           }
	          		    	 
	          		   //  }//if close
			/*
			 * if(remainder==0) { form.append("\n</tr>"); form.append("\n<tr>"); }else
			 * if(i++ == rn_userlist.size() - 1) { form.append("\n</tr>"); }
			 */
	          		    
	          		    	 if(remainder==0) 
	          		    	 { 
	          		    		 form.append("\n</tr>"); 
	          		    		 form.append("\n<tr>"); 
	          		         }
	          		    	
	          		    	
	          		      
	          		  }
	          		    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\\n\\n\\n\\n\\n\\n\\n\\n\\n");
				   // form.append("\n</tr>");
				    
				    
			/*	    form.append("\n<tr>");
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		     String mapping_lower=mapping4.toLowerCase();
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();
	          		    
	          		    if(i>2 && i<=5)
	          		    {

		          		    
		          		    	 if(type_field.equals("textfield"))
		                           {
		                                  form.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                  
										     System.out.println("-------------mandatory for testing=------------------"+mandatory);

													  if(mandatory.equals("Y"))
													  {   
														  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													form.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">");
														 form.append("\n<input" );
														
															if(hidden.equals("Y"))
															{
																form.append("  type=\"hidden\" ");
															}else
															{
																form.append("  type=\"text\" ");
															}
															if(calculated_field.equals("Y"))
															{
															    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
															}
															else
															{
															    form.append( "name=\""+mapping_lower+"\" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
															}	
															
													if(mandatory.equals("Y"))
													{ 	  
														form.append("required");
													}
													
													if(readonly.equals("Y"))
													{ 	  
														form.append("  readonly");
													}
															form.append("/>\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                           }
		          		    	 
		          		    	 
		          		    	 
		          		    	if(type_field.equals("date"))
		                         {
		          		    		form.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                

													  if(mandatory.equals("Y"))
													  {   
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													  form.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">"
														+"<div class=\"input-group input-append date\" id=\"datePicker\">");
													
													
													  form.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
														
															if(hidden.equals("Y"))
															{
																form.append("  type=\"hidden\" ");
															}else
															{
																form.append("  type=\"text\" ");
															}
															
															form.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
																
															
													if(mandatory.equals("Y"))
													{ 	  
													form.append("required");
													}
													
													
													form.append("  readonly");
													
													form.append("/>\n"
																	+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																	+"\n</span>"
																	+"\n</div>"
																	+ "\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                         }
		          		    	 
		          		    	 
		          		    	 
		          		    	 
		          		    	 
		          		    	 
		          		    	if(type_field.equals("autocomplete"))
		                           {
		                                  form.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                  

													  if(mandatory.equals("Y"))
														  System.out.println("-------------in loop 1 part 2 required-------------------");			  {   
														  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													form.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">");
														
														 form.append("\n<input" );
														
															if(hidden.equals("Y"))
															{
																form.append("  type=\"hidden\" ");
															}else
															{
																form.append("  type=\"text\" ");
															}
															if(calculated_field.equals("Y"))
															{
															    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
															}
															else
															{
															    form.append( "name="+mapping_lower+" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" ");
															}	
															
													
															form.append("/>\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
															
															
															 autocomplete_script_latest.append("<script>"
																		+"var options = {"
																		+"url: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
																		+"getValue: \"drop_value\","
																		+"list: {"	
																		+"match: {"
																		+"enabled: true"
																		+"}},"
																		+"theme: \"square\""
																		+"};"
																		+"$(\"#"+mapping_lower+"\").easyAutocomplete(options);"
																		+"</script>");				
															
															
															
											
		                           }
		          		    	
		          		    	
		          		    	if(type_field.equals("dropdown"))
		                           {
		                                  form.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                  

													  if(mandatory.equals("Y"))
													  {   
														  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													form.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">");
															
													if(dependent.equals("Y"))
													{
														
														form.append( "\n<select name=\"state_name\" id=\""+mapping_lower+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
	                                                                +"\n<option >---select---</option>"
	                                                               +"\n</select>");
													}else{	
														
							
												    form.append("\n<select name=\""+mapping_lower+"\" id=\""+mapping_lower+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
												    form.append( "\t");
												    form.append( "\" >"
	                                                  +"\n<option >---select---</option>"
	                                                  +"\n<option value=\"${drop_value}\"");
														
															
													if(mandatory.equals("Y"))
													{ 	  
														form.append("required");
													}
													
													if(readonly.equals("Y"))
													{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
														form.append("readonly");
													}
															form.append(">${drop_value}</option>\n"
	                                                  +"</select>\n");
												}
	                                                  form.append("</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
	                                                  
	                                                  
	                                                  dropdown_script_latest.append("\n$.ajax({"
																+"\nurl: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
																+"\nsuccess: function(data)"
																+"\n{"
																+"\nconsole.log(data);"
																+"\nvar select = $('#"+mapping_lower+"');"
																+"\n$('<option>').text('select').val(0).appendTo(select);"
																+"\nselect.find('option').remove();"
																+"\n$.each(data, function(index, value) {"
																+"\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
																+"\n});"
																+"\n}"
																+"\n});");             
	                                                  
	                                                  
	                                                  
		                           }
		          		    	
		          		    	
		          		    	 if(type_field.equals("togglebutton"))
		                           {
		                                  form.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                  
										     System.out.println("-------------mandatory for testing=------------------"+mandatory);

													  if(mandatory.equals("Y"))
													  {   System.out.println("-------------in loop 1-------------------");
														  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													form.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">");
													
													
														 form.append("\n<input" );
														
															if(hidden.equals("Y"))
															{
																form.append("  type=\"hidden\" ");
															}else
															{
																form.append("  type=\"checkbox\" ");
															}
															
															    form.append( "name="+mapping_lower+" id="+mapping_lower+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
																
															
													if(mandatory.equals("Y"))
													{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
														form.append("required");
													}
													
													
															form.append("/>\n"
																	+"\n<span class=\"lbl\"></span>"
																	
																	+ "\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                           }
		          		    	 
		          		    	 
		          		    	 
		          		    	if(type_field.equals("checkbox"))
		                           {
		                                  form.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                  
										     System.out.println("-------------mandatory for testing=------------------"+mandatory);

													  if(mandatory.equals("Y"))
													  {   System.out.println("-------------in loop 1-------------------");
														  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													form.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">");
													
													
														 form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
														
															if(hidden.equals("Y"))
															{
																form.append("  type=\"hidden\" ");
															}else
															{
																form.append("  type=\"checkbox\" ");
															}
															
															    form.append( "name="+mapping_lower+" id="+mapping_lower+"  onblur=\"CheckUserStatusHeader1()\"");
																
															
													if(mandatory.equals("Y"))
													{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
														form.append("required");
													}
													
													
															form.append("/>\n"
																	+ "\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                           }
	          		    	
		          		    }
	          		     }
	          		  
				    
				    form.append("\n</tr>");
				    
				    form.append("\n<tr>");
				    
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		     String mapping_lower=mapping4.toLowerCase();
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();
	          		    
	          		    if(i>5 && i<=8)
	          		    {
	          		    	if(type_field.equals("textfield"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
														    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
														    form.append( "name=\""+mapping_lower+"\" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}
														form.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	
	          		    	if(type_field.equals("date"))
	                         {
	          		    		form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">"
													+"<div class=\"input-group input-append date\" id=\"datePicker\">");
												
												
												  form.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														
														form.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form.append("required");
												}
												
												
												form.append("  readonly");
												
												form.append("/>\n"
																+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																+"\n</span>"
																+"\n</div>"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                         }
	          		    	 
	          		    	
	          		    	
	          		    	
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
														    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
														    form.append( "name="+mapping_lower+" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" ");
														}	
														
												
														form.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														 autocomplete_script_latest.append("<script>"
																	+"var options = {"
																	+"url: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
																	+"getValue: \"drop_value\","
																	+"list: {"	
																	+"match: {"
																	+"enabled: true"
																	+"}},"
																	+"theme: \"square\""
																	+"};"
																	+"$(\"#"+mapping_lower+"\").easyAutocomplete(options);"
																	+"</script>");					
														
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form.append( "\n<select name=\"state_name\" id=\""+mapping_lower+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                             +"\n<option >---select---</option>"
                                                            +"\n</select>");
												}else{	
													
						
											    form.append("\n<select name=\""+mapping_lower+"\" id=\""+mapping_lower+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
											    form.append( "\t");
											    form.append( "\" >"
                                               +"\n<option >---select---</option>"
                                               +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("readonly");
												}
														form.append(">${drop_value}</option>\n"
                                               +"</select>\n");
											}
                                               form.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
                                               
                                               
                                               dropdown_script_latest.append("\n$.ajax({"
														+"\nurl: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
														+"\nsuccess: function(data)"
														+"\n{"
														+"\nconsole.log(data);"
														+"\nvar select = $('#"+mapping_lower+"');"
														+"\n$('<option>').text('select').val(0).appendTo(select);"
														+"\nselect.find('option').remove();"
														+"\n$.each(data, function(index, value) {"
														+"\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
														+"\n});"
														+"\n}"
														+"\n});");        
                                               
                                               
                                               
                                               
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"checkbox\" ");
														}
														
														    form.append( "name="+mapping_lower+" id="+mapping_lower+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												
														form.append("/>\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													 form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"checkbox\" ");
														}
														
														    form.append( "name="+mapping_lower+" id="+mapping_lower+"  onblur=\"CheckUserStatusHeader1()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												
														form.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		     }
	          		  }
				    form.append("\n</tr>");
				    
				    form.append("\n<tr>");
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    String mapping_lower=mapping4.toLowerCase();
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();
	          		    
	          		   if(i>8 && i<=11)
	          		    {
	          		    	if(type_field.equals("textfield"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
														    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
														    form.append( "name=\""+mapping_lower+"\" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}
														form.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("date"))
	                         {
	          		    		form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">"
													+"<div class=\"input-group input-append date\" id=\"datePicker\">");
												
												
												  form.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														
														form.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form.append("required");
												}
												
												
												form.append("  readonly");
												
												form.append("/>\n"
																+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																+"\n</span>"
																+"\n</div>"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                         }
	          		    	 
	          		    	
	          		    	
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
														    form.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
														    form.append( "name="+mapping_lower+" id=\""+mapping_lower+"\" class=\"col-xs-12 col-sm-4\" ");
														}	
														
												
														form.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														 autocomplete_script_latest.append("<script>"
																	+"var options = {"
																	+"url: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
																	+"getValue: \"drop_value\","
																	+"list: {"	
																	+"match: {"
																	+"enabled: true"
																	+"}},"
																	+"theme: \"square\""
																	+"};"
																	+"$(\"#"+mapping_lower+"\").easyAutocomplete(options);"
																	+"</script>");						
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	                                        form.append("\n<td>"
	                                                    +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												        +"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												        +"\n "+field_name4);
	                                  
									        System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form.append( "\n<select name=\""+mapping_lower+"\" id=\""+mapping_lower+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                             +"\n<option >---select---</option>"
                                                            +"\n</select>");
												}else{	
													
						
											    form.append("\n<select name=\""+mapping_lower+"\" id=\""+mapping_lower+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
											    form.append( "\t");
											    form.append( "\" >"
                                               +"\n<option >---select---</option>"
                                               +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("readonly");
												}
														form.append(">${drop_value}</option>\n"
                                               +"</select>\n");
											}
                                               form.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
                                               
                                               
                                               
                                               dropdown_script_latest.append("\n$.ajax({"
														+"\nurl: '${pageContext.request.contextPath}/"+mapping_lower+"_list',"
														+"\nsuccess: function(data)"
														+"\n{"
														+"\nconsole.log(data);"
														+"\nvar select = $('#"+mapping_lower+"');"
														+"\n$('<option>').text('select').val(0).appendTo(select);"
														+"\nselect.find('option').remove();"
														+"\n$.each(data, function(index, value) {"
														+"\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
														+"\n});"
														+"\n}"
														+"\n});");      
                                               
                                               
                                               
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													 form.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"checkbox\" ");
														}
														
														    form.append( "name="+mapping_lower+" id="+mapping_lower+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												
														form.append("/>\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													 form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														if(hidden.equals("Y"))
														{
															form.append("  type=\"hidden\" ");
														}else
														{
															form.append("  type=\"checkbox\" ");
														}
														
														    form.append( "name="+mapping_lower+" id="+mapping_lower+"  onblur=\"CheckUserStatusHeader1()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}
												
												
														form.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		     }
	          		  }
				    
				    form.append("\n</tr>");   */
				    
				    
				    
				    
				    form.append("\n<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_extension.jsp\"%>\n</table>");
				    
				    if(line_table_name !=null && !line_table_name.isEmpty())
				    {
				    	
				    	 String line_table_name_lower=line_table_name.toLowerCase();
					     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
					     String line_table_name_upper=line_table_name.toUpperCase();
				         form.append(" <%@include file=\"/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_line_view.jsp\"%>");
				    }

				    form.append("\n<div class=\"hr hr-dotted\"></div>"
				    		+ "\n<div class=\"wizard-actions\">"
                                                            +"\n<button type=\"submit\" class=\"btn btn-success center\" onclick=\"submitForms()\">"
															  +"\nCREATE"
															+"\n</button>"
														+"\n</div> " 
						  +"\n</form>");
				 
				    
 //-----------------------------coding for line part----------------------------------------------------------------------------
				    
				    
				    for_line_part.append("\n<div class=\"table-header\" style=\"margin-top: 30px;\">Group Lines Details</div>"
							+"\n<div>"
							+"\n<% int n=0; %>"
							+"\n<input type=\"hidden\" id=\"rowcount1\" name=\"rowcount1\">"
							+"\n<input type=\"hidden\" id=\"delrow1\" name=\"delrow1\" value=\"\">"
							+"\n<table id=\"dynamic-table1\" class=\"table table-striped table-bordered table-hover\">"
							+"\n<thead>"
							+"\n<tr>");
					 
						//for loop for id is primary
						
						
							 for(int i=0;i<line_id_primary.size();i++)
			            	  {
			                	String field_name4=line_id_primary.get(i).getField_name();
			          			String mapping4=line_id_primary.get(i).getMapping();
			          			String data_type4=line_id_primary.get(i).getData_type();	
							
			          			for_line_part.append ("\n<th>"
							                          +"\n<center>Select</center>"
							                       +"\n</th>");
			            	  }
							 
							
							 
						//for loop for other field (like varchar field)	this is only heading
							
							
							 for(int i=0;i<line_varchar.size();i++)
			            	  {
			                	String field_name4=line_varchar.get(i).getField_name();
			          			String mapping4=line_varchar.get(i).getMapping();
			          			String data_type4=line_varchar.get(i).getData_type();
			          			
							           for_line_part.append("\n<th>"
							                                 +"\n<center>"+field_name4+"</center>"
							                                 +"\n</th>");
			            	    }	
							 
						if(line_table_name !=null && !line_table_name.isEmpty())
							{
								     String line_table_name_lower=line_table_name.toLowerCase();
								     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
								     String line_table_name_upper=line_table_name.toUpperCase();
								     
							        for_line_part.append("\n\n<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension.jsp\"%>\n\n</tr>");
							}
							for_line_part.append("\n</thead>"
							+"\n<tbody>"
                                  //upate code
							
							+"\n</tbody>"
							+"\n</table>"

							+"\n<input type=\"button\" class=\"btn btn-md btn-success\""
							+"\nstyle=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%;\""
							+"\nvalue=\"Add Row\" onclick=\"AddRow()\">"


							+"\n<input type=\"button\" class=\"btn btn-md btn-success\""
							+"\nstyle=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%; font-size: auto;\""
							+"\nvalue=\"Delete Row\" onclick=\"del()\">"
                           +"\n</div>");
				    
				   
							
							
							
							
//---------------------line part update------------------------------	
							
						if(line_table_name !=null && !line_table_name.isEmpty())
						{
							
							 String line_table_name_lower=line_table_name.toLowerCase();
						     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
						     String line_table_name_upper=line_table_name.toUpperCase();
						     
							for(int i=0;i<line_id_primary.size();i++)
			            	  {
			                	String field_name4=line_id_primary.get(i).getField_name();
			          			String mapping4=line_id_primary.get(i).getMapping();
			          			String data_type4=line_id_primary.get(i).getData_type();
			          			
			          			String lower_case=field_name4.toLowerCase();
							     
			          			line_value_update.append( "<td style=\"display:none\">'+($('#dynamic-table1 tr').length)+'"
			          				             +"<input type=\"hidden\" value=\"${"+line_table_name_lower+"_updt."+lower_case+"}\" id=\""+lower_case+"\" name=\""+lower_case+"\"  <%=j%>/>"
			          					  
			          				              +"<td>"
			          				                 +"<center>"
			          				                    + "<input type=\"checkbox\" class=\"Deleterow\" id=\"chk\" name=\"chk\" style=\"text-align:center;\"/>"
			          				                 +"</center>"
			          				            +"</td>");
			                  }
							
							
					
				            //for loop for other field values
				            for(int i=0;i<line_varchar.size();i++)
			            	  {
			                	String field_name4=line_varchar.get(i).getField_name();
			          			String mapping4=line_varchar.get(i).getMapping();
			          			String data_type4=line_varchar.get(i).getData_type();
			          			String lower_case=mapping4.toLowerCase();
							
			          			
			          			
			          			
			          			
			          			System.out.println("field name-------"+field_name4); 
			          		    System.out.println("mapping-------"+mapping4);
			          		    System.out.println("data type-------"+data_type4);
			          		    
			          		    
			          		    String mandatory=line_varchar.get(i).getMandatory();
						   	    String readonly=line_varchar.get(i).getReadonly();
						   	    String hidden=line_varchar.get(i).getHidden();
						   	    
						   	    //String mapping=rn_userlist.get(i).getMapping();
						   	    
						   	    String type_field=line_varchar.get(i).getType_field();
						   	    
						   	    
						   	     String dependent=line_varchar.get(i).getDependent();
						   	     String dependent_on=line_varchar.get(i).getDependent_on();
						   	     String dependent_sp=line_varchar.get(i).getDependent_sp();
						   	     String dependent_sp_param=line_varchar.get(i).getDependent_sp_param();
						   	     
						   	     String sequence=line_varchar.get(i).getSequence();
						   	     String seq_name=line_varchar.get(i).getSeq_name();  
						   	     String seq_sp=line_varchar.get(i).getSeq_sp();
						   	     String seq_sp_param=line_varchar.get(i).getSeq_sp_param();
						   	     
						   	       String validation_1=line_varchar.get(i).getValidation_1();
						   	       String val_type=line_varchar.get(i).getVal_type();
						   	       String val_sp=line_varchar.get(i).getVal_sp();
						   	       String val_sp_param=line_varchar.get(i).getVal_sp_param();
						   	       
						   	       
						   	       String default_1=line_varchar.get(i).getDefault_1();
						   	       String default_typename=line_varchar.get(i).getDefault_type();
						   	       String default_value=line_varchar.get(i).getDefault_value();
						   	       String default_sp=line_varchar.get(i).getDefault_sp();
						   	       String default_sp_param=line_varchar.get(i).getDefault_sp_param();
						   	       
						   	       String calculated_field=line_varchar.get(i).getCalculated_field();
						   	       String cal_sp=line_varchar.get(i).getCal_sp();
						   	       String cal_sp_param=line_varchar.get(i).getCal_sp_param();
						   	       
						   	       String add_to_grid=line_varchar.get(i).getAdd_to_grid();
						   	       
						   	       String sp_for_dropdown=line_varchar.get(i).getSp_for_dropdown();
						   	       String sp_for_autocomplete=line_varchar.get(i).getSp_for_autocomplete();
						   	       
						   	       String sp_name_for_dropdown=line_varchar.get(i).getSp_name_for_dropdown();
						   	       String sp_name_for_autocomplete=line_varchar.get(i).getSp_name_for_autocomplete();
							
						   	     if(type_field.equals("textfield"))
						   	     {  
						   	       
						   	    	line_value_update.append("<td>"
			          				                    +"<input");
			          			     if(hidden.equals("Y"))
			          			     {
			          			    	line_value_update.append("\ttype=\"hidden\"");
			          			     }else{
			          			    	line_value_update.append("\ttype=\"text\"");
			          			     }
			          			   
			          			   line_value_update.append("\t value=\"${"+line_table_name_lower+"_updt."+lower_case+"}\" id=\""+lower_case+"\" name=\""+lower_case+"\" style=\"width:100%;\" <%=j%>");
			          			   
			          			   if(mandatory.equals("Y"))
			          			     {
			          				    line_value_update.append(" \trequired ");
			          			      }
			          			   
			          			   if(readonly.equals("Y"))
		          			       {
			          				 line_value_update.append(" \treadonly ");
		          			        }
			          			   
			          			 line_value_update.append(" />");
			          			line_value_update.append("</td>");
			                     }
						   	     
						   	   if(type_field.equals("date"))
						   	     {  
						   	       
						   		line_value_update.append("<td>"
			          			                          +"<div class=\"clearfix\">"
			          			                            +"<div class=\"input-group input-append date\" id=\"datePicker\" style=\"width:100%;\">"
			          				                           +"<input  type=\"text\" value=\"${"+line_table_name_lower+"_updt."+lower_case+"}\" name=\""+lower_case+"\" id=\""+lower_case+"\" placeholder=\"picup Date\" class=\"form-control\"   <%=j%>/>"
			          				                              +"<span class=\"input-group-addon\">"
			          				                              +"<i class=\"fa fa-calendar bigger-110\"></i></span>"
			          				                           +"</div>"
			          				                          +"</div>"
			          		                               +"</td>");
			                     }
						   	   
						   	 if(type_field.equals("dropdown"))
					   	     {  
					   	       
						   		line_value_update.append("<td>"
		          			                        + "<select  id=\""+lower_case+"\" name=\""+lower_case+"\" class=\"ChangeDropdown\" style=\"width:100%;\" required <%=j%>>"
		          		                                +"<option selected disabled value=\"\"></option>"
		          		                              +"</select>"
		          		                            +"</td>");
		                     }
						   	 
						   	 
						   	 if(type_field.equals("autocomplete"))
					   	     {  
					   	       
						   		line_value_update.append("<td>"
			      				                    +"<input");
			      			     if(hidden.equals("Y"))
			      			     {
			      			    	line_value_update.append("\ttype=\"hidden\"");
			      			     }else{
			      			    	line_value_update.append("\ttype=\"text\"");
			      			     }
			      			   
			      			   line_value_update.append("\t value=\"${"+line_table_name_lower+"_updt."+lower_case+"}\"  id=\""+lower_case+"\" name=\""+lower_case+"\" style=\"width:100%;\" <%=j%>");
			      			   
			      			   if(mandatory.equals("Y"))
			      			     {
			      				 line_value_update.append(" \trequired ");
			      			      }
			      			   
			      			   if(readonly.equals("Y"))
			  			       {
			      				 line_value_update.append(" \treadonly ");
			  			        }
			      			   
			      			 line_value_update.append(" />");
			      			line_value_update.append("</td>");
		                     }
						   	 
						   	 
						   	 if(type_field.equals("togglebutton"))
					   	     {  
					   	       
						   		line_value_update.append("<td>"
		          			                         +"<center>"
		          		                                 +"<div class=\"clearfix\">"
		          			                              +" <input name=\""+lower_case+"\" value=\"${"+line_table_name_lower+"_updt."+lower_case+"}\"  id=\""+lower_case+"\" class=\"ace ace-switch ace-switch-6\" type=\"checkbox\" onclick=\"CheckUserStatusGrid(id)\" <%=j%>/>"
		          				                             +"<span class=\"lbl\"></span>"
		          			                            +"</div>"
		          		                                 +"</center>"
		          		                               +"</td>");
		                     }
						   	 
						   	 if(type_field.equals("checkbox"))
					   	     {  
					   	       
						   		line_value_update.append("<td>"
		          			                        +"<center>"
		          			   	                      +"<input type=\"checkbox\"  value=\"${"+line_table_name_lower+"_updt."+lower_case+"}\" id=\""+lower_case+"\" name=\""+lower_case+"\" style=\"text-align:center;\"/<%=j%>>"
		          			   	                    +"</center>"
		          			                      +"</td>");
		                     }
						   	 
							
			            }
			                  	
							
							
							
							 for_line_part_update.append("\n<div class=\"table-header\" style=\"margin-top: 30px;\">Group Lines Details</div>"
										+"\n<div>"
										+"\n<% int n=0; %>"
										+"\n<input type=\"hidden\" id=\"rowcount1\" name=\"rowcount1\">"
										+"\n<input type=\"hidden\" id=\"delrow1\" name=\"delrow1\" value=\"\">"
										+"\n<table id=\"dynamic-table1\" class=\"table table-striped table-bordered table-hover\">"
										+"\n<thead>"
										+"\n<tr>");
								 
									//for loop for id is primary
							 
									  for(int i=0;i<line_id_primary.size();i++)
						            	  {
						                	String field_name4=line_id_primary.get(i).getField_name();
						          			String mapping4=line_id_primary.get(i).getMapping();
						          			String data_type4=line_id_primary.get(i).getData_type();	
										
						          			for_line_part_update.append ("\n<th>"
										                          +"\n<center>Select</center>"
										                       +"\n</th>");
						            	  }
									  
									//for loop for other field (like varchar field)	
									  
										  for(int i=0;i<line_varchar.size();i++)
						            	  {
						                	String field_name4=line_varchar.get(i).getField_name();
						          			String mapping4=line_varchar.get(i).getMapping();
						          			String data_type4=line_varchar.get(i).getData_type();
						          			
										           for_line_part_update.append("\n<th>"
										                                 +"\n<center>"+field_name4+"</center>"
										                                 +"\n</th>");
						            	    }	
										 

										    for_line_part_update.append("\n\n<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension.jsp\"%>\n\n</tr>");
										 
										for_line_part_update.append("\n</thead>"
										+"\n<tbody>"
			                                  //for update
										+"\n<% int j=1; %>"
										+"\n<c:forEach var=\""+line_table_name_lower+"_updt\" items=\"${linelist}\" varStatus=\"status\">"
			                            + "\n<tr>" 
										       +line_value_update
			                            +"<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_ext_update.jsp\"%></tr>"
			                            +"\n<%  j=j+1; %>"
										+"\n</c:forEach>"
										+"\n</tbody>"
										+"\n</table>"

										+"\n<input type=\"button\" class=\"btn btn-md btn-success\""
										+"\nstyle=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%;\""
										+"\nvalue=\"Add Row\" onclick=\"AddRow()\">"


										+"\n<input type=\"button\" class=\"btn btn-md btn-success\""
										+"\nstyle=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%; font-size: auto;\""
										+"\nvalue=\"Delete Row\" onclick=\"del()\">"
			                           +"\n</div>");	
							
							
							
							
							
							
							
							
							
							
							
							
				     //for loop for field id and not pri
				    
							for(int i=0;i<line_id_primary.size();i++)
			            	  {
			                	String field_name4=line_id_primary.get(i).getField_name();
			          			String mapping4=line_id_primary.get(i).getMapping();
			          			String data_type4=line_id_primary.get(i).getData_type();
			          			
			          			String lower_case=field_name4.toLowerCase();
							
			          			line_value.append("<td style=\"display:none\">'+($('#dynamic-table1 tr').length)+'"
			          				             +"<input type=\"hidden\" value=\"\" id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" name=\""+lower_case+"\" />"
			          					  
			          				              +"<td>"
			          				                 +"<center>"
			          				                    + "<input type=\"checkbox\" class=\"Deleterow\" id=\"chk'+$('#dynamic-table1 tr').length+'\" name=\"chk\" style=\"text-align:center;\"/>"
			          				                 +"</center>"
			          				            +"</td>");
			                  }
							
							
					
				     //for loop for other field values
				    
				    
							for(int i=0;i<line_varchar.size();i++)
			            	  {
								String field_name4=line_varchar.get(i).getField_name();
			          			String mapping4=line_varchar.get(i).getMapping();
			          			String data_type4=line_varchar.get(i).getData_type();
			          			String lower_case=field_name4.toLowerCase();
			          			System.out.println("field name-------"+field_name4); 
			          		    System.out.println("mapping-------"+mapping4);
			          		    System.out.println("data type-------"+data_type4);
			          		    
			          		    String mapping_lower=mapping4.toLowerCase();
			          		    
			          		    String mandatory=line_varchar.get(i).getMandatory();
						   	    String readonly=line_varchar.get(i).getReadonly();
						   	    String hidden=line_varchar.get(i).getHidden();
						   	    
						   	    //String mapping=rn_userlist.get(i).getMapping();
						   	    
						   	    String type_field=line_varchar.get(i).getType_field();
						   	    
						   	    
						   	     String dependent=line_varchar.get(i).getDependent();
						   	     String dependent_on=line_varchar.get(i).getDependent_on();
						   	     String dependent_sp=line_varchar.get(i).getDependent_sp();
						   	     String dependent_sp_param=line_varchar.get(i).getDependent_sp_param();
						   	     
						   	     String sequence=line_varchar.get(i).getSequence();
						   	     String seq_name=line_varchar.get(i).getSeq_name();  
						   	     String seq_sp=line_varchar.get(i).getSeq_sp();
						   	     String seq_sp_param=line_varchar.get(i).getSeq_sp_param();
						   	     
						   	       String validation_1=line_varchar.get(i).getValidation_1();
						   	       String val_type=line_varchar.get(i).getVal_type();
						   	       String val_sp=line_varchar.get(i).getVal_sp();
						   	       String val_sp_param=line_varchar.get(i).getVal_sp_param();
						   	       
						   	       
						   	       String default_1=line_varchar.get(i).getDefault_1();
						   	       String default_typename=line_varchar.get(i).getDefault_type();
						   	       String default_value=line_varchar.get(i).getDefault_value();
						   	       String default_sp=line_varchar.get(i).getDefault_sp();
						   	       String default_sp_param=line_varchar.get(i).getDefault_sp_param();
						   	       
						   	       String calculated_field=line_varchar.get(i).getCalculated_field();
						   	       String cal_sp=line_varchar.get(i).getCal_sp();
						   	       String cal_sp_param=line_varchar.get(i).getCal_sp_param();
						   	       
						   	       String add_to_grid=line_varchar.get(i).getAdd_to_grid();
						   	       
						   	       String sp_for_dropdown=line_varchar.get(i).getSp_for_dropdown();
						   	       String sp_for_autocomplete=line_varchar.get(i).getSp_for_autocomplete();
						   	       
						   	       String sp_name_for_dropdown=line_varchar.get(i).getSp_name_for_dropdown();
						   	       String sp_name_for_autocomplete=line_varchar.get(i).getSp_name_for_autocomplete();
							
						   	     if(type_field.equals("textfield"))
						   	     {  
						   	       
			          			     line_value.append("<td>"
			          				                    +"<input");
			          			     if(hidden.equals("Y"))
			          			     {
			          			        line_value.append("\ttype=\"hidden\"");
			          			     }else{
			          			    	line_value.append("\ttype=\"text\"");
			          			     }
			          			   
			          			   line_value.append("\tvalue=\"\" id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" name=\""+lower_case+"\" style=\"width:100%;\"");
			          			   
			          			   if(mandatory.equals("Y"))
			          			     {
			          			       line_value.append(" \trequired ");
			          			      }
			          			   
			          			   if(readonly.equals("Y"))
		          			       {
		          			         line_value.append(" \treadonly ");
		          			        }
			          			   
			          			    line_value.append(" />");
			          			    line_value.append("</td>");
			                     }
						   	     
						   	   if(type_field.equals("date"))
						   	     {  
						   	       
			          			     line_value.append("<td>"
			          			                          +"<div class=\"clearfix\">"
			          			                            +"<div class=\"input-group input-append date\" id=\"datePicker1"+i+"\" style=\"width:100%;\">"
			          				                           +"<input  type=\"text\" name=\""+mapping_lower+"\" id=\""+mapping_lower+"'+$('#dynamic-table1 tr').length+'\" placeholder=\"picup Date\" class=\"form-control\"   required/>"
			          				                              +"<span class=\"input-group-addon\">"
			          				                              +"<i class=\"fa fa-calendar bigger-110\"></i></span>"
			          				                           +"</div>"
			          				                          +"</div>"
			          		                               +"</td>");
			                     }
						   	   
						   	 if(type_field.equals("dropdown"))
					   	     {  
					   	       
		          			     line_value.append("<td>"
		          			                        + "<select  id=\""+mapping_lower+"'+$('#dynamic-table1 tr').length+'\" name=\""+mapping_lower+"\" class=\"ChangeDropdown\" style=\"width:100%;\" required>"
		          		                                +"<option >--select--</option>"
		          		                              +"</select>"
		          		                            +"</td>");
		          			     
		          			     
		          			     
		          			   dropdown_script_latest_for_line.append("\n$.ajax({"
										+"\nurl: '${pageContext.request.contextPath}/"+mapping_lower+"_list_line',"
										+"\nsuccess: function(data)"
										+"\n{"
										+"\nconsole.log(data);"
										+"\nvar select = $('#"+mapping_lower+"'+count);"
										+"\n$('<option>').text('select').val(0).appendTo(select);"
										+"\nselect.find('option').remove();"
										+"\n$.each(data, function(index, value) {"
										+"\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
										+"\n});"
										+"\n}"
										+"\n});"); 
		          			     
		          			     
		          			     
		          			     
		          			     
		          			     
		                     }
						   	 
						   	 
						   	 if(type_field.equals("autocomplete"))
					   	     {  
					   	       
						        line_value.append("<td>"
			      				                    +"<input");
			      			     if(hidden.equals("Y"))
			      			     {
			      			        line_value.append("\ttype=\"hidden\"");
			      			     }else{
			      			    	line_value.append("\ttype=\"text\"");
			      			     }
			      			   
			      			   line_value.append("\tvalue=\"\" id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" name=\""+lower_case+"\" style=\"width:100%;\"");
			      			   
			      			   if(mandatory.equals("Y"))
			      			     {
			      			       line_value.append(" \trequired ");
			      			      }
			      			   
			      			   if(readonly.equals("Y"))
			  			       {
			  			         line_value.append(" \treadonly ");
			  			        }
			      			   
			      			    line_value.append(" />");
			      			    line_value.append("</td>");
			      			    
			      			    
			      			  dropdown_script_latest_for_line.append("var options = {"
								      				+"url: '${pageContext.request.contextPath}/"+mapping_lower+"_line_list',"
								      				+"getValue: \"drop_value\","
								      				+"list: {"	
								      				+"match: {"
								      				+"enabled: true"
								      				+"}},"
								      				+"theme: \"square\""
								      				+"};"
								      				+"$(\"#"+lower_case+"\").easyAutocomplete(options);");
								      			    
								      			    
			      			    
			      			    
		                     }
						   	 
						   	 
						   	 if(type_field.equals("togglebutton"))
					   	     {  
					   	       
		          			     line_value.append("<td>"
		          			                         +"<center>"
		          		                                 +"<div class=\"clearfix\">"
		          			                              +" <input name=\""+lower_case+"\" id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" class=\"ace ace-switch ace-switch-6\" type=\"checkbox\" onclick=\"CheckUserStatusGrid(id)\"/>"
		          				                             +"<span class=\"lbl\"></span>"
		          			                            +"</div>"
		          		                                 +"</center>"
		          		                               +"</td>");
		                     }
						   	 
						   	 if(type_field.equals("checkbox"))
					   	     {  
					   	       
		          			     line_value.append("<td>"
		          			                        +"<center>"
		          			   	                      +"<input type=\"checkbox\"  id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" name=\""+lower_case+"\" style=\"text-align:center;\"/>"
		          			   	                    +"</center>"
		          			                      +"</td>");
		                     }
						   	 
							
			            	  }
						
						}
						
								
							
//-----------------------------------------------------line readonly--------------------------------------------------------------------------							
					if(line_table_name !=null && !line_table_name.isEmpty())
					{	
						
						 String line_table_name_lower=line_table_name.toLowerCase();
					     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
					     String line_table_name_upper=line_table_name.toUpperCase();
					     
							for(int i=0;i<line_id_primary.size();i++)
			            	  {
			                	String field_name4=line_id_primary.get(i).getField_name();
			          			String mapping4=line_id_primary.get(i).getMapping();
			          			String data_type4=line_id_primary.get(i).getData_type();
			          			
			          			String lower_case=field_name4.toLowerCase();
							     
			          			line_value_readonly.append( "<td style=\"display:none\">'+($('#dynamic-table1 tr').length)+'"
			          				             +"<input type=\"hidden\" value=\"${"+line_table_name_lower+"_updt."+lower_case+"}\" id=\""+lower_case+"\" name=\""+lower_case+"\"  <%=j%>/>"
			          					  
			          				              +"<td>"
			          				                 +"<center>"
			          				                    + "<input type=\"checkbox\" class=\"Deleterow\" id=\"chk\" name=\"chk\" style=\"text-align:center;\"/>"
			          				                 +"</center>"
			          				            +"</td>");
			                  }
							
							
					
				     //for loop for other field values
				    
				    
							for(int i=0;i<line_varchar.size();i++)
			            	  {
							String field_name4=line_varchar.get(i).getField_name();
		          			String mapping4=line_varchar.get(i).getMapping();
		          			String data_type4=line_varchar.get(i).getData_type();
		          			String lower_case=field_name4.toLowerCase();
		          			System.out.println("field name-------"+field_name4); 
		          		    System.out.println("mapping-------"+mapping4);
		          		    System.out.println("data type-------"+data_type4);
		          		    
		          		    
		          		    String mandatory=line_varchar.get(i).getMandatory();
					   	    String readonly=line_varchar.get(i).getReadonly();
					   	    String hidden=line_varchar.get(i).getHidden();
					   	    
					   	    //String mapping=rn_userlist.get(i).getMapping();
					   	    
					   	    String type_field=line_varchar.get(i).getType_field();
					   	    
					   	    
					   	     String dependent=line_varchar.get(i).getDependent();
					   	     String dependent_on=line_varchar.get(i).getDependent_on();
					   	     String dependent_sp=line_varchar.get(i).getDependent_sp();
					   	     String dependent_sp_param=line_varchar.get(i).getDependent_sp_param();
					   	     
					   	     String sequence=line_varchar.get(i).getSequence();
					   	     String seq_name=line_varchar.get(i).getSeq_name();  
					   	     String seq_sp=line_varchar.get(i).getSeq_sp();
					   	     String seq_sp_param=line_varchar.get(i).getSeq_sp_param();
					   	     
					   	       String validation_1=line_varchar.get(i).getValidation_1();
					   	       String val_type=line_varchar.get(i).getVal_type();
					   	       String val_sp=line_varchar.get(i).getVal_sp();
					   	       String val_sp_param=line_varchar.get(i).getVal_sp_param();
					   	       
					   	       
					   	       String default_1=line_varchar.get(i).getDefault_1();
					   	       String default_typename=line_varchar.get(i).getDefault_type();
					   	       String default_value=line_varchar.get(i).getDefault_value();
					   	       String default_sp=line_varchar.get(i).getDefault_sp();
					   	       String default_sp_param=line_varchar.get(i).getDefault_sp_param();
					   	       
					   	       String calculated_field=line_varchar.get(i).getCalculated_field();
					   	       String cal_sp=line_varchar.get(i).getCal_sp();
					   	       String cal_sp_param=line_varchar.get(i).getCal_sp_param();
					   	       
					   	       String add_to_grid=line_varchar.get(i).getAdd_to_grid();
					   	       
					   	       String sp_for_dropdown=line_varchar.get(i).getSp_for_dropdown();
					   	       String sp_for_autocomplete=line_varchar.get(i).getSp_for_autocomplete();
					   	       
					   	       String sp_name_for_dropdown=line_varchar.get(i).getSp_name_for_dropdown();
					   	       String sp_name_for_autocomplete=line_varchar.get(i).getSp_name_for_autocomplete();
						
					   	     if(type_field.equals("textfield"))
					   	     {  
					   	       
					   	    	line_value_readonly.append("<td>"
		          				                    +"<input");
		          			     if(hidden.equals("Y"))
		          			     {
		          			    	line_value_readonly.append("\ttype=\"hidden\"");
		          			     }else{
		          			    	line_value_readonly.append("\ttype=\"text\"");
		          			     }
		          			   
		          			   line_value_readonly.append("\tvalue=\"\" id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" name=\""+lower_case+"\" style=\"width:100%;\"");
		          			   
		          			   if(mandatory.equals("Y"))
		          			     {
		          				 line_value_readonly.append(" \trequired ");
		          			      }
		          			   
		          			   
		          				 line_value_readonly.append(" \treadonly ");
	          			        
		          			   
		          			 line_value_readonly.append(" />");
		          			line_value_readonly.append("</td>");
		                     }
					   	     
					   	   if(type_field.equals("date"))
					   	     {  
					   	       
					   		line_value_readonly.append("<td>"
		          			                          +"<div class=\"clearfix\">"
		          			                            +"<div class=\"input-group input-append date\" id=\"datePicker\" style=\"width:100%;\">"
		          				                           +"<input  type=\"text\" name=\""+mapping4+"\" id=\""+mapping4+"'+$('#dynamic-table1 tr').length+'\" placeholder=\"picup Date\" class=\"form-control\"   readonly/>"
		          				                              +"<span class=\"input-group-addon\">"
		          				                              +"<i class=\"fa fa-calendar bigger-110\"></i></span>"
		          				                           +"</div>"
		          				                          +"</div>"
		          		                               +"</td>");
		                     }
					   	   
					   	 if(type_field.equals("dropdown"))
				   	     {  
				   	       
					   		line_value_readonly.append("<td>"
	          			                        + "<select  id=\""+mapping4+"'+$('#dynamic-table1 tr').length+'\" name=\""+mapping4+"\" class=\"ChangeDropdown\" style=\"width:100%;\" readonly>"
	          		                                +"<option selected disabled value=\"\"></option>"
	          		                              +"</select>"
	          		                            +"</td>");
	                     }
					   	 
					   	 
					   	 if(type_field.equals("autocomplete"))
				   	     {  
				   	       
					   		line_value_readonly.append("<td>"
		      				                    +"<input");
		      			     if(hidden.equals("Y"))
		      			     {
		      			    	line_value_readonly.append("\ttype=\"hidden\"");
		      			     }else{
		      			    	line_value_readonly.append("\ttype=\"text\"");
		      			     }
		      			   
		      			   line_value_readonly.append("\tvalue=\"\" id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" name=\""+lower_case+"\" style=\"width:100%;\"");
		      			   
		      			   if(mandatory.equals("Y"))
		      			     {
		      				 line_value_readonly.append(" \trequired ");
		      			      }
		      			   
		      			   
		      				 line_value_readonly.append(" \treadonly ");
		  			        
		      			   
		      			 line_value_readonly.append(" />");
		      			line_value_readonly.append("</td>");
	                     }
					   	 
					   	 
					   	 if(type_field.equals("togglebutton"))
				   	     {  
				   	       
					   		line_value_readonly.append("<td>"
	          			                         +"<center>"
	          		                                 +"<div class=\"clearfix\">"
	          			                              +" <input name=\""+lower_case+"\" id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" class=\"ace ace-switch ace-switch-6\" type=\"checkbox\" onclick=\"CheckUserStatusGrid(id)\" readonly/>"
	          				                             +"<span class=\"lbl\"></span>"
	          			                            +"</div>"
	          		                                 +"</center>"
	          		                               +"</td>");
	                     }
					   	 
					   	 if(type_field.equals("checkbox"))
				   	     {  
				   	       
					   		line_value_readonly.append("<td>"
	          			                        +"<center>"
	          			   	                      +"<input type=\"checkbox\"  id=\""+lower_case+"'+$('#dynamic-table1 tr').length+'\" name=\""+lower_case+"\" style=\"text-align:center;\" readonly/ >"
	          			   	                    +"</center>"
	          			                      +"</td>");
	                     }
			                  }	
							
							
						
							 for_line_part_readonly.append("\n<div class=\"table-header\" style=\"margin-top: 30px;\">Group Lines Details</div>"
										+"\n<div>"
										+"\n<% int n=0; %>"
										+"\n<input type=\"hidden\" id=\"rowcount1\" name=\"rowcount1\">"
										+"\n<input type=\"hidden\" id=\"delrow1\" name=\"delrow1\" value=\"\">"
										+"\n<table id=\"dynamic-table1\" class=\"table table-striped table-bordered table-hover\">"
										+"\n<thead>"
										+"\n<tr>");
								 
									//for loop for id is primary
							 
									  for(int i=0;i<line_id_primary.size();i++)
						            	  {
						                	String field_name4=line_id_primary.get(i).getField_name();
						          			String mapping4=line_id_primary.get(i).getMapping();
						          			String data_type4=line_id_primary.get(i).getData_type();	
										
						          			for_line_part_readonly.append ("\n<th>"
										                          +"\n<center>Select</center>"
										                       +"\n</th>");
						            	  }
									  
									//for loop for other field (like varchar field)	
									  
										  for(int i=0;i<line_varchar.size();i++)
						            	  {
						                	String field_name4=line_varchar.get(i).getField_name();
						          			String mapping4=line_varchar.get(i).getMapping();
						          			String data_type4=line_varchar.get(i).getData_type();
						          			
						          			for_line_part_readonly.append("\n<th>"
										                                 +"\n<center>"+field_name4+"</center>"
										                                 +"\n</th>");
						            	    }	
										 
										 
										  for_line_part_readonly.append("\n</tr>"
									    +"\n</thead>"
										+"\n<tbody>"
			                                  //for update
										+"\n<% int j=1; %>"
										+"\n<c:forEach var=\""+line_table_name_lower+"_updt\" items=\"${linelist}\" varStatus=\"status\">"
			                            + "\n<tr>" 
										       +line_value_readonly
			                            +"\n\n<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_ext_readonly.jsp\"%>\n</tr>"
			                            +"\n<%  j=j+1; %>"
										+"\n</c:forEach>"
										+"\n</tbody>"
										+"\n</table>"

										+"\n<input type=\"button\" class=\"btn btn-md btn-success\""
										+"\nstyle=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%;\""
										+"\nvalue=\"Add Row\" >"


										+"\n<input type=\"button\" class=\"btn btn-md btn-success\""
										+"\nstyle=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%; font-size: auto;\""
										+"\nvalue=\"Delete Row\" >"
			                           +"\n</div>");			
							
							
					}	
					
					
					
				    //script for line entry part			
				    line_script.append("\n<script language=\"javascript\">"
                            +"\nfunction AddRow()  "     												
                            +"\n{$('#dynamic-table1 tr').last().after('<tr>"+line_value);
				    
				           if(line_table_name !=null && !line_table_name.isEmpty())
					       {
						     String line_table_name_lower=line_table_name.toLowerCase();
						     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
						     String line_table_name_upper=line_table_name.toUpperCase();
						     
                              line_script.append("<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension2.jsp\"%>");
					        }  
				           
                            line_script.append( "</tr>');");
                            		
                            for(int i=0;i<line_varchar.size();i++)
                            {	
                            	String type_field=line_varchar.get(i).getType_field();
                            	if(type_field.equals("date"))
                            	{
                                  line_script.append("\n$('#datePicker1"+i+"').datepicker({format : 'dd-mm-yyyy'}).on('changeDate', function(e) {$('#eventForm').formValidation('revalidateField', 'date');});");
                            	}
                            }	
                            line_script.append("\n$('#rowcount1').val($('#dynamic-table1 tr').length-1);"
                              +"\nvar count = $('#rowcount1').val();"
                              +dropdown_script_latest_for_line
                              +"\n$('.Deleterow').click(function() { "	
                              +"\nvar index = $('.Deleterow').index(this)+1;"
                              +"\n$('#delrow1').val(index);"
                              +"\n});"
                              +"\n}"
                              +"\n</script>"
                              +"\n<script>"
                              +"\nfunction del(){"
                              +"\nvar index=$('#delrow1').val();"
                              +"\nif(index!= \"\")"
                              +"\n{"	
                              +"\ndocument.getElementById(\"dynamic-table1\").deleteRow(index);"
                              +"\n$('#delrow1').val(\"\");"
                              +"\n}"	
                              +"\n}"
                              +"\n</script>");
				    
				    
				    //script for line update part
				    line_script_update.append("\n<script language=\"javascript\">"
                            +"\nfunction AddRow()  "     												
                            +"\n{$('#dynamic-table1 tr').last().after('<tr>"+line_value);
				    if(line_table_name !=null && !line_table_name.isEmpty())
				       {
					     String line_table_name_lower=line_table_name.toLowerCase();
					     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
					     String line_table_name_upper=line_table_name.toUpperCase();
                            line_script_update.append("<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension2.jsp\"%>");
				       } 
				    		line_script_update.append( "</tr>');"
                                +"\n$('#rowcount1').val($('#dynamic-table1 tr').length-1);"
                              +"\nvar count = $('#rowcount1').val();"
                               +"\n$.ajax({"
                               +"\nurl: '${pageContext.request.contextPath}/loadreports',"
                               +"\ndata: ({name : 'name'}),"
                               +"\nsuccess: function(data) {"
                               +"\nvar select = $('#name'+count);"
                               +"\nselect.find('option').remove();"
                              +"\n$.each(data, function(index, value) {"
                              +"\n$('<option>').val(value).text(value).appendTo(select);"
                               +"\n});"
                                +"\n}"
                               +"\n});"

                              +"\n$('.Deleterow').click(function() { "	
                              +"\nvar index = $('.Deleterow').index(this)+1;"
                              +"\n$('#delrow1').val(index);"
                              +"\n});"
                               +"\n}"
                              +"\n</script>"

                              +"\n<script>"
                              +"\nfunction del(){"
                              +"\nvar index=$('#delrow1').val();"
                              +"\nif(index!= \"\")"
                              +"\n{"	
                               +"\ndocument.getElementById(\"dynamic-table1\").deleteRow(index);"
                              +"\n$('#delrow1').val(\"\");"
                               +"\n}"	
                               +"\n}"
                               +"\n</script>");
				    
				    
				    
				    List<Rn_Ext_Fields> ext_user=rn_user_registration_dao.ext_userlist2(f_code);
				    
				   
				    
				    
				    
				    
				              strContent.append(importsection
				    		                    +" \n"
												+ headsection 
												+ "\n<body>\n<div class=\"main-container\" id=\"main-container\">"
												+ "\n<div class=\"main-content\">" 
												+ "\n<div class=\"main-content-inner\">"
												+ "\n<div class=\"breadcrumbs\" id=\"breadcrumbs\">" 
												+ "\n<ul class=\"breadcrumb\">"
												+ "\n<li><i class=\"ace-icon fa fa-home home-icon\"></i> <a href=\"#\">Home</a>" 
												+ "\n</li>"
												+ "\n<li><a href=\"#\">ManageUsers</a></li>" 
												+ "\n</ul>" 
												+ "\n</div>"
								
												+ "\n<div class=\"page-content\">" 
												+ "\n<div class=\"page-header\">" 
												+ "\n<h1>User Registration"
												+ "<div style=\"float: right; padding-right: 5%;\">"
												+ "<a href=\"rn_form_builder_extension\"><i class=\"fa fa-paper-plane-o fa-1x\" aria-hidden=\"true\" title=\"help\"></i></a>"
								
												+ "</div></h1>" 
												+ "\n</div>"
								
												+ "\n<div class=\"row\">" 
												+ "\n<div class=\"col-xs-12\">"
												+ "\n<div class=\"widget-box\" style=\"width: 90%; margin-left: 5%;\">"
												+ "\n<div class=\"widget-header widget-header-blue widget-header-flat\">"
												+ "\n<h4 class=\"widget-title lighter\">User Profile</h4>" + "\n</div>"
												+ "\n<div class=\"widget-body\">" 
												+ "\n<div class=\"widget-main\">"
												+ "\n<div id=\"fuelux-wizard-container\">" 
												+ "\n<div class=\"step-content pos-rel\">"
												+ "\n<div class=\"step-pane active\" data-step=\"1\">"
								                + "<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\">" + "User Credentials "
												+ "</div>"
								                +  form 
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>"
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>" 
												+ "\n</div>"
												+ "<script src=\"<c:url value='/resources/assets/js/bootstrap-datepicker.min.js'/>\" type=\"text/javascript\">"
												+ "\n</script>" 
												+ "<script>" 
												+ date_script 
												+ dropdown_script_latest 
												+togglescript
												+ "</script>"
												+ autocomplete_script_latest 
												+ line_script 
												+ dropdown_script 
												+ dependent_dropdown 
												+ autocomplete_script
												+ "\n</body>\n</html>");
				    			   
     
     //---------------------------------grid view code-------------------------------------------------
				    
				    for(int i=0;i<id_list.size();i++)
					{
			    	   String field_name=id_list.get(i).getField_name();
			    	   String field_name_lower=field_name.toLowerCase();
			    	   table_field_for_id.append("\n<th class=\"center\">"+field_name+"</th>");
			    	 
			    	 //table_field_value_for_id.append("\n<td class=\"center\"><a href=\""+table_name_lower+"_update?"+field_name+"=${"+table_name_lower+"."+field_name+"}\""</td>");
			    	 
			    	 table_field_value_for_id.append("<td class=\"center\">"
			    		+"<a href=\""+table_name_lower+"_readonly?"+field_name_lower+"=${"+table_name_lower+"."+field_name_lower+"}\">"
			    		   +"<i class=\"fa fa-eye green\" aria-hidden=\"true\"></i>/"
			    		 +"<a href=\""+table_name_lower+"_update?"+field_name_lower+"=${"+table_name_lower+"."+field_name_lower+"}\">"
			    		   +"<i class=\"fa fa-edit red\" aria-hidden=\"true\"></i>/"
			    		 +"<a href=\"rn_view_new_reports?user_id=${rn_userlist.id}\">"
			    		   +"<i class=\"fa fa-graduation-cap\" aria-hidden=\"true\"></i>"
			    		 +"</a>"
			    	+"</td>");
			    	 
			    	 } 
				    
				    for(int i=0;i<id_notprimary.size();i++)
				       {
				    	String field_name=id_notprimary.get(i).getField_name();
				    	String data_type=id_notprimary.get(i).getData_type();
				   	    String lower_case=field_name.toLowerCase();
				   	    String only_upper=field_name.toUpperCase();
				   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
				   	    if(data_type.equals("int"))
				   	    {
				   	    	
				   	    	id_not_pri_field_for_grid.append("\n<th class=\"center\">"+field_name+"</th>");	
				   	    	id_not_pri_field_for_grid_value.append("\n<td class=\"center\">${"+table_name_lower+"."+lower_case+"}</td>");
				   	    }
				   	    
				       
				      }
				    
				    
				    
				    for(int i=0;i<datetime_list.size();i++)
				    {
				 	    String field_name=datetime_list.get(i).getField_name();
				 	    String data_type=datetime_list.get(i).getData_type();
				 	    
					    String lower_case=field_name.toLowerCase();
					    String only_upper=field_name.toUpperCase();
					    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
					    
					    if(data_type.equals("datetime"))
				   	    {
					    	
					    	date_field_for_grid.append("\n<th class=\"center\">"+field_name+"</th>");	
				   	    	date_field_for_grid_value.append("\n<td class=\"center\">${"+table_name_lower+"."+lower_case+"}</td>");
					    	
				   	    }
					    
				    
				    }
				    
				    
				    for(int i=0;i<rn_userlist.size();i++)
					{
			    	 String field_name=rn_userlist.get(i).getField_name();
			    	 String field_name_lower=field_name.toLowerCase();
			    	 
			    	 table_field.append("\n<th class=\"center\">"+field_name+"</th>");
			    	 
			    	 table_field_value.append("\n<td class=\"center\">${"+table_name_lower+"."+field_name_lower+"}</td>");
			    	 
					}
                       table_grid_view.append(" \n<%@page contentType=\"text/html\" pageEncoding=\"UTF-8\"%>"
                                                +"\n<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
                                                +"\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\" %>"
                                                +"\n<%@ taglib prefix=\"form\" uri=\"http://www.springframework.org/tags/form\"%>"
+"\n<html>"                                          
  + "\n<head>"
		+"\n<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />"
		+"\n<meta charset=\"utf-8\"/>"
		+"\n<title>Realnet Oil Engines Ltd</title>"
        +"\n<meta name=\"description\" content=\"Static &amp; Dynamic Tables\" />"
		+"\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\"/>"
        +"\n<!-- bootstrap & fontawesome -->"
		+"\n<link rel=\"stylesheet\" href=\"resources/assets/css/bootstrap.min.css\" />"
		+"\n<link rel=\"stylesheet\" href=\"resources/assets/font-awesome/4.2.0/css/font-awesome.min.css\" />"
        +"\n<!-- page specific plugin styles -->"
        +"\n<!-- text fonts -->"
		+"\n<link rel=\"stylesheet\" href=\"resources/assets/fonts/fonts.googleapis.com.css\"/>"
        +"\n<!-- ace styles -->"
		+"\n<link rel=\"stylesheet\" href=\"resources/assets/css/ace.min.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style\" />"
  +"\n</head>"
  +"\n<body class=\"no-skin\">"
     +"\n<div class=\"main-container\" id=\"main-container\">"
		+"\n<div class=\"main-content\">"
				+"\n<div class=\"main-content-inner\">"
					+"\n<div class=\"breadcrumbs\" id=\"breadcrumbs\">"
						+"\n<script type=\"text/javascript\">"
							+"\ntry{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}"
						+"\n</script>"
                        +"\n<ul class=\"breadcrumb\">"
							+"\n<li>"
								+"\n<i class=\"ace-icon fa fa-home home-icon\"></i>"
								+"\n<a href=\"#\">Home</a>"
							+"\n</li>"

							+"\n<li class=\"active\"> User Details</li>"
						+"\n</ul>"
                 +" \n </div>"
                     +"\n<div class=\"page-content\">"
                       +"\n <div class=\"page-header\">"
							+"\n<h1>"
								+"\nUser Details"
								+"\n<div style=\"float: right; padding-right: 3%;\">"
 								   +"\n<a href=\""+table_name_lower+"_entryform\"> <i class=\"fa fa-plus fa-1g\" aria-hidden=\"true\" title=\"Add New Report\"></i></a>"
								+"\n</div>"
							+"\n</h1>"
						+"\n</div><!-- /.page-header -->"

						+"\n<div class=\"row\">"
							+"\n<div class=\"col-xs-12\">"
								+"\n<div class=\"row\">"
									+"\n<div class=\"col-xs-12\">"
                                        +"\n<div class=\"clearfix\">"
											+"\n<div class=\"pull-right tableTools-container\"></div>"
										+"\n</div>"
									     +"\n<div class=\"table-header\">"
											+"\nUser Details"
										+"\n</div>"
                                           +"\n<div>"
										
											
	+"\n<table   class=\"table table-striped table-bordered table-hover\" id=\"table1\"  cellspacing=\"0\" width=\"1500px\" style=\"width:100%; margin: 0 auto;\">"
			+"\n<thead>"
	        	+"\n<tr>"
			      +table_field_for_id
			      +id_not_pri_field_for_grid
			      +date_field_for_grid
			      +table_field
	        	 +"\n<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_add_grid.jsp\"%>\n</tr>"
	        + "\n</thead>"
	        + "\n<tbody>"
			+"\n<c:forEach var=\""+table_name_lower+"\" items=\"${"+table_name_lower+"}\" varStatus=\"status\">"
	        	+"\n<tr>	"
			        +table_field_value_for_id
			        +id_not_pri_field_for_grid_value
			        +date_field_for_grid_value
 					+table_field_value
 					
				+"<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_add_grid2.jsp\"%>\n</tr>"
			  +"\n</c:forEach>"	        	
		    +"\n</tbody>"
		    + " \n</table>"
												
										+"\n</div>"
									+"\n</div>"
								+"\n</div>"
                             +"\n</div>"
						+"\n</div>"
					+"\n</div>"
				+"\n</div>"
		  +"\n</div>"
			

			+"\n<a href=\"#\" id=\"btn-scroll-up\" class=\"btn-scroll-up btn btn-sm btn-inverse\">"
				+"\n<i class=\"ace-icon fa fa-angle-double-up icon-only bigger-110\"></i>"
			+"\n</a>"
		+"\n</div>"
       + "\n<script src=\"resources/assets/js/jquery.2.1.1.min.js\"></script>"
       
		+"\n<script src=\"resources/assets/js/bootstrap.min.js\"></script>"
        +"\n<script src=\"resources/assets/js/jquery.dataTables.min.js\"></script>"
		+"\n<script src=\"resources/assets/js/jquery.dataTables.bootstrap.min.js\"></script>"
		+"\n<script src=\"resources/assets/js/dataTables.tableTools.min.js\"></script>"
		+"\n<script src=\"resources/assets/js/dataTables.colVis.min.js\"></script>"
		+"\n<script src=\"resources/assets/js/ace-elements.min.js\"></script>"
		+"\n<script src=\"resources/assets/js/ace.min.js\"></script>"
		
		
		
		+"\n<script type=\"text/javascript\">"
		
		+"\n$(document).ready(function() "
				+"\n{"
			+"\n$('#table1').DataTable( {"
			+"\n\"scrollX\": true"
			
			+"\n} );"
			+"\n} );"
			+"\njQuery(function($)"
		  +"\n{"
				+"\nvar oTable1 = "
				+"\n$('#dynamic-table')"
				+"\n.dataTable( {"
					+"\nbAutoWidth: false,"
					+"\n\"aoColumns\": ["
					  +"\n{ \"bSortable\": false },"
					  +"\n null, null,null, null, null,"
					  +"\n{ \"bSortable\": false }"
					+"\n],"
					+"\n\"aaSorting\": [],"
			
				
			   + "\n} );"
			+"\n})"
		+"\n</script>"
	+"\n</body>"
+"\n</html>");
                       
   //------------------------------------ jsp readonly---------------------------------------------------------     
      
                       importsectionreadonly.append("\n<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>"
                               
                                             +"\n<%@ page import=\"java.util.ArrayList\"%>"
                                        + "\n<%@ page import=\"java.util.Date\"%>"
                                         +"\n<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>"
                                        + "\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\"%>"
                                         +"\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>");
                                         		
				    headsectionreadonly.append("\n<html lang=\"en\">\n<head>" 
				    +"\n<meta http-equiv=\"X-UA-Compatible\"  content=\"IE=edge,chrome=1\">"
				    +"\n<meta charset=\"utf-8\" />"
				    +"\n<title>Realnet Oil Engines Ltd</title>"
				    +"\n<meta name=\"description\" content=\"Common form elements and layouts\" />"
				    +"\n<meta name=\"viewport\""
	                +"\ncontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />"
				    +"\n<!-- bootstrap & fontawesome -->"
                    +"\n<link rel=\"stylesheet\""
	                +"\n href=\"<c:url value='/resources/assets/css/bootstrap.min.css'/>\" />"
                    +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css'/>\" />"
	                +"\n<!-- page specific plugin styles -->"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/jquery-ui.custom.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/chosen.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/datepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/bootstrap-timepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/daterangepicker.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/bootstrap-datetimepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/colorpicker.min.css' />\" />"
	                +"\n<!-- text fonts -->"
				    +"\n<link rel=\"stylesheet\""
				    +"\n\nhref=\"<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />\" />"
                    +"\n<!-- ace styles -->"
				    +"\n<link rel=\"stylesheet\""
				    +"\nhref=\"<c:url value='/resources/assets/css/ace.min.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style' />\" />"
				    +"\n<!-- inline styles related to this page -->"

				    +"\n<!-- ace settings handler -->"
				    +"\n<script src=\"<c:url value='/resources/assets/js/ace-extra.min.js'/>\""
				    	+"\ntype=\"text/javascript\"></script>"


				    		 +"\n<script>"
				    		    +"\nsubmitForms = function()"
				    		     +"\n{"
				                   +"\ndocument.forms[\"userdetails1\"].submit();"
				                   +"\ndocument.forms[\"userdetails2\"].submit();"
				                 +"}"
				            +"\n</script> "
				    			
				    +" \n</head>");
     
     
     
				    form_readonly.append("\n<form action=\"submit_form\" class=\"form-horizontal\" id=\"Regi\" method=\"Post\">"
				    		+ "\n<input type=\"hidden\" name=\"test\" id=\"test\" value=\"\" />"
				    		+ " \n<table>"
				    	  + "\n<c:forEach var=\""+table_name_lower+"_updt\" items=\"${"+table_name_lower+"_update}\" varStatus=\"status\">"

							+ "\n<tr>");
				    
				    
				    for(int i=0;i<id_notprimary.size();i++)
	            	  {
				    	
				    	String field_name=id_notprimary.get(i).getField_name();
				    	String data_type=id_notprimary.get(i).getData_type();
				   	    String lower_case=field_name.toLowerCase();
				   	    String only_upper=field_name.toUpperCase();
				   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
				    	
				            if(data_type.equals("int"))
				            {
				            	form_readonly.append("\n<td>"
		          		    	+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
		          		    	+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
		          		    	 +first_upper
		          		    	 +"\n<i class=\"menu-icon fa red\"> *</i>"
		          		    	+"\n</label>"
		          		    	+"\n<div class=\"col-xs-12 col-sm-9\">"
		          		    	+"\n<div class=\"clearfix\">"
		          		    	+"\n<input   value=\"${"+table_name_lower+"_updt."+lower_case+"}\"type=\"text\" name=\""+lower_case+"\"id=\""+lower_case+"\"class=\"col-xs-12 col-sm-4\" readonly/>"
		          		    	+"\n</div>"
		          		    	+"\n</div>"
		          		    	+"\n</div>"
		          		    	+"\n</td>");
				            }
				    	
				    	
	            	  }
				    form_readonly.append("\n</tr>");
				    
				    form_readonly.append("\n<tr>");
				    
				    for(int i=0;i<datetime_list.size();i++)
	            	  {
				    	
				    	String field_name=datetime_list.get(i).getField_name();
				    	String data_type=datetime_list.get(i).getData_type();
				   	    String lower_case=field_name.toLowerCase();
				   	    String only_upper=field_name.toUpperCase();
				   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
				    	
				   	    
				    	
				   	    String mandatory=datetime_list.get(i).getMandatory();
				   	    String readonly=datetime_list.get(i).getReadonly();
				   	    String hidden=datetime_list.get(i).getHidden();
				   	    
				   	    String mapping=datetime_list.get(i).getMapping();
				   	    String mapping_lower=mapping.toLowerCase();
				   	    
				   	    
				   	     String dependent=datetime_list.get(i).getDependent();
				   	     String dependent_on=datetime_list.get(i).getDependent_on();
				   	     String dependent_sp=datetime_list.get(i).getDependent_sp();
				   	     String dependent_sp_param=datetime_list.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=datetime_list.get(i).getSequence();
				   	     String seq_name=datetime_list.get(i).getSeq_name();  
				   	     String seq_sp=datetime_list.get(i).getSeq_sp();
				   	     String seq_sp_param=datetime_list.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=datetime_list.get(i).getValidation_1();
				   	       String val_type=datetime_list.get(i).getVal_type();
				   	       String val_sp=datetime_list.get(i).getVal_sp();
				   	       String val_sp_param=datetime_list.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=datetime_list.get(i).getDefault_1();
				   	       String default_typename=datetime_list.get(i).getDefault_type();
				   	       String default_value=datetime_list.get(i).getDefault_value();
				   	       String default_sp=datetime_list.get(i).getDefault_sp();
				   	       String default_sp_param=datetime_list.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=datetime_list.get(i).getCalculated_field();
				   	       String cal_sp=datetime_list.get(i).getCal_sp();
				   	       String cal_sp_param=datetime_list.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=datetime_list.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=datetime_list.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=datetime_list.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=datetime_list.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=datetime_list.get(i).getSp_name_for_autocomplete();
	
	                       System.out.println("-----value of mantadoryr laest-----"+mandatory);
				   	    
	                       if(data_type.equals("datetime"))
	                       {
	                    	   
	   				    	System.out.println("---under if condition loop latest------");
	
	   				    	form_readonly.append("\n<td>"
										+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											   +"\n "+field_name);
	                              
								     System.out.println("-------------mandatory for testing=------------------"+mandatory);
	
											  if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
											  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											
	
											  form_readonly.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">"
												+"<div class=\"input-group input-append date\" id=\"datePicker\">");
											
											
											  form_readonly.append("\n<input" );
												
													
												   form_readonly.append("  type=\"text\" ");
													
													form_readonly.append( "name="+mapping_lower+"  value=\"${"+table_name_lower+"_updt."+mapping_lower+"}\"  id="+mapping_lower+" placeholder=\"picup Date\" class=\"form-control\"");
														
											
											form_readonly.append("  readonly");
											
											form_readonly.append("/>\n"
															+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
															+"\n</span>"
															+"\n</div>"
															+ "\n</div>"
											+"\n</div>"
										+"\n</div>"
										+"\n</td>");
	                       }
				    	
				    	
	            	  }
				    
				    
				    form_readonly.append("\n</tr>");
				    
				    
				    
				    
				    form_readonly.append("\n<tr>");
				    
				    
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
	                	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
	          		    if(i<=2)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("  readonly");
												
												form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	if(type_field.equals("date"))
	                         {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">"
													+"<div class=\"input-group input-append date\" id=\"datePicker\">");
												
												
												  form_readonly.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
														}
														
														form_readonly.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												
												form_readonly.append("  readonly");
												
												form_readonly.append("/>\n"
																+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																+"\n</span>"
																+"\n</div>"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                         }
         		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" readonly");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_readonly.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                            +"\n<option >---select---</option>"
                                                           +"\n</select>");
												}else{	
													
						
													form_readonly.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_readonly.append( "\t"+mapping4);
													form_readonly.append( "\" readonly>"
                                              +"\n<option >---select---</option>"
                                              +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("readonly");
												}
												form_readonly.append(">${drop_value}</option>\n"
                                              +"</select>\n");
											}
												form_readonly.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  type=\"checkbox\" ");
														}
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
												
												
												
												form_readonly.append("readonly />\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														
															form_readonly.append("  type=\"checkbox\" ");
														
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\" readonly");
															
												
												
												
												form_readonly.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
	          		    
				    form_readonly.append("\n</tr>");
				    
				    
				    form_readonly.append("\n<tr>");
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
				   	    if(i>2 && i<=5)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("  readonly");
												
												form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("date"))
	                         {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">"
													+"<div class=\"input-group input-append date\" id=\"datePicker\">");
												
												
												  form_readonly.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
														}
														
														form_readonly.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												
												form_readonly.append("  readonly");
												
												form_readonly.append("/>\n"
																+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																+"\n</span>"
																+"\n</div>"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                         }
         		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" readonly");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_readonly.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                            +"\n<option >---select---</option>"
                                                           +"\n</select>");
												}else{	
													
						
													form_readonly.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_readonly.append( "\t"+mapping4);
													form_readonly.append( "\" readonly>"
                                              +"\n<option >---select---</option>"
                                              +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("readonly");
												}
												form_readonly.append(">${drop_value}</option>\n"
                                              +"</select>\n");
											}
												form_readonly.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  type=\"checkbox\" ");
														}
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
												
												
												
												form_readonly.append("readonly />\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														
															form_readonly.append("  type=\"checkbox\" ");
														
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\" readonly");
															
												
												
												
												form_readonly.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
				    
				    form_readonly.append("\n</tr>");
				    
				    form_readonly.append("\n<tr>");
				    
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
	          		    if(i>5 && i<=8)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("  readonly");
												
												form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	if(type_field.equals("date"))
	                         {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">"
													+"<div class=\"input-group input-append date\" id=\"datePicker\">");
												
												
												  form_readonly.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
														}
														
														form_readonly.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												
												form_readonly.append("  readonly");
												
												form_readonly.append("/>\n"
																+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																+"\n</span>"
																+"\n</div>"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                         }
         		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" readonly");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_readonly.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                            +"\n<option >---select---</option>"
                                                           +"\n</select>");
												}else{	
													
						
													form_readonly.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_readonly.append( "\t"+mapping4);
													form_readonly.append( "\" readonly>"
                                              +"\n<option >---select---</option>"
                                              +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("readonly");
												}
												form_readonly.append(">${drop_value}</option>\n"
                                              +"</select>\n");
											}
												form_readonly.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  type=\"checkbox\" ");
														}
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
												
												
												
												form_readonly.append("readonly />\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														
															form_readonly.append("  type=\"checkbox\" ");
														
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\" readonly");
															
												
												
												
												form_readonly.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
				    form_readonly.append("\n</tr>");
				    
				    form_readonly.append("\n<tr>");
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
				   	    if(i>8 && i<=11)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("  readonly");
												
												form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
		          		    	if(type_field.equals("date"))
		                         {
		          		    		form_readonly.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                
										     System.out.println("-------------mandatory for testing=------------------"+mandatory);

													  if(mandatory.equals("Y"))
													  {   System.out.println("-------------in loop 1-------------------");
													  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													  form_readonly.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">"
														+"<div class=\"input-group input-append date\" id=\"datePicker\">");
													
													
													  form_readonly.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
														
															if(hidden.equals("Y"))
															{
																form_readonly.append("  type=\"hidden\" ");
															}else
															{
																form_readonly.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
															}
															
															form_readonly.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
																
															
													if(mandatory.equals("Y"))
													{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form_readonly.append("required");
													}
													
													
													form_readonly.append("  readonly");
													
													form_readonly.append("/>\n"
																	+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																	+"\n</span>"
																	+"\n</div>"
																	+ "\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                         }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_readonly.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_readonly.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" readonly");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_readonly.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_readonly.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                            +"\n<option >---select---</option>"
                                                           +"\n</select>");
												}else{	
													
						
													form_readonly.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_readonly.append( "\t"+mapping4);
													form_readonly.append( "\" readonly>"
                                              +"\n<option >---select---</option>"
                                              +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_readonly.append("readonly");
												}
												form_readonly.append(">${drop_value}</option>\n"
                                              +"</select>\n");
											}
												form_readonly.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_readonly.append("  type=\"hidden\" ");
														}else
														{
															form_readonly.append("  type=\"checkbox\" ");
														}
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
												
												
												
												form_readonly.append("readonly />\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_readonly.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_readonly.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_readonly.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_readonly.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														
															form_readonly.append("  type=\"checkbox\" ");
														
														
														form_readonly.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\" readonly");
															
												
												
												
												form_readonly.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
				    
				    form_readonly.append("\n</tr>\n\n<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_ext_Readonly.jsp\"%>\n</c:forEach>");
				    
				    
				    
				    
				    form_readonly.append("\n</table>"); 
				    
				    if(line_table_name !=null && !line_table_name.isEmpty())
				    {
				    	String line_table_name_lower=line_table_name.toLowerCase();
				        String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
				        String line_table_name_upper=line_table_name.toUpperCase();
				        
				    	form_readonly.append("\n\n<%@include file=\"/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_readonly.jsp\"%>");
				    }

				    form_readonly.append("\n<div class=\"hr hr-dotted\"></div>"
				    		/*+ "\n<div class=\"wizard-actions\">"
                                                            +"\n<button type=\"submit\" class=\"btn btn-success center\" onclick=\"submitForms()\">"
															  +"\nCREATE"
															+"\n</button>"
														+"\n</div> "*/ 
						  +"\n</form>");
				    
				    
				    strContentreadonly.append(importsectionreadonly+" \n"+ headsectionreadonly+"\n<body>\n<div class=\"main-container\" id=\"main-container\">"
	+"\n<div class=\"main-content\">"
			+"\n<div class=\"main-content-inner\">"
				+"\n<div class=\"breadcrumbs\" id=\"breadcrumbs\">"
					+"\n<ul class=\"breadcrumb\">"
						+"\n<li><i class=\"ace-icon fa fa-home home-icon\"></i> <a href=\"#\">Home</a>"
						+"\n</li>"

						+"\n<li><a href=\"#\">ManageUsers</a></li>"
						+"\n<li class=\"active\">User Registration</li>"
					+"\n</ul>"
				+"\n</div>"

				+"\n<div class=\"page-content\">"
                      +"\n<div class=\"page-header\">"
						+"\n<h1>User Registration</h1>"
					  +"\n</div>"
					

					+"\n<div class=\"row\">"
						+"\n<div class=\"col-xs-12\">"
							+"\n<div class=\"widget-box\" style=\"width: 90%; margin-left: 5%;\">"
								+"\n<div class=\"widget-header widget-header-blue widget-header-flat\">"
									+"\n<h4 class=\"widget-title lighter\">User Profile</h4>"
                                +"\n</div>"
                              +"\n<div class=\"widget-body\">"
									+"\n<div class=\"widget-main\">"
										+"\n<div id=\"fuelux-wizard-container\">"
                                             +"\n<div class=\"step-content pos-rel\">"
												+"\n<div class=\"step-pane active\" data-step=\"1\">"
													+"\n<h3 class=\"lighter block green\">User Credentials</h3>"
													+form_readonly
													
													+"\n</div>"
                                                         +"\n</div>"
                                                         +"\n</div>"
                                                       +"\n</div>"
                                                        +"\n</div>"
                                                         +"\n</div>"
                                                           +"\n</div>"
                                                          +"\n</div>"
                                                           +"\n</div>"
                                                         +"\n</div>"
                                                        +"\n</div>"
                                                       +"\n</div>\n</body>\n"+line_script_update+"</html>");
                       
                       
                       
//-------------------------------------jsp prefield update----------------------------------------
                       
				    importsectionprefield.append("\n<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>"
                            
                                             +"\n<%@ page import=\"java.util.ArrayList\"%>"
                                        + "\n<%@ page import=\"java.util.Date\"%>"
                                         +"\n<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>"
                                        + "\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\"%>"
                                         +"\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>");
                                         		
				    headsectionprefield.append("\n<html lang=\"en\">\n<head>" 
				    +"\n<meta http-equiv=\"X-UA-Compatible\"  content=\"IE=edge,chrome=1\">"
				    +"\n<meta charset=\"utf-8\" />"
				    +"\n<title>Realnet Oil Engines Ltd</title>"
				    +"\n<meta name=\"description\" content=\"Common form elements and layouts\" />"
				    +"\n<meta name=\"viewport\""
	                +"\ncontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />"
				    +"\n<!-- bootstrap & fontawesome -->"
                    +"\n<link rel=\"stylesheet\""
	                +"\n href=\"<c:url value='/resources/assets/css/bootstrap.min.css'/>\" />"
                    +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css'/>\" />"
	                +"\n<!-- page specific plugin styles -->"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/jquery-ui.custom.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/chosen.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/datepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/bootstrap-timepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/daterangepicker.min.css' />\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/bootstrap-datetimepicker.min.css'/>\" />"
	                +"\n<link rel=\"stylesheet\""
	                +"\nhref=\"<c:url value='/resources/assets/css/colorpicker.min.css' />\" />"
	                +"\n<!-- text fonts -->"
				    +"\n<link rel=\"stylesheet\""
				    +"\n\nhref=\"<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />\" />"
                    +"\n<!-- ace styles -->"
				    +"\n<link rel=\"stylesheet\""
				    +"\nhref=\"<c:url value='/resources/assets/css/ace.min.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style' />\" />"
				    +"\n<!-- inline styles related to this page -->"

				    +"\n<!-- ace settings handler -->"
				    +"\n<script src=\"<c:url value='/resources/assets/js/ace-extra.min.js'/>\""
				    	+"\ntype=\"text/javascript\"></script>"


				    		 +"\n<script>"
				    		    +"\nsubmitForms = function()"
				    		     +"\n{"
				                   +"\ndocument.forms[\"userdetails1\"].submit();"
				                   +"\ndocument.forms[\"userdetails2\"].submit();"
				                 +"}"
				            +"\n</script> "
				            + "\n<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" 
				            + "\n<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n" 
				           
				    			
				    +" \n</head>");
     
				    form_id.append("\n<input   value=\"${"+table_name_lower+"_updt."+field_name_for_id_lower+"}\"type=\"hidden\" name=\""+field_name_for_id_lower+"\"id=\""+field_name_for_id_lower+"\"class=\"col-xs-12 col-sm-4\" />"
	          		    	);
     
				    form_prefield.append("\n<form action=\""+action2+"\" class=\"form-horizontal\" id=\"Regi\" method=\"Post\">"
				    		+ "\n<input type=\"hidden\" name=\"test\" id=\"test\" value=\"\" />"
				    		+ " \n<table>"
				    		+ "\n<c:forEach var=\""+table_name_lower+"_updt\" items=\"${"+table_name_lower+"_update}\" varStatus=\"status\">"

							+ "\n<tr>"
							+ form_id);
				    form_prefield.append("\n</tr>");
				    
				    
				    form_prefield.append("\n<tr>");
				    for(int i=0;i<id_notprimary.size();i++)
	            	  {
				    	
				    	String field_name=id_notprimary.get(i).getField_name();
				    	String data_type=id_notprimary.get(i).getData_type();
				   	    String lower_case=field_name.toLowerCase();
				   	    String only_upper=field_name.toUpperCase();
				   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
				    	
				            if(data_type.equals("int"))
				            {
				            	form_prefield.append("\n<td>"
		          		    	+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
		          		    	+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
		          		    	 +first_upper
		          		    	 
		          		    	+"\n</label>"
		          		    	+"\n<div class=\"col-xs-12 col-sm-9\">"
		          		    	+"\n<div class=\"clearfix\">"
		          		    	+"\n<input   value=\"${"+table_name_lower+"_updt."+lower_case+"}\"type=\"text\" name=\""+lower_case+"\"id=\""+lower_case+"\"class=\"col-xs-12 col-sm-4\" />"
		          		    	+"\n</div>"
		          		    	+"\n</div>"
		          		    	+"\n</div>"
		          		    	+"\n</td>");
				            }
				    	
				    	
	            	  }
				    form_prefield.append("\n</tr>");
				    
				    form_prefield.append("\n<tr>");
				    
				    for(int i=0;i<datetime_list.size();i++)
	            	{
				    	
									String field_name=datetime_list.get(i).getField_name();
							    	String data_type=datetime_list.get(i).getData_type();
							   	    String lower_case=field_name.toLowerCase();
							   	    String only_upper=field_name.toUpperCase();
							   	    String first_upper = lower_case.substring(0, 1).toUpperCase() + lower_case.substring(1);
							    	
							   	    String mandatory=datetime_list.get(i).getMandatory();
							   	    String readonly=datetime_list.get(i).getReadonly();
							   	    String hidden=datetime_list.get(i).getHidden();
							   	    
							   	    String mapping=datetime_list.get(i).getMapping();
							   	    String mapping_lower=mapping.toLowerCase();
							   	    
							   	    
							   	     String dependent=datetime_list.get(i).getDependent();
							   	     String dependent_on=datetime_list.get(i).getDependent_on();
							   	     String dependent_sp=datetime_list.get(i).getDependent_sp();
							   	     String dependent_sp_param=datetime_list.get(i).getDependent_sp_param();
							   	     
							   	     String sequence=datetime_list.get(i).getSequence();
							   	     String seq_name=datetime_list.get(i).getSeq_name();  
							   	     String seq_sp=datetime_list.get(i).getSeq_sp();
							   	     String seq_sp_param=datetime_list.get(i).getSeq_sp_param();
							   	     
							   	       String validation_1=datetime_list.get(i).getValidation_1();
							   	       String val_type=datetime_list.get(i).getVal_type();
							   	       String val_sp=datetime_list.get(i).getVal_sp();
							   	       String val_sp_param=datetime_list.get(i).getVal_sp_param();
							   	       
							   	       
							   	       String default_1=datetime_list.get(i).getDefault_1();
							   	       String default_typename=datetime_list.get(i).getDefault_type();
							   	       String default_value=datetime_list.get(i).getDefault_value();
							   	       String default_sp=datetime_list.get(i).getDefault_sp();
							   	       String default_sp_param=datetime_list.get(i).getDefault_sp_param();
							   	       
							   	       String calculated_field=datetime_list.get(i).getCalculated_field();
							   	       String cal_sp=datetime_list.get(i).getCal_sp();
							   	       String cal_sp_param=datetime_list.get(i).getCal_sp_param();
							   	       
							   	       String add_to_grid=datetime_list.get(i).getAdd_to_grid();
							   	       
							   	       String sp_for_dropdown=datetime_list.get(i).getSp_for_dropdown();
							   	       String sp_for_autocomplete=datetime_list.get(i).getSp_for_autocomplete();
							   	       
							   	       String sp_name_for_dropdown=datetime_list.get(i).getSp_name_for_dropdown();
							   	       String sp_name_for_autocomplete=datetime_list.get(i).getSp_name_for_autocomplete();
				
				                       System.out.println("-----value of mantadoryr laest-----"+mandatory);
							   	    
				                       if(data_type.equals("datetime"))
				                       {
				                    	   
				   				    	System.out.println("---under if condition loop latest------");
				
				   				    	form_prefield.append("\n<td>"
													+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
														+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
														   +"\n "+field_name);
				                              
											     System.out.println("-------------mandatory for testing=------------------"+mandatory);
				
														  if(mandatory.equals("Y"))
														  {   System.out.println("-------------in loop 1-------------------");
														  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
														  }
														
				
														  form_prefield.append("\n</label>"
																+"\n<div class=\"col-xs-12 col-sm-9\">"
															+"\n<div class=\"clearfix\">"
															+"<div class=\"input-group input-append date\" id=\"datePicker"+i+"\">");
														
														
														  form_prefield.append("\n<input" );
															
																if(hidden.equals("Y"))
																{
																	form_prefield.append("  type=\"hidden\" ");
																}else
																{
																	form_prefield.append("  type=\"text\" ");
																}
																
																form_prefield.append( "name="+mapping_lower+"  value=\"${"+table_name_lower+"_updt."+mapping_lower+"}\"  id="+mapping_lower+" placeholder=\"picup Date\" class=\"form-control\"");
																	
																
														if(mandatory.equals("Y"))
														{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
														form_prefield.append("required");
														}
														
														if(readonly.equals("Y"))
														{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
														form_prefield.append("  readonly");
														}
														form_prefield.append("/>\n"
																		+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																		+"\n</span>"
																		+"\n</div>"
																		+ "\n</div>"
														+"\n</div>"
													+"\n</div>"
													+"\n</td>");
				                       }
								    	
				    	
	            	  }
				    
				    
				    form_prefield.append("\n</tr>");
				    
				   
				    form_prefield.append("\n<tr>");
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
	                	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
	          		    if(i<=2)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("  readonly");
												}
												form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("date"))
	                         {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">"
													+"<div class=\"input-group input-append date\" id=\"datePicker\">");
												
												
												  form_prefield.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
														}
														
														form_prefield.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("  readonly");
												
												form_prefield.append("/>\n"
																+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																+"\n</span>"
																+"\n</div>"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                         }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" ");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_prefield.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                              +"\n<option >---select---</option>"
                                                             +"\n</select>");
												}else{	
													
						
													form_prefield.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_prefield.append( "\t"+mapping4);
													form_prefield.append( "\" >"
                                                +"\n<option >---select---</option>"
                                                +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("readonly");
												}
												form_prefield.append(">${drop_value}</option>\n"
                                                +"</select>\n");
											}
												form_prefield.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
	          		    
				    form_prefield.append("\n</tr>");
				    
				    
				    form_prefield.append("\n<tr>");
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
	          		  if(i>2 && i<=5)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("  readonly");
												}
												form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
		          		    	if(type_field.equals("date"))
		                         {
		          		    		form_prefield.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                
										     System.out.println("-------------mandatory for testing=------------------"+mandatory);

													  if(mandatory.equals("Y"))
													  {   System.out.println("-------------in loop 1-------------------");
													  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													  form_prefield.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">"
														+"<div class=\"input-group input-append date\" id=\"datePicker\">");
													
													
													  form_prefield.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
														
															if(hidden.equals("Y"))
															{
																form_prefield.append("  type=\"hidden\" ");
															}else
															{
																form_prefield.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
															}
															
															form_prefield.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
																
															
													if(mandatory.equals("Y"))
													{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form_prefield.append("required");
													}
													
													
													form_prefield.append("  readonly");
													
													form_prefield.append("/>\n"
																	+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																	+"\n</span>"
																	+"\n</div>"
																	+ "\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                         }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" ");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_prefield.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                              +"\n<option >---select---</option>"
                                                             +"\n</select>");
												}else{	
													
						
													form_prefield.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_prefield.append( "\t"+mapping4);
													form_prefield.append( "\" >"
                                                +"\n<option >---select---</option>"
                                                +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("readonly");
												}
												form_prefield.append(">${drop_value}</option>\n"
                                                +"</select>\n");
											}
												form_prefield.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
				    
				    form_prefield.append("\n</tr>");
				    
				    form_prefield.append("\n<tr>");
				    
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
	          		    if(i>5 && i<=8)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("  readonly");
												}
												form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
		          		    	if(type_field.equals("date"))
		                         {
		          		    		form_prefield.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                
										     System.out.println("-------------mandatory for testing=------------------"+mandatory);

													  if(mandatory.equals("Y"))
													  {   System.out.println("-------------in loop 1-------------------");
													  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													  form_prefield.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">"
														+"<div class=\"input-group input-append date\" id=\"datePicker\">");
													
													
													  form_prefield.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
														
															if(hidden.equals("Y"))
															{
																form_prefield.append("  type=\"hidden\" ");
															}else
															{
																form_prefield.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
															}
															
															form_prefield.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
																
															
													if(mandatory.equals("Y"))
													{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form_prefield.append("required");
													}
													
													
													form_prefield.append("  readonly");
													
													form_prefield.append("/>\n"
																	+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																	+"\n</span>"
																	+"\n</div>"
																	+ "\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                         }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" ");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_prefield.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                              +"\n<option >---select---</option>"
                                                             +"\n</select>");
												}else{	
													
						
													form_prefield.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_prefield.append( "\t"+mapping4);
													form_prefield.append( "\" >"
                                                +"\n<option >---select---</option>"
                                                +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("readonly");
												}
												form_prefield.append(">${drop_value}</option>\n"
                                                +"</select>\n");
											}
												form_prefield.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
				    form_prefield.append("\n</tr>");
				    
				    form_prefield.append("\n<tr>");
				    for(int i=0;i<rn_userlist.size();i++)
	            	  {
				    	String field_name4=rn_userlist.get(i).getField_name();
	                	String field_name_lower=field_name4.toLowerCase();
	          			String mapping4=rn_userlist.get(i).getMapping();
	          			String mapping_lower_case=mapping4.toLowerCase();
	          			String data_type4=rn_userlist.get(i).getData_type();
	          			
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          			String lower_case=field_name4.toLowerCase();
	          			System.out.println("field name-------"+field_name4); 
	          		    System.out.println("mapping-------"+mapping4);
	          		    System.out.println("data type-------"+data_type4);
	          		    
	          		    
	          		    String mandatory=rn_userlist.get(i).getMandatory();
				   	    String readonly=rn_userlist.get(i).getReadonly();
				   	    String hidden=rn_userlist.get(i).getHidden();
				   	    
				   	    //String mapping=rn_userlist.get(i).getMapping();
				   	    
				   	    String type_field=rn_userlist.get(i).getType_field();
				   	    
				   	    
				   	     String dependent=rn_userlist.get(i).getDependent();
				   	     String dependent_on=rn_userlist.get(i).getDependent_on();
				   	     String dependent_sp=rn_userlist.get(i).getDependent_sp();
				   	     String dependent_sp_param=rn_userlist.get(i).getDependent_sp_param();
				   	     
				   	     String sequence=rn_userlist.get(i).getSequence();
				   	     String seq_name=rn_userlist.get(i).getSeq_name();  
				   	     String seq_sp=rn_userlist.get(i).getSeq_sp();
				   	     String seq_sp_param=rn_userlist.get(i).getSeq_sp_param();
				   	     
				   	       String validation_1=rn_userlist.get(i).getValidation_1();
				   	       String val_type=rn_userlist.get(i).getVal_type();
				   	       String val_sp=rn_userlist.get(i).getVal_sp();
				   	       String val_sp_param=rn_userlist.get(i).getVal_sp_param();
				   	       
				   	       
				   	       String default_1=rn_userlist.get(i).getDefault_1();
				   	       String default_typename=rn_userlist.get(i).getDefault_type();
				   	       String default_value=rn_userlist.get(i).getDefault_value();
				   	       String default_sp=rn_userlist.get(i).getDefault_sp();
				   	       String default_sp_param=rn_userlist.get(i).getDefault_sp_param();
				   	       
				   	       String calculated_field=rn_userlist.get(i).getCalculated_field();
				   	       String cal_sp=rn_userlist.get(i).getCal_sp();
				   	       String cal_sp_param=rn_userlist.get(i).getCal_sp_param();
				   	       
				   	       String add_to_grid=rn_userlist.get(i).getAdd_to_grid();
				   	       
				   	       String sp_for_dropdown=rn_userlist.get(i).getSp_for_dropdown();
				   	       String sp_for_autocomplete=rn_userlist.get(i).getSp_for_autocomplete();
				   	       
				   	       String sp_name_for_dropdown=rn_userlist.get(i).getSp_name_for_dropdown();
				   	       String sp_name_for_autocomplete=rn_userlist.get(i).getSp_name_for_autocomplete();

	          		    
	          		    
	          		    
				   	    if(i>8 && i<=11)
	          		    {
	          		    	 if(type_field.equals("textfield"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name=\""+mapping_lower_case+"\" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														}	
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("  readonly");
												}
												form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
		          		    	if(type_field.equals("date"))
		                         {
		          		    		form_prefield.append("\n<td>"
												+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
													+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
													   +"\n "+field_name4);
		                                
										     System.out.println("-------------mandatory for testing=------------------"+mandatory);

													  if(mandatory.equals("Y"))
													  {   System.out.println("-------------in loop 1-------------------");
													  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
													  }
													

													  form_prefield.append("\n</label>"
															+"\n<div class=\"col-xs-12 col-sm-9\">"
														+"\n<div class=\"clearfix\">"
														+"<div class=\"input-group input-append date\" id=\"datePicker\">");
													
													
													  form_prefield.append("\n<input  value=\"${"+table_name_lower+"_updt."+mapping4+"}\"" );
														
															if(hidden.equals("Y"))
															{
																form_prefield.append("  type=\"hidden\" ");
															}else
															{
																form_prefield.append("   value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"text\" ");
															}
															
															form_prefield.append( "name="+mapping4+" id="+mapping4+" placeholder=\"picup Date\" class=\"form-control\"");
																
															
													if(mandatory.equals("Y"))
													{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form_prefield.append("required");
													}
													
													
													form_prefield.append("  readonly");
													
													form_prefield.append("/>\n"
																	+"\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
																	+"\n</span>"
																	+"\n</div>"
																	+ "\n</div>"
													+"\n</div>"
												+"\n</div>"
												+"\n</td>");
		                         }
	          		    	 
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("autocomplete"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
													
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append(" value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\"  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  value=\"${"+table_name_lower+"_updt."+mapping_lower_case+"}\" type=\"text\" ");
														}
														if(calculated_field.equals("Y"))
														{
															form_prefield.append( "name=\"total\" id=\"total\" class=\"stunden form-control\" ");
														}
														else
														{
															form_prefield.append( "name="+mapping_lower_case+" id=\""+mapping_lower_case+"\" class=\"col-xs-12 col-sm-4\" ");
														}	
														
												/*if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("required");
												}*/
												
												/*if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
													form.append("  readonly");
												}*/
														form_prefield.append("/>\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
														
														
										
	                           }
	          		    	
	          		    	
	          		    	if(type_field.equals("dropdown"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
														
												if(dependent.equals("Y"))
												{
													
													form_prefield.append( "\n<select name=\"state_name\" id=\""+mapping4+"\"  class=\"col-xs-3 col-sm-3 form-control state\">"
                                                              +"\n<option >---select---</option>"
                                                             +"\n</select>");
												}else{	
													
						
													form_prefield.append("\n<select name=\"drop_value\" id=\""+mapping4+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
													form_prefield.append( "\t"+mapping4);
													form_prefield.append( "\" >"
                                                +"\n<option >---select---</option>"
                                                +"\n<option value=\"${drop_value}\"");
													
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												if(readonly.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("readonly");
												}
												form_prefield.append(">${drop_value}</option>\n"
                                                +"</select>\n");
											}
												form_prefield.append("</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	
	          		    	
	          		    	 if(type_field.equals("togglebutton"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+"\n<span class=\"lbl\"></span>"
																
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	 
	          		    	 
	          		    	 
	          		    	if(type_field.equals("checkbox"))
	                           {
	          		    		form_prefield.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+field_name4);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
												  form_prefield.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												  form_prefield.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
												  form_prefield.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
													
														if(hidden.equals("Y"))
														{
															form_prefield.append("  type=\"hidden\" ");
														}else
														{
															form_prefield.append("  type=\"checkbox\" ");
														}
														
														form_prefield.append( " value=\"${"+table_name_lower+"_updt."+field_name_lower+"}\" name="+mapping4+" id="+mapping4+"  onblur=\"CheckUserStatusHeader1()\"");
															
														
												if(mandatory.equals("Y"))
												{ 	  System.out.println("-------------in loop 1 part 2 required-------------------");
												form_prefield.append("required");
												}
												
												
												form_prefield.append("/>\n"
																+ "\n</div>"
												+"\n</div>"
											+"\n</div>"
											+"\n</td>");
	                           }
	          		    	}
	          		  }
				    
				    form_prefield.append("\n</tr>\n\n<%@include file=\"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_ext_Update.jsp\"%>\n"
				    		+ "\n</c:forEach>");
				    
				    
				    
				    
				    form_prefield.append("\n</table>"); 
				    
				    if(line_table_name != null && !line_table_name.isEmpty())
				    {
				    	 String line_table_name_lower=line_table_name.toLowerCase();
					     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
					     String line_table_name_upper=line_table_name.toUpperCase();
					     
				        form_prefield.append("\n\n<%@include file=\"/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_update.jsp\"%>");
				    }
				    
				    form_prefield.append("\n<div class=\"hr hr-dotted\"></div>"
				    		+ "\n<div class=\"wizard-actions\">"
                                                            +"\n<button type=\"submit\" class=\"btn btn-success center\" onclick=\"submitForms()\">"
															  +"\nCREATE"
															+"\n</button>"
														+"\n</div> " 
						  +"\n</form>");
				    
				    
		strContentprefield.append(importsectionprefield + " \n" + headsectionprefield
								+ "\n<body>\n<div class=\"main-container\" id=\"main-container\">" + "\n<div class=\"main-content\">"
								+ "\n<div class=\"main-content-inner\">" + "\n<div class=\"breadcrumbs\" id=\"breadcrumbs\">"
								+ "\n<ul class=\"breadcrumb\">"
								+ "\n<li><i class=\"ace-icon fa fa-home home-icon\"></i> <a href=\"#\">Home</a>" + "\n</li>"
				
								+ "\n<li><a href=\"#\">ManageUsers</a></li>" + "\n<li class=\"active\">User Registration</li>"
								+ "\n</ul>" + "\n</div>"
				
								+ "\n<div class=\"page-content\">" 
								+ "\n<div class=\"page-header\">" 
								+ "\n<h1>User Registration"
								
								+" \n <div style=\"float: right; padding-right: 5%;\">"
								+ " \n <a href=\"#myModel\" id=\"${rn_rb_reports_t_updt.id}\">"
								+ "  \n  <i class=\"fa fa-ticket\" aria-hidden=\"true\""
								+"	 \n  data-toggle=\"modal\" data-target=\"#myModal\">"
								+"	 \n  </i>"
								  +" \n</a>"
							      +"\n</div>" 
								
								+ "\n</h1>"
								+ "\n</div>"
				
								+ "\n<div class=\"row\">" + "\n<div class=\"col-xs-12\">"
								+ "\n<div class=\"widget-box\" style=\"width: 90%; margin-left: 5%;\">"
								+ "\n<div class=\"widget-header widget-header-blue widget-header-flat\">"
								+ "\n<h4 class=\"widget-title lighter\">User Profile</h4>" + "\n</div>"
								+ "\n<div class=\"widget-body\">" + "\n<div class=\"widget-main\">"
								+ "\n<div id=\"fuelux-wizard-container\">" + "\n<div class=\"step-content pos-rel\">"
								+ "\n<div class=\"step-pane active\" data-step=\"1\">"
								/* +"\n<h3 class=\"lighter block green\">User Credentials</h3>" */
								+ "<div class=\"table-header\" style=\"margin-top: 30px;\">" + "User Credentials" + "</div>"
								+ form_prefield
								+"\n<div class=\"modal fade\"  id=\"myModal\" role=\"dialog\">"
								+"\n<div class=\"modal-dialog\">"
								+"\n	<!-- Modal content-->"
								+"\n	<div class=\"modal-content\">"
								+"\n		<div class=\"modal-header\">"
								+"\n			<button type=\"button\" class=\"close\""
								+"\n				data-dismiss=\"modal\">&times;</button>"
								+"	\n	</div>"
								+"	\n	<input type=\"hidden\" name=\"test\" id=\"test\" >"
								
                                + "\n<c:forEach var=\""+table_name_lower+"_updt\" items=\"${"+table_name_lower+"_update}\" varStatus=\"status\">"

								
								+"\n			<input value=\"${rn_rb_reports_t_updt.id}\""
								+"\n				type=\"hidden\" name=\"id\" id=\"id\""
								+"\n				class=\"col-xs-12 col-sm-4\" />"

                                
                                
								+"	\n		<div class=\"modal-body mx-3\">"
								+"		\n		<div class=\"md-form mb-5\">"
								+"	\n				<label data-error=\"wrong\" data-success=\"right\">Account</label>"
								+"	\n				<input type=\"text\" id=\"acc_id\" name=\"acc_id\""
								+"	\n					class=\"form-control validate\""
								+"	\n					value=\"${"+table_name_lower+"_updt.account_id}\" readonly>"
								+"	\n			</div>"
								+"	\n		</div>"
								+"	\n		<div class=\"modal-body mx-3\">"
								+"	\n			<div class=\"md-form mb-5\">"
								+"	\n				<label data-error=\"wrong\" data-success=\"right\">Form_Name</label>"
								+"	\n				<input type=\"text\" id=\"form_name\" name=\"form_name\""
								+"	\n					class=\"form-control validate\""
								+"	\n					 readonly>"
								+"	\n			</div>"
								+"	\n		</div>"
								+"	\n		<div class=\"modal-body mx-3\">"
								+"	\n			<div class=\"md-form mb-5\">"
								+"	\n				<label data-error=\"wrong\" data-success=\"right\">Form_Code</label>"
								+"	\n				<input type=\"text\" id=\"form_code\" name=\"form_code\""
								+"	\n					class=\"form-control validate\""
								+"	\n					 readonly>"
								+"	\n			</div>"
								+"	\n		</div>"
								+"	\n		<div class=\"modal-body mx-3\">"
								+"	\n			<div class=\"md-form mb-5\">"
								+"	\n				<label data-error=\"wrong\" data-success=\"right\">Created_By</label>"
								+"	\n				<input type=\"text\" id=\"created_by\""
								+"	\n					name=\"created_by\" class=\"form-control validate\""
								+"	\n					value=\"${"+table_name_lower+"_updt.created_by}\""
								+"	\n					readonly>"
								+"	\n			</div>"
								+"	\n		</div>"
								+"	\n		<div class=\"modal-body mx-3\">"
								+"	\n			<div class=\"md-form mb-5\">"
								+"	\n				<label data-error=\"wrong\" data-success=\"right\">Creation_Date</label>"
								+"	\n				<input type=\"text\" id=\"creation_date\""
								+"	\n					name=\"creation_date\" class=\"form-control validate\""
								+"	\n					value=\"${"+table_name_lower+"_updt.creation_date}\""
								+"	\n					readonly>"
								+"	\n			</div>"
								+"	\n		</div>"
								+"	\n		<div class=\"modal-body mx-3\">"
								+"	\n			<div class=\"md-form mb-5\">"
								+"	\n				<label data-error=\"wrong\" data-success=\"right\">Last_Updated_by</label>"
								+"	\n				<input type=\"text\" id=\"last_updated_by\""
								+"	\n					name=\"last_updated_by\""
								+"	\n					class=\"form-control validate\""
								+"	\n					value=\"${"+table_name_lower+"_updt.last_updated_by}\""
								+"	\n					readonly>"
								+"	\n			</div>"
								+"	\n		</div>"
								+"	\n		<div class=\"modal-body mx-3\">"
								+"	\n			<div class=\"md-form mb-5\">"
								+"	\n				<label data-error=\"wrong\" data-success=\"right\">Last_Update_Date</label>"
								+"	\n				<input type=\"text\" id=\"last_update_date\""
								+"	\n					name=\"last_update_date\""
								+"	\n					class=\"form-control validate\""
								+"	\n					value=\"${"+table_name_lower+"_updt.last_update_date}\""
								+"	\n					readonly>"
								+"	\n			</div>"
								+"	\n		</div>"
								+   "\n</c:forEach>"
								+"	\n	<div class=\"modal-body\"></div>"
								+"	\n	<div class=\"modal-footer\">"
								+"	\n		<button type=\"button\" class=\"btn btn-default\""
								+"	\n			data-dismiss=\"modal\">Close</button>"
								+"	\n	</div>"
						      	+"	\n	</div>"
								+"\n</div>"
							    +"\n</div>"	
							
							    +"\n<div class=\"modal-body mx-3\">"
				
								+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>"
								+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>\n"
								+ "\n<script src=\"<c:url value='/resources/assets/js/bootstrap-datepicker.min.js'/>\" type=\"text/javascript\">"
								+ "\n</script>" + "\n<script>" + date_script + "\n</script>" + "\n" + line_script_update
								+ "\n</body>\n</html>");
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                    
				   
			    	
			//---------------------for production-----------------------------	
                
				    
				    
				    
				    
				    
				    
				    
				    
				    
                       
                       
                       
                       
                       
		String devPath=environment.getRequiredProperty("devPath");           
                       
		List<Rn_Instance_Type>	rn_instance_type_t=rn_instance_type_dao.userlist();
          
		String instance_type=rn_instance_type_t.get(0).getInstance_type();
		
		System.out.println("instance type ==="+instance_type);
		System.out.println("Dev path::"+devPath);
		
		if(instance_type.equals("Dev"))
		{ 
		
			
		    File temp22 = new File( devPath+"/WEB-INF/tiles.xml");
		    File newtemp22 = new File( devPath+"/WEB-INF/xyz.xml");
		    BufferedReader br22 = new BufferedReader(new FileReader(temp22));
		    BufferedWriter bw22 = new BufferedWriter(new FileWriter(newtemp22));
		    String removeStr22 = "</tiles-definitions>";
		    String currentLine22;
		    
		    System.out.println(temp22.getName());
		    while((currentLine22 = br22.readLine()) != null)
		    {
		        String trimmedLine = currentLine22.trim();
		        if(trimmedLine.equals(removeStr22))
	               {
		            currentLine22 = "";
		        }
		        bw22.write(currentLine22 + System.getProperty("line.separator"));
	
		    }
		    bw22.close();
		    br22.close();
		    boolean delete22 = temp22.delete();
		    boolean b22 = newtemp22.renameTo(temp22);
		
		    StringBuilder tiles22=new StringBuilder();	
	     
		    try
	        {
		    	
	     	
		    	tiles22.append("\n<definition name=\""+table_name_first_upper+"_grid\" extends=\"acemaster.definition\">" 
				        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
				        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_grid.jsp\"/>"
				      +"\n</definition>");
				      
	        
		        tiles22.append("\n<definition name=\""+jsp_name+"\" extends=\"acemaster.definition\">" 
				        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
				        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+jsp_name+".jsp\"/>"
				      +"\n</definition>");
				     
		        
		        tiles22.append("\n<definition name=\""+table_name_first_upper+"_update\" extends=\"acemaster.definition\">" 
				        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
				        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_update.jsp\"/>"
				      +"\n</definition>");
				      
		        
		        tiles22.append("\n<definition name=\""+table_name_first_upper+"_readonly\" extends=\"acemaster.definition\">" 
				        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
				        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_readonly.jsp\"/>"
				      +"\n</definition>"
				      + "\n</tiles-definitions>");
	        
		    
			    String  filename=devPath+"/WEB-INF/tiles.xml";
			    
	
	           FileWriter fw=new FileWriter(filename,true);    
	           fw.write(tiles22.toString());    
	           fw.close(); 
	           
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }
        }else {
        	
        	String tilespath = request.getServletContext().getRealPath("/WEB-INF");
            File tilespath1 = new File(tilespath+"/tiles.xml");

	    	
		    File temp = new File(projectPath+"/src/main/webapp/WEB-INF/tiles.xml");
		    
           // File newtemp = new File("F:\\shubham\\Wecan6\\REAL_NET\\src\\main\\webapp\\WEB-INF\\xyz.xml");
		    File newtemp = new File( projectPath+"/src/main/webapp/WEB-INF/xyz.xml");
		    File newTilespath= new File(tilespath+"/xyz.xml");

		    
			BufferedReader br = new BufferedReader(new FileReader(temp));
		    BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
		    
		    BufferedReader br2 = new BufferedReader(new FileReader(tilespath1));
		    BufferedWriter bw2 = new BufferedWriter(new FileWriter(newTilespath));
		    String removeStr = "</tiles-definitions>";
		    String currentLine;
		    String currentLine2;
		    System.out.println(temp.getName());
		    while((currentLine = br.readLine()) != null)
		    {
		        String trimmedLine = currentLine.trim();
		        if(trimmedLine.equals(removeStr))
	               {
		            currentLine = "";
		        }
		        bw1.write(currentLine + System.getProperty("line.separator"));

		    }
		    bw1.close();
		    br.close();
		    boolean delete = temp.delete();
		    boolean b = newtemp.renameTo(temp);
		
		    //for production
		    while((currentLine2 = br2.readLine()) != null)
		    {
		        String trimmedLine = currentLine2.trim();
		        if(trimmedLine.equals(removeStr))
	               {
		            currentLine2= "";
		        }
		        bw2.write(currentLine2 + System.getProperty("line.separator"));

		    }
		    bw2.close();
		    br2.close();
		    boolean delete2 = tilespath1.delete();
		    boolean b2 = newTilespath.renameTo(tilespath1);
		    
		    
		       
               
            StringBuilder tiles=new StringBuilder();	
            
		    try
            {
            	
            tiles.append("\n<definition name=\""+table_name_first_upper+"_grid\" extends=\"acemaster.definition\">" 
				        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
				        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_grid.jsp\"/>"
				      +"\n</definition>");
				      
            
            tiles.append("\n<definition name=\""+jsp_name+"\" extends=\"acemaster.definition\">" 
			        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
			        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+jsp_name+".jsp\"/>"
			      +"\n</definition>");
			     
            
            tiles.append("\n<definition name=\""+table_name_first_upper+"_update\" extends=\"acemaster.definition\">" 
			        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
			        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_update.jsp\"/>"
			      +"\n</definition>");
			      
            
            tiles.append("\n<definition name=\""+table_name_first_upper+"_readonly\" extends=\"acemaster.definition\">" 
			        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
			        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_readonly.jsp\"/>"
			      +"\n</definition>"
			      + "\n</tiles-definitions>");
               
		    // String filename="F:/shubham/Wecan6/REAL_NET/src/main/webapp/WEB-INF/tiles.xml";
			    String filename=projectPath+"/src/main/webapp/WEB-INF/tiles.xml";
			    String filename2=tilespath+"/tiles.xml";

	           FileWriter fw=new FileWriter(filename,true);    
	           fw.write(tiles.toString());    
	           fw.close(); 
	           
	           FileWriter fw2=new FileWriter(filename2,true);    
	           fw2.write(tiles.toString());    
	           fw2.close();
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } 
        	
        	
        }
		
		
		System.out.println("after tiles ==="+instance_type);
		
                       
		         
                       
   if(instance_type.equals("Eclipse"))
   {                    
    
    try {
    	//String moveToclasses = request.getServletContext().getRealPath("/WEB-INF/classes");
    	
    	String jspPath = request.getServletContext().getRealPath("/WEB-INF/tiles/acemaster");
    	
    	System.out.println("jsp real path::"+jspPath);


    	//java files
        File file1 = new File(projectPath+"/src/main/java/com/springmvc/controller/"+controller_name_first_upper+".java");
        File file2 = new File(projectPath+"/src/main/java/com/springmvc/dao/"+dao_name_first_upper+".java");
		File file3 = new File(projectPath+"/src/main/java/com/springmvc/dao/"+dao_impl_name_first_upper+".java");
        File file4 = new File(projectPath+"/src/main/java/com/springmvc/service/"+service_name_first_upper+".java");
		File file5 = new File(projectPath+"/src/main/java/com/springmvc/service/"+service_impl_name_first_upper+".java");
        File file7 = new File(projectPath+"/src/main/java/com/springmvc/model/"+table_name_first_upper+".java");

		
		//crud jsp
		File file6 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+jsp_name+".jsp");
		File file8 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_grid.jsp");
		File file9 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_update.jsp");
		File file10 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_readonly.jsp");
		
		
		//for production crud
		File jspPath1 = new File(jspPath+"/FB_jsp/"+jsp_name+".jsp");
		File jspPath2 = new File(jspPath+"/FB_jsp/"+table_name_first_upper+"_grid.jsp");
		File jspPath3 = new File(jspPath+"/FB_jsp/"+table_name_first_upper+"_update.jsp");
		File jspPath4 = new File(jspPath+"/FB_jsp/"+table_name_first_upper+"_readonly.jsp");
		
    	System.out.println("jsp actual  file  path::"+jspPath1);

		
		
		
		
		
		//extension jsps
		File file15 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_extension.jsp");
        //File file20 = new File("F:/Ganesh/Realnet_17/REAL_NET_GB1/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension2.jsp");
        File file18 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_add_grid.jsp");
		File file19 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_add_grid2.jsp");
        File file21 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_ext_Update.jsp");
        File file22 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_ext_Readonly.jsp");
       
        
      //extension jsps
      	File jspExt1 = new File(jspPath+"/extpages/"+table_name_lower+"_extension.jsp");
        File jspExt2 = new File(jspPath+"/extpages/"+table_name_lower+"_add_grid.jsp");
        File jspExt3 = new File(jspPath+"/extpages/"+table_name_lower+"_add_grid2.jsp");
        File jspExt4 = new File(jspPath+"/extpages/"+table_name_lower+"_ext_Update.jsp");
        File jspExt5 = new File(jspPath+"/extpages/"+table_name_lower+"_ext_Readonly.jsp");
             
		
		
		System.out.println("file name in build form"+file1);
		
		
		
		if(!file1.exists()) 
		{
		   file1.createNewFile();
		   
			
		}

		FileWriter fw = new FileWriter(file1.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(controller.toString());
		bw.close();


		System.out.println("DoneController");

		
		if(!file15.exists()) 
		{
		   file15.createNewFile();
			
		}

		 fw = new FileWriter(file15.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		 bw.write("");
		 bw.close();
		
		
		if(!file18.exists()) 
		{
		   file18.createNewFile();
			
		}

		 fw = new FileWriter(file18.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		bw.write("");
		bw.close();
		
		
		if(!file19.exists()) 
		{
		   file19.createNewFile();
			
		}

		 fw = new FileWriter(file19.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		bw.write("");
		bw.close();
		
		
		
		if(!file21.exists()) 
		{
		   file21.createNewFile();
			
		}

		 fw = new FileWriter(file21.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		bw.write("");
		bw.close();
		
		
		
		if(!file22.exists()) 
		{
		   file22.createNewFile();
			
		}

		 fw = new FileWriter(file22.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		bw.write("");
		bw.close();
		
		
		
		
		
		
		
		if (!file2.exists()) {
			file2.createNewFile();
		}
		fw = new FileWriter(file2.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(dao.toString());
		bw.close();

		System.out.println("Done dao");
     
		
		
		
		if (!file3.exists()) 
		{
			file3.createNewFile();
		}
		fw = new FileWriter(file3.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(dao_impl.toString());
		bw.close();

		System.out.println("Done dao impl");

		
		
		
		if (!file4.exists()) {
			file4.createNewFile();
		}
		fw = new FileWriter(file4.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(service.toString());
		bw.close();

		System.out.println("Done service");

		
		
		if (!file5.exists()) {
			file5.createNewFile();
		}
		fw = new FileWriter(file5.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(service_impl.toString());
		bw.close();

		System.out.println("Done service impl");
		
		if (!file6.exists()) {
			file6.createNewFile();
		}
		fw = new FileWriter(file6.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(strContent.toString());
		bw.close();

		System.out.println("Done jsp");

		
		
		
		if (!file7.exists()) {
			file7.createNewFile();
		}
		fw = new FileWriter(file7.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(model_class.toString());
		bw.close();

		System.out.println("Done service impl");
		
		
		if (!file8.exists()) {
			file8.createNewFile();
		}
		fw = new FileWriter(file8.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(table_grid_view.toString());
		bw.close();

		System.out.println("Done service impl");
		
		
		
		if (!file9.exists())
		{
			file9.createNewFile();
		}
		fw = new FileWriter(file9.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(strContentprefield.toString());
		bw.close();
		
		
		
		if (!file10.exists()) {
			file10.createNewFile();
		}
		fw = new FileWriter(file10.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(strContentreadonly.toString());
		bw.close();
		
		
		//for production part crud jsp
		if (!jspPath1.exists()) 
		{
			jspPath1.createNewFile();
		}
		fw = new FileWriter(jspPath1.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(strContent.toString());
		bw.close();
		
		
		if (!jspPath2.exists()) {
			jspPath2.createNewFile();
		}
		fw = new FileWriter(jspPath2.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(table_grid_view.toString());
		bw.close();

		System.out.println("Done service impl");
		
		
		
		if (!jspPath3.exists())
		{
			jspPath3.createNewFile();
		}
		fw = new FileWriter(jspPath3.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(strContentprefield.toString());
		bw.close();
		
		
		
		if (!jspPath4.exists()) {
			jspPath4.createNewFile();
		}
		fw = new FileWriter(jspPath4.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(strContentreadonly.toString());
		bw.close();
		
		
		
		//for production part exxtension jsp
		if(!jspExt1.exists()) 
		{
			jspExt1.createNewFile();
			
		}

		 fw = new FileWriter(jspExt1.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		bw.write("");
		bw.close();
		
		
		if(!jspExt2.exists()) 
		{
			jspExt2.createNewFile();
			
		}

		 fw = new FileWriter(jspExt2.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		 bw.write("");
		 bw.close();
		
		
		if(!jspExt3.exists()) 
		{
			jspExt3.createNewFile();
			
		}

		 fw = new FileWriter(jspExt3.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		 bw.write("");
		 bw.close();
		
		
		
		if(!jspExt4.exists()) 
		{
			jspExt4.createNewFile();
			
		}

		 fw = new FileWriter(jspExt4.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		 bw.write("");
		 bw.close();
		
		
		
		if(!jspExt5.exists()) 
		{
			jspExt5.createNewFile();
			
		}

		 fw = new FileWriter(jspExt5.getAbsoluteFile());
		 bw = new BufferedWriter(fw);
		 bw.write("");
		 bw.close();
		
		
		
		
		if(line_table_name != null && !line_table_name.isEmpty())
		{
			    String line_table_name_lower=line_table_name.toLowerCase();
		        String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
		        String line_table_name_upper=line_table_name.toUpperCase();
		     
			    File file11 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_line_view.jsp");

		        File file12 = new File(projectPath+"/src/main/java/com/springmvc/model/"+line_table_name_first_upper+".java");

				File file13 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_update.jsp");

				File file14 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_readonly.jsp");

				File file16 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension.jsp");

				File file17 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_ext_update.jsp");

				File file20 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension2.jsp");
    
				File file23 = new File(projectPath+"/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_ext_readonly.jsp");

				
				
				if (!file16.exists()) {
					file16.createNewFile();
				}
				fw = new FileWriter(file16.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write("");
				bw.close();	
				
				
				if (!file17.exists()) {
					file17.createNewFile();
				}
				fw = new FileWriter(file17.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write("");
				bw.close();
				
				
				if (!file20.exists()) {
					file20.createNewFile();
				}
				fw = new FileWriter(file20.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write("");
				bw.close();
				
				
				if (!file23.exists()) {
					file23.createNewFile();
				}
				fw = new FileWriter(file23.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write("");
				bw.close();
				
				
				
				
				
				if (!file11.exists()) {
					file11.createNewFile();
				}
				fw = new FileWriter(file11.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(for_line_part.toString());
				bw.close();
				
				
				if (!file12.exists()) {
					file12.createNewFile();
				}
				fw = new FileWriter(file12.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(model_class_for_line.toString());
				bw.close();
				
				
				if (!file13.exists()) {
					file13.createNewFile();
				}
				fw = new FileWriter(file13.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(for_line_part_update.toString());
				bw.close();
				
				
				if (!file14.exists()) {
					file14.createNewFile();
				}
				fw = new FileWriter(file14.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(for_line_part_readonly.toString());
				bw.close();
				
		        controllerName.add(projectPath+"/src/main/java/com/springmvc/model/"+line_table_name_first_upper+".java");

		
	  }//end line
		
		
	/*------------------------------------------zip file code------------------------------------	*/		
				/*
				 * try { //zip file creation String zipname="gb"; String source
				 * =projectPath+"/src/main/java/patchBuilder"; String dest
				 * =projectPath+"/src/main/java/patch_export/"+zipname+".zip";
				 * 
				 * zipFolder(source,dest);
				 * 
				 * //unzip file String src =
				 * projectPath+"/src/main/java/exp_patchzip_wip/gb.zip"; String ds =
				 * projectPath+"/src/main/java/exp_compilefiles_wip/";
				 * 
				 * unzip2(src,ds);
				 * 
				 * } catch (FileNotFoundException e){ e.printStackTrace(); } catch (IOException
				 * e) { e.printStackTrace(); }
				 */
		
		
	        controllerName.add(projectPath+"/src/main/java/com/springmvc/model/"+table_name_first_upper+".java");

            controllerName.add(projectPath+"/src/main/java/com/springmvc/dao/"+dao_name_first_upper+".java");
			
		    controllerName.add(projectPath+"/src/main/java/com/springmvc/dao/"+dao_impl_name_first_upper+".java");
	        controllerName.add(projectPath+"/src/main/java/com/springmvc/service/"+service_name_first_upper+".java");

		    controllerName.add(projectPath+"/src/main/java/com/springmvc/service/"+service_impl_name_first_upper+".java");
            controllerName.add(projectPath+"/src/main/java/com/springmvc/controller/"+controller_name_first_upper+".java");
	        
		    //compiler
		    TomcatCompiler();
		    
		    
		
		
		
		
		

	} catch (Exception e) {
		System.out.println(e);
	}
    }
   //for dev instance type
   
   else{   
    	
        try {
        	//String devPath=environment.getRequiredProperty("devPath");
        	
        	System.out.println("DEV PATH::"+devPath);

        	// java files
            File file1 = new File(devPath+"/WEB-INF/classes/DEV/com/springmvc/controller/"+controller_name_first_upper+".java");

    		File file2 = new File(devPath+"/WEB-INF/classes/DEV/com/springmvc/dao/"+dao_name_first_upper+".java");
    		
    		File file3 = new File(devPath+"/WEB-INF/classes/DEV/com/springmvc/dao/"+dao_impl_name_first_upper+".java");

    		File file4 = new File(devPath+"/WEB-INF/classes/DEV/com/springmvc/service/"+service_name_first_upper+".java");
    		
    		File file5 = new File(devPath+"/WEB-INF/classes/DEV/com/springmvc/service/"+service_impl_name_first_upper+".java");
    		
    		File file7 = new File(devPath+"/WEB-INF/classes/DEV/com/springmvc/model/"+table_name_first_upper+".java");
    		
    		System.out.println("DEV complete PATH::"+file1);
    		
    		
    		//jsp for dev
    		File file6 = new File(devPath+"/WEB-INF/tiles/acemaster/FB_jsp/"+jsp_name+".jsp");
    		
            File file8 = new File(devPath+"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_grid.jsp");
    		
    		File file9 = new File(devPath+"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_update.jsp");
    		
    		File file10 = new File(devPath+"/WEB-INF/tiles/acemaster/FB_jsp/"+table_name_first_upper+"_readonly.jsp");
    		
    		
    		//extension files
    		File file15 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_extension.jsp");

            File file18 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_add_grid.jsp");
    		File file19 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_add_grid2.jsp");
            File file21 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_ext_Update.jsp");
            File file22 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+table_name_lower+"_ext_Readonly.jsp");

    		
    		if(!file1.exists()) 
    		{
    		   file1.createNewFile();
    			
    		}

    		FileWriter fw = new FileWriter(file1.getAbsoluteFile());
    		BufferedWriter bw = new BufferedWriter(fw);
    		bw.write(controller.toString());
    		bw.close();

    		System.out.println("DoneController");

    		
    		if(!file15.exists()) 
    		{
    		   file15.createNewFile();
    			
    		}

    		 fw = new FileWriter(file15.getAbsoluteFile());
    		 bw = new BufferedWriter(fw);
    		bw.write("");
    		bw.close();
    		
    		
    		if(!file18.exists()) 
    		{
    		   file18.createNewFile();
    			
    		}

    		 fw = new FileWriter(file18.getAbsoluteFile());
    		 bw = new BufferedWriter(fw);
    		bw.write("");
    		bw.close();
    		
    		
    		if(!file19.exists()) 
    		{
    		   file19.createNewFile();
    			
    		}

    		 fw = new FileWriter(file19.getAbsoluteFile());
    		 bw = new BufferedWriter(fw);
    		bw.write("");
    		bw.close();
    		
    		
    		
    		if(!file21.exists()) 
    		{
    		   file21.createNewFile();
    			
    		}

    		 fw = new FileWriter(file21.getAbsoluteFile());
    		 bw = new BufferedWriter(fw);
    		bw.write("");
    		bw.close();
    		
    		
    		
    		if(!file22.exists()) 
    		{
    		   file22.createNewFile();
    			
    		}

    		 fw = new FileWriter(file22.getAbsoluteFile());
    		 bw = new BufferedWriter(fw);
    		bw.write("");
    		bw.close();
    		
    		
    		
    		
    		
    		
    		
    		if (!file2.exists()) {
    			file2.createNewFile();
    		}
    		fw = new FileWriter(file2.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(dao.toString());
    		bw.close();

    		System.out.println("Done dao");
         
    		
    		
    		
    		if (!file3.exists()) 
    		{
    			file3.createNewFile();
    		}
    		fw = new FileWriter(file3.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(dao_impl.toString());
    		bw.close();

    		System.out.println("Done dao impl");

    		
    		
    		
    		if (!file4.exists()) {
    			file4.createNewFile();
    		}
    		fw = new FileWriter(file4.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(service.toString());
    		bw.close();

    		System.out.println("Done service");

    		
    		
    		if (!file5.exists()) {
    			file5.createNewFile();
    		}
    		fw = new FileWriter(file5.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(service_impl.toString());
    		bw.close();

    		System.out.println("Done service impl");
    		
    		if (!file6.exists()) {
    			file6.createNewFile();
    		}
    		fw = new FileWriter(file6.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(strContent.toString());
    		bw.close();

    		System.out.println("Done jsp");

    		
    		
    		
    		if (!file7.exists()) {
    			file7.createNewFile();
    		}
    		fw = new FileWriter(file7.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(model_class.toString());
    		bw.close();

    		System.out.println("Done service impl");
    		
    		
    		if (!file8.exists()) {
    			file8.createNewFile();
    		}
    		fw = new FileWriter(file8.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(table_grid_view.toString());
    		bw.close();

    		System.out.println("Done service impl");
    		
    		
    		
    		if (!file9.exists()) {
    			file9.createNewFile();
    		}
    		fw = new FileWriter(file9.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(strContentprefield.toString());
    		bw.close();
    		
    		
    		
    		if (!file10.exists()) {
    			file10.createNewFile();
    		}
    		fw = new FileWriter(file10.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(strContentreadonly.toString());
    		bw.close();
    		
    		
    		      controllerName.add(devPath+"/WEB-INF/classes/DEV/com/springmvc/model/"+table_name_first_upper+".java");
                  controllerName.add(devPath+"/WEB-INF/classes/DEV/com/springmvc/dao/"+ dao_name_first_upper+".java");
				  controllerName.add(devPath+"/WEB-INF/classes/DEV/com/springmvc/dao/"+dao_impl_name_first_upper+".java");
				  controllerName.add(devPath+"/WEB-INF/classes/DEV/com/springmvc/service/"+service_name_first_upper+".java");
				  controllerName.add(devPath+"/WEB-INF/classes/DEV/com/springmvc/service/"+ service_impl_name_first_upper+".java");
				  controllerName.add(devPath+"/WEB-INF/classes/DEV/com/springmvc/controller/"+ controller_name_first_upper+".java");
				 
	        
		    
    		
    		
    		if(line_table_name != null && !line_table_name.isEmpty())
    		{
    			 String line_table_name_lower=line_table_name.toLowerCase();
    		     String line_table_name_first_upper=line_table_name_lower.substring(0, 1).toUpperCase()+line_table_name_lower.substring(1);
    		     String line_table_name_upper=line_table_name.toUpperCase();
    		     
    			    File file11 = new File(devPath+"/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_line_view.jsp");

    		        File file12 = new File(devPath+"/WEB-INF/classes/DEV/com/springmvc/model/"+line_table_name_first_upper+".java");

    				File file13 = new File(devPath+"/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_update.jsp");

    				File file14 = new File(devPath+"/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_readonly.jsp");

    				File file16 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension.jsp");

    				File file17 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_ext_update.jsp");

    				File file20 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_extension2.jsp");
        
    				File file23 = new File(devPath+"/WEB-INF/tiles/acemaster/extpages/"+line_table_name_lower+"_ext_readonly.jsp");

    				
    				
    				if (!file16.exists()) {
    					file16.createNewFile();
    				}
    				fw = new FileWriter(file16.getAbsoluteFile());
    				bw = new BufferedWriter(fw);
    				bw.write("");
    				bw.close();	
    				
    				
    				if (!file17.exists()) {
    					file17.createNewFile();
    				}
    				fw = new FileWriter(file17.getAbsoluteFile());
    				bw = new BufferedWriter(fw);
    				bw.write("");
    				bw.close();
    				
    				
    				if (!file20.exists()) {
    					file20.createNewFile();
    				}
    				fw = new FileWriter(file20.getAbsoluteFile());
    				bw = new BufferedWriter(fw);
    				bw.write("");
    				bw.close();
    				
    				
    				if (!file23.exists()) {
    					file23.createNewFile();
    				}
    				fw = new FileWriter(file23.getAbsoluteFile());
    				bw = new BufferedWriter(fw);
    				bw.write("");
    				bw.close();
    				
    				
    				
    				
    				
    		if (!file11.exists()) {
    			file11.createNewFile();
    		}
    		fw = new FileWriter(file11.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(for_line_part.toString());
    		bw.close();
    		
    		
    		if (!file12.exists()) {
    			file12.createNewFile();
    		}
    		fw = new FileWriter(file12.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(model_class_for_line.toString());
    		bw.close();
    		
    		
    		if (!file13.exists()) {
    			file13.createNewFile();
    		}
    		fw = new FileWriter(file13.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(for_line_part_update.toString());
    		bw.close();
    		
    		
    		if (!file14.exists()) {
    			file14.createNewFile();
    		}
    		fw = new FileWriter(file14.getAbsoluteFile());
    		bw = new BufferedWriter(fw);
    		bw.write(for_line_part_readonly.toString());
    		bw.close();
    		
    		 controllerName.add(devPath+"/WEB-INF/classes/DEV/com/springmvc/model/"+line_table_name_first_upper+".java");
    		
    		}
    		
    		//compiler
		   TomcatCompilerDev(devPath);	

    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	
    	
        
        
        
        /*try 
        {
        	
        	    File fileToCopy = new File("/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/line_view.jsp");
        	    File newFile = new File("c:/temp/testcopied.txt");
        	 
        	    FileUtils.copyFile(fileToCopy, newFile);
            // let's create a ZIP file to write data
            FileOutputStream fos = new FileOutputStream("F:/Ganesh/ganeshsample.zip");
            ZipOutputStream zipOS = new ZipOutputStream(fos);

            String file111 = "/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/line_view.jsp";
            String file222 = "/src/main/java/com/springmvc/model/gah.java";
            //String file333 = "targetrr/apache.txt";
            //String file444 = "java.txt";
            
            //File file111 = new File("/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_line_view.jsp");

	        //File file222 = new File("/src/main/java/com/springmvc/model/"+line_table_name_first_upper+".java");

			//File file333 = new File("/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_update.jsp");

			//File file444 = new File("/src/main/webapp/WEB-INF/tiles/acemaster/FB_jsp/"+line_table_name_first_upper+"_readonly.jsp");


            writeToZipFile(file111, zipOS);
            writeToZipFile(file222, zipOS);
            //writeToZipFile(file333, zipOS);
            //writeToZipFile(file444, zipOS);

            zipOS.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    	
    	
    }
    
    
   /*System.out.println("unzip part");
   String zipFilePath = "/src/main/java/patch_export/ganeshsample.zip";
   String destDirectory ="F:/Ganesh/cvs";
   //UnzipUtility unzipper = new UnzipUtility();
   try {
       unzip(zipFilePath, destDirectory);
   } catch (Exception ex) {
       ex.printStackTrace();
   }*/
   
   
   
   /*String zipFilePath = "/src/main/java/patch_export/ganeshsample.zip";
   
   String destDir = "F:/Ganesh/edit";
   
   unzip2(zipFilePath, destDir);*/
   
   
   
   

   
   
   
   
   
   
   
   return  new ModelAndView("redirect:fb_header_grid");
   
   
	
	
	
}


   public static void writeToZipFile(String path,ZipOutputStream zipStream)
        throws FileNotFoundException, IOException 
     {

		    System.out.println("Writing file : '" + path + "' to zip file");
		
		    File aFile = new File(path);
		    FileInputStream fis = new FileInputStream(aFile);
		    ZipEntry zipEntry = new ZipEntry(path);
		    zipStream.putNextEntry(zipEntry);
		
		    byte[] bytes = new byte[1024];
		    int length;
		    while ((length = fis.read(bytes)) >= 0) 
		    {
		        zipStream.write(bytes, 0, length);
		    }
		
		    zipStream.closeEntry();
		    fis.close();
       }
   
   /*
   
   public void unzip(String zipFilePath, String destDirectory) throws IOException
   {
	       File destDir = new File(destDirectory);
	       if (!destDir.exists()) 
	       {
	           destDir.mkdir();
	       }
	       ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
	       ZipEntry entry = zipIn.getNextEntry();
	       // iterates over entries in the zip file
	       while (entry != null) {
	           String filePath = destDirectory + File.separator + entry.getName();
	           if (!entry.isDirectory()) {
	               // if the entry is a file, extracts it
	               extractFile(zipIn, filePath);
	           } else {
	               // if the entry is a directory, make the directory
	               File dir = new File(filePath);
	               dir.mkdir();
	           }
	           zipIn.closeEntry();
	           entry = zipIn.getNextEntry();
	       }
	       zipIn.close();
   }

   
   private void extractFile(ZipInputStream zipIn, String filePath) throws IOException 
   {
	       final int BUFFER_SIZE = 1024;
	       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
	       byte[] bytesIn = new byte[BUFFER_SIZE];
	       int read = 0;
	       while ((read = zipIn.read(bytesIn)) != -1) {
	       bos.write(bytesIn, 0, read);
    }
           bos.close();
   }
   */
   
   private static void unzip2(String zipFilePath, String destDir) {
       File dir = new File(destDir);
       // create output directory if it doesn't exist
       if(!dir.exists()) dir.mkdirs();
       FileInputStream fis;
       
       //buffer for read and write data to file
       byte[] buffer = new byte[1024];
       try 
       {
           fis = new FileInputStream(zipFilePath);
           ZipInputStream zis = new ZipInputStream(fis);
           ZipEntry ze = zis.getNextEntry();
           
           while(ze != null)
           {
               String fileName = ze.getName();
               
               File newFile = new File(destDir+fileName);
               
               newFile.setWritable(true,false);
               
               System.out.println("new file ::"+newFile+" \nfile name::"+fileName);
               
               System.out.println("Unzipping to "+newFile.getAbsolutePath());
               //create directories for sub directories in zip
               new File(newFile.getParent()).mkdirs();
               FileOutputStream fos = new FileOutputStream(newFile);
               int len;
               while ((len = zis.read(buffer)) > 0) 
               {
                fos.write(buffer, 0, len);
               }
               fos.close();
               //close this ZipEntry
               zis.closeEntry();
               ze = zis.getNextEntry();
           }
           //close last ZipEntry
           zis.closeEntry();
           zis.close();
           fis.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   
		   public void zipFolder(String srcFolder,String destZipFile) throws Exception 
		   {
				ZipOutputStream zip = null;
				FileOutputStream fileWriter = null;
				
				fileWriter = new FileOutputStream(destZipFile);
				zip = new ZipOutputStream(fileWriter);
				
				addFolderToZip("", srcFolder, zip);
				
				zip.flush();
				zip.close();
		    }

  
   
			   private void addFileToZip(String path, String srcFile,ZipOutputStream zip) throws Exception 
			   {
			
				File folder = new File(srcFile);
				if (folder.isDirectory()) 
				{
				  addFolderToZip(path, srcFile, zip);
				} else 
				{
					byte[] buf = new byte[1024];
					int len;
					FileInputStream in = new FileInputStream(srcFile);
					zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
					while ((len = in.read(buf)) > 0) 
					{
					  zip.write(buf, 0, len);
				    }
			    }
			   }
			
			private void addFolderToZip(String path, String srcFolder,ZipOutputStream zip) throws Exception 
			{
			File folder = new File(srcFolder);
			
			for (String fileName : folder.list()) {
			if (path.equals("")) 
			{
			  addFileToZip(folder.getName(), srcFolder + "/" + fileName,zip);
			} else 
			{
			  addFileToZip(path + "/" + folder.getName(),
			   srcFolder + "/" + fileName, zip);
			}
		}
			  
			   
		}	
			
			
			public void unzip(String srcZipFile, String destDir) 
			{
				 byte[] buffer = new byte[1024];
				 
				 try {
				 ZipInputStream zis = new ZipInputStream(new FileInputStream(srcZipFile));
				 ZipEntry zipEntry = zis.getNextEntry();
				 while (zipEntry != null) {
				 String fileName = zipEntry.getName();
				 File newFile = new File(destDir + File.separator + fileName);
				 FileOutputStream fos = new FileOutputStream(newFile);
				 int len;
				 while ((len = zis.read(buffer)) > 0) {
				 fos.write(buffer, 0, len);
				 }
				 fos.close();
				 //This is required to make sure the unzipped files keep the last modified 
				 //time same as of the time they were compressed.
				 newFile.setLastModified(zipEntry.getTime());
				 zipEntry = zis.getNextEntry();
				 }
				 zis.closeEntry();
				 zis.close();
				 } catch (IOException ex) {
				 ex.printStackTrace();
				 }
			}	
			
			
			 //compilation code
			
			
			
			
			//File f = new File("F:\\Ganesh\\MyProject\\REAL_NET_GB1\\src\\main\\java\\patchBuilder");
			//ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
			
			
			  /*for (String file : names) 
			  {
		         System.out.println("\nNumber = " + file);
		      }*/
			
			public void TomcatCompiler()
			{
				String changedir = "pwd";
				
				System.out.println("ganesh bute call compile method"+controllerName);
			 	
				for(int i=0;i<controllerName.size();i++)
				{		
				
					 System.out.println("under method");
					 
				     String moveToclasses = request.getServletContext().getRealPath("/WEB-INF/classes");
				
				     System.out.println("move to::"+moveToclasses);
				
					 String librarypath = request.getServletContext().getRealPath("/WEB-INF/lib");
					
					 System.out.println("library path::"+librarypath);
					
					//String Localcompile = "javac -classpath /home/user2/Downloads/tomcat7/webapps/BassPlus/WEB-INF/lib/*:. -d "+moveToclasses+" /home/user2/Documents/workspace-sts-3.7.1.RELEASE/SpringHibernateExample/src/main/java/com/springmvc/"+controllerName.get(i)+"";
					
					//String ServerCompile="javac -classpath "+environment.getRequiredProperty("CpClasses") + ":" + environment.getRequiredProperty("libjar")+" -d "+moveToclasses+" "+environment.getRequiredProperty("ControllerPath")+controllerName.get(i)+"";
					
					 
				//	System.out.println("check envirment path::"+environment.getRequiredProperty("ControllerPath")+controllerName.get(i));
					 
					 
					//String ServerCompile="javac -classpath "+moveToclasses+";" + librarypath +"/*;."+" -d "+moveToclasses+" "
					        //            +environment.getRequiredProperty("ControllerPath")+names.get(i)+"";
					
					String ServerCompile = "javac -classpath " + moveToclasses + environment.getRequiredProperty("tomcatcmdPath") + librarypath + environment.getRequiredProperty("controllerNamePath") + "*" +environment.getRequiredProperty("tomcatcmdPath") + "." + " -d "
							+ moveToclasses + " "+controllerName.get(i)
							+ "";
					
			        System.out.println("path="+moveToclasses+"\ncommand="+ServerCompile);
			        Process proc = null;
					String line = "";
					
					try {
	                  
						proc = Runtime.getRuntime().exec(ServerCompile);
					    System.out.println("compile done");
				    // proc = Runtime.getRuntime().exec(changedir);
			        // Read the output
			
			        BufferedReader reader =   new BufferedReader(new InputStreamReader(proc.getInputStream()));
			
			        while((line = reader.readLine()) != null) 
			        {
			            System.out.print(line + "\n");
			        }
	                proc.waitFor();
					} catch (Exception e)
					{
						 e.printStackTrace();
					}
					
					
					//---------------------------------------------------------------------------------------------------*/
//					 Runtime runtime = Runtime.getRuntime();
//					 String restarttomcat = "/home/omfys/Desktop/apache-tomcat-8.0.28/bin/startup.sh";
//					 try {
//						 System.out.print("Into the tomcat run code");
//					     Process p1 = runtime.exec(restarttomcat);
//					     InputStream is = p1.getInputStream();
//					     int i1 = 0;
//					     while( (i1 = is.read() ) != -1) {
//					         System.out.print((char)i1);
//					     }
//					 } catch(IOException ioException) {
//					     System.out.println(ioException.getMessage());
//					 }
					  	
             }//for loop	
				
			}//method
			
			
			void TomcatCompilerDev(String devPath)
			{
				String changedir = "pwd";
				System.out.println("ganesh bute call compile method"+controllerName+"\n");
			 	
				for(int i=0;i<controllerName.size();i++)
				{		
					// String projectPath=rn_project_setup_dao.getProjectPath();
				     System.out.println("under for loop");
				    // F:\Ganesh\MyProject\REAL_NET_GB1\src\main\java\compilefiles
					 String moveToclasses=devPath+"/WEB-INF/classes";
				     //String librarypath="F:/Ganesh/CreatedProjectForExport/"+project_name+"/WEB-INF/lib";
				     //String librarypath = request.getServletContext().getRealPath("/WEB-INF/lib");
				     String librarypath = devPath+"/WEB-INF/lib";
				     System.out.println("move to::"+moveToclasses+"\nlibrary path::"+librarypath);
				     System.out.println("check envirment path::"+controllerName.get(i));
					 
					// String ServerCompile = "javac -classpath " + moveToclasses + environment.getRequiredProperty("tomcatcmdPath") + librarypath + environment.getRequiredProperty("controllerNamePath") + "*" +environment.getRequiredProperty("tomcatcmdPath") + "." + " -d "
						///	+ moveToclasses + " "+controllerName.get(i)
						//	+ "";
				     
				     
				     
				     
				     String ServerCompile = "javac -classpath \"" + moveToclasses+"\""+ environment.getRequiredProperty("tomcatcmdPath") +"\""+ librarypath+"\"" + environment.getRequiredProperty("controllerNamePath") + "*" +environment.getRequiredProperty("tomcatcmdPath") + "." + " -d "
								+ "\""+moveToclasses+"\"" + " \""+controllerName.get(i)+"\""
								+ "";
				     
				     
				     
					// String ServerCompile = "javac -cp \""+moveToclasses+":\"\""+librarypath + "/" + "*:\"" + " -d \""+ moveToclasses + "\" "+controllerName.get(i)
								//+ "";
					 
					 
					 System.out.println("path="+moveToclasses+"\ncommand="+ServerCompile);
			         Process proc = null;
					 String line = "";
					
					 try {
		                proc = Runtime.getRuntime().exec(ServerCompile);
					    System.out.println("compile done");
				        BufferedReader reader =   new BufferedReader(new InputStreamReader(proc.getInputStream()));
			
			        while((line = reader.readLine()) != null) 
			        {
			        	System.out.print(line + "\n");
			        }
		                proc.waitFor();
					} catch (Exception e)
					{
						 e.printStackTrace();
					}
			      }
			   }		
			
			
			

    
}
	

