package edu.neu.reports;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import edu.neu.models.GitSubmission;
import edu.neu.models.Submission;

@Entity
@Table(name="PlagiarismRun")
public class PlagiarismRun {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int runId;
    private int userId;
    List<String> gitUrls;
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

    public List<String> getGitUrls() {
        return gitUrls;
    }

    public void setGitUrls(List<String> gitUrls) {
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
    		for(String gitUrl : this.getGitUrls()) {
    			submissions.add(new GitSubmission(gitUrl));
    		}
    		return submissions;
    }
    
    @Override
    public String toString() {
    		return "PlagiarismRun : ["+"RunID:"+runId+","+"UserID:"+userId+","+"GitURLS:"+gitUrls+"]";
    }

}
