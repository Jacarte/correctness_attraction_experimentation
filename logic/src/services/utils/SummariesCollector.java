package services.utils;

import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;
import services.engine.ISummariesCollector;

import java.util.Map;
import java.util.TreeMap;

public class SummariesCollector implements ISummariesCollector {
    @Override
    public WholeSummary getWholeSummary(Map<IPerturbationPoint, ISpaceExplorer.PbiSummary> summaries) {

        WholeSummary result = new WholeSummary();
        result.proportions = new TreeMap<>();

        int totalSuccess = 0;
        int totalExecutions = 0;
        double correctnessRatio = 0.0;

        for(IPerturbationPoint pbi: summaries.keySet()){
            PointSummary sum = new PointSummary();

            double total = summaries.get(pbi).successCount + summaries.get(pbi).wrongCount + summaries.get(pbi).errorCount;

            sum.correctCount = summaries.get(pbi).successCount;
            sum.brokenCount = summaries.get(pbi).wrongCount;
            sum.errorCount = summaries.get(pbi).errorCount;

            sum.errorProportion = sum.errorCount/total;
            sum.correctProportion = sum.correctCount/total;
            sum.brokenProportion = sum.brokenCount/total;

            result.proportions.put(pbi, sum);

            totalSuccess += summaries.get(pbi).successCount;
            totalExecutions += total;
        }

        correctnessRatio = (double)totalSuccess/(totalExecutions);

        result.correctnessRatio = correctnessRatio;
        result.totalSuccess = totalSuccess;

        return result;
    }
}
