package org.ivko.tracker.batch.chunks.match;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.ivko.tracker.model.Match;
import org.ivko.tracker.model.Team;
import org.ivko.tracker.services.TeamService;

@Dependent
@Named
public class MatchesProcessor implements ItemProcessor {

    @Inject
    TeamService teamService;
    
    @Override
    public Object processItem(Object item) throws Exception {
        BatchMatch batchMatch = (BatchMatch) item;
        Match match = new Match();
        match.setTeam1(buildTeam(batchMatch.getHomeTeam()));
        match.setTeam2(buildTeam(batchMatch.getAwayTeam()));
        match.setTeam1Result(batchMatch.getHomeTeamResult());
        match.setTeam2Result(batchMatch.getAwayTeamResult());
        match.setJodaMatchTime(batchMatch.getMatchDate());
        return match;
    }

    private Team buildTeam(String homeTeam) {
        return (Team)teamService.findById(getIdOfSoccerWayTeam(homeTeam)).getEntity();
    }

    private Long getIdOfSoccerWayTeam(String homeTeam) {
        return null;
    }

}
