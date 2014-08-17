package org.ivko.tracker.batch.chunks.match;

import org.joda.time.DateTime;

public class BatchMatch {

    private String homeTeam;
    private String awayTeam;
    private int homeTeamResult;
    private int awayTeamResult;
    private DateTime matchDate;
    
    public String getHomeTeam() {
        return homeTeam;
    }
    
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }
    
    public String getAwayTeam() {
        return awayTeam;
    }
    
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
    
    public int getHomeTeamResult() {
        return homeTeamResult;
    }
    
    public void setHomeTeamResult(int homeTeamResult) {
        this.homeTeamResult = homeTeamResult;
    }
    
    public int getAwayTeamResult() {
        return awayTeamResult;
    }
    
    public void setAwayTeamResult(int awayTeamResult) {
        this.awayTeamResult = awayTeamResult;
    }
    
    public DateTime getMatchDate() {
        return matchDate;
    }
    
    public void setMatchDate(DateTime matchDate) {
        this.matchDate = matchDate;
    }
}
