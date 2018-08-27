package entrypoint;

import com.github.antlrjavaparser.JavaParser;
import com.github.antlrjavaparser.api.*;
import services.interpolator.IntegerArrayInputProvider;
import services.utils.*;
import target.maximum.testIntr;
import target.maximum.testManager;
import target.md5.MD5Intr;
import target.md5.MD5Manager;
import target.quicksort.QuickSortIntr;
import target.quicksort.QuickSortManager;
import target.sudoku.SudokuIntr;
import target.sudoku.SudokuManager;
//import target.testIntr;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Main {



    static String[] args;

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {


        setup();

        Main.args = args;

        StaticUtils.serviceProvider = provider;

        parseArgs();

    }

    static IServiceProvider provider;

    public static void setup(){

        provider = new ServiceProvider();
    }

    public static void parseArgs(){
        if(args.length == 0)
            printHelp();
        else if(args[0].equals("-h"))
            printHelp();
        else if(args[0].equals("-r"))
            runPerturbation();
    }

    public static void printHelp(){
        System.out.println("-h: print help");
        System.out.println("-r -time x -size x -type <pbool>|<pone> -target <qs>: run perturbation experiment");
    }

    public static void runPerturbation(){

        new CommandLineParser().run(1, args);


        switch (target){
            case "qs": // Quick sort
                QuickSortIntr.setupPerturbation();
                provider.getPerturbationEngine().setFileName("QuickSort.java");
                provider.getPerturbationEngine().makeSpace(new QuickSortManager(), new IntegerArrayInputProvider());
                break;
            case "mx":

                testIntr.setupPerturbation();
                provider.getPerturbationEngine().setFileName("Maximum.java");
                provider.getPerturbationEngine().makeSpace(new testManager(), new IntegerArrayInputProvider());
                break;
            case "su":

                SudokuIntr.setupPerturbation();
                provider.getPerturbationEngine().setFileName("Sudoku.java");
                provider.getPerturbationEngine().makeSpace(new SudokuManager(), new SudokuInputProvider(CommandLineParser.CONFIG.get("grid")));
                break;
            case "md5":
                MD5Intr.setupPerturbation();
                provider.getPerturbationEngine().setFileName("MD5.java");
                provider.getPerturbationEngine().makeSpace(new MD5Manager(), new ByteArrayInputProvider());
                break;


    }

}
