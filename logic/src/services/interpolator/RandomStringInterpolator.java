package services.interpolator;

import services.engine.ISpaceExplorer;
import services.utils.CommandLineParser;

import java.util.Random;

public class RandomStringInterpolator implements ISpaceExplorer.IInputProvider<String> {

    int count;
    int size;


    public RandomStringInterpolator(){

        size = Integer.parseInt(CommandLineParser.CONFIG.get("size"));
        count = size;
    }

    @Override
    public boolean canNext() {
        return count > 0;
    }

    @Override
    public String getIn() {
        count--;
        return createRandomArray();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String copy(String in) {

        StringBuilder builder = new StringBuilder(in);
        return builder.toString();
    }

    String createRandomArray(){
        Random r = new Random(1001231);

        int size = r.nextInt(1000);

        String result = "";

        for(int i = 0; i < size; i++){
            result += ((char)r.nextInt(256));
        }

        return result;
    }
}
