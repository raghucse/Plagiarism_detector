package edu.neu;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);
    
    private Log() {
    		// empty private constructor to hide the implicit private one
    }

    public static void info(String message){
        logger.info(message);
    }

    public static void error(String message){
        logger.error(message);
    }
    
    public static void trace(String message){
        logger.trace(message);
    }
}
