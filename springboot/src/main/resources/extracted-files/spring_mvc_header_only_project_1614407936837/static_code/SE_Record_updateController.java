"package com.realnet.test_module1." + module_name + "." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.io.IOException;" + "\r\n" + 
"import java.text.ParseException;" + "\r\n" + 
"import java.time.LocalDateTime;" + "\r\n" + 
"import java.time.format.DateTimeFormatter;" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import javax.servlet.http.HttpServletRequest;" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.stereotype.Controller;" + "\r\n" + 
"import org.springframework.ui.ModelMap;" + "\r\n" + 
"import org.springframework.validation.BindingResult;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.ModelAttribute;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMethod;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestParam;" + "\r\n" + 
"import org.springframework.web.servlet.ModelAndView;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.test_module1.model.Rn_nil_final;" + "\r\n" + 
"import com.realnet.test_module1.dao." + dao_name_first_upper + ";" + "\r\n" + 
"import com.realnet.test_module1.service.Nil_final_service;" + "\r\n" + 
"@Controller" + "\r\n" + 
"public class Record_updateController {" + "\r\n" + 
"	@Autowired	private " + dao_name_first_upper + "	" + dao_name_lower + ";" + "\r\n" + 
"	// INSERT FIELDS USING ACTION BUILDER" + "\r\n" + 
"@GetMapping(value = \"/record_update\")" + "\r\n" + 
"	public ModelAndView record_update(@RequestParam(value = \"id\") String h_id) throws IOException {" + "\r\n" + 
"		int hId = Integer.parseInt(h_id);" + "\r\n" + 
"		//System.out.println(\"JSP ID = \" + hId);" + "\r\n" + 
"	// CFF_LAYOUT_CONTROLLER_START" + "\r\n" + 
"		System.out.println(\"PLEASE INSERT CODE... GO TO ACTION BUILDER... \");" + "\r\n" + 
"	// CFF_LAYOUT_CONTROLLER_END" + "\r\n" + 
"		return new ModelAndView(\"redirect:rn_nil_final_update?id=\" + hId);" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
