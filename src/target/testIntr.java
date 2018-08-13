package target;
import annotations.Ignore;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;

public class testIntr {

    static int getMaximum(int[] a) {
        int result = pE.pInt(L_1, a[pE.pInt(L_0, 0)]);
        for (int i = pE.pInt(L_2, 0); pE.pBool(L_11, pE.pInt(L_3, i) < pE.pInt(L_4, a.length)); pE.pInt(L_12, i++)) if (pE.pBool(L_8, pE.pInt(L_6, result) < pE.pInt(L_7, a[pE.pInt(L_5, i)]))) result = pE.pInt(L_10, a[pE.pInt(L_9, i)]);
        return pE.pInt(L_13, result);
    }

    static IServiceProvider _serviceProvider = new ServiceProvider();

    public static IPerturbationEngine pE;

    public static IntegerPerturbationPoint L_0;

    public static IntegerPerturbationPoint L_1;

    public static IntegerPerturbationPoint L_2;

    public static IntegerPerturbationPoint L_3;

    public static IntegerPerturbationPoint L_4;

    public static IntegerPerturbationPoint L_5;

    public static IntegerPerturbationPoint L_6;

    public static IntegerPerturbationPoint L_7;

    public static BooleanPerturbationPoint L_8;

    public static IntegerPerturbationPoint L_9;

    public static IntegerPerturbationPoint L_10;

    public static BooleanPerturbationPoint L_11;

    public static IntegerPerturbationPoint L_12;

    public static IntegerPerturbationPoint L_13;

    public static void setupPerturbation() {
        pE = _serviceProvider.getPerturbationEngine();
        L_0 = new IntegerPerturbationPoint("null (23:9)", 0, _serviceProvider);
        L_1 = new IntegerPerturbationPoint("null (21:9)", 1, _serviceProvider);
        L_2 = new IntegerPerturbationPoint("null (20:12)", 2, _serviceProvider);
        L_3 = new IntegerPerturbationPoint("null (0:0)", 3, _serviceProvider);
        L_4 = new IntegerPerturbationPoint("null (0:0)", 4, _serviceProvider);
        L_5 = new IntegerPerturbationPoint("null (26:13)", 5, _serviceProvider);
        L_6 = new IntegerPerturbationPoint("null (0:0)", 6, _serviceProvider);
        L_7 = new IntegerPerturbationPoint("null (0:0)", 7, _serviceProvider);
        L_8 = new BooleanPerturbationPoint("null (12:14)", 8, _serviceProvider);
        L_9 = new IntegerPerturbationPoint("null (27:14)", 9, _serviceProvider);
        L_10 = new IntegerPerturbationPoint("null (25:14)", 10, _serviceProvider);
        L_11 = new BooleanPerturbationPoint("null (23:12)", 11, _serviceProvider);
        L_12 = new IntegerPerturbationPoint("null (37:12)", 12, _serviceProvider);
        L_13 = new IntegerPerturbationPoint("null (15:16)", 13, _serviceProvider);
    }
}
