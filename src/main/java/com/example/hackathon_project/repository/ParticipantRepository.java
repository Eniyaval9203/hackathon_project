package com.example.hackathon_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon_project.entity.Participant;
@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
