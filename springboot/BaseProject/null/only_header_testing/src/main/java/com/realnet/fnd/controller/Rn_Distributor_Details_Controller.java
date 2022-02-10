package com.realnet.fnd.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.dao.Rn_Distributor_Details_Dao;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.model.Rn_state_t;
import com.realnet.fnd.service.Rn_User_Registration_Service;

@Controller
public class Rn_Distributor_Details_Controller {

	@Autowired
	private Rn_User_Registration_Service rn_user_registration_service;

	@Autowired
	private Rn_Distributor_Details_Dao rn_distributor_details_dao;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private HibernateConfiguration hibernateConfiguration;

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * @RequestMapping(value="/customerdetails1") public ModelAndView
	 * customerDetails(ModelAndView model,HttpServletRequest request) throws
	 * IOException{ int userId =
	 * Integer.parseInt(request.getParameter("DISTRIBUTOR_ID")); String
	 * id=Integer.toString(userId);
	 * 
	 * List<Rn_Customer_Details_Grid> customerdetails_list =
	 * rn_distributor_details_dao.CustomerDetails_list(id); //Customer_Details
	 * customerdetails = new Customer_Details();
	 * model.addObject("customerdetails_list", customerdetails_list);
	 * //model.addObject("customerdetails", customerdetails);
	 * model.setViewName("customerdetails1");
	 * 
	 * return model; }
	 */
	/*
	 * @RequestMapping(value="customersites1",method = RequestMethod.GET) public
	 * ModelAndView customerSiteDetails(ModelAndView model,HttpServletRequest
	 * request) throws IOException{ int userId =
	 * Integer.parseInt(request.getParameter("CUSTOMER_ID")); String
	 * id=Integer.toString(userId);
	 * 
	 * List<Rn_Customer_Site_Details_Grid> customersitedetails_list =
	 * rn_distributor_details_dao.CustomrSiteDetails_List(id);
	 * //CustomerSite_DetailsGrid customerstes = new CustomerSite_DetailsGrid();
	 * model.addObject("customersitedetails_list", customersitedetails_list);
	 * //model.addObject("customerstes", customerstes);
	 * model.setViewName("customersitedetails1");
	 * 
	 * return model; }
	 */

	@RequestMapping(value = "/rn_user_grid")
	public ModelAndView DistributorDetails(ModelAndView model) throws IOException {
		List<Rn_Users> rn_userlist = rn_distributor_details_dao.rn_userlist();
		// Customer_Details customerdetails = new Customer_Details();
		model.addObject("rn_userlist", rn_userlist);
		// model.addObject("customerdetails", customerdetails);
		System.out.println("sujit");
		model.setViewName("Rn_Distributor_Details");

		return model;
	}

	// for autofil distributor details

	// --------------------for
	// readonly------------------------------------------------

	@RequestMapping(value = "/rn_user_readonly", method = RequestMethod.GET)
	public ModelAndView RnUserReadonly(@RequestParam(value = "user_id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);

		Rn_Users rn = new Rn_Users();
		map.addAttribute("rep_updt", rn);

		List<Rn_Users> report = rn_user_registration_service.new_view_report(u_id);
		map.addAttribute("report_update", report);

		return new ModelAndView("Rn_User_Readonly");
	}

	// -----------------------for prefield part-----------------------------------

	@RequestMapping(value = "/rn_user_update", method = RequestMethod.GET)
	public ModelAndView loadReport1(@RequestParam(value = "user_id") String id, ModelAndView modelview,
			HttpServletRequest request, ModelMap map) throws IOException {
		int u_id = Integer.parseInt(id);
		Rn_Users rn = new Rn_Users();

		map.addAttribute("rep_updt", rn);

		List<Rn_Users> report = rn_user_registration_service.new_view_report(u_id);
		map.addAttribute("report_update", report);

		return new ModelAndView("Rn_User_Update");
	}

	// --------------------------sbmit update
	// part---------------------------------------------------

	@RequestMapping(value = "/rn_user_update_submit", method = RequestMethod.POST)
	public ModelAndView rnUserUpdateSubmit(@ModelAttribute Rn_Users rn_users, BindingResult resultReportRegister,
			ModelMap map, HttpServletRequest request) throws ParseException {
		int report = 0;

		String[] user_id = request.getParameterValues("user_id");
		// int user_id = (Integer) request.getSession().getAttribute("user_id");
		String user_name[] = request.getParameterValues("user_name");
		// Date start_date= request.getParameterValues("start_date");
		// Date end_date= request.getParameterValues("end_date");
		Date start_date = null;
		try {
			start_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("start_date"));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		Date end_date = null;
		try {
			end_date = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("end_date"));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		String first_name[] = request.getParameterValues("first_name");
		String middle_name[] = request.getParameterValues("middle_name");
		String last_name[] = request.getParameterValues("last_name");
		String contact_number[] = request.getParameterValues("contact_number");
		String email_address[] = request.getParameterValues("email_address");

		int rowcount = user_id.length;

		report = rn_user_registration_service.addReport(rowcount, user_id, user_name, start_date, end_date, first_name,
				middle_name, last_name, contact_number, email_address);
		System.out.println(report);
		String check = request.getParameter("repupdt");
		map.addAttribute("check", check);
		map.addAttribute("report", report);

		Rn_Users rep_reg = new Rn_Users();
		map.addAttribute("rep_reg", rep_reg);
		List<Rn_Users> report_list = rn_user_registration_service.rn_userlist();
		map.addAttribute("report_list", report_list);

		return new ModelAndView("redirect:rn_user_grid");
	}

	@RequestMapping(value = "/userdetailshelp")
	public ModelAndView forgotpassword(ModelAndView model, ModelMap map) throws IOException {
		return new ModelAndView("userdetailshelp");

	}

	@RequestMapping(value = "/hello")
	public ModelAndView forgotpassword1(ModelAndView model, ModelMap map) throws IOException {
		return new ModelAndView("viewcreation");

	}

	@RequestMapping(value = "/user2/", method = RequestMethod.GET)
	public ResponseEntity<List<Rn_state_t>> listAllUsers() {
		System.out.println(" staart contrrjlfg");
		List<Rn_state_t> users = rn_distributor_details_dao.findAllEmployees();

		if (users.isEmpty()) {
			return new ResponseEntity<List<Rn_state_t>>(HttpStatus.NO_CONTENT);// You many decide to return
																				// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Rn_state_t>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user2/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rn_state_t> getUser(@PathVariable("id") int id) {
		System.out.println("Fetching User with id " + id);

		Rn_state_t user = rn_distributor_details_dao.findById(id);

		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Rn_state_t>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Rn_state_t>(user, HttpStatus.OK);
	}

}
