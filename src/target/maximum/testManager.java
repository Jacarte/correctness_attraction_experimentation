package target.maximum;
import services.engine.ISpaceExplorer;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;
import services.*;
import services.utils.*;
import services.engine.*;
import pbi.*;

public class testManager implements ISpaceExplorer.IManager<int[], Integer> {

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
