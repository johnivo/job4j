package ru.job4j.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author John Ivanov (johnivo@mail.ru)
 * @since 18.06.2019
 */
public class ExJob {

    /**
     * Log.
     */
    private static final Logger LOG = LogManager.getLogger(ExJob.class.getName());

    /**
     * Main.
     * @param args main.
     */
    public static void main(String[] args) {
        int version = 1;
        LOG.trace("trace message {}", version);
        LOG.debug("trace message {}", version);
        LOG.info("trace message {}", version);
        LOG.warn("trace message {}", version);
        LOG.error("trace message {}", version);
    }
}
