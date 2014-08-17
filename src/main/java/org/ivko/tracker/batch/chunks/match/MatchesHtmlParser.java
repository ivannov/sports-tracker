package org.ivko.tracker.batch.chunks.match;

import java.io.File;
import java.util.List;

import org.joda.time.DateTime;

public class MatchesHtmlParser {

    private File matchesHtml;

    public MatchesHtmlParser(File matchesHtml) {
        this.matchesHtml = matchesHtml;
    }

    public List<BatchMatch> matchesAsOf(DateTime lastMatchDate) {
        // TODO parse all the matches from the file with date equal or later of the given
        return null;
    }
}
