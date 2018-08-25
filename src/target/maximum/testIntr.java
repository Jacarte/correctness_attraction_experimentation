package target.maximum;
import annotations.Ignore;
import services.api.Translate;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;

@Translate
public class testIntr {

    int min = -1;

    public static int getMaximum(int[] a) {
        int result = pE.pInt(L_1, pE.pInt(L_0, new test().min));
        for (int i = pE.pInt(L_3, pE.pInt(L_2, 0)); pE.pBool(L_12, pE.pInt(L_4, i) < pE.pInt(L_5, a.length)); pE.pInt(L_13, i++)) if (pE.pBool(L_9, pE.pInt(L_7, result) < pE.pInt(L_8, a[pE.pInt(L_6, i)]))) result = pE.pInt(L_11, a[pE.pInt(L_10, i)]);
        return pE.pInt(L_14, result);
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

    public static IntegerPerturbationPoint L_8;

    public static BooleanPerturbationPoint L_9;

    public static IntegerPerturbationPoint L_10;

    public static IntegerPerturbationPoint L_11;

    public static BooleanPerturbationPoint L_12;

    public static IntegerPerturbationPoint L_13;

    public static IntegerPerturbationPoint L_14;

    public static void setupPerturbation() {
        pE = _serviceProvider.getPerturbationEngine();
        L_0 = new IntegerPerturbationPoint("test.java (14:21)", 0, "new test().min", _serviceProvider);
        L_1 = new IntegerPerturbationPoint("test.java (0:0)", 1, "pE.pInt(L_0, new test().min)", _serviceProvider);
        L_2 = new IntegerPerturbationPoint("test.java (17:20)", 2, "0", _serviceProvider);
        L_3 = new IntegerPerturbationPoint("test.java (0:0)", 3, "pE.pInt(L_2, 0)", _serviceProvider);
        L_4 = new IntegerPerturbationPoint("test.java (0:0)", 4, "i", _serviceProvider);
        L_5 = new IntegerPerturbationPoint("test.java (0:0)", 5, "a.length", _serviceProvider);
        L_6 = new IntegerPerturbationPoint("test.java (18:26)", 6, "i", _serviceProvider);
        L_7 = new IntegerPerturbationPoint("test.java (0:0)", 7, "result", _serviceProvider);
        L_8 = new IntegerPerturbationPoint("test.java (0:0)", 8, "a[pE.pInt(L_6, i)]", _serviceProvider);
        L_9 = new BooleanPerturbationPoint("test.java (19:12)", 9, "pE.pInt(L_7, result) < pE.pInt(L_8, a[pE.pInt(L_6, i)])", _serviceProvider);
        L_10 = new IntegerPerturbationPoint("test.java (19:27)", 10, "i", _serviceProvider);
        L_11 = new IntegerPerturbationPoint("test.java (19:25)", 11, "a[pE.pInt(L_10, i)]", _serviceProvider);
        L_12 = new BooleanPerturbationPoint("test.java (17:23)", 12, "pE.pInt(L_4, i) < pE.pInt(L_5, a.length)", _serviceProvider);
        L_13 = new IntegerPerturbationPoint("test.java (17:37)", 13, "i++", _serviceProvider);
        L_14 = new IntegerPerturbationPoint("test.java (21:15)", 14, "result", _serviceProvider);
    }
}
