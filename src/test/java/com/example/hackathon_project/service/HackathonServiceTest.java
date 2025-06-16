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

import com.example.hackathon_project.entity.Hackathon;
import com.example.hackathon_project.repository.HackathonRepository;
@ExtendWith(MockitoExtension.class)
public class HackathonServiceTest {

	@Mock
    private HackathonRepository hackathonRepository;

    @InjectMocks
    private HackathonService hackathonService;

    private Hackathon hackathon1;
    private Hackathon hackathon2;

    @BeforeEach
    public void setUp() {
        hackathon1 = new Hackathon();
        hackathon1.setId(1L);
        hackathon1.setName("hackathon1");
        hackathon1.setDate("2020/05/15");
        
        hackathon2 = new Hackathon();
        hackathon2.setId(2L);
        hackathon2.setName("hackathon2");
        hackathon2.setDate("2021/07/12");
    }
    
    @Test
    public void testSaveHackathon() {
    	
    	//Assign - Given
        when(hackathonRepository.save(hackathon1)).thenReturn(hackathon1);

        //Action -
        Hackathon savedHackathon = hackathonService.saveHackathon(hackathon1);
        

        assertThat(savedHackathon).isNotNull();
        assertThat(savedHackathon.getName()).isEqualTo("hackathon1");
        verify(hackathonRepository, times(1)).save(hackathon1);
    }

    @Test
    public void testGetHackathon() {
        when(hackathonRepository.findById(1L)).thenReturn(Optional.of(hackathon1));

        Hackathon foundHackathon = hackathonService.getHackathon(1L);

        assertThat(foundHackathon).isNotNull();
        assertThat(foundHackathon.getName()).isEqualTo("hackathon1");
        verify(hackathonRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllHackathons() {
        when(hackathonRepository.findAll()).thenReturn(Arrays.asList(hackathon1, hackathon2));

        List<Hackathon> hackathons = hackathonService.getAllHackathon();

        assertThat(hackathons).hasSize(2);
        verify(hackathonRepository, times(1)).findAll();
    }

//    @Test
//    public void testRemoveHackathon() {
//    	doNothing().when(hackathonRepository).deleteById(1L);
//
//        hackathonService.deleteHackathon(1L);
//
//        verify(hackathonRepository, times(1)).deleteById(1L);
//    }
}
