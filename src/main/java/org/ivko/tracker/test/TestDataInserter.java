package org.ivko.tracker.test;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.ivko.tracker.model.League;
import org.ivko.tracker.model.Match;
import org.ivko.tracker.model.Team;
import org.ivko.tracker.services.LeagueService;
import org.ivko.tracker.services.MatchService;
import org.ivko.tracker.services.TeamService;

@Singleton
@Startup
public class TestDataInserter implements Serializable {

    private static final long serialVersionUID = -3400176380104108973L;

    @EJB
    private MatchService matchService;
    
    @EJB
    private TeamService teamService;

    @EJB
    private LeagueService leagueService;
    
    @PostConstruct
    public void insertTestData() {
        League bulgaria = new League("A PFG", "Bulgaria", 2014, 2015, 1);
        
        Team cska = new Team("CSKA", "Sofia");
        Team lefski = new Team("Levski", "Sofia");
        Team crazy = new Team("Ludogorets", "Razgrad");
        
        teamService.create(cska);
        teamService.create(lefski);
        teamService.create(crazy);
        
        Set<Team> bgTeams = new HashSet<>();
        bgTeams.add(cska);
        bgTeams.add(lefski);
        bgTeams.add(crazy);
        bulgaria.setTeams(bgTeams);
        
        Match match1 = new Match(cska, lefski, 3, 0, null);
        Match match2 = new Match(cska, crazy, 1, 1, null);
        Match match3 = new Match(crazy, lefski, 1, 0, null);
        
        matchService.create(match1);
        matchService.create(match2);
        matchService.create(match3);
        
        Set<Match> bgMatches = new HashSet<>();
        bgMatches.add(match1);
        bgMatches.add(match2);
        bgMatches.add(match3);
        bulgaria.setMatches(bgMatches);
        leagueService.create(bulgaria);        
        
        League premiership = new League("Premier League", "England", 2014, 2015, 1);
        
        Team liverpool = new Team("Liverpool", "Liverpool");
        Team manUtd = new Team("Manchester United", "Manchester");
        Team chelsea = new Team("Chelsea", "London");
        
        teamService.create(liverpool);
        teamService.create(manUtd);
        teamService.create(chelsea);
        
        Set<Team> engTeams = new HashSet<>();
        engTeams.add(liverpool);
        engTeams.add(manUtd);
        engTeams.add(chelsea);
        premiership.setTeams(engTeams);
        
        Match match4 = new Match(liverpool, manUtd, 4, 1, null);
        Match match5 = new Match(manUtd, chelsea, 0, 0, null);
        Match match6 = new Match(chelsea, liverpool, 1, 1, null);
        
        matchService.create(match4);
        matchService.create(match5);
        matchService.create(match6);
    
        Set<Match> engMatches = new HashSet<>();
        engMatches.add(match4);
        engMatches.add(match5);
        engMatches.add(match6);
        premiership.setMatches(engMatches);
        leagueService.create(premiership);
    }
}