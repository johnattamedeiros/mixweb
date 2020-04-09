package com.mixapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mixapp.app.domain.Match;
import com.mixapp.app.domain.MatchResult;

/**
 * Spring Data JPA repository for the {@link Match} entity.
 */
@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {

}
