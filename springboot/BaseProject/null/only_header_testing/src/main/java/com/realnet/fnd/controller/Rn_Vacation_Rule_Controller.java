package com.realnet.fnd.controller;


import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Menu_Group_Autofill;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Group_Line;
import com.realnet.fnd.service.Rn_Menu_Register_Service;
import com.realnet.fnd.service.Rn_Vacation_Rule_Service;

@Controller
public class Rn_Vacation_Rule_Controller {
	@Autowired
	Rn_Vacation_Rule_Service rn_vacation_rule_service;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private HibernateConfiguration hibernateConfiguration;
	
	@Autowired
	private Rn_Menu_Register_Service rn_menu_register_service;
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	

	
	
	@RequestMapping(value="/menuGroupHelp")
	public ModelAndView forgotpassword(ModelAndView model,ModelMap map) throws IOException
	{		
		return new ModelAndView("MenuGroupHelp");
		
	}
	
	// -----------------------------------for grid view only--------------------------------------------------

	 
	 @RequestMapping("/rn_menu_group_grid")
		public ModelAndView newMenuGroupGrid(HttpServletRequest request, ModelMap map, Model model) {
			
		 Rn_Menu_Group_Header rn_menu_group_header = new Rn_Menu_Group_Header();
		 Rn_Menu_Group_Line rn_menu_group_line = new Rn_Menu_Group_Line();
		 
		 
		 List<Rn_Menu_Group_Autofill> rn_menu_group_autofill = rn_vacation_rule_service.get_group_menu();
		 
		 model.addAttribute("rn_menu_group_autofill",rn_menu_group_autofill);
		 model.addAttribute("rn_menu_group_header", rn_menu_group_header);
		 model.addAttribute("rn_menu_group_line", rn_menu_group_line);
			
			return new ModelAndView("Rn_Menu_Group_Grid");
		}
	 
		// ----------------------entry form ------------------------------------------
		
	 @RequestMapping("/rn_menu_group_entryform")
	 public ModelAndView newMenuGroup(HttpServletRequest request, ModelMap map, Model model) {
			System.out.println("Load");
			
			 Rn_Menu_Group_Header rn_menu_group_header = new Rn_Menu_Group_Header();
			 Rn_Menu_Group_Line rn_menu_group_line = new Rn_Menu_Group_Line();
			
			 model.addAttribute("rn_menu_group_header", rn_menu_group_header);
			 model.addAttribute("rn_menu_group_line", rn_menu_group_line);
			
	        return new ModelAndView("Rn_Menu_Group_Entry");
		}
	 
		// ----------------------entry form sbmit------------------------------------------
	 
	 @RequestMapping("/rn_menu_group_entryform_submit")
	 public ModelAndView saveManuGroup(@ModelAttribute Rn_Menu_Group_Header rn_menu_group_header, BindingResult resultMenuGroupHeader,
			 Rn_Menu_Group_Line rn_menu_group_line, BindingResult resultReportMenuLines,
				Model model, HttpServletRequest request) throws ParseException
	 {		 		
		 String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		 {			 
			 int id=0;
			
			if(request.getParameter("menu_group_header_id")!="")
			{
			  id=Integer.parseInt(request.getParameter("menu_group_header_id"));
			  rn_menu_group_header.setMenu_group_header_id(id);
			}
			
			
			 String menu_name=request.getParameter("menu_name");
			 String description=request.getParameter("description");
			 String flag=request.getParameter("header_active");
			 
			 rn_menu_group_header.setMenu_name(menu_name);
			 rn_menu_group_header.setDescription(description);
			 rn_menu_group_header.setHeader_active(flag);
			 
			 String startdate = request.getParameter("header_start_date");
				if(startdate!=null)
				{
					Date sdate = null;
					try {
						sdate = new SimpleDateFormat("dd-MM-yyyy").parse(startdate);
			           } catch (ParseException e) {

			               e.printStackTrace();
			           } 			         
					rn_menu_group_header.setHeader_start_date(sdate);
				}
			 

			 
				 String enddate = request.getParameter("header_end_date");
					if(enddate!=null){
						Date edate = null;
						try {
							edate = new SimpleDateFormat("dd-MM-yyyy").parse(enddate);
				           } catch (ParseException e) {

				               e.printStackTrace();
				           } 			         
						rn_menu_group_header.setHeader_end_date(edate);
					}
				 
				 
			int menu_group_id=rn_vacation_rule_service.savemenu(rn_menu_group_header);
			
			System.out.println("-----menu group id value----------------"+menu_group_id);
			
			String report_group_line_id1[]=request.getParameterValues("menu_group_line_id");
		 	String name1[]=request.getParameterValues("menu_id");
			String active1[]=request.getParameterValues("para");
		
			
			System.out.println(" valu of line id"+report_group_line_id1);
			
			int rowcount=report_group_line_id1.length;
			
			System.out.println("Rowcount  "+rowcount);
			
			String check = request.getParameter("menuupdt");
			model.addAttribute("check", check);
			
			int group_line = rn_vacation_rule_service.addmenuGroupLine(rowcount, menu_group_id, report_group_line_id1, name1, active1);
			model.addAttribute("group_line", group_line);
			  
			
		    List<Rn_Menu_Group_Autofill> rn_menu_group_autofill = rn_vacation_rule_service.get_group_menu();
			model.addAttribute("rn_menu_group_autofill",rn_menu_group_autofill);
			 
		 }	
		 return new ModelAndView("Rn_Menu_Group_Grid");
	 }
	 
	 
		// -----------------------for prefield part-----------------------------------

	 @RequestMapping("/rn_menu_group_update")
	 public ModelAndView updategroupreport(HttpServletRequest request, ModelMap map, Model model) 
	 {
			System.out.println("updatemenugroup started in VacationRuleController ");
			

			
			int menu_grp_id=Integer.parseInt(request.getParameter("Grp_menu_Number"));
			
			 List<Rn_Menu_Group_Autofill> rn_menu_group_autofill = rn_vacation_rule_service.update_group_menu(menu_grp_id);
			 List<Rn_Menu_Group_Autofill> rn_menu_group_autofill_line = rn_vacation_rule_service.update_group_menu_line(menu_grp_id);

			 model.addAttribute("rn_menu_group_autofill", rn_menu_group_autofill);
			 model.addAttribute("rn_menu_group_autofill_line", rn_menu_group_autofill_line);
			 
			
	        return new ModelAndView("Rn_Menu_Group_Update");
		}
	 
	 
	 //call sp
	 
	 
	 
	 @RequestMapping(value = "/loadMenugroupname", method = RequestMethod.GET)
     public
     @ResponseBody
     List<Rn_Menu_Group_Autofill> loadgroup(HttpServletRequest request, HttpServletResponse response)  {
    	
    	 
    	 System.out.println("start controller in menu");
    	 List<Rn_Menu_Group_Autofill> rn_menu_group_autofill = new ArrayList<>();
    	 String json = null;
    	 CallableStatement cStmt;
    	 try {
    		 
    		 cStmt = hibernateConfiguration.dataSource().getConnection().prepareCall("{call RN_SP_MENU_REGISTER()}");
    	        ResultSet rs = cStmt.executeQuery();
    	       
    	 		while (rs.next()) {
    	 			int data = rs.getInt(1);
    				String data1 = rs.getString(2);
    				rn_menu_group_autofill.add(new Rn_Menu_Group_Autofill(data, data1));
    	 		}
    	        } catch (SQLException e) {
    	        e.printStackTrace();
    	        }
    	        catch (Exception e) {
    	        System.out.println(e.getMessage());
    	        }
    	 	for (Rn_Menu_Group_Autofill vc : rn_menu_group_autofill) {
				System.out.println(vc.getMain_menu_name()+ " data" + vc.getMain_menu_name());
			}				
			
	
			return rn_menu_group_autofill;
     }
     
     
     @RequestMapping(value = "/newmenuGroupRegister", method = RequestMethod.POST)
 	public ModelAndView saveGroupReport(@ModelAttribute Rn_Menu_Group_Header menuGroupHeader,
 			BindingResult resultReportGroupHeader, @ModelAttribute Rn_Menu_Group_Line menuGroupLine,
 			BindingResult resultReportGroupLines, ModelMap map, HttpServletRequest request) throws ParseException {
 		int group_header = 0;
 		
 		String h_status = request.getParameter("header_active");
 		menuGroupHeader.setHeader_active(h_status);
 	
 		String startdate = request.getParameter("header_start_date");
 		if (startdate != null) 
 		{
 			Date sdate = null;
 			try {
 				sdate = new SimpleDateFormat("dd-MM-yyyy").parse(startdate);
 			} catch (ParseException e) {

 				e.printStackTrace();
 			}
 			menuGroupHeader.setHeader_start_date(sdate);
 		}
 		
 		String enddate = request.getParameter("header_end_date");
 		if (enddate != null) {
 			Date edate = null;
 			try {
 				edate = new SimpleDateFormat("dd-MM-yyyy").parse(startdate);
 			} catch (ParseException e) {

 				e.printStackTrace();
 			}
 			menuGroupHeader.setHeader_end_date(edate);
 		}
 		
 		group_header = rn_menu_register_service.addMenuGroupHeader1(menuGroupHeader);
 		//System.out.println(group_header);
 		
 		
 		
 		

 		String menu_group_line_id1[] = request.getParameterValues("menu_group_line_id");
 		String name1[] = request.getParameterValues("name");
 		String active1[] = request.getParameterValues("line_status");
 	
 		int rowcount = menu_group_line_id1.length;

 		int group_line = rn_menu_register_service.addMenuGroupLine1(rowcount,group_header,menu_group_line_id1,name1,active1);
 		
 		String check = request.getParameter("repgrpupdt");
 		map.addAttribute("check", check);
 		map.addAttribute("group_line", group_line);
 		
 		Rn_Menu_Group_Header grp_reg = new Rn_Menu_Group_Header();
 		map.addAttribute("grp_reg", grp_reg);
 		
 		
 		return new ModelAndView("RegisteredGroupReports");
 	}
	 
}
	

