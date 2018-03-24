package edu.neu.reports;

import javax.persistence.*;

@Entity
@Table(name="reportshared")
public class ReportShared {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int reportId;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}
