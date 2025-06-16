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
import com.example.hackathon_project.entity.Participant;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.test.properties")
public class ParticipantRepositoryTest {

	   @Autowired
	    private ParticipantRepository participantRepository;

	    @Test
	    @DisplayName("Test saving and retrieving a Participant")
	    public void testSaveAndFindById() {
	        // Arrange
	    	Participant participant = new Participant();
	        participant.setName("eniya");

	        // Act
	        Participant savedParticipant = participantRepository.save(participant);
	        Optional<Participant> retrievedParticipant = participantRepository.findById(savedParticipant.getId());

	        // Assert
	        assertThat(retrievedParticipant).isPresent();
	        assertThat(retrievedParticipant.get().getName()).isEqualTo("eniya");
	        assertEquals("eniya", retrievedParticipant.get().getName());
	    }

	    @Test
	    @DisplayName("Test deleting a Participant")
	    public void testDeleteParticipant() {
	        // Arrange
	    	Participant participant = new Participant();
	    	participant.setName("eniya");;
	    	Participant savedParticipant = participantRepository.save(participant);

	        // Act
	    	participantRepository.deleteById(savedParticipant.getId());
	        Optional<Participant> deletedParticipant = participantRepository.findById(savedParticipant.getId());

	        // Assert
	        assertThat(deletedParticipant).isNotPresent();
	    }
	}
