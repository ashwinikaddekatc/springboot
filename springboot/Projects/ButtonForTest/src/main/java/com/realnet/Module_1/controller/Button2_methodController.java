package com.realnet.Module_1.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.Module_1.model.Cust_details_t;
import com.realnet.Module_1.dao.Cust_details_dao;
import com.realnet.Module_1.service.Cust_details_service;
@Controller
public class Button2_methodController {
	@Autowired	private Cust_details_dao	cust_details_dao;
	// INSERT FIELDS USING ACTION BUILDER
@GetMapping(value = "/button2_method")
	public ModelAndView button2_method(@RequestParam(value = "id") String h_id) throws IOException {
		int hId = Integer.parseInt(h_id);
		//System.out.println("JSP ID = " + hId);
	// CFF_LAYOUT_CONTROLLER_START
		System.out.println("PLEASE INSERT CODE... GO TO ACTION BUILDER... ");
	// CFF_LAYOUT_CONTROLLER_END
		return new ModelAndView("redirect:cust_details_t_update?id=" + hId);
	}
}