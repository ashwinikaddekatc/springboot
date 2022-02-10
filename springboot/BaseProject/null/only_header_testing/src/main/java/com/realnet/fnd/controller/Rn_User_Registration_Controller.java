package com.realnet.fnd.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.realnet.fnd.dao.Rn_Distributor_Details_Dao;
import com.realnet.fnd.dao.Rn_Instance_Type_Dao;
import com.realnet.fnd.dao.Rn_Project_Setup_Dao;
import com.realnet.fnd.dao.Rn_User_Registration_Dao;
import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Instance_Type;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Header;
import com.realnet.fnd.model.Rn_Two_Jsp;
import com.realnet.fnd.model.Rn_User_Assignment;
import com.realnet.fnd.model.Rn_User_Pref;
import com.realnet.fnd.model.Rn_User_Role_Assignment;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.service.Rn_User_Registration_Service;
import com.realnet.rb.model.Rn_Report_Group_Header;
import com.realnet.wfb.model.Rn_Fb_Header;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
public class Rn_User_Registration_Controller {

	

	@Autowired
	private Rn_User_Registration_Service rn_user_registration_service; 
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private Rn_User_Registration_Dao rn_user_registration_dao;

	@Autowired
	private Rn_Distributor_Details_Dao rn_distributor_details_dao;
	
	@Autowired
	private Environment environment;

	@Autowired
	private Rn_Instance_Type_Dao rn_instance_type_dao;

	@Autowired
	private Rn_Project_Setup_Dao rn_project_setup_dao;

	
	public static final String ALGORITHM = "AES";
	public static final String KEY = "1Hbfh667adfDEJ78";

	
	/*------user registration entry form--------*/
	@RequestMapping("/rn_user_registration_entryform")
	public ModelAndView userRegistration(ModelMap map, Model model, HttpServletRequest request) {
		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {
			System.out.println("autofill");
			Rn_Users userreg = new Rn_Users();
			map.addAttribute("userreg", userreg);
		}
		return new ModelAndView("Rn_User_Registration_EntryForm");
	}
	
	
	/*------new user registration --------*/
	@Transactional
	@RequestMapping(value = "/rn_new_registration_submit", method = RequestMethod.POST)
	public ModelAndView newUserRegistration(@ModelAttribute Rn_Users user, BindingResult resultKoel_user, Model model,
			ModelMap map, HttpServletRequest request) {

		int user_id = (Integer) request.getSession().getAttribute("userid");

		System.out.println("user " + user.getUser_id());

		Date start_date = null;
		try {
			start_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("start_date"));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Date end_date = null;
		try {
			end_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("end_date"));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		String user_name = request.getParameter("user_name");

		String password = request.getParameter("password");

		Key key = null;
		Cipher cipher = null;
		try {
			key = generateKey();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			cipher = Cipher.getInstance(Rn_User_Registration_Controller.ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] encryptedByteValue = null;
		try {
			encryptedByteValue = cipher.doFinal(password.getBytes("utf-8"));
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String encryptedValue = new BASE64Encoder().encode(encryptedByteValue);

		System.out.println("encryptedValue === " + encryptedValue);

		user.setPassword(encryptedValue);

		String user_ststus = request.getParameter("user_status");
		if (user_ststus == null) {
			user_ststus = "N";
		}
		String first_name = request.getParameter("first_name");
		String middle_name = request.getParameter("middle_name");
		String last_name = request.getParameter("last_name");
		String contact_number = request.getParameter("contact_number");
		String email_address = request.getParameter("email_address");

		user.setUser_name(user_name);
		user.setFirst_name(first_name);
		user.setMiddle_name(middle_name);
		user.setLast_name(last_name);
		user.setContact_number(contact_number);
		user.setEmail_address(email_address);

		user.setStart_date(start_date);
		user.setEnd_date(end_date);
		user.setCreated_by(user_id);
		user.setLast_updated_by(user_id);
		user.setIs_active(user_ststus);

		int id = 0;
		if (request.getParameter("user_id") != "") {
			id = Integer.parseInt(request.getParameter("user_id"));
		}

		int newuserId = rn_user_registration_service.CreateUser(user, id);
		System.out.println(newuserId);

		//go to role access management 
		//end user registration & updation part

		String test = request.getParameter("test");
		String name = null;

		int assgid = 0, assgroleid = 0;

		if (test.equals("N")) {
			System.out.println("ssssssssssssssssssssssssssssssssssss");

			Rn_User_Assignment assg = new Rn_User_Assignment();
			assg.setUser_id(newuserId);
			hibernateTemplate.saveOrUpdate(assg);
			assgid = assg.getUser_assignment_id();

			Rn_User_Role_Assignment assg1 = new Rn_User_Role_Assignment();
			assg1.setUser_id(newuserId);
			assg1.setRole("default");
			assg1.setEnable_flag("Y");
			hibernateTemplate.saveOrUpdate(assg1);
			assgroleid = assg1.getUser_role_assignment_id();

		}

		ArrayList<Rn_User_Assignment> user_List_ass = (ArrayList<Rn_User_Assignment>) hibernateTemplate
				.find("from User_Assignment where user_id=?", newuserId);
		if (user_List_ass.size() != 0) {
			int role_id = user_List_ass.get(0).getUser_assignment_id();
			int menuid = user_List_ass.get(0).getMenu_group_id();
			int reportid = user_List_ass.get(0).getReport_group_id();
			String dash = user_List_ass.get(0).getAttribute1();

			if (role_id != 0) {
				model.addAttribute("role_id", role_id);
			}
			if (menuid != 0) {
				ArrayList<Rn_Menu_Group_Header> menu_List_ass = (ArrayList<Rn_Menu_Group_Header>) hibernateTemplate
						.find("from MenuGroupHeader where menu_group_header_id=?", menuid);
				String menu_name = menu_List_ass.get(0).getMenu_name();

				model.addAttribute("menu_name", menu_name);
				model.addAttribute("menuid", menuid);
			}
			if (reportid != 0) {
				ArrayList<Rn_Report_Group_Header> rep_List_ass = (ArrayList<Rn_Report_Group_Header>) hibernateTemplate
						.find("from ReportGroupHeader where report_group_header_id=?", reportid);
				String report_name = rep_List_ass.get(0).getReport_name();

				model.addAttribute("report_name", report_name);
				model.addAttribute("reportid", reportid);
			}
			if (dash != null) {
				model.addAttribute("dash", dash);
			}

			List<Rn_Lookup_Autofill> user_List_role = rn_user_registration_service.findrole(newuserId);

			model.addAttribute("user_List_role", user_List_role);
		}

		ArrayList<Rn_Users> user_List = (ArrayList<Rn_Users>) hibernateTemplate.find("from Koel_Users where user_id=?",
				newuserId);

		name = user_List.get(0).getUser_name();
		user_id = user_List.get(0).getUser_id();

		model.addAttribute("name", name);
		model.addAttribute("userid", user_id);

		return new ModelAndView("Rn_roles_menu_access");
	}

	
	/*-------------------find user---------------------------*/
	@RequestMapping(value = "/rn_find_user", method = RequestMethod.GET)
	public void findUser(@RequestParam(value = "UserName") String user_name, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			System.out.println("in controller");

			List<Rn_Users> userlist = new ArrayList<Rn_Users>();
			String json = null;
			System.out.println("user name " + user_name);

			userlist = rn_user_registration_service.SearchUser(user_name); //koel user table not exist .Nil

			Rn_User_Pref usr_pref = new Rn_User_Pref();

			for (int i = 0; i < userlist.size(); i++)

			{

				Date start = userlist.get(0).getStart_date();
				Date end = userlist.get(0).getEnd_date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				String format = formatter.format(start);
				String format1 = null;
				if (end != null) {
					format1 = formatter.format(end);
				}
				usr_pref.setUser_id(userlist.get(0).getUser_id());

				usr_pref.setName(userlist.get(0).getUser_name());

				Key key = null;
				try {
					key = generateKey();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Cipher cipher = null;
				try {
					cipher = Cipher.getInstance(Rn_Forgot_Password_Controller.ALGORITHM);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					cipher.init(Cipher.DECRYPT_MODE, key);
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] decryptedValue64 = null;
				try {
					decryptedValue64 = new BASE64Decoder().decodeBuffer(userlist.get(0).getPassword());
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				byte[] decryptedByteValue = null;
				try {
					decryptedByteValue = cipher.doFinal(decryptedValue64);
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String decryptedValue = null;
				try {
					decryptedValue = new String(decryptedByteValue, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				usr_pref.setPassword(decryptedValue);
				usr_pref.setUserstatus(userlist.get(0).getIs_active());

				usr_pref.setStart_date(format);
				if (end != null) {
					usr_pref.setEnd_date(format1);
				}

				usr_pref.setFirst_name(userlist.get(0).getFirst_name());
				usr_pref.setMiddle_name(userlist.get(0).getMiddle_name());
				usr_pref.setLast_name(userlist.get(0).getLast_name());
				usr_pref.setContact_number(userlist.get(0).getContact_number());
				usr_pref.setEmail_address(userlist.get(0).getEmail_address());
				// usr_pref.setUserstatus(userlist.get(0).getIs_active());
				System.out.println("in controller : status" + userlist.get(0).getIs_active());

			}

			json = new Gson().toJson(usr_pref);

			response.setContentType("application/json");
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/*-----------------password encription----------*/
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(Rn_User_Registration_Controller.KEY.getBytes(), Rn_User_Registration_Controller.ALGORITHM);
		return key;
	}

	 
	 
	/*------------------ user registration submit ------------------*/
    @Transactional
	@RequestMapping(value = "/rn_user_registration_submit", method = RequestMethod.POST)
	public ModelAndView saveServiceRequest2(@ModelAttribute Rn_Users Koel_user,BindingResult resultKoel_user ,
												Model model,ModelMap map, HttpServletRequest request)  
	{	 	    
	    System.out.println("this is controller-------------");
		int user_id = (Integer) request.getSession().getAttribute("userid");
				
		System.out.println("user "+ Koel_user.getUser_id());
		
		 		

			String user_name = request.getParameter("user_name");
			String password = request.getParameter("password");	
			String user_ststus = request.getParameter("user_status");			
			if (user_ststus==null)
			{
				user_ststus="N";
			}
			String first_name = request.getParameter("first_name");
			String middle_name = request.getParameter("middle_name");
			String last_name = request.getParameter("last_name");
			String contact_number = request.getParameter("contact_number");
			String email_address = request.getParameter("email_address");
			
			
				
			Koel_user.setUser_name(user_name);
			Koel_user.setPassword(password);
			Koel_user.setFirst_name(first_name);
			Koel_user.setMiddle_name(middle_name);
			Koel_user.setLast_name(last_name);
			Koel_user.setContact_number(contact_number);
			Koel_user.setEmail_address(email_address);
			Koel_user.setCreated_by(user_id);
			Koel_user.setLast_updated_by(user_id);
	        Koel_user.setIs_active(user_ststus);
	        
	        int id=0;
	        
	        
			int newuserId = rn_user_registration_service.CreateUser(Koel_user, id);
			System.out.println("new user id------------>"+newuserId);
			
		  return new ModelAndView("redirect:Rn_distributor_details1");
	}
 
	
	/*-------------------extension details-------------------------------*/
	@RequestMapping(value = "/rn_extension_details")
	public ModelAndView extensionDetails(ModelAndView model) throws IOException {

		List<Rn_Ext_Fields> ext_userlist = rn_user_registration_dao.ext_userlist(); //rn_user_registration_dao

		model.addObject("ext_userlist", ext_userlist);
		model.setViewName("Rn_extension_details");

		return model;
	}
	
	
	/*-------------------form builder extension details-------------------------------*/
	@RequestMapping(value = "/rn_form_builder_extension")
	public ModelAndView formExtension(ModelAndView model, HttpServletRequest request) throws IOException {

		HttpSession session = request.getSession(false);
		String f_code = (String) session.getAttribute("form_code");

		List<Rn_Ext_Fields> ext_userlist = rn_user_registration_dao.ext_userlist1(f_code);

		model.addObject("ext_userlist", ext_userlist);

		model.setViewName("Rn_form_builder_extension");

		return model;
	}

	HttpSession hs = null;

	
	/*-------------------create view with error message-------------------------------*/
	@RequestMapping(value = "/rn_create_view", method = RequestMethod.POST)
	public ModelAndView createView(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String message = "view created";
		// hs = request.getSession();
		String str = (String) hs.getAttribute("view_name");
		System.out.println("view is=" + str);

		return new ModelAndView(str, "errormessage", message);

	}
	
	
	/*-------------------write view-------------------------------*/
	@RequestMapping(value = "/rn_write_view", method = RequestMethod.GET)
	public ModelAndView writeView(Rn_Menu_Header menu_header, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {

		String projectPath = rn_project_setup_dao.getProjectPath();
		System.out.println(projectPath + " :: is Project Path");

		String view_name = "insertField";

		StringBuilder strContent = new StringBuilder();
		
		StringBuilder grid_part = new StringBuilder();
		StringBuilder grid_part2 = new StringBuilder();
		StringBuilder dropdown_script = new StringBuilder();

		StringBuilder importsecton = new StringBuilder();
		StringBuilder headsection = new StringBuilder();
		StringBuilder form = new StringBuilder();
		StringBuilder calculated_script = new StringBuilder();
		StringBuilder field3_extPart = new StringBuilder();
		StringBuilder inserfield_extUpdate = new StringBuilder();
		StringBuilder inserfield_extReadonly = new StringBuilder();

		StringBuilder autocomplete_script = new StringBuilder();

		StringBuilder dependent_dropdown = new StringBuilder();

		StringBuilder for_line_part = new StringBuilder();

		StringBuilder line_script = new StringBuilder();

		StringBuilder radio_code = new StringBuilder();

		StringBuilder sb = new StringBuilder();

		StringBuilder radio_code2 = new StringBuilder();

		StringBuilder radiobutton = new StringBuilder();

		List<Rn_Ext_Fields> ext_user = rn_user_registration_dao.ext_userlist_non_radio();

		List<Rn_Ext_Fields> ext_user_radio = rn_user_registration_dao.ext_userlist_radio();

		List<Rn_Two_Jsp> rn_userlist = rn_distributor_details_dao.gridview_three();

		for_line_part.append("<div class=\"table-header\" style=\"margin-top: 30px;\">Section</div>" + "<div>"
				+ "<% int n=0; %>" + "<input type=\"hidden\" id=\"rowcount1\" name=\"rowcount1\">"
				+ "<input type=\"hidden\" id=\"delrow1\" name=\"delrow1\" value=\"\">"
				+ "<table id=\"dynamic-table1\" class=\"table table-striped table-bordered table-hover\">" + "<thead>"
				+ "<tr>"

				+ "<th>" + "<center>Select</center>" + "</th>"

				+ "<th>" + "<center>Name</center>" + "</th>"

				+ "</tr>" + "</thead>" + "<tbody>"

				+ "</tbody>" + "</table>"

				+ "<input type=\"button\" class=\"btn btn-md btn-success\""
				+ "style=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%;"
				+ "value=\"Add Row\" onclick=\"AddRow()\">"

				+ "<input type=\"button\" class=\"btn btn-md btn-success\""
				+ "style=\"background-color: #87b87f; border-color: #87b87f; margin-top: 1%; margin-bottom: 1%; font-size: auto;\""
				+ "value=\"Delete Row\" onclick=\"del()\">" + "</div>");

		line_script.append("<script language=\"javascript\">" + "function AddRow()  "
				+ "$('#rowcount1').val($('#dynamic-table1 tr').length-1);" + "var count = $('#rowcount1').val();"
				+ "$.ajax({" + "url: '${pageContext.request.contextPath}/loadreports'," + "data: ({name : 'name'}),"
				+ "success: function(data) {" + "var select = $('#name'+count);" + "select.find('option').remove();"
				+ "$.each(data, function(index, value) {" + "$('<option>').val(value).text(value).appendTo(select);"
				+ "});" + "}" + "});"

				+ "$('.Deleterow').click(function() { " + "var index = $('.Deleterow').index(this)+1;"
				+ "$('#delrow1').val(index);" + "});" + "}" + "</script>"

				+ "<script>" + "function del(){" + "var index=$('#delrow1').val();" + "if(index!= \"\")" + "{"
				+ "document.getElementById(\"dynamic-table1\").deleteRow(index);" + "$('#delrow1').val(\"\");" + "}"
				+ "}" + "</script>");

		importsecton
				.append("\n<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>"

						+ "\n<%@ page import=\"java.util.ArrayList\"%>" + "\n<%@ page import=\"java.util.Date\"%>"
						+ "\n<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>"
						+ "\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/functions\" prefix=\"fn\"%>"
						+ "\n<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>");

		headsection.append("\n<html lang=\"en\">\n<head>"
				+ "\n<meta http-equiv=\"X-UA-Compatible\"  content=\"IE=edge,chrome=1\">"
				+ "\n<meta charset=\"utf-8\" />" + "\n<title>Realnet Oil Engines Ltd</title>"
				+ "\n<meta name=\"description\" content=\"Common form elements and layouts\" />"
				+ "\n<meta name=\"viewport\""
				+ "\ncontent=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" />"
				+ "\n<!-- bootstrap & fontawesome -->" + "\n<link rel=\"stylesheet\""
				+ "\n href=\"<c:url value='/resources/assets/css/bootstrap.min.css'/>\" />"
				+ "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/font-awesome/4.2.0/css/font-awesome.min.css'/>\" />"
				+ "\n<!-- page specific plugin styles -->" + "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/css/jquery-ui.custom.min.css' />\" />"
				+ "\n<link rel=\"stylesheet\"" + "\nhref=\"<c:url value='/resources/assets/css/chosen.min.css' />\" />"
				+ "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/css/datepicker.min.css'/>\" />"
				+ "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/css/bootstrap-timepicker.min.css'/>\" />"
				+ "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/css/daterangepicker.min.css' />\" />"
				+ "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/css/bootstrap-datetimepicker.min.css'/>\" />"
				+ "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/css/colorpicker.min.css' />\" />" + "\n<!-- text fonts -->"
				+ "\n<link rel=\"stylesheet\""
				+ "\n\nhref=\"<c:url value='/resources/assets/fonts/fonts.googleapis.com.css' />\" />"
				+ "\n<!-- ace styles -->" + "\n<link rel=\"stylesheet\""
				+ "\nhref=\"<c:url value='/resources/assets/css/ace.min.css\" class=\"ace-main-stylesheet\" id=\"main-ace-style' />\" />"
				+ "\n<!-- inline styles related to this page -->"

				+ "\n<!-- ace settings handler -->"
				+ "\n<script src=\"<c:url value='/resources/assets/js/ace-extra.min.js'/>\""
				+ "\ntype=\"text/javascript\"></script>"

				+ "\n<script>" + "\nsubmitForms = function()" + "\n{" + "\ndocument.forms[\"userdetails1\"].submit();"
				+ "\ndocument.forms[\"userdetails2\"].submit();" + "}" + "\n</script>"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css\">"

				+ " \n</head>");

		// -------------------llop for radio
		// buttons--------------------------------------
		for (int i = 0; i < ext_user_radio.size(); i++) {
			System.out.println("-------radio button code-------------\n");

			String field_name4 = ext_user_radio.get(i).getField_name();
			String mapping4 = ext_user_radio.get(i).getMapping();
			String data_type4 = ext_user_radio.get(i).getData_type();

			System.out.println("-------data type value in radio button---------" + data_type4);

			String mandatory = ext_user_radio.get(i).getMandatory();
			String hidden = ext_user_radio.get(i).getHidden();
			String readonly = ext_user_radio.get(i).getReadonly();
			String dependent = ext_user_radio.get(i).getDependent();

			String calculated_field = ext_user_radio.get(i).getCalculated_field();

			String ext_dd_id = ext_user_radio.get(i).getExt_dd_id();
			String sp_name = ext_user_radio.get(i).getSp_name();

			String sp_name_for_auto = ext_user_radio.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user_radio.get(i).getExt_auto_id();

			String ext_dependent_id = ext_user_radio.get(i).getExt_dependent_id();
			String dependent_on = ext_user_radio.get(i).getDependent_on();
			String dependent_sp = ext_user_radio.get(i).getDependent_sp();

			String radio = ext_user_radio.get(i).getRadio();

			if (data_type4.equals("radiobutton"))

			{
				String radio_option = ext_user_radio.get(i).getRadio_option();
				System.out.println("--------ganesh bute under radio-----------------" + radio_option);

				System.out.println("---options--" + radio_option);

				String[] split = radio_option.split(",");

				System.out.println("-----after split--------" + split);

				int j = 0;
				for (String name : split) {

					// System.out.println("-----value of j-----"+j);
					// for(int j=0;j<split.length;j++)
					// {

					/*
					 * radio_code.append("\n<div style=\"margin-left: 10%;\" class=\"form-check\">"
					 * +"\n<input class=\"form-check-input\" id=\""+mapping4+"\"");
					 * if(hidden.equals("Y")) { radio_code.append( " type=\"hidden\">"); }else {
					 * radio_code.append( " type=\"radio\">"); }
					 * 
					 * radio_code.
					 * append("\n<label class=\"form-check-label\" for=\"materialGroupExample2\">"
					 * +name+"</label>" +"\n</div>");
					 */

					if (j == 0) {
						radio_code.append("\n<div style=\"margin-left: 22%; \" class=\"form-check\">"
								+ "\n<input class=\"ace ace-switch ace-switch-6\" id=\"" + mapping4 + "\" name=\""
								+ mapping4 + "\" ");

						if (hidden.equals("Y")) {
							radio_code.append("type=\"hiden\"/>");
						} else {
							radio_code.append("type=\"radio\"/>");
						}

						radio_code.append("\n<span class=\"lbl\"></span>" + "\n<label class=\"form-check-label\">"
								+ name + "</label>" + "\n</div>");

					}

					if (j > 0 && j <= 2) {

						radio_code.append("\n<td>\n<div style=\"margin-left: 22%; \" class=\"form-check\">"
								+ "\n<input class=\"ace ace-switch ace-switch-6\" id=\"" + mapping4 + "\" name=\""
								+ mapping4 + "\" ");

						if (hidden.equals("Y")) {
							radio_code.append("type=\"hiden\"/>");
						} else {
							radio_code.append("type=\"radio\"/>");
						}

						radio_code.append("\n<span class=\"lbl\"></span>" + "\n<label class=\"form-check-label\">"
								+ name + "</label>" + "\n</div>\n</td>");

					}

					/*
					 * if(j==2) {
					 * 
					 * radio_code.
					 * append("\n<td>\n<div style=\"margin-left: 22%; \" class=\"form-check\">"
					 * +"\n<input class=\"ace ace-switch ace-switch-6\" id=\""+mapping4+"\" name=\""
					 * +mapping4+"\" ");
					 * 
					 * if(hidden.equals("Y")) { radio_code.append( "type=\"hiden\"/>"); }else {
					 * radio_code.append( "type=\"radio\"/>"); }
					 * 
					 * radio_code.append("\n<span class=\"lbl\"></span>"
					 * +"\n<label class=\"form-check-label\">"+name+"</label>" +"\n</div>\n</td>" );
					 * 
					 * }
					 */

					/*
					 * radio_code.append("<tr>");
					 * 
					 * if(j>2 && j<=5) {
					 * 
					 * radio_code.
					 * append("\n<td>\n<div style=\"margin-left: 22%; \" class=\"form-check\">"
					 * +"\n<input class=\"ace ace-switch ace-switch-6\" id=\""+mapping4+"\" name=\""
					 * +mapping4+"\" ");
					 * 
					 * if(hidden.equals("Y")) { radio_code.append( "type=\"hiden\"/>"); }else {
					 * radio_code.append( "type=\"radio\"/>"); }
					 * 
					 * radio_code.append("\n<span class=\"lbl\"></span>"
					 * +"\n<label class=\"form-check-label\">"+name+"</label>" +"\n</div>\n</td>" );
					 * 
					 * }
					 * 
					 * radio_code.append("</tr>");
					 */

					/*
					 * radio_code.append("\n<label >" + "\n<input"); if(hidden.equals("Y")) {
					 * radio_code.append( " type=\"hidden\""); }else{ radio_code.append(
					 * " type=\"radio\""); }
					 * radio_code.append("class=\"form-check-input\" name=\""+mapping4+"\" id=\""
					 * +mapping4+"\" >"+name +"\n</label>");
					 */

					j++;
				}
				// }

			}
		}

		radio_code2.append("<tr>");

		for (int i = 0; i < ext_user_radio.size(); i++) {

			String field_name4 = ext_user_radio.get(i).getField_name();
			String mapping4 = ext_user_radio.get(i).getMapping();
			String data_type4 = ext_user_radio.get(i).getData_type();

			String mandatory = ext_user_radio.get(i).getMandatory();
			String hidden = ext_user_radio.get(i).getHidden();
			String readonly = ext_user_radio.get(i).getReadonly();
			String dependent = ext_user_radio.get(i).getDependent();

			String calculated_field = ext_user_radio.get(i).getCalculated_field();

			String ext_dd_id = ext_user_radio.get(i).getExt_dd_id();
			String sp_name = ext_user_radio.get(i).getSp_name();

			String sp_name_for_auto = ext_user_radio.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user_radio.get(i).getExt_auto_id();

			String ext_dependent_id = ext_user_radio.get(i).getExt_dependent_id();
			String dependent_on = ext_user_radio.get(i).getDependent_on();
			String dependent_sp = ext_user_radio.get(i).getDependent_sp();

			String radio = ext_user_radio.get(i).getRadio();

			if (data_type4.equals("radiobutton")) {
				String radio_option = ext_user_radio.get(i).getRadio_option();

				System.out.println("---options--" + radio_option);

				String[] split = radio_option.split(",");

				System.out.println("-----after split--------" + split);

				int j = 0;
				for (String name : split) {
					// System.out.println("-----value of j-----"+j);

					if (j > 2 && j <= 5) {

						radio_code2.append("\n<td>\n<div style=\"margin-left: 22%; \" class=\"form-check\">"
								+ "\n<input class=\"ace ace-switch ace-switch-6\" id=\"" + mapping4 + "\" name=\""
								+ mapping4 + "\" ");

						if (hidden.equals("Y")) {
							radio_code2.append("type=\"hiden\"/>");
						} else {
							radio_code2.append("type=\"radio\"/>");
						}

						radio_code2.append("\n<span class=\"lbl\"></span>" + "\n<label class=\"form-check-label\">"
								+ name + "</label>" + "\n</div>\n</td>");

					}

					j++;
				}
			}

		}
		radio_code2.append("\n</tr>");

		radiobutton.append("<table>");

		System.out.println("-----under table-------------");

		radiobutton.append("<tr>");

		for (int i = 0; i < ext_user_radio.size(); i++) {

			String field_name4 = ext_user_radio.get(i).getField_name();
			String mapping4 = ext_user_radio.get(i).getMapping();
			String data_type4 = ext_user_radio.get(i).getData_type();

			String mandatory = ext_user_radio.get(i).getMandatory();
			String hidden = ext_user_radio.get(i).getHidden();
			String readonly = ext_user_radio.get(i).getReadonly();
			String dependent = ext_user_radio.get(i).getDependent();

			String calculated_field = ext_user_radio.get(i).getCalculated_field();

			String ext_dd_id = ext_user_radio.get(i).getExt_dd_id();
			String sp_name = ext_user_radio.get(i).getSp_name();

			String sp_name_for_auto = ext_user_radio.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user_radio.get(i).getExt_auto_id();

			String ext_dependent_id = ext_user_radio.get(i).getExt_dependent_id();
			String dependent_on = ext_user_radio.get(i).getDependent_on();
			String dependent_sp = ext_user_radio.get(i).getDependent_sp();

			String radio = ext_user_radio.get(i).getRadio();

			System.out.println("--------------field name-------------------" + field_name4);

			if (data_type4.equals("radiobutton")) {
				radiobutton
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					radiobutton.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				radiobutton
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				// -------radio code-----

				radiobutton.append("\n" + radio_code + radio_code2
				// ----------end---------------
						+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

		}
		radiobutton.append("\n</tr>");
		radiobutton.append("\n</table>");

		form.append("\n<form action=\"savetwojsp\" class=\"form-horizontal\" id=\"Regi\" method=\"Post\">"
				+ "\n<input type=\"hidden\" name=\"test\" id=\"test\" value=\"\" />"

				+ "<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\">"
				+ "User Personal Information" + "</div>" + " \n<table>" + "\n<tr>");

		for (int i = 0; i < ext_user.size(); i++) {
			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();

			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();

			String calculated_field = ext_user.get(i).getCalculated_field();

			String ext_dd_id = ext_user.get(i).getExt_dd_id();
			String sp_name = ext_user.get(i).getSp_name();

			String sp_name_for_auto = ext_user.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user.get(i).getExt_auto_id();

			String ext_dependent_id = ext_user.get(i).getExt_dependent_id();
			String dependent_on = ext_user.get(i).getDependent_on();
			String dependent_sp = ext_user.get(i).getDependent_sp();

			System.out.println("field name-------" + field_name4);
			System.out.println("mapping-------" + mapping4);
			System.out.println("data type-------" + data_type4);
			System.out.println("mandatory------" + mandatory);
			System.out.println("calculated-------" + calculated_field);

			System.out.println("-value of i for non radio---" + i);
			if (i <= 2) {
				System.out.println("-under i<=2---");
				// ------------------------------for
				// textfield------------------------------------------//
				if (data_type4.equals("textfield")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}
					if (calculated_field.equals("Y")) {
						form.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
					} else {
						form.append("name=\"" + mapping4 + "\" id=\"" + mapping4
								+ "\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
					}

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("  readonly");
					}
					form.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				// --------------------------for
				// autocomplete---------------------------------------------

				if (data_type4.equals("autocomplete")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "<%String\t" + ext_auto_id + " = \"" + sp_name_for_auto + "\"; %>"
							+ "<%session.setAttribute(\"" + ext_auto_id + "\", " + ext_auto_id + "); %>");
					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}
					if (calculated_field.equals("Y")) {
						form.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
					} else {
						form.append("name=" + mapping4 + " id=\"" + ext_auto_id + "\" class=\"col-xs-12 col-sm-4\" ");
					}

					/*
					 * if(mandatory.equals("Y")) { System.out.
					 * println("-------------in loop 1 part 2 required-------------------");
					 * form.append("required"); }
					 */

					/*
					 * if(readonly.equals("Y")) { System.out.
					 * println("-------------in loop 1 part 2 required-------------------");
					 * form.append("  readonly"); }
					 */
					form.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

				}

				/*----------------------------for dropdown-----------------------------------*/

				if (data_type4.equals("dropdown")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

					if (dependent.equals("Y")) {

						form.append("\n<%String\t" + ext_dependent_id + " = \"" + dependent_sp + "\"; %>"
								+ "\n<%session.setAttribute(\"" + ext_dependent_id + "\",  " + ext_dependent_id
								+ "); %>");
						form.append("\n<select name=\"state_name\" id=\"" + mapping4
								+ "\"  class=\"col-xs-3 col-sm-3 form-control state\">"
								+ "\n<option >---select---</option>" + "\n</select>");
					} else {
						form.append("\n<%String\t" + mapping4 + " = \"" + sp_name + "\"; %>"
								+ "\n<%session.setAttribute(\"" + mapping4 + "\",  " + mapping4 + "); %>");

						form.append("\n<select name=\"drop_value\" id=\"" + mapping4
								+ "\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
						form.append("\t" + mapping4);
						form.append("\" >" + "\n<option >---select---</option>" + "\n<option value=\"${drop_value}\"");

						if (mandatory.equals("Y")) {
							System.out.println("-------------in loop 1 part 2 required-------------------");
							form.append("required");
						}

						if (readonly.equals("Y")) {
							System.out.println("-------------in loop 1 part 2 required-------------------");
							form.append("readonly");
						}
						form.append(">${drop_value}</option>\n" + "</select>\n");
					}
					form.append("</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				// ----------------------------------------------for dependent
				// dropdown------------------------------------------------------------------------->

				/*
				 * if(data_type4.equals("dropdown") && dependent.equals("Y") ) {
				 * form.append("\n<td>"
				 * +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
				 * +"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
				 * +"\n "+field_name4);
				 * 
				 * System.out.println("-------------mandatory for testing=------------------"
				 * +mandatory);
				 * 
				 * if(mandatory.equals("Y")) {
				 * System.out.println("-------------in loop 1-------------------"); form.append
				 * ("\n <i class=\"menu-icon fa red\"> *</i>"); }
				 * 
				 * 
				 * form.append("\n</label>" +"\n<div class=\"col-xs-12 col-sm-9\">"
				 * +"\n<div class=\"clearfix\">" +
				 * "\n<%String\t"+ext_dependent_id+" = \""+dependent_sp+"\"; %>"
				 * +"\n<%session.setAttribute(\""+ext_dependent_id+"\",  "
				 * +ext_dependent_id+"); %>");
				 * 
				 * 
				 * form.append("\n<select name=\"drop_value\" id=\""
				 * +ext_dependent_id+"\"   class=\"col-xs-3 col-sm-3 form-control");
				 * 
				 * form.append( "\" >" +"\n<option >---select---</option>"
				 * 
				 * +"</select>\n</div>" +"\n</div>" +"\n</div>" +"\n</td>"); }
				 */

				/*------------------------ for date----------- */

				if (data_type4.equals("date")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "<div class=\"input-group input-append date\" id=\"datePicker\">");

					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4
							+ " placeholder=\"picup Date\" class=\"form-control\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("  readonly");
					}
					form.append(
							"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
									+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

				}

				/////////////////////// for toggle button///////////////

				if (data_type4.equals("togglebutton")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"checkbox\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4
							+ "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					form.append("/>\n" + "\n<span class=\"lbl\"></span>"

							+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				// ------------------------------for
				// checkbox---------------------------------------

				if (data_type4.equals("checkbox")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

					form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"checkbox\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					form.append("/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				// ------------------------------------for radio
				// butttons------------------------------------------------------------

			}

		}
		form.append("\n</tr>");

		form.append("\n<tr>");

		for (int i = 0; i < ext_user.size(); i++) {
			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();
			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();
			String calculated_field = ext_user.get(i).getCalculated_field();

			String ext_dd_id = ext_user.get(i).getExt_dd_id();
			String sp_name = ext_user.get(i).getSp_name();
			String sp_name_for_auto = ext_user.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user.get(i).getExt_auto_id();

			System.out.println("field name-------" + field_name4);
			System.out.println("mapping-------" + mapping4);
			System.out.println("data type-------" + data_type4);
			if (i > 2 && i <= 5) {

				// ------------------------------for
				// textfield------------------------------------------//
				if (data_type4.equals("textfield")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}
					if (calculated_field.equals("Y")) {
						form.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
					} else {
						form.append("name=" + mapping4 + " id=" + mapping4
								+ " class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
					}

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("  readonly");
					}
					form.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				// --------------------------for autocomplete-------------------------

				if (data_type4.equals("autocomplete")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "<%String\t" + ext_auto_id + " = \"" + sp_name_for_auto + "\"; %>"
							+ "<%session.setAttribute(\"" + ext_auto_id + "\", " + ext_auto_id + "); %>");
					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}
					if (calculated_field.equals("Y")) {
						form.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
					} else {
						form.append("name=" + mapping4 + " id=\"" + ext_auto_id + "\" class=\"col-xs-12 col-sm-4\" ");
					}

					/*
					 * if(mandatory.equals("Y")) { System.out.
					 * println("-------------in loop 1 part 2 required-------------------");
					 * form.append("required"); }
					 */

					/*
					 * if(readonly.equals("Y")) { System.out.
					 * println("-------------in loop 1 part 2 required-------------------");
					 * form.append("  readonly"); }
					 */
					form.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

				}

				/*----------------------------for dropdown-----------------------------------*/

				if (data_type4.equals("dropdown")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "\n<%String\t" + ext_dd_id + " = \"" + sp_name + "\"; %>" + "\n<%session.setAttribute(\""
							+ ext_dd_id + "\",  " + ext_dd_id + "); %>");

					form.append("\n<select name=\"drop_value\" id=\"" + ext_dd_id
							+ "\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");

					if (ext_dd_id.equals("dd_1")) {
						form.append(" dd1");
					}

					if (ext_dd_id.equals("dd_2")) {
						form.append(" dd2");
					}

					if (ext_dd_id.equals("dd_3")) {
						form.append(" dd3");
					}

					if (ext_dd_id.equals("dd_4")) {
						form.append(" dd4");
					}

					if (ext_dd_id.equals("dd_5")) {
						form.append(" dd5");
					}

					if (ext_dd_id.equals("dd_6")) {
						form.append(" dd6");
					}

					if (ext_dd_id.equals("dd_7")) {
						form.append(" dd7");
					}

					if (ext_dd_id.equals("dd_8")) {
						form.append(" dd8");
					}

					form.append("\" >" + "\n<option >---select---</option>" + "\n<option value=\"${drop_value}\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("readonly");
					}
					form.append(
							">${drop_value}</option>\n" + "</select>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				/*------------------------ for date----------- */

				if (data_type4.equals("date")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "<div class=\"input-group input-append date\" id=\"datePicker\">");

					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4
							+ " placeholder=\"picup Date\" class=\"form-control\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("  readonly");
					}
					form.append(
							"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
									+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				/////////////////////// for toggle button///////////////

				if (data_type4.equals("togglebutton")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"checkbox\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4
							+ "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					form.append("/>\n" + "\n<span class=\"lbl\"></span>"

							+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				if (data_type4.equals("checkbox")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

					form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"checkbox\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					form.append("/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

			}

		}
		form.append("\n</tr>");

		form.append("\n<tr>");
		for (int i = 0; i < ext_user.size(); i++) {
			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();
			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();
			String calculated_field = ext_user.get(i).getCalculated_field();

			String ext_dd_id = ext_user.get(i).getExt_dd_id();
			String sp_name = ext_user.get(i).getSp_name();
			String sp_name_for_auto = ext_user.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user.get(i).getExt_auto_id();

			System.out.println("field name-------" + field_name4);
			System.out.println("mapping-------" + mapping4);
			System.out.println("data type-------" + data_type4);
			if (i > 5 && i <= 8) {
				// ------------------------------for
				// textfield------------------------------------------//
				if (data_type4.equals("textfield")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}
					if (calculated_field.equals("Y")) {
						form.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
					} else {
						form.append("name=" + mapping4 + " id=" + mapping4
								+ " class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
					}

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("  readonly");
					}
					form.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

//--------------------------for autocomplete-------------------------

				if (data_type4.equals("autocomplete")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "<%String\t" + ext_auto_id + " = \"" + sp_name_for_auto + "\"; %>"
							+ "<%session.setAttribute(\"" + ext_auto_id + "\", " + ext_auto_id + "); %>");
					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}
					if (calculated_field.equals("Y")) {
						form.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
					} else {
						form.append("name=" + mapping4 + " id=\"" + ext_dd_id + "\" class=\"col-xs-12 col-sm-4\" ");
					}

					/*
					 * if(mandatory.equals("Y")) { System.out.
					 * println("-------------in loop 1 part 2 required-------------------");
					 * form.append("required"); }
					 */

					/*
					 * if(readonly.equals("Y")) { System.out.
					 * println("-------------in loop 1 part 2 required-------------------");
					 * form.append("  readonly"); }
					 */
					form.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

				}

				/*----------------------------for dropdown-----------------------------------*/

				if (data_type4.equals("dropdown")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "\n<%String\t" + ext_dd_id + " = \"" + sp_name + "\"; %>" + "\n<%session.setAttribute(\""
							+ ext_dd_id + "\",  " + ext_dd_id + "); %>");

					form.append("\n<select name=\"drop_value\" id=\"" + ext_dd_id
							+ "\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");

					if (ext_dd_id.equals("dd_1")) {
						form.append(" attribute1");
					}

					if (ext_dd_id.equals("dd_2")) {
						form.append(" attribute2");
					}

					if (ext_dd_id.equals("dd_3")) {
						form.append(" attribute3");
					}

					if (ext_dd_id.equals("dd_4")) {
						form.append(" attribute4");
					}

					if (ext_dd_id.equals("dd_5")) {
						form.append(" attribute5");
					}

					if (ext_dd_id.equals("dd_6")) {
						form.append(" attribute6");
					}

					if (ext_dd_id.equals("dd_7")) {
						form.append(" attribute7");
					}

					if (ext_dd_id.equals("dd_8")) {
						form.append(" attribute8");
					}

					form.append("\" >" + "\n<option >---select---</option>" + "\n<option value=\"${drop_value}\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("readonly");
					}
					form.append(
							">${drop_value}</option>\n" + "</select>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				/*------------------------ for date----------- */

				if (data_type4.equals("date")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
							+ "<div class=\"input-group input-append date\" id=\"datePicker\">");

					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"text\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4
							+ " placeholder=\"picup Date\" class=\"form-control\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("  readonly");
					}
					form.append(
							"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
									+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				/////////////////////// for toggle button///////////////

				if (data_type4.equals("togglebutton")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

					form.append("\n<input");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"checkbox\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4
							+ "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					form.append("/>\n" + "\n<span class=\"lbl\"></span>"

							+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

				if (data_type4.equals("checkbox")) {
					form.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
							+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
							+ field_name4);

					System.out.println("-------------mandatory for testing=------------------" + mandatory);

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1-------------------");
						form.append("\n <i class=\"menu-icon fa red\"> *</i>");
					}

					form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

					form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"");

					if (hidden.equals("Y")) {
						form.append("  type=\"hidden\" ");
					} else {
						form.append("  type=\"checkbox\" ");
					}

					form.append("name=" + mapping4 + " id=" + mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						form.append("required");
					}

					form.append("/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				}

			}

		}
		form.append("\n</tr>");

		/*
		 * form.append("\n<tr>"); for(int i=0;i<ext_user.size();i++) { String
		 * field_name4=ext_user.get(i).getField_name(); String
		 * mapping4=ext_user.get(i).getMapping(); String
		 * data_type4=ext_user.get(i).getData_type();
		 * 
		 * System.out.println("field name-------"+field_name4);
		 * System.out.println("mapping-------"+mapping4);
		 * System.out.println("data type-------"+data_type4); if(i>2) {
		 * form.append("\n<td>"
		 * +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
		 * +"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
		 * +"\n "+field_name4 +"\n</label>"
		 * 
		 * +"\n<div class=\"col-xs-12 col-sm-9\">" +"\n<div class=\"clearfix\">"
		 * +"\n<input type=\"text\"name="+mapping4+" id="
		 * +mapping4+"class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"/>"
		 * +"\n</div>" +"\n</div>" +"\n</div>" +"\n</td>"); } } form.append("\n</tr>");
		 */

		form.append("\n</table>" + radiobutton);

		/*
		 * if(i==1){ form.append(" \n<td>"
		 * +"<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
		 * +"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
		 * +"\n"+field_name4 +"\n</label>"
		 * 
		 * + "\n<div class=\"col-xs-12 col-sm-9\">" +"\n<div class=\"clearfix\">"
		 * +"\n<input type=\"text\" name="+mapping4+" id="
		 * +mapping4+" class=\"col-xs-12 col-sm-4\"/>" +"\n</div>" +"\n</div>"
		 * +"\n</div>" +"\n</td>" ); }
		 */

		/*
		 * form.append("\n<td>"
		 * +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
		 * +"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"url\">"
		 * +" \n"+field_name4 +"\n</label>" +"\n<div class=\"col-xs-12 col-sm-9\">"
		 * +"\n<div class=\"clearfix\">" +
		 * " \n<input type=\"text\" name="+mapping4+" id="
		 * +mapping4+" class=\"col-xs-12 col-sm-4\" onblur=\"validateEmail(this);\"  />"
		 * +"\n</div>" +"\n</div>" +"\n</div>" +"\n</td>"
		 * 
		 * +"\n</tr>" +"\n</table>");
		 */

		form.append("\n<div class=\"hr hr-dotted\"></div> "

				/*
				 * + "\n<div class=\"wizard-actions\">"
				 * +"\n<button type=\"submit\" class=\"btn btn-success center\" onclick=\"submitForms()\">"
				 * +"\nCREATE" +"\n</button>" +"\n</div> "
				 */

				+ "\n</form>");

		String calculated_field = ext_user.get(1).getCalculated_field();

		System.out.println("--------------testing calculte field for script-----------" + calculated_field);

		// if(calculated_field.equals("Y"))
		// {
		calculated_script.append("\n<script type=\"text/javascript\">" + "\nfunction findTotal(){"
				+ " \nvar arr = document.getElementsByName('qty');" + "\nvar tot=0;" + "\nfor(var i=0;i<arr.length;i++)"
				+ "\n{" + "\nif" + "\n(" + "\nparseInt(arr[i].value)" + "\n)" + "\ntot += parseInt(arr[i].value);"
				+ "\n}" + "\ndocument.getElementById('total').value = tot;" + "\n}" + "\n</script>;");
		// }

		/*
		 * dropdown_script.append("\n<script>" +
		 * "\n$('.attribute1').mousedown(function(event) {" +"\n$.ajax({" +
		 * "\nurl: '${pageContext.request.contextPath}/sp_for_dd_1',"
		 * 
		 * +"\nsuccess: function(data) " +"\n{" +"\nvar select = $('#attribute1');"
		 * +"\nselect.find('option').remove();"
		 * 
		 * +"\n$.each(data, function(index, value) {" +
		 * "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
		 * +"\n });" + "\n}" +"\n});"
		 * 
		 * +"\n});" +"\n</script>");
		 */
		for (int i = 0; i < ext_user.size(); i++) {
			String dependent = ext_user.get(i).getDependent();
			String ext_dependent_id = ext_user.get(i).getExt_dependent_id();
			String dependent_on = ext_user.get(i).getDependent_on();
			String dependent_sp = ext_user.get(i).getDependent_sp();
			String mapping4 = ext_user.get(i).getMapping();

			if (dependent.equals("N")) {
				dropdown_script.append("\n<script>" + "\n$('." + mapping4 + "').mousedown(function(event) {"
						+ "\n$.ajax({" + "\nurl: '${pageContext.request.contextPath}/sp_for_" + mapping4 + "',"

						+ "\nsuccess: function(data) " + "\n{" + "\nvar select = $('#" + mapping4 + "');"
						+ "\nselect.find('option').remove();"

						+ "\n$.each(data, function(index, value) {"
						+ "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);" + "\n });"
						+ "\n}" + "\n});"

						+ "\n});" + "\n</script>");
			}
		}
		/*
		 * dropdown_script.append("\n<script>" +
		 * "\n$('.attribute3').mousedown(function(event) {" +"\n$.ajax({" +
		 * "\nurl: '${pageContext.request.contextPath}/sp_for_dd_3',"
		 * 
		 * +"\nsuccess: function(data) " +"\n{" +"\nvar select = $('#attribute3');"
		 * +"\nselect.find('option').remove();"
		 * 
		 * +"\n$.each(data, function(index, value) {" +
		 * "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
		 * +"\n });" + "\n}" +"\n});"
		 * 
		 * +"\n});" +"\n</script>");
		 * 
		 * dropdown_script.append("\n<script>" +
		 * "\n$('.attribute4').mousedown(function(event) {" +"\n$.ajax({" +
		 * "\nurl: '${pageContext.request.contextPath}/sp_for_dd_4',"
		 * 
		 * +"\nsuccess: function(data) " +"\n{" +"\nvar select = $('#attribute4');"
		 * +"\nselect.find('option').remove();"
		 * 
		 * +"\n$.each(data, function(index, value) {" +
		 * "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
		 * +"\n });" + "\n}" +"\n});"
		 * 
		 * +"\n});" +"\n</script>"); dropdown_script.append("\n<script>" +
		 * "\n$('.attribute5').mousedown(function(event) {" +"\n$.ajax({" +
		 * "\nurl: '${pageContext.request.contextPath}/sp_for_dd_5',"
		 * 
		 * +"\nsuccess: function(data) " +"\n{" +"\nvar select = $('#attribute5');"
		 * +"\nselect.find('option').remove();"
		 * 
		 * +"\n$.each(data, function(index, value) {" +
		 * "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
		 * +"\n });" + "\n}" +"\n});"
		 * 
		 * +"\n});" +"\n</script>"); dropdown_script.append("\n<script>" +
		 * "\n$('.attribute6').mousedown(function(event) {" +"\n$.ajax({" +
		 * "\nurl: '${pageContext.request.contextPath}/sp_for_dd_6',"
		 * 
		 * +"\nsuccess: function(data) " +"\n{" +"\nvar select = $('#attribute6');"
		 * +"\nselect.find('option').remove();"
		 * 
		 * +"\n$.each(data, function(index, value) {" +
		 * "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
		 * +"\n });" + "\n}" +"\n});"
		 * 
		 * +"\n});" +"\n</script>"); dropdown_script.append("\n<script>" +
		 * "\n$('.attribute7').mousedown(function(event) {" +"\n$.ajax({" +
		 * "\nurl: '${pageContext.request.contextPath}/sp_for_dd_7',"
		 * 
		 * +"\nsuccess: function(data) " +"\n{" +"\nvar select = $('#attribute7');"
		 * +"\nselect.find('option').remove();"
		 * 
		 * +"\n$.each(data, function(index, value) {" +
		 * "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
		 * +"\n });" + "\n}" +"\n});"
		 * 
		 * +"\n});" +"\n</script>"); dropdown_script.append("\n<script>" +
		 * "\n$('.attribute8').mousedown(function(event) {" +"\n$.ajax({" +
		 * "\nurl: '${pageContext.request.contextPath}/sp_for_dd_8',"
		 * 
		 * +"\nsuccess: function(data) " +"\n{" +"\nvar select = $('#attribute8');"
		 * +"\nselect.find('option').remove();"
		 * 
		 * +"\n$.each(data, function(index, value) {" +
		 * "\n$('<option>').text(value.drop_value).val(value.country_id).appendTo(select);"
		 * +"\n });" + "\n}" +"\n});"
		 * 
		 * +"\n});" +"\n</script>");
		 */

		autocomplete_script.append("\n<script>" + "\nvar options = {"
				+ "\nurl: '${pageContext.request.contextPath}/autocomplete_sp_1'," + "\ngetValue: \"table_name\","
				+ "\nlist: {	" + "\nmatch: {" + "\nenabled: true" + "\n}" + "}," + "\ntheme: \"square\"" + "\n};"

				+ "\n$(\"#auto_1\").easyAutocomplete(options);"

				+ "\n</script>");

		autocomplete_script.append("\n<script>" + "\nvar options = {"
				+ "\nurl: '${pageContext.request.contextPath}/autocomplete_sp_2'," + "\ngetValue: \"table_name\","
				+ "\nlist: {	" + "\nmatch: {" + "\nenabled: true" + "\n}" + "}," + "\ntheme: \"square\"" + "\n};"

				+ "\n$(\"#auto_2\").easyAutocomplete(options);"

				+ "\n</script>");

		autocomplete_script.append("\n<script>" + "\nvar options = {"
				+ "\nurl: '${pageContext.request.contextPath}/autocomplete_sp_3'," + "\ngetValue: \"table_name\","
				+ "\nlist: {	" + "\nmatch: {" + "\nenabled: true" + "\n}" + "}," + "\ntheme: \"square\"" + "\n};"

				+ "\n$(\"#auto_3\").easyAutocomplete(options);"

				+ "\n</script>");

		autocomplete_script.append("\n<script>" + "\nvar options = {"
				+ "\nurl: '${pageContext.request.contextPath}/autocomplete_sp_4'," + "\ngetValue: \"table_name\","
				+ "\nlist: {	" + "\nmatch: {" + "\nenabled: true" + "\n}" + "}," + "\ntheme: \"square\"" + "\n};"

				+ "\n$(\"#auto_4\").easyAutocomplete(options);"

				+ "\n</script>");

		for (int i = 0; i < ext_user.size(); i++) {
			String dependent = ext_user.get(i).getDependent();
			String ext_dependent_id = ext_user.get(i).getExt_dependent_id();
			String dependent_on = ext_user.get(i).getDependent_on();
			String dependent_sp = ext_user.get(i).getDependent_sp();
			String mapping4 = ext_user.get(i).getMapping();

			if (dependent.equals("Y")) {
				dependent_dropdown.append("\n<script>" + "\n$('#" + dependent_on + "').change(function() {" + "\n$('#"
						+ mapping4 + "').html('<option>select</option>');" + "\nvar country=$(this).val();"
						+ "\nconsole.log(country);" + "\n$.ajax({"
						+ "\nurl: '${pageContext.request.contextPath}/stateSp'," + "\ntype:'GET',"
						+ "\ndata:{ 'country_id': country }," + "\nsuccess: function(data) " + "\n{"
						+ "\nconsole.log(data);" + "\nvar select = $('#" + mapping4 + "');"
						+ "\nselect.find('option').remove();" + "\n$.each(data, function(index, value) " + "\n{"
						+ "\n $('<option>').text(value.state_name).appendTo(select);" + "\n});" + "\n}//success"
						+ "\n});//ajax" + "\n});//change function" + "\n</script>");
			}
		}

		dependent_dropdown.append("\n<script>" + "\n$('.country').mousedown(function(event) " + "\n{" + "\n$.ajax({"
				+ "\nurl: '${pageContext.request.contextPath}/countrySp'," + "\nsuccess: function(data)" + "\n{"
				+ "\nconsole.log(data);" + "\nvar select = $('#patient_country');"
				+ "\n$('<option>').text('select').val(0).appendTo(select);" + "\nselect.find('option').remove();"
				+ "\n$.each(data, function(index, value) {"
				+ "\n$('<option>').text(value.patient_country).val(value.country_id).appendTo(select);" + "\n});"
				+ "\n}" + "\n});" + "\n});" + "\n</script>");

		strContent.append(importsecton + " \n" + headsection
				+ "\n<body>\n<div class=\"main-container\" id=\"main-container\">"
				+ "\n<script type=\"text/javascript\">" + "\ntry {" + "\nace.settings.check('main-container', 'fixed')"
				+ "\n} catch (e) {" + "\n}" + "\n</script>" + "\n<div class=\"main-content\">"
				+ "\n<div class=\"main-content-inner\">" + "\n<div class=\"page-content\">" + "\n<!-- /.page-header -->"
				+ "\n<div class=\"row\">" + "\n<div class=\"col-xs-12\">" + "\n<!-- PAGE CONTENT BEGINS -->"
				+ "\n</div>" + "<div class=\"widget-body\">" + "<div class=\"widget-main\">"
				+ "<div id=\"fuelux-wizard-container\">" + "<div class=\"step-content pos-rel\">"
				+ "\n<div class=\"step-pane active\" data-step=\"1\">" + form + "\n</div>" + "\n</div>" + "\n</div>"
				+ "\n</div>" + "\n<!-- /.widget-main -->" + "\n</div>" + "\n<!-- /.widget-body -->" + "</div>"
				+ "\n</div>" + "\n<!-- /.col -->" + "\n</div>" + "\n<!-- /.row -->" + "\n</div>"
				+ "\n<!-- /.page-content -->" + "\n</div>" + calculated_script + "\n" + dropdown_script
				+ autocomplete_script + dependent_dropdown + "</body>\n</html>");

		for (int i = 0; i < ext_user.size(); i++) {
			String add_to_grid = ext_user.get(i).getAdd_to_grid();
			String field_name = ext_user.get(i).getField_name();
			String mapping = ext_user.get(i).getMapping();
			String attr1 = rn_userlist.get(i).getAttribute1();

			if (add_to_grid.equals("Y")) {
				grid_part.append("<th class=\"center\">" + field_name + "</th>");
			}

			if (add_to_grid.equals("Y")) {
				grid_part2.append("<td  class=\"center\">${rn_userlist." + mapping + "}</td>");
			}

		}

		/*
		 * ---------------------------------for edit
		 * part--------------------------------------------------
		 */

		inserfield_extUpdate
				.append("\n<h3 class=\"lighter block green\">User Personal Information</h3>" + "\n<table>" + "\n<tr>");
		for (int i = 0; i < ext_user.size(); i++) {

			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();

			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();

			String calculated_field3 = ext_user.get(i).getCalculated_field();

			if (data_type4.equals("textfield")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				form.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
				inserfield_extUpdate.append("\n<input");

				inserfield_extUpdate.append("  type=\"text\" ");

				if (calculated_field3.equals("Y")) {
					inserfield_extUpdate.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					inserfield_extUpdate.append(" value=\"${rep_updt." + mapping4 + "}\" name=" + mapping4 + " id="
							+ mapping4 + " class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
				}

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				inserfield_extUpdate.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

			}

			/*----------------------------for dropdown-----------------------------------*/

			if (data_type4.equals("dropdown")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				if (data_type4.equals("dropdown")) {
					inserfield_extUpdate.append(
							"\n<select name=\"drop_value\" id=\"drop_value\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control menucls\" >"
									+ "<option value=\"${drop_value}\" selected>${rep_updt." + mapping4 + "}</option>"
									+ "<option >---select---</option>" + "<option value=\"${drop_value}\"");
				}

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				inserfield_extUpdate.append(
						">${drop_value}</option>\n" + "</select>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			/*------------------------ for date----------- */

			if (data_type4.equals("date")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
								+ "<div class=\"input-group input-append date\" id=\"datePicker\">");

				inserfield_extUpdate.append("\n<input");

				inserfield_extUpdate.append("  type=\"text\" ");

				inserfield_extUpdate.append(" value=\"${rep_updt." + mapping4 + "}\" name=" + mapping4 + " id="
						+ mapping4 + " placeholder=\"picup Date\" class=\"form-control\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				inserfield_extUpdate.append(
						"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
								+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			/////////////////////// for toggle button///////////////

			if (data_type4.equals("togglebutton")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extUpdate.append("\n<input");

				if (hidden.equals("Y")) {
					inserfield_extUpdate.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"checkbox\" ");
				}

				inserfield_extUpdate.append("  value=\"${rep_updt." + mapping4 + "}\"name=" + mapping4 + " id="
						+ mapping4 + "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				inserfield_extUpdate.append("/>\n" + "\n<span class=\"lbl\"></span>"

						+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			///////////////////////////////// for checkbox///////////////////

			if (data_type4.equals("checkbox")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extUpdate.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"");

				if (hidden.equals("Y")) {
					inserfield_extUpdate.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"checkbox\" ");
				}

				inserfield_extUpdate.append(" value=\"${rep_updt." + mapping4 + "}\" name=" + mapping4 + " id="
						+ mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				inserfield_extUpdate.append("/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

		}
		inserfield_extUpdate.append("\n</tr>" + "\n</table>");

		///////////////////////// readonly readonly///////////////

		inserfield_extReadonly.append("\n<h3 class=\"lighter block green\">User Personal Information</h3>" + "\n<table>"

				+ "\n<tr>");
		for (int i = 0; i < ext_user.size(); i++) {

			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();

			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();

			String calculated_field3 = ext_user.get(i).getCalculated_field();

			if (data_type4.equals("textfield")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
				inserfield_extReadonly.append("\n<input");

				inserfield_extReadonly.append("  type=\"text\" ");

				if (calculated_field3.equals("Y")) {
					inserfield_extReadonly.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					inserfield_extReadonly.append(" value=\"${rep_updt." + mapping4 + "}\" name=" + mapping4 + " id="
							+ mapping4 + " class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
				}

				inserfield_extReadonly.append("readonly");

				inserfield_extReadonly.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

			}

			/*----------------------------for dropdown-----------------------------------*/

			if (data_type4.equals("dropdown")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				if (data_type4.equals("dropdown")) {
					inserfield_extReadonly.append(
							"\n<select name=\"drop_value\" id=\"drop_value\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control menucls\" >"
									+ "<option value=\"${drop_value}\" selected>${rep_updt." + mapping4 + "}</option>"
									+ "<option >---select---</option>" + "<option value=\"${drop_value}\"");
				}

				inserfield_extReadonly.append("readonly");

				inserfield_extReadonly.append(
						">${drop_value}</option>\n" + "</select>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			/*------------------------ for date----------- */

			if (data_type4.equals("date")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
								+ "<div class=\"input-group input-append date\" id=\"datePicker\">");

				inserfield_extReadonly.append("\n<input");

				inserfield_extReadonly.append("  type=\"text\" ");

				inserfield_extReadonly.append(" value=\"${rep_updt." + mapping4 + "}\" name=" + mapping4 + " id="
						+ mapping4 + " placeholder=\"picup Date\" class=\"form-control\"");

				inserfield_extReadonly.append("readonly");

				inserfield_extReadonly.append(
						"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
								+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			/////////////////////// for toggle button///////////////

			if (data_type4.equals("togglebutton")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extReadonly.append("\n<input");

				if (hidden.equals("Y")) {
					inserfield_extReadonly.append("  type=\"hidden\" ");
				} else {
					inserfield_extReadonly.append("  type=\"checkbox\" ");
				}

				inserfield_extReadonly.append("  value=\"${rep_updt." + mapping4 + "}\" name=" + mapping4 + " id="
						+ mapping4 + "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

				inserfield_extReadonly.append("readonly");

				inserfield_extReadonly.append("/>\n" + "\n<span class=\"lbl\"></span>"

						+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			///////////////////////////////// for checkbox///////////////////

			if (data_type4.equals("checkbox")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extReadonly.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"");

				if (hidden.equals("Y")) {
					inserfield_extReadonly.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"checkbox\" ");
				}

				inserfield_extReadonly.append(" value=\"${rep_updt." + mapping4 + "}\" name=" + mapping4 + " id="
						+ mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

				inserfield_extReadonly.append("readonly");

				inserfield_extReadonly.append("/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

		}
		inserfield_extReadonly.append("\n</tr>" + "\n</table>");

		String tomcatRoot1 = request.getServletContext().getRealPath("/WEB-INF/tiles/acemaster/extpages");
		System.out.println(" tomcatRoot path=" + new File(tomcatRoot1).getAbsolutePath());

		// File file4 = new File(tomcatRoot1 + "/" + view_name +"_ext.jsp");
		File file4 = new File(
				projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/" + view_name + "_ext.jsp");
		File file5 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/pages/gridview_ext.jsp");
		File file6 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/pages/gridview_ext2.jsp");
		File file7 = new File(
				projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/inserfield_extUpdate.jsp");
		File file8 = new File(
				projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/inserfield_extReadonly.jsp");

		System.out.println("file path" + file4);

		file4.setExecutable(true);
		file4.setReadable(true);
		file4.setWritable(true);

		if (file4.exists()) {
			file4.delete();

			System.out.println("---------file is deleted---------");
		}

		if (file5.exists()) {

			file5.delete();

			System.out.println("---------file is deleted---------");
		}
		if (file6.exists()) {

			file6.delete();

			System.out.println("---------file is deleted---------");
		}
		if (file7.exists()) {

			file7.delete();

			System.out.println("---------file is deleted---------");
		}

		if (file8.exists()) {

			file8.delete();

			System.out.println("---------file is deleted---------");
		}

		/*
		 * if (!file4.exists()) {
		 */
		try {
			file4.createNewFile();
			System.out.println("------file 4 is created-------" + file4);
			file5.createNewFile();
			file6.createNewFile();
			file7.createNewFile();
			file8.createNewFile();
			System.out.println("------file is created-------");
			System.out.println("------file6  is created-------");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// }

		FileWriter fw4 = null;
		FileWriter fw5 = null;
		FileWriter fw6 = null;
		FileWriter fw7 = null;
		FileWriter fw8 = null;
		try {
			fw4 = new FileWriter(file4.getAbsoluteFile());
			fw5 = new FileWriter(file5.getAbsoluteFile());
			fw6 = new FileWriter(file6.getAbsoluteFile());
			fw7 = new FileWriter(file7.getAbsoluteFile());
			fw8 = new FileWriter(file8.getAbsoluteFile());
			System.out.println("file writer fw4------" + fw4);
			System.out.println("file writer fw6------" + fw6);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedWriter bw4 = new BufferedWriter(fw4);
		BufferedWriter bw5 = new BufferedWriter(fw5);
		BufferedWriter bw6 = new BufferedWriter(fw6);
		BufferedWriter bw7 = new BufferedWriter(fw7);
		BufferedWriter bw8 = new BufferedWriter(fw8);
		System.out.println("buffer writer------" + bw4);
		System.out.println("buffer writer bw6------" + bw6);
		try {
			bw4.write(strContent.toString());
			bw5.write(grid_part.toString());
			bw6.write(grid_part2.toString());
			bw7.write(inserfield_extUpdate.toString());
			bw8.write(inserfield_extReadonly.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			bw4.close();
			bw5.close();
			bw6.close();
			bw7.close();
			bw8.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done\n\n\n\n");

		return new ModelAndView("redirect:Rn_roles_menu_access");

	}

	
	
	/* -----------------------extension for form builder---------------------------*/
	@RequestMapping(value = "/rn_write_view_form_builder", method = RequestMethod.GET)
	public ModelAndView writeViewFormBuilder(Rn_Menu_Header menu_header, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {

		String projectPath = environment.getRequiredProperty("projectPath");
		String devPath = environment.getRequiredProperty("devPath");

		HttpSession session = request.getSession(false);
		String f_code = (String) session.getAttribute("form_code");

		List<Rn_Fb_Header> report = rn_user_registration_service.fb_header(f_code);

		String table_name = report.get(0).getTable_name();
		System.out.println("table_name "+table_name);
		String table_name_lower = table_name.toLowerCase();

		System.out.println("table_name_lower "+table_name_lower);
		String line_table_name = report.get(0).getLine_table_name();

		String line_table_name_lower = line_table_name;
		if (line_table_name != null) {
			line_table_name_lower = line_table_name.toLowerCase();
		}

		System.out.println("-----table name in form builder-----" + table_name_lower);

		StringBuilder ext_field = new StringBuilder();
		StringBuilder grid_part_line = new StringBuilder();
		StringBuilder grid_part_line2 = new StringBuilder();
		StringBuilder add_grid = new StringBuilder();
		StringBuilder add_grid2 = new StringBuilder();
		StringBuilder datepicker = new StringBuilder();
		StringBuilder inserfield_extUpdate = new StringBuilder();
		StringBuilder inserfield_extReadonly = new StringBuilder();
		StringBuilder grid_for_line_update = new StringBuilder();
		StringBuilder grid_for_line_readonly = new StringBuilder();
		
		StringBuilder line_update_head = new StringBuilder();
		

		System.out.println("---------form code------" + f_code);

		List<Rn_Ext_Fields> ext_user = rn_user_registration_dao.ext_userlist2(f_code);
		List<Rn_Ext_Fields> line_field = rn_user_registration_dao.ext_userlist3(f_code);
		String type = null;
		try {	
		 type = ext_user.get(0).getType();
		 System.out.println("type value in rn_write_view_form_builder :: "+type);
		
		}catch(IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		
		
		
		
		try {
		if (type.equals("header") )
		{
			System.out.println("type value in if :: "+type);
			ext_field.append("\n"
						+ "\n<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\"> Extension</div>"
						+ "\n<table>"
						+ "\n<tr>");
		}
		}catch(NullPointerException e) {
			System.out.println(e);
		}
		
		
		int count = 1;

		for (int i = 0; i < ext_user.size(); i++) {
			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();

			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();

			String calculated_field = ext_user.get(i).getCalculated_field();

			String ext_dd_id = ext_user.get(i).getExt_dd_id();
			String sp_name = ext_user.get(i).getSp_name();

			String sp_name_for_auto = ext_user.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user.get(i).getExt_auto_id();

			String ext_dependent_id = ext_user.get(i).getExt_dependent_id();
			String dependent_on = ext_user.get(i).getDependent_on();
			String dependent_sp = ext_user.get(i).getDependent_sp();

			int dividend = count;
			int divisor = 3;
			int remainder = dividend % divisor;

			System.out.println("remainder::" + remainder);
			System.out.println("count::" + count);
			count++;

			// if(i<=2)
			// {
			// ------------------------------for
			// textfield------------------------------------------//
			if (data_type4.equals("textfield")) {
				ext_field
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					ext_field.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				ext_field.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
				ext_field.append("\n<input");

				if (hidden.equals("Y")) {
					ext_field.append("  type=\"hidden\" ");
				} else {
					ext_field.append("  type=\"text\" ");
				}
				if (calculated_field.equals("Y")) {
					ext_field.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					ext_field.append("name=\"" + mapping4 + "\" id=\"" + mapping4 + "\" class=\"col-xs-12 col-sm-4\" ");
				}

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					ext_field.append("required");
				}

				if (readonly.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					ext_field.append("  readonly");
				}
				ext_field.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// --------------------------for
			// autocomplete---------------------------------------------

			if (data_type4.equals("autocomplete")) {
				ext_field
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					ext_field.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				ext_field.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
						+ "<%String\t" + ext_auto_id + " = \"" + sp_name_for_auto + "\"; %>"
						+ "<%session.setAttribute(\"" + ext_auto_id + "\", " + ext_auto_id + "); %>");
				ext_field.append("\n<input");

				if (hidden.equals("Y")) {
					ext_field.append("  type=\"hidden\" ");
				} else {
					ext_field.append("  type=\"text\" ");
				}
				if (calculated_field.equals("Y")) {
					ext_field.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					ext_field.append("name=" + mapping4 + " id=\"" + ext_auto_id + "\" class=\"col-xs-12 col-sm-4\" ");
				}

				/*
				 * if(mandatory.equals("Y")) { System.out.
				 * println("-------------in loop 1 part 2 required-------------------");
				 * form.append("required"); }
				 */

				/*
				 * if(readonly.equals("Y")) { System.out.
				 * println("-------------in loop 1 part 2 required-------------------");
				 * form.append("  readonly"); }
				 */
				ext_field.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

			}

			/*----------------------------for dropdown-----------------------------------*/

			if (data_type4.equals("dropdown")) {
				ext_field
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					ext_field.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				ext_field.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				if (dependent.equals("Y")) {

					ext_field.append("\n<%String\t" + ext_dependent_id + " = \"" + dependent_sp + "\"; %>"
							+ "\n<%session.setAttribute(\"" + ext_dependent_id + "\",  " + ext_dependent_id + "); %>");
					ext_field.append("\n<select name=\"state_name\" id=\"" + mapping4
							+ "\"  class=\"col-xs-3 col-sm-3 form-control state\">" + "\n<option >---select---</option>"
							+ "\n</select>");
				} else {
					ext_field.append("\n<%String\t" + mapping4 + " = \"" + sp_name + "\"; %>"
							+ "\n<%session.setAttribute(\"" + mapping4 + "\",  " + mapping4 + "); %>");

					ext_field.append("\n<select name=\"drop_value\" id=\"" + mapping4
							+ "\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
					ext_field.append("\t" + mapping4);
					ext_field.append("\" >" + "\n<option >---select---</option>" + "\n<option value=\"${drop_value}\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						ext_field.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						ext_field.append("readonly");
					}
					ext_field.append(">${drop_value}</option>\n" + "</select>\n");
				}
				ext_field.append("</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			/*------------------------ for date----------- */

			if (data_type4.equals("date")) {
				ext_field
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					ext_field.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				ext_field.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
						+ "<div class=\"input-group input-append date\" id=\"datePickerE" + i + "\">");

				ext_field.append("\n<input");

				if (hidden.equals("Y")) {
					ext_field.append("  type=\"hidden\" ");
				} else {
					ext_field.append("  type=\"text\" ");
				}

				ext_field.append(
						"name=" + mapping4 + " id=" + mapping4 + " placeholder=\"picup Date\" class=\"form-control\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					ext_field.append("required");
				}

				if (readonly.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					ext_field.append("  readonly");
				}
				ext_field.append(
						"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
								+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

				datepicker.append("<script>" + "$('#datePickerE" + i
						+ "').datepicker({format : 'dd-mm-yyyy'}).on('changeDate', function(e) {$('#eventForm').formValidation('revalidateField', 'date');});"
						+ "</script>");

			}

			// -----------------------------------for toggle
			// button-----------------------------

			if (data_type4.equals("togglebutton")) {
				ext_field
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					ext_field.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				ext_field.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				ext_field.append("\n<input");

				if (hidden.equals("Y")) {
					ext_field.append("  type=\"hidden\" ");
				} else {
					ext_field.append("  type=\"checkbox\" ");
				}

				ext_field.append("name=" + mapping4 + " id=" + mapping4
						+ "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					ext_field.append("required");
				}

				ext_field.append("/>\n" + "\n<span class=\"lbl\"></span>"

						+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// ------------------------------for
			// checkbox---------------------------------------

			if (data_type4.equals("checkbox")) {
				ext_field
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					ext_field.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				ext_field.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				ext_field.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"");

				if (hidden.equals("Y")) {
					ext_field.append("  type=\"hidden\" ");
				} else {
					ext_field.append("  type=\"checkbox\" ");
				}

				ext_field.append("name=" + mapping4 + " id=" + mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					ext_field.append("required");
				}

				ext_field.append("/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				// }//if

			} // for rows

			if (remainder == 0) {
				ext_field.append("\n</tr>");
				ext_field.append("\n<tr>");
			}

		} // close for loop

		ext_field.append("\n</tr>" + "</table>\n" + datepicker + "");

//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
////////////////////////////jsp for update////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////

		for (int i = 0; i < ext_user.size(); i++) {
			String add_to_grid = ext_user.get(i).getAdd_to_grid();
			String field_name = ext_user.get(i).getField_name();
			String mapping = ext_user.get(i).getMapping();
			// String attr1=rn_userlist.get(i).getAttribute1();

			if (add_to_grid.equals("Y")) {
				add_grid.append("<th class=\"center\">" + field_name + "</th>");
			}

			if (add_to_grid.equals("Y")) {
				add_grid2.append("<td  class=\"center\">${" + table_name_lower + "." + mapping + "}</td>");
			}

		}

		for (int i = 0; i < line_field.size(); i++) {
			String add_to_grid = line_field.get(i).getAdd_to_grid();
			String field_name = line_field.get(i).getField_name();
			String mapping = line_field.get(i).getMapping();
			// String attr1=rn_userlist.get(i).getAttribute1();

			if (add_to_grid.equals("Y")) {
				add_grid.append("<th class=\"center\">" + field_name + "</th>");
			}

			if (add_to_grid.equals("Y")) {
				add_grid2.append("<td  class=\"center\">${" + table_name_lower + "." + mapping + "}</td>");
			}

		}
		// ----------------jsp for
		// update---------------------------------------------------------------------------------

		inserfield_extUpdate.append(
				"\n<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\"> Extension</div>"
						+ "\n<table>");
		inserfield_extUpdate.append("<tr>");

		int count1 = 1;

		for (int i = 0; i < ext_user.size(); i++) {
			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();

			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();

			String calculated_field = ext_user.get(i).getCalculated_field();

			String ext_dd_id = ext_user.get(i).getExt_dd_id();
			String sp_name = ext_user.get(i).getSp_name();

			String sp_name_for_auto = ext_user.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user.get(i).getExt_auto_id();

			String ext_dependent_id = ext_user.get(i).getExt_dependent_id();
			String dependent_on = ext_user.get(i).getDependent_on();
			String dependent_sp = ext_user.get(i).getDependent_sp();

			int dividend = count1;
			int divisor = 3;
			int remainder = dividend % divisor;

			System.out.println("remainder::" + remainder);
			System.out.println("before count::" + count1);
			count1++;

			// if(i<=2)
			// {
			// ------------------------------for
			// textfield------------------------------------------//
			if (data_type4.equals("textfield")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
				inserfield_extUpdate.append("\n<input value=\"${" + table_name_lower + "_updt." + mapping4 + "}\" ");

				if (hidden.equals("Y")) {
					inserfield_extUpdate.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"text\" ");
				}
				if (calculated_field.equals("Y")) {
					inserfield_extUpdate.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					inserfield_extUpdate.append("name=\"" + mapping4 + "\" id=\"" + mapping4
							+ "\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
				}

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				if (readonly.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("  readonly");
				}
				inserfield_extUpdate.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// --------------------------for
			// autocomplete---------------------------------------------

			if (data_type4.equals("autocomplete")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">"
						+ "\n<div class=\"clearfix\">" + "<%String\t" + ext_auto_id + " = \"" + sp_name_for_auto
						+ "\"; %>" + "<%session.setAttribute(\"" + ext_auto_id + "\", " + ext_auto_id + "); %>");
				inserfield_extUpdate.append("\n<input  value=\"${" + table_name_lower + "_updt." + mapping4 + "}\"");

				if (hidden.equals("Y")) {
					inserfield_extUpdate.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"text\" ");
				}
				if (calculated_field.equals("Y")) {
					inserfield_extUpdate.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					inserfield_extUpdate
							.append("name=" + mapping4 + " id=\"" + ext_auto_id + "\" class=\"col-xs-12 col-sm-4\" ");
				}

				/*
				 * if(mandatory.equals("Y")) { System.out.
				 * println("-------------in loop 1 part 2 required-------------------");
				 * form.append("required"); }
				 */

				/*
				 * if(readonly.equals("Y")) { System.out.
				 * println("-------------in loop 1 part 2 required-------------------");
				 * form.append("  readonly"); }
				 */
				inserfield_extUpdate.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

			}

			/*----------------------------for dropdown-----------------------------------*/

			if (data_type4.equals("dropdown")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				if (dependent.equals("Y")) {

					inserfield_extUpdate.append("\n<%String\t" + ext_dependent_id + " = \"" + dependent_sp + "\"; %>"
							+ "\n<%session.setAttribute(\"" + ext_dependent_id + "\",  " + ext_dependent_id + "); %>");
					inserfield_extUpdate.append("\n<select  value=\"${" + table_name_lower + "_updt." + mapping4
							+ "}\" name=\"state_name\" id=\"" + mapping4
							+ "\"  class=\"col-xs-3 col-sm-3 form-control state\">" + "\n<option >---select---</option>"
							+ "\n</select>");
				} else {
					inserfield_extUpdate.append("\n<%String\t" + mapping4 + " = \"" + sp_name + "\"; %>"
							+ "\n<%session.setAttribute(\"" + mapping4 + "\",  " + mapping4 + "); %>");

					inserfield_extUpdate.append("\n<select  value=\"${" + table_name_lower + "_updt." + mapping4
							+ "}\" name=\"drop_value\" id=\"" + mapping4
							+ "\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
					inserfield_extUpdate.append("\t" + mapping4);
					inserfield_extUpdate
							.append("\" >" + "\n<option >---select---</option>" + "\n<option value=\"${drop_value}\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						inserfield_extUpdate.append("required");
					}

					if (readonly.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						inserfield_extUpdate.append("readonly");
					}
					inserfield_extUpdate.append(">${drop_value}</option>\n" + "</select>\n");
				}
				inserfield_extUpdate.append("</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			/*------------------------ for date----------- */

			if (data_type4.equals("date")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
								+ "<div class=\"input-group input-append date\" id=\"datePicker\">");

				inserfield_extUpdate.append("\n<input  value=\"${" + table_name_lower + "_updt." + mapping4 + "}\"");

				if (hidden.equals("Y")) {
					inserfield_extUpdate.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"text\" ");
				}

				inserfield_extUpdate.append(
						"name=" + mapping4 + " id=" + mapping4 + " placeholder=\"picup Date\" class=\"form-control\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				if (readonly.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("  readonly");
				}
				inserfield_extUpdate.append(
						"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
								+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// -----------------------------------for toggle
			// button-----------------------------

			if (data_type4.equals("togglebutton")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extUpdate.append("\n<input  value=\"${" + table_name_lower + "_updt." + mapping4 + "}\"");

				if (hidden.equals("Y")) {
					inserfield_extUpdate.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"checkbox\" ");
				}

				inserfield_extUpdate.append("name=" + mapping4 + " id=" + mapping4
						+ "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				inserfield_extUpdate.append("/>\n" + "\n<span class=\"lbl\"></span>"

						+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// ------------------------------for
			// checkbox---------------------------------------

			if (data_type4.equals("checkbox")) {
				inserfield_extUpdate
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extUpdate.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extUpdate
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extUpdate.append("\n<input   value=\"${" + table_name_lower + "_updt." + mapping4
						+ "}\" style=\"width:10%; margin-left:20px\" class=\"form-control\"");

				if (hidden.equals("Y")) {
					inserfield_extUpdate.append("  type=\"hidden\" ");
				} else {
					inserfield_extUpdate.append("  type=\"checkbox\" ");
				}

				inserfield_extUpdate
						.append("name=" + mapping4 + " id=" + mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extUpdate.append("required");
				}

				inserfield_extUpdate.append("/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				// }//if

			} // for rows

			if (remainder == 0) {
				inserfield_extUpdate.append("\n</tr>");
				inserfield_extUpdate.append("\n<tr>");
			}
		} // close for loop

		inserfield_extUpdate.append("\n</table>");

//------------------jsp for redonly----------------------------------------------------------------------------------            

		inserfield_extReadonly.append(
				"\n<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\"> Extension</div>"
						+ "\n<table>");

		inserfield_extReadonly.append("<tr>");

		int count2 = 1;

		for (int i = 0; i < ext_user.size(); i++) {
			String field_name4 = ext_user.get(i).getField_name();
			String mapping4 = ext_user.get(i).getMapping();
			String data_type4 = ext_user.get(i).getData_type();

			String mandatory = ext_user.get(i).getMandatory();
			String hidden = ext_user.get(i).getHidden();
			String readonly = ext_user.get(i).getReadonly();
			String dependent = ext_user.get(i).getDependent();

			String calculated_field = ext_user.get(i).getCalculated_field();

			String ext_dd_id = ext_user.get(i).getExt_dd_id();
			String sp_name = ext_user.get(i).getSp_name();

			String sp_name_for_auto = ext_user.get(i).getSp_name_for_autocomplete();
			String ext_auto_id = ext_user.get(i).getExt_auto_id();

			String ext_dependent_id = ext_user.get(i).getExt_dependent_id();
			String dependent_on = ext_user.get(i).getDependent_on();
			String dependent_sp = ext_user.get(i).getDependent_sp();

			int dividend = count2;
			int divisor = 3;
			int remainder = dividend % divisor;

			System.out.println("remainder::" + remainder);
			System.out.println("before count::" + count2);
			count2++;

			// if(i<=2)
			// {
			// ------------------------------for
			// textfield------------------------------------------//
			if (data_type4.equals("textfield")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");
				inserfield_extReadonly.append("\n<input value=\"${" + table_name_lower + "_updt." + mapping4 + "}\" ");

				if (hidden.equals("Y")) {
					inserfield_extReadonly.append("  type=\"hidden\" ");
				} else {
					inserfield_extReadonly.append("  type=\"text\" ");
				}
				if (calculated_field.equals("Y")) {
					inserfield_extReadonly.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					inserfield_extReadonly.append("name=\"" + mapping4 + "\" id=\"" + mapping4
							+ "\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
				}

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extReadonly.append("required");
				}

				inserfield_extReadonly.append("  readonly");

				inserfield_extReadonly.append("/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// --------------------------for
			// autocomplete---------------------------------------------

			if (data_type4.equals("autocomplete")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">"
						+ "\n<div class=\"clearfix\">" + "<%String\t" + ext_auto_id + " = \"" + sp_name_for_auto
						+ "\"; %>" + "<%session.setAttribute(\"" + ext_auto_id + "\", " + ext_auto_id + "); %>");
				inserfield_extReadonly.append("\n<input  value=\"${" + table_name_lower + "_updt." + mapping4 + "}\"");

				if (hidden.equals("Y")) {
					inserfield_extReadonly.append("  type=\"hidden\" ");
				} else {
					inserfield_extReadonly.append("  type=\"text\" ");
				}
				if (calculated_field.equals("Y")) {
					inserfield_extReadonly.append("name=\"total\" id=\"total\" class=\"stunden form-control\" ");
				} else {
					inserfield_extReadonly
							.append("name=" + mapping4 + " id=\"" + ext_auto_id + "\" class=\"col-xs-12 col-sm-4\" ");
				}

				/*
				 * if(mandatory.equals("Y")) { System.out.
				 * println("-------------in loop 1 part 2 required-------------------");
				 * form.append("required"); }
				 */

				/*
				 * if(readonly.equals("Y")) { System.out.
				 * println("-------------in loop 1 part 2 required-------------------");
				 * form.append("  readonly"); }
				 */
				inserfield_extReadonly.append("readonly/>\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");

			}

			/*----------------------------for dropdown-----------------------------------*/

			if (data_type4.equals("dropdown")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				if (dependent.equals("Y")) {

					inserfield_extReadonly.append("\n<%String\t" + ext_dependent_id + " = \"" + dependent_sp + "\"; %>"
							+ "\n<%session.setAttribute(\"" + ext_dependent_id + "\",  " + ext_dependent_id + "); %>");
					inserfield_extReadonly.append("\n<select  value=\"${" + table_name_lower + "_updt." + mapping4
							+ "}\" name=\"state_name\" id=\"" + mapping4
							+ "\"  class=\"col-xs-3 col-sm-3 form-control state\">" + "\n<option >---select---</option>"
							+ "\n</select>");
				} else {
					inserfield_extReadonly.append("\n<%String\t" + mapping4 + " = \"" + sp_name + "\"; %>"
							+ "\n<%session.setAttribute(\"" + mapping4 + "\",  " + mapping4 + "); %>");

					inserfield_extReadonly.append("\n<select  value=\"${" + table_name_lower + "_updt." + mapping4
							+ "}\" name=\"drop_value\" id=\"" + mapping4
							+ "\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
					inserfield_extReadonly.append("\t" + mapping4);
					inserfield_extReadonly
							.append("\" >" + "\n<option >---select---</option>" + "\n<option value=\"${drop_value}\"");

					if (mandatory.equals("Y")) {
						System.out.println("-------------in loop 1 part 2 required-------------------");
						inserfield_extReadonly.append("required");
					}

					inserfield_extReadonly.append("readonly");

					inserfield_extReadonly.append(">${drop_value}</option>\n" + "</select>\n");
				}
				inserfield_extReadonly.append("</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			/*------------------------ for date----------- */

			if (data_type4.equals("date")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">"
								+ "<div class=\"input-group input-append date\" id=\"datePicker\">");

				inserfield_extReadonly.append("\n<input  value=\"${" + table_name_lower + "_updt." + mapping4 + "}\"");

				if (hidden.equals("Y")) {
					inserfield_extReadonly.append("  type=\"hidden\" ");
				} else {
					inserfield_extReadonly.append("  type=\"text\" ");
				}

				inserfield_extReadonly.append(
						"name=" + mapping4 + " id=" + mapping4 + " placeholder=\"picup Date\" class=\"form-control\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extReadonly.append("required");
				}

				inserfield_extReadonly.append("  readonly");

				inserfield_extReadonly.append(
						"/>\n" + "\n<span class=\"input-group-addon\"> <i class=\"fa fa-calendar bigger-110\"></i>"
								+ "\n</span>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// -----------------------------------for toggle
			// button-----------------------------

			if (data_type4.equals("togglebutton")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extReadonly.append("\n<input  value=\"${" + table_name_lower + "_updt." + mapping4 + "}\"");

				if (hidden.equals("Y")) {
					inserfield_extReadonly.append("  type=\"hidden\" ");
				} else {
					inserfield_extReadonly.append("  type=\"checkbox\" ");
				}

				inserfield_extReadonly.append("name=" + mapping4 + " id=" + mapping4
						+ "  class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extReadonly.append("required");
				}

				inserfield_extReadonly.append("readonly/>\n" + "\n<span class=\"lbl\"></span>"

						+ "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
			}

			// ------------------------------for
			// checkbox---------------------------------------

			if (data_type4.equals("checkbox")) {
				inserfield_extReadonly
						.append("\n<td>" + "\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
								+ "\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">" + "\n "
								+ field_name4);

				System.out.println("-------------mandatory for testing=------------------" + mandatory);

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1-------------------");
					inserfield_extReadonly.append("\n <i class=\"menu-icon fa red\"> *</i>");
				}

				inserfield_extReadonly
						.append("\n</label>" + "\n<div class=\"col-xs-12 col-sm-9\">" + "\n<div class=\"clearfix\">");

				inserfield_extReadonly.append("\n<input   value=\"${" + table_name_lower + "_updt." + mapping4
						+ "}\" style=\"width:10%; margin-left:20px\" class=\"form-control\"");

				if (hidden.equals("Y")) {
					inserfield_extReadonly.append("  type=\"hidden\" ");
				} else {
					inserfield_extReadonly.append("  type=\"checkbox\" ");
				}

				inserfield_extReadonly
						.append("name=" + mapping4 + " id=" + mapping4 + "  onblur=\"CheckUserStatusHeader1()\"");

				if (mandatory.equals("Y")) {
					System.out.println("-------------in loop 1 part 2 required-------------------");
					inserfield_extReadonly.append("required");
				}

				inserfield_extReadonly.append("readonly/>\n" + "\n</div>" + "\n</div>" + "\n</div>" + "\n</td>");
				// }//if

			} // for rows

			if (remainder == 0) {
				inserfield_extReadonly.append("\n</tr>");
				inserfield_extReadonly.append("\n<tr>");
			}

		} // close for loop

		inserfield_extReadonly.append("\n</table>");

		for (int i = 0; i < line_field.size(); i++) {
			String field_name4 = line_field.get(i).getField_name();

			grid_part_line.append("\n<th>" + "\n<center>" + field_name4 + "</center>" + "\n</th>");
		}

		for (int i = 0; i < line_field.size(); i++) {
			String field_name4 = line_field.get(i).getField_name();
			String mapping = line_field.get(i).getMapping();

			grid_part_line2.append("<td>" + "<input type=\"text\" value=\"\" id=\"" + mapping
					+ "'+$('#dynamic-table1 tr').length+'\" name=\"" + mapping + "\" style=\"width:100%;\" />"
					+ "</td>");
		}

		for (int i = 0; i < line_field.size(); i++) {

			String field_name4 = line_field.get(i).getField_name();
			String mapping = line_field.get(i).getMapping();

			grid_for_line_update.append("<td>" + "<input type=\"text\" value=\"${" + line_table_name_lower + "_updt."
					+ mapping + "}\" id=\"" + mapping + "\" name=\"" + mapping + "\" style=\"width:100%;\"  />"
					+ "</td>\n");

			grid_for_line_readonly.append("<td>" + "<input type=\"text\" value=\"${" + line_table_name_lower + "_updt."
					+ mapping + "}\" id=\"" + mapping + "\" name=\"" + mapping
					+ "\" style=\"width:100%;\"   readonly />" + "</td>\n");

			line_update_head.append("<th class=\"center\">" +field_name4 + "</th>\n");
		}

		List<Rn_Instance_Type> rn_instance_type_t = rn_instance_type_dao.userlist();

		String instance_type = rn_instance_type_t.get(0).getInstance_type();

		// -----------------creating
		// files---------------------------------------------------------------------

		if (instance_type.equals("Eclipse")) {

			String tomcatRoot1 = request.getServletContext().getRealPath("/WEB-INF/tiles/acemaster/extpages");
			System.out.println(" tomcatRoot path=" + new File(tomcatRoot1).getAbsolutePath());

			// File file4 = new File(tomcatRoot1 + "/" + view_name +"_ext.jsp");
			File file4 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/" + table_name_lower
					+ "_extension.jsp");

			File file5 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"
					+ line_table_name_lower + "_extension.jsp");
			File file6 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"
					+ line_table_name_lower + ".jsp");

			File file061 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"
					+ line_table_name_lower + "_extension2.jsp");
			
			File file7 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/" + table_name_lower
					+ "_ext_Update.jsp");
			File file8 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/" + table_name_lower
					+ "_ext_Readonly.jsp");

			File file9 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/" + table_name_lower
					+ "_add_grid.jsp");
			File file10 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/" + table_name_lower
					+ "_add_grid2.jsp");

			File file11 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"
					+ line_table_name_lower + "_ext_update.jsp");

			File file12 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"
					+ line_table_name_lower + "_ext_readonly.jsp");
			
			File file13 = new File(projectPath + "/src/main/webapp/WEB-INF/tiles/acemaster/extpages/"
					+ line_table_name_lower + "_ext_Update_head.jsp");
			
			
			System.out.println("file path" + file4);

			file4.setExecutable(true);
			file4.setReadable(true);
			file4.setWritable(true);

			if (file061.exists()) {
				file061.delete();

				System.out.println("---------file061 is deleted---------");
			}
			
			if (file13.exists()) {
				file13.delete();

				System.out.println("---------file13 is deleted---------");
			}
			
			if (file4.exists()) {
				file4.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file5.exists()) {

				file5.delete();

				System.out.println("---------file is deleted---------");
			}
			if (file6.exists()) {

				file6.delete();

				System.out.println("---------file is deleted---------");
			}
			if (file7.exists()) {

				file7.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file8.exists()) {

				file8.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file9.exists()) {

				file9.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file10.exists()) {

				file10.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file11.exists()) {

				file11.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file12.exists()) {

				file12.delete();

				System.out.println("---------file is deleted---------");
			}

			/*
			 * if (!file4.exists()) {
			 */
			
			
			try {
				file4.createNewFile();
				file5.createNewFile();
				file6.createNewFile();
				file7.createNewFile();
				file8.createNewFile();
				file061.createNewFile();

				file9.createNewFile();
				file10.createNewFile();
				file11.createNewFile();
				file12.createNewFile();
				file13.createNewFile();
				System.out.println("------file is created-------");
				System.out.println("------file6  is created-------");
			} catch (IOException e) {
				e.printStackTrace();
			}
			// }

			FileWriter fw4 = null;
			FileWriter fw5 = null;
			FileWriter fw6 = null;
			FileWriter fw7 = null;
			FileWriter fw8 = null;
			FileWriter fw061 = null;

			FileWriter fw9 = null;
			FileWriter fw10 = null;

			FileWriter fw11 = null;
			FileWriter fw12 = null;
			FileWriter fw13 = null;

			try {
				fw4 = new FileWriter(file4.getAbsoluteFile());
				fw5 = new FileWriter(file5.getAbsoluteFile());
				fw6 = new FileWriter(file6.getAbsoluteFile());
				fw7 = new FileWriter(file7.getAbsoluteFile());
				fw8 = new FileWriter(file8.getAbsoluteFile());
				fw061 = new FileWriter(file061.getAbsoluteFile());

				fw9 = new FileWriter(file9.getAbsoluteFile());
				fw10 = new FileWriter(file10.getAbsoluteFile());
				fw11 = new FileWriter(file11.getAbsoluteFile());
				fw12 = new FileWriter(file12.getAbsoluteFile());
				fw13 = new FileWriter(file13.getAbsoluteFile());
				System.out.println("file writer fw4------" + fw4);
				System.out.println("file writer fw6------" + fw6);
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedWriter bw4 = new BufferedWriter(fw4);
			BufferedWriter bw5 = new BufferedWriter(fw5);
			BufferedWriter bw6 = new BufferedWriter(fw6);
			BufferedWriter bw7 = new BufferedWriter(fw7);
			BufferedWriter bw8 = new BufferedWriter(fw8);
			BufferedWriter bw061 = new BufferedWriter(fw061);

			BufferedWriter bw9 = new BufferedWriter(fw9);
			BufferedWriter bw10 = new BufferedWriter(fw10);

			BufferedWriter bw11 = new BufferedWriter(fw11);
			BufferedWriter bw12 = new BufferedWriter(fw12);
			BufferedWriter bw13 = new BufferedWriter(fw13);
			System.out.println("buffer writer------" + bw4);
			System.out.println("buffer writer bw6------" + bw6);
			try {
				bw4.write(ext_field.toString());

				bw5.write(grid_part_line.toString());
				bw6.write(grid_part_line2.toString());
				bw061.write(grid_part_line2.toString());

				bw7.write(inserfield_extUpdate.toString());
				bw8.write(inserfield_extReadonly.toString());

				bw9.write(add_grid.toString());
				bw10.write(add_grid2.toString());

				bw11.write(grid_for_line_update.toString());
				bw12.write(grid_for_line_readonly.toString());
				bw13.write(line_update_head.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
			try {
				bw4.close();
				bw5.close();
				bw6.close();
				bw061.close();
				bw7.close();
				bw8.close();
				bw9.close();
				bw10.close();
				bw11.close();
				bw12.close();
				bw13.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (instance_type.equals("Dev")){

			File file4 = new File(devPath + "/WEB-INF/tiles/acemaster/extpages/" + table_name_lower + "_extension.jsp");
			File file7 = new File(
					devPath + "/WEB-INF/tiles/acemaster/extpages/" + table_name_lower + "_ext_Update.jsp");
			File file8 = new File(
					devPath + "/WEB-INF/tiles/acemaster/extpages/" + table_name_lower + "_ext_Readonly.jsp");
			File file9 = new File(devPath + "/WEB-INF/tiles/acemaster/extpages/" + table_name_lower + "_add_grid.jsp");
			File file10 = new File(
					devPath + "/WEB-INF/tiles/acemaster/extpages/" + table_name_lower + "_add_grid2.jsp");

			File file5 = new File(
					devPath + "/WEB-INF/tiles/acemaster/extpages/" + line_table_name_lower + "_extension.jsp");
			File file6 = new File(devPath + "/WEB-INF/tiles/acemaster/extpages/" + line_table_name_lower + ".jsp");
			File file11 = new File(
					devPath + "/WEB-INF/tiles/acemaster/extpages/" + line_table_name_lower + "_ext_update.jsp");
			File file12 = new File(
					devPath + "/WEB-INF/tiles/acemaster/extpages/" + line_table_name_lower + "_ext_readonly.jsp");

			System.out.println("file path" + file4);
			file4.setExecutable(true);
			file4.setReadable(true);
			file4.setWritable(true);

			if (file4.exists()) {
				file4.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file5.exists()) {

				file5.delete();

				System.out.println("---------file is deleted---------");
			}
			if (file6.exists()) {

				file6.delete();

				System.out.println("---------file is deleted---------");
			}
			if (file7.exists()) {

				file7.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file8.exists()) {

				file8.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file9.exists()) {

				file9.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file10.exists()) {

				file10.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file11.exists()) {

				file11.delete();

				System.out.println("---------file is deleted---------");
			}

			if (file12.exists()) {

				file12.delete();

				System.out.println("---------file is deleted---------");
			}

			/*
			 * if (!file4.exists()) {
			 */
			try {
				file4.createNewFile();
				file5.createNewFile();
				file6.createNewFile();
				file7.createNewFile();
				file8.createNewFile();

				file9.createNewFile();
				file10.createNewFile();
				file11.createNewFile();
				file12.createNewFile();
				System.out.println("------file is created-------");
				System.out.println("------file6  is created-------");
			} catch (IOException e) {
				e.printStackTrace();
			}
			// }

			FileWriter fw4 = null;
			FileWriter fw5 = null;
			FileWriter fw6 = null;
			FileWriter fw7 = null;
			FileWriter fw8 = null;

			FileWriter fw9 = null;
			FileWriter fw10 = null;

			FileWriter fw11 = null;
			FileWriter fw12 = null;

			try {
				fw4 = new FileWriter(file4.getAbsoluteFile());
				fw5 = new FileWriter(file5.getAbsoluteFile());
				fw6 = new FileWriter(file6.getAbsoluteFile());
				fw7 = new FileWriter(file7.getAbsoluteFile());
				fw8 = new FileWriter(file8.getAbsoluteFile());

				fw9 = new FileWriter(file9.getAbsoluteFile());
				fw10 = new FileWriter(file10.getAbsoluteFile());
				fw11 = new FileWriter(file11.getAbsoluteFile());
				fw12 = new FileWriter(file12.getAbsoluteFile());
				System.out.println("file writer fw4------" + fw4);
				System.out.println("file writer fw6------" + fw6);
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedWriter bw4 = new BufferedWriter(fw4);
			BufferedWriter bw5 = new BufferedWriter(fw5);
			BufferedWriter bw6 = new BufferedWriter(fw6);
			BufferedWriter bw7 = new BufferedWriter(fw7);
			BufferedWriter bw8 = new BufferedWriter(fw8);

			BufferedWriter bw9 = new BufferedWriter(fw9);
			BufferedWriter bw10 = new BufferedWriter(fw10);

			BufferedWriter bw11 = new BufferedWriter(fw11);
			BufferedWriter bw12 = new BufferedWriter(fw12);
			System.out.println("buffer writer------" + bw4);
			System.out.println("buffer writer bw6------" + bw6);
			try {
				bw4.write(ext_field.toString());

				bw5.write(grid_part_line.toString());
				bw6.write(grid_part_line2.toString());

				bw7.write(inserfield_extUpdate.toString());
				bw8.write(inserfield_extReadonly.toString());

				bw9.write(add_grid.toString());
				bw10.write(add_grid2.toString());

				bw11.write(grid_for_line_update.toString());
				bw12.write(grid_for_line_readonly.toString());
			} catch (IOException e) {

				e.printStackTrace();
			}
			try {
				bw4.close();
				bw5.close();
				bw6.close();
				bw7.close();
				bw8.close();

				bw9.close();
				bw10.close();
				bw11.close();
				bw12.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return new ModelAndView("redirect:" + table_name_lower + "_entryform");

	}

}
