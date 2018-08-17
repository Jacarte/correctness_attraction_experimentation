package target.maximum;

import annotations.Ignore;
import services.api.Translate;

@Translate
public class test {

    public static int getMaximum(int[] a){

        int result = a[0];


        for(int i = 0; i < a.length; i++)
            if(result < a[i])
                result = a[i];

        return result;
    }
}
