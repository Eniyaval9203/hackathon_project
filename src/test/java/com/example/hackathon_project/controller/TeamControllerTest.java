package com.example.hackathon_project.controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.hackathon_project.entity.Team;
import com.example.hackathon_project.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeamController.class)

class TeamControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveTeam() throws Exception {
        //A
    	Team team = new Team();
        team.setId(1L);
        team.setTeamName("wonders");;

        //Make fake call - part of A 
        Mockito.when(teamService.saveTeam(any(Team.class))).thenReturn(team);

        //Action & Assertion
        mockMvc.perform(post("/team")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(team.getId()))
                .andExpect(jsonPath("$.teamName").value(team.getTeamName()));
}
    @Test
    public void testGetAllTeams() throws Exception {
        Team team = new Team();
        team.setId(1L);
        team.setTeamName("wonders");

        Mockito.when(teamService.getAllTeam()).thenReturn(Collections.singletonList(team));

        mockMvc.perform(get("/team"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(team.getId()))
                .andExpect(jsonPath("$[0].teamName").value(team.getTeamName()));
    }
//    @Test
//    public void testDeleteTeam() throws Exception {
//        Long teamId = 1L;
//        doNothing().when(teamService).deleteTeam(teamId);
//
//        mockMvc.perform(post("/team/{id}", teamId))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Deleted Team with id :" + teamId));
//    }
}
