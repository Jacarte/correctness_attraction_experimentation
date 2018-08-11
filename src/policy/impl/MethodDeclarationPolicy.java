package policy.impl;

import com.github.antlrjavaparser.api.body.MethodDeclaration;
import policy.IPolicy;

public class MethodDeclarationPolicy implements IPolicy<MethodDeclaration> {
    @Override
    public boolean check(MethodDeclaration node) {
        return true;
    }
}
