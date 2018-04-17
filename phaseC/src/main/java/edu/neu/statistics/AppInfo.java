package edu.neu.statistics;


/**
 * Pojo for storing application information
 */
public class AppInfo {

    private long numberOfUsers;
    private long numberOfreports;

    /**
     *
     * @return total number of users in the system
     */
    public long getNumberOfUsers() {
        return numberOfUsers;
    }

    /**
     *
     * @param numberOfUsers total number of users in the system
     */
    public void setNumberOfUsers(long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    /**
     *
     * @return total number of plagiarism runs done so far
     */
    public long getNumberOfreports() {
        return numberOfreports;
    }

    /**
     *
     * @param numberOfreports total number of plagiarism runs done so far
     */
    public void setNumberOfreports(long numberOfreports) {
        this.numberOfreports = numberOfreports;
    }


}
