package edu.neu.reports;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import edu.neu.models.GitSubmission;
import edu.neu.models.Submission;

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
    
    public List<Submission> getStudentSubmissions() {
    		List<Submission> submissions = new ArrayList<>();
    		for(String gitUrl : this.getGitUrls().split(",")) {
    			submissions.add(new GitSubmission(gitUrl));
    		}
    		return submissions;
    }

}
