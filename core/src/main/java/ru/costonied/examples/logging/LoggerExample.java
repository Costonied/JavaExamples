package ru.costonied.examples.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import java.util.logging.LogManager;

public class LoggerExample {
    private static final Logger LOGGER = Logger.getLogger(LoggerExample.class.getName());

    static {
        InputStream stream = LoggerExample.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LOGGER.info("It's alive!");
        LOGGER.info("Alive!");
    }
}
