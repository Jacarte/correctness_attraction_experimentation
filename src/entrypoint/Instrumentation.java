package entrypoint;

import com.github.antlrjavaparser.JavaParser;
import com.github.antlrjavaparser.api.CompilationUnit;
import services.utils.DirExplorer;
import services.utils.IServiceProvider;
import services.utils.ServiceProvider;
import services.utils.StaticUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class Instrumentation {

    static String[] args;

    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {


        setup();

        Instrumentation.args = args;

        StaticUtils.serviceProvider = provider;

        makeInstrumentation();
    }

    static IServiceProvider provider;

    public static void setup(){

        provider = new ServiceProvider();
    }

    public static void makeInstrumentation(){
        String path = args[1];
        makeInstrumentation(path);
    }


    public static void makeInstrumentation(String dirName){
        new DirExplorer((level, path, file) -> {
            return path.endsWith(".java") &&
                    !path.endsWith(provider.getNamingService().getInstrumentationSuffix() + ".java");
        }, (level,parent, path, file) -> {
            CompilationUnit unit = null;
            try {
                provider.getNamingService().reset();
                provider.getPerturbationEngine().reset();
                provider.getTranslator().reset();

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

