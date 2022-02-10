package com.realnet.robmod1.controller;

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

import com.realnet.robmod1.model.Justforfun_t;
import com.realnet.robmod1.dao.Justforfun_dao;
import com.realnet.robmod1.service.Justforfun_service;
@Controller
public class Button2_methodController {
	@Autowired	private Justforfun_dao	justforfun_dao;
	// INSERT FIELDS USING ACTION BUILDER
@GetMapping(value = "/button2_method")
	public ModelAndView button2_method(@RequestParam(value = "id") String h_id) throws IOException {
		int hId = Integer.parseInt(h_id);
		//System.out.println("JSP ID = " + hId);
	// CFF_LAYOUT_CONTROLLER_START
		System.out.println("PLEASE INSERT CODE... GO TO ACTION BUILDER... ");
	// CFF_LAYOUT_CONTROLLER_END
		return new ModelAndView("redirect:justforfun_t_update?id=" + hId);
	}
}