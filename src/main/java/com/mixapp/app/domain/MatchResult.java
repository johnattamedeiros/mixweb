package com.mixapp.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A user.
 */
@Entity
@Table(name = "match_results")
public class MatchResult extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Match match;
    
    @NotNull
    @Size(max = 254)
    @Column(name = "team", length = 254)
    private String team;
    
    @NotNull
    @Column(name = "kill")
    private Integer kill;
    
    @NotNull
    @Column(name = "death")
    private Integer death;
    
    @NotNull
    @Column(name = "assist")
    private Integer assist;
    
    @NotNull
    @Column(name = "damage")
    private Double damage;
    
    @NotNull
    @Column(name = "roundsWin")
    private Integer roundsWin;
    
    @NotNull
    @Column(name = "roundsLoss")
    private Integer roundsLoss;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MatchResult)) {
            return false;
        }
        return id != null && id.equals(((MatchResult) o).id);
    }

    @Override
    public String toString() {
        return "MatchResult [id=" + id + ", user=" + user + ", match=" + match + ", team=" + team + ", kill=" + kill
                + ", death=" + death + ", assist=" + assist + ", damage=" + damage + ", roundsWin=" + roundsWin
                + ", roundsLoss=" + roundsLoss + "]";
    }
   
}
