package com.mixapp.app.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mixapp.app.domain.Match;
import com.mixapp.app.domain.MatchResult;
import com.mixapp.app.domain.User;
import com.mixapp.app.repository.MatchRepository;
import com.mixapp.app.repository.MatchResultRepository;
import com.mixapp.app.repository.UserRepository;
import com.mixapp.app.service.dto.MatchResultDTO;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class MatchResultService {

    private final MatchResultRepository matchResultRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    
    public MatchResultService(MatchResultRepository matchResultRepository, MatchRepository matchRepository, UserRepository userRepository) {
        this.matchResultRepository = matchResultRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }
    
    @Transactional
    public MatchResult createMatchResult(@Valid MatchResultDTO matchResultDTO) {
        Optional<User> user =  userRepository.findById(matchResultDTO.getIdUser());
        Optional<Match> match  = matchRepository.findById(matchResultDTO.getIdMatch());
        MatchResult matchResult = new MatchResult();
        matchResult.setUser(user.get());
        matchResult.setMatch(match.get());
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
    
    @Transactional
    public void deleteMatchResultById(Long id) {
        matchResultRepository.deleteById(id);
    }

}
