package io.samotek.ipldashboard.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String teamName;
    private long totalMatches;
    private long totalWins;

    @Transient
    List<Match> listOfMatchs;

    public Team(String name, long totolMatchesPlayed) {
        this.teamName = name;
        this.totalMatches = totolMatchesPlayed;

    }
}
