package com.mixapp.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.MixwebApp;

/**
 * Integration tests for {@link UserService}.
 */
@SpringBootTest(classes = MixwebApp.class)
@Transactional
public class SteamScrapServiceTest {
    
    @Autowired
    private SteamScrapService steamScrapService;
    
    @Test
    @Transactional
    public void getImageFromSteamUrl() throws IOException {
        String imageUrl = steamScrapService.getSteamImage("https://steamcommunity.com/id/nemdanada");
        assertThat(imageUrl).isNotNull();
    }

}
