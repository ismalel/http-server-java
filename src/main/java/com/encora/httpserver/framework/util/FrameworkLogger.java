package com.encora.httpserver.framework.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FrameworkLogger {

    static Logger LOG = Logger.getLogger("com.encora.httpserver.framework.util.FrameworLogger");

    public static void info(String log) {
        LOG.info(log);
    }
    public static void error(String log) {
        LOG.severe(log);
    }

    public static void warning(String log) {
        LOG.warning(log);
    }
}
