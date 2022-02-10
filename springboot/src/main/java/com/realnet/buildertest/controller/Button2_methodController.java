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
public ResponseEntity<?>button2_method()throws IOException {

Abtuisales  model_obj = new Abtuisales();

String label1 = "test1" ;
model_obj.setLabel1(label1);

String label2 = "test2" ;
model_obj.setLabel2(label2);


String label3 = "test3" ;
model_obj.setLabel3(label3);

String label4 = "test4" ;
model_obj.setLabel4(label4);

System.out.println("in a  btn2 method");
model_obj=abtuisalesservice.save(model_obj);
//System.out.println(+model_obj.getLabel2()+" ");
return ResponseEntity.ok().build();
	}
}