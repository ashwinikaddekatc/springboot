package com.realnet.test_module1.controller;

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

import com.realnet.test_module1.model.Rn_nil_final;
import com.realnet.test_module1.dao.Nil_final_dao;
import com.realnet.test_module1.service.Nil_final_service;
@Controller
public class Record_updateController {
	@Autowired	private Nil_final_dao	nil_final_dao;
	// INSERT FIELDS USING ACTION BUILDER
@GetMapping(value = "/record_update")
	public ModelAndView record_update(@RequestParam(value = "id") String h_id) throws IOException {
		int hId = Integer.parseInt(h_id);
		//System.out.println("JSP ID = " + hId);
	// CFF_LAYOUT_CONTROLLER_START
		System.out.println("PLEASE INSERT CODE... GO TO ACTION BUILDER... ");
	// CFF_LAYOUT_CONTROLLER_END
		return new ModelAndView("redirect:rn_nil_final_update?id=" + hId);
	}
}