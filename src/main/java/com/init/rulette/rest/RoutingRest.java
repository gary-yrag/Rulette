package com.init.rulette.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping //(POST)
	public ResponseEntity <rulette>CreateRulette(@RequestBody rulette Rulette){
		rulette newRulette = ruletteDao.save(Rulette);
		return ResponseEntity.ok(newRulette);
	}
	
	@DeleteMapping(value="{ruletteId}")
	public ResponseEntity <Void> deleteRulette(@PathVariable("ruletteId")Long ruletteId){
		ruletteDao.deleteById(ruletteId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<rulette> updateRulette(@RequestBody rulette Rulette){
		Optional<rulette> optionalRulette = ruletteDao.findById(Rulette.getId());
		
		if(optionalRulette.isPresent()) {
			rulette updateRulette = optionalRulette.get();
			updateRulette.setStatus(Rulette.getStatus());
			ruletteDao.save(updateRulette);
			
			return ResponseEntity.ok(updateRulette);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@RequestMapping(value="RCreateRulette", method = RequestMethod.GET)
	public String RcreateRulette() {
		rulette CreateRulette = new rulette();
		Long rId = ruletteDao.count();
		rId = rId+1;
		CreateRulette.setId(rId);
		CreateRulette.setStatus("DISABLED");
		
		rulette CreatedResult = ruletteDao.save(CreateRulette);
		
		if(CreatedResult!=null) {
			return ""+CreatedResult.getId();
		}
		return "";
	}
	
	@RequestMapping(value="ROpenRulette/{RuletteId}", method=RequestMethod.GET)
	public String ROpenRulette(@PathVariable("RuletteId") Long ruletteId) {
		Optional<rulette> OptionalRulette = ruletteDao.findById(ruletteId);
		
		if(OptionalRulette.isPresent()) {
			rulette rUpdate = OptionalRulette.get();
			rUpdate.setStatus("ACTIVE");
			
			rulette rResult = ruletteDao.save(rUpdate);
			return "Ruleta Numero: " + rResult.getId() + ", Activada"; 
		}else {
			return "No existe la ruleta buscada";
		}
	}
	
	class CloseRulette{
		private Long BetsId;
		private Long UserId;
		private String Criterion;
		private double BetsValue;
		private double ValueWins;
		private String statusClose;
		
		CloseRulette(){
			
		}
		
		public Long getBetsId() {
			return BetsId;
		}
		public void setBetsId(Long betsId) {
			BetsId = betsId;
		}
		public Long getUserId() {
			return UserId;
		}
		public void setUserId(Long userId) {
			UserId = userId;
		}
		public String getCriterion() {
			return Criterion;
		}
		public void setCriterion(String criterion) {
			Criterion = criterion;
		}
		public double getBetsValue() {
			return BetsValue;
		}
		public void setBetsValue(double betsValue) {
			BetsValue = betsValue;
		}
		public double getValueWins() {
			return ValueWins;
		}
		public void setValueWins(double valueWins) {
			ValueWins = valueWins;
		}
		public String getStatusClose() {
			return statusClose;
		}
		public void setStatusClose(String statusClose) {
			this.statusClose = statusClose;
		}
	}
	
	@RequestMapping(value="RCloseRulette/{RuletteId}", method=RequestMethod.GET)
	public ResponseEntity<CloseRulette> RCloseRulette(@PathVariable("RuletteId") Long RuletteId) {
		CloseRulette cr = new CloseRulette();
		
		return ResponseEntity.ok(cr);
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
