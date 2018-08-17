package services.interpolator;

import jdk.nashorn.internal.codegen.CompilerConstants;
import services.engine.*;
import services.utils.StaticUtils;
import services.utils.SummariesCollector;

import java.lang.management.MemoryUsage;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SinglePerturbationExplorar implements ISpaceExplorer {

    final Lock _lock = new ReentrantLock();

    @Override
    public <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis, ICallback<Tin, Tout> callback, IPerturbationEngine engine, IAnswerChecker<Tout> checker, IExpectedProvider<Tin, Tout> provider, IInputProvider<Tin> inputProvider, ILogger logger) {

        Map<IPerturbationPoint, PbiSummary> summaries = new TreeMap<>();

        int executionCounter = 0;
        int total = pbis.size()*engine.getExecutionTimes(pbis.get(0)) * inputProvider.getSize();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        CompletionService<Tout> service
                = new ExecutorCompletionService<>(executor);

        StaticUtils.serviceProvider.getLoggerService().reportHeader(engine.getFilename());

        while(inputProvider.canNext()){

            Tin input = inputProvider.getIn();

            Tout expected = provider.get(inputProvider.copy(input));

            for(IPerturbationPoint pbi: pbis){

                if(!summaries.containsKey(pbi))
                    summaries.put(pbi, new PbiSummary());

                for(int i = 0; i < engine.getExecutionTimes(pbi); i++) {

                    while (pbi.canPerturb(engine)) {

                        pbi.next();


                        Callable<Tout> callable = () -> {
                            return callback._do(inputProvider.copy(input));
                        };

                        try {

                            executionCounter++;

                            StaticUtils.serviceProvider.getLoggerService().reportStatus("Perturbing",
                                    ((double)executionCounter/total),  String.format("%s/%s", executionCounter, total) + " Executing..."
                                    + pbi.getName()
                                    + " "
                                    + pbi.getIndex());

                            service.submit(callable);

                            Future<Tout> future = service.poll(engine.getExecutionTimeout(), TimeUnit.MILLISECONDS);

                            Tout result = future.get();

                            if (checker._do(expected, result))
                                summaries.get(pbi).successCount++;
                            else
                                summaries.get(pbi).wrongCount++;

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            summaries.get(pbi).errorCount++;
                            StaticUtils.serviceProvider.getLoggerService().reportStatus("Perturbing", ((double)executionCounter/total), "Finishing with..." + e.getMessage());

                            //System.out.println();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                            summaries.get(pbi).errorCount++;
                            StaticUtils.serviceProvider.getLoggerService().reportStatus("Perturbing", ((double)executionCounter/total), "Finishing with..." + e.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            summaries.get(pbi).errorCount++;
                            StaticUtils.serviceProvider.getLoggerService().reportStatus("Perturbing", ((double)executionCounter/total), "Finishing with...timeout");
                        }
                    }

                    pbi.reset();
                }

            }

        }

        ISummariesCollector.WholeSummary summary = new SummariesCollector().getWholeSummary(summaries);

        logger.logResult(summary);

        executor.shutdown();
    }

    @Override
    public <Tin, Tout> void makeSpace(List<IPerturbationPoint> pbis, IPerturbationEngine engine, IManager<Tin, Tout> manager, IInputProvider<Tin> inputProvider, ILogger logger) {
        makeSpace(pbis, manager, engine, manager, manager, inputProvider, logger);
    }
}
