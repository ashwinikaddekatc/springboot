
package com.realnet.fnd.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.dao.Rn_Forms_Setup_Dao;
import com.realnet.fnd.model.Rn_Dynamic_Form;
import com.realnet.fnd.model.Rn_Forms_Component_Setup;
import com.realnet.fnd.model.Rn_Forms_Setup;
import com.realnet.fnd.model.Rn_Sub_Menu;
import com.realnet.fnd.service.Rn_Forms_Setup_Service;

@Controller
public class Rn_Forms_Setup_Controller {
	@Autowired
	private HibernateConfiguration hibernateConfiguration;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private Rn_Forms_Setup_Service rn_forms_setup_service;
	@Autowired
	private Rn_Forms_Setup_Dao rn_forms_setup_dao;

	// ----------------------entry form sbmit------------------------------------------
	@Transactional
	@RequestMapping(value = "/dynamicFormSubmit", method = RequestMethod.POST)
	public ModelAndView saveServiceRequest(@ModelAttribute Rn_Dynamic_Form rn_forms_setup,
			BindingResult resultKoel_user, ModelMap map, HttpServletRequest request) {
		int form_id = Integer.parseInt(request.getParameter("form_id"));
		
        String comp1 = request.getParameter("comp0");
		String comp2 = request.getParameter("comp1");
		String comp3 = request.getParameter("comp2");
		String comp4 = request.getParameter("comp3");
		String comp5 = request.getParameter("comp4");
		String comp6 = request.getParameter("comp5");
		String comp7 = request.getParameter("comp6");
		String comp8 = request.getParameter("comp7");
		String comp9 = request.getParameter("comp8");
		String comp10 = request.getParameter("comp9");
		String comp11 = request.getParameter("comp10");
		String comp12 = request.getParameter("comp11");
		String comp13 = request.getParameter("comp12");
		String comp14 = request.getParameter("comp13");
		String comp15 = request.getParameter("comp14");
		String comp16 = request.getParameter("comp15");
		String comp17 = request.getParameter("comp16");
		String comp18 = request.getParameter("comp17");
		String comp19 = request.getParameter("comp18");
		String comp20 = request.getParameter("comp19");
		String comp21 = request.getParameter("comp20");
		String comp22 = request.getParameter("comp21");
		String comp23 = request.getParameter("comp22");
		String comp24 = request.getParameter("comp23");
		String comp25 = request.getParameter("comp24");
		String comp26 = request.getParameter("comp25");
		String comp27 = request.getParameter("comp26");
		String comp28 = request.getParameter("comp27");
		String comp29 = request.getParameter("comp28");
		String comp30 = request.getParameter("comp29");

		rn_forms_setup.setForm_id(form_id);
		rn_forms_setup.setComp1(comp1);
		rn_forms_setup.setComp2(comp2);
		rn_forms_setup.setComp3(comp3);
		rn_forms_setup.setComp4(comp4);
		rn_forms_setup.setComp5(comp5);
		rn_forms_setup.setComp6(comp6);
		rn_forms_setup.setComp7(comp7);
		rn_forms_setup.setComp8(comp8);
		rn_forms_setup.setComp9(comp9);
		rn_forms_setup.setComp10(comp10);
		rn_forms_setup.setComp11(comp11);
		rn_forms_setup.setComp12(comp12);
		rn_forms_setup.setComp13(comp13);
		rn_forms_setup.setComp14(comp14);
		rn_forms_setup.setComp15(comp15);
		rn_forms_setup.setComp16(comp16);
		rn_forms_setup.setComp17(comp17);
		rn_forms_setup.setComp18(comp18);
		rn_forms_setup.setComp19(comp19);
		rn_forms_setup.setComp20(comp20);
		rn_forms_setup.setComp21(comp21);
		rn_forms_setup.setComp22(comp22);
		rn_forms_setup.setComp23(comp23);
		rn_forms_setup.setComp24(comp24);
		rn_forms_setup.setComp25(comp25);
		rn_forms_setup.setComp26(comp26);
		rn_forms_setup.setComp27(comp27);
		rn_forms_setup.setComp28(comp28);
		rn_forms_setup.setComp29(comp29);
		rn_forms_setup.setComp30(comp30);
		
		hibernateTemplate.saveOrUpdate(rn_forms_setup);
		return new ModelAndView("redirect:rn_forms_setup_grid_view");

	}

	@RequestMapping("/rn_forms_setup_entryform")
	public ModelAndView input_form3(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("Rn_Forms_Setup_Entry");
	}
	
	
	
	@RequestMapping(value ="/testDynamicForm/{form_name}",method = RequestMethod.GET)
	public ModelAndView dynamicForm(@PathVariable("form_name") String fname, ModelMap map) 
	{
		  System.out.println("Form name::"+fname);
          return new ModelAndView(""+fname+"");
	}

	// -----------------------------------for grid view only--------------------------------------------------

	@RequestMapping(value = "/rn_forms_setup_grid_view")
	public ModelAndView rn_forms_setup_Details(ModelAndView model) throws IOException {
		List<Rn_Forms_Setup> rn_forms_setup = rn_forms_setup_dao.userlist();
		model.addObject("rn_forms_setup", rn_forms_setup);
		System.out.println("sujit");
		model.setViewName("Rn_Forms_Setup_Grid");
		return model;
	}

	// -----------------------for prefield  part-----------------------------------

	@RequestMapping(value = "/rn_forms_setup_update", method = RequestMethod.GET)
	public ModelAndView loadReport1(@RequestParam(value = "form_id") String form_id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(form_id);
		HttpSession session = request.getSession();
		session.setAttribute("u_id", u_id);
		Rn_Forms_Setup rn_forms_setup1 = new Rn_Forms_Setup();
		Rn_Forms_Component_Setup rn_forms_component_setup_t = new Rn_Forms_Component_Setup();
		map.addAttribute("rn_forms_setup1", rn_forms_setup1);
		List<Rn_Forms_Setup> rn_forms_setup = rn_forms_setup_service.prefield(u_id);
		map.addAttribute("rn_forms_setup", rn_forms_setup);
		List<Rn_Forms_Component_Setup> rn_forms_component_setup = rn_forms_setup_service.update_group_menu_line(u_id);
		map.addAttribute("rn_forms_component_setup", rn_forms_component_setup);
		map.addAttribute("rn_forms_component_setup_t", rn_forms_component_setup_t);
		return new ModelAndView("Rn_Forms_Setup_Update");
	}

	// --------------------for readonly------------------------------------------------

	@RequestMapping(value = "/rn_forms_setup_readonly", method = RequestMethod.GET)
	public ModelAndView loadReport2(@RequestParam(value = "form_id") String form_id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(form_id);
		Rn_Forms_Setup rn_forms_setup_t = new Rn_Forms_Setup();
		Rn_Forms_Component_Setup rn_forms_component_setup_t = new Rn_Forms_Component_Setup();
		map.addAttribute("rn_forms_setup_t", rn_forms_setup_t);
		List<Rn_Forms_Setup> rn_forms_setup = rn_forms_setup_service.prefield(u_id);
		map.addAttribute("rn_forms_setup", rn_forms_setup);
		List<Rn_Forms_Component_Setup> rn_forms_component_setup = rn_forms_setup_service.update_group_menu_line(u_id);
		map.addAttribute("rn_forms_component_setup", rn_forms_component_setup);
		map.addAttribute("rn_forms_component_setup_t", rn_forms_component_setup_t);
		return new ModelAndView("Rn_Forms_Setup_Readonly");
	}

	// --------------------------sbmit update part---------------------------------------------------

	@RequestMapping(value = "/rn_forms_setup_update_submit", method = RequestMethod.POST)
	public ModelAndView saveReportRegister(@ModelAttribute Rn_Forms_Setup rn_forms_setup_t,
			BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
		int report = 0;
		String[] form_id = request.getParameterValues("form_id");
		String[] form_name = request.getParameterValues("form_name");
		String[] form_desc = request.getParameterValues("form_desc");
		String[] related_to = request.getParameterValues("related_to");
		String[] page_event = request.getParameterValues("page_event");
		String[] button_caption = request.getParameterValues("button_caption");
		int rowcount = form_id.length;
		report = rn_forms_setup_service.save(rowcount, form_id, form_name, form_desc, related_to, page_event,
				button_caption);
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("report", report);
		Rn_Forms_Setup rn_forms_setup = new Rn_Forms_Setup();
		map.addAttribute("rn_forms_setup", rn_forms_setup);
		List<Rn_Forms_Setup> rn_forms_setup1 = rn_forms_setup_service.userlist();
		map.addAttribute("rn_forms_setup1", rn_forms_setup1);
		return new ModelAndView("redirect:rn_forms_setup_grid_view");
	}

	// -----------------------------header line submit------------------------------------------------

	@RequestMapping("/rn_forms_component_setup_submit_headerline")
	public ModelAndView saveGroupReport(@ModelAttribute Rn_Forms_Setup rn_forms_setup,
			BindingResult resultMenuGroupHeader, Rn_Forms_Component_Setup rn_forms_component_setup_t,
			BindingResult resultReportMenuLines, ModelMap map, HttpServletRequest request) throws ParseException {

		if (request.getParameter("form_id") != "") {
			int id = Integer.parseInt(request.getParameter("form_id"));
			rn_forms_setup.setForm_id(id);
		}
		String form_name = request.getParameter("form_name");
		String form_desc = request.getParameter("form_desc");
		String related_to = request.getParameter("related_to");
		String page_event = request.getParameter("page_event");
		String button_caption = request.getParameter("button_caption");
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

		rn_forms_setup.setForm_name(form_name);
		rn_forms_setup.setForm_desc(form_desc);
		rn_forms_setup.setRelated_to(related_to);
		rn_forms_setup.setPage_event(page_event);
		rn_forms_setup.setButton_caption(button_caption);
		rn_forms_setup.setAttribute1(attribute1);
		rn_forms_setup.setAttribute2(attribute2);
		rn_forms_setup.setAttribute3(attribute3);
		rn_forms_setup.setAttribute4(attribute4);
		rn_forms_setup.setAttribute5(attribute5);
		rn_forms_setup.setAttribute6(attribute6);
		rn_forms_setup.setAttribute7(attribute7);
		rn_forms_setup.setAttribute8(attribute8);
		rn_forms_setup.setAttribute9(attribute9);
		rn_forms_setup.setAttribute10(attribute10);
		rn_forms_setup.setAttribute11(attribute11);
		rn_forms_setup.setAttribute12(attribute12);
		rn_forms_setup.setAttribute13(attribute13);
		rn_forms_setup.setAttribute14(attribute14);
		rn_forms_setup.setAttribute15(attribute15);
		rn_forms_setup.setFlex1(flex1);
		rn_forms_setup.setFlex2(flex2);
		rn_forms_setup.setFlex3(flex3);
		rn_forms_setup.setFlex4(flex4);
		rn_forms_setup.setFlex5(flex5);
		int form_id = rn_forms_setup_service.saveheader(rn_forms_setup);
		String component_id[] = request.getParameterValues("component_id");
		String label[] = request.getParameterValues("label");
		String type[] = request.getParameterValues("type");
		String mandatory[] = request.getParameterValues("mandatory");
		String readonly[] = request.getParameterValues("readonly");
		String values[] = request.getParameterValues("values");
		String sp[] = request.getParameterValues("sp");
		int rowcount = component_id.length;
		String check = request.getParameter("menuupdt");
		map.addAttribute("check", check);
		int group_line = rn_forms_setup_service.addmenuGroupLine(rowcount, form_id, component_id, label, type,
				mandatory, readonly, values, sp);
		map.addAttribute("group_line", group_line);
		List<Rn_Forms_Setup> rn_forms_setup1 = rn_forms_setup_dao.userlist();
		map.addAttribute("rn_forms_setup1", rn_forms_setup1);
		return new ModelAndView("redirect:rn_forms_setup_grid_view");
	}

	
	
	// ----------------------dynamic form builder by  ganesh--------------------------------------

	@Transactional
	@RequestMapping(value = "/buildDynamicForm", method = RequestMethod.GET)
	public ModelAndView writeView(HttpServletRequest request, HttpServletResponse response) throws IOException {

		StringBuilder jspFinal = new StringBuilder();
		StringBuilder importsection = new StringBuilder();
		StringBuilder headsection = new StringBuilder();
		StringBuilder form = new StringBuilder();

		HttpSession session = request.getSession(false);
		int u_id = (Integer) session.getAttribute("u_id");
		List<Rn_Forms_Setup> formDetails = rn_forms_setup_service.prefield(u_id);
		List<Rn_Forms_Component_Setup> formComponent = rn_forms_setup_service.update_group_menu_line(u_id);

		String formName = formDetails.get(0).getForm_name();
		String upperFormName = formName.toUpperCase();
		
		String buttonCaption = formDetails.get(0).getButton_caption();
		

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
						    +" \n</head>");
		            
		
		
		 form.append("\n<form action=\"dynamicFormSubmit\" class=\"form-horizontal\" id=\"Regi\" method=\"Post\">"
		    		+ "\n<input type=\"hidden\" name=\"test\" id=\"test\" value=\"\" />"
		    		+ "<input type=\"hidden\" name=\"form_id\" id=\"form_id\" value=\""+u_id+"\" />"
		    		+ " \n<table>"
					+ "\n<tr>");
		 
					for (int i = 0; i < formComponent.size(); i++) 
					{
						String label = formComponent.get(i).getLabel();
						String type = formComponent.get(i).getType();
						String mandatory = formComponent.get(i).getMandatory();
						String readonly = formComponent.get(i).getReadonly();
						String drop_value = formComponent.get(i).getDrop_values();
						
						System.out.println("Label Name::"+label);
						
						if(i<=2)
			  		    {
							if(type.equals("Textfield"))
			                {
								form.append("\n<td>"
										    +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											+"\n "+label);
                                              if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											

											form.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">");
												 form.append("\n<input" );
												 form.append("  type=\"text\" ");
												 form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														
													
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
							if(type.equals("Dropdown"))
			                {
								form.append("\n<td>"
										+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											   +"\n "+label);
                                  
								     System.out.println("-------------mandatory for testing=------------------"+mandatory);

											  if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											

											form.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">");
													
												
												
					
										    form.append("\n<select name=\"comp"+i+"\" id=\"comp"+i+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
										    form.append( "\t");
										    form.append( "\" >"
                                              +"\n<option >---select---</option>" );
												
													
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
										  
                                              form.append("</div>"
											+"\n</div>"
										+"\n</div>"
										+"\n</td>");
								
								
								
								
			                }
							if(type.equals("Date"))
			                {
								form.append("\n<td>"
										    +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											+"\n "+label);
                                            if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
											  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											  form.append("\n</label>"
													      +"\n<div class=\"col-xs-12 col-sm-9\">"
												          +"\n<div class=\"clearfix\">"
												          +"<div class=\"input-group input-append date\" id=\"datePicker\">");
											
											
											  form.append("\n<input  " );
											  form.append("  type=\"text\" ");
											  form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\" placeholder=\"picup Date\" class=\"form-control\"");
														
													
											if(mandatory.equals("Y"))
											{ 	  
											  form.append(" required");
											}
											
											if(readonly.equals("Y"))
											{
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
							
							if(type.equals("Togglebutton"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+label);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													form.append("\n<input" );
													form.append("  type=\"checkbox\" ");
													form.append( "name=\"comp"+i+"\" id=\"comp"+i+" \" class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
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
							
							
							if(type.equals("Checkbox"))
			                {           form.append("\n<td>"
									+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
									+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
									   +"\n "+label);
                          
						     System.out.println("-------------mandatory for testing=------------------"+mandatory);

									  if(mandatory.equals("Y"))
									  {   System.out.println("-------------in loop 1-------------------");
										  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
									  }
									

									form.append("\n</label>"
											+"\n<div class=\"col-xs-12 col-sm-9\">"
										+"\n<div class=\"clearfix\">");
									
									
										 form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
										 form.append("  type=\"checkbox\" ");
										 form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\"  onblur=\"CheckUserStatusHeader1()\"");
												
											
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
							if(type.equals("Texarea"))
			                {
								
			                }
						 }
					}
					
                    form.append("\n</tr>");
				    form.append("\n<tr>");
				    
				    
				    //2nd for loop
				    for (int i = 0; i < formComponent.size(); i++) 
					{
						String label = formComponent.get(i).getLabel();
						String type = formComponent.get(i).getType();
						String mandatory = formComponent.get(i).getMandatory();
						String readonly = formComponent.get(i).getReadonly();
						String drop_value = formComponent.get(i).getDrop_values();
						
						if(i>2 && i<=5)
			  		    {
							if(type.equals("Textfield"))
			                {
								form.append("\n<td>"
										    +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											+"\n "+label);
                                              if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											

											form.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">");
												 form.append("\n<input" );
												 form.append("  type=\"text\" ");
												 form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														
													
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
							if(type.equals("Dropdown"))
			                {
								form.append("\n<td>"
										+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											   +"\n "+label);
                                  
								     System.out.println("-------------mandatory for testing=------------------"+mandatory);

											  if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											

											form.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">");
													
												
												
					
										    form.append("\n<select name=\"comp"+i+"\" id=\"comp"+i+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
										    form.append( "\t");
										    form.append( "\" >"
                                              +"\n<option >---select---</option>" );
												
													
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
										  
                                              form.append("</div>"
											+"\n</div>"
										+"\n</div>"
										+"\n</td>");
								
								
								
								
			                }
							if(type.equals("Date"))
			                {
								form.append("\n<td>"
										    +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											+"\n "+label);
                                            if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
											  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											  form.append("\n</label>"
													      +"\n<div class=\"col-xs-12 col-sm-9\">"
												          +"\n<div class=\"clearfix\">"
												          +"<div class=\"input-group input-append date\" id=\"datePicker\">");
											
											
											  form.append("\n<input  " );
											  form.append("  type=\"text\" ");
											  form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\" placeholder=\"picup Date\" class=\"form-control\"");
														
													
											if(mandatory.equals("Y"))
											{ 	  
											  form.append(" required");
											}
											
											if(readonly.equals("Y"))
											{
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
							
							if(type.equals("Togglebutton"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+label);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													form.append("\n<input" );
													form.append("  type=\"checkbox\" ");
													form.append( "name=\"comp"+i+"\" id=\"comp"+i+" \" class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
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
							
							
							if(type.equals("Checkbox"))
			                {           form.append("\n<td>"
									+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
									+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
									   +"\n "+label);
                          
						     System.out.println("-------------mandatory for testing=------------------"+mandatory);

									  if(mandatory.equals("Y"))
									  {   System.out.println("-------------in loop 1-------------------");
										  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
									  }
									

									form.append("\n</label>"
											+"\n<div class=\"col-xs-12 col-sm-9\">"
										+"\n<div class=\"clearfix\">");
									
									
										 form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
										 form.append("  type=\"checkbox\" ");
										 form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\"  onblur=\"CheckUserStatusHeader1()\"");
												
											
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
							if(type.equals("Texarea"))
			                {
								
			                }
						 }
					}
				    
				    form.append("\n</tr>");
				    form.append("\n<tr>"); 
				    
				    //3rd for loop
				    for (int i = 0; i < formComponent.size(); i++) 
					{
						String label = formComponent.get(i).getLabel();
						String type = formComponent.get(i).getType();
						String mandatory = formComponent.get(i).getMandatory();
						String readonly = formComponent.get(i).getReadonly();
						String drop_value = formComponent.get(i).getDrop_values();
						
						if(i>5 && i<=8)
			  		    {
							if(type.equals("Textfield"))
			                {
								form.append("\n<td>"
										    +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											+"\n "+label);
                                              if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											

											form.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">");
												 form.append("\n<input" );
												 form.append("  type=\"text\" ");
												 form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\" class=\"col-xs-12 col-sm-4\" pattern=\"[A-Za-z]{1,30}\"");
														
													
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
							if(type.equals("Dropdown"))
			                {
								form.append("\n<td>"
										+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											   +"\n "+label);
                                  
								     System.out.println("-------------mandatory for testing=------------------"+mandatory);

											  if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
												  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											

											form.append("\n</label>"
													+"\n<div class=\"col-xs-12 col-sm-9\">"
												+"\n<div class=\"clearfix\">");
													
												
												
					
										    form.append("\n<select name=\"comp"+i+"\" id=\"comp"+i+"\"  value=\"${drop_value}\" class=\"col-xs-3 col-sm-3 form-control");
										    form.append( "\t");
										    form.append( "\" >"
                                              +"\n<option >---select---</option>" );
												
													
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
										  
                                              form.append("</div>"
											+"\n</div>"
										+"\n</div>"
										+"\n</td>");
								
								
								
								
			                }
							if(type.equals("Date"))
			                {
								form.append("\n<td>"
										    +"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
											+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
											+"\n "+label);
                                            if(mandatory.equals("Y"))
											  {   System.out.println("-------------in loop 1-------------------");
											  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
											  }
											  form.append("\n</label>"
													      +"\n<div class=\"col-xs-12 col-sm-9\">"
												          +"\n<div class=\"clearfix\">"
												          +"<div class=\"input-group input-append date\" id=\"datePicker\">");
											
											
											  form.append("\n<input  " );
											  form.append("  type=\"text\" ");
											  form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\" placeholder=\"picup Date\" class=\"form-control\"");
														
													
											if(mandatory.equals("Y"))
											{ 	  
											  form.append(" required");
											}
											
											if(readonly.equals("Y"))
											{
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
							
							if(type.equals("Togglebutton"))
	                           {
	                                  form.append("\n<td>"
											+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
												+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
												   +"\n "+label);
	                                  
									     System.out.println("-------------mandatory for testing=------------------"+mandatory);

												  if(mandatory.equals("Y"))
												  {   System.out.println("-------------in loop 1-------------------");
													  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
												  }
												

												form.append("\n</label>"
														+"\n<div class=\"col-xs-12 col-sm-9\">"
													+"\n<div class=\"clearfix\">");
												
												
													form.append("\n<input" );
													form.append("  type=\"checkbox\" ");
													form.append( "name=\"comp"+i+"\" id=\"comp"+i+" \" class=\"ace ace-switch ace-switch-6\" onblur=\"CheckUserStatus()\"");
															
														
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
							
							
							if(type.equals("Checkbox"))
			                {           form.append("\n<td>"
									+"\n<div class=\"form-group\" style=\"margin-left: 10%; margin-right: 10%;\">"
									+"\n<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\">"
									   +"\n "+label);
                          
						     System.out.println("-------------mandatory for testing=------------------"+mandatory);

									  if(mandatory.equals("Y"))
									  {   System.out.println("-------------in loop 1-------------------");
										  form.append ("\n <i class=\"menu-icon fa red\"> *</i>");
									  }
									

									form.append("\n</label>"
											+"\n<div class=\"col-xs-12 col-sm-9\">"
										+"\n<div class=\"clearfix\">");
									
									
										 form.append("\n<input  style=\"width:10%; margin-left:20px\" class=\"form-control\"" );
										 form.append("  type=\"checkbox\" ");
										 form.append( "name=\"comp"+i+"\" id=\"comp"+i+"\"  onblur=\"CheckUserStatusHeader1()\"");
												
											
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
							if(type.equals("Texarea"))
			                {
								
			                }
						 }
					}
				    form.append("\n</tr>");
				    form.append("\n</table>\n<div class=\"hr hr-dotted\"></div>"
				    		+ "\n<div class=\"wizard-actions\">"
                                                            +"\n<button type=\"submit\" class=\"btn btn-success center\" onclick=\"submitForms()\">"
															  +buttonCaption
															+"\n</button>"
														+"\n</div> " 
						  +"\n</form>");
				    
				    
		
		
		jspFinal.append(importsection+" \n"+ headsection+"\n<body>\n<div class=\"main-container\" id=\"main-container\">"
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
						+"\n<h1>"
                        +upperFormName
						+"</h1>"
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
												
													+"<div class=\"table-header\" style=\"margin-bottom: 30px; margin-top: 30px;\">"
							                        +"User Credentials "
							                         +"</div>"
													
													+form
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
                                                       +"\n</div>"
                                                       + "<script src=\"<c:url value='/resources/assets/js/bootstrap-datepicker.min.js'/>\" type=\"text/javascript\">"
                                                       + "\n</script>"
                                                       +"\n</body>\n</html>");
		
		
		
		//tiles part
		
		
		System.out.println("file------begin");
	    
	   
    	

    	
	    File temp = new File( "F:/Ganesh/MyProject/REAL_NET_GB1/src/main/webapp/WEB-INF/tiles.xml");
	    
	    File newtemp = new File( "F:/Ganesh/MyProject/REAL_NET_GB1/src/main/webapp/WEB-INF/xyz.xml");
	   

	    
		BufferedReader br = new BufferedReader(new FileReader(temp));
	    BufferedWriter bw1 = new BufferedWriter(new FileWriter(newtemp));
	    
	    
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
	
	    
	    
	    
	       
           
        StringBuilder tiles=new StringBuilder();	
        
	    try
        {
        	
        tiles.append("\n<definition name=\""+formName+"\" extends=\"acemaster.definition\">" 
			        +"\n<put-attribute name=\"title\" value=\"WASIB\"/>"
			        +"\n<put-attribute name=\"body\" value=\"/WEB-INF/tiles/acemaster/DynamicForm/"+formName+".jsp\"/>"
			      +"\n</definition>"
			      + "\n</tiles-definitions>");
		String filename="F:/Ganesh/MyProject/REAL_NET_GB1/src/main/webapp/WEB-INF/tiles.xml";
		    

           FileWriter fw=new FileWriter(filename,true);    
           fw.write(tiles.toString());    
           fw.close(); 
           
           
        } catch (IOException e) {  
            e.printStackTrace();  
        }
		
		
	    
		
		

		try {
			File file1 = new File(
					"F:/Ganesh/MyProject/REAL_NET_GB1/src/main/webapp/WEB-INF/tiles/acemaster/DynamicForm/" + formName
							+ ".jsp");
			if (!file1.exists()) {
				file1.createNewFile();
			}
			FileWriter fw = new FileWriter(file1.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jspFinal.toString());
			bw.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		Rn_Sub_Menu subMenu=new Rn_Sub_Menu();
	    subMenu.setMain_menu_id(78);
	    subMenu.setSub_menu_name(formName);
	    subMenu.setSub_menu_action_name("testDynamicForm");
	    hibernateTemplate.saveOrUpdate(subMenu);
		
		
		

		return new ModelAndView("redirect:rn_forms_setup_grid_view");
	}

}