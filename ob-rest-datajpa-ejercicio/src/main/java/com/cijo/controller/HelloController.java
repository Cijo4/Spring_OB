package com.cijo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
	
	@Value("${app.message}")
	String message;
	
	@Value("${app.varexample}")
	String message2;

	@GetMapping("/hello")
	public String Hello() {
		System.out.println(message);
		System.out.println(message2);
		return "Hello world!!";
	}
}
