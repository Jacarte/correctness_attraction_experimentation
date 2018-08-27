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
        return Arrays.equals(result, expected);
    }

    @Override
    public byte[] get(byte[] in) {
        return MD5.computeMD5(in);
    }
}
