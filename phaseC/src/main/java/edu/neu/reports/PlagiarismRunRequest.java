package edu.neu.reports;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the plagiarism run requests
 */
public class PlagiarismRunRequest {

    /**
     * @return Returns the run descriptiom
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the run description to a particular String value
     * @param description determines the description which is to be stored
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the GitHub URLs
     */
    public List<String> getGitUrls() {
        return gitUrls;
    }

    /**
     * Stores the Git URLs
     * @param gitUrls is the list of GitHub URLs to be stored
     */
    public void setGitUrls(List<String> gitUrls) {
        this.gitUrls = gitUrls;
    }

    /**
     * @return Returns the list of shared users
     */
    public List<String> getSharedUsers() {
        return sharedUsers;
    }

    /**
     * Sets the list of users which will be sharing a particular report
     * @param sharedUsers determines the users to be added to the share list
     *                    of a report
     */
    public void setSharedUsers(List<String> sharedUsers) {
        this.sharedUsers = sharedUsers;
    }

    private String description;
    private List<String> gitUrls = new ArrayList<>();
    private List<String> sharedUsers = new ArrayList<>();

}
