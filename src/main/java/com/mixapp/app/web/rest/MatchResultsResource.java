package com.mixapp.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

import com.mixapp.app.domain.MatchResult;
import com.mixapp.app.security.AuthoritiesConstants;
import com.mixapp.app.service.MatchResultService;
import com.mixapp.app.service.dto.MatchResultDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class MatchResultsResource {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    
    private final MatchResultService matchResultService;

    
    public MatchResultsResource(MatchResultService matchResultService) {
        this.matchResultService = matchResultService;
    }

    @PostMapping("/matchResults")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<MatchResult> createMatchResult(@Valid @RequestBody MatchResultDTO matchResultDTO) throws URISyntaxException {
         MatchResult newMatchResult = matchResultService.createMatchResult(matchResultDTO);
            return ResponseEntity.created(new URI("/api/matchResults/" + newMatchResult.getId()))
                .body(newMatchResult);
    }
    
    @GetMapping("/matchResults")
    public ResponseEntity<List<MatchResult>> getAllMatchResults(Pageable pageable) {
        final Page<MatchResult> page = matchResultService.getAllMatchesResult(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/matchResults/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteMatchResult(@PathVariable Long id) {
        matchResultService.deleteMatchResultById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName,  "A match result is deleted with identifier " + id.toString(), id.toString())).build();
    }
}
