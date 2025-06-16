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
import com.example.hackathon_project.entity.Team;
import com.example.hackathon_project.exception.InvalidInput;
import com.example.hackathon_project.exception.ResourceNotFound;
import com.example.hackathon_project.service.TeamService;

@RestController
@CrossOrigin("*")
public class TeamController {
	@Autowired
	private TeamService teamService;
	
	@PostMapping("/team")
	public ResponseEntity<Team> saveTeam(@RequestBody Team team){
		if(team == null) {
			throw new  InvalidInput("Given input is invalid: "+team);
		}
		Team saved = teamService.saveTeam(team);
		return new ResponseEntity<Team>(saved, HttpStatus.OK);
	}
	
	@GetMapping("/team")
	public ResponseEntity<List<Team>> getAllTeam(){
		List<Team> allTeams = teamService.getAllTeam();
		return new ResponseEntity<List<Team>>(allTeams, HttpStatus.OK);
	}
	
	@GetMapping("/team/{id}")
	public ResponseEntity<Team> getById(@PathVariable Long id){
		Team team = teamService.getTeam(id);
		return new ResponseEntity<Team>(team, HttpStatus.OK);
	}
	
	@PutMapping("/team/{id}")
	public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team){
		if(team == null) {
			throw new ResourceNotFound("Given details is not Found: " +team);
		}
		Team updateTeam = teamService.updateTeam(id, team);
		return new ResponseEntity<Team>(updateTeam, HttpStatus.OK);
	}
	
	@DeleteMapping("/team/{id}")
	public void deleteTeam(@PathVariable Long id) {
		teamService.deleteTeam(id);
	}

}
