package com.example.hackathon_project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hackathon_project.entity.Team;
import com.example.hackathon_project.repository.TeamRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;
	
	public Team saveTeam (Team team) {
		return teamRepository.save(team);
	}
	
	public Team getTeam(Long id) {
		return teamRepository.findById(id).orElseThrow(()-> new RuntimeException ("Team is not found with "+id));
	}
	
	public List<Team> getAllTeam(){
		return teamRepository.findAll();
	}
	
	public void deleteTeam(Long id) {
		teamRepository.deleteById(null);
	}
	public Team updateTeam(Long id, Team updatedTeam) {
		Team team = teamRepository.findById(id).get();
		team.setTeamName(updatedTeam.getTeamName());
		
		return teamRepository.save(team);
	}

}
