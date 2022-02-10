package com.realnet.actionbuilder.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.realnet.comm.entity.Customer;
import com.realnet.comm.repository.CustomerRepo;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class button2_methodController {

	@Autowired
	private Ganesh_uisalesservice ganesh_uisalesservice;

	@GetMapping(value = "/button2_method")
public ResponseEntity<?>button2_method(@RequestParam(value = "id") int id,
		@RequestParam(value = "label1") String label1,
		@RequestParam(value = "label2") String label2)throws IOException {

	Ganesh_uisales  model_obj = new Ganesh_uisales();
	String label1 = null;
	model_obj.setLabel1(label1)
	
	String label2 = null;
	model_obj.setLabel2(label2)
	
	String label3 = null;
	model_obj.setLabel3(label3)
	
	String label4 = null;
	model_obj.setLabel4(label4)
	
	model_obj.setId(id)
	
	model_obj.setLabel1(label1)
	
	model_obj.setLabel2(label2)
	
	model_obj=ganesh_uisalesservice.updateById(id,model_obj);
	System.out.println(model_obj.getLabel1(),model_obj.getLabel2(),);
	return ResponseEntity.ok().build();
	}
}