package com.init.rulette.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.rulette.dao.RuletteDao;
import com.init.rulette.entitysJPA.rulette;

//import com.init.rulette.entitys.rulette;

@RestController
//@RequestMapping("/")
@RequestMapping("/rulette")
public class RoutingRest {
	
	@Autowired
	private RuletteDao ruletteDao; 
		
	//@RequestMapping(value="getRulettes", method= RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<rulette>> getRulettes(){
		List<rulette> rulettes =  ruletteDao.findAll();
		return ResponseEntity.ok(rulettes);
	}
	
	@RequestMapping(value="{ruletteId}", method= RequestMethod.GET)
	public ResponseEntity <rulette>getRuletteById(@PathVariable("ruletteId") Long productId) {
		Optional<rulette> optionalRulette = ruletteDao.findById(productId);
		
		if(optionalRulette.isPresent()) {
			return ResponseEntity.ok(optionalRulette.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping
	public ResponseEntity <rulette>CreateRulette(@RequestBody rulette Rulette){
		rulette newRulette = ruletteDao.save(Rulette);
		return ResponseEntity.ok(newRulette);
	}
	
	@DeleteMapping(value="{productId}")
	public ResponseEntity <Void> deleteRulette(@PathVariable("productId")Long produdctId){
		ruletteDao.deleteById(produdctId);
		return ResponseEntity.ok(null);
	}
	
	//@GetMapping //Disponble Mediante metodo get //no define una Url (Toma como url la base /) localhost:8080
	/*@RequestMapping(value="hello", method = RequestMethod.GET)
	public String hello() {
		return "Hellow Word";
	}
	*/
	
	/*
	@GetMapping
	public ResponseEntity<rulette> getRulette(){
		rulette r = new rulette();
		r.setId(1);
		r.setStatus("Abierto");
		
		return ResponseEntity.ok(r);		
	}
	*/
	
	/*
	@RequestMapping(value="getRulette", method = RequestMethod.GET)
	public ResponseEntity<List<com.init.rulette.entitysJPA.rulette>> getsRulette(){
		return null;
	}
	*/
	
	//public ResponseEntity<List<rulette>> getRulettes(){
		
	//}
}
