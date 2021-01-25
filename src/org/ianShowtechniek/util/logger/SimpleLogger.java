package org.ianShowtechniek.util.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogger extends Logger {

    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss.SSS");

    public SimpleLogger(String name) {
        super(name);
        setLogLevel(LogLevel.ALL);
    }

    public SimpleLogger(String name, LogLevel info) {
        super(name);
        setLogLevel(info);
    }

    @Override
    public void log(LogLevel level, String msg) {
        if (getLogLevel().getLevel() <= level.getLevel()) {
            if (LogLevel.WARNING.getLevel() <= level.getLevel()) {
                System.err.println(level.getCombinedColor() + getName() + " " + time.format(new Date()) + " : " + level.getName() + "    ->    " + msg);
            } else {
                System.out.println(level.getCombinedColor() + getName() + " " + time.format(new Date()) + " : " + level.getName() + "    ->    " + msg + ANSI_RESET);
            }
        }
    }

    @Override
    public void fatal(String fatal) {
        log(LogLevel.FATAL, fatal);
    }

    @Override
    public void error(String error) {
        log(LogLevel.ERROR, error);
    }

    @Override
    public void warning(String warning) {
        log(LogLevel.WARNING, warning);
    }

    @Override
    public void info(String info) {
        log(LogLevel.INFO, info);
    }

    @Override
    public void debug(String debug) {
        log(LogLevel.DEBUG, debug);
    }

    @Override
    public void trace(String trace) {
        log(LogLevel.TRACE, trace);
    }
}
