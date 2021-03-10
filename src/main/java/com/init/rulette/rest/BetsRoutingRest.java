package com.init.rulette.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.rulette.dao.BetsDao;
import com.init.rulette.dao.RuletteDao;
import com.init.rulette.entitysJPA.bets;
import com.init.rulette.entitysJPA.rulette;

@RestController
@RequestMapping("/bets")
public class BetsRoutingRest {		
	@Autowired
	private BetsDao betsDao;
	private RuletteDao ruletteDao;
	
	@GetMapping		
	public ResponseEntity<List<bets>> getBeats(){
		List<bets> bets =  betsDao.findAll();
		return ResponseEntity.ok(bets);
	}
	
	@RequestMapping(value="{betsId}", method= RequestMethod.GET)
	public ResponseEntity <bets>getBetsById(@PathVariable("betsId") Long betsId) {
		Optional<bets> optionalBets = betsDao.findById(betsId);
		
		if(optionalBets.isPresent()) {
			return ResponseEntity.ok(optionalBets.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	class Message{
		private String MessageId;
		private String MessageStr;
		
		Message(){}
		
		public String getMessageId() {
			return MessageId;
		}
		public void setMessageId(String messageId) {
			MessageId = messageId;
		}
		public String getMessageStr() {
			return MessageStr;
		}
		public void setMessageStr(String messageStr) {
			MessageStr = messageStr;
		}
	}
	
	/*@PostMapping
	public ResponseEntity <bets>CreateBets(@RequestBody bets Bets){
		bets newBets = betsDao.save(Bets);
		return ResponseEntity.ok(newBets);
	}*/
	//@RequestMapping(value="/CreateBets",headers= {}, method=RequestMethod.POST)
	@PostMapping
	public ResponseEntity <Message>CreateBets(@RequestHeader("idCliente") Long idCliente, @RequestBody bets Bets){
		Message msg = new Message();
		if(!this.validIsActiveRulette(Bets.getRulette_id())) {
			
			msg.setMessageId("01");
			msg.setMessageStr("Ruleta desactivada o no existe");
			return ResponseEntity.ok(msg);
		}
		
		//System.out.println("idCliente: "+idCliente);
		boolean valid = false;
		if(Bets.getBetting_criterion()== "black" || Bets.getBetting_criterion()=="red") {
			valid = true;
		}else {
			Ivalidations Iv = validationsNumber::new;
			validationsNumber vfn = Iv.v("");
			
			if(vfn.isValidNumber()) {
				Integer in = vfn.getNumber();
				valid = true;
			}
		}
		
		IvalidationsV Iv2 = validationsSaldo::new;
		validationsSaldo vfn = Iv2.v2("");
		
		boolean rvalid = vfn.ValidValue();		
		
		if(rvalid && valid) {
			Bets.setUser_id(idCliente);
			bets newBets = betsDao.save(Bets);
			
			msg.setMessageId("02");
			msg.setMessageStr("Apuesta realizada correctamente");
			
			return ResponseEntity.ok(msg);
			//return ResponseEntity.ok(newBets);
		}else {
			msg.setMessageId("03");
			msg.setMessageStr("El valor aportado o criterio apostdo no es valido");
			
			return ResponseEntity.ok(msg);			
			//return ResponseEntity.notFound().build();
		}
		
	}
	
	private boolean validIsActiveRulette(Long IdRulette) {		
		Optional<rulette> Rulette =  ruletteDao.findById(IdRulette);
		
		if(Rulette.isPresent()) {
			rulette ResultOptional = Rulette.get();
			if(ResultOptional.getStatus() == "Active") {
				return true;
			}
		}
		return false;
		
	}
	
	interface Ivalidations{
		validationsNumber v (String Value);		
		//boolean isValidNumber(validations v);
		//int getNumber(validations v);
	} 
	interface IvalidationsV{
		validationsSaldo v2 (String Value);
	}
	
	
	class validationsNumber{
		private String PossibleNumber;
		validationsNumber (String PossibleNumber){
			this.PossibleNumber = PossibleNumber;
		}
		
		public boolean isValidNumber() {
			try {
				Integer.valueOf(PossibleNumber);
				return true;
			}catch(NumberFormatException e) {
				return false;
			}
		}
		
		public int getNumber() {
			int in = Integer.valueOf(PossibleNumber);
			if(in>=0 && in<=36) {
				return in;
			}
			return 0;
		}
	}
	
	class validationsSaldo{
		private String v ;
		validationsSaldo(String v){
			this.v = v ;
		}
		
		public boolean ValidValue() {
			double t =(double)Double.parseDouble(v); 
			if(t<=10000) {
				return true;
			}
			return false;
		}
		
		public double getValue() {
			double t =(double)Double.parseDouble(v); 
			return t;
		}
	}
	
	
	
	@DeleteMapping(value="{betsId}")
	public ResponseEntity <Void> deleteRulette(@PathVariable("betsId")Long betsId){
		betsDao.deleteById(betsId);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping
	public ResponseEntity<bets> updateBets(@RequestBody bets Bets){
		Optional<bets> optionalBets = betsDao.findById(Bets.getId());
		
		if(optionalBets.isPresent()) {
			bets updateBets = optionalBets.get();
			updateBets.setBet_value(Bets.getBet_value());
			updateBets.setBetting_criterion(Bets.getBetting_criterion());
			betsDao.save(updateBets);
			
			return ResponseEntity.ok(updateBets);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
