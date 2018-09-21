package target.md5;

import services.engine.ISpaceExplorer;

import java.util.Arrays;

public class MD5Manager implements ISpaceExplorer.IManager<byte[], byte[]> {

    @Override
    public byte[] _do(byte[] in) {
        return MD5Intr.computeMD5(in);
    }

    @Override
    public boolean _do(byte[] result, byte[] expected) {

        if(result.length != expected.length) return false;

        for(int i = 0; i < result.length; i++)
            if(result[i] != expected[i]) return false;

        return true;
    }

    @Override
    public byte[] get(byte[] in) {
        return MD5.computeMD5(in);
    }
}
