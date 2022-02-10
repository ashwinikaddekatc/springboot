
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
import com.realnet.test_module1.dao.Nil_test2_dao;
import com.realnet.test_module1.service.Nil_test2_service;
import com.realnet.test_module1.model.Rn_nil_test2;
import java.text.ParseException;
import javax.servlet.http.HttpSession;

@Controller
public class Nil_test2_controller {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private Nil_test2_service nil_test2_service;
	@Autowired
	HibernateConfiguration hibernateConfiguration;
	@Autowired
	private Nil_test2_dao nil_test2_dao;

//----------------------entry form sbmit------------------------------------------
	@Transactional
	@RequestMapping(value = "/rn_nil_test2_submit", method = RequestMethod.POST)
	public ModelAndView saveServiceRequest(@ModelAttribute Rn_nil_test2 rn_nil_test2, BindingResult resultKoel_user,
			ModelMap map, HttpServletRequest request) {
		int user_id = (Integer) request.getSession().getAttribute("userid");
		String textfield1 = request.getParameter("textfield1");
		String textfield2 = request.getParameter("textfield2");
		String textfield3 = request.getParameter("textfield3");
		String textfield4 = request.getParameter("textfield4");
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

		rn_nil_test2.setTextfield1(textfield1);
		rn_nil_test2.setTextfield2(textfield2);
		rn_nil_test2.setTextfield3(textfield3);
		rn_nil_test2.setTextfield4(textfield4);
		rn_nil_test2.setAttribute1(attribute1);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute2(attribute2);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute3(attribute3);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute4(attribute4);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute5(attribute5);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute6(attribute6);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute7(attribute7);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute8(attribute8);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute9(attribute9);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute10(attribute10);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute11(attribute11);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute12(attribute12);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute13(attribute13);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute14(attribute14);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setAttribute15(attribute15);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setFlex1(flex1);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setFlex2(flex2);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setFlex3(flex3);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setFlex4(flex4);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		rn_nil_test2.setFlex5(flex5);
		rn_nil_test2.setCreated_by(user_id);
		rn_nil_test2.setLast_updated_by(user_id);
		hibernateTemplate.saveOrUpdate(rn_nil_test2);
		return new ModelAndView("redirect:rn_nil_test2_grid_view");

	}

	@RequestMapping("/rn_nil_test2_entryform")
	public ModelAndView input_form3(HttpServletRequest request, ModelMap map) {
		return new ModelAndView("Rn_nil_test2_view");
	}

//-----------------------------------for grid view only--------------------------------------------------

	@RequestMapping(value = "/rn_nil_test2_grid_view")
	public ModelAndView rn_nil_test2Details(ModelAndView model) throws IOException {
		List<Rn_nil_test2> rn_nil_test2 = nil_test2_dao.userlist();
		model.addObject("rn_nil_test2", rn_nil_test2);
		System.out.println("sujit");
		model.setViewName("Rn_nil_test2_grid");
		return model;
	}

//-----------------------for prefield part-----------------------------------

	@RequestMapping(value = "/rn_nil_test2_update", method = RequestMethod.GET)
	public ModelAndView loadReport1(@RequestParam(value = "id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);
		Rn_nil_test2 rn_nil_test2 = new Rn_nil_test2();
		map.addAttribute("rn_nil_test2_updt", rn_nil_test2);
		List<Rn_nil_test2> report = nil_test2_service.prefield(u_id);
		int updt_id = report.get(0).getId();
		map.addAttribute("rn_nil_test2_updt_id", updt_id);

		map.addAttribute("rn_nil_test2_update", report);
		return new ModelAndView("Rn_nil_test2_update");
	}

//--------------------for readonly------------------------------------------------

	@RequestMapping(value = "/rn_nil_test2_readonly", method = RequestMethod.GET)
	public ModelAndView loadReport2(@RequestParam(value = "id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);
		Rn_nil_test2 rn_nil_test2 = new Rn_nil_test2();
		map.addAttribute("rn_nil_test2_updt", rn_nil_test2);
		List<Rn_nil_test2> report = nil_test2_service.prefield(u_id);
		map.addAttribute("rn_nil_test2_update", report);
		return new ModelAndView("Rn_nil_test2_readonly");
	}

//--------------------------submit update part---------------------------------------------------

	@RequestMapping(value = "/rn_nil_test2_update_submit", method = RequestMethod.POST)
	public ModelAndView saveReportRegister(@ModelAttribute Rn_nil_test2 rn_nil_test2,
			BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
		int report = 0;
		String id = request.getParameter("id");
		String textfield1 = request.getParameter("textfield1");
		String textfield2 = request.getParameter("textfield2");
		String textfield3 = request.getParameter("textfield3");
		String textfield4 = request.getParameter("textfield4");
		int rowcount = id.length();
		report = nil_test2_service.save(rowcount, id, textfield1, textfield2, textfield3, textfield4);
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("report", report);
		Rn_nil_test2 rep_reg = new Rn_nil_test2();
		map.addAttribute("rep_reg", rep_reg);
		List<Rn_nil_test2> report_list = nil_test2_service.userlist();
		map.addAttribute("report_list", report_list);
		return new ModelAndView("redirect:rn_nil_test2_grid_view");
	}
}