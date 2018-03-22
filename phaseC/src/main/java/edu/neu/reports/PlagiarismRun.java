package edu.neu.reports;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PlagarismRun")
public class PlagiarismRun {

    private int runId;
    private int userId;
    String gitUrls;
    String description;

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGitUrls() {
        return gitUrls;
    }

    public void setGitUrls(String gitUrls) {
        this.gitUrls = gitUrls;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
