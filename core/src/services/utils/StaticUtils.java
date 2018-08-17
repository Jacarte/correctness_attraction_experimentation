package services.utils;

public class StaticUtils {

    public static IServiceProvider serviceProvider;

    public static String padRight(Object s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(Object s, int n) {
        return String.format("%1$" + n + "s", s);
    }


    public static String printPercent(double value){

        double val = value;

        int size = 100;

        double fill = val*size;

        String result = "│";

        for(int i= 0 ; i < size; i++){
            if(i < fill)
                result += "█";
            else
                result += " ";
        }

        return result + '│';
    }
}

