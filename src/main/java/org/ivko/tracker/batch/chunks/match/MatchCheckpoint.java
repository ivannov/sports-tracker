package org.ivko.tracker.batch.chunks.match;

import java.io.Serializable;

import org.joda.time.DateTime;

public class MatchCheckpoint implements Serializable {

    private static final long serialVersionUID = -2459130788332281158L;

    private DateTime lastMatchDate;
    private int matchesRead;
    
    public MatchCheckpoint(DateTime asOfDate) {
        this.lastMatchDate = asOfDate;
    }

    public void incrementMatchesRead() {
        matchesRead++;
    }

    public DateTime getLastMatchDate() {
        return lastMatchDate;
    }

    public void setLastMatchDate(DateTime lastMatchDate) {
        this.lastMatchDate = lastMatchDate;
    }

    public int getMatchesRead() {
        return matchesRead;
    }
}
