package translator;

import com.github.antlrjavaparser.api.body.VariableDeclarator;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.ForStmt;
import com.github.antlrjavaparser.api.type.*;
import sun.tools.tree.ForStatement;

import java.util.List;

public interface ITranslator {

    void translate(VariableDeclarator node, Type target);

    void translate(AssignExpr expr, Type target);

    void translate(UnaryExpr expr, Type target);

    void translate(MethodCallExpr expr, List<Type> callArgs, Type storeType);

    void translate(BinaryExpr expr, Type leftType, Type rightType);

    void translate(ForStmt expr, Type updateType);

    Expression getScope();

    void setScope(Expression scope);

    String integerPerturbationMethodName();

    String booleanPerturbationMethodName();
}
