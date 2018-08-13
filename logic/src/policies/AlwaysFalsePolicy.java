package policies;

import services.policies.IPolicy;

public class AlwaysFalsePolicy implements IPolicy<Object> {
    @Override
    public boolean check(Object node) {
        return false;
    }
}
