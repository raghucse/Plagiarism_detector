package edu.neu.statistics;

/**
 * Java server deatils
 */
public class ServerDetails {

    private long maxMemory;
    private long allocatedMemory;
    private long freeMemory;
    private long availableProcessors;
    private Runtime runtime;


    ServerDetails(){
        runtime = Runtime.getRuntime();
        maxMemory = runtime.maxMemory();
        allocatedMemory = runtime.totalMemory();
        freeMemory = runtime.freeMemory();
        availableProcessors =  runtime.availableProcessors();
    }

    /**
     *
     * @return Maximum memory to be used by the system
     */
    public long getMaxMemory() {
        return maxMemory;
    }

    /**
     *
     * @param maxMemory Maximum memory to be used by the system
     */
    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    /**
     *
     * @return currently allocated memory to the system
     */
    public long getAllocatedMemory() {
        return allocatedMemory;
    }

    /**
     *
     * @param allocatedMemory currently allocated memory to the system
     */
    public void setAllocatedMemory(long allocatedMemory) {
        this.allocatedMemory = allocatedMemory;
    }

    /**
     *
     * @return Free memory out of allocated memory
     */
    public long getFreeMemory() {
        return freeMemory;
    }

    /**
     *
     * @param freeMemory Free memory out of allocated memory
     */
    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    /**
     *
     * @return processors available for the server
     */
    public long getAvailableProcessors() {
        return availableProcessors;
    }

    /**
     *
     * @param availableProcessors processors available for the server
     */
    public void setAvailableProcessors(long availableProcessors) {
        this.availableProcessors = availableProcessors;
    }


}
