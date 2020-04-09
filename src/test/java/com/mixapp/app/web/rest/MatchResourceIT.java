package com.mixapp.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.MixwebApp;
import com.mixapp.app.domain.Match;
import com.mixapp.app.repository.MatchRepository;
import com.mixapp.app.security.AuthoritiesConstants;

/**
 * Integration tests for the {@link MatchResource} REST controller.
 */
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@SpringBootTest(classes = MixwebApp.class)
public class MatchResourceIT {


    @Autowired
    private MockMvc restUserMockMvc;
    
    @Autowired
    private MatchRepository matchRepository;


    @Test
    @Transactional
    public void createMatch() throws Exception {
        Match match = new Match();
        match.setMap("dust_2");
        match.setMatchDate(Instant.now());
        match.setName("Mix da rapaziada");
        match.setType("MM");
        
        restUserMockMvc.perform(post("/api/matches")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(match))
            .with(csrf()))
            .andExpect(status().isCreated());

    }
    
    @Test
    @Transactional
    public void getAllMatches() throws Exception {
        restUserMockMvc.perform(get("/api/matches?sort=id,asc")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[*].map").value(hasItems("dust_2","mirage","inferno")))
            .andExpect(jsonPath("$.[*].name").value(hasItems("Mix James","Mix Jailson","Mix Pedro")));
    }
    
    @Test
    @Transactional
    public void deleteMatch() throws Exception {
        restUserMockMvc.perform(delete("/api/matches/3")
            .accept(MediaType.APPLICATION_JSON)
            .with(csrf()))
            .andExpect(status().isNoContent());

        List<Match> matches = matchRepository.findAll();
        Match match = matches
                .stream()
                .filter(matchFind -> matchFind.getId().equals(3L))
                .findFirst()
                .orElse(null);
        
        assertThat(match).isNull();
    }

   
}
