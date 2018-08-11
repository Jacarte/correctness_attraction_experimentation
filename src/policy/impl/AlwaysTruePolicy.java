package policy.impl;

import policy.IPolicy;

public class AlwaysTruePolicy implements IPolicy<Object> {
    @Override
    public boolean check(Object node) {
        return true;
    }
}
