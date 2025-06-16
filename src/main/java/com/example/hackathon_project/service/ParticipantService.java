package com.example.hackathon_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hackathon_project.entity.Participant;
import com.example.hackathon_project.repository.ParticipantRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ParticipantService {
	@Autowired
	ParticipantRepository participantRepository;
	
	public Participant saveParticipant (Participant participant) {
		return participantRepository.save(participant);
	}
	
	public Participant getParticipant(Long id) {
		return participantRepository.findById(id).orElseThrow(()-> new RuntimeException("Participant is not found with "+id));
	}
	
	public List<Participant> getAllParticipant(){
		return participantRepository.findAll();
	}
	
	public void deleteParticipant(Long id) {
		participantRepository.deleteById(null);
	}
	public Participant updateParticipant(Long id, Participant updatedParticipant) {
		Participant participant = participantRepository.findById(id).get();
		participant.setName(updatedParticipant.getName());
		participant.setEmail(updatedParticipant.getEmail());
		
		return participantRepository.save(participant);
	}

}
