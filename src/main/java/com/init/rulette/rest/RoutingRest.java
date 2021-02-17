package com.init.rulette.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RoutingRest {
	
	//@GetMapping //Disponble Mediante metodo get //no define una Url (Toma como url la base /) localhost:8080
	@RequestMapping(value="hello", method = RequestMethod.GET)
	public String hello() {
		return "Hellow Word";
	}
}
