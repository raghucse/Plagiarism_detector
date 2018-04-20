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

    
    /**
     * @return Returns the run id
     */
    public String getRunID() {
		return runID;
	}

    /**
     * Sets the run id
     * @param runID : the run id
     */
	public void setRunID(String runID) {
		this.runID = runID;
	}

	/**
	 * Returns the created user id
	 * @return : the created user id
	 */
	public String getCreatedUserID() {
		return createdUserID;
	}

	/**
	 * Sets the created user id
	 * @param createdUserID : The created user id
	 */
	public void setCreatedUserID(String createdUserID) {
		this.createdUserID = createdUserID;
	}

	/**
	 * Returns the strategy weights 
	 * @return : The strategy weights
	 */
	public List<Double> getStrategiesWeight() {
		return strategiesWeight;
	}

	/**
	 * Sets the weights of the strategies
	 * @param strategiesWeight : the strategies weights
	 */
	public void setStrategiesWeight(List<Double> strategiesWeight) {
		this.strategiesWeight = strategiesWeight;
	}

	/**
	 * Returns the strategies names
	 * @return : The strategies names
	 */
	public List<String> getStrategiesNames() {
		return strategiesNames;
	}

	/**
	 * Sets the strategies names
	 * @param strategiesNames : the strategies names
	 */
	public void setStrategiesNames(List<String> strategiesNames) {
		this.strategiesNames = strategiesNames;
	}

	/**
	 * Returns the student names
	 * @return : the student names
	 */
	public List<String> getStudentNames() {
		return studentNames;
	}

	/**
	 * Sets the student names
	 * @param studentNames : the student names
	 */
	public void setStudentNames(List<String> studentNames) {
		this.studentNames = studentNames;
	}

	private String runID;
    private String createdUserID;
    private String description;

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

	private String runName;
    private List<String> sharedUsers = new ArrayList<>();
    private List<Double> strategiesWeight = new ArrayList<>();
    private List<String> strategiesNames = new ArrayList<>();
    private List<String> gitUrls = new ArrayList<>();
    private List<String> studentNames = new ArrayList<>();
    
}
