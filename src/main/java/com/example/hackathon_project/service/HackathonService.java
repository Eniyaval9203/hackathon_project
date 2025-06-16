package com.example.hackathon_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hackathon_project.entity.Hackathon;
import com.example.hackathon_project.repository.HackathonRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HackathonService {
	
	@Autowired
	private HackathonRepository hackathonRepository;
	
	public Hackathon saveHackathon (Hackathon hackathon) {
		return hackathonRepository.save(hackathon);
	}
	
	public Hackathon getHackathon(Long id) {
		return hackathonRepository.findById(id).orElseThrow(()-> new RuntimeException("Hackathon not found with " +id));
	}
	
	public List<Hackathon> getAllHackathon(){
		return hackathonRepository.findAll();
	}
	
	public void deleteHackathon(Long id) {
		hackathonRepository.deleteById(null);
	}
	public Hackathon updateHackathon(Long id, Hackathon updatedHackathon) {
		Hackathon hackathon = hackathonRepository.findById(id).get();
		hackathon.setName(updatedHackathon.getName());
		hackathon.setDate(updatedHackathon.getDate());
		return hackathonRepository.save(hackathon);
	}

}
