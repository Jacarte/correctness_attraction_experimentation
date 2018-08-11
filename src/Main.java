import com.github.antlrjavaparser.JavaParser;
import com.github.antlrjavaparser.api.*;
import com.github.antlrjavaparser.api.body.*;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.*;
import com.github.antlrjavaparser.api.type.*;
import com.github.antlrjavaparser.api.visitor.GenericVisitor;
import com.github.antlrjavaparser.api.visitor.GenericVisitorAdapter;
import com.github.antlrjavaparser.api.visitor.VoidVisitor;
import com.github.antlrjavaparser.api.visitor.VoidVisitorAdapter;
import dsl.AnnotationDSLService;
import org.w3c.dom.traversal.NodeIterator;
import policy.PolicyFactory;
import services.NamingService;
import sun.tools.java.Environment;
import translator.Translator;
import visitor.TransformationVisitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class Main {



    public static void main(String[] args) throws IOException {

        CompilationUnit unit = JavaParser.parse(new FileInputStream("./target/test.java"));

        Translator t = new Translator("test.java", new NamingService());


        unit.accept(TransformationVisitor.getInstance(new PolicyFactory(), t), null);

        System.out.println(unit.toString());
    }
}
