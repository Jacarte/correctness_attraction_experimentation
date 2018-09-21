package services.interpolator;

import services.engine.*;
import services.utils.IServiceProvider;
import services.utils.StaticUtils;
import services.utils.SummariesCollector;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SinglePerturbationExplorer implements ISpaceExplorer {

    final Lock _lock = new ReentrantLock();

    @Override
    public <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis, ICallback<Tin, Tout> callback, IPerturbationEngine engine, IAnswerChecker<Tout> checker, IExpectedProvider<Tin, Tout> provider, IInputProvider<Tin> inputProvider, List<ILogger> logger) {

        Map<IPerturbationPoint, PbiSummary> summaries = new TreeMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(1);

        CompletionService<Tout> service
                = new ExecutorCompletionService<>(executor);

        StaticUtils.serviceProvider.getLoggerService().reportHeader(engine.getFilename());

        ISummariesCollector.WholeSummary summary;

        while(inputProvider.canNext()){

            Tin input = inputProvider.getIn();

            Tout expected = provider.get(inputProvider.copy(input));

            executeWithoutPerturbation(callback, inputProvider.copy(input));

            Map<IPerturbationPoint, Integer> accessCount = engine.getAccessCount();

            for(IPerturbationPoint pbi: pbis)
                pbi.reset();

            for(IPerturbationPoint pbi: pbis){


                if(!summaries.containsKey(pbi))
                    summaries.put(pbi, new PbiSummary());

                if(!accessCount.containsKey(pbi))
                    continue;;

                for(int i = 0; i < accessCount.get(pbi); i++) {

                    pbi.setTime(0);

                    int finalI = i;

                    Callable<Tout> callable = () -> {
                            return executePerturbation(pbi, StaticUtils.serviceProvider.getModel(), callback, inputProvider.copy(input), finalI);
                        };

                        try {

                            summary = new SummariesCollector().getWholeSummary(summaries);


                            long time = System.currentTimeMillis();
                            service.submit(callable);

                            Future<Tout> future = service.poll(engine.getExecutionTimeout(), TimeUnit.MILLISECONDS);


                            Tout result = future.get();

                            time = System.currentTimeMillis() - time;



                            if (result != null && checker._do(result, expected))
                                summaries.get(pbi).successCount++;
                            else
                                summaries.get(pbi).wrongCount++;

                            //System.out.println();
                        } catch (Exception e) {
                            //e.printStackTrace();
                            summaries.get(pbi).errorCount++;
                            // StaticUtils.serviceProvider.getLoggerService().reportStatus("Perturbing", ((double)executionCounter/total), "Finishing with...timeout");
                        }
                        pbi.setTime(-2);
                }


            }

            engine.resetAccessCount();

        }


        summary = new SummariesCollector().getWholeSummary(summaries);

        ISummariesCollector.WholeSummary finalSummary = summary;
        logger.forEach(l ->
                l.logResult(finalSummary));

        executor.shutdown();
    }

    @Override
    public <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis, IPerturbationEngine engine, IManager<Tin, Tout> manager, IInputProvider<Tin> inputProvider, List<ILogger> logger) {
        makeSpace(pbis, manager, engine, manager, manager, inputProvider, logger);
    }


    public <Tin, Tout> Tout executeWithoutPerturbation(ICallback<Tin, Tout> callback, Tin input) {

        StaticUtils.serviceProvider.setExecutionPolicy(IServiceProvider.ExectionPolicy.REGISTER_ACCESS);

        Tout result = callback._do(input);

        StaticUtils.serviceProvider.setExecutionPolicy(IServiceProvider.ExectionPolicy.PERTURBING);

        return result;
    }

    @Override
    public <Tin, Tout> Tout executePerturbation(IPerturbationPoint pbi, IPerturbationModel model, ICallback<Tin, Tout> callback, Tin input, int time) {

        pbi.perturb(model, time);

        Tout result = callback._do(input);

        pbi.reset(model);

        return result;
    }
}
