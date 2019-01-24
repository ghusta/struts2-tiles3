package fr.husta.test.util.ansi;

/**
 * Voir exemple : org.apache.tools.ant.listener.AnsiColorLogger.
 * <p>
 * Table ANSI ESCAPE SEQ : http://ascii-table.com/ansi-escape-sequences.php
 * </p>
 * <p>
 * ANSI escape colors : https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
 * </p>
 */
public abstract class AnsiColor
{

    // private static final int ATTR_NORMAL = 0;
    private static final int ATTR_BRIGHT = 1; // = Bold on
    private static final int ATTR_DIM = 2;
    private static final int ATTR_UNDERLINE = 3;
    private static final int ATTR_BLINK = 5;
    private static final int ATTR_REVERSE = 7;
    // private static final int ATTR_HIDDEN = 8;

    public static final int FG_BLACK = 30;
    public static final int FG_RED = 31;
    public static final int FG_GREEN = 32;
    public static final int FG_YELLOW = 33;
    public static final int FG_BLUE = 34;
    public static final int FG_MAGENTA = 35;
    public static final int FG_CYAN = 36;
    public static final int FG_WHITE = 37;

    public static final int FG_BRIGHT_BLACK = 90;
    public static final int FG_BRIGHT_RED = 91;
    public static final int FG_BRIGHT_GREEN = 92;
    public static final int FG_BRIGHT_YELLOW = 93;
    public static final int FG_BRIGHT_BLUE = 94;
    public static final int FG_BRIGHT_MAGENTA = 95;
    public static final int FG_BRIGHT_CYAN = 96;
    public static final int FG_BRIGHT_WHITE = 97;

    public static final int BG_BLACK = 40;
    public static final int BG_RED = 41;
    public static final int BG_GREEN = 42;
    public static final int BG_YELLOW = 44;
    public static final int BG_BLUE = 44;
    public static final int BG_MAGENTA = 45;
    public static final int BG_CYAN = 46;
    public static final int BG_WHITE = 47;

    public static final int BG_BRIGHT_YELLOW = 103;

    private static final char ESCAPE_CHAR = '\u001B'; // CHAR : Escape (27)
    private static final String PREFIX = ESCAPE_CHAR + "[";
    private static final String SUFFIX = "m";
    private static final char SEPARATOR = ';';
    private static final String END_COLOR = PREFIX + SUFFIX;

    private static String errColor
            = PREFIX + ATTR_DIM + SEPARATOR + FG_RED + SUFFIX;
    private static String warnColor
            = PREFIX + ATTR_DIM + SEPARATOR + FG_MAGENTA + SUFFIX;
    private static String infoColor
            = PREFIX + ATTR_DIM + SEPARATOR + FG_BLUE + SUFFIX;
    private static String verboseColor
            = PREFIX + ATTR_DIM + SEPARATOR + FG_GREEN + SUFFIX;
    private static String debugColor
            = PREFIX + ATTR_DIM + SEPARATOR + FG_BLUE + SUFFIX;

    public static String colorizeDefault(String message)
    {
        return infoColor + message + END_COLOR;
    }

    public static String colorize(String message, int foregroundColor)
    {
        return PREFIX + ATTR_DIM + SEPARATOR + foregroundColor + SUFFIX + message + END_COLOR;
    }

    public static String colorize(String message, int foregroundColor, int backgroundColor)
    {
        return PREFIX + ATTR_DIM + SEPARATOR + foregroundColor + SUFFIX
                + PREFIX + ATTR_DIM + SEPARATOR + backgroundColor + SUFFIX
                + message + END_COLOR;
    }

    public static String bold(String message)
    {
        return PREFIX + ATTR_BRIGHT + SUFFIX + message + END_COLOR;
    }

    public static String blink(String message)
    {
        return PREFIX + ATTR_BLINK + SUFFIX + message + END_COLOR;
    }

    public static String reverseVideo(String message)
    {
        return PREFIX + ATTR_REVERSE + SUFFIX + message + END_COLOR;
    }

}
