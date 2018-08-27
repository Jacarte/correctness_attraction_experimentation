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
        int result = pE.pInt(L_0, new test().min);
        for (int i = pE.pInt(L_1, 0); pE.pBool(L_10, pE.pInt(L_2, i) < pE.pInt(L_3, a.length)); pE.pInt(L_11, i++)) if (pE.pBool(L_7, pE.pInt(L_5, result) < pE.pInt(L_6, a[pE.pInt(L_4, i)]))) result = pE.pInt(L_9, a[pE.pInt(L_8, i)]);
        return pE.pInt(L_12, result);
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

    public static BooleanPerturbationPoint L_7;

    public static IntegerPerturbationPoint L_8;

    public static IntegerPerturbationPoint L_9;

    public static BooleanPerturbationPoint L_10;

    public static IntegerPerturbationPoint L_11;

    public static IntegerPerturbationPoint L_12;

    public static void setupPerturbation() {
        pE = _serviceProvider.getPerturbationEngine();
        L_0 = new IntegerPerturbationPoint("test.java (14:21)", 0, "new test().min", _serviceProvider);
        L_1 = new IntegerPerturbationPoint("test.java (17:20)", 1, "0", _serviceProvider);
        L_2 = new IntegerPerturbationPoint("test.java (0:0)", 2, "i", _serviceProvider);
        L_3 = new IntegerPerturbationPoint("test.java (0:0)", 3, "a.length", _serviceProvider);
        L_4 = new IntegerPerturbationPoint("test.java (18:26)", 4, "i", _serviceProvider);
        L_5 = new IntegerPerturbationPoint("test.java (0:0)", 5, "result", _serviceProvider);
        L_6 = new IntegerPerturbationPoint("test.java (0:0)", 6, "a[pE.pInt(L_4, i)]", _serviceProvider);
        L_7 = new BooleanPerturbationPoint("test.java (19:12)", 7, "pE.pInt(L_5, result) < pE.pInt(L_6, a[pE.pInt(L_4, i)])", _serviceProvider);
        L_8 = new IntegerPerturbationPoint("test.java (19:27)", 8, "i", _serviceProvider);
        L_9 = new IntegerPerturbationPoint("test.java (19:25)", 9, "a[pE.pInt(L_8, i)]", _serviceProvider);
        L_10 = new BooleanPerturbationPoint("test.java (17:23)", 10, "pE.pInt(L_2, i) < pE.pInt(L_3, a.length)", _serviceProvider);
        L_11 = new IntegerPerturbationPoint("test.java (17:37)", 11, "i++", _serviceProvider);
        L_12 = new IntegerPerturbationPoint("test.java (21:15)", 12, "result", _serviceProvider);
    }
}
