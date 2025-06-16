package com.example.hackathon_project.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hackathon_project.entity.Participant;
import com.example.hackathon_project.service.ParticipantService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

@WebMvcTest(ParticipantController.class)

class ParticipantControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParticipantService participantService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveParticipant() throws Exception {
        //A
    	Participant participant = new Participant();
        participant.setId(1L);;
        participant.setName("eniya");;

        //Make fake call - part of A 
        Mockito.when(participantService.saveParticipant(any(Participant.class))).thenReturn(participant);

        //Action & Assertion
        mockMvc.perform(post("/participant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(participant)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(participant.getId()))
                .andExpect(jsonPath("$.name").value(participant.getName()));
}
    @Test
    public void testGetAllParticipants() throws Exception {
        Participant participant = new Participant();
        participant.setId(1L);
        participant.setName("eniya");

        Mockito.when(participantService.getAllParticipant()).thenReturn(Collections.singletonList(participant));

        mockMvc.perform(get("/participant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(participant.getId()))
                .andExpect(jsonPath("$[0].name").value(participant.getName()));
    }
//    @Test
//    public void testDeleteParticipant() throws Exception {
//        Long participantId = 1L;
//        doNothing().when(participantService).deleteParticipant(participantId);
//
//        mockMvc.perform(post("/participant/{id}", participantId))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Deleted Participant with id :" + participantId));
//    }
}