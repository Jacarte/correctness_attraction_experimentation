package api.interpolator;


public class SequencialInterpolator implements Interpolator<int[], Integer> {


    @Override
    public int[] interpolate(Integer value) {

        int[] result = new int[10];

        for(int i = -5 ;i  < result.length - 5; i++){
            result[i + 5] = value + i;
        }

        return result;
    }
}
