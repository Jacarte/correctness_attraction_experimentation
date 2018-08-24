package services.interpolator;

import services.engine.ISpaceExplorer;
import services.utils.CommandLineParser;

import java.util.Random;

public class IntegerArrayInputProvider implements ISpaceExplorer.IInputProvider<int[]> {

    int count;
    int size;


    public IntegerArrayInputProvider(){

        size = Integer.parseInt(CommandLineParser.CONFIG.get("size"));
        count = size;
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
        Random r = new Random(0);

        int size = r.nextInt(200) + 1;

        int[] result = new int[size];

        for(int i = 0; i < result.length; i++){
            result[i] = (int)(r.nextLong() - Integer.MAX_VALUE);
        }

        return result;
    }
}
