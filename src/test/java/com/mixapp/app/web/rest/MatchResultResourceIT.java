package com.mixapp.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.mixapp.app.domain.MatchResult;
import com.mixapp.app.repository.MatchResultRepository;
import com.mixapp.app.security.AuthoritiesConstants;
import com.mixapp.app.service.dto.MatchResultDTO;

/**
 * Integration tests for the {@link MatchResource} REST controller.
 */
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@SpringBootTest(classes = MixwebApp.class)
public class MatchResultResourceIT {


    @Autowired
    private MockMvc restUserMockMvc;
    
    @Autowired
    private MatchResultRepository matchResultRepository;


    @Test
    @Transactional
    public void createMatchResult() throws Exception {
        MatchResultDTO matchResultDto = new MatchResultDTO();
        
        matchResultDto.setIdMatch(1L);
        matchResultDto.setIdUser(4L);
        matchResultDto.setKill(10);
        matchResultDto.setDeath(9);
        matchResultDto.setAssist(2);
        matchResultDto.setDamage(1200.23);
        matchResultDto.setRoundsWin(16);
        matchResultDto.setRoundsLoss(14);
        matchResultDto.setTeam("CT");
        
        restUserMockMvc.perform(post("/api/matchResults")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(matchResultDto))
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
            .andExpect(jsonPath("$.[*].id").exists());
    }
    
    @Test
    @Transactional
    public void deleteMatchResult() throws Exception {
        restUserMockMvc.perform(delete("/api/matchResults/7")
            .accept(MediaType.APPLICATION_JSON)
            .with(csrf()))
            .andExpect(status().isNoContent());

        List<MatchResult> matches = matchResultRepository.findAll();
        MatchResult matchResult = matches
                .stream()
                .filter(matchFind -> matchFind.getId().equals(7L))
                .findFirst()
                .orElse(null);
        
        assertThat(matchResult).isNull();
    }

   
}
