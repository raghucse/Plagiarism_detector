package edu.neu.statistics;

/**
 * System statistics response
 */
public class StatsRes {
    private OsDetails osDetails;
    private AppInfo appInfo;
    private ServerDetails serverDetails;

    StatsRes(){
        osDetails = new OsDetails();
        serverDetails = new ServerDetails();
    }

    /**
     *
     * @return operating system details
     */
    public OsDetails getOsDetails() {
        return osDetails;
    }

    /**
     *
     * @param osDetails operating system details
     */
    public void setOsDetails(OsDetails osDetails) {
        this.osDetails = osDetails;
    }

    /**
     *
     * @return Application information
     */
    public AppInfo getAppInfo() {
        return appInfo;
    }

    /**
     *
     * @param appInfo Application information
     */
    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    /**
     *
     * @return java server details
     */
    public ServerDetails getServerDetails() {
        return serverDetails;
    }

    /**
     *
     * @param serverDetails java server details
     */
    public void setServerDetails(ServerDetails serverDetails) {
        this.serverDetails = serverDetails;
    }


}
