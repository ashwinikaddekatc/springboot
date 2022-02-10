"package com.realnet.test_module1." + module_name + "." + module_name + ".controller;" + "\r\n" + 
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
"import com.realnet.test_module1.dao." + dao_name_first_upper + ";" + "\r\n" + 
"import com.realnet.test_module1.service.Nil_final_service;" + "\r\n" + 
"import com.realnet.test_module1.model.Rn_nil_final;import java.text.ParseException;import javax.servlet.http.HttpSession;" + "\r\n" + 
"@Controller" + "\r\n" + 
"public class	Nil_final_controller" + "\r\n" + 
"{" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private HibernateTemplate  hibernateTemplate;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private	Nil_final_service	nil_final_service;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"HibernateConfiguration hibernateConfiguration;" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private	" + dao_name_first_upper + "	" + dao_name_lower + ";" + "\r\n" + 
"" + "\r\n" + 
"//----------------------entry form sbmit------------------------------------------" + "\r\n" + 
"@Transactional" + "\r\n" + 
"@RequestMapping(value = \"/rn_nil_final_submit\", method = RequestMethod.POST)" + "\r\n" + 
"public ModelAndView saveServiceRequest(@ModelAttribute	Rn_nil_final rn_nil_final, BindingResult resultKoel_user ," + "\r\n" + 
"ModelMap map, HttpServletRequest request) {" + "\r\n" + 
"	int user_id=(Integer)request.getSession().getAttribute(\"userid\");" + "\r\n" + 
"rn_nil_final.setCreated_by(user_id);" + "\r\n" + 
"rn_nil_final.setLast_updated_by(user_id);" + "\r\n" + 
"hibernateTemplate.saveOrUpdate(rn_nil_final);" + "\r\n" + 
"return new ModelAndView(\"redirect:rn_nil_final_grid_view\");" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(\"/rn_nil_final_entryform\")" + "\r\n" + 
"public ModelAndView input_form3(HttpServletRequest request, ModelMap map) " + "\r\n" + 
"{" + "\r\n" + 
"return new ModelAndView(\"Rn_nil_final_view\");" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"//-----------------------------------for grid view only--------------------------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value=\"/rn_nil_final_grid_view\")" + "\r\n" + 
"public ModelAndView	rn_nil_finalDetails(ModelAndView model) throws IOException" + "\r\n" + 
"{" + "\r\n" + 
"List<Rn_nil_final>	rn_nil_final=" + dao_name_lower + ".userlist();" + "\r\n" + 
"model.addObject(\"rn_nil_final\", rn_nil_final);" + "\r\n" + 
"model.setViewName(\"Rn_nil_final_grid\");" + "\r\n" + 
"return model;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"//-----------------------for prefield part-----------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value = \"/rn_nil_final_update\", method = RequestMethod.GET)" + "\r\n" + 
"public ModelAndView loadReport1(@RequestParam(value = \"id\") String	id, ModelAndView modelview," + "\r\n" + 
"HttpServletRequest request, ModelMap map) throws IOException " + "\r\n" + 
"{" + "\r\n" + 
"int u_id = Integer.parseInt(id);" + "\r\n" + 
"Rn_nil_final	rn_nil_final = new	Rn_nil_final();" + "\r\n" + 
"map.addAttribute(\"rn_nil_final_updt\",rn_nil_final);" + "\r\n" + 
"List<Rn_nil_final> report =nil_final_service.prefield(u_id);" + "\r\n" + 
"int updt_id = report.get(0).getId();" + "\r\n" + 
"map.addAttribute(\"rn_nil_final_updt_id\", updt_id);" + "\r\n" + 
"map.addAttribute(\"rn_nil_final_update\", report);" + "\r\n" + 
"return new ModelAndView(\"Rn_nil_final_update\");" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"//--------------------for readonly------------------------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value = \"/rn_nil_final_readonly\", method = RequestMethod.GET)" + "\r\n" + 
"public ModelAndView loadReport2(@RequestParam(value = \"id\") String	id, ModelAndView modelview," + "\r\n" + 
"HttpServletRequest request, ModelMap map) throws IOException {" + "\r\n" + 
"int u_id = Integer.parseInt(id);" + "\r\n" + 
"Rn_nil_final	rn_nil_final = new	Rn_nil_final();" + "\r\n" + 
"map.addAttribute(\"rn_nil_final_updt\",rn_nil_final);" + "\r\n" + 
"List<Rn_nil_final> report = nil_final_service.prefield(u_id);" + "\r\n" + 
"map.addAttribute(\"rn_nil_final_update\", report);" + "\r\n" + 
"return new ModelAndView(\"Rn_nil_final_readonly\");" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"//--------------------------submit update part---------------------------------------------------" + "\r\n" + 
"" + "\r\n" + 
"@RequestMapping(value = \"/rn_nil_final_update_submit\", method = RequestMethod.POST)" + "\r\n" + 
"public ModelAndView saveReportRegister(@ModelAttribute	Rn_nil_final	rn_nil_final," + "\r\n" + 
"BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {" + "\r\n" + 
"int report = 0;" + "\r\n" + 
"report = nil_final_service.saveheader(rn_nil_final);" + "\r\n" + 
"String check = request.getParameter(\"repupdt\");" + "\r\n" + 
"map.addAttribute(\"check\", check);" + "\r\n" + 
"map.addAttribute(\"report\", report);" + "\r\n" + 
"Rn_nil_final rep_reg = new 	Rn_nil_final();" + "\r\n" + 
"map.addAttribute(\"rep_reg\", rep_reg);" + "\r\n" + 
"List<Rn_nil_final> report_list = nil_final_service.userlist();" + "\r\n" + 
"map.addAttribute(\"report_list\", report_list);" + "\r\n" + 
"return new ModelAndView(\"redirect:rn_nil_final_grid_view\");" + "\r\n" + 
"}" + "\r\n" + 
"}" 
