package services.utils;

import services.engine.ILogger;
import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;
import services.engine.ISummariesCollector;

import java.util.Map;

public class ConsoleLoggerService implements ILogger {
    @Override
    public void logResult(IPerturbationPoint pbi, boolean rightResult, Exception ex, long executionTime, long memoryWaste, long cpuWaste, Object expected, Object result) {
        System.out.println(String.format("%s perturbation:%s originalValue:%s success:%s exception:%s memory:%s cpu:%s",
                pbi.getName(),
                pbi.getPerturbationValue(),
                null,
                rightResult, ex != null? ex.getStackTrace()[0].toString(): "",
                memoryWaste,
                cpuWaste));
    }

    @Override
    public void logResult(String fileName, Map<IPerturbationPoint, ISpaceExplorer.PbiSummary> summaries) {

        for(IPerturbationPoint pbi: summaries.keySet()){

            ISpaceExplorer.PbiSummary summary = summaries.get(pbi);

            System.out.println(String.format("%s s:%s w:%s e:%s", pbi.getName(), summary.successCount, summary.wrongCount, summary.errorCount));
        }

    }

    @Override
    public void logResult(ISummariesCollector.WholeSummary summary) {
        System.out.println("Total success: " + summary.totalSuccess);
        System.out.println("Correctness ratio: " + summary.correctnessRatio);


        System.out.println("=============================================");

        for(IPerturbationPoint pbi: summary.proportions.keySet()){

            ISummariesCollector.PointSummary sum = summary.proportions.get(pbi);


            System.out.println(String.format("i:%s name:%s code: %s succ:%s fail:%s error:%s correct_ratio:%s",pbi.getIndex(), pbi.getName(), pbi.getOriginalExpression(), sum.correctCount, sum.brokenCount, sum.errorCount, sum.correctProportion ));

        }
    }
}
