package io.samotek.ipldashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private long totalMatchesPlayed;
    private long totalWins;

    public Team(String name, long totolMatchesPlayed) {
        this.teamName = name;
        this.totalMatchesPlayed = totolMatchesPlayed;

    }
}
