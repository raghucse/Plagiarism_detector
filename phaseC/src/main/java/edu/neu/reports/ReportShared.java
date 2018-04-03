package edu.neu.reports;

import javax.persistence.*;

@Entity
@Table(name="reportshared")

/**
 * This class maintains the information of the reports
 * shared between various users
 */
public class ReportShared {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int reportId;
    private int userId;

    /**
     * @return Returns the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the id for the particular user
     * @param userId is the id to be assigned to the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return Returns the report id
     */
    public int getReportId() {
        return reportId;
    }

    /**
     * Sets the id for the particular report
     * @param reportId is the id to be assigned to the report
     */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}
