package services.engine;

import java.beans.Expression;
import java.util.Map;

public interface ILogger {

    void logResult(IPerturbationPoint pbi, boolean rightResult, Exception ex, long executionTime, long memoryWaste, long cpuWaste, Object expected, Object result);

    void logResult(String fileName, Map<IPerturbationPoint, ISpaceExplorer.PbiSummary> summaries);

    void logResult(ISummariesCollector.WholeSummary collector);
}
