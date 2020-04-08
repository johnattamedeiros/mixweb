package com.mixapp.app.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.domain.Match;
import com.mixapp.app.repository.MatchRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class MatchService {

    private final Logger log = LoggerFactory.getLogger(MatchService.class);

    private final MatchRepository matchRepository;


    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }


    public Match createMatch(@Valid Match match) {
        matchRepository.save(match);
        log.debug("Created Information for User: {}", match);
        return match;
    }


    @Transactional(readOnly = true)
    public Page<Match> getAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    public void deleteMatchById(Long id) {
        matchRepository.deleteById(id);
    }
}
