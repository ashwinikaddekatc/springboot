package com.realnet.niladri_module.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Button2_methodController {
	// INSERT FIELDS USING ACTION BUILDER
	@Autowired
	Rn_Cff_ActionBuilderRules_Service rn_cff_actionbuilder_utils_dao;

	@GetMapping(value = "/button2_method")
	public ModelAndView button2_method(@RequestParam(value = "id") String h_id,@ResponseBody Student student) throws IOException {
		int hId = Integer.parseInt(h_id);
		// CFF_LAYOUT_CONTROLLER_START
				System.out.println("PLEASE INSERT CODE... GO TO ACTION BUILDER... ");
		// CFF_LAYOUT_CONTROLLER_END
		return new ModelAndView("redirect:rn_abhiui_update?id=" + hId);
	}
}
