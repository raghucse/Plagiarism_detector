package edu.neu.reports;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PlagarismRun")
public class PlagarismRun {

    private int runId;
    private int userId;
    String gitUrl;
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

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
