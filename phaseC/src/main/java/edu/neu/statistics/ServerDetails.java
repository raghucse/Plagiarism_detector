package edu.neu.statistics;

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

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public long getAllocatedMemory() {
        return allocatedMemory;
    }

    public void setAllocatedMemory(long allocatedMemory) {
        this.allocatedMemory = allocatedMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(long availableProcessors) {
        this.availableProcessors = availableProcessors;
    }


}
