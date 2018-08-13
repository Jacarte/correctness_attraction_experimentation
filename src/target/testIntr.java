package target;
import annotations.Ignore;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;

public class testIntr {

    static int getMaximum(int[] a) {
        int result = _serviceProvider.getPerturbationEngine().pInt(L_1, a[_serviceProvider.getPerturbationEngine().pInt(L_0, 0)]);
        int j = _serviceProvider.getPerturbationEngine().pInt(L_2, 0);
        for (int i = _serviceProvider.getPerturbationEngine().pInt(L_3, 0); _serviceProvider.getPerturbationEngine().pBool(L_12, _serviceProvider.getPerturbationEngine().pInt(L_4, i) < _serviceProvider.getPerturbationEngine().pInt(L_5, a.length)); _serviceProvider.getPerturbationEngine().pInt(L_13, i++), _serviceProvider.getPerturbationEngine().pInt(L_14, j++)) if (_serviceProvider.getPerturbationEngine().pBool(L_9, _serviceProvider.getPerturbationEngine().pInt(L_7, result) < _serviceProvider.getPerturbationEngine().pInt(L_8, a[_serviceProvider.getPerturbationEngine().pInt(L_6, i)]))) result = _serviceProvider.getPerturbationEngine().pInt(L_11, a[_serviceProvider.getPerturbationEngine().pInt(L_10, i)]);
        return _serviceProvider.getPerturbationEngine().pInt(L_15, result);
    }

    static IServiceProvider _serviceProvider = new ServiceProvider();

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

    public static IntegerPerturbationPoint L_15;

    public static void setupPerturbation() {
        L_0 = new IntegerPerturbationPoint("null (23:9)", 0, _serviceProvider);
        L_1 = new IntegerPerturbationPoint("null (21:9)", 1, _serviceProvider);
        L_2 = new IntegerPerturbationPoint("null (16:11)", 2, _serviceProvider);
        L_3 = new IntegerPerturbationPoint("null (20:13)", 3, _serviceProvider);
        L_4 = new IntegerPerturbationPoint("null (0:0)", 4, _serviceProvider);
        L_5 = new IntegerPerturbationPoint("null (0:0)", 5, _serviceProvider);
        L_6 = new IntegerPerturbationPoint("null (26:14)", 6, _serviceProvider);
        L_7 = new IntegerPerturbationPoint("null (0:0)", 7, _serviceProvider);
        L_8 = new IntegerPerturbationPoint("null (0:0)", 8, _serviceProvider);
        L_9 = new BooleanPerturbationPoint("null (12:15)", 9, _serviceProvider);
        L_10 = new IntegerPerturbationPoint("null (27:15)", 10, _serviceProvider);
        L_11 = new IntegerPerturbationPoint("null (25:15)", 11, _serviceProvider);
        L_12 = new BooleanPerturbationPoint("null (23:13)", 12, _serviceProvider);
        L_13 = new IntegerPerturbationPoint("null (37:13)", 13, _serviceProvider);
        L_14 = new IntegerPerturbationPoint("null (42:13)", 14, _serviceProvider);
        L_15 = new IntegerPerturbationPoint("null (15:17)", 15, _serviceProvider);
    }
}
