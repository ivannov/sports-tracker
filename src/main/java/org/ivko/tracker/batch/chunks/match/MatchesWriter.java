package org.ivko.tracker.batch.chunks.match;

import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.ivko.tracker.model.Match;
import org.ivko.tracker.services.MatchService;

@Dependent
@Named
public class MatchesWriter implements ItemWriter {

    @Inject
    MatchService matchService;
    
    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public void open(Serializable arg0) throws Exception {
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        for (Object item : items) {
            matchService.create((Match) item);
        }
    }

}
