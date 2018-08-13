package services.engine;

import java.util.List;

public interface ISpaceExplorer {

    void makeSpace(List<IPerturbationPoint> pbis, ICallback callback, IPerturbationEngine engine);


    interface ICallback{
        void _do();
    }

}
