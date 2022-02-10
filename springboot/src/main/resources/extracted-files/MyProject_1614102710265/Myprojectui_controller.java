package com.realnet.MyProjectModule.controller;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.List;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.web.bind.annotation.RequestParam;
import javax.transaction.Transactional;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.google.gson.Gson;
import java.util.Date;
import com.realnet.configuration.HibernateConfiguration;
import java.text.SimpleDateFormat;
import com.realnet.test_module1.dao.Myprojectui_dao;
import com.realnet.test_module1.service.Myprojectui_service;
import com.realnet.test_module1.model.Myprojectui_t;import java.text.ParseException;import javax.servlet.http.HttpSession;
@Controller
public class	Myprojectui_controller
{
@Autowired
private HibernateTemplate  hibernateTemplate;
@Autowired
private	Myprojectui_service	myprojectui_service;
@Autowired
HibernateConfiguration hibernateConfiguration;
@Autowired
private	Myprojectui_dao	myprojectui_dao;

//----------------------entry form sbmit------------------------------------------
@Transactional
@RequestMapping(value = "/myprojectui_t_submit", method = RequestMethod.POST)
public ModelAndView saveServiceRequest(@ModelAttribute	Myprojectui_t myprojectui_t, BindingResult resultKoel_user ,
ModelMap map, HttpServletRequest request) {
	int user_id=(Integer)request.getSession().getAttribute("userid");
myprojectui_t.setCreated_by(user_id);
myprojectui_t.setLast_updated_by(user_id);
hibernateTemplate.saveOrUpdate(myprojectui_t);
return new ModelAndView("redirect:myprojectui_t_grid_view");


}


@RequestMapping("/myprojectui_t_entryform")
public ModelAndView input_form3(HttpServletRequest request, ModelMap map) 
{
return new ModelAndView("Myprojectui_t_view");
}

//-----------------------------------for grid view only--------------------------------------------------

@RequestMapping(value="/myprojectui_t_grid_view")
public ModelAndView	myprojectui_tDetails(ModelAndView model) throws IOException
{
List<Myprojectui_t>	myprojectui_t=myprojectui_dao.userlist();
model.addObject("myprojectui_t", myprojectui_t);
model.setViewName("Myprojectui_t_grid");
return model;
}

//-----------------------for prefield part-----------------------------------

@RequestMapping(value = "/myprojectui_t_update", method = RequestMethod.GET)
public ModelAndView loadReport1(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException 
{
int u_id = Integer.parseInt(id);
Myprojectui_t	myprojectui_t = new	Myprojectui_t();
map.addAttribute("myprojectui_t_update",myprojectui_t);
List<Myprojectui_t> report = myprojectui_service.prefield(u_id);
map.addAttribute("myprojectui_t_update", report);
return new ModelAndView("Myprojectui_t_update");
}

//--------------------for readonly------------------------------------------------

@RequestMapping(value = "/myprojectui_t_readonly", method = RequestMethod.GET)
public ModelAndView loadReport2(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException {
int u_id = Integer.parseInt(id);
Myprojectui_t	myprojectui_t = new	Myprojectui_t();
map.addAttribute("myprojectui_t_update",myprojectui_t);
List<Myprojectui_t> report = myprojectui_service.prefield(u_id);
map.addAttribute("myprojectui_t_update", report);
return new ModelAndView("Myprojectui_t_readonly");
}



//--------------------------submit update part---------------------------------------------------

@RequestMapping(value = "/myprojectui_t_update_submit", method = RequestMethod.POST)
public ModelAndView saveReportRegister(@ModelAttribute	Myprojectui_t	myprojectui_t,
BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
int report = 0;
report = myprojectui_service.saveheader(myprojectui_t);
String check = request.getParameter("repupdt");
map.addAttribute("check", check);
map.addAttribute("report", report);
Myprojectui_t rep_reg = new 	Myprojectui_t();
map.addAttribute("rep_reg", rep_reg);
List<Myprojectui_t> report_list = myprojectui_service.userlist();
map.addAttribute("report_list", report_list);
return new ModelAndView("redirect:myprojectui_t_grid_view");
}
}