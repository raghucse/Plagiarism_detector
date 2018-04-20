package edu.neu.reports;

public class ReportIdsRes {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    String runName;

    @Override
    public String toString() {
        return "id: "+id+" Run Name: "+runName;
    }
}
