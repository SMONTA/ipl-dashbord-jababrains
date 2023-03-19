package io.samotek.ipldashboard.model;

import lombok.Data;

/**
 * This is a data model of each entry in the csv file.
 * 
 */

@Data
public class MatchInput {

    private String id;
    private String city;
    private String date;
    private String player_of_match;
    private String venue;
    private String neutral_venue;
    private String team1;
    private String team2;
    private String toss_winner;
    private String toss_decision;
    private String winner;
    private String result;
    private String result_margin;
    private String eliminator;
    private String method;
    private String umpire1;
    private String umpire2;

}
