package edu.neu.reports;

import java.util.ArrayList;
import java.util.List;

public class PlagiarismRunRequest {
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getGitUrls() {
        return gitUrls;
    }

    public void setGitUrls(List<String> gitUrls) {
        this.gitUrls = gitUrls;
    }

    public List<String> getSharedUsers() {
        return sharedUsers;
    }

    public void setSharedUsers(List<String> sharedUsers) {
        this.sharedUsers = sharedUsers;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String description;
    private List<String> gitUrls = new ArrayList<>();
    private List<String> sharedUsers = new ArrayList<>();
    private int userId;
}
