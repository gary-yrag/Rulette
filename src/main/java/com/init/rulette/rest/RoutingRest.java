package com.init.rulette.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.rulette.entitys.rulette;

@RestController
//@RequestMapping("/")
@RequestMapping("/rulette")
public class RoutingRest {
	
	//@GetMapping //Disponble Mediante metodo get //no define una Url (Toma como url la base /) localhost:8080
	@RequestMapping(value="hello", method = RequestMethod.GET)
	public String hello() {
		return "Hellow Word";
	}
	
	@GetMapping
	public ResponseEntity<rulette> getRulette(){
		rulette r = new rulette();
		r.setId(1);
		r.setStatus("Abierto");
		
		return ResponseEntity.ok(r);		
	}
	
	//public ResponseEntity<List<rulette>> getRulettes(){
		
	//}
}
