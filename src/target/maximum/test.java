package target.maximum;

import annotations.Ignore;
import services.api.Translate;

@Translate
public class test {

    int min = -1;

    public static int getMaximum(int[] a){


        int result = new test().min;


        for(int i = 0; i < a.length; i++)
            if(result < a[i])
                result = a[i];

        return result;
    }
}
