package target.zip;

import services.engine.ISpaceExplorer;

public class ZipManager implements ISpaceExplorer.IManager<String, String> {
    @Override
    public String _do(String in) {
        return LZWIntr.decompress(LZWIntr.compress(in));
    }

    @Override
    public boolean _do(String result, String expected) {
        return result.equals(expected);
    }

    @Override
    public String get(String in) {
        return LZW.decompress(LZW.compress(in));
    }
}
