import com.github.antlrjavaparser.JavaParser;
import com.github.antlrjavaparser.api.*;
import services.Translator;
import services.api.INamingService;
import services.api.ITranslator;
import services.engine.ISpaceExplorer;
import services.engine.PolicyFactory;
import services.engine.NamingService;
import services.test.IService1;
import services.test.IService2;
import services.test.Service1;
import services.test.Service2;
import services.utils.IServiceProvider;
import services.utils.ServiceProvider;
import services.utils.StaticUtils;
import services.visitor.TransformationVisitor;
import target.*;
//import target.testIntr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {



    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {


        setup();

        StaticUtils.serviceProvider = provider;

        CompilationUnit unit = JavaParser.parse(new FileInputStream("./target/test.java"));

        provider.getTranslator().setFileName("test.java");

        unit.accept(provider.getVisitor(), null);


        FileOutputStream out = new FileOutputStream("./target/" + unit.getTypes().get(0).getName() + ".java");

        out.write(unit.toString().getBytes());

        out.close();


        testIntr.setupPerturbation();

        provider.getPerturbationEngine().makeSpace(
                () -> testIntr.getMaximum(new int[] {1,2,25,1,3,12,12,22}),
                (obj) -> obj.equals(test.getMaximum(new int[] {1,2,25,1,3,12,12,22})),
                () -> test.getMaximum(new int[] {1,2,25,1,3,12,12,22})
                );

    }

    static IServiceProvider provider;

    public static void setup(){

        provider = new ServiceProvider();

    }
}
