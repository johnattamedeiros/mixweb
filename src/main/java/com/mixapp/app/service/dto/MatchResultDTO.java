package com.mixapp.app.service.dto;

import javax.validation.constraints.NotNull;

public class MatchResultDTO {

    @NotNull
    private Long idUser;
    
    @NotNull
    private Long idMatch;
    
    @NotNull
    private String team;
    
    @NotNull
    private Integer kill;
    
    @NotNull
    private Integer death;
    
    @NotNull
    private Integer assist;
    
    @NotNull
    private Double damage;
    
    @NotNull
    private Integer roundsWin;
    
    @NotNull
    private Integer roundsLoss;

    public MatchResultDTO() {
        
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(Long idMatch) {
        this.idMatch = idMatch;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getKill() {
        return kill;
    }

    public void setKill(Integer kill) {
        this.kill = kill;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public Integer getRoundsWin() {
        return roundsWin;
    }

    public void setRoundsWin(Integer roundsWin) {
        this.roundsWin = roundsWin;
    }

    public Integer getRoundsLoss() {
        return roundsLoss;
    }

    public void setRoundsLoss(Integer roundsLoss) {
        this.roundsLoss = roundsLoss;
    }
}
