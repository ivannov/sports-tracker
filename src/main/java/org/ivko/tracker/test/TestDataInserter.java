package org.ivko.tracker.test;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.ivko.tracker.model.Match;
import org.ivko.tracker.model.Result;
import org.ivko.tracker.model.Team;
import org.ivko.tracker.services.MatchService;
import org.ivko.tracker.services.ResultService;
import org.ivko.tracker.services.TeamService;

@Singleton
@Startup
public class TestDataInserter implements Serializable {

    private static final long serialVersionUID = -3400176380104108973L;

    @EJB
    private MatchService matchService;

    @EJB
    private ResultService resultService;
    
    @EJB
    private TeamService teamService;

    @PostConstruct
    public void insertTestData() {
        Team cska = new Team("CSKA", "Sofia");
        Team lefski = new Team("Levski", "Sofia");
        Team crazy = new Team("Ludogorets", "Razgrad");
        
        teamService.create(cska);
        teamService.create(lefski);
        teamService.create(crazy);
        
        Result result1 = new Result(1, 0);
        Result result2 = new Result(1, 1);
        Result result3 = new Result(3, 0);

        resultService.create(result1);
        resultService.create(result2);
        resultService.create(result3);
        
        Match match1 = new Match(cska, lefski, result1, null);
        Match match2 = new Match(cska, crazy, result2, null);
        Match match3 = new Match(crazy, lefski, result3, null);
        
        matchService.create(match1);
        matchService.create(match2);
        matchService.create(match3);
    }
}