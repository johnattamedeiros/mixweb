package com.mixapp.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.InstantAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.MixwebApp;
import com.mixapp.app.domain.Authority;
import com.mixapp.app.domain.Match;
import com.mixapp.app.domain.User;
import com.mixapp.app.repository.UserRepository;
import com.mixapp.app.security.AuthoritiesConstants;
import com.mixapp.app.service.dto.UserDTO;
import com.mixapp.app.service.mapper.UserMapper;
import com.mixapp.app.web.rest.vm.ManagedUserVM;

/**
 * Integration tests for the {@link MatchResource} REST controller.
 */
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@SpringBootTest(classes = MixwebApp.class)
public class MatchResourceIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MockMvc restUserMockMvc;


    @Test
    @Transactional
    public void createMatch() throws Exception {
        Match match = new Match();
        match.setMap("dust_2");
        match.setMatchDate(Instant.now());
        match.setName("Mix da rapaziada");
        
        restUserMockMvc.perform(post("/api/match")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(match))
            .with(csrf()))
            .andExpect(status().isCreated());

    }

   
}
