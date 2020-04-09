package com.mixapp.app.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.domain.Match;
import com.mixapp.app.domain.MatchResult;
import com.mixapp.app.repository.MatchRepository;
import com.mixapp.app.repository.MatchResultRepository;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class MatchResultService {

    private final MatchResultRepository matchResultRepository;

    public MatchResultService(MatchResultRepository matchResultRepository) {
        this.matchResultRepository = matchResultRepository;
    }

    public MatchResult createMatchResult(@Valid MatchResult matchResult) {
        matchResultRepository.save(matchResult);
        return matchResult;
    }

    @Transactional(readOnly = true)
    public Page<MatchResult> getAllMatchesResult(Pageable pageable) {
        return matchResultRepository.findAll(pageable);
    }

    public void deleteMatchResultById(Long id) {
        matchResultRepository.deleteById(id);
    }
}
