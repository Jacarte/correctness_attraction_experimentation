package services.engine;

import java.util.List;

public interface ISpaceExplorer {

    void makeSpace(List<IPerturbationPoint> pbis, ICallback callback, IPerturbationEngine engine, IAnswerChecker checker,IExpectedProvider provider, ILogger logger);


    interface ICallback{
        Object _do();
    }

    interface IAnswerChecker{

        boolean _do(Object result);

    }

    interface IExpectedProvider{
        Object get();
    }

}
