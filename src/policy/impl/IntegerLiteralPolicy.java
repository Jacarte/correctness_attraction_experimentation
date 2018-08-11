package policy.impl;

import com.github.antlrjavaparser.api.expr.IntegerLiteralExpr;
import policy.IPolicy;

public class IntegerLiteralPolicy implements IPolicy<IntegerLiteralExpr> {

    @Override
    public boolean check(IntegerLiteralExpr node) {

        System.out.println("Literal integer found at col: " + node.getBeginColumn());

        return true;
    }

}
