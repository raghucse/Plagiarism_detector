package edu.neu.statistics;

public class StatsRes {
    private OsDetails osDetails;
    private AppInfo appInfo;
    private ServerDetails serverDetails;

    StatsRes(){
        osDetails = new OsDetails();
        serverDetails = new ServerDetails();
    }


    public OsDetails getOsDetails() {
        return osDetails;
    }

    public void setOsDetails(OsDetails osDetails) {
        this.osDetails = osDetails;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public ServerDetails getServerDetails() {
        return serverDetails;
    }

    public void setServerDetails(ServerDetails serverDetails) {
        this.serverDetails = serverDetails;
    }


}
