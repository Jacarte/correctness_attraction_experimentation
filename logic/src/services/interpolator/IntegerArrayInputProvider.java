package services.interpolator;

import services.engine.ISpaceExplorer;

import java.util.Random;

public class IntegerArrayInputProvider implements ISpaceExplorer.IInputProvider<int[]> {

    int count = 1000;
    int size = 0;

    public IntegerArrayInputProvider(){
        size = count;
    }

    @Override
    public boolean canNext() {
        return count > 0;
    }

    @Override
    public int[] getIn() {
        count--;
        return createRandomArray();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int[] copy(int[] in) {

        int[] result = new int[in.length];

        for(int i = 0; i < in.length; i++)
            result[i] = in[i];

        return result;
    }

    int[] createRandomArray(){
        int size = new Random().nextInt(3000) + 1;

        int[] result = new int[size];

        for(int i = 0; i < result.length; i++){
            result[i] = new Random().nextInt(400000);
        }

        return result;
    }
}
