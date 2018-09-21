package services.utils.loggers;

import services.engine.ILogger;
import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;
import services.engine.ISummariesCollector;
import services.utils.CommandLineParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class PythonDataLogger implements ILogger {

    FileOutputStream out;
    IPerturbationPoint pbi;

    int success_count;
    int wrong_count;
    int error_count;

    void createNewFileOut(String name){


        try {
            out = new FileOutputStream(name + "_python_array.txt");
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
            out.write("{".getBytes());
            out.write(String.format("\"label\":\"%s\",'data':", CommandLineParser.CONFIG.get("target") ).getBytes());
            out.write("[".getBytes());
            for(IPerturbationPoint pbi: summary.proportions.keySet()){

                ISummariesCollector.PointSummary sum = summary.proportions.get(pbi);

                if(!Double.isNaN(sum.correctProportion))
                    out.write(String.format("%s,", sum.correctProportion).getBytes());
            }
            out.write("]".getBytes());
            out.write("}".getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
