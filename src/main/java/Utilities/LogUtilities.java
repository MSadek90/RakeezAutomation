package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtilities {

    String LogPath = "src/test/resources/logs/";

    private static final Logger logger = LogManager.getLogger(LogUtilities.class.getName());


    public static void trace (String message) {
        logger.trace(message);
    }

    public static void debug (String message) {
        logger.debug(message);
    }

    public static void info (String message) {
        logger.info(message);
    }

    public static void warn (String message) {
        logger.warn(message);
    }

    public static void error (String message) {
        logger.error(message);
    }

    public static void fatal (String message) {
        logger.fatal(message);
    }


}
