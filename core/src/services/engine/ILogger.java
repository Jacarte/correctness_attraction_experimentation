package services.engine;

import java.beans.Expression;

public interface ILogger {

    void logResult(IPerturbationPoint pbi, boolean rightResult, Exception ex, long executionTime, long memoryWaste, long cpuWaste, Object expected, Object result);

}
