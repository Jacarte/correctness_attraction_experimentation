package services.utils;

import services.engine.ILogger;
import services.engine.IPerturbationPoint;

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
}
