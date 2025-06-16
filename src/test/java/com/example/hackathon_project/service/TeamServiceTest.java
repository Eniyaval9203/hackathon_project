package com.example.hackathon_project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hackathon_project.entity.Participant;
import com.example.hackathon_project.entity.Team;
import com.example.hackathon_project.repository.ParticipantRepository;
import com.example.hackathon_project.repository.TeamRepository;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

	@Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team team1;
    private Team team2;

    @BeforeEach
    public void setUp() {
    	team1 = new Team();
    	team1.setId(1L);
    	team1.setTeamName("eniya");

        
    	team2 = new Team();
    	team2.setId(2L);
    	team2.setTeamName("sweety");
    	
    }
    
    @Test
    public void testSaveTeam() {
    	
    	//Assign - Given
        when(teamRepository.save(team1)).thenReturn(team1);

        //Action -
        Team savedTeam = teamService.saveTeam(team1);
        

        assertThat(savedTeam).isNotNull();
        assertThat(savedTeam.getTeamName()).isEqualTo("eniya");
        verify(teamRepository, times(1)).save(team1);
    }

    @Test
    public void testGetTeam() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team1));

        Team foundTeam = teamService.getTeam(1L);

        assertThat(foundTeam).isNotNull();
        assertThat(foundTeam.getTeamName()).isEqualTo("eniya");
        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllTeams() {
        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1, team2));

        List<Team> teams = teamService.getAllTeam();

        assertThat(teams).hasSize(2);
        verify(teamRepository, times(1)).findAll();
    }

//    @Test
//    public void testRemoveTeam() {
//    	doNothing().when(teamRepository).deleteById(1L);
//
//        teamService.deleteTeam(1L);
//
//        verify(teamRepository, times(1)).deleteById(1L);
//    }
}
