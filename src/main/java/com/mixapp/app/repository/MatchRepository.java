package com.mixapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mixapp.app.domain.Match;

/**
 * Spring Data JPA repository for the {@link Match} entity.
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
