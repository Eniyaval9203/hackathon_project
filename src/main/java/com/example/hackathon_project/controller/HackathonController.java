package com.example.hackathon_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hackathon_project.entity.Hackathon;
import com.example.hackathon_project.exception.InvalidInput;
import com.example.hackathon_project.exception.ResourceNotFound;
import com.example.hackathon_project.service.HackathonService;

@RestController
@CrossOrigin("*")

public class HackathonController {
	@Autowired
	private HackathonService hackathonService;

	@PostMapping("/hackathon")
	public ResponseEntity<Hackathon> saveHackathon(@RequestBody Hackathon hackathon){
		if(hackathon == null) {
			throw new  InvalidInput("Given input is invalid: "+hackathon);
		}
		Hackathon saved = hackathonService.saveHackathon(hackathon);
		return new ResponseEntity<Hackathon>(saved, HttpStatus.OK);
	}
	
	@GetMapping("/hackathon")
	public ResponseEntity<List<Hackathon>> getAllHackathon(){
		List<Hackathon> allHackathons = hackathonService.getAllHackathon();
		return new ResponseEntity<List<Hackathon>>(allHackathons, HttpStatus.OK);
	}
	
	@GetMapping("/hackathon/{id}")
	public ResponseEntity<Hackathon> getById(@PathVariable Long id){
		Hackathon hackathon = hackathonService.getHackathon(id);
		return new ResponseEntity<Hackathon>(hackathon, HttpStatus.OK);
	}
	
	@PutMapping("/hackathon/{id}")
	public ResponseEntity<Hackathon> updateHackathon(@PathVariable Long id, @RequestBody Hackathon hackathon){
		if(hackathon == null) {
			throw new ResourceNotFound("Given details is not Found: " +hackathon);
		}
		Hackathon updateHackathon = hackathonService.updateHackathon(id, hackathon);
		return new ResponseEntity<Hackathon>(updateHackathon, HttpStatus.OK);
	}
	
	@DeleteMapping("/hackathon/{id}")
	public void deleteHackathon(@PathVariable Long id) {
		hackathonService.deleteHackathon(id);
	}

}
