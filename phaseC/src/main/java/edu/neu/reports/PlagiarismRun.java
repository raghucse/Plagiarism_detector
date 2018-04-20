package edu.neu.reports;

import edu.neu.models.GitSubmission;
import edu.neu.models.Submission;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PlagiarismRun")

/**
 * This class stores all the data related to a Plagiarism run
 */
public class PlagiarismRun {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int runId;
    private int userId;

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    private String runName;


    @ElementCollection
    List<String> gitUrls;
    
    @ElementCollection
    List<String> studentNames;
    
    String description;

    /**
     * @return Returns the id of the plagiarism run
     */
    public int getRunId() {
        return runId;
    }

    /**
     * Sets the id of the plagiarism run
     * @param runId determines the value to be set as the id
     *              for the plagiarism run
     */
    public void setRunId(int runId) {
        this.runId = runId;
    }

    /**
     * @return Returns the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user id
     * @param userId determines the value to be set as the
     *               user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return Returns the list of GitHub urls
     */
    public List<String> getGitUrls() {
        return gitUrls;
    }

    /**
     * Stores the GitHub urls
     * @param gitUrls determines the GitHub urls to be stored
     */
    public void setGitUrls(List<String> gitUrls) {
        this.gitUrls = gitUrls;
    }
    
    /**
     * @return Returns the list of Student names associated with this run
     */
    public List<String> getStudentNames() {
        return studentNames;
    }

    /**
     * Stores the student names
     * @param studentNames determines the student names to be stored
     */
    public void setStudentNames(List<String> studentNames) {
        this.studentNames = studentNames;
    }
    
    
    /**
     * @return Returns the run description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the run
     * @param description determines the content to be stored
     *                    as the description of the run
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns all the submissions of the students
     */
    public List<Submission> getStudentSubmissions() {
        List<Submission> submissions = new ArrayList<>();
        for(int i=0; i<this.getGitUrls().size(); i++) {
        	submissions.add(new GitSubmission(this.getStudentNames().get(i), this.getGitUrls().get(i)));
        }
        return submissions;
    }

    /**
     * Returns all the desired information in the form of a string
     * @return returns the plagiarism run id, the user id and the GitHub
     *         URLs in the form of a String
     */
    public String toString() {
        return "PlagiarismRun : ["+"RunID:"+runId+","+"UserID:"+userId+","+"GitURLS:"+gitUrls+","+"Students:"+studentNames+"]";
    }

}
