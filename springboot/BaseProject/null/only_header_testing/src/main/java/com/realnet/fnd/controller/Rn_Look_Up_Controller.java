package com.realnet.fnd.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.fnd.model.Rn_Look_Up;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_Lookup_Values;
import com.realnet.fnd.service.Rn_Look_Up_Service;
@Controller
public class Rn_Look_Up_Controller {

	@Autowired
	Rn_Look_Up_Service rn_look_up_service;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	

	// -----------------------------------for grid view only--------------------------------------------------

	@RequestMapping(value="/rn_look_up_grid")
	public ModelAndView rnLookUpGrid(ModelAndView model,ModelMap map) throws IOException{
		System.out.println("in controller");
		
		Rn_Look_Up rn_look_up=new Rn_Look_Up();
		List<Rn_Lookup_Autofill> rn_lookup_autofill = rn_look_up_service.lookuplist1();
		
		Rn_Lookup_Autofill rn_lookup_autofill1 = new Rn_Lookup_Autofill();
		
		model.addObject("rn_lookup_autofill1", rn_lookup_autofill1);
		model.addObject("rn_lookup_autofill", rn_lookup_autofill);
		
		map.addAttribute("rn_look_up",rn_look_up);		
		map.addAttribute("rn_lookup_autofill",rn_lookup_autofill);
		
		model.setViewName("Rn_Look_Up_Grid");
		
		return model;
	}
	
	// ----------------------entry form ------------------------------------------

	@RequestMapping(value="/rn_look_up_entry")
	public ModelAndView rnLookUp(ModelAndView model,ModelMap map) throws IOException
	{
		Rn_Look_Up rn_look_up=new Rn_Look_Up();
		Rn_Lookup_Values rn_lookup_values=new Rn_Lookup_Values();
		
		map.addAttribute("rn_look_up",rn_look_up);		
		map.addAttribute("rn_lookup_values",rn_lookup_values);
	
		return new ModelAndView("Rn_Look_Up_Entry");
		
	}
	

	// ----------------------entry form sbmit------------------------------------------

	
	@RequestMapping(value = "/rn_look_up_entry_submit", method = RequestMethod.POST)
	public ModelAndView saveLookup1(@ModelAttribute Rn_Look_Up rn_look_up, BindingResult resultlookup,@ModelAttribute Rn_Lookup_Values lookupvalues, 
			BindingResult resultServiceRequestDetails, ModelMap map, HttpServletRequest request) throws ParseException
	{
		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {	
					
		int user_id = (Integer) request.getSession().getAttribute("userid");		
		System.out.println("User id"+user_id);
					
		   Date start_date = null;
           try {
        	   System.out.println("start date"+request.getParameter("active_start_date"));
               start_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("active_start_date"));
           } catch (ParseException e) {

               e.printStackTrace();
           }

           Date end_date = null;
           try {
        	  System.out.println("start date"+request.getParameter("active_end_date"));
               end_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("active_end_date"));
           } catch (ParseException e) {

               e.printStackTrace();
           } 

           rn_look_up.setActive_start_date(start_date);
           rn_look_up.setActive_end_date(end_date);         
           rn_look_up.setCreated_by(user_id);
           rn_look_up.setLast_updated_by(user_id);
           rn_look_up.setEnabled_flag("Y");		
			
			String type = rn_look_up_service.save_lookup(rn_look_up);
			
//save line
			String lookup_code[] = request.getParameterValues("lookup_code");
			String meaning[] = request.getParameterValues("meaning");
			String description[] = request.getParameterValues("description");
			String active_start_date[] = request.getParameterValues("l_active_start_date");
			String active_end_date[] = request.getParameterValues("l_active_end_date");
				 
			int count=lookup_code.length;
			
			int result = rn_look_up_service.save_lookupvalues(user_id,count,type,lookup_code,meaning,description,active_start_date,active_end_date);
 
			Rn_Look_Up rn_look_up1=new Rn_Look_Up();
			Rn_Lookup_Values rn_lookup_values=new Rn_Lookup_Values();
			
			map.addAttribute("lookup",rn_look_up1);		
			map.addAttribute("lookupvalues",rn_lookup_values);
		}
		
		return new ModelAndView("Rn_Look_Up_Grid");
		
	
	}

	// -----------------------for prefield part-----------------------------------

	
	@RequestMapping(value="/rn_look_up_update",method = RequestMethod.GET)
	public ModelAndView lookupfunction(HttpServletRequest request,ModelAndView model1,ModelMap map,Model model,@ModelAttribute Rn_Look_Up lookup, BindingResult resultlookup,@ModelAttribute Rn_Lookup_Values lookupvalues,BindingResult resultlookup1) throws IOException
	{
		System.out.println("in controller 1");	
		 Rn_Lookup_Autofill rn_lookup_autofill_1=new Rn_Lookup_Autofill();
		
	 String s1 = (request.getParameter("lookup_type"));
	 System.out.println("lookup type "+s1);
	 
	 List<Rn_Lookup_Autofill> rn_lookup_autofill_2 = rn_look_up_service.lookup1(s1);
	 List<Rn_Lookup_Autofill> rn_lookup_autofill_3 = rn_look_up_service.lookup2(s1);

	 	model.addAttribute("rn_lookup_autofill_1", rn_lookup_autofill_1);
	 	model.addAttribute("rn_lookup_autofill_2", rn_lookup_autofill_2);
	 	model.addAttribute("rn_lookup_autofill_3", rn_lookup_autofill_3);
	 	map.addAttribute("lookup",lookup);		
		map.addAttribute("lookupvalues",lookupvalues);

		System.out.println("end controller 1");

		return new ModelAndView("Rn_Look_Up_Update");
	}

	// --------------------------sbmit update part---------------------------------------------------

	@RequestMapping(value = "/rn_look_up_update_submit", method = RequestMethod.POST)
	public ModelAndView saveLookup(@ModelAttribute Rn_Look_Up rn_look_up, BindingResult resultlookup,@ModelAttribute Rn_Lookup_Values lookupvalues, 
			BindingResult resultServiceRequestDetails, ModelMap map, HttpServletRequest request) throws ParseException
	{
		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {	
		
		int user_id = (Integer) request.getSession().getAttribute("userid");
		System.out.println("User id"+user_id);
		  
		String startdate = request.getParameter("active_start_date");
		if (startdate != null) {
			Date sdate = null;
			try {
				sdate = new SimpleDateFormat("dd-MM-yyyy").parse(startdate);
			} catch (ParseException e) {

				e.printStackTrace();
			}
			rn_look_up.setActive_start_date(sdate);
		}
		
		String enddate = request.getParameter("active_end_date");
		if (enddate != null) {
			Date edate = null;
			try {
				edate = new SimpleDateFormat("dd-MM-yyyy").parse(enddate);
			} catch (ParseException e) {

				e.printStackTrace();
			}
			rn_look_up.setActive_end_date(edate);
		}
		
		rn_look_up.setCreated_by(user_id);
		rn_look_up.setLast_updated_by(user_id);
		rn_look_up.setEnabled_flag("Y");
		rn_look_up.getActive_start_date();
		rn_look_up.getActive_end_date();
			
			String type = rn_look_up_service.save_lookup(rn_look_up);
		

			String lookup_code[] = request.getParameterValues("lookup_code");
			String meaning[] = request.getParameterValues("meaning");
			String active_start_date[] = request.getParameterValues("l_active_start_date");
			String active_end_date[] = request.getParameterValues("l_active_end_date");
			String description[] = request.getParameterValues("description");
	 
			int count=lookup_code.length;
			int aa =rn_look_up_service.save_lookupvalues(user_id,count,type,lookup_code,meaning,description,active_start_date,active_end_date);

			Rn_Look_Up lookup2=new Rn_Look_Up();
			Rn_Lookup_Values lookupvalues2=new Rn_Lookup_Values();
			
			map.addAttribute("lookup",lookup2);		
			map.addAttribute("lookupvalues",lookupvalues2);
		}	
		return new ModelAndView("redirect:/rn_look_up_grid");		
	
	}
	
	@RequestMapping(value = "/getTagsLookUp", method = RequestMethod.GET)
	public @ResponseBody List<Rn_Look_Up> getTags(@RequestParam String tagName,HttpServletRequest request) 
	{

		int user_id = (Integer) request.getSession().getAttribute("userid");

		if(user_id != 0)
		{
		if(tagName.equals(null))
				{	
				 List<Rn_Look_Up> inst_returnlist = new  ArrayList<Rn_Look_Up> ();
				 Rn_Look_Up prfobj = new Rn_Look_Up();
				 return inst_returnlist;
				}
			else
				{
				return simulateSearchResult(tagName);
				}
				}
		return null;
	}

	private List<Rn_Look_Up> simulateSearchResult(String tagName)
	{
		System.out.println("start of controller");

		List<Rn_Look_Up>lookup_result = new ArrayList<Rn_Look_Up>();
				if (tagName.equals(""))
				{
					lookup_result = null;
			
				} 
				else {
				List<Rn_Look_Up> lookuplist = rn_look_up_service.listLookUp(tagName);
				for (Rn_Look_Up lookuptag : lookuplist)
				{	
								
					lookup_result.add(lookuptag);											
										
				}
			}
			
			if (lookup_result.isEmpty())
			{

			}
			System.out.println("end of controller");
			return lookup_result;
		}
		
	

	
	@RequestMapping(value="/showlookup")
	public ModelAndView show_lookup(ModelAndView model,ModelMap map) throws IOException
	{
		List<Rn_Lookup_Autofill> lookuplist = rn_look_up_service.lookuplist();
		Rn_Lookup_Autofill lookup=new Rn_Lookup_Autofill();
		map.addAttribute("lookup",lookup);
		map.addAttribute("lookuplist",lookuplist);		
		
		//LookupValues lookupvalues=new LookupValues();
		//map.addAttribute("lookupvalues",lookupvalues);
		//model.setViewName("LookUpType");

		return new ModelAndView("LookUpType");
		
	}

	
	
}
	

	

