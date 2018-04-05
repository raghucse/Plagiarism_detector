package edu.neu.statistics;

public class OsDetails {
    private String osName;
    private String osVersion;
    private String osArch;

    private String nameOS = "os.name";
    private String versionOS = "os.version";
    private String architectureOS = "os.arch";

    OsDetails(){
        osName = System.getProperty(nameOS);
        osVersion = System.getProperty(versionOS);
        osArch = System.getProperty(architectureOS);
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }
}
