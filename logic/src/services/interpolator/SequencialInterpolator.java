package services.interpolator;


import services.interpolator.Interpolator;

public class SequencialInterpolator implements IntegerInterpolator{


    protected int startFrom = -100;
    protected int endIn = 100;

    int current = startFrom;

    @Override
    public Object getNext() {
        return current++;
    }

    @Override
    public boolean canNext() {
        return current != endIn;
    }

    @Override
    public void reset() {
        current = startFrom;
    }


}
