package io.samotek.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import io.samotek.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pageable);

    @Query("select m from Match m  where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :startDate and :endDate order by date desc")

    List<Match> findMatchesByTeamBetweenDates(
            @Param("teamName") String teamName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
    // List<Match> findByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    // String team1,
    // LocalDate date1,
    // LocalDate date2,
    // String team2,
    // LocalDate date3,
    // LocalDate date4);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {

        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, Pageable.ofSize(count));
    }

}
