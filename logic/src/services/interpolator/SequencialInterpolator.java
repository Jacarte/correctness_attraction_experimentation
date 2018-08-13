package services.interpolator;


import services.interpolator.Interpolator;

public class SequencialInterpolator implements IntegerInterpolator{


    int startFrom = -5;
    int endIn = 5;

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
