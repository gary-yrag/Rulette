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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.rulette.dao.BetsDao;
import com.init.rulette.entitysJPA.bets;

@RestController
@RequestMapping("/bets")
public class BetsRoutingRest {		
	@Autowired
	private BetsDao betsDao;
	
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
	
	@PostMapping
	public ResponseEntity <bets>CreateBets(@RequestBody bets Bets){
		bets newBets = betsDao.save(Bets);
		return ResponseEntity.ok(newBets);
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
