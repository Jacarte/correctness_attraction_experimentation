package translator;

import com.github.antlrjavaparser.api.body.VariableDeclarator;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.*;
import com.github.antlrjavaparser.api.type.*;
import sun.tools.tree.ForStatement;
import sun.tools.tree.SwitchStatement;

import java.util.List;

public interface ITranslator {

    void translate(VariableDeclarator node, Type target);

    void translate(AssignExpr expr, Type target);

    void translate(UnaryExpr expr, Type target);

    void translate(MethodCallExpr expr, List<Type> callArgs, Type storeType);

    void translate(BinaryExpr expr, Type leftType, Type rightType);

    void translate(ForStmt expr, Type updateType);

    void translate(ReturnStmt expr, Type returnType);

    void translate(SwitchStmt expr, Type selectorType);

    void translate(SwitchEntryStmt expr, Type labelType);

    void translate(IfStmt expr, Type conditionType);

    void translate(WhileStmt expr, Type conditionType);

    void translate(DoStmt expr, Type conditionType);

    void translate(ExpressionStmt expr, Type t);

    void translate(ArrayAccessExpr expr, Type t);

    void translate(EnclosedExpr expr, Type t);

    Expression getScope();

    void setScope(Expression scope);

    String integerPerturbationMethodName();

    String booleanPerturbationMethodName();
}
