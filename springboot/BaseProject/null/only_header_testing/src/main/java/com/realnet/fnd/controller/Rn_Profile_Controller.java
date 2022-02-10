package com.realnet.fnd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.service.Rn_User_Profile_Service;


@Controller
public class Rn_Profile_Controller {

	@Autowired
	Rn_User_Profile_Service rn_user_profile_service;

	@RequestMapping("/Profile")
	public ModelAndView userProfile(HttpServletRequest request, Model model) {
		
		String user = (String) request.getSession().getAttribute("kwm_user");
		if (user != null) {
			int user_id = (Integer) request.getSession().getAttribute("userid");

			List<Rn_Users> Pend_req = rn_user_profile_service.getUserData(user_id);

			model.addAttribute("User_Details", Pend_req);

			return new ModelAndView("User_Profile");
		} else {
			return new ModelAndView("redirect:logout");
			
		}
	}

	@RequestMapping("/user-workbench")
	public ModelAndView userWorkbench(HttpServletRequest request, Model model) {

		String user = (String) request.getSession().getAttribute("kwm_user");
		if (user != null) {

			int user_id = (Integer) request.getSession().getAttribute("userid");

			List<Rn_Users> Pend_req1 = rn_user_profile_service.getUsers();

			model.addAttribute("listuserdetails", Pend_req1);
		}

		return new ModelAndView("User_workbench");

	}

	@RequestMapping("/activeuser")
	public ModelAndView userActive(HttpServletRequest request, Model model) {
		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {

			int user_id = (Integer) request.getSession().getAttribute("userid");

			// int user_id=1;
			String s1 = "A";

			int id = Integer.parseInt(request.getParameter("user_id"));
			System.out.println("controller id" + id);

			Rn_Users k = new Rn_Users();

			String Pend_req = rn_user_profile_service.updateuser(user_id, id, s1);

			System.out.println("msg" + Pend_req);

			List<Rn_Users> Pend_req1 = rn_user_profile_service.getUsers();

			model.addAttribute("listuserdetails", Pend_req1);
			model.addAttribute("displayresult", Pend_req);
		}
		return new ModelAndView("User_workbench");
	}

	@RequestMapping("/deactiveuser")
	public ModelAndView userDeactive(HttpServletRequest request, Model model) {
		String user = (String) request.getSession().getAttribute("kwm_user");
		if (user != null) {

			int user_id = (Integer) request.getSession().getAttribute("userid");
			String s1 = "D";
			// int user_id=1;
			int id = Integer.parseInt(request.getParameter("user_id"));
			System.out.println("controller id" + id);

			Rn_Users k = new Rn_Users();

			String Pend_req = rn_user_profile_service.updateuser(user_id, id, s1);
			System.out.println("msg" + Pend_req);
			List<Rn_Users> Pend_req1 = rn_user_profile_service.getUsers();

			model.addAttribute("listuserdetails", Pend_req1);
			model.addAttribute("displayresult", Pend_req);
		}

		return new ModelAndView("User_workbench");

	}

}
