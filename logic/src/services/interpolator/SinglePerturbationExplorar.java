package services.interpolator;

import services.engine.*;
import services.utils.SummariesCollector;

import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SinglePerturbationExplorar implements ISpaceExplorer {


    @Override
    public <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis, ICallback<Tin, Tout> callback, IPerturbationEngine engine, IAnswerChecker<Tout> checker, IExpectedProvider<Tin, Tout> provider, IInputProvider<Tin> inputProvider, ILogger logger) {

        Map<IPerturbationPoint, PbiSummary> summaries = new TreeMap<>();

        int executionCounter = 0;

        while(inputProvider.canNext()){

            Tin input = inputProvider.getIn();

            Tout expected = provider.get(input);

            for(IPerturbationPoint pbi: pbis){

                if(!summaries.containsKey(pbi))
                    summaries.put(pbi, new PbiSummary());

                while(pbi.canPerturb(engine)){

                    pbi.next();

                    executionCounter++;

                    try {
                        Tout result = callback._do(input);

                        boolean isRight = checker._do(result, expected);

                        if(isRight)
                            summaries.get(pbi).successCount++;
                        else
                            summaries.get(pbi).wrongCount++;
                    }
                    catch (Exception e){
                        summaries.get(pbi).errorCount++;
                    }
                }

                pbi.reset();
            }
        }

        ISummariesCollector.WholeSummary summary = new SummariesCollector().getWholeSummary(summaries);

        logger.logResult(summary);
    }

    @Override
    public <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis, IPerturbationEngine engine, IManager<Tin, Tout> manager, IInputProvider<Tin> inputProvider, ILogger logger) {
        makeSpace(pbis, manager, engine, manager, manager, inputProvider, logger);
    }
}
