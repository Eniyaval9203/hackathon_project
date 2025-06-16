package com.example.hackathon_project.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hackathon_project.entity.Hackathon;
import com.example.hackathon_project.service.HackathonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

@WebMvcTest(HackathonController.class)

class HackathonControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private HackathonService hackathonService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveBooking() throws Exception {
        //A
    	Hackathon hackathon = new Hackathon();
        hackathon.setId(1L);;
        hackathon.setName("hackathon1");;

        //Make fake call - part of A 
        Mockito.when(hackathonService.saveHackathon(any(Hackathon.class))).thenReturn(hackathon);

        //Action & Assertion
        mockMvc.perform(post("/hackathon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hackathon)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(hackathon.getId()))
                .andExpect(jsonPath("$.name").value(hackathon.getName()));
}
    @Test
    public void testGetAllHackathons() throws Exception {
        Hackathon hackathon = new Hackathon();
        hackathon.setId(1L);
        hackathon.setName("hackathon1");

        Mockito.when(hackathonService.getAllHackathon()).thenReturn(Collections.singletonList(hackathon));

        mockMvc.perform(get("/hackathon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(hackathon.getId()))
                .andExpect(jsonPath("$[0].name").value(hackathon.getName()));
    }
//    @Test
//    public void testDeleteHackathon() throws Exception {
//        Long hackathonId = 1L;
//        doNothing().when(hackathonService).deleteHackathon(hackathonId);
//
//        mockMvc.perform(post("/hackathon/{id}", hackathonId))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Deleted Hackathon with id :" + hackathonId));
//    }
}
