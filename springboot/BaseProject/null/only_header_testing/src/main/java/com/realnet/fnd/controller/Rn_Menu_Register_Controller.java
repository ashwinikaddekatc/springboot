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
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

import com.google.gson.Gson;
import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Menu_Group_Line;
import com.realnet.fnd.model.Rn_Menu_Register;
import com.realnet.fnd.model.Rn_Two_Jsp;
import com.realnet.fnd.service.Rn_Look_Up_Service;
import com.realnet.fnd.service.Rn_Menu_Register_Service;

import oracle.jdbc.OracleTypes;

@Controller
public class Rn_Menu_Register_Controller {

	@Autowired
	Rn_Look_Up_Service rn_look_up_service;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HibernateConfiguration hibernateConfiguration;

	@Autowired
	private Rn_Menu_Register_Service rn_menu_register_service;

	@Autowired
	private HibernateTemplate hibernateTemplate;

// ----------------------entry form ------------------------------------------

	@RequestMapping(value = "/rn_menu_register_view")
	public ModelAndView menuregister(ModelAndView model, ModelMap map) throws IOException {
		Rn_Menu_Register rn_menu_register = new Rn_Menu_Register();
		map.addAttribute("rn_menu_register", rn_menu_register);
		List<Rn_Lookup_Autofill> rn_lookup_autofill = rn_menu_register_service.MenuRegister_List();
		map.addAttribute("rn_lookup_autofill", rn_lookup_autofill);
		model.setViewName("Rn_Menu_Register_View");
		return new ModelAndView("Rn_Menu_Register_View");

	}

// ----------------------entry form sbmit------------------------------------------

	@Transactional
	@RequestMapping(value = "/rn_menu_register_submit", method = RequestMethod.POST)
	public ModelAndView saveLookup1(@ModelAttribute Rn_Menu_Register rn_menu_register, BindingResult result,
			ModelMap map, HttpServletRequest request) throws ParseException {

		int user_id = (Integer) request.getSession().getAttribute("userid");
		System.out.println("User id sujit" + user_id);
		int id = 0;
		if (request.getParameter("id") != "") {
			id = Integer.parseInt(request.getParameter("id"));
		} else {
			id = 0;
		}
		rn_menu_register.setCreated_by(user_id);
		rn_menu_register.setLast_updated_by(user_id);
		System.out.println("hibernet data " + rn_menu_register.getEnable_flag());

		if (rn_menu_register.getEnable_flag() == null) {
			String n = "N";
			rn_menu_register.setEnable_flag(n);
		}
		System.out.println("flag" + rn_menu_register.getMain_menu_icon());
		System.out.println("date" + rn_menu_register.getEnd_date());

		Date endDate = null;
		try {
			endDate = new SimpleDateFormat("YYYY-MM-DD").parse(request.getParameter("end_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rn_menu_register.setEnd_date(endDate);

		System.out.println();
		if (id != 0) {
			rn_menu_register.setMain_menu_id(id);
			hibernateTemplate.saveOrUpdate(rn_menu_register);
		} else {
			hibernateTemplate.saveOrUpdate(rn_menu_register);

		}
		return new ModelAndView("redirect:rn_menu_register_view");

	}

// ---------------------- form help------------------------------------------

	@RequestMapping(value = "/rn_menu_register_help")
	public ModelAndView forgotpassword(ModelAndView model, ModelMap map) throws IOException {
		return new ModelAndView("Rn_Menu_Register_Help");

	}

// -----------------------for update part-----------------------------------

	@RequestMapping(value = "/rn_menu_register_update", method = RequestMethod.GET)
	public ModelAndView loadMenu(@RequestParam(value = "main_menu_id") String id1,
			// @RequestParam(value="country_code") String country,
			ModelAndView modelview, HttpServletRequest request, Model model) throws IOException {
		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {

			int SR_num = Integer.parseInt(id1);
			System.out.println("menu_id" + SR_num);
			int id = 0;
			String menu_name = null;
			String menu_action_name = null;
			String menu_icon = null;
			String enable_flag = null;
			String end_date = null;

			List<Rn_Lookup_Autofill> rn_lookup_autofill1 = rn_menu_register_service.Menu_List(SR_num);
			for (int i = 0; i < rn_lookup_autofill1.size(); i++) {
				id = rn_lookup_autofill1.get(0).getMain_menu_id();
				menu_name = rn_lookup_autofill1.get(0).getMain_menu_name();
				menu_action_name = rn_lookup_autofill1.get(0).getMain_menu_action_name();
				menu_icon = rn_lookup_autofill1.get(0).getMain_menu_icon();
				enable_flag = rn_lookup_autofill1.get(0).getEnable_flag();
				end_date = rn_lookup_autofill1.get(0).getEnd_date();

			}

			model.addAttribute("id", id);
			model.addAttribute("menu_name", menu_name);
			model.addAttribute("menu_action_name", menu_action_name);
			model.addAttribute("menu_icon", menu_icon);
			model.addAttribute("enable_flag", enable_flag);

			model.addAttribute("end_date", end_date);
			System.out.println("end date" + end_date);
			System.out.println(
					"main menu id  sujitsssss" + id + "menu name :-" + menu_name + "Action namr" + menu_action_name);

		}

		List<Rn_Lookup_Autofill> rn_lookup_autofill = rn_menu_register_service.MenuRegister_List();
		model.addAttribute("rn_lookup_autofill", rn_lookup_autofill);

		return new ModelAndView("Rn_Menu_Register_View");
	}

	@RequestMapping("/newmenugroupregister")
	public ModelAndView newgroupreport(HttpServletRequest request, ModelMap map, Model model) {
		System.out.println("Load");
		Rn_Menu_Group_Header menuGroupHeader = new Rn_Menu_Group_Header();
		Rn_Menu_Group_Line menuGroupLine = new Rn_Menu_Group_Line();
		map.addAttribute("menuGroupHeader", menuGroupHeader);
		map.addAttribute("menuGroupLine", menuGroupLine);
		return new ModelAndView("MenuGroup");
	}

	@RequestMapping(value = "/FindAction name", method = RequestMethod.GET)
	public void findUser(@RequestParam(value = "action") String action_name, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			ArrayList<Rn_Menu_Register> action_List = (ArrayList<Rn_Menu_Register>) hibernateTemplate
					.find("from MenuRegister where main_menu_action_name=?", action_name);

			Rn_Menu_Register arr = new Rn_Menu_Register();

			String name = action_List.get(0).getMain_menu_action_name();
			arr.setMain_menu_action_name(name);

			String json = null;
			json = new Gson().toJson(arr);

			response.setContentType("application/json");
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/loadMenus1", method = RequestMethod.GET)
	public @ResponseBody List<Rn_Menu_Group_Line> loadMenuName(HttpServletRequest request,
			HttpServletResponse response) {
		List<Rn_Menu_Group_Line> menus = new ArrayList<>();
		String json = null;
		CallableStatement cStmt;
		try {

			cStmt = hibernateConfiguration.dataSource().getConnection().prepareCall("{call RN_SP_MENU_NAMES(?)}");
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			ResultSet result = cStmt.executeQuery();

			ResultSet rs1 = (ResultSet) cStmt.getObject(1);
			while (rs1.next()) {
				int data = rs1.getInt(1);
				String data1 = rs1.getString(2);

				menus.add(new Rn_Menu_Group_Line(data, data1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		for (Rn_Menu_Group_Line grp_line : menus) {
			System.out.println(grp_line.getMenu_id() + " data" + grp_line.getName());
		}
		return menus;
	}

	@Transactional
	@RequestMapping(value = "/savetwojsp", method = RequestMethod.POST)
	public ModelAndView saveLookup1(@ModelAttribute Rn_Two_Jsp two_jsp, BindingResult result, ModelMap map,
			HttpServletRequest request) throws ParseException {

		// int user_id = (Integer) request.getSession().getAttribute("userid");
		// System.out.println("User id sujit"+user_id);
		/*
		 * int id = 0; if(request.getParameter("id") != ""){
		 * id=Integer.parseInt(request.getParameter("id")); }else{ id = 0; }
		 */
		String user_name = request.getParameter("user_name");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");

		String middle_name = request.getParameter("middle_name");
		String address = request.getParameter("address");
		String email_address = request.getParameter("email_address");

		two_jsp.setUser_name(user_name);
		two_jsp.setFirst_name(first_name);
		two_jsp.setLast_name(last_name);

		two_jsp.setMiddle_name(middle_name);
		two_jsp.setAddress(address);
		two_jsp.setEmail_address(email_address);

		/*
		 * Date startDate = null; try { startDate = new
		 * SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("start_date")); }
		 * catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } two_jsp.setStart_date(startDate);
		 * 
		 * Date endDate = null; try { endDate = new
		 * SimpleDateFormat("dd/mm/yyyy").parse(request.getParameter("end_date")); }
		 * catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } two_jsp.setEnd_date(endDate);
		 */

		System.out.println();
		// if(id!=0)
		// {
		// two_jsp.setId(id);
		hibernateTemplate.saveOrUpdate(two_jsp);
		// }
		// else
		// {
		// hibernateTemplate.saveOrUpdate(two_jsp);

		// }
		return new ModelAndView("redirect:distributordetails1");
		// return new ModelAndView("MenuRegister");

	}

	@Transactional
	@RequestMapping(value = "/saverecord", method = RequestMethod.POST)
	public ModelAndView saveLookup3(@ModelAttribute Rn_Ext_Fields rn, BindingResult result, ModelMap map,
			HttpServletRequest request) throws ParseException {

		// int user_id = (Integer) request.getSession().getAttribute("userid");
		// System.out.println("User id sujit"+user_id);

		String field_name = request.getParameter("field_name");

		System.out.println("ganesh-----fileld name -------" + field_name);

		String mapping = request.getParameter("lookup_code");

		System.out.println("ganesh------mapping-------" + mapping);

		String data_type = request.getParameter("meaning");
		System.out.println("ganesh------data type-------" + data_type);

		String jsp_name = "insertfield";

		rn.setField_name(field_name);
		rn.setMapping(mapping);
		rn.setData_type(data_type);
		rn.setForm_code(jsp_name);

		hibernateTemplate.saveOrUpdate(rn);

		return new ModelAndView("redirect:extensiondetails");
		// return new ModelAndView("MenuRegister");

	}

	@Transactional
	@RequestMapping(value = "/saverecord_form_builder", method = RequestMethod.POST)
	public ModelAndView saveLookup4(@ModelAttribute Rn_Ext_Fields rn, BindingResult result, ModelMap map,
			HttpServletRequest request) throws ParseException {

		// int user_id = (Integer) request.getSession().getAttribute("userid");
		// System.out.println("User id sujit"+user_id);

		String type = request.getParameter("type");

		System.out.println("------value of type----" + type);

		String field_name = request.getParameter("field_name");

		System.out.println("ganesh-----fileld name -------" + field_name);

		String mapping = request.getParameter("lookup_code");

		System.out.println("ganesh------mapping-------" + mapping);

		String data_type = request.getParameter("meaning");
		System.out.println("ganesh------data type-------" + data_type);

		// String jsp_name="insertfield";

		HttpSession session = request.getSession(false);
		String f_code = (String) session.getAttribute("form_code");

		System.out.println("--value of form code in insert field ---" + f_code);

		rn.setType(type);
		rn.setField_name(field_name);
		rn.setMapping(mapping);
		rn.setData_type(data_type);
		rn.setForm_code(f_code);

		hibernateTemplate.saveOrUpdate(rn);

		return new ModelAndView("redirect:rn_form_builder_extension");
		// return new ModelAndView("MenuRegister");

	}

}
