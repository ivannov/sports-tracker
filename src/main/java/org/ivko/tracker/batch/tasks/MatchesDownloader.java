package org.ivko.tracker.batch.tasks;

import java.io.File;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named
public class MatchesDownloader implements Batchlet {

    @Inject
    JobContext jobContext;
    
    @Override
    public String process() throws Exception {
        String downloadUrl = jobContext.getProperties().getProperty("downloadUrl");
        File targetFile = new File(jobContext.getProperties().getProperty("targetFile"));
        // Download the HTML and save it in the target file
        return "COMPLETED";
    }

    @Override
    public void stop() throws Exception {
        // TODO Auto-generated method stub
        
    }

}
