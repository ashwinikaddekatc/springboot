package com.realnet.test_module1.controller;
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
import com.realnet.test_module1.dao.Nil_final_dao;
import com.realnet.test_module1.service.Nil_final_service;
import com.realnet.test_module1.model.Rn_nil_final;import java.text.ParseException;import javax.servlet.http.HttpSession;
@Controller
public class	Nil_final_controller
{
@Autowired
private HibernateTemplate  hibernateTemplate;
@Autowired
private	Nil_final_service	nil_final_service;
@Autowired
HibernateConfiguration hibernateConfiguration;
@Autowired
private	Nil_final_dao	nil_final_dao;

//----------------------entry form sbmit------------------------------------------
@Transactional
@RequestMapping(value = "/rn_nil_final_submit", method = RequestMethod.POST)
public ModelAndView saveServiceRequest(@ModelAttribute	Rn_nil_final rn_nil_final, BindingResult resultKoel_user ,
ModelMap map, HttpServletRequest request) {
	int user_id=(Integer)request.getSession().getAttribute("userid");
rn_nil_final.setCreated_by(user_id);
rn_nil_final.setLast_updated_by(user_id);
hibernateTemplate.saveOrUpdate(rn_nil_final);
return new ModelAndView("redirect:rn_nil_final_grid_view");


}


@RequestMapping("/rn_nil_final_entryform")
public ModelAndView input_form3(HttpServletRequest request, ModelMap map) 
{
return new ModelAndView("Rn_nil_final_view");
}

//-----------------------------------for grid view only--------------------------------------------------

@RequestMapping(value="/rn_nil_final_grid_view")
public ModelAndView	rn_nil_finalDetails(ModelAndView model) throws IOException
{
List<Rn_nil_final>	rn_nil_final=nil_final_dao.userlist();
model.addObject("rn_nil_final", rn_nil_final);
model.setViewName("Rn_nil_final_grid");
return model;
}

//-----------------------for prefield part-----------------------------------

@RequestMapping(value = "/rn_nil_final_update", method = RequestMethod.GET)
public ModelAndView loadReport1(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException 
{
int u_id = Integer.parseInt(id);
Rn_nil_final	rn_nil_final = new	Rn_nil_final();
map.addAttribute("rn_nil_final_updt",rn_nil_final);
List<Rn_nil_final> report =nil_final_service.prefield(u_id);
int updt_id = report.get(0).getId();
map.addAttribute("rn_nil_final_updt_id", updt_id);
map.addAttribute("rn_nil_final_update", report);
return new ModelAndView("Rn_nil_final_update");
}

//--------------------for readonly------------------------------------------------

@RequestMapping(value = "/rn_nil_final_readonly", method = RequestMethod.GET)
public ModelAndView loadReport2(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException {
int u_id = Integer.parseInt(id);
Rn_nil_final	rn_nil_final = new	Rn_nil_final();
map.addAttribute("rn_nil_final_updt",rn_nil_final);
List<Rn_nil_final> report = nil_final_service.prefield(u_id);
map.addAttribute("rn_nil_final_update", report);
return new ModelAndView("Rn_nil_final_readonly");
}



//--------------------------submit update part---------------------------------------------------

@RequestMapping(value = "/rn_nil_final_update_submit", method = RequestMethod.POST)
public ModelAndView saveReportRegister(@ModelAttribute	Rn_nil_final	rn_nil_final,
BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
int report = 0;
report = nil_final_service.saveheader(rn_nil_final);
String check = request.getParameter("repupdt");
map.addAttribute("check", check);
map.addAttribute("report", report);
Rn_nil_final rep_reg = new 	Rn_nil_final();
map.addAttribute("rep_reg", rep_reg);
List<Rn_nil_final> report_list = nil_final_service.userlist();
map.addAttribute("report_list", report_list);
return new ModelAndView("redirect:rn_nil_final_grid_view");
}
}