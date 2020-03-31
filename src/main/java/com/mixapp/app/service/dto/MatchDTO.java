package com.mixapp.app.service.dto;

import java.time.Instant;

import javax.validation.constraints.Size;

public class MatchDTO {

    @Size(max = 254)
    private String name;
    
    @Size(max = 254)
    private String map;
    
    private Instant matchDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public Instant getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Instant matchDate) {
        this.matchDate = matchDate;
    }
    
}
