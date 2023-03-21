package io.samotek.ipldashboard.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.samotek.ipldashboard.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Optional<Team> findByTeamName(String teamName);
}
