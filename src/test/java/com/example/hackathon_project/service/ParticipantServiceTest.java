package com.example.hackathon_project.service;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.example.hackathon_project.repository.ParticipantRepository;

@ExtendWith(MockitoExtension.class)
public class ParticipantServiceTest {

	@Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private ParticipantService participantService;

    private Participant participant1;
    private Participant participant2;

    @BeforeEach
    public void setUp() {
    	participant1 = new Participant();
    	participant1.setId(1L);
    	participant1.setName("eniya");
    	participant1.setEmail("eniya@gmail.com");
        
    	participant2 = new Participant();
    	participant2.setId(2L);
    	participant2.setName("sweety");
    	participant2.setEmail("sweety@gmail.com");
    }
    
    @Test
    public void testSaveParticipant() {
    	
    	//Assign - Given
        when(participantRepository.save(participant1)).thenReturn(participant1);

        //Action -
        Participant savedParticipant = participantService.saveParticipant(participant1);
        

        assertThat(savedParticipant).isNotNull();
        assertThat(savedParticipant.getName()).isEqualTo("eniya");
        verify(participantRepository, times(1)).save(participant1);
    }

    @Test
    public void testGetParticipant() {
        when(participantRepository.findById(1L)).thenReturn(Optional.of(participant1));

        Participant foundParticipant = participantService.getParticipant(1L);

        assertThat(foundParticipant).isNotNull();
        assertThat(foundParticipant.getName()).isEqualTo("eniya");
        verify(participantRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllParticipants() {
        when(participantRepository.findAll()).thenReturn(Arrays.asList(participant1, participant2));

        List<Participant> participants = participantService.getAllParticipant();

        assertThat(participants).hasSize(2);
        verify(participantRepository, times(1)).findAll();
    }

//    @Test
//    public void testRemoveParticipant() {
//    	doNothing().when(participantRepository).deleteById(1L);
// 
//
//        participantService.deleteParticipant(1L);
//
//        verify(participantRepository, times(1)).deleteById(1L);
//    }
}
