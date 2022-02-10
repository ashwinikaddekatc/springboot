package com.realnet.buildertest.controller;

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
import com.realnet.Module.salesnew.service.Abtuisalesservice;
import com.realnet.Module.salesnew.entity.Abtuisales;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Button2_methodController {

	@Autowired
	private Abtuisalesservice abtuisalesservice;

	@GetMapping(value = "/button2_method")
public ResponseEntity<?>button2_method(@RequestParam(value = "label1") String label1)throws IOException {

Abtuisales  model_obj = new Abtuisales();
String label3 = "sdsds" ;
model_obj.setLabel3(label3);

String label4 = "dsds" ;
model_obj.setLabel4(label4);

model_obj.setLabel1(label1);


model_obj=abtuisalesservice.save(model_obj);
System.out.println();
return ResponseEntity.ok().build();
	}
}