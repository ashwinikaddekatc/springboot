"package com.realnet.MyProjectModule." + module_name + ".controller;" + "\r\n" + 
"import javax.servlet.http.HttpServletRequest;" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.stereotype.Controller;" + "\r\n" + 
"import org.springframework.validation.BindingResult;" + "\r\n" + 
"import org.springframework.web.bind.annotation.ModelAttribute;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMethod;" + "\r\n" + 
"import org.springframework.web.servlet.ModelAndView;" + "\r\n" + 
"import java.io.IOException;" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import org.springframework.orm.hibernate5.HibernateTemplate;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestParam;" + "\r\n" + 
"import javax.transaction.Transactional;" + "\r\n" + 
"import org.springframework.ui.ModelMap;" + "\r\n" + 
"import javax.servlet.http.HttpServletResponse;" + "\r\n" + 
"import java.util.ArrayList;" + "\r\n" + 
"import java.sql.CallableStatement;" + "\r\n" + 
"import java.sql.ResultSet;" + "\r\n" + 
"import java.sql.SQLException;" + "\r\n" + 
"import com.google.gson.Gson;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"import com.realnet.configuration.HibernateConfiguration;" + "\r\n" + 
"import java.text.SimpleDateFormat;" + "\r\n" + 
"import com.realnet.test_module1.dao.Myprojectui_dao;" + "\r\n" + 
"import com.realnet.test_module1.service.Myprojectui_service;" + "\r\n" + 
"import com.realnet.test_module1.model.Myprojectui_t;import java.text.ParseException;import javax.servlet.http.HttpSession;" + "\r\n" + 
"@Controller" + "\r\n" + 
"public class	Myprojectui_controller" + "\r\n" + 
"{" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private HibernateTemplate  hibernateTemplate;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private	Myprojectui_service	myprojectui_service;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"HibernateConfiguration hibernateConfiguration;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private	Myprojectui_dao	myprojectui_dao;" + "\r\n" + 
"" + "\r\n" + 
"//----------------------entry form sbmit------------------------------------------" + "\r\n" + 
"@Transactional" + "\r\n" + 
"@RequestMapping(value = \"/myprojectui_t_submit\", method = RequestMethod.POST)" + "\r\n" + 
"public ModelAndView saveServiceRequest(@ModelAttribute	Myprojectui_t myprojectui_t, BindingResult resultKoel_user ," + "\r\n" + 
"ModelMap map, HttpServletRequest request) {" + "\r\n" + 
"	int user_id=(Integer)request.getSession().getAttribute(\"userid\");" + "\r\n" + 
"myprojectui_t.setCreated_by(user_id);" + "\r\n" + 
"myprojectui_t.setLast_updated_by(user_id);" + "\r\n" + 
"hibernateTemplate.saveOrUpdate(myprojectui_t);" + "\r\n" + 
"return new ModelAndView(\"redirect:myprojectui_t_grid_view\");" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(\"/myprojectui_t_entryform\")" + "\r\n" + 
"public ModelAndView input_form3(HttpServletRequest request, ModelMap map) " + "\r\n" + 
"{" + "\r\n" + 
"return new ModelAndView(\"Myprojectui_t_view\");" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"//-----------------------------------for grid view only--------------------------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value=\"/myprojectui_t_grid_view\")" + "\r\n" + 
"public ModelAndView	myprojectui_tDetails(ModelAndView model) throws IOException" + "\r\n" + 
"{" + "\r\n" + 
"List<Myprojectui_t>	myprojectui_t=myprojectui_dao.userlist();" + "\r\n" + 
"model.addObject(\"myprojectui_t\", myprojectui_t);" + "\r\n" + 
"model.setViewName(\"Myprojectui_t_grid\");" + "\r\n" + 
"return model;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"//-----------------------for prefield part-----------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value = \"/myprojectui_t_update\", method = RequestMethod.GET)" + "\r\n" + 
"public ModelAndView loadReport1(@RequestParam(value = \"id\") String	id, ModelAndView modelview," + "\r\n" + 
"HttpServletRequest request, ModelMap map) throws IOException " + "\r\n" + 
"{" + "\r\n" + 
"int u_id = Integer.parseInt(id);" + "\r\n" + 
"Myprojectui_t	myprojectui_t = new	Myprojectui_t();" + "\r\n" + 
"map.addAttribute(\"myprojectui_t_update\",myprojectui_t);" + "\r\n" + 
"List<Myprojectui_t> report = myprojectui_service.prefield(u_id);" + "\r\n" + 
"map.addAttribute(\"myprojectui_t_update\", report);" + "\r\n" + 
"return new ModelAndView(\"Myprojectui_t_update\");" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"//--------------------for readonly------------------------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value = \"/myprojectui_t_readonly\", method = RequestMethod.GET)" + "\r\n" + 
"public ModelAndView loadReport2(@RequestParam(value = \"id\") String	id, ModelAndView modelview," + "\r\n" + 
"HttpServletRequest request, ModelMap map) throws IOException {" + "\r\n" + 
"int u_id = Integer.parseInt(id);" + "\r\n" + 
"Myprojectui_t	myprojectui_t = new	Myprojectui_t();" + "\r\n" + 
"map.addAttribute(\"myprojectui_t_update\",myprojectui_t);" + "\r\n" + 
"List<Myprojectui_t> report = myprojectui_service.prefield(u_id);" + "\r\n" + 
"map.addAttribute(\"myprojectui_t_update\", report);" + "\r\n" + 
"return new ModelAndView(\"Myprojectui_t_readonly\");" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"//--------------------------submit update part---------------------------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value = \"/myprojectui_t_update_submit\", method = RequestMethod.POST)" + "\r\n" + 
"public ModelAndView saveReportRegister(@ModelAttribute	Myprojectui_t	myprojectui_t," + "\r\n" + 
"BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {" + "\r\n" + 
"int report = 0;" + "\r\n" + 
"report = myprojectui_service.saveheader(myprojectui_t);" + "\r\n" + 
"String check = request.getParameter(\"repupdt\");" + "\r\n" + 
"map.addAttribute(\"check\", check);" + "\r\n" + 
"map.addAttribute(\"report\", report);" + "\r\n" + 
"Myprojectui_t rep_reg = new 	Myprojectui_t();" + "\r\n" + 
"map.addAttribute(\"rep_reg\", rep_reg);" + "\r\n" + 
"List<Myprojectui_t> report_list = myprojectui_service.userlist();" + "\r\n" + 
"map.addAttribute(\"report_list\", report_list);" + "\r\n" + 
"return new ModelAndView(\"redirect:myprojectui_t_grid_view\");" + "\r\n" + 
"}" + "\r\n" + 
"}" 
