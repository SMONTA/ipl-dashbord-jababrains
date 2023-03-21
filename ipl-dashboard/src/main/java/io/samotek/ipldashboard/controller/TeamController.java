package io.samotek.ipldashboard.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.samotek.ipldashboard.model.Team;
import io.samotek.ipldashboard.repository.MatchRepository;
import io.samotek.ipldashboard.repository.TeamRepository;

@RestController
@RequestMapping("/team/v1/")
public class TeamController {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("{teamName}")
    public Team getTeamInfo(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new RuntimeException("No team name was found " + teamName));
        var pageable = PageRequest.of(0, 4);
        var matches = matchRepository.findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
        team.setListOfMatchs(matches);
        return team;
    }

}
