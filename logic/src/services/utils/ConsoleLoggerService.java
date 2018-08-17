package services.utils;

import services.engine.ILogger;
import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;
import services.engine.ISummariesCollector;

import java.text.DecimalFormat;
import java.util.Map;

import static services.utils.StaticUtils.printPercent;

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
        //System.out.print("\r");
        System.out.println(StaticUtils.padRight("Total success: ", 15) + StaticUtils.padLeft(summary.totalSuccess, 20));
        System.out.println(StaticUtils.padRight("Correctness ratio: ", 20) + StaticUtils.padLeft(summary.correctnessRatio, 20));

        System.out.println(
                StaticUtils.padRight("index ", 8) +
                StaticUtils.padRight("succ ", 8) +
                StaticUtils.padRight("fail ", 8) +
                StaticUtils.padRight("error ", 8) +
                StaticUtils.padRight("ratio ", 8)
                );

        for(IPerturbationPoint pbi: summary.proportions.keySet()){

            ISummariesCollector.PointSummary sum = summary.proportions.get(pbi);

            DecimalFormat format = new DecimalFormat("#");

            System.out.println(String.format("%s %s %s %s %s %s",
                    StaticUtils.padRight(" " + pbi.getIndex(), 6),
                    StaticUtils.padLeft(" " + sum.correctCount, 6),
                    StaticUtils.padLeft(" " + sum.brokenCount, 6),
                    StaticUtils.padLeft(" " + sum.errorCount, 6),
                    StaticUtils.padRight("  " + format.format(sum.correctProportion * 100) + "% ", 10),
                    printPercent(sum.correctProportion)
                     ));

        }
    }

}
