package app;

public class ColorTerminal {
    public static String green() {
        return "\033[32m";
    }

    public static String red() {
        return "\033[31m";
    }

    public static String yellow() {
        return "\033[33m";
    }

    public static String blue() {
        return "\033[34m";
    }

    public static String white() {
        return "\033[37m";
    }

    public static String bold() {
        return "\033[1m";
    }

    public static String underlined() {
        return "\033[4m";
    }

    public static String end() {
        return "\033[0m";
    }
}
