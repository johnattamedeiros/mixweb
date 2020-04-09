package com.mixapp.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mixapp.app.domain.Match;
import com.mixapp.app.security.AuthoritiesConstants;
import com.mixapp.app.service.MatchService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class MatchResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    
    private final MatchService matchService;

    public MatchResource(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/matches")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Match> createMatch(@Valid @RequestBody Match match) throws URISyntaxException {

            Match newMatch = matchService.createMatch(match);
            return ResponseEntity.created(new URI("/api/matches/" + match.getId()))
                .body(newMatch);
    }
    
    @GetMapping("/matches/{id}")
    public ResponseEntity<Match> getExample(@PathVariable Long id) {
        Optional<Match> match = matchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(match);
    }
    
    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllMatches(Pageable pageable) {
        final Page<Match> page = matchService.getAllMatches(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/matches/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatchById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName,  "A match is deleted with identifier " + id.toString(), id.toString())).build();
    }
}
