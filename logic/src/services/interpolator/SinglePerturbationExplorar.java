package services.interpolator;

import services.engine.IPerturbationEngine;
import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;

import java.util.List;

public class SinglePerturbationExplorar implements ISpaceExplorer {

    @Override
    public void makeSpace(List<IPerturbationPoint> pbis, ICallback callback, IPerturbationEngine engine) {

        makeSpaceAux(pbis, callback, engine, 0);

    }

    private void makeSpaceAux(List<IPerturbationPoint> pbis, ICallback callback, IPerturbationEngine engine, int index){

        if(index == pbis.size())
            return;

        IPerturbationPoint current = pbis.get(index);

        while(current.canPerturb(engine)){

            current.next();
            
            callback._do();

        }

        current.reset();
        makeSpaceAux(pbis, callback, engine, index + 1);
    }
}
