package ru.costonied.examples.logging;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public class LogToConsoleExample {
    public static void main(String[] args) {

        Logger logger = Logger.getLogger("my.logger");
        logger.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
        logger.info("It's alive!");

    }
}
