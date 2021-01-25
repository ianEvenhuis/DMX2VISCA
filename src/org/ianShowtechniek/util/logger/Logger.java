package org.ianShowtechniek.util.logger;

public abstract class Logger {

    private LogLevel logLevel = LogLevel.ALL;

    private String name;

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Logger(String name) {
        this.name = name;
    }

    public Logger(Class name) {
        this.name = name.getSimpleName();
    }

    public abstract void log(LogLevel level, String msg);

    public abstract void fatal(String fatal);

    public abstract void error(String error);

    public abstract void warning(String warning);

    public abstract void info(String info);

    public abstract void debug(String debug);

    public abstract void trace(String trace);

    public boolean isDebug() {
        return (getLogLevel().getLevel() <= LogLevel.DEBUG.getLevel());
    }

    //<editor-fold desc="Getters and Setters">
    public void setLogLevel(LogLevel newLogLevel) {
        this.logLevel = newLogLevel;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getName() {
        return name;
    }

    public Logger setName(String name) {
        this.name = name;
        return this;
    }

    public Logger setName(Class name) {
        this.name = name.getSimpleName();
        return this;
    }

    public boolean isTraceEnabled() {
        return logLevel.getLevel() >= LogLevel.TRACE.getLevel();
    }

    ;
    //</editor-fold>
}
