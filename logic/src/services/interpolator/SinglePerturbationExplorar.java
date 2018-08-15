package services.interpolator;

import services.engine.ILogger;
import services.engine.IPerturbationEngine;
import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;

import java.lang.management.MemoryUsage;
import java.util.List;

public class SinglePerturbationExplorar implements ISpaceExplorer {

    @Override
    public void makeSpace(List<IPerturbationPoint> pbis, ICallback callback, IPerturbationEngine engine, IAnswerChecker checker, IExpectedProvider provider, ILogger logger) {

        makeSpaceAux(pbis, callback, engine, 0, checker , provider, logger);

    }

    private void makeSpaceAux(List<IPerturbationPoint> pbis, ICallback callback, IPerturbationEngine engine, int index, IAnswerChecker rightAnswer, IExpectedProvider provider, ILogger logger){

        if(index == pbis.size())
            return;

        IPerturbationPoint current = pbis.get(index);

        while(current.canPerturb(engine)){

            current.next();

            long timeNow = System.nanoTime();

            try{


                Object result = callback._do();

                timeNow = System.nanoTime() - timeNow;

                boolean isRight = rightAnswer._do(result);

                logger.logResult(current, isRight, null, timeNow, 0, 0, provider.get() , result );

            }
            catch (Exception e){
                timeNow = System.nanoTime() - timeNow;
                logger.logResult(current, false, e, timeNow, 0, 0, provider.get() , null );
            }
        }

        current.reset();
        makeSpaceAux(pbis, callback, engine, index + 1, rightAnswer,provider, logger);
    }
}
