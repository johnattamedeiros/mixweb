package com.mixapp.app.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.domain.Match;
import com.mixapp.app.domain.MatchResult;
import com.mixapp.app.domain.User;
import com.mixapp.app.repository.MatchResultRepository;
import com.mixapp.app.service.dto.MatchResultDTO;

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

    public MatchResult createMatchResult(@Valid MatchResultDTO matchResultDTO, User user, Match match) {
        MatchResult matchResult = new MatchResult();
        matchResult.setUser(user);
        matchResult.setMatch(match);
        matchResult.setTeam(matchResultDTO.getTeam());
        matchResult.setKill(matchResultDTO.getKill());
        matchResult.setDeath(matchResultDTO.getDeath());
        matchResult.setAssist(matchResultDTO.getAssist());
        matchResult.setDamage(matchResultDTO.getDamage());
        matchResult.setRoundsWin(matchResultDTO.getRoundsWin());
        matchResult.setRoundsLoss(matchResultDTO.getRoundsLoss());
        
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
