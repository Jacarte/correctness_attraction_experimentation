package policy.impl;

import com.github.antlrjavaparser.api.expr.VariableDeclarationExpr;
import com.github.antlrjavaparser.api.type.PrimitiveType;
import policy.IPolicy;

public class VariableDeclarationPolicy implements IPolicy<VariableDeclarationExpr> {
    @Override
    public boolean check(VariableDeclarationExpr node) {

        return node.getType().equals(new PrimitiveType(PrimitiveType.Primitive.Int));
    }
}
