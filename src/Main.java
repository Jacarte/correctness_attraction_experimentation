import com.github.antlrjavaparser.JavaParser;
import com.github.antlrjavaparser.api.*;
import services.Translator;
import services.api.INamingService;
import services.api.ITranslator;
import services.engine.ISpaceExplorer;
import services.engine.PolicyFactory;
import services.engine.NamingService;
import services.interpolator.IntegerArrayInputProvider;
import services.test.IService1;
import services.test.IService2;
import services.test.Service1;
import services.test.Service2;
import services.utils.DirExplorer;
import services.utils.IServiceProvider;
import services.utils.ServiceProvider;
import services.utils.StaticUtils;
import services.visitor.TransformationVisitor;
import target.quicksort.QuickSortIntr;
import target.quicksort.QuickSortManager;
//import target.testIntr;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Main {



    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {


        setup();

        StaticUtils.serviceProvider = provider;

        //makeInstrumentation("./target");


        QuickSortIntr.setupPerturbation();

        System.setErr(new PrintStream(new FileOutputStream("error.txt")));
        // System.setOut(new PrintStream(new FileOutputStream("out.txt")));

        provider.getPerturbationEngine().makeSpace(new QuickSortManager(), new IntegerArrayInputProvider());




        /*testIntr.setupPerturbation();

        testManager manager = new testManager();

        provider.getPerturbationEngine().makeSpace(manager, new IntegerArrayInputProvider());*/

    }

    static IServiceProvider provider;

    public static void setup(){

        provider = new ServiceProvider();

    }


    public static void makeInstrumentation(String dirName){
        new DirExplorer((level, path, file) -> {
            return path.endsWith(".java") &&
                    !path.endsWith(provider.getNamingService().getInstrumentationSuffix() + ".java");
        }, (level,parent, path, file) -> {
            CompilationUnit unit = null;
            try {
                unit = JavaParser.parse(new FileInputStream(path));

                provider.getTranslator().setFileName(file.getName());

                unit.accept(provider.getVisitor(), null);

                FileOutputStream out = new FileOutputStream(parent + "/" + unit.getTypes().get(0).getName() + ".java");

                out.write(unit.toString().getBytes());

                out.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        }).explore(new File(dirName));
    }
}
