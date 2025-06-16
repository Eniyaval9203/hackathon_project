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
import com.example.hackathon_project.entity.Participant;
import com.example.hackathon_project.exception.InvalidInput;
import com.example.hackathon_project.exception.ResourceNotFound;
import com.example.hackathon_project.service.ParticipantService;

@RestController
@CrossOrigin("*")


public class ParticipantController {
	@Autowired
	private ParticipantService participantService;
	
	@PostMapping("/participant")
	public ResponseEntity<Participant> saveParticipant(@RequestBody Participant participant){
		if(participant == null) {
			throw new  InvalidInput("Given input is invalid: "+participant);
		}
		Participant saved = participantService.saveParticipant(participant);
		return new ResponseEntity<Participant>(saved, HttpStatus.OK);
	}
	
	@GetMapping("/participant")
	public ResponseEntity<List<Participant>> getAllParticipant(){
		List<Participant> allParticipants = participantService.getAllParticipant();
		return new ResponseEntity<List<Participant>>(allParticipants, HttpStatus.OK);
	}
	
	@GetMapping("/participant/{id}")
	public ResponseEntity<Participant> getById(@PathVariable Long id){
		Participant participant = participantService.getParticipant(id);
		return new ResponseEntity<Participant>(participant, HttpStatus.OK);
	}
	
	@PutMapping("/participant/{id}")
	public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participant){
		if(participant == null) {
			throw new ResourceNotFound("Given details is not Found: " +participant);
		}
		Participant updateParticipant = participantService.updateParticipant(id, participant);
		return new ResponseEntity<Participant>(updateParticipant, HttpStatus.OK);
	}
	
	@DeleteMapping("/participant/{id}")
	public void deleteParticipant(@PathVariable Long id) {
		participantService.deleteParticipant(id);
	}

}
