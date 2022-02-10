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
public class Button3_methodController {

	@Autowired
	private Abtuisalesservice abtuisalesservice;

	@GetMapping(value = "/button3_method")
public ResponseEntity<?>button3_method(@RequestParam(value = "id") int id,@RequestParam(value = "label1") int label1,@RequestParam(value = "label2") String label2)throws IOException {

Abtuisales  model_obj = new Abtuisales();
String label3 = null ;
model_obj.setLabel3(label3);

String label4 = null ;
model_obj.setLabel4(label4);

model_obj=abtuisalesservice.updateById(id,model_obj);
System.out.println(model_obj.getLabel1()+" "+model_obj.getLabel2()+" ");
return ResponseEntity.ok().build();
	}
}