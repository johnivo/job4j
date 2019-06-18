package ru.job4j.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 18.06.2019
 */
public class UsageLog4j2 {

    /**
     * Log.
     */
    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    /**
     * Main.
     * @param args main.
     */
    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }

}
