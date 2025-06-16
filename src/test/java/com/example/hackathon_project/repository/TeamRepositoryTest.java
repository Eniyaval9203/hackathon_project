package com.example.hackathon_project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import com.example.hackathon_project.entity.Team;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.test.properties")
public class TeamRepositoryTest {

	   @Autowired
	    private TeamRepository teamRepository;

	    @Test
	    @DisplayName("Test saving and retrieving a Team")
	    public void testSaveAndFindById() {
	        // Arrange
	    	Team team = new Team();
	        team.setTeamName("wonders");

	        // Act
	        Team savedTeam = teamRepository.save(team);
	        Optional<Team> retrievedTeam = teamRepository.findById(savedTeam.getId());

	        // Assert
	        assertThat(retrievedTeam).isPresent();
	        assertThat(retrievedTeam.get().getTeamName()).isEqualTo("wonders");
	        assertEquals("wonders", retrievedTeam.get().getTeamName());
	    }

	    @Test
	    @DisplayName("Test deleting a Team")
	    public void testDeleteTeam() {
	        // Arrange
	    	Team team = new Team();
	    	team.setTeamName("wonders");;
	    	Team savedTeam = teamRepository.save(team);

	        // Act
	    	teamRepository.deleteById(savedTeam.getId());
	        Optional<Team> deletedTeam = teamRepository.findById(savedTeam.getId());

	        // Assert
	        assertThat(deletedTeam).isNotPresent();
	    }
	}
