package org.ivko.tracker.batch.chunks.match;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;

@Dependent
@Named
public class MatchesReader implements ItemReader {
    
    @Inject
    private JobContext jobContext;

    private MatchCheckpoint checkPoint;
    private List<BatchMatch> matches;
    
    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkPoint;
    }

    @Override
    public void close() throws Exception {
        // Do some cleanup if necessary
    }

    @Override
    public void open(Serializable checkPoint) throws Exception {
        if (checkPoint != null) {
            this.checkPoint = (MatchCheckpoint) checkPoint;
        } else {
            this.checkPoint = new MatchCheckpoint(DateTime.now()); // TODO inject the starting date for the checkpoint
        }
        
        MatchesHtmlParser parser = new MatchesHtmlParser(new File(jobContext.getProperties().getProperty("targetFile")));
        matches = parser.matchesAsOf(this.checkPoint.getLastMatchDate());
    }

    @Override
    public Object readItem() throws Exception {
        if (checkPoint.getMatchesRead() == matches.size()) {
            // As far as I got it, readItem returning null means no more items to process
            return null;
        }
        BatchMatch item = matches.get(checkPoint.getMatchesRead());
        checkPoint.incrementMatchesRead();
        return item;
    }

}
