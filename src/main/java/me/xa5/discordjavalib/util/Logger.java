package me.xa5.discordjavalib.util;

import java.io.PrintStream;

public class Logger {
    private static final LogLevel LOG_LEVEL = LogLevel.TRACE; //TODO not use trace in prod.
    private String name;

    private Logger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Logger create(String name) {
        return new Logger(name);
    }

    public void log(LogLevel level, Object message) {
        if (level.getLevel() < LOG_LEVEL.getLevel()) return;

        PrintStream out = System.out;
        if (level.getLevel() > LogLevel.WARN.level) out = System.err;

        out.println(String.format("[%s > %s]: %s", name, level, message));
    }

    public void trace(Object message) {
        log(LogLevel.TRACE, message);
    }

    public void debug(Object message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(Object message) {
        log(LogLevel.INFO, message);
    }

    public void warn(Object message) {
        log(LogLevel.WARN, message);
    }

    public void error(Object message) {
        log(LogLevel.ERROR, message);
    }

    public enum LogLevel {
        TRACE(0), DEBUG(1), INFO(2), WARN(3), ERROR(4);

        private int level;

        LogLevel(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }
}