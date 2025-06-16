package com.example.hackathon_project.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hackathon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private LocalDate date;
    
    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Team> teams;

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public Hackathon() {
		super();
	}

	public Hackathon(Long id, String name, LocalDate date, List<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "Hackathon [id=" + id + ", name=" + name + ", date=" + date + ", teams=" + teams + "]";
	}

	public void setDate(String string) {
		// TODO Auto-generated method stub
		
	}
    
    
    
}



