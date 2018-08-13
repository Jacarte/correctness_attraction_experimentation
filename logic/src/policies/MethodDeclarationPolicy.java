package policies;

import com.github.antlrjavaparser.api.body.MethodDeclaration;
import services.policies.IPolicy;

public class MethodDeclarationPolicy implements IPolicy<MethodDeclaration> {
    @Override
    public boolean check(MethodDeclaration node) {

        return true;
    }
}
