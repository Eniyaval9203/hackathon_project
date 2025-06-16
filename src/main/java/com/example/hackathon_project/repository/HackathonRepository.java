package com.example.hackathon_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon_project.entity.Hackathon;


@Repository
public interface HackathonRepository extends JpaRepository<Hackathon,Long>{

}
