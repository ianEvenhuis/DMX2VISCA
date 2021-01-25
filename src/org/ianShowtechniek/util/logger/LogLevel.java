package org.ianShowtechniek.util.logger;

public enum LogLevel {

    FATAL(6, "FATAL", Logger.ANSI_YELLOW, Logger.ANSI_RED_BACKGROUND),
    ERROR(5, "Error", Logger.ANSI_RED, Logger.ANSI_YELLOW_BACKGROUND),
    WARNING(4, "Warning", Logger.ANSI_BLUE, Logger.ANSI_YELLOW_BACKGROUND),
    INFO(3, "Info ", Logger.ANSI_BLUE, ""),
    DEBUG(2, "Debug", Logger.ANSI_YELLOW, ""),
    TRACE(1, "Trace", Logger.ANSI_CYAN, ""),
    ALL(0, "All", Logger.ANSI_RED, "");

    private int level;
    private String name;
    private String textColor;
    private String backgroundColor;
    private String combinedColor;

    LogLevel(int level, String name, String textColor, String backgroundColor) {
        this.level = level;
        this.name = name;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
        this.combinedColor = textColor + backgroundColor;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getCombinedColor() {
        return combinedColor;
    }
}
