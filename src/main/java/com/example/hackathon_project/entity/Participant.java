package com.example.hackathon_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String name;
	private String email;
    @ManyToOne 
    private Team team;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Participant() {
		super();
	}
	public Participant(Long id, String name, String email, Team team) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.team = team;
	}
	@Override
	public String toString() {
		return "Participant [id=" + id + ", name=" + name + ", email=" + email + ", team=" + team + "]";
	}
}
