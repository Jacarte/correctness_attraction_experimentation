package services.utils;

import services.engine.ILogger;
import services.engine.IPerturbationPoint;
import services.engine.ISpaceExplorer;
import services.engine.ISummariesCollector;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class FileLoggerService implements ILogger {

    FileOutputStream out;
    IPerturbationPoint pbi;

    int success_count;
    int wrong_count;
    int error_count;

    void createNewFileOut(IPerturbationPoint pbi){


        try {
            if(out != null) {

                out.write(("Success count: " + success_count + "\n").getBytes());
                out.write(("Wrong count: " + wrong_count + "\n").getBytes());
                out.write(("Error count: " + error_count + "\n").getBytes());

                out.close();
            }
            out = new FileOutputStream(pbi.getName() + pbi.getIndex() + ".txt");

            success_count = 0;
            wrong_count = 0;
            error_count = 0;

            out.write((pbi.getName() + "\n").getBytes());
            out.write((pbi.getOriginalExpression() + "\n").getBytes());
            out.write(("======================================\n").getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void logResult(IPerturbationPoint pbi, boolean rightResult, Exception ex, long executionTime, long memoryWaste, long cpuWaste, Object expected, Object result) {

        if(!pbi.equals(this.pbi)){
            createNewFileOut(pbi);
        }

        this.pbi = pbi;

        if(rightResult)
            success_count++;
        else if(ex != null)
            error_count++;
        else
            wrong_count++;

        StringBuilder message = new StringBuilder();


        message.append("Perturbed in value: ");
        message.append(pbi.getPerturbationValue());
        message.append("\n");


        message.append("Result success: ");
        message.append(rightResult);
        message.append("\n");


        message.append("Expected value: ");
        message.append(expected);
        message.append("\n");

        message.append("Result value: ");
        message.append(result);
        message.append("\n");


        message.append("Memory waste: ");
        message.append(memoryWaste);
        message.append("\n");


        message.append("Cpu waste: ");
        message.append(cpuWaste);
        message.append("\n");


        if(ex != null) {
            message.append("Exception: ");
            message.append(ex);
            message.append("\n");
        }

        message.append("\n\n");

        try {
            out.write(message.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logResult(String fileName, Map<IPerturbationPoint, ISpaceExplorer.PbiSummary> summaries) {

    }

    @Override
    public void logResult(ISummariesCollector.WholeSummary collector) {

    }
}
