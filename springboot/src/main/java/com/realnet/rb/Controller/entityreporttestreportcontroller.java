package com.realnet.rb.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.realnet.comm.entity.*;
import com.realnet.actionbuilder.service.*;
import com.realnet.bi.service.*;
import com.realnet.codeextractor.service.*;
import com.realnet.comm.service.*;
import com.realnet.flf.service.*;
import com.realnet.fnd.service.*;
import com.realnet.qb.service.*;
import com.realnet.rb.service.*;
import com.realnet.users.service.*;
import com.realnet.wfb.service.*;

import com.realnet.rb.response.Rn_tableResponse;
import com.realnet.rb.service.reportbuilder2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Rn_report_builder" })
public class entityreporttestreportcontroller {
	
	
	@Autowired
	private Rn_Bcf_Extractor_Service  aser;


	@ApiOperation(value = "Add A Tables", response = Rn_tableResponse.class)
	@GetMapping("/entityreporttestapi")
	public ResponseEntity<?> reportgetall(){
	Object getall = (Object)aser.getAll();		
		
		return ResponseEntity.ok(getall);
	}
	

}
