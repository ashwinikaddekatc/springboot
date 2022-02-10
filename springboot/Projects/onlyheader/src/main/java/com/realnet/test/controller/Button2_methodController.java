package com.realnet.test.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

 @RestController
public class Button2_methodController {
	// INSERT FIELDS USING ACTION BUILDER
@GetMapping(value = "/button2_method")
	public ResponseEntity<?> button2_method() throws IOException {
	// CFF_LAYOUT_CONTROLLER_START
		System.out.println("PLEASE INSERT CODE... GO TO ACTION BUILDER... ");
	// CFF_LAYOUT_CONTROLLER_END
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}