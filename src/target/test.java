package target;

import annotations.Ignore;

public class test {

    static int getMaximum(int[] a){

        int result = a[0];

        int j = 0;

        for(int i = 0; i < a.length; i++, j++)
            if(result < a[i])
                result = a[i];

        return result;
    }
}
