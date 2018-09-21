package services.utils.loggers;

import services.engine.ILogger;
import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;
import services.engine.ISummariesCollector;
import services.utils.CommandLineParser;
import services.utils.StaticUtils;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

public class FileLoggerService implements ILogger {

    FileOutputStream out;
    IPerturbationPoint pbi;

    int success_count;
    int wrong_count;
    int error_count;

    void createNewFileOut(String name){


        try {
            out = new FileOutputStream(name + "_results.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void logResult(IPerturbationPoint pbi, boolean rightResult, Exception ex, long executionTime, long memoryWaste, long cpuWaste, Object expected, Object result) {

        
    }

    @Override
    public void logResult(String fileName, Map<IPerturbationPoint, ISpaceExplorer.PbiSummary> summaries) {

    }

    @Override
    public void logResult(ISummariesCollector.WholeSummary summary) {
        createNewFileOut(CommandLineParser.CONFIG.get("target"));

        try {
            out.write("\r".getBytes());

            out.write((StaticUtils.padRight("Total success: ", 15) + StaticUtils.padLeft(summary.totalSuccess, 20)).getBytes());
            out.write("\n".getBytes());
            out.write((StaticUtils.padRight("Correctness ratio: ", 20) + StaticUtils.padLeft(summary.correctnessRatio, 20)).getBytes());
            out.write("\n".getBytes());

            out.write((
                    StaticUtils.padRight("index ", 9) +
                            StaticUtils.padRight("succ ", 9) +
                            StaticUtils.padRight("fail ", 9) +
                            StaticUtils.padRight("error ", 9) +
                            StaticUtils.padRight("ratio ", 9)).getBytes()
            );

            out.write("\n".getBytes());

            for(IPerturbationPoint pbi: summary.proportions.keySet()){

                ISummariesCollector.PointSummary sum = summary.proportions.get(pbi);

                DecimalFormat format = new DecimalFormat("#");

                out.write((String.format("%s %s %s %s %s %s",
                        StaticUtils.padRight(" " + pbi.getIndex(), 7),
                        StaticUtils.padLeft(" " + sum.correctCount, 7),
                        StaticUtils.padLeft(" " + sum.brokenCount, 7),
                        StaticUtils.padLeft(" " + sum.errorCount, 7),
                        StaticUtils.padRight("  " + format.format(sum.correctProportion * 100) + "% ", 10),
                        StaticUtils.padRight(pbi.getOriginalExpression(), 12)).getBytes()

                ));

                out.write("\n".getBytes());

            }

           out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
