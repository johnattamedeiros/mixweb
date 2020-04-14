package com.mixapp.app.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A match.
 */
@Entity
@Table(name = "match")
public class Match extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 254)
    @Column(name = "name", length = 254)
    private String name;

    @NotNull
    @Size(max = 254)
    @Column(name = "map", length = 254)
    private String map;
    
    
    @Column(name = "type", length = 254)
    private String type;

    @Column(name = "matchDate")
    private Instant matchDate;
    
    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    @OrderBy("team DESC")
    private List<MatchResult> matchResults;
    
    public Match() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    

    public List<MatchResult> getMatchResults() {
        return matchResults;
    }

    public void setMatchResults(List<MatchResult> matchResults) {
        this.matchResults = matchResults;
    }

    public Instant getMatchDate() {
        if (matchDate == null) {
            return Instant.now();
        }
        return matchDate;
    }

    public void setMatchDate(Instant matchDate) {
        if (matchDate == null) {
            matchDate = Instant.now();
        }
        this.matchDate = matchDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Match)) {
            return false;
        }
        return id != null && id.equals(((Match) o).id);
    }

    @Override
    public String toString() {
        return "Match [id=" + id + ", name=" + name + ", map=" + map + ", type=" + type + ", matchDate=" + matchDate
                + ", matchResults=" + matchResults + "]";
    }

}
