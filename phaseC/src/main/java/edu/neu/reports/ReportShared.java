package edu.neu.reports;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="reportshared")
public class ReportShared {
    private int reportId;
    private int user;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}
