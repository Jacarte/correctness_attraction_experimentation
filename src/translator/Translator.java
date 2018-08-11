package translator;

import com.github.antlrjavaparser.api.body.VariableDeclarator;
import com.github.antlrjavaparser.api.expr.*;
import com.github.antlrjavaparser.api.stmt.ForStmt;
import com.github.antlrjavaparser.api.type.PrimitiveType;
import com.github.antlrjavaparser.api.type.Type;
import sun.tools.tree.ForStatement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Translator implements ITranslator {


    static Type intType = new PrimitiveType(PrimitiveType.Primitive.Int);
    static Type boolType = new PrimitiveType(PrimitiveType.Primitive.Boolean);

    @Override
    public void translate(VariableDeclarator node, Type target) {

        node.setInit(translateExpression(node.getInit(), target));

    }

    @Override
    public void translate(AssignExpr expr, Type target) {
        expr.setValue(translateExpression(expr.getValue(), target));
    }

    @Override
    public void translate(UnaryExpr expr, Type target) {
        expr.setExpr(translateExpression(expr.getExpr(), target));
    }

    @Override
    public void translate(MethodCallExpr expr, List<Type> callArgs, Type storedType) {

        List<Expression> args = new ArrayList<>();

        if(expr.getArgs() != null)
            for(int i = 0; i < expr.getArgs().size(); i++){
                args.add(translateExpression(expr.getArgs().get(i), callArgs.get(i)));
            }

        expr.setArgs(args);
    }

    private Expression translateExpression(Expression base, Type t){

        if(t == null)
            return base;

        if(t.equals(intType))
            return new MethodCallExpr(getScope(), integerPerturbationMethodName(), Arrays.asList(base));
        if(t.equals(boolType))
            return new MethodCallExpr(getScope(), booleanPerturbationMethodName(), Arrays.asList(base));

        return base;
    }

    @Override
    public void translate(BinaryExpr expr, Type leftType, Type rightType) {

        if(leftType != null && rightType != null){

            expr.setLeft(translateExpression(expr.getLeft(), leftType));
            expr.setRight(translateExpression(expr.getRight(), rightType));
        }

    }

    @Override
    public void translate(ForStmt expr, Type updateType) {

        expr.setCompare(translateExpression(expr.getCompare(), updateType));

    }


    Expression scope;

    @Override
    public Expression getScope() {
        return scope;
    }

    @Override
    public void setScope(Expression scope) {
        this.scope = scope;
    }

    @Override
    public String integerPerturbationMethodName() {
        return "pInt";
    }

    @Override
    public String booleanPerturbationMethodName() {
        return "pBool";
    }
}
