package io.samotek.ipldashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Team(String name, long totolMatchesPlayed) {
        this.teamName = name;
        this.totalMatches = totolMatchesPlayed;

    }
}
