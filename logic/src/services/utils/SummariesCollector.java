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

            sum.correctProportion = summaries.get(pbi).successCount/total;
            sum.brokenProportion = summaries.get(pbi).wrongCount/total;
            sum.errorProportion = summaries.get(pbi).errorCount/total;

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
