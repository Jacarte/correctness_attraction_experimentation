package services.api;

import com.github.antlrjavaparser.api.CompilationUnit;
import com.github.antlrjavaparser.api.body.ClassOrInterfaceDeclaration;
import com.github.antlrjavaparser.api.body.VariableDeclarator;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.*;
import com.github.antlrjavaparser.api.type.*;

import java.util.List;

public interface ITranslator {

    void translate(VariableDeclarator node, Type target);

    void translate(AssignExpr expr, Type target);

    void translate(UnaryExpr expr, Type target);

    void translate(MethodCallExpr expr, List<Type> callArgs, Type storeType);

    void translate(BinaryExpr expr, Type leftType, Type rightType);

    void translate(ForStmt expr, List<Type> updateTypes);

    void translate(ReturnStmt expr, Type returnType);

    void translate(SwitchStmt expr, Type selectorType);

    void translate(SwitchEntryStmt expr, Type labelType);

    void translate(IfStmt expr, Type conditionType);

    void translate(WhileStmt expr, Type conditionType);

    void translate(DoStmt expr, Type conditionType);

    void translate(ExpressionStmt expr, Type t);

    void translate(ArrayAccessExpr expr, Type t);

    void translate(EnclosedExpr expr, Type t);

    void translate(ClassOrInterfaceDeclaration expr);

    void translate(CompilationUnit unit);

    Expression getScope();

    void setScope(Expression scope);


    void setFileName(String name);

    void reset();

    List<InterestingPoint> getPbis();


    class InterestingPoint{
        public int colNumber;
        public int rowNumber;

        public String variableId;

        public String fileName;

        public String perturbationType;

        public int pbiIndex;

        public String original;
    }
}
