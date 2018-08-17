package services.utils;

public class StaticUtils {

    public static IServiceProvider serviceProvider;

    public static String padRight(Object s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(Object s, int n) {
        return String.format("%1$" + n + "s", s);
    }
}
