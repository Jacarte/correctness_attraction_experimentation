package services.interpolator;

import services.engine.IBooleanPerturbationPoint;

public class BooleanInterpolator implements IBooleanInterpolator {

    int state = 0;

    @Override
    public boolean getValue(boolean value) {
        return (state <= 1) == value;
    }

    @Override
    public void incrementInSpace() {
        state++;
    }

    @Override
    public String getPerturbationOperation() {
        return (state <= 1)? "Original":"Negation";
    }

    @Override
    public Object getNext() {
        return null;
    }

    @Override
    public boolean canNext() {

        return state < 2;
    }

    @Override
    public void reset() {
        state = 0;
    }
}
