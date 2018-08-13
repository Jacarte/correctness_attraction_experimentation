package policies;

import com.github.antlrjavaparser.api.expr.BooleanLiteralExpr;
import services.policies.IPolicy;

public class BooleanLiteralPolicy implements IPolicy<BooleanLiteralExpr> {

    @Override
    public boolean check(BooleanLiteralExpr node) {
        return true;
    }

}
