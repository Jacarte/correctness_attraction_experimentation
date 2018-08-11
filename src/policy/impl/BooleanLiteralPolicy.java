package policy.impl;

import com.github.antlrjavaparser.api.expr.BooleanLiteralExpr;
import policy.IPolicy;

public class BooleanLiteralPolicy implements IPolicy<BooleanLiteralExpr> {

    @Override
    public boolean check(BooleanLiteralExpr node) {
        return true;
    }

}
