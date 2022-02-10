
package com.realnet.fnd.controller;
 import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.dao.Rn_Instance_Type_Dao;
import com.realnet.fnd.model.Rn_Instance_Type;
import com.realnet.fnd.service.Rn_Instance_Type_Service;
@Controller
public class	Rn_Instance_Type_Controller
{
@Autowired
private HibernateConfiguration hibernateConfiguration;
@Autowired
private HibernateTemplate  hibernateTemplate;
@Autowired
private	Rn_Instance_Type_Service	rn_instance_type_service;
@Autowired
private	Rn_Instance_Type_Dao	rn_instance_type_dao;

//----------------------entry form sbmit------------------------------------------
@Transactional
@RequestMapping(value = "/rn_instance_type_t_submit", method = RequestMethod.POST)
public ModelAndView saveServiceRequest(@ModelAttribute	Rn_Instance_Type	rn_instance_type,BindingResult resultKoel_user ,
ModelMap map, HttpServletRequest request)  
{
	 String instance_type=request.getParameter("instance_type");
String 	attribute1=request.getParameter("attribute1");
String 	attribute2=request.getParameter("attribute2");
String 	attribute3=request.getParameter("attribute3");
String 	attribute4=request.getParameter("attribute4");
String 	attribute5=request.getParameter("attribute5");
String 	attribute6=request.getParameter("attribute6");
String 	attribute7=request.getParameter("attribute7");
String 	attribute8=request.getParameter("attribute8");
String 	attribute9=request.getParameter("attribute9");
String 	attribute10=request.getParameter("attribute10");
String 	attribute11=request.getParameter("attribute11");
String 	attribute12=request.getParameter("attribute12");
String 	attribute13=request.getParameter("attribute13");
String 	attribute14=request.getParameter("attribute14");
String 	attribute15=request.getParameter("attribute15");
String 	flex1=request.getParameter("flex1");
String 	flex2=request.getParameter("flex2");
String 	flex3=request.getParameter("flex3");
String 	flex4=request.getParameter("flex4");
String 	flex5=request.getParameter("flex5");

rn_instance_type.setInstance_type(instance_type);
rn_instance_type.setAttribute1(attribute1);
rn_instance_type.setAttribute2(attribute2);
rn_instance_type.setAttribute3(attribute3);
rn_instance_type.setAttribute4(attribute4);
rn_instance_type.setAttribute5(attribute5);
rn_instance_type.setAttribute6(attribute6);
rn_instance_type.setAttribute7(attribute7);
rn_instance_type.setAttribute8(attribute8);
rn_instance_type.setAttribute9(attribute9);
rn_instance_type.setAttribute10(attribute10);
rn_instance_type.setAttribute11(attribute11);
rn_instance_type.setAttribute12(attribute12);
rn_instance_type.setAttribute13(attribute13);
rn_instance_type.setAttribute14(attribute14);
rn_instance_type.setAttribute15(attribute15);
rn_instance_type.setFlex1(flex1);
rn_instance_type.setFlex2(flex2);
rn_instance_type.setFlex3(flex3);
rn_instance_type.setFlex4(flex4);
rn_instance_type.setFlex5(flex5);
hibernateTemplate.saveOrUpdate(rn_instance_type);
return new ModelAndView("redirect:rn_instance_type_t_grid_view");


}

//----------------------entry form ------------------------------------------


@RequestMapping("/rn_instance_type_t_entryform")
public ModelAndView input_form3(HttpServletRequest request, ModelMap map) 
{
return new ModelAndView("rn_instance_type_view");
}

//-----------------------------------for grid view only--------------------------------------------------

@RequestMapping(value="/rn_instance_type_t_grid_view")
public ModelAndView	rn_instance_type_tDetails(ModelAndView model) throws IOException
{
List<Rn_Instance_Type>	rn_instance_type_t=rn_instance_type_dao.userlist();
model.addObject("rn_instance_type_t", rn_instance_type_t);
System.out.println("sujit");
model.setViewName("Rn_instance_type_t_grid");
return model;
}

//-----------------------for prefield part-----------------------------------

@RequestMapping(value = "/rn_instance_type_t_update", method = RequestMethod.GET)
public ModelAndView loadReport1(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException 
{
int u_id = Integer.parseInt(id);
Rn_Instance_Type	rn_instance_type_t = new	Rn_Instance_Type();
map.addAttribute("rn_instance_type_t_updt",rn_instance_type_t);
List<Rn_Instance_Type> report =rn_instance_type_service.prefield(u_id);
map.addAttribute("rn_instance_type_t_update", report);
return new ModelAndView("Rn_instance_type_t_update");
}



//--------------------for readonly------------------------------------------------

@RequestMapping(value = "/rn_instance_type_t_readonly", method = RequestMethod.GET)
public ModelAndView loadReport2(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException 
{
int u_id = Integer.parseInt(id);
Rn_Instance_Type	rn_instance_type_t = new	Rn_Instance_Type();
map.addAttribute("rn_instance_type_t_updt",rn_instance_type_t);
List<Rn_Instance_Type> report =rn_instance_type_service.prefield(u_id);
map.addAttribute("rn_instance_type_t_update", report);
return new ModelAndView("Rn_instance_type_t_readonly");
}



//--------------------------sbmit update part---------------------------------------------------

@RequestMapping(value = "/rn_instance_type_t_update_submit", method = RequestMethod.POST)
public ModelAndView saveReportRegister(@ModelAttribute	Rn_Instance_Type	rn_instance_type_t,
BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
int report = 0;
String[]	id=request.getParameterValues("id");
String[]	instance_type=request.getParameterValues("instance_type");
int rowcount=id.length;
report = rn_instance_type_service.save(rowcount,id,instance_type	);
String check = request.getParameter("repupdt");
map.addAttribute("check", check);
map.addAttribute("report", report);
	Rn_Instance_Type	 rep_reg = new 	Rn_Instance_Type();
map.addAttribute("rep_reg", rep_reg);
List<Rn_Instance_Type> report_list = rn_instance_type_service.userlist();
map.addAttribute("report_list", report_list);
return new ModelAndView("redirect:rn_instance_type_t_grid_view");
}
}