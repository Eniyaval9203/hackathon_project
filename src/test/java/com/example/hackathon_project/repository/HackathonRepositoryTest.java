package com.example.hackathon_project.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.example.hackathon_project.entity.Hackathon;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.test.properties")
public class HackathonRepositoryTest {

	   @Autowired
	    private HackathonRepository hackathonRepository;

	    @Test
	    @DisplayName("Test saving and retrieving a Hackathon")
	    public void testSaveAndFindById() {
	        // Arrange
	    	Hackathon hackathon = new Hackathon();
	        hackathon.setName("hackathon1");

	        // Act
	        Hackathon savedHackathon = hackathonRepository.save(hackathon);
	        Optional<Hackathon> retrievedHackathon = hackathonRepository.findById(savedHackathon.getId());

	        // Assert
	        assertThat(retrievedHackathon).isPresent();
	        assertThat(retrievedHackathon.get().getName()).isEqualTo("hackathon1");
	        assertEquals("hackathon1", retrievedHackathon.get().getName());
	    }

	    @Test
	    @DisplayName("Test deleting a Hackathon")
	    public void testDeleteHackathon() {
	        // Arrange
	        Hackathon hackathon = new Hackathon();
	        hackathon.setName("hackathon2");
	        Hackathon savedHackathon = hackathonRepository.save(hackathon);

	        // Act
	        hackathonRepository.deleteById(savedHackathon.getId());
	        Optional<Hackathon> deletedHackathon = hackathonRepository.findById(savedHackathon.getId());

	        // Assert
	        assertThat(deletedHackathon).isNotPresent();
	    }
	}

