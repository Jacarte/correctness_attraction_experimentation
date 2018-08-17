package target.quicksort;

import services.engine.ISpaceExplorer;

public class QuickSortManager implements ISpaceExplorer.IManager<int[], int[]> {
    @Override
    public int[] _do(int[] in) {
        QuickSortIntr.sort(in, 0, in.length - 1);
        return in;
    }

    @Override
    public boolean _do(int[] output, int[] input) {
        int cpt = 1;

        if (!contains(input, output[0]) || input.length != output.length)
            return false;

        for (int i = 1; i < output.length; i++) {
            if (output[i - 1] > output[i])
                return false;
            if (contains(input, output[i]))
                cpt++;
            else {
                return false;
            }
        }

        return input.length == cpt;
    }

    @Override
    public int[] get(int[] in) {
        QuickSort.sort(in, 0, in.length - 1);
        return in;
    }


    boolean compareNaive(int[] result, int[] expected){
        if(result == null || expected == null)
            return false;

        if(result.length != expected.length)
            return false;

        for(int i = 0; i < expected.length; i++)
            if(expected[i] != result[i])
                return false;

        return true;
    }

    private boolean contains(int[] array, int value) {
        for (int values : array) {
            if (values == value)
                return true;
        }
        return false;
    }

}
