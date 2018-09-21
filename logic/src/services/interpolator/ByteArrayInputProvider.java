package services.interpolator;

import services.engine.ISpaceExplorer;
import services.utils.CommandLineParser;

import java.util.Random;

public class ByteArrayInputProvider implements ISpaceExplorer.IInputProvider<byte[]> {


    int count;
    int size;


    public ByteArrayInputProvider(){

        size = Integer.parseInt(CommandLineParser.CONFIG.get("size"));
        count = size;
    }

    @Override
    public boolean canNext() {
        return count > 0;
    }

    @Override
    public byte[] getIn() {
        count--;
        return createRandomArray();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public byte[] copy(byte[] in) {

        byte[] result = new byte[in.length];

        for(int i = 0; i < in.length; i++)
            result[i] = in[i];

        return result;
    }


    byte[] createRandomArray(){
        Random r = new Random(0);

        int size = r.nextInt(100);

        byte[] result = new byte[size];

        for(int i = 0; i < result.length; i++){
            result[i] = (byte)r.nextInt(255);
        }

        return result;
    }
}
