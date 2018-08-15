package target;

import services.engine.ISpaceExplorer;

public class testManager implements ISpaceExplorer.IManager<int[], Integer>
{

    @Override
    public Integer _do(int[] in) {
        return testIntr.getMaximum(in);
    }

    @Override
    public boolean _do(Integer result, Integer expected) {
        return result.equals(expected);
    }

    @Override
    public Integer get(int[] in) {
        return test.getMaximum(in);
    }
}
