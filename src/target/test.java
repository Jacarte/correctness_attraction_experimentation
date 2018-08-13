package target;

import annotations.Ignore;

public class test {

    static int getMaximum(int[] a){

        int result = a[0];


        for(int i = 0; i < a.length; i++)
            if(result < a[i])
                result = a[i];

        return result;
    }
}
