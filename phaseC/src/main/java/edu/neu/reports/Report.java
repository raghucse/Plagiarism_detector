package edu.neu.reports;

import javax.persistence.*;

import edu.neu.models.ReportContent;

@Entity
@Table(name="report")
public class Report {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private int owner;
    private double reportScore;
    private ReportContent reportContent;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double getReportScore() {
        return reportScore;
    }

    public void setReportScore(double reportScore) {
        this.reportScore = reportScore;
    }
    
    public void setReportFile(ReportContent reportContent) {
	    	this.reportContent = reportContent;
    }

    public ReportContent getReportFile() {
        return reportContent;
    }

}