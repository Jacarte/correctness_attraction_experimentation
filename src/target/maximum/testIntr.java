package target.maximum;
import annotations.Ignore;
import services.api.Translate;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;

@Translate
public class testIntr {

    public static int getMaximum(int[] a) {
        int result = pE.pInt(L_53, a[pE.pInt(L_52, 0)]);
        for (int i = pE.pInt(L_54, 0); pE.pBool(L_63, pE.pInt(L_55, i) < pE.pInt(L_56, a.length)); pE.pInt(L_64, i++)) if (pE.pBool(L_60, pE.pInt(L_58, result) < pE.pInt(L_59, a[pE.pInt(L_57, i)]))) result = pE.pInt(L_62, a[pE.pInt(L_61, i)]);
        return pE.pInt(L_65, result);
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

    public static IntegerPerturbationPoint L_9;

    public static IntegerPerturbationPoint L_10;

    public static IntegerPerturbationPoint L_11;

    public static IntegerPerturbationPoint L_12;

    public static IntegerPerturbationPoint L_13;

    public static BooleanPerturbationPoint L_14;

    public static IntegerPerturbationPoint L_15;

    public static IntegerPerturbationPoint L_16;

    public static IntegerPerturbationPoint L_17;

    public static BooleanPerturbationPoint L_18;

    public static IntegerPerturbationPoint L_19;

    public static IntegerPerturbationPoint L_20;

    public static IntegerPerturbationPoint L_21;

    public static IntegerPerturbationPoint L_22;

    public static BooleanPerturbationPoint L_23;

    public static IntegerPerturbationPoint L_24;

    public static IntegerPerturbationPoint L_25;

    public static IntegerPerturbationPoint L_26;

    public static BooleanPerturbationPoint L_27;

    public static IntegerPerturbationPoint L_29;

    public static IntegerPerturbationPoint L_30;

    public static IntegerPerturbationPoint L_31;

    public static IntegerPerturbationPoint L_32;

    public static IntegerPerturbationPoint L_33;

    public static IntegerPerturbationPoint L_34;

    public static BooleanPerturbationPoint L_35;

    public static IntegerPerturbationPoint L_37;

    public static IntegerPerturbationPoint L_38;

    public static IntegerPerturbationPoint L_39;

    public static IntegerPerturbationPoint L_40;

    public static BooleanPerturbationPoint L_41;

    public static IntegerPerturbationPoint L_43;

    public static IntegerPerturbationPoint L_44;

    public static IntegerPerturbationPoint L_45;

    public static IntegerPerturbationPoint L_46;

    public static IntegerPerturbationPoint L_47;

    public static IntegerPerturbationPoint L_48;

    public static IntegerPerturbationPoint L_49;

    public static IntegerPerturbationPoint L_50;

    public static IntegerPerturbationPoint L_51;

    public static IntegerPerturbationPoint L_52;

    public static IntegerPerturbationPoint L_53;

    public static IntegerPerturbationPoint L_54;

    public static IntegerPerturbationPoint L_55;

    public static IntegerPerturbationPoint L_56;

    public static IntegerPerturbationPoint L_57;

    public static IntegerPerturbationPoint L_58;

    public static IntegerPerturbationPoint L_59;

    public static BooleanPerturbationPoint L_60;

    public static IntegerPerturbationPoint L_61;

    public static IntegerPerturbationPoint L_62;

    public static BooleanPerturbationPoint L_63;

    public static IntegerPerturbationPoint L_64;

    public static IntegerPerturbationPoint L_65;

    public static void setupPerturbation() {
        pE = _serviceProvider.getPerturbationEngine();
        L_0 = new IntegerPerturbationPoint("QuickSort.java (9:19)", 0, "beg", _serviceProvider);
        L_1 = new IntegerPerturbationPoint("QuickSort.java (9:32)", 1, "end", _serviceProvider);
        L_2 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 2, "end", _serviceProvider);
        L_3 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 3, "beg", _serviceProvider);
        L_4 = new IntegerPerturbationPoint("QuickSort.java (10:34)", 4, "pE.pInt(L_2, end) - pE.pInt(L_3, beg)", _serviceProvider);
        L_5 = new IntegerPerturbationPoint("QuickSort.java (10:33)", 5, "(pE.pInt(L_4, pE.pInt(L_2, end) - pE.pInt(L_3, beg)))", _serviceProvider);
        L_6 = new IntegerPerturbationPoint("QuickSort.java (10:47)", 6, "2", _serviceProvider);
        L_7 = new IntegerPerturbationPoint("QuickSort.java (10:33)", 7, "pE.pInt(L_5, (pE.pInt(L_4, pE.pInt(L_2, end) - pE.pInt(L_3, beg)))) / pE.pInt(L_6, 2)", _serviceProvider);
        L_8 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 8, "beg", _serviceProvider);
        L_9 = new IntegerPerturbationPoint("QuickSort.java (10:32)", 9, "(pE.pInt(L_7, pE.pInt(L_5, (pE.pInt(L_4, pE.pInt(L_2, end) - pE.pInt(L_3, beg)))) / pE.pInt(L_6, 2)))", _serviceProvider);
        L_10 = new IntegerPerturbationPoint("QuickSort.java (10:26)", 10, "pE.pInt(L_8, beg) + pE.pInt(L_9, (pE.pInt(L_7, pE.pInt(L_5, (pE.pInt(L_4, pE.pInt(L_2, end) - pE.pInt(L_3, beg)))) / pE.pInt(L_6, 2))))", _serviceProvider);
        L_11 = new IntegerPerturbationPoint("QuickSort.java (10:20)", 11, "array[pE.pInt(L_10, pE.pInt(L_8, beg) + pE.pInt(L_9, (pE.pInt(L_7, pE.pInt(L_5, (pE.pInt(L_4, pE.pInt(L_2, end) - pE.pInt(L_3, beg)))) / pE.pInt(L_6, 2)))))]", _serviceProvider);
        L_12 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 12, "left", _serviceProvider);
        L_13 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 13, "right", _serviceProvider);
        L_14 = new BooleanPerturbationPoint("QuickSort.java (12:15)", 14, "pE.pInt(L_12, left) <= pE.pInt(L_13, right)", _serviceProvider);
        L_15 = new IntegerPerturbationPoint("QuickSort.java (14:25)", 15, "left", _serviceProvider);
        L_16 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 16, "array[pE.pInt(L_15, left)]", _serviceProvider);
        L_17 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 17, "pivot", _serviceProvider);
        L_18 = new BooleanPerturbationPoint("QuickSort.java (14:19)", 18, "pE.pInt(L_16, array[pE.pInt(L_15, left)]) < pE.pInt(L_17, pivot)", _serviceProvider);
        L_19 = new IntegerPerturbationPoint("QuickSort.java (15:16)", 19, "left++", _serviceProvider);
        L_20 = new IntegerPerturbationPoint("QuickSort.java (18:25)", 20, "right", _serviceProvider);
        L_21 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 21, "array[pE.pInt(L_20, right)]", _serviceProvider);
        L_22 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 22, "pivot", _serviceProvider);
        L_23 = new BooleanPerturbationPoint("QuickSort.java (18:19)", 23, "pE.pInt(L_21, array[pE.pInt(L_20, right)]) > pE.pInt(L_22, pivot)", _serviceProvider);
        L_24 = new IntegerPerturbationPoint("QuickSort.java (19:16)", 24, "right--", _serviceProvider);
        L_25 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 25, "left", _serviceProvider);
        L_26 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 26, "right", _serviceProvider);
        L_27 = new BooleanPerturbationPoint("QuickSort.java (26:12)", 27, "pE.pInt(L_25, left) <= pE.pInt(L_26, right)", _serviceProvider);
        L_29 = new IntegerPerturbationPoint("QuickSort.java (23:28)", 29, "left", _serviceProvider);
        L_30 = new IntegerPerturbationPoint("QuickSort.java (23:34)", 30, "right", _serviceProvider);
        L_31 = new IntegerPerturbationPoint("QuickSort.java (24:16)", 31, "left++", _serviceProvider);
        L_32 = new IntegerPerturbationPoint("QuickSort.java (25:16)", 32, "right--", _serviceProvider);
        L_33 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 33, "beg", _serviceProvider);
        L_34 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 34, "right", _serviceProvider);
        L_35 = new BooleanPerturbationPoint("QuickSort.java (30:8)", 35, "pE.pInt(L_33, beg) < pE.pInt(L_34, right)", _serviceProvider);
        L_37 = new IntegerPerturbationPoint("QuickSort.java (30:24)", 37, "beg", _serviceProvider);
        L_38 = new IntegerPerturbationPoint("QuickSort.java (30:29)", 38, "right", _serviceProvider);
        L_39 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 39, "end", _serviceProvider);
        L_40 = new IntegerPerturbationPoint("QuickSort.java (0:0)", 40, "left", _serviceProvider);
        L_41 = new BooleanPerturbationPoint("QuickSort.java (32:8)", 41, "pE.pInt(L_39, end) > pE.pInt(L_40, left)", _serviceProvider);
        L_43 = new IntegerPerturbationPoint("QuickSort.java (32:24)", 43, "left", _serviceProvider);
        L_44 = new IntegerPerturbationPoint("QuickSort.java (32:30)", 44, "end", _serviceProvider);
        L_45 = new IntegerPerturbationPoint("QuickSort.java (36:22)", 45, "i", _serviceProvider);
        L_46 = new IntegerPerturbationPoint("QuickSort.java (36:16)", 46, "array[pE.pInt(L_45, i)]", _serviceProvider);
        L_47 = new IntegerPerturbationPoint("QuickSort.java (37:14)", 47, "i", _serviceProvider);
        L_48 = new IntegerPerturbationPoint("QuickSort.java (37:25)", 48, "j", _serviceProvider);
        L_49 = new IntegerPerturbationPoint("QuickSort.java (37:19)", 49, "array[pE.pInt(L_48, j)]", _serviceProvider);
        L_50 = new IntegerPerturbationPoint("QuickSort.java (38:14)", 50, "j", _serviceProvider);
        L_51 = new IntegerPerturbationPoint("QuickSort.java (38:19)", 51, "x", _serviceProvider);
        L_52 = new IntegerPerturbationPoint("test.java (11:23)", 52, "0", _serviceProvider);
        L_53 = new IntegerPerturbationPoint("test.java (11:21)", 53, "a[pE.pInt(L_52, 0)]", _serviceProvider);
        L_54 = new IntegerPerturbationPoint("test.java (14:20)", 54, "0", _serviceProvider);
        L_55 = new IntegerPerturbationPoint("test.java (0:0)", 55, "i", _serviceProvider);
        L_56 = new IntegerPerturbationPoint("test.java (0:0)", 56, "a.length", _serviceProvider);
        L_57 = new IntegerPerturbationPoint("test.java (15:26)", 57, "i", _serviceProvider);
        L_58 = new IntegerPerturbationPoint("test.java (0:0)", 58, "result", _serviceProvider);
        L_59 = new IntegerPerturbationPoint("test.java (0:0)", 59, "a[pE.pInt(L_57, i)]", _serviceProvider);
        L_60 = new BooleanPerturbationPoint("test.java (16:12)", 60, "pE.pInt(L_58, result) < pE.pInt(L_59, a[pE.pInt(L_57, i)])", _serviceProvider);
        L_61 = new IntegerPerturbationPoint("test.java (16:27)", 61, "i", _serviceProvider);
        L_62 = new IntegerPerturbationPoint("test.java (16:25)", 62, "a[pE.pInt(L_61, i)]", _serviceProvider);
        L_63 = new BooleanPerturbationPoint("test.java (14:23)", 63, "pE.pInt(L_55, i) < pE.pInt(L_56, a.length)", _serviceProvider);
        L_64 = new IntegerPerturbationPoint("test.java (14:37)", 64, "i++", _serviceProvider);
        L_65 = new IntegerPerturbationPoint("test.java (18:15)", 65, "result", _serviceProvider);
    }
}
