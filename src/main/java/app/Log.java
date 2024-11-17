package app;

public class Log {
    public static void log(String message) {
        System.out.println(ColorTerminal.green() + "system << log (" + message + ")." + ColorTerminal.end());
    }

    public static void log(String name, String message) {
        System.out.println(ColorTerminal.green() + "system << log (" + name + "): " + message + ColorTerminal.end());
    }
}
