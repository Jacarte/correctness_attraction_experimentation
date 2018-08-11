package policy;

import com.github.antlrjavaparser.api.body.MethodDeclaration;
import com.github.antlrjavaparser.api.body.VariableDeclarator;
import com.github.antlrjavaparser.api.expr.BooleanLiteralExpr;
import com.github.antlrjavaparser.api.expr.IntegerLiteralExpr;
import com.github.antlrjavaparser.api.expr.VariableDeclarationExpr;
import policy.impl.*;

import java.util.Map;
import java.util.TreeMap;

public class PolicyFactory implements IPolicyFactory {

    private static Map<String, Class> policies = new TreeMap<>();

    static {
        policies.put(BooleanLiteralExpr.class.getName(), BooleanLiteralPolicy.class);
        policies.put(IntegerLiteralExpr.class.getName(), IntegerLiteralPolicy.class);
        policies.put(MethodDeclaration.class.getName(), MethodDeclarationPolicy.class);
        policies.put(VariableDeclarationExpr.class.getName(), VariableDeclarationPolicy.class);
    }

    @Override
    public <T> IPolicy<T> getPolicy(Object type) {

        Object result = new AlwaysFalsePolicy();


        try {
            return (IPolicy<T>) policies.get(type.getClass().getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (IPolicy<T>) result;
    }
}
