package services.interpolator;

import services.engine.ISpaceExplorer;

import java.util.Random;

public class IntegerArrayInputProvider implements ISpaceExplorer.IInputProvider<int[]> {

    int count = 10000;

    public IntegerArrayInputProvider(){

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
        return count;
    }

    int[] createRandomArray(){
        int size = new Random().nextInt(2000) + 1;

        int[] result = new int[size];

        for(int i = 0; i < result.length; i++){
            result[i] = new Random().nextInt(400000);
        }

        return result;
    }
}
