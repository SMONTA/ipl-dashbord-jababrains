package io.samotek.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.samotek.ipldashboard.model.Match;
import io.samotek.ipldashboard.model.Team;
import io.samotek.ipldashboard.repository.MatchRepository;
import io.samotek.ipldashboard.repository.TeamRepository;

@RestController
@RequestMapping("/team/v1/")
@CrossOrigin
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
        // var pageable = PageRequest.of(0, 4);
        var matches = matchRepository.findLatestMatchesByTeam(teamName, 4);
        team.setListOfMatchs(matches);
        return team;
    }

    @GetMapping("{teamName}/matches")
    public List<Match> getMatchsForTeam(@PathVariable String teamName, @RequestParam int year) {
        var startDate = LocalDate.of(year, 1, 1);
        var endDate = LocalDate.of(year + 1, 1, 1);

        return matchRepository.findMatchesByTeamBetweenDates(teamName, startDate, endDate);
        // return
        // matchRepository.findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
        // teamName,
        // startDate,
        // endDate,
        // teamName,
        // startDate,
        // endDate);

    }

}
