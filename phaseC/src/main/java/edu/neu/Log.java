package edu.neu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Logger used for the
 */
public class Log {
    /**
     * instance of a logger
     */
    private static final Logger logger = LogManager.getLogger(Log.class.getName());

    /**
     * log info
     * @param message message to be logged
     */
    public  static void info(String message){
        logger.info(message);
    }

    /**
     * log error
     * @param message message to be logged
     */
    public  static void error(String message){
        logger.error(message);
    }

    /**
     * log trace
     * @param message message to be logged
     */
    public static void trace(String message){
        logger.trace(message);
    }
}
