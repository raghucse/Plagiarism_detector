package edu.neu.statistics;

/**
 * Provides operating system details
 */
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

    /**
     *
     * @return operating system on which application is running
     */
    public String getOsName() {
        return osName;
    }

    /**
     *
     * @param osName operating system on which application is running
     */
    public void setOsName(String osName) {
        this.osName = osName;
    }

    /**
     *
     * @return operating system version
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     *
     * @param osVersion operating system version
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    /**
     *
     * @return Operating system architecture
     */
    public String getOsArch() {
        return osArch;
    }

    /**
     *
     * @param osArch Operating system architecture
     */
    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }
}
