package com.realnet.fnd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.realnet.fnd.dao.Rn_Dashbord_Dao;
import com.realnet.fnd.dao.Rn_News_Login_Dao;
import com.realnet.fnd.model.Rn_Admin_Count;
import com.realnet.fnd.model.Rn_Dashbord;
import com.realnet.fnd.model.Rn_Main_Menu;
import com.realnet.fnd.model.Rn_News_Login;
import com.realnet.fnd.model.Rn_Sub_Menu;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.service.Rn_Login_Service;

@Controller
public class Rn_Login_Controller {

	@Autowired
	Rn_Login_Service rn_login_service;
	
	@Autowired
	private Rn_News_Login_Dao rn_news_login_dao;

	@Autowired
	private Rn_Dashbord_Dao rn_dashbord_dao;
	
	//----------------------handle RESOURCE NOT FOUND Exception------------------------------------------
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleResourceNotFoundException() {
		return new ModelAndView("error_404");
	}
	
	//----------------------for error 404------------------------------------------
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public ModelAndView ResourceNotFound(@PathVariable("name") final String name, HttpServletRequest request,
			ModelMap map, Model model) {
		if (name.equals("null"))
			throw new ResourceNotFoundException();
		return new ModelAndView("error_404");
	}

	//----------------------welcome page-----------------------------------------
	@RequestMapping("/")
	public ModelAndView welcomePage(HttpServletRequest request, ModelMap map, Model model) {

		HttpSession session = request.getSession();

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {
			kwm_user = (String) request.getSession().getAttribute("kwm_user");
			String username = (String) request.getSession().getAttribute("username");
			Integer userid = (Integer) request.getSession().getAttribute("userid");
			Integer profile_id = (Integer) request.getSession().getAttribute("profileid");

			int profileid = profile_id;
			System.out.println(profileid);
			List<Rn_News_Login> NewsLogin_List = rn_news_login_dao.DashbordApprover_List();
			model.addAttribute("NewsLogin", NewsLogin_List);

			if (profileid == 1) {

				int user_id = (Integer) request.getSession().getAttribute("userid");
				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

				model.addAttribute("DashbordApprover", DashbordApprover_List);

				List<Rn_Dashbord> DashbordDealerCount_List = rn_dashbord_dao.DashbordDealerCount_List(user_id);

				model.addAttribute("DashbordDealerCount", DashbordDealerCount_List);
				return new ModelAndView("redirect:dealer");

			} else if (profileid == 2) {
				
				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);
				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);

				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);
				model.addAttribute("DashbordApprover", DashbordApprover_List);
				
				String history = "abc";
				model.addAttribute("history", history);

				List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();
				model.addAttribute("AdminCount", AdminCount);

				return new ModelAndView("redirect:sales");

			} else if (profileid == 3) {

				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);
				model.addAttribute("DashbordApprover", DashbordApprover_List);
				String history = "abc";
				model.addAttribute("history", history);

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);
				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
		
				return new ModelAndView("redirect:approver");

			} else {
				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);

				List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

				model.addAttribute("AdminCount", AdminCount);

				return new ModelAndView("redirect:admin");
			}

		} else {

			Rn_Users kwm_users = new Rn_Users();
			map.addAttribute("kwm_users", kwm_users);

			System.out.println("welcomePage() method call");
			
			List<Rn_News_Login> NewsLogin_List = rn_news_login_dao.DashbordApprover_List();
			model.addAttribute("NewsLogin", NewsLogin_List);

			return new ModelAndView("welcome");
		}

	}

	//----------------------login page----------------------------------------

	@RequestMapping("/login")
	public ModelAndView loginPage(HttpServletRequest request, ModelMap map, Model model) {

		HttpSession session = request.getSession();

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		
		if (kwm_user != null) {
			kwm_user = (String) request.getSession().getAttribute("kwm_user");
			String username = (String) request.getSession().getAttribute("username");
			Integer userid = (Integer) request.getSession().getAttribute("userid");
			Integer profile_id = (Integer) request.getSession().getAttribute("profileid");

			int profileid = profile_id;
			System.out.println(profileid);
			
			List<Rn_News_Login> NewsLogin_List = rn_news_login_dao.DashbordApprover_List();
			model.addAttribute("NewsLogin", NewsLogin_List);

			if (profileid == 1) {

				int user_id = (Integer) request.getSession().getAttribute("userid");
				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

				model.addAttribute("DashbordApprover", DashbordApprover_List);

				List<Rn_Dashbord> DashbordDealerCount_List = rn_dashbord_dao.DashbordDealerCount_List(user_id);

				model.addAttribute("DashbordDealerCount", DashbordDealerCount_List);
				return new ModelAndView("redirect:dealer");

			} else if (profileid == 2) {


				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);

				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

				model.addAttribute("DashbordApprover", DashbordApprover_List);
				
				String history = "abc";
				model.addAttribute("history", history);

				List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

				model.addAttribute("AdminCount", AdminCount);

				return new ModelAndView("redirect:sales");

			} else if (profileid == 3) {

				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);
				model.addAttribute("DashbordApprover", DashbordApprover_List);
				
				String history = "abc";
				model.addAttribute("history", history);

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
				return new ModelAndView("redirect:approver");

			} else {
				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);

				List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

				model.addAttribute("AdminCount", AdminCount);

				return new ModelAndView("redirect:admin");

			}

		} else {

			Rn_Users kwm_users = new Rn_Users();

			map.addAttribute("kwm_users", kwm_users);

			System.out.println("loginPage() call");
			List<Rn_News_Login> NewsLogin_List = rn_news_login_dao.DashbordApprover_List();
			model.addAttribute("NewsLogin", NewsLogin_List);

			return new ModelAndView("login");
		}

	}

	//----------------------login page 2----------------------------------------

	@RequestMapping(value = "/rn_login", method = RequestMethod.GET)
	public ModelAndView getLoginForm2(ModelMap map) {
		Rn_Users kwm_users = new Rn_Users();

		map.addAttribute("kwm_users", kwm_users);

		System.out.println("getLoginForm2() method call");

		return new ModelAndView("login");
	}

	//----------------------login form----------------------------------------

	@RequestMapping(value = "/rn_login", method = RequestMethod.POST)
	public ModelAndView getLoginForm(HttpServletRequest request, @ModelAttribute("kwm_users") Rn_Users kwm_users,
			BindingResult resultkwm_users, final RedirectAttributes re, ModelMap map, Model model) {

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user == null) {

			System.out.println(" login details " + kwm_users.getUser_name() + " " + kwm_users.getPassword());

			List<Rn_Users> Kwm_Userslist = rn_login_service.getUser(kwm_users);

			if ((Kwm_Userslist != null) && (Kwm_Userslist.size() > 0)) {
				Rn_Users Kwm_User = (Rn_Users) Kwm_Userslist.get(0);
				System.out.println("list doa " + Kwm_User.getUser_id() + " " + Kwm_User.getUser_name() + " "
						+ kwm_users.getPassword());

				HttpSession session = request.getSession(true);

				session.setAttribute("kwm_user", Kwm_User.getUser_name());

				System.out.println("Last name = " + Kwm_User.getLast_name());
				System.out.println("First name = " + Kwm_User.getFirst_name());

				if (Kwm_User.getLast_name() != null && Kwm_User.getFirst_name() != null) {
					session.setAttribute("username", Kwm_User.getLast_name() + ", " + Kwm_User.getFirst_name());
				} else {
					session.setAttribute("username", Kwm_User.getUser_name());
				}

				session.setAttribute("userid", Kwm_User.getUser_id());
				session.setAttribute("profileid", Kwm_User.getProfile_id());
				session.setMaxInactiveInterval(-1);

				re.addFlashAttribute("kwm_user", Kwm_User);
				int profileid = Kwm_User.getProfile_id();
				System.out.println(profileid);
				System.out.println("==========================");

				if (Kwm_User.getUser_type().equals("DISTRIBUTOR") || Kwm_User.getUser_type().equals("APPROVER")
						|| Kwm_User.getUser_type().equals("REVIEWER") || Kwm_User.getUser_type().equals("ADMIN")) {

					List<Rn_Main_Menu> menu_group = rn_login_service.get_user_main_menu(Kwm_User.getUser_id());

					for (Rn_Main_Menu mg : menu_group) {
						System.out.println(mg.getMain_menu_id() + " " + mg.getMain_menu_name());

						List<Rn_Sub_Menu> menu_line = rn_login_service.get_user_sub_menu(mg.getMain_menu_id(),
								mg.getMenu_type());

						for (Rn_Sub_Menu ml : menu_line) {

							System.out.println(
									ml.getSub_menu_id() + " " + ml.getMain_menu_id() + " " + ml.getSub_menu_name());

						}

						if (menu_line.isEmpty()) {

						} else {

							System.out.println("submenu" + Integer.toString(mg.getMain_menu_id()));

							session.setAttribute("submenu" + Integer.toString(mg.getMain_menu_id()), menu_line);

						}
					}

					session.setAttribute("menus", menu_group);

				} else {
					List<Rn_Main_Menu> menu_group = rn_login_service.getMenuGroup(Kwm_User.getProfile_id());

					for (Rn_Main_Menu mg : menu_group) {
						System.out.println(mg.getMain_menu_id() + " " + mg.getMain_menu_name());

						List<Rn_Sub_Menu> menu_line = rn_login_service.getSubMenuLine(mg.getMain_menu_id());

						for (Rn_Sub_Menu ml : menu_line) {

							System.out.println(
									ml.getSub_menu_id() + " " + ml.getMain_menu_id() + " " + ml.getSub_menu_name());

						}

						if (menu_line.isEmpty()) {

						} else {

							System.out.println("submenu" + Integer.toString(mg.getMain_menu_id()));

							session.setAttribute("submenu" + Integer.toString(mg.getMain_menu_id()), menu_line);

						}
					}

					session.setAttribute("menus", menu_group);

				}

				if (profileid == 1) {
					System.out.println("view_aceview1");
					int user_id = (Integer) request.getSession().getAttribute("userid");

					List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

					model.addAttribute("DashbordApprover", DashbordApprover_List);
					List<Rn_Dashbord> DashbordDealerCount_List = rn_dashbord_dao.DashbordDealerCount_List(user_id);

					model.addAttribute("DashbordDealerCount", DashbordDealerCount_List);
					return new ModelAndView("redirect:dealer");

				} else if (profileid == 2) {
			

					int user_id = (Integer) request.getSession().getAttribute("userid");

					List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

					model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
					List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);
					String history = "abc";
					model.addAttribute("history", history);

					model.addAttribute("DashbordApprover", DashbordApprover_List);

					List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

					model.addAttribute("AdminCount", AdminCount);

					return new ModelAndView("redirect:sales");

				} else if (profileid == 3) {
					int user_id = (Integer) request.getSession().getAttribute("userid");

					List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

					model.addAttribute("DashbordApprover", DashbordApprover_List);
					String history = "abc";
					model.addAttribute("history", history);

					List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

					model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
					System.out.println("view_aceview3");
					return new ModelAndView("redirect:approver");

				}

				else {
					int user_id = (Integer) request.getSession().getAttribute("userid");

					List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

					model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
					System.out.println("admindashboard");
					List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

					model.addAttribute("AdminCount", AdminCount);
					return new ModelAndView("redirect:admin");

				}


			} else {

				map.addAttribute("kwm_users", kwm_users);
				return new ModelAndView("login", "message", "Please check your credentials...");
			}

		} else {

			kwm_user = (String) request.getSession().getAttribute("kwm_user");
			String username = (String) request.getSession().getAttribute("username");
			Integer userid = (Integer) request.getSession().getAttribute("userid");
			Integer profile_id = (Integer) request.getSession().getAttribute("profileid");

			int profileid = profile_id;
			System.out.println(profileid);
			
			if (profileid == 1) {
				int user_id = (Integer) request.getSession().getAttribute("userid");
				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

				model.addAttribute("DashbordApprover", DashbordApprover_List);
				String history = "abc";
				model.addAttribute("history", history);

				List<Rn_Dashbord> DashbordDealerCount_List = rn_dashbord_dao.DashbordDealerCount_List(user_id);

				model.addAttribute("DashbordDealerCount", DashbordDealerCount_List);
				return new ModelAndView("redirect:dealer");

			} else if (profileid == 2) {

				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);

				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

				model.addAttribute("DashbordApprover", DashbordApprover_List);
				String history = "abc";
				model.addAttribute("history", history);

				List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

				model.addAttribute("AdminCount", AdminCount);

				return new ModelAndView("redirect:sales");

			}

			else if (profileid == 3) {
				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

				model.addAttribute("DashbordApprover", DashbordApprover_List);
				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);
				String history = "abc";
				model.addAttribute("history", history);

				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
				return new ModelAndView("redirect:approver");

			} else {
				int user_id = (Integer) request.getSession().getAttribute("userid");

				List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

				model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
				List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

				model.addAttribute("AdminCount", AdminCount);
				return new ModelAndView("redirect:admin");

			}

		}

	}
	
	//----------------------dealer----------------------------------------

	@RequestMapping("/dealer")
	public ModelAndView dealer(HttpServletRequest request, ModelMap map, Model model) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1 * 900);

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		int user_id = (Integer) request.getSession().getAttribute("userid");

		List<Rn_Dashbord> DashbordDealerCount_List = rn_dashbord_dao.DashbordDealerCount_List(user_id);
		List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

		model.addAttribute("DashbordApprover", DashbordApprover_List);
		String history = "abc";
		model.addAttribute("history", history);

		if (kwm_user != null) {

			System.out.println("dealer() method call ");
			model.addAttribute("DashbordDealerCount", DashbordDealerCount_List);
			return new ModelAndView("dealerdashboard");

		} else {
			System.out.println("dealer logout");
			List<Rn_News_Login> NewsLogin_List = rn_news_login_dao.DashbordApprover_List();
			model.addAttribute("NewsLogin", NewsLogin_List);
			return new ModelAndView("redirect:logout");

		}
	}

	//----------------------sales---------------------------------------

	@RequestMapping("/sales")
	public ModelAndView sales(HttpServletRequest request, ModelMap map, Model model) {
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1 * 900);

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {


			System.out.println("sales() method call");
			int user_id = (Integer) request.getSession().getAttribute("userid");

			List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

			model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);

			List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

			model.addAttribute("DashbordApprover", DashbordApprover_List);
			String history = "abc";
			model.addAttribute("history", history);

			List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

			model.addAttribute("AdminCount", AdminCount);

			return new ModelAndView("admindashboard");

		} else {
			return new ModelAndView("redirect:logout");
		}
	}

	//----------------------approver----------------------------------------

	@RequestMapping("/approver")
	public ModelAndView approver(HttpServletRequest request, ModelMap map, Model model) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1 * 900);

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");

		if (kwm_user != null) {

			int user_id = (Integer) request.getSession().getAttribute("userid");

			List<Rn_Dashbord> DashbordApprover_List = rn_dashbord_dao.DashbordApprover_List(user_id);

			model.addAttribute("DashbordApprover", DashbordApprover_List);
			String history = "abc";
			model.addAttribute("history", history);

			List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

			model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
			System.out.println("view_aceview3");
			return new ModelAndView("approverdashboard");

		} else {
			return new ModelAndView("redirect:logout");
		}
	}
	//----------------------admin----------------------------------------

	@RequestMapping("/admin")
	public ModelAndView admin(HttpServletRequest request, ModelMap map, Model model) {
		// HttpSession session = request.getSession(false);
		//
		// if (session != null) {

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1 * 900);

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {

			int user_id = (Integer) request.getSession().getAttribute("userid");

			List<Rn_Dashbord> DashbordSalesCount_List = rn_dashbord_dao.DashbordSalesCount_List(user_id);

			model.addAttribute("DashbordSalesCount", DashbordSalesCount_List);
			List<Rn_Admin_Count> AdminCount = rn_dashbord_dao.AdminCount_List();

			model.addAttribute("AdminCount", AdminCount);
			return new ModelAndView("admindashboard");
		} else {
			return new ModelAndView("redirect:logout");
		}
	}
	//----------------------logout----------------------------------------

	@RequestMapping("/logout")
	public ModelAndView LogoutForm(HttpServletRequest request, ModelMap map, Model model) {
		HttpSession session = request.getSession();

		Integer profile_id = (Integer) request.getSession().getAttribute("profileid");

		if (profile_id != null) {
			int profileid = profile_id;
			System.out.println(profileid);

			List<Rn_Main_Menu> menu_group = rn_login_service.getMenuGroup(profileid);

			for (Rn_Main_Menu mg : menu_group) {
				System.out.println(mg.getMain_menu_id() + " " + mg.getMain_menu_name());

				System.out.println("submenu" + Integer.toString(mg.getMain_menu_id()));

				List<Rn_Sub_Menu> menu_line = rn_login_service.getSubMenuLine(mg.getMain_menu_id());

				for (Rn_Sub_Menu ml : menu_line) {

					System.out.println(ml.getSub_menu_id() + " " + ml.getMain_menu_id() + " " + ml.getSub_menu_name());

				}

				session.removeAttribute("submenu" + Integer.toString(mg.getMain_menu_id()));

			}

			session.removeAttribute("menus");

		}

		session.removeAttribute("kwm_user");
		session.removeAttribute("username");
		session.removeAttribute("userid");
		session.removeAttribute("profileid");


		session.invalidate();
		System.out.println("session destroyrd");

		Rn_Users kwm_users = new Rn_Users();

		map.addAttribute("kwm_users", kwm_users);

		System.out.println("LogoutForm() method call");
		List<Rn_News_Login> NewsLogin_List = rn_news_login_dao.DashbordApprover_List();
		model.addAttribute("NewsLogin", NewsLogin_List);
		return new ModelAndView("login");

	}
	//----------------------session Invalid----------------------------------------

	@RequestMapping("/sessionInvalid")
	public ModelAndView sessionInvalid(HttpServletRequest request, ModelMap map) {

		Rn_Users kwm_users = new Rn_Users();
		map.addAttribute("kwm_users", kwm_users);

		System.out.println("sessionInvalid() method call");

		return new ModelAndView("login");
	}

}