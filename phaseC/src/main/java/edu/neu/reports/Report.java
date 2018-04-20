package edu.neu.reports;

import edu.neu.models.ReportContent;

import javax.persistence.*;

@Entity
@Table(name="report")
/**
 * This class maintains the information of the Report
 */
public class Report {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    private String description;
    private String runName;
    private int owner;
    private double reportScore;
    private ReportContent reportFile;

    /**
     * @return Returns the report id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the report id to a particular value
     * @param id is the value to be used for the report
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Returns the report owner
     */
    public int getOwner() {
        return owner;
    }

    /**
     * Sets the report owner
     * @param owner is the value to be set for the owner
     */
    public void setOwner(int owner) {
        this.owner = owner;
    }


    /**
     * @return Returns the report score
     */
    public double getReportScore() {
        return reportScore;
    }

    /**
     * Sets the report score to a particular value
     * @param reportScore
     */
    public void setReportScore(double reportScore) {
        this.reportScore = reportScore;
    }

    /**
     * Sets the content of the report
     * @param reportFile determines the content to be added to the report
     */
    public void setReportFile(ReportContent reportFile) {
	    	this.reportFile = reportFile;
    }

    /**
     * @return Returns the report file
     */
    public ReportContent getReportFile() {
        return reportFile;
    }

}